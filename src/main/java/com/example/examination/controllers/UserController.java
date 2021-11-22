package com.example.examination.controllers;

import com.example.examination.dto.response.UserResponseDto;
import com.example.examination.repository.UserRepository;
import com.example.examination.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/detail/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('TEACHER')")
    public ResponseEntity<UserResponseDto> getDetailInfomation(@PathVariable Integer id) {
        UserResponseDto result = userService.getDetailInfomation(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
