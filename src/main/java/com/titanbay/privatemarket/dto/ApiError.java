package com.titanbay.privatemarket.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError {

    private int status;

    private String message;

    private long timestamp;

    public ApiError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }
}
