package com.dichlowan.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class UplinkModel {
    @Id
    @JsonIgnore
    public String id;

    public String networkOrigin;

    public String deviceId;

    public Date receivedAt;

    public int value;

    public UplinkModel(){}

    public UplinkModel(String networkOrigin, String deviceId, Date receiveAt, int value) {
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

    public Date getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(Date receivedAt) {
        this.receivedAt = receivedAt;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
