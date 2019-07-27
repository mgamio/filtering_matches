package com.spark.filtering.rest.user;

import com.spark.filtering.model.Matches;
import com.spark.filtering.model.User;
import com.spark.filtering.rest.geo.GeoService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private Matches matches;

    private GeoService geoService;

    //we assume London as the reference
    private final double latitude1 = 51.509865d;
    private final double longitude1 = -0.118092d;

    public UserServiceImpl (Matches matches, GeoService geoService) {

        this.matches = matches;
        this.geoService = geoService;
    }

    @Override
    public List<User> all() throws Exception {
        return matches.getMatches();
    }

    @Override
    public List<User> find(FilterRequest filter) throws Exception {

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

        if (filter.getCompatibilityScore() > 0 && user.getCompatibility_score() < filter.getCompatibilityScore()/100)
            return false;

        if (filter.getAge() != null && user.getAge() < filter.getAge().intValue())
            return false;

        if (filter.getHeight() != null && user.getHeight_in_cm() < filter.getHeight().intValue())
            return false;

        if (filter.getDistanceInKm() != null) {
            double distance = geoService.calculateDistance(latitude1, longitude1, user.getCity().getLat(), user.getCity().getLon());
            if (distance >= filter.getDistanceInKm())
                return false;
        }

        return true;
    }

}
