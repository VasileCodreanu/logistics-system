package com.cedacri.logisticssystem.exceptions.customExceptions;

//extends RuntimeException
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message, Object id) {
        super(message + id);
    }
}
