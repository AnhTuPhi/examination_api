package com.example.examination.service;

import com.example.examination.dto.response.UserResponseDto;

import java.util.List;

public interface AdminService {
    List<UserResponseDto> getListUser();

}
