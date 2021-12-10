package com.dichlowan.backend.tasks;

import com.dichlowan.backend.service.ScrapperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;

@Component
public class ScrapperTask {
    private static final Logger logger = LoggerFactory.getLogger(ScrapperTask.class);

    @Autowired
    ScrapperService scrapperService;

    // @Scheduled(initialDelay = 10000, fixedRate = 10000)
    @Scheduled(initialDelay = 60000, fixedRate = 600000)
    public void scapperTask() {
        scrapperService.scrap();
        logger.info("Scrapper task performed");
    }

    /*
    @Scheduled(fixedRate = 10000)
    public void performTask() {
        logger.debug("Task 1");
    }
    */
    /*
    @Scheduled(initialDelay = 10000, fixedRate = 10000)
    public void performDelayedTask() {
        logger.debug("Task 2");
    }
    */

    // @Scheduled(cron = "*/5 * * * * *")
    /*
    public void performTaskUsingCron() {
        logger.debug("Task 3");
    }
    */
}