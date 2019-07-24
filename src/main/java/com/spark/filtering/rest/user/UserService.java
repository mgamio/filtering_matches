package com.spark.filtering.rest.user;

import com.spark.filtering.model.Matches;
import com.spark.filtering.model.User;

import java.util.List;

public interface UserService {

    public List<User> filtering(FilterRequest filter) throws Exception;

    public List<User> all() throws Exception;

}
