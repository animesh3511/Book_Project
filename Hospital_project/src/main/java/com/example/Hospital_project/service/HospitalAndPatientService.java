package com.example.Hospital_project.service;

import com.example.Hospital_project.model.HospitalRequest;
import com.example.Hospital_project.model.request.*;

import java.util.List;

public interface HospitalAndPatientService {


    Object saveOrUpdate(HospitalRequest hospitalRequest, List<PatientRequest> patientRequest);

    Object searchByNameMobNoAddressSpeciallity(List<String> nameList, List<String> mobNoList, List<String> addressList, List<String> speciallityList);

    Object searchByNameMobNoAddressSpeciallityNew(List<String> nameList, List<String> mobNoList, List<String> addressList, List<String> speciallityList);
}
