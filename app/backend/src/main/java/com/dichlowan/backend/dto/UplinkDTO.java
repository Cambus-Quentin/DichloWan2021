package com.dichlowan.backend.dto;

import java.time.LocalDateTime;

public class UplinkDTO {
    private String networkOrigin;

    private String deviceId;

    private LocalDateTime receivedAt;

    private int value;

    public UplinkDTO(String networkOrigin, String deviceId, LocalDateTime receiveAt, int value) {
        this.setNetworkOrigin(networkOrigin);
        this.setDeviceId(deviceId);
        this.setReceivedAt(receiveAt);
        this.setValue(value);
    }

    public String getNetworkOrigin() {
        return networkOrigin;
    }

    public void setNetworkOrigin(String networkOrigin) {
        this.networkOrigin = networkOrigin;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
