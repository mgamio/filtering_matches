package com.spark.filtering.rest.user;

import com.spark.filtering.model.City;
import com.spark.filtering.model.Matches;
import com.spark.filtering.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value="/filter")
    public ResponseEntity<?> filter(@RequestBody FilterRequest request) throws Exception {

        List<User> response = userService.filtering(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping()
    public ResponseEntity<?> getAll() throws Exception {

        List<User> response = userService.all();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
