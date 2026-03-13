package com.hei.hotelapi.controller;

import com.hei.hotelapi.model.Booking;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    List<Booking> bookings = new ArrayList<>();

    // 5a
    @GetMapping
    public List<Booking> getBookings(){
        return bookings;
    }

    // 5b
    @PostMapping
    public List<Booking> addBooking(@RequestBody Booking booking){

        // BONUS
        if(booking.getRoomNumber() < 1 || booking.getRoomNumber() > 9){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Room number must be between 1 and 9"
            );
        }

        // 5c
        for(Booking b : bookings){
            if(b.getRoomNumber() == booking.getRoomNumber()
                    && b.getBookingDate().equals(booking.getBookingDate())){

                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Room already booked for this date"
                );
            }
        }

        bookings.add(booking);
        return bookings;
    }
}