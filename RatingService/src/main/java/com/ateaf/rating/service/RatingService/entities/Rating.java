package com.ateaf.rating.service.RatingService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_rating")
public class Rating {

    @Id
    public String ratingId;
    public String userId;
    public String hotelId;
    public int rating;
    public String feedback;

}