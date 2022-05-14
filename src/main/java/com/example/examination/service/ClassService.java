package com.example.examination.service;

import com.example.examination.dto.request.InsClassV1;
import com.example.examination.dto.response.ClassResponseV1;

public interface ClassService {
    ClassResponseV1 createRoom(InsClassV1 dto);
}
