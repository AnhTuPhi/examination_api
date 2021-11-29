package com.example.examination.controllers;

import com.example.examination.dto.request.RoomDto;
import com.example.examination.dto.response.EAResponse;
import com.example.examination.service.TeacherSesrvice;
import com.example.examination.serviceImpl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    public final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    TeacherSesrvice teacherSesrvice;

    @PostMapping("/create/room")
    @PreAuthorize("hasRole('TEACHER')")
    public EAResponse createRoom(@RequestBody RoomDto dto){
        EAResponse response;
        response = teacherSesrvice.createRoom(dto);
        return response;
    }
}
