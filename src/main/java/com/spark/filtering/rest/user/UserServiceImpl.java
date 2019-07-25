package com.spark.filtering.rest.user;

import com.spark.filtering.model.City;
import com.spark.filtering.model.Matches;
import com.spark.filtering.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private Matches matches;

    //we assume London as the reference
    private final double lat1 = 51.509865d;
    private final double lon1 = -0.118092d;

    public UserServiceImpl (Matches matches) {
        this.matches = matches;
    }

    @Override
    public List<User> all() throws Exception {
        return matches.getMatches();
    }

    @Override
    public List<User> filtering(FilterRequest filter) throws Exception {

        if (filter == null)
            return matches.getMatches();

        ArrayList<User> newListOfMatches = new ArrayList<>();

        for (User user : matches.getMatches()) {
            if (isCandidate(user, filter))
                newListOfMatches.add(user);
        }

        return newListOfMatches;
    }

    public boolean isCandidate(User user, FilterRequest filter) {

        if (filter.getHasPhoto() != null) {
            if (filter.getHasPhoto() == true && user.getMain_photo() == null)
                return false;
            if (filter.getHasPhoto() == false && user.getMain_photo() != null)
                return  false;
        }

        if (filter.getInContact() != null) {
            if (filter.getInContact() == true && user.getContacts_exchanged() <= 0)
                return false;
            if (filter.getInContact() == false && user.getContacts_exchanged() > 0)
                return false;
        }

        if (filter.getFavourite() != null) {
            if (filter.getFavourite() == true && user.getFavourite() == false)
                return false;
            if (filter.getFavourite() == false && user.getFavourite() == true)
                return false;
        }

        if (filter.getCompatibilityScore() > 0 && user.getCompatibility_score() < filter.getCompatibilityScore())
            return false;

        if (filter.getAge() != null && user.getAge() < filter.getAge().intValue())
            return false;

        if (filter.getHeight() != null && user.getHeight_in_cm() < filter.getHeight().intValue())
            return false;

        if (filter.getDistanceInKm() != null && distance(user.getCity()) >= filter.getDistanceInKm())
            return false;

        return true;
    }

    public double distance(City city) {

        //Ref: https://www.geodatasource.com/developers/java

        if ((lat1 == city.getLat()) && (lon1 == city.getLon())) {
            return 0;
        }
        else {
            double theta = lon1 - city.getLon();
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(city.getLat()))
                    + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(city.getLat())) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            dist = dist * 1.609344; //to Km

            return (dist);
        }
    }

}
