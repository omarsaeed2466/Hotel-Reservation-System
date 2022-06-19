package com.example.hotelreservationsystem.repos;

import com.example.hotelreservationsystem.model.Capacity;
import com.example.hotelreservationsystem.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CapacityRepository extends JpaRepository<Capacity,Long> {
    Capacity findByRoomType(RoomType roomType);
}
