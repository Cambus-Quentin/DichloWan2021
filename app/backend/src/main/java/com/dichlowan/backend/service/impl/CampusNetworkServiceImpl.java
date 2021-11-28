package com.dichlowan.backend.service.impl;

import com.dichlowan.backend.dto.UplinkDTO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;


import com.dichlowan.backend.service.NetworkService;
import com.dichlowan.backend.exception.NotImplementedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

@Service
public class CampusNetworkServiceImpl implements NetworkService {

    private static final Logger logger = LoggerFactory.getLogger(CampusNetworkServiceImpl.class);

    public ArrayList<UplinkDTO> getAllUplink() {
        throw new NotImplementedException("The Campus IoT network is not yet supported");
    }

    public String getUplinkFromDevice(String deviceId) {
        throw new NotImplementedException("The Campus IoT network is not yet supported");
    }
}
