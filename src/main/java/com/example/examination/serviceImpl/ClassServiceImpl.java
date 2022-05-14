package com.example.examination.serviceImpl;

import com.example.examination.config.db.dao.ClassDao;
import com.example.examination.config.db.dto.DBResponse;
import com.example.examination.dto.request.GetClassV1;
import com.example.examination.dto.request.InsClassV1;
import com.example.examination.dto.response.ClassResponseV1;
import com.example.examination.repository.RoomRepository;
import com.example.examination.service.ClassService;
import com.example.examination.utils.CodeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.UUID;

@Service
public class ClassServiceImpl implements ClassService {
    private static final Logger logger = LoggerFactory.getLogger(ClassServiceImpl.class);

    @Autowired
    RoomRepository roomRepository;

    private final ClassDao classDao;

    public ClassServiceImpl(ClassDao classDao) {
        this.classDao = classDao;
    }

    @Override
    public ClassResponseV1 createRoom(InsClassV1 dto) {
        ClassResponseV1 response = new ClassResponseV1();
        String SESSION_ID = UUID.randomUUID().toString();
        if(!ObjectUtils.isEmpty(dto)) {
            dto.setCode(CodeUtils.getRandomCode8Digit());
//            param.setCreatedBy(dto.getCreatedBy());
            DBResponse<?> dbResponse = classDao.insClassV1(SESSION_ID, dto);
            Integer classId = Integer.parseInt(dbResponse.getErrorMsg());
            logger.info("CREATED CLASS SUCCESS: {}",classId);

            GetClassV1 request = new GetClassV1();
            request.setId(classId);
            DBResponse<List<ClassResponseV1>> classResponse = classDao.getClassV1(SESSION_ID, request);
            List<ClassResponseV1> lstClass = classResponse.getResult();
            response = lstClass.get(0);

            return response;
        }
        return null;
    }
}
