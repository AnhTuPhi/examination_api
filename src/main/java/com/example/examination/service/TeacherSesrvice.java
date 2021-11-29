package com.example.examination.service;

import com.example.examination.dto.request.RoomDto;
import com.example.examination.dto.response.EAResponse;

public interface TeacherSesrvice {
    EAResponse createRoom(RoomDto dto);
}
