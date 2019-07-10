package com.spark.filtering.rest.user;

import com.spark.filtering.model.Matches;

public class UserServiceImpl implements UserService {

    private Matches matches;

    public UserServiceImpl (Matches matches) {
        this.matches = matches;
    }

    @Override
    public Matches filtering(FilterRequest filter) throws Exception {
        return null;
    }
}
