package com.ateaf.rating.service.RatingService.controllers;

import com.ateaf.rating.service.RatingService.entities.Rating;
import com.ateaf.rating.service.RatingService.services.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

//    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.saveRating(rating));
    }

    @GetMapping()
    public ResponseEntity<List<Rating>> getAll() {
        return ResponseEntity.ok(ratingService.getAllRating());
    }


//    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserID(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getAllByUserId(userId));
    }

//    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> geRatingByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(ratingService.getAllByHotelId(hotelId));
    }

}
