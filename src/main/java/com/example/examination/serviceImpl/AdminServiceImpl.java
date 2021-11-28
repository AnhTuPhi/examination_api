package com.example.examination.serviceImpl;

import com.example.examination.dto.response.UserResponseDto;
import com.example.examination.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Override
    public List<UserResponseDto> getListUser() {
        return null;
    }
}
