package com.example.examination.controllers;

import com.example.examination.dto.request.UserDetailDto;
import com.example.examination.dto.response.UserResponseDto;
import com.example.examination.entity.User;
import com.example.examination.repository.UserRepository;
import com.example.examination.security.AuthTokenFilter;
import com.example.examination.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping("/detail/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('TEACHER')")
    public ResponseEntity<UserResponseDto> getDetailInfomation(@PathVariable Integer id) {
        UserResponseDto result = userService.getDetailInfomation(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('USER') or hasRole('TEACHER')")
    public ResponseEntity<UserResponseDto> updateDetailInfomation(@RequestBody UserDetailDto dto, Authentication authentication){
        String curUser = authentication.getName();
        logger.info("current user : {}", curUser);
        UserResponseDto result = userService.updateDetailInfomation(dto, curUser);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
