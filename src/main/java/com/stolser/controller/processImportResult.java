package com.stolser.controller;

public class ProcessImportResult {
    public static final String RESULT_SUCCESS = "formPage.import.success";
    public static final String RESULT_ERROR = "formPage.import.error";

    private boolean success;
    private String message;
    private int savedVideos;

    public ProcessImportResult(boolean success, String message, int savedVideos) {
        this.success = success;
        this.message = message;
        this.savedVideos = savedVideos;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

        if (success != result.success) {
            return false;
        }
        if (savedVideos != result.savedVideos) {
            return false;
        }
        return message != null ? message.equals(result.message) : result.message == null;

    }

    @Override
    public int hashCode() {
        int result = (success ? 1 : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + savedVideos;
        return result;
    }
}
