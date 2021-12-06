package com.dichlowan.backend.service;

import com.dichlowan.backend.dto.UplinkDTO;

import java.util.ArrayList;

public interface NetworkService {
    abstract ArrayList<UplinkDTO> getAllUplink();

    abstract String getUplinkFromDevice(String deviceID);
}
