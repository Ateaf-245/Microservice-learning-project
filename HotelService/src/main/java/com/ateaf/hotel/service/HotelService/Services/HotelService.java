package com.ateaf.hotel.service.HotelService.Services;

import com.ateaf.hotel.service.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {
    // create
    Hotel saveHotel(Hotel hotel);

    // get all Hotels
    List<Hotel> getAllHotel();

    //get single user of given hotelId
    Hotel getHotel(String hotelId);

    //delete a single user bu given hotelId
    void deleteHotel(String hotelId);

    //update a user by given hotelId
    Hotel updateHotel(Hotel hotel, String hotelId);
}
