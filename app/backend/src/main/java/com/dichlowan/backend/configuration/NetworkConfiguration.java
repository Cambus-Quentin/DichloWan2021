package com.dichlowan.backend.configuration;

import com.dichlowan.backend.exception.BadPropertyException;
import com.dichlowan.backend.service.NetworkService;
import com.dichlowan.backend.service.impl.CampusNetworkServiceImpl;
import com.dichlowan.backend.service.impl.TTNetworkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class NetworkConfiguration {
    @Autowired
    private Environment env;

    @Bean
    public NetworkService networkService() throws BadPropertyException {
        if (env.getRequiredProperty("network.choice").equals("ttn")){
            return new TTNetworkServiceImpl();
        } else if (env.getRequiredProperty("network.choice").equals("campusIoT")) {
            return new CampusNetworkServiceImpl();
        } else {
            throw new BadPropertyException("network.choice", "The value must be \"ttn\" ou \"campusIoT\"");
        }
    }
}
