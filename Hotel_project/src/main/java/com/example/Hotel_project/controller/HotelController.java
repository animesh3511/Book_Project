package com.example.Hotel_project.controller;

import com.example.Hotel_project.model.HotelRequest;
import com.example.Hotel_project.model.response.CustomEntityResponse;
import com.example.Hotel_project.model.response.EntityResponse;
import com.example.Hotel_project.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody HotelRequest hotelRequest) {
        try {
            return new ResponseEntity<>(new EntityResponse(hotelService.saveOrUpdate(hotelRequest), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomEntityResponse(e.getMessage(), -1), HttpStatus.BAD_REQUEST);
        }


    }

}
