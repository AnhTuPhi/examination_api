package com.example.examination.exception;

public enum ErrorMessage {
    BAD_REQUEST(400, "bad_request"),
    CONNECTION_DATABASE_FAIL(600, "connection_data_fail"),
    APIKEY_INVALID(600, "apikey_invalid"),
    CONNECTION_REDIS_FAIL(601, "connection_redis_fail"),
    QUEUE_FAIL(602, "queue_fail"),
    AUTHEN_FAIL(603, "authen_fail"),
    SUCCESS(200, "success"),
    INVALID_PARAM(400001, "invalid_input_params"),
    REPORT_ERROR(400002, "cannot_generate_report"),
    CONTRAINS_EXCEPTION (400003,"duplicate key value violates unique constraint"),
    FORBIDDEN_API(40301, "cannot_access_api"),
    NOT_FOUND(404, "resource_not_found"),
    USER_NOT_FOUND(404000, "user_not_found"),
    PASSWORD_NOT_MATCH(404001, "password_not_match"),
    ;

    private int errorCode;

    private String message;

    ErrorMessage(int pCode, String pMessage) {
        errorCode = pCode;
        message = pMessage;
    }

    public int getCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
