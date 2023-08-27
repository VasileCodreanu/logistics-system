package com.cedacri.logisticssystem.exceptions.customExceptions;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String message, Object id){
       super(message + id);
    }

}
