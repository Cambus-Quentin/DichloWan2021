package com.dichlowan.backend.service.impl;

import com.dichlowan.backend.exception.NotImplementedException;
import com.dichlowan.backend.model.UplinkModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.dichlowan.backend.service.NetworkService;
import com.dichlowan.backend.configuration.TTNProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class TTNetworkServiceImpl implements NetworkService {
    @Autowired
    TTNProperties ttnProperties;

    private String TTN_API;
    private String BEAR_TOKEN;
    private String APP_ID;

    private static final Logger logger = LoggerFactory.getLogger(TTNetworkServiceImpl.class);

    @PostConstruct
    public void postConstruct() {
        TTN_API = ttnProperties.getApi().getUrl();
        BEAR_TOKEN = ttnProperties.getApp().getAuth().getBearerToken();
        APP_ID = ttnProperties.getApp().getId();
    }

    private int fromByteArray(byte[] bytes) {
        return ((bytes[0] & 0xFF) << 24) |
                ((bytes[1] & 0xFF) << 16) |
                ((bytes[2] & 0xFF) << 8 ) |
                ((bytes[3] & 0xFF) << 0 );
    }

    private List<UplinkModel> parser(String response) {
        ArrayList<UplinkModel> uplinks = new ArrayList<>();

        if (response == null)
            return uplinks;

        response = "[" + response.replace("\n{", ",\n{") + "]";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode[] data = objectMapper.readValue(response, JsonNode[].class);

            for (JsonNode node : data){
                String devideId = node.get("result").get("end_device_ids").get("device_id").asText();
                String receivedAt = node.get("result").get("received_at").asText();

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                Date date = dateFormat.parse(receivedAt);

                JsonNode bytesNode = node.get("result").get("uplink_message").get("decoded_payload").get("bytes");
                byte[] bytes = new byte[4];
                for (int i = 0; i < 4; i++) {
                    int b = bytesNode.get(i).asInt();
                    bytes[3-i] = (new Integer(b)).byteValue();
                }

                int value = this.fromByteArray(bytes);

                uplinks.add(new UplinkModel(ttnProperties.NETWORK_NAME, devideId, date, value));
            }
        }catch(IOException | ParseException e){
            logger.error("error parsing");
        }

        return uplinks;
    }

    public List<UplinkModel> getAllUplink() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(BEAR_TOKEN);
        HttpEntity httpEntity = new HttpEntity(headers);

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("after=2021-11-25T00:00:00Z");
        // strBuilder.append("&limit=10");
        strBuilder.append("&field_mask=up.uplink_message.decoded_payload");

        String url = TTN_API + "as/applications/" + APP_ID + "/packages/storage/uplink_message" + "?" + strBuilder.toString();

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

        // TODO : check status code

        List<UplinkModel> uplinks = parser(responseEntity.getBody());

        return uplinks;
    }

    public String getUplinkFromDevice(String deviceId) {
        throw new NotImplementedException("Not yet implemented");
    }
}
