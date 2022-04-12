package com.example.examination.exception;

public class EAException extends Exception{
    private final ErrorMessage errorMessage;

    public EAException(ErrorMessage pErrorMessage) {
        super();
        errorMessage = pErrorMessage;
    }
    public EAException(String message){
        super(message);
        errorMessage = ErrorMessage.BAD_REQUEST;
    }

    public EAException() {
        super();
        errorMessage = null;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}
