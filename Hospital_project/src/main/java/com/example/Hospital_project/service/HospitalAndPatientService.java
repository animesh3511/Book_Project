package com.example.Hospital_project.service;

import com.example.Hospital_project.model.HospitalRequest;
import com.example.Hospital_project.model.request.PatientRequest;

import java.util.List;

public interface HospitalAndPatientService {


    Object saveOrUpdate(HospitalRequest hospitalRequest, List<PatientRequest> patientRequest);
}
