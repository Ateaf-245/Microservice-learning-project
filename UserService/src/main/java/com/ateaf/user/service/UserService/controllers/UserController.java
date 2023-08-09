package com.ateaf.user.service.UserService.controllers;

import com.ateaf.user.service.UserService.entities.User;
import com.ateaf.user.service.UserService.services.UserService;
import com.ateaf.user.service.UserService.services.impl.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    //create
    @PostMapping
    public ResponseEntity<User> createUser (@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    int retryCount = 1;
    //get single user
    @GetMapping("/{userId}")
//    @CircuitBreaker(name ="ratingHotelBreaker",fallbackMethod ="ratingHotelFallback" )
//    @Retry(name ="ratingHotelRetry",fallbackMethod ="ratingHotelFallback" )
    @RateLimiter(name ="userRateLimiter",fallbackMethod ="ratingHotelFallback" )
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
//        logger.info("Retry Count : {}",retryCount);
//        retryCount++;
       User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    // Creating fall back method for Circuit Breaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){

        ex.printStackTrace();

        logger.info("Fallback is executed because service is down:",ex.getMessage());
        User user = User.builder()
                .email("dummy@gmail.com")
                .name("Dummy")
                .about("This user is created dummy because some service is down")
                .userId("123123")
                .build();
        retryCount = 1;
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //geta all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    //delete a  user
    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId){
        // delete employee from DB
        userService.deleteUser(userId);
        return ResponseEntity.ok().body("User deleted successfully!");
    }

    //update a user
    @PutMapping("{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User user){
        User user1 = userService.updateUser(user,userId);
        return ResponseEntity.ok(user1);
    }

}
