package com.example.Hotel_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HotelRequest {

    private Long hotelId;
    private String hotelName;
    private String location;
    private String address;
    private String zipcode;
    private String email;
    private String password;
    private String contact;
    private Double rating;
    private LocalDate startDate;
    private LocalDate endDate;
}