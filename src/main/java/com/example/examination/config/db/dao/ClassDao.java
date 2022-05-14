package com.example.examination.config.db.dao;

import com.example.examination.config.db.dto.DBResponse;
import com.example.examination.config.db.dto.UserParam;
import com.example.examination.config.db.dto.UserResponse;
import com.example.examination.config.db.service.BaseService;
import com.example.examination.dto.request.GetClassV1;
import com.example.examination.dto.request.InsClassV1;
import com.example.examination.dto.response.ClassResponseV1;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassDao extends BaseService {
    private static final String INS_CLASS_V1 = "ins_class_v1";
    private static final String GET_CLASS_V1 = "get_class_v1";

    public DBResponse<?>insClassV1(String sessionId, InsClassV1 params) {
        return dbInsOrUpd(sessionId, INS_CLASS_V1, params);
    }

    public DBResponse<List<ClassResponseV1>> getClassV1(String sessionId, GetClassV1 params) {
        return this.dbGet(sessionId, GET_CLASS_V1, params, ClassResponseV1.class);
    }
}
