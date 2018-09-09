package com.shamildev.retro.domain.error;




import java.io.IOException;



public class BaseError extends IOException {

    private static final int ERROR_CODE_401 = 401;
    private static final int ERROR_CODE_404 = 404;
    private static final int ERROR_CODE_422 = 422;

    private int responseCode;
    private int statusCode;
    private String message;
    private Boolean success;


    public static BaseError getTMDBError(int responseCode, String rawJson) {
         int statusCode = 0;
         String message = "";
         Boolean success = false;


        return new BaseError(responseCode,message);
    }


    public BaseError(int responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

    public BaseError(int responseCode, String message, int statusCode) {
        this.message = message;
        this.responseCode = responseCode;
        this.statusCode = statusCode;

    }


    public BaseError(int responseCode, String message, int statusCode, Boolean success) {
        this.message = message;
        this.responseCode = responseCode;
        this.statusCode = statusCode;
        this.success = success;
    }




    public int getResponseCode() {
        return responseCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Boolean getSuccess() {
        return success;
    }


}
