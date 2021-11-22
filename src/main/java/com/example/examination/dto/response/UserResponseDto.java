package com.example.examination.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.objects.annotations.Property;

import java.util.Date;

public class UserResponseDto {
    private Integer userId;
    private String password;
    private String username;
    private String email;
    private Integer age;
    private String phoneNumber;
    private String createDate;
    private String modifyDate;

    public UserResponseDto() {
    }

    public UserResponseDto(Integer userId, String password, String username, String email, Integer age, String phoneNumber, String createDate, String modifyDate) {
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.email = email;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }
}
