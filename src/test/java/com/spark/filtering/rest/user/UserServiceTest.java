package com.spark.filtering.rest.user;

import com.spark.filtering.model.Matches;
import com.spark.filtering.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import static org.mockito.Mockito.when;
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
    public void test_Matches_hasPhoto() throws Exception {
        User user = new User();
        user.setMain_photo("http://thecatapi.com/api/images/get?format=src&type=gif");
        ArrayList<User> listOfUsers = new ArrayList<>();
        listOfUsers.add(user);
        when(matches.getMatches()).thenReturn(listOfUsers);
        FilterRequest request = new FilterRequest();
        request.setHasPhoto(true);
        assertEquals(1, userService.filtering(request).getMatches().size()) ;

    }

    @Test
    public void test_Matches_inContact() throws Exception {
        User user = new User();
        user.setContacts_exchanged(2);
        ArrayList<User> listOfUsers = new ArrayList<>();
        listOfUsers.add(user);
        when(matches.getMatches()).thenReturn(listOfUsers);
        FilterRequest request = new FilterRequest();
        request.setInContact(true);
        assertEquals(1, userService.filtering(request).getMatches().size()) ;
    }

}
