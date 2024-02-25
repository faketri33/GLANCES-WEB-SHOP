package com.faketri.market.infastructure.config.exception;

/**
 * The type Jwt valid exception.
 *
 * @author Dmitriy Faketri
 */
public class JwtValidException extends RuntimeException {

    /**
     * Instantiates a new Jwt valid exception.
     *
     * @param message the message
     */
    public JwtValidException(String message) {
        super(message);
    }

}
