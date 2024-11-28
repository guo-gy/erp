package com.erp.utils;

public class JsonResponse<T> {
    public T data;
    public String message;
    public boolean success;

    public JsonResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
