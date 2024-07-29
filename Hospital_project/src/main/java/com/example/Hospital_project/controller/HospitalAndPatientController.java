package com.example.Hospital_project.controller;

import com.example.Hospital_project.model.HospitalRequest;
import com.example.Hospital_project.model.request.HospitalAndPatientRequest;
import com.example.Hospital_project.model.request.ListOfRequests;
import com.example.Hospital_project.model.request.PatientRequest;
import com.example.Hospital_project.model.response.CustomEntityResponse;
import com.example.Hospital_project.model.response.EntityResponse;
import com.example.Hospital_project.service.HospitalAndPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HospitalAndPatientController {

    @Autowired
    HospitalAndPatientService hospitalAndPatientService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody HospitalAndPatientRequest request) {
        try {
            return new ResponseEntity(new EntityResponse(hospitalAndPatientService.saveOrUpdate(request.getHospitalRequest(), request.getPatientRequests()), 0), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new CustomEntityResponse(e.getMessage(), -1), HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/searchByNameMobNoAddressSpeciallity")
    public ResponseEntity<?> searchByNameMobNoAddressSpeciallity(@RequestBody ListOfRequests requests) {
        try {
            List<String> list = requests.getNameList();
            System.out.println("inside controller "+list);
            return new ResponseEntity<>(new EntityResponse(hospitalAndPatientService.searchByNameMobNoAddressSpeciallity(list,
                                                                                                                        requests.getMobNoList(),
                                                                                                                        requests.getAddressList(),
                                                                                                                        requests.getSpeciallityList()), 0), HttpStatus.OK);
/*
            return new ResponseEntity<>(new EntityResponse(hospitalAndPatientService.searchByNameMobNoAddressSpeciallityNew(list,
                    requests.getMobNoList(),
                    requests.getAddressList(),
                    requests.getSpeciallityList()), 0), HttpStatus.OK);
*/
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomEntityResponse(e.getMessage(), -1), HttpStatus.BAD_REQUEST);
        }

    }


//controller class ends here
}
