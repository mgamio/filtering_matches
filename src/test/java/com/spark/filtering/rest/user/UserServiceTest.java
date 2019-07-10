package com.spark.filtering.rest.user;

import com.spark.filtering.model.Matches;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    //test conditional logic based on filters

    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserServiceImpl(matches());
        }

        @Bean
        public Matches matches() {
            return new Matches();
        }
    }

    @MockBean
    private Matches matches;

    @Autowired
    private UserService userService;

    @Test
    public void test_Matches_isEmpty() throws Exception {
        assertEquals(null, userService.filtering(null)) ;

    }

}
