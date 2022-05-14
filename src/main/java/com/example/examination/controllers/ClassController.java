package com.example.examination.controllers;

import com.example.examination.dto.request.InsClassV1;
import com.example.examination.dto.response.EAResponse;
import com.example.examination.dto.response.ClassResponseV1;
import com.example.examination.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    ClassService classService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('TEACHER')")
    public EAResponse<InsClassV1> createRoom(@RequestBody InsClassV1 dto){
        ClassResponseV1 result = classService.createRoom(dto);
        if(!ObjectUtils.isEmpty(result))
            return EAResponse.buildResponse(result, 1, "CREATED ROOM SUCCESS", HttpStatus.OK.value());
        return EAResponse.buildResponse(null, 0, "CREATED ROOM FAILED", HttpStatus.BAD_REQUEST.value());
    }
}
