package com.spark.filtering.rest.geo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class GeoServiceTest {

    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        public GeoService geoService() {
            return new GeoServiceImpl();
        }
    }

    @Autowired
    private GeoService geoService;

    //Ref: https://www.geodatasource.com/distance-calculator

    @Test
    public void test_distanceLondonLeadsIsTrue() throws Exception {
        double distance = geoService.calculateDistance(51.509865d, -0.118092d, 53.801277d, -1.548567d);
        //272.4232649949114
        assertTrue(distance > 272);
    }

    @Test
    public void test_distanceLondonSolihullIsFalse() throws Exception {
        double distance = geoService.calculateDistance(51.509865d, -0.118092d, 52.412811d, -1.778197d);
        //151.70574579725914
        assertFalse(distance < 151);
    }

}
