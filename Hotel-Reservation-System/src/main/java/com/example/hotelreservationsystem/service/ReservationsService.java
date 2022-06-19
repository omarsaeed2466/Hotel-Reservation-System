package com.example.hotelreservationsystem.service;

import com.example.hotelreservationsystem.model.Reservations;
import com.example.hotelreservationsystem.repos.CapacityRepository;
import com.example.hotelreservationsystem.repos.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReservationsService {
private final ReservationsRepository reservationsRepository ;
private final CapacityRepository capacityRepository ;


@Autowired
public ReservationsService(final ReservationsRepository reservationsRepository,final CapacityRepository capacityRepository) {
        this.reservationsRepository = reservationsRepository;
        this.capacityRepository = capacityRepository;
    }
    public List<Reservations>findAll(){
    return reservationsRepository.findAll();
}

public Reservations get(final Long id){
    return reservationsRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
}

public Long create(final Reservations reservations){
    int capacity = capacityRepository.findByRoomType(reservations.getRoomType()).getCapacity();
    int overlappingReservations = reservationsRepository
            .findReservationsByReservationDateAndStartTimeBeforeAndEndTimeAfterOrStartTimeBetween(
                    reservations.getReservationDate()
                    ,reservations.getStartTime(),
                    reservations.getEndTime(),reservations.getStartTime(),reservations.getEndTime()).size();
if (overlappingReservations >= capacity){
    throw new IllegalStateException("This room's capacity is full at desired time");
}
return reservationsRepository.save(reservations).getId();
}public void update(final Long id , final Reservations reservations ){
    final Reservations existingReservation = reservationsRepository.findById(id).orElseThrow(()->
            new ResponseStatusException(HttpStatus.NOT_FOUND));
    reservationsRepository.save(reservations);
    }
    public void delete(final Long id) {
        reservationsRepository.deleteById(id);
    }

}
