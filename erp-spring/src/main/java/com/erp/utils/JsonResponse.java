package com.erp.utils;

public class JsonResponse {
    private String message;
    private boolean success;

    public JsonResponse() {
    }

    public JsonResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "JsonResponse{" +
                "message='" + message + '\'' +
                ", success=" + success +
                '}';
    }
}
