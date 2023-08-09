package com.ateaf.user.service.UserService.external.service;

import com.ateaf.user.service.UserService.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Hotel-Service")
public interface HotelService {

    @GetMapping("/hotels/{hotelid}")
    Hotel getHotels(@PathVariable String hotelid);
}
