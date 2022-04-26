package com.example.examination.dto.request;

public class ActivityLogV1 {
    private Integer userId;
    private String activity;
    private String objectType;
    private Integer objectId;
    private String field;
    private Integer newProperty;
    private String jsonLog;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Integer getNewProperty() {
        return newProperty;
    }

    public void setNewProperty(Integer newProperty) {
        this.newProperty = newProperty;
    }

    public String getJsonLog() {
        return jsonLog;
    }

    public void setJsonLog(String jsonLog) {
        this.jsonLog = jsonLog;
    }
}
