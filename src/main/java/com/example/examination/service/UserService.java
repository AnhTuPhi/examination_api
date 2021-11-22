package com.example.examination.service;

import com.example.examination.dto.request.UserDetailDto;
import com.example.examination.dto.response.UserResponseDto;

public interface UserService {
    UserResponseDto getDetailInfomation(Integer id);

    UserResponseDto updateDetailInfomation(UserDetailDto dto);
}
