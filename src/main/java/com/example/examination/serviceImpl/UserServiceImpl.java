package com.example.examination.serviceImpl;

import com.example.examination.dto.request.UserDetailDto;
import com.example.examination.dto.response.UserResponseDto;
import com.example.examination.entity.User;
import com.example.examination.repository.UserRepository;
import com.example.examination.service.UserService;
import com.example.examination.utils.LogUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    public final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserResponseDto getDetailInfomation(Integer id) {
        Query query = entityManager.createNativeQuery("SELECT * FROM tbl_users WHERE user_id = :userId");
        query.setParameter("userId", id);
        List<Object[]> data = query.getResultList();
        UserResponseDto userResponseDto = new UserResponseDto();
        for (Object[] row : data) {
            if (row[0] != null) {
                userResponseDto.setUserId(Integer.parseInt(row[0].toString()));
            }
            if (row[1] != null) {
                userResponseDto.setAge(Integer.parseInt(row[1].toString()));
            }
            if (row[2] != null) {
                userResponseDto.setCreateDate(row[2].toString());
            }
            if (row[3] != null) {
                userResponseDto.setEmail(row[3].toString());
            }
            if (row[4] != null) {
                userResponseDto.setModifyDate(row[4].toString());
            }
            if (row[5] != null) {
                userResponseDto.setPassword(row[5].toString());
            }
            if (row[6] != null) {
                userResponseDto.setPhoneNumber(row[6].toString());
            }
            if (row[7] != null) {
                userResponseDto.setUsername(row[7].toString());
            }
        }
        logger.info(LogUtils.toJson(userResponseDto));
        return userResponseDto;
    }

    @Override
    public UserResponseDto updateDetailInfomation(UserDetailDto dto) {
        if(dto != null){
            User user = null;
            if(dto.getUserId() != null){
                user = userRepository.getById(dto.getUserId());
            }
            user.setPassword(dto.getPassword());
            user.setEmail(dto.getEmail());
            user.setAge(dto.getAge());
            user.setPhoneNumber(dto.getPhoneNumber());
            user.setModifyDate(new Date());
            user = userRepository.save(user);
        }
        return null;
    }

}
