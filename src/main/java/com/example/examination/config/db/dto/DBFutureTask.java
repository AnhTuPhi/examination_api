package com.example.examination.config.db.dto;

public abstract class DBFutureTask<T> extends FutureTask<T> {
    public String type(){return "DB";}
}
