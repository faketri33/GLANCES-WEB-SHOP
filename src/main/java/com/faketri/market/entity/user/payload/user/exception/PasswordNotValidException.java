package com.faketri.market.entity.user.payload.user.exception;

/**
 * The type Password not valid exception.
 *
 * @author Dmitriy Faketri
 */
public class PasswordNotValidException extends RuntimeException {

    /**
     * Instantiates a new Password not valid exception.
     *
     * @param message the message
     */
    public PasswordNotValidException(String message) {
        super(message);
    }

}
