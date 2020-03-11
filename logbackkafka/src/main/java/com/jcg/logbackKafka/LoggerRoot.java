package com.jcg.logbackKafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerRoot {

    private static final Logger logger = LoggerFactory.getLogger(LoggerRoot.class.getSimpleName());

    public static void main(String... args) throws InterruptedException {
        int counter = 0;
        while (counter < 10) {
            logger.info("Counter:" + counter);
            counter++;
        }
        Thread.sleep(1000);
        logger.error("Completed");
    }
}
