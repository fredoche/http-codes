package devoxx.serverless.model;

import lombok.Data;

@Data
public class ErrorResponse {

    public static ErrorResponse fromMessage(String message) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(message);
        return error;
    }

    public static ErrorResponse fromException(Exception e) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(e.getMessage());
        error.setCode(e.getClass().getCanonicalName());
        return error;
    }

    private String message;
    private String code;

}
