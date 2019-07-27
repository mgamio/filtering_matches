package com.spark.filtering.rest.user;

import com.spark.filtering.exception.ConstraintViolationException;
import com.spark.filtering.exception.ResourceNotFoundException;
import com.spark.filtering.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value="/filter")
    public ResponseEntity<?> filter(@Valid @RequestBody FilterRequest request) throws ConstraintViolationException, Exception {

        if (request.getCompatibilityScore() > 0) {
            if (request.getCompatibilityScore() < 1 || request.getCompatibilityScore() > 99)
                throw new ConstraintViolationException("Compatibility Score must be between 1% to 99%");
        }

        if (request.getAge() != null && request.getAge().intValue() < 18)
            throw new ConstraintViolationException("Age must be greater than or equal to 18 years old");

        if (request.getHeight() != null && request.getHeight().intValue() < 135)
            throw new ConstraintViolationException("Height must be greater than or equal to 135cm");

        List<User> response = userService.find(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping()
    public ResponseEntity<?> getAll() throws Exception {

        List<User> response = userService.all();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
