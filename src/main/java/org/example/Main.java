package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("The Development Company application is starting...");

        try {
            logger.debug("Attempting to connect to the Hikari Connection Pool");
        } catch (Exception e) {
            logger.error("Database connection failed!", e);
        }
    }
}