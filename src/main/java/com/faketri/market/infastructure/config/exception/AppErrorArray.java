package com.faketri.market.infastructure.config.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppErrorArray {

    private int statusCode;
    private Map message;

}
