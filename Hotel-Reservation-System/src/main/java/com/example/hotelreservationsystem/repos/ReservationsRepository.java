package com.example.hotelreservationsystem.repos;

import com.example.hotelreservationsystem.model.Reservations;
import com.example.hotelreservationsystem.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationsRepository extends JpaRepository<Reservations,Long> {
    List<Reservations>  findReservationsByRoomType(RoomType roomType);
    List<Reservations> findReservationsByReservationDateAndStartTimeBeforeAndEndTimeAfterOrStartTimeBetween
            (LocalDate reservationDate,LocalDate startTime,LocalDate endTime , LocalDate betweenStart , LocalDate betweenEnd);
}
