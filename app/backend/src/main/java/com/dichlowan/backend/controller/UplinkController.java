package com.dichlowan.backend.controller;

import com.dichlowan.backend.dto.UplinkDTO;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Operation(
            summary = "Get all uplink",
            description="get all uplink from all devices (endpoint)")
    @ApiResponse(
            responseCode = "200")
    @ApiResponse(
            responseCode = "500",
            description = "Internal Error",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = String.class)
            ))
    public ArrayList<UplinkDTO> getAllUplink(){
        ArrayList<UplinkDTO> res = networkService.getAllUplink();
        return res;
    }

    @GetMapping(value="/device")
    @Hidden // NotImplementedException
    @Operation(
            summary = "Get uplink from device",
            description="Get uplink from given devices (endpoint)")
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = String.class)
            ))
    public String getUplinkFromDevice(
            @Parameter(description = "The device id", required = true)
            @RequestParam(value="id", required = true)
                    String deviceId
    ){
        String res = networkService.getUplinkFromDevice(deviceId);
        return res;
    }
}