package br.com.challenge.shoppingcart.dto.error;

public class ErrorValidationDTO {
    private String message;
    private String exception;

    public ErrorValidationDTO(String message, String exception) {
        this.message = message;
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
