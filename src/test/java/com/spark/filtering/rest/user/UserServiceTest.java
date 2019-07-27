package com.spark.filtering.rest.user;

import com.spark.filtering.model.City;
import com.spark.filtering.model.Matches;
import com.spark.filtering.model.User;
import com.spark.filtering.rest.geo.GeoService;
import com.spark.filtering.rest.geo.GeoServiceImpl;
import org.junit.Before;
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

    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        public GeoService geoService() {
            return new GeoServiceImpl();
        }

        @Bean
        public UserService userService() {
            return new UserServiceImpl(matches(), geoService());
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

    @Autowired
    private GeoService geoService;

    private City city1;
    private City city2;

    @Before
    public void setUp() {
        city1 = new City();
        city1.setName("Leeds");
        city1.setLat(53.801277d);
        city1.setLon(-1.548567d);

        city2 = new City();
        city2.setName("Solihull");
        city2.setLat(52.412811d);
        city2.setLon(-1.778197d);
    }

    @Test
    public void test_Matches_hasPhoto() throws Exception {
        User user = new User();
        user.setMain_photo("http://thecatapi.com/api/images/get?format=src&type=gif");
        user.setCity(city1);
        ArrayList<User> listOfUsers = new ArrayList<>();
        listOfUsers.add(user);
        when(matches.getMatches()).thenReturn(listOfUsers);
        FilterRequest request = new FilterRequest();
        request.setHasPhoto(true);
        assertEquals(1, userService.find(request).size()) ;

    }

    @Test
    public void test_Matches_inContact() throws Exception {
        User user = new User();
        user.setContacts_exchanged(2);
        user.setCity(city1);
        ArrayList<User> listOfUsers = new ArrayList<>();
        listOfUsers.add(user);
        when(matches.getMatches()).thenReturn(listOfUsers);
        FilterRequest request = new FilterRequest();
        request.setInContact(true);
        assertEquals(1, userService.find(request).size()) ;
    }

    @Test
    public void test_Matches_favourite_one_found() throws Exception {
        ArrayList<User> listOfUsers = new ArrayList<>();
        User user1 = new User();
        user1.setCity(city1);
        user1.setFavourite(true);
        listOfUsers.add(user1);
        User user2 = new User();
        user2.setFavourite(false);
        user2.setCity(city1);
        listOfUsers.add(user2);
        when(matches.getMatches()).thenReturn(listOfUsers);
        FilterRequest request = new FilterRequest();
        request.setFavourite(true);
        assertEquals(1, userService.find(request).size()) ;
    }

    @Test
    public void test_Matches_compatibility_score() throws Exception {
        ArrayList<User> listOfUsers = new ArrayList<>();
        User user1 = new User();
        user1.setCompatibility_score(0.76d);
        user1.setCity(city1);
        listOfUsers.add(user1);
        User user2 = new User();
        user2.setCompatibility_score(0.95d);
        user2.setCity(city2);
        listOfUsers.add(user2);
        when(matches.getMatches()).thenReturn(listOfUsers);
        FilterRequest request = new FilterRequest();
        request.setCompatibilityScore(95d);
        assertEquals(1, userService.find(request).size()) ;
    }

    @Test
    public void test_Matches_age() throws Exception {
        ArrayList<User> listOfUsers = new ArrayList<>();
        User user1 = new User();
        user1.setAge(45);
        user1.setCity(city1);
        listOfUsers.add(user1);
        User user2 = new User();
        user2.setAge(44);
        user2.setCity(city2);
        listOfUsers.add(user2);
        when(matches.getMatches()).thenReturn(listOfUsers);
        FilterRequest request = new FilterRequest();
        request.setAge(45);
        assertEquals(1, userService.find(request).size()) ;
    }

    @Test
    public void test_Matches_height() throws Exception {
        ArrayList<User> listOfUsers = new ArrayList<>();
        User user1 = new User();
        user1.setHeight_in_cm(135);
        user1.setCity(city1);
        listOfUsers.add(user1);
        User user2 = new User();
        user2.setHeight_in_cm(200);
        user2.setCity(city1);
        listOfUsers.add(user2);
        User user3 = new User();
        user3.setHeight_in_cm(210);
        user3.setCity(city2);
        listOfUsers.add(user3);
        when(matches.getMatches()).thenReturn(listOfUsers);
        FilterRequest request = new FilterRequest();
        request.setHeight(185);
        assertEquals(2, userService.find(request).size()) ;
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
        request.setDistanceInKm(272);
        assertEquals(1, userService.find(request).size()) ;
    }


}
