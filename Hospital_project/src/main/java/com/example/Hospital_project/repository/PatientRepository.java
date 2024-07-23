package com.example.Hospital_project.repository;

import com.example.Hospital_project.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {


    boolean existsByEmail(String email);

    void deleteByHospitalId(Long hospitalId);
}
