package br.com.challenge.shoppingcart.dto.error;

public class FieldValidationDTO {
    private String field;
    private String message;
    private String exception;

    public FieldValidationDTO(String field, String message, String exception) {
        this.field = field;
        this.message = message;
        this.exception = exception;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
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
