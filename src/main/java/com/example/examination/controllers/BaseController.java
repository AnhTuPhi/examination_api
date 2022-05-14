package com.example.examination.controllers;

import com.example.examination.config.db.dao.UserDao;
import com.example.examination.config.db.dto.DBResponse;
import com.example.examination.config.db.dto.UserCache;
import com.example.examination.config.db.dto.UserParam;
import com.example.examination.entity.User;
import com.example.examination.exception.EAException;
import com.example.examination.exception.ErrorMessage;
import com.example.examination.helper.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected static Map<String, UserCache> _MAP_USER = new HashMap<>();
    protected String SESSION_ID;
    protected User _curUser = null;

    @Autowired
    UserDao userDao;

    public User getCurrentUser() throws EAException {
        return getCurrentUser(null);
    }

    public User getCurrentUser(HttpServletRequest request) throws EAException {
        String userName = "";
        if (request != null) {
            Authentication principal = (Authentication) request.getUserPrincipal();
            userName = principal.getName();
        } else {
            userName = Helper.getUserName();
        }
        System.out.println("username " + userName);
        UserCache userCache = new UserCache();
        if (_MAP_USER.containsKey(userName)) {
            userCache = _MAP_USER.get(userName);
            if (!userCache.isExprire()) {
                return userCache.getUser();
            }
        }

        logger.info("[USER] Get user info from DB {}", userName);
        DBResponse<List<User>> user = null;
        try {
            Date now = new Date();
            UserParam param = new UserParam();
            param.setUsername(userName);
            user = userDao.getUserV2(SESSION_ID, param);
            if (user != null && !user.getResult().isEmpty()) {
                for (int i = 0; i < user.getResult().size(); i++) {
                    _curUser = user.getResult().get(i);
                    if (userName.equals(_curUser.getUsername())) {
                        Date newDate = new Date(now.getTime() + 5 * 60 * 1000);
                        userCache.setExprireAt(newDate);
                        userCache.setUser(_curUser);

                        _MAP_USER.put(userName, userCache);
                        return _curUser;
                    }
                }
            }
            return null;
        } catch (Exception e) {
            throw new EAException(ErrorMessage.USER_NOT_FOUND);
        }
    }
}
