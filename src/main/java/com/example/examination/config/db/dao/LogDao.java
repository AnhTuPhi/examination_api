package com.example.examination.config.db.dao;

import com.example.examination.config.db.dto.DBResponse;
import com.example.examination.config.db.service.BaseService;
import com.example.examination.dto.request.ActivityLogV1;
import com.example.examination.dto.request.InsUserV1;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogDao extends BaseService {
    private static final String INS_LOG_ACTIVITY_V1 = "ins_log_activity_v1";

    public DBResponse<?>insLogActivityV1(String sessionId, ActivityLogV1 param) {
        return dbInsOrUpd(sessionId, INS_LOG_ACTIVITY_V1, param);
    }
}
