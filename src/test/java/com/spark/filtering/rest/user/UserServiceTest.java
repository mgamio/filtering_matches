package com.spark.filtering.rest.user;

import com.spark.filtering.model.City;
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
        assertEquals(1, userService.filtering(request).size()) ;

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
        assertEquals(1, userService.filtering(request).size()) ;
    }

    @Test
    public void test_Matches_favourite_one_found() throws Exception {
        ArrayList<User> listOfUsers = new ArrayList<>();
        User user1 = new User();
        user1.setFavourite(true);
        listOfUsers.add(user1);
        User user2 = new User();
        user2.setFavourite(false);
        listOfUsers.add(user2);
        when(matches.getMatches()).thenReturn(listOfUsers);
        FilterRequest request = new FilterRequest();
        request.setFavourite(true);
        assertEquals(1, userService.filtering(request).size()) ;
    }

    @Test
    public void test_Matches_compatibility_score() throws Exception {
        ArrayList<User> listOfUsers = new ArrayList<>();
        User user1 = new User();
        user1.setCompatibility_score(0.76d);
        listOfUsers.add(user1);
        User user2 = new User();
        user2.setCompatibility_score(0.95d);
        listOfUsers.add(user2);
        when(matches.getMatches()).thenReturn(listOfUsers);
        FilterRequest request = new FilterRequest();
        request.setCompatibilityScore(0.95d);
        assertEquals(1, userService.filtering(request).size()) ;
    }

    @Test
    public void test_Matches_age() throws Exception {
        ArrayList<User> listOfUsers = new ArrayList<>();
        User user1 = new User();
        user1.setAge(45);
        listOfUsers.add(user1);
        User user2 = new User();
        user2.setAge(44);
        listOfUsers.add(user2);
        when(matches.getMatches()).thenReturn(listOfUsers);
        FilterRequest request = new FilterRequest();
        request.setAge(45);
        assertEquals(1, userService.filtering(request).size()) ;
    }

    @Test
    public void test_Matches_height() throws Exception {
        ArrayList<User> listOfUsers = new ArrayList<>();
        User user1 = new User();
        user1.setHeight_in_cm(135);
        listOfUsers.add(user1);
        User user2 = new User();
        user2.setHeight_in_cm(200);
        listOfUsers.add(user2);
        User user3 = new User();
        user3.setHeight_in_cm(210);
        listOfUsers.add(user3);
        when(matches.getMatches()).thenReturn(listOfUsers);
        FilterRequest request = new FilterRequest();
        request.setHeight(185);
        assertEquals(2, userService.filtering(request).size()) ;
    }

    @Test
    public void test_Matches_distanceInKm() throws Exception {
        ArrayList<User> listOfUsers = new ArrayList<>();
        User user1 = new User();
        City city1 = new City();
        city1.setName("Leeds");
        city1.setLat(53.801277d);
        city1.setLon(-1.548567d);
        user1.setCity(city1);
        listOfUsers.add(user1);

        User user2 = new User();
        City city2 = new City();
        city2.setName("Solihull");
        city2.setLat(52.412811d);
        city2.setLon(-1.778197d);
        user2.setCity(city2);
        listOfUsers.add(user2);

        when(matches.getMatches()).thenReturn(listOfUsers);
        FilterRequest request = new FilterRequest();
        request.setDistanceInKm(210);
        assertEquals(1, userService.filtering(request).size()) ;
    }


}
