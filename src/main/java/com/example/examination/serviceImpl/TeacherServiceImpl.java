package com.example.examination.serviceImpl;

import com.example.examination.dto.request.RoomDto;
import com.example.examination.dto.response.EAResponse;
import com.example.examination.entity.Room;
import com.example.examination.repository.RoomRepository;
import com.example.examination.service.TeacherSesrvice;
import com.example.examination.utils.CodeUtils;
import com.example.examination.utils.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TeacherServiceImpl implements TeacherSesrvice {
    private static final Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

    @Autowired
    RoomRepository roomRepository;

    @Override
    public EAResponse createRoom(RoomDto dto) {
        if(dto != null){
            Room room = new Room();
            room.setRoomCode(CodeUtils.getRandomCode8Digit());
            room.setRoomName(dto.getRoomName());
            room.setTitle(dto.getTitle());
            room.setSubTitle(dto.getSubTitle());
            room.setSubject(dto.getSubject());
            room.setCreatedBy(dto.getCreatedBy());
            room.setCreateDate(new Date());
            room = roomRepository.save(room);
            logger.info("CREATED ROOM SUCCESS: {}", room.getRoomId());

            return EAResponse.buildResponse(null, 1, "CREATED ROOM SUCCESS", HttpStatus.OK.value());
        }
        return EAResponse.buildResponse(null, 0, "CANNOT CREATE ROOM", HttpStatus.BAD_REQUEST.value());
    }
}
