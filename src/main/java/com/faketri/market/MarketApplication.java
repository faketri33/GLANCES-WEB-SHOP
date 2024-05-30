package com.faketri.market;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The type Market application.
 *
 * @author Dmitriy Faketri
 */
@SpringBootApplication
@EnableScheduling
public class MarketApplication {

    static final Logger log = LoggerFactory.getLogger(MarketApplication.class);

    public static void main(String[] args) {
        log.info("Before Starting application");
        SpringApplication.run(MarketApplication.class, args);
        log.debug("Starting my application in debug with {} args", args.length);
        log.info("Starting my application with {} args.", args.length);
    }
}

