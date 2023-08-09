package com.ateaf.hotel.service.HotelService.Services.impl;

import com.ateaf.hotel.service.HotelService.Services.HotelService;
import com.ateaf.hotel.service.HotelService.entities.Hotel;
import com.ateaf.hotel.service.HotelService.exception.ResourceNotFoundException;
import com.ateaf.hotel.service.HotelService.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel saveHotel(Hotel hotel) {

        //generate unique user id
        String randomHotelId = UUID.randomUUID().toString();
        hotel.setId(randomHotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel with given id is not found server "+hotelId));
    }

    @Override
    public void deleteHotel(String hotelId) {
        hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel with given id is not found server "+hotelId));

        hotelRepository.deleteById(hotelId);
    }

    @Override
    public Hotel updateHotel(Hotel hotel, String hotelId) {
        hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found server "+hotelId));

        return hotelRepository.save(hotel);
    }
}
