package com.example.examination.task;

import com.example.examination.config.db.dao.LogDao;
import com.example.examination.dto.request.ActivityLogV1;
import com.example.examination.dto.request.InsUserV1;
import com.example.examination.helper.Const;
import com.example.examination.helper.LogHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DBLogWriter {
    private static final Logger logger = LoggerFactory.getLogger(DBLogWriter.class);

    private final LogDao logDao;

    public DBLogWriter(LogDao logDao) {
        this.logDao = logDao;
    }

    //write log ins user
    @Async
    public void writeActivityLogV1(InsUserV1 param, Integer userId) throws Exception {
        try {
            String SESSION_ID = UUID.randomUUID().toString();
            ActivityLogV1 logDto = new ActivityLogV1();
            String json = "{}";
            if (param != null) {
                ObjectMapper mapper = new ObjectMapper();
                json = mapper.writeValueAsString(param);
            }

            logDto.setUserId(Const.SYSTEM_TYPE);
            logDto.setActivity(Const.ACT_INS_USER);
            logDto.setJsonLog(json);
            logDto.setField(Const.FIELD_USER);
            logDto.setNewProperty(Const.UN_LOCKED);
            logDto.setObjectType(Const.OBJECT_TYPE_USER);
            logDto.setObjectId(userId);

            logDao.insLogActivityV1(SESSION_ID, logDto);
        } catch (final Exception e) {
            logger.error("DBLogWriter Error: ", e);
        }
    }
}
