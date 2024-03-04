package com.faketri.market.entity.exception;

/**
 * The type Resource not found exception.
 *
 * @author Dmitriy Faketri
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Instantiates a new Resource not found exception.
     *
     * @param message the message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

}
