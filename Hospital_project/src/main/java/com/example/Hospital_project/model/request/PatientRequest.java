package com.example.Hospital_project.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientRequest {


    private Long patientId;

    private String name;

    private String email;

    private String mobNo;

    private String address;

    private String gender;

    private String disease;

    private String joiningDate;


}
