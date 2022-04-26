package com.example.examination.entity;

import javax.persistence.*;

@Entity
@Table(name = "log_activity")
public class LogActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "log_id")
    private Integer id;

    @Column(name = "activity")
    private String activity;

    @Column(name = "object_type")
    private String objectType;

    @Column(name = "object_id")
    private Integer objectId;

    @Column(name = "field")
    private String field;

    @Column(name = "new_property")
    private Integer newProperty;

    @Column(name = "log_json")
    private String jsonLog;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LogActivity() {
    }

    public LogActivity(Integer id, String activity, String objectType, Integer objectId, String field, Integer newProperty, String jsonLog) {
        this.id = id;
        this.activity = activity;
        this.objectType = objectType;
        this.objectId = objectId;
        this.field = field;
        this.newProperty = newProperty;
        this.jsonLog = jsonLog;
    }
}
