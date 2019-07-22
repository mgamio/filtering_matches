package com.spark.filtering.rest.user;

import com.spark.filtering.model.Matches;
import com.spark.filtering.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private Matches matches;

    public UserServiceImpl (Matches matches) {
        this.matches = matches;
    }

    @Override
    public Matches filtering(FilterRequest filter) throws Exception {

        Matches matchesResult = new Matches();
        ArrayList<User> newListOfMatches = new ArrayList<>();
        List<User> listOfMatches = matches.getMatches();

        boolean isCandidate = true;
        for (User user : listOfMatches) {
            if (filter.getHasPhoto() != null) {
                if (filter.getHasPhoto() == true && user.getMain_photo() == null)
                    isCandidate = false;
                if (filter.getHasPhoto() == false && user.getMain_photo() != null)
                    isCandidate = false;
            }

            if (filter.getInContact() != null) {
                if (filter.getInContact() == true && user.getContacts_exchanged() <= 0)
                    isCandidate = false;
                if (filter.getInContact() == false && user.getContacts_exchanged() > 0)
                    isCandidate = false;
            }

            if (isCandidate) {
                newListOfMatches.add(user);
            }
            isCandidate = true;
        }

        matchesResult.setMatches(newListOfMatches);

        return matchesResult;
    }
}
