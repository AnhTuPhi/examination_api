package com.example.examination.config.db.dto;

public abstract class FileFutureTask<T> extends FutureTask<T> {
    public String type(){return "FILE";}
}

