package com.example.examination.config.db.dao;

import com.example.examination.config.db.dto.DBResponse;
import com.example.examination.config.db.dto.UserParam;
import com.example.examination.config.db.dto.UserResponse;
import com.example.examination.config.db.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDao extends BaseService {
    private static final String GET_USER = "get_user";

    /* BEGIN GET */
    public DBResponse<List<UserResponse>> getUser(String sessionId, UserParam params) {
        return this.dbGet(sessionId, GET_USER, params, UserResponse.class);
    }
    /* END GET */

}
