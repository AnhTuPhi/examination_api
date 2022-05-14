package com.example.examination.config.db.dto;

import com.example.examination.entity.User;

import java.util.Date;

public class UserCache {
    private User user;
    private Date exprireAt;
    private int groupId;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExprireAt() {
        return exprireAt;
    }

    public void setExprireAt(Date exprireAt) {
        this.exprireAt = exprireAt;
    }

    public boolean isExprire() {
        return (exprireAt.compareTo(new Date()) < 0);
    }
}
