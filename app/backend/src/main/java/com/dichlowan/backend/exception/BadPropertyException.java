package com.dichlowan.backend.exception;

import org.springframework.beans.factory.BeanCreationException;

public class BadPropertyException extends BeanCreationException {
    public BadPropertyException(String property, String message) {
        super("\"" + property + "\" :  " + message);
    }
}
