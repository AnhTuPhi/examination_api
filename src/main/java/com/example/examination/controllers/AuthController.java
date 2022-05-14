package com.example.examination.controllers;

import com.example.examination.config.db.dao.UserDao;
import com.example.examination.config.db.dto.DBResponse;
import com.example.examination.dto.request.InsUserV1;
import com.example.examination.dto.request.LoginRequest;
import com.example.examination.dto.request.SignupRequest;
import com.example.examination.dto.response.EAResponse;
import com.example.examination.dto.response.JwtResponse;
import com.example.examination.dto.response.MessageResponse;
import com.example.examination.entity.ERole;
import com.example.examination.entity.Role;
import com.example.examination.exception.ErrorMessage;
import com.example.examination.helper.Const;
import com.example.examination.repository.RoleRepository;
import com.example.examination.repository.UserRepository;
import com.example.examination.serviceImpl.UserDetailsImpl;
import com.example.examination.task.DBLogWriter;
import com.example.examination.utils.JwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    private final UserDao userDao;

    private final DBLogWriter dbLogWriter;

    public AuthController(DBLogWriter dbLogWriter, UserDao userDao) {
        this.dbLogWriter = dbLogWriter;
        this.userDao = userDao;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if(userDetails.getIsLocked()==Const.LOCKED)
            return new ResponseEntity<>(EAResponse.buildResponse(null, 0, ErrorMessage.ACCOUNT_IS_LOCKED.getMessage(), 200), HttpStatus.OK);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new ResponseEntity<>(EAResponse.buildResponse(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles), 0, ErrorMessage.SUCCESS.getMessage(), 200), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws Exception {
        //init data
        String SESSION_ID = UUID.randomUUID().toString();
        String username = signUpRequest.getUsername();
        String email = signUpRequest.getEmail();
        String password = signUpRequest.getPassword();
        Integer age = signUpRequest.getAge();
        String phone = signUpRequest.getPhoneNumber();

        if (userRepository.existsByUsername(username)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(ErrorMessage.USERNAME_ALREADY_TAKEN.getMessage()));
        }

        if (userRepository.existsByEmail(email)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(ErrorMessage.EMAIL_ALREADY_TAKEN.getMessage()));
        }

        // Create new user's account
        InsUserV1 param = new InsUserV1();
        param.setUsername(username);
        param.setEmail(email);
        param.setPassword(encoder.encode(password));
        param.setAge(age);
        param.setPhone(phone);
        param.setUserType(Const.USER_STUDENT_TYPE);
        param.setIsLocked(Const.UN_LOCKED);

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException(ErrorMessage.ROLE_NOT_FOUND.getMessage()));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case Const.USER_ADMIN_TYPE:
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException(ErrorMessage.ROLE_NOT_FOUND.getMessage()));
                        roles.add(adminRole);

                        break;
                    case Const.USER_TEACHER_TYPE:
                        Role modRole = roleRepository.findByName(ERole.ROLE_TEACHER)
                                .orElseThrow(() -> new RuntimeException(ErrorMessage.ROLE_NOT_FOUND.getMessage()));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException(ErrorMessage.ROLE_NOT_FOUND.getMessage()));
                        roles.add(userRole);
                }
            });
        }

//        user.setRoles(roles);
//        userRepository.save(user);
        DBResponse<?> response = userDao.insUserV1(SESSION_ID, param);
        Integer userId = Integer.parseInt(response.getErrorMsg());

        dbLogWriter.writeActivityLogV1(param, userId);
        return ResponseEntity.ok(new MessageResponse(ErrorMessage.CREATE_USER_SUCCESS.getMessage()));
    }
}
