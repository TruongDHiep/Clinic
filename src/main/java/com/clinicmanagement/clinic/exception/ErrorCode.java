package com.clinicmanagement.clinic.exception;

public enum ErrorCode {

    INVALID_KEY(1003,"Invalid key"),
    UNCATEGORIZED_EXCEPTIO(5050,"Uncategorized Error"),
    USER_EXISTED(1001,"User existed"),
    USERNAME_INVALID(1002,"Username phải có ít nhất 8 kí tự"),
    PASS_INVALID(1003,"Password phải ít nhất 8 kí tự"),
    USER_NOT_EXISTED(1004,"User not existed"),
    UNAUTHENICATED(1005,"Unauthenicated");

    ErrorCode(int code,String message){
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
