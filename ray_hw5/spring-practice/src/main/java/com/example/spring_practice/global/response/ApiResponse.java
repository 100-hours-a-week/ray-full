package com.example.spring_practice.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiResponse<T> {
    private String message;
    private T data;

    public static <T> ApiResponse<T> success(String message, T data){
        return new ApiResponse<>(message, data);
    }
    public static <T> ApiResponse<T> success(String message){
        return new ApiResponse<>(message, null);
    }
    public static <T> ApiResponse<T> fail(String message, T data){
        return new ApiResponse<>(message, data);
    }
    public static <T> ApiResponse<T> fail(String message){return new ApiResponse<>(message, null);}
}
