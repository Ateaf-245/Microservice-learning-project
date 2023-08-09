package com.ateaf.hotel.service.HotelService.controllers;

import com.ateaf.hotel.service.HotelService.Services.HotelService;
import com.ateaf.hotel.service.HotelService.entities.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    //create

    @PostMapping
    public ResponseEntity<Hotel> createHotel (@RequestBody Hotel hotel){
        Hotel hotel1 = hotelService.saveHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    //get single user
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getSingleHotel(@PathVariable String hotelId){
        Hotel hotel = hotelService.getHotel(hotelId);
        return ResponseEntity.ok(hotel);
    }

    //geta all users
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotel(){
        List<Hotel> allUser = hotelService.getAllHotel();
        return ResponseEntity.ok(allUser);
    }

    //delete a  user
    @DeleteMapping("{hotelId}")
    public ResponseEntity<String> deleteHotel(@PathVariable String hotelId){
        // delete employee from DB
        hotelService.deleteHotel(hotelId);
        return ResponseEntity.ok().body("Hotel deleted successfully!");
    }

    //update a user
    @PutMapping("{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable String hotelId, @RequestBody Hotel hotel){
        Hotel hotel1 = hotelService.updateHotel(hotel,hotelId);
        return ResponseEntity.ok(hotel1);
    }

}
