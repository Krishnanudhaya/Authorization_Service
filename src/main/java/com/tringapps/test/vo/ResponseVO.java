package com.tringapps.test.vo;

public class ResponseVO {

    private boolean isSuccess;
    private String statusMessage;
    private Error errorMessage;
    private Object data;

    public ResponseVO() {
        this.isSuccess = false;
    }

    public ResponseVO(boolean isSuccess, String statusMessage, Error errorMessage, Object data) {
        this.isSuccess = isSuccess;
        this.statusMessage = statusMessage;
        this.errorMessage = errorMessage;
        this.data = data;
    }


    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Error getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Error errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
