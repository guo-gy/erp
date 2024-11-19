package com.erp.utils;

// 返回的json格式
public class JsonResponse {

    private final boolean success;
    private final String message;

    public JsonResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
