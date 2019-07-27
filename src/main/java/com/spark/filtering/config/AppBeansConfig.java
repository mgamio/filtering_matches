package com.spark.filtering.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spark.filtering.model.Matches;
import com.spark.filtering.rest.geo.GeoService;
import com.spark.filtering.rest.geo.GeoServiceImpl;
import com.spark.filtering.rest.user.UserService;
import com.spark.filtering.rest.user.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class AppBeansConfig {

    @Bean
    public UserService userService() throws  Exception {
        return new UserServiceImpl(matches(), geoService());
    }

    @Bean
    public Matches matches() throws  Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        //read json file and convert to matches object
        Matches matches = objectMapper.readValue(new File("matches.json"), Matches.class);

        return matches;
    }

    @Bean
    public GeoService geoService() {
        return new GeoServiceImpl();
    }

}
