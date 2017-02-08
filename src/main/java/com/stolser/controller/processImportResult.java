package com.stolser.controller;

public class ProcessImportResult {
    public static final String START_SUCCESS_MSG = "formPage.import.successfulStart";
    public static final String GENERAL_ERROR_MSG = "formPage.import.generalError";
    public static final String VALIDATION_ERROR_MSG = "formPage.import.validationError";
    public static final ProcessImportResult START_SUCCESS =
            new ProcessImportResult(false, ProcessImportResult.START_SUCCESS_MSG);
    public static final ProcessImportResult GENERAL_ERROR =
            new ProcessImportResult(false, ProcessImportResult.GENERAL_ERROR_MSG);
    public static final ProcessImportResult VALIDATION_ERROR =
            new ProcessImportResult(false, ProcessImportResult.VALIDATION_ERROR_MSG);

    private boolean isSuccess;
    private String message;

    public ProcessImportResult(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
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
        return message != null ? message.equals(result.message) : result.message == null;

    }

    @Override
    public int hashCode() {
        int result = (isSuccess ? 1 : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
