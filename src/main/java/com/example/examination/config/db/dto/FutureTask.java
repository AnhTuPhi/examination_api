package com.example.examination.config.db.dto;

import java.util.function.Supplier;

public abstract class FutureTask<T> implements Supplier<T> {
    public abstract String type();
}
