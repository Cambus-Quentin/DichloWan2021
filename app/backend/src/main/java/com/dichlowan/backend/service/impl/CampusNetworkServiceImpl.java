package com.dichlowan.backend.service.impl;

import com.dichlowan.backend.model.UplinkModel;
import org.springframework.stereotype.Service;

import com.dichlowan.backend.service.NetworkService;
import com.dichlowan.backend.exception.NotImplementedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class CampusNetworkServiceImpl implements NetworkService {

    private static final Logger logger = LoggerFactory.getLogger(CampusNetworkServiceImpl.class);

    public List<UplinkModel> getAllUplink() {
        throw new NotImplementedException("The Campus IoT network is not yet supported");
    }

    public String getUplinkFromDevice(String deviceId) {
        throw new NotImplementedException("The Campus IoT network is not yet supported");
    }
}
