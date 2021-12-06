package com.dichlowan.backend.controller;

import com.dichlowan.backend.exception.NotImplementedException;
import com.dichlowan.backend.service.NetworkService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/network")
public class NetworkController {

    @Autowired
    NetworkService networkService;

    @GetMapping("/devices")
    @Hidden // NotImplementedException
    @Operation(summary = "Get devices", description="Get devices list of the current network")
    public void getDevices() {
        throw new NotImplementedException("Not yet implemented");
    }
}
