

package com.shamildev.retro.data.entity.mapper.error;


import java.io.IOException;


public class MappingError extends IOException {


    private final String className;
    private int responseCode;

    private String message;

    public static MappingError getError(String errorMsg, Class clazz) {


        return new MappingError(1, errorMsg,clazz.getClass().getSimpleName());


    }


    public MappingError(int responseCode, String message, String simpleName) {
        this.responseCode = responseCode;
        this.message = message;
        this.className = simpleName;

    }


    public int getResponseCode() {
        return responseCode;
    }

    @Override
    public String getMessage() {
        return message;
    }


}