package com.example.Hotel_project.repository;

import com.example.Hotel_project.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
    @Query(value = "SELECT MAX(SUBSTRING(hotel_code, 12)) FROM hotel",nativeQuery = true)
    Long findByMaxNumber();
}
