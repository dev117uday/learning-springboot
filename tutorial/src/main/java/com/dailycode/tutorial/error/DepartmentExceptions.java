package com.dailycode.tutorial.error;

public class DepartmentExceptions extends Exception {

    private Integer statusCode = 0;
    private String message;

    public DepartmentExceptions() {
        super();
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public DepartmentExceptions(Integer statusCode, String message) {
        this.statusCode  = statusCode;
        this.message = message;
    }

}
