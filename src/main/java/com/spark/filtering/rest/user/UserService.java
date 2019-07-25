package com.spark.filtering.rest.user;

import com.spark.filtering.model.User;

import java.util.List;

public interface UserService {

    List<User> filtering(FilterRequest filter) throws Exception;

    List<User> all() throws Exception;

}
