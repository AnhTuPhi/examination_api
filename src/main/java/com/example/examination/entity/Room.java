package com.example.examination.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "room_id")
    private Integer roomId;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "room_code")
    private String roomCode;

    @Column(name = "title")
    private String title;

    @Column(name = "sub_title")
    private String subTitle;

    @Column(name = "subject")
    private String subject;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User createdBy;

    @Column(name = "create_date")
    private Date createDate;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
