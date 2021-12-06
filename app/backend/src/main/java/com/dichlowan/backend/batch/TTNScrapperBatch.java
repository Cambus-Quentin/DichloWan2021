package com.dichlowan.backend.batch;

import com.dichlowan.backend.model.UplinkModel;
import com.dichlowan.backend.repository.UplinkRepository;
import com.dichlowan.backend.service.NetworkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TTNScrapperBatch {
    private static final Logger logger = LoggerFactory.getLogger(TTNScrapperBatch.class);

    @Autowired
    NetworkService networkService;

    @Autowired
    UplinkRepository uplinkRepository;

    public void scrap() {
        List<UplinkModel> uplinks = networkService.getAllUplink();

        if (uplinks.size() == 0){
            logger.info("No newest data to scrape");

            return;
        }

        uplinkRepository.saveAll(uplinks);

        logger.info("New data have been scraped");
    }
}
