package com.example.Hospital_project.model.request;

import com.example.Hospital_project.model.HospitalRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HospitalAndPatientRequest {

  private HospitalRequest hospitalRequest;

  private List<PatientRequest> patientRequests;


}
