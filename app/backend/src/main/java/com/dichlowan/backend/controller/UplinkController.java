package com.dichlowan.backend.controller;

import com.dichlowan.backend.model.UplinkModel;
import com.dichlowan.backend.repository.UplinkRepository;
import com.dichlowan.backend.service.EmailService;
import com.dichlowan.backend.service.ScrapperService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;

import com.dichlowan.backend.service.NetworkService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping(value = "/v1/uplink")
@CrossOrigin(origins = "http://localhost:3000")
public class UplinkController {
    private static final Logger logger = LoggerFactory.getLogger(UplinkController.class);

    @Autowired
    NetworkService networkService;

    @Autowired
    UplinkRepository uplinkRepository;

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
    public List<UplinkModel> getAllUplink(){
        return uplinkRepository.findAll();
    }

    @GetMapping("/date")
    @Operation(
            summary = "Get uplink between 2 date",
            description="get uplink between 2 dates : after the first one and before the second one -> pattern('yyyy-MM-dd'T'HH:mm:ssZ')")
    @ApiResponse(
            responseCode = "200")
    @ApiResponse(
            responseCode = "500",
            description = "Internal Error",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = String.class)
            ))
    public List<UplinkModel> getFromDate(
            @RequestParam(value="a", required = true)
            @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
                    Date after,
            @RequestParam(value="b", required = true)
            @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
                    Date before
    ) {

        return uplinkRepository.findBetween(after, before);
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