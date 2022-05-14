package com.example.examination.config.db.dao;

import com.example.examination.config.db.dto.DBResponse;
import com.example.examination.config.db.dto.UserParam;
import com.example.examination.config.db.dto.UserResponse;
import com.example.examination.config.db.service.BaseService;
import com.example.examination.dto.request.InsUserV1;
import com.example.examination.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDao extends BaseService {
    private static final String GET_USER = "get_user";
    private static final String GET_USER_V2 = "get_user_v2";
    private static final String INS_USER_V1 = "ins_user_v1";

    public DBResponse<List<UserResponse>> getUser(String sessionId, UserParam params) {
        return this.dbGet(sessionId, GET_USER, params, UserResponse.class);
    }

    public DBResponse<List<User>> getUserV2(String sessionId, UserParam params) {
        return this.dbGet(sessionId, GET_USER_V2, params, User.class);
    }

    public DBResponse<?>insUserV1(String sessionId, InsUserV1 updParams) {
        return dbInsOrUpd(sessionId, INS_USER_V1, updParams);
    }
}
