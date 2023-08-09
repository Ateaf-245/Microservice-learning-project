package com.ateaf.rating.service.RatingService.services;

import com.ateaf.rating.service.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {

    // create
    Rating saveRating(Rating rating);

    // get all ratings
    List<Rating> getAllRating();

    //get rating by userId
    List<Rating> getAllByUserId(String userId);

    //get rating by HotelId
    List<Rating> getAllByHotelId(String HotelId);

    //delete a single rating bu given ratingId
    void deleteURating(String ratingId);

    //update a user by given ratingId
    Rating updateRating(Rating rating, String ratingId);
}
