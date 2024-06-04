package com.faketri.market.entity.exception;

/**
 * The type Resource not found exception.
 *
 * @author Dmitriy Faketri
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
