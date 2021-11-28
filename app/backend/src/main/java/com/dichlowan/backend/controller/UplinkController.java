package com.dichlowan.backend.controller;

import com.dichlowan.backend.dto.UplinkDTO;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;

import com.dichlowan.backend.service.NetworkService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/v1/uplink")
public class UplinkController {
    private static final Logger logger = LoggerFactory.getLogger(UplinkController.class);

    @Autowired
    NetworkService networkService;

    @GetMapping
    public ArrayList<UplinkDTO> getAllUplink(){
        ArrayList<UplinkDTO> res = networkService.getAllUplink();
        return res;
    }

    @GetMapping(value="/device")
    public String getUplinkFromDevice(
        @RequestParam(value="id", required = true) String deviceId
    ){
        String res = networkService.getUplinkFromDevice(deviceId);
        return res;
    }
}