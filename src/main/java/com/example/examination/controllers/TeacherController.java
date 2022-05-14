package com.example.examination.controllers;

import com.example.examination.service.TeacherSesrvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    public final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    TeacherSesrvice teacherSesrvice;


}
