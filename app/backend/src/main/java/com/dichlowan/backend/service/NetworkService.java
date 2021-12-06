package com.dichlowan.backend.service;

import com.dichlowan.backend.model.UplinkModel;

import java.util.ArrayList;
import java.util.List;

public interface NetworkService {
    abstract List<UplinkModel> getAllUplink();

    abstract String getUplinkFromDevice(String deviceID);
}
