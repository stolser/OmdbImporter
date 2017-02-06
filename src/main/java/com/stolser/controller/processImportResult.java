package com.stolser.controller;

public class ProcessImportResult {
    public static final String RESULT_SUCCESS_MSG = "formPage.import.success";
    public static final String RESULT_ERROR_MSG = "formPage.import.error";
    public static final ProcessImportResult ERROR =
            new ProcessImportResult(false, ProcessImportResult.RESULT_ERROR_MSG, 0);

    private boolean isSuccess;
    private String message;
    private int savedVideos;

    public ProcessImportResult(boolean isSuccess, String message, int savedVideos) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.savedVideos = savedVideos;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        this.isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSavedVideos() {
        return savedVideos;
    }

    public void setSavedVideos(int savedVideos) {
        this.savedVideos = savedVideos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProcessImportResult result = (ProcessImportResult) o;

        if (isSuccess != result.isSuccess) {
            return false;
        }
        if (savedVideos != result.savedVideos) {
            return false;
        }
        return message != null ? message.equals(result.message) : result.message == null;

    }

    @Override
    public int hashCode() {
        int result = (isSuccess ? 1 : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + savedVideos;
        return result;
    }
}
