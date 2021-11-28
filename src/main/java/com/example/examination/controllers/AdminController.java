package com.example.examination.controllers;

import com.example.examination.dto.response.UserResponseDto;
import com.example.examination.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/listUser")
    public ResponseEntity<List<UserResponseDto>> getListUser(){
        List<UserResponseDto> result = adminService.getListUser();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
