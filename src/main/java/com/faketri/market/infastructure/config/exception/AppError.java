package com.faketri.market.infastructure.config.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type App error.
 *
 * @author Dmitriy Faketri
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppError {

    private int    statusCode;
    private String message;

}
