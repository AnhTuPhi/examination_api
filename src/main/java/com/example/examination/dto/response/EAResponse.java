package com.example.examination.dto.response;

import com.example.examination.exception.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EAResponse<T> {
    private int code;
    private String message;
    private T data;
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @SuppressWarnings("unchecked")
    public static EAResponse buildResponse(Object data) {
        EAResponse res = new EAResponse();
        res.data = data;
        res.code = 200;
        return res;
    }

    public static EAResponse<String> buildResponse(ErrorMessage errorMessage) {
        EAResponse<String> res = new EAResponse<>();
        res.code = errorMessage.getCode();
        res.message = errorMessage.getMessage();
        return res;
    }

    public static <T> EAResponse<T> buildResponse(T data, ErrorMessage errorMessage) {
        EAResponse<T> res = new EAResponse<>();
        res.data = data;
        res.code = errorMessage.getCode();
        res.message = errorMessage.getMessage();
        return res;
    }

    @SuppressWarnings("rawtypes")
    public static EAResponse buildResponse(Object data, Integer total) {
        EAResponse res = buildResponse(data);
        res.total = total;
        return res;
    }

    public static EAResponse buildResponse(Object data, Integer total, String message, Integer errCode) {
        EAResponse res = buildResponse(data, total);
        res.code = errCode;
        res.setMessage(message);
        return res;
    }

    public static EAResponse buildApplicationException(String tmsEx, int code) {
        EAResponse res = new EAResponse();
        res.code = code;
        res.message = tmsEx;
        return res;
    }
    public static ResponseEntity buildExcelFileResponse(byte[] data, String fileName) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-disposition", "attachment; filename=" + fileName);
        responseHeaders.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        ResponseEntity respEntity = new ResponseEntity(data, responseHeaders, HttpStatus.OK);
        return respEntity;
    }

    public static ResponseEntity buildDeninedPermission() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json");
        ResponseEntity respEntity = new ResponseEntity(
                "You don't have permision to run this function, please contact Administrator!", responseHeaders,
                HttpStatus.BAD_REQUEST);
        return respEntity;
    }
}
