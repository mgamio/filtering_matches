package com.spark.filtering.rest.user;

import com.spark.filtering.model.Matches;

public interface UserService {

    public Matches filtering(FilterRequest filter) throws Exception;

}
