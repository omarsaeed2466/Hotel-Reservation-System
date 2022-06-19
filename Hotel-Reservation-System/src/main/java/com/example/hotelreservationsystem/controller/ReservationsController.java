package com.example.hotelreservationsystem.controller;

import com.example.hotelreservationsystem.model.Reservations;
import com.example.hotelreservationsystem.service.ReservationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1")
public class ReservationsController {
Logger logger = LoggerFactory.getLogger(ReservationsController.class);
    private final ReservationsService reservationsService;
@Autowired
    public ReservationsController(ReservationsService reservationsService) {
        this.reservationsService = reservationsService;
    }
    @GetMapping("/get/all-reservations")
    public List <Reservations> getReservations(){
    return reservationsService.findAll();
    }
    @PostMapping
    public void setNewReservation(@RequestBody Reservations reservation){
    reservationsService.create(reservation);
}
    @DeleteMapping("/{id}")
    public void deleteReservationById(@PathVariable("id") Long reservationId)
    {
        reservationsService.delete(reservationId);
    }

}
