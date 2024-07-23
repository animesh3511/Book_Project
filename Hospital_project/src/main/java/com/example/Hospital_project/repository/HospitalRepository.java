package com.example.Hospital_project.repository;

import com.example.Hospital_project.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital,Long> {


    boolean existsByEmail(String email);

    @Query(value = "SELECT MAX(SUBSTRING(hospital_code, 8)) FROM Hospital",nativeQuery = true)
    Long findMaxSequenceNumber();

    boolean existsByEmailAndHospitalIdNotIn(String email, List<Long> hospitalIds);
}
