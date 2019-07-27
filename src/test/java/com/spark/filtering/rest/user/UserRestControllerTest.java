package com.spark.filtering.rest.user;

import com.spark.filtering.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@WebMvcTest(UserRestController.class)
public class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void getStatusOK() throws Exception {
        FilterRequest filterRequest = new FilterRequest();
        when(userService.find(filterRequest)).thenReturn(new ArrayList<User>());

        String json = mapper.writeValueAsString(filterRequest);
        this.mockMvc.perform(post("/users/filter").contentType(MediaType.APPLICATION_JSON)
                .content(json)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getBadRequestByWrongAge() throws Exception {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setAge(17);
        when(userService.find(filterRequest)).thenReturn(new ArrayList<User>());

        String json = mapper.writeValueAsString(filterRequest);
        this.mockMvc.perform(post("/users/filter").contentType(MediaType.APPLICATION_JSON)
                .content(json)).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getBadRequestByWrongScore() throws Exception {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setCompatibilityScore(101);
        when(userService.find(filterRequest)).thenReturn(new ArrayList<User>());

        String json = mapper.writeValueAsString(filterRequest);
        this.mockMvc.perform(post("/users/filter").contentType(MediaType.APPLICATION_JSON)
                .content(json)).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getBadRequestByWrongHeight() throws Exception {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setHeight(130);
        when(userService.find(filterRequest)).thenReturn(new ArrayList<User>());

        String json = mapper.writeValueAsString(filterRequest);
        this.mockMvc.perform(post("/users/filter").contentType(MediaType.APPLICATION_JSON)
                .content(json)).andDo(print())
                .andExpect(status().isBadRequest());
    }
}
