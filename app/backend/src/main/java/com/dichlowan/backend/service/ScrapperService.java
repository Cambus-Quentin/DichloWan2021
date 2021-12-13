package com.dichlowan.backend.service;

import com.dichlowan.backend.model.UplinkModel;
import com.dichlowan.backend.repository.UplinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ScrapperService {
    private static final Logger logger = LoggerFactory.getLogger(ScrapperService.class);

    @Autowired
    NetworkService networkService;

    @Autowired
    UplinkRepository uplinkRepository;

    public void scrap() {
        Date after = uplinkRepository.findLastDate();

        // after is null if no data into DB
        if (after != null){
            // not rescrape the data at the given date
            // 1000 ms
            after.setTime(after.getTime()+1000);
        }

        List<UplinkModel> uplinks = networkService.getAllUplink(after);

        if (uplinks.size() == 0){
            if (after != null) {
                logger.info("No newest data to scrape - after " + after);
            } else {
                logger.info("No newest data to scrape");
            }

            return;
        }

        uplinkRepository.saveAll(uplinks);

        logger.info("New data have been scraped - after " + after);
    }
}
