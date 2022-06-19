package com.example.hotelreservationsystem.model;

public enum RoomType {
    SINGLE("SINGLE"), DOUBLE("DOUBLE"), TRIPLE("TRIPLE");
    private final String name;

    RoomType(String value) {
        name = value;
    }

    @Override
    public String toString() {
        return name;
    }
}
