
package com.example.Hospital_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HospitalRequest {

    private Long hospitalId;

    private String name;

    private String email;

    private String mobNo;

    //private String hospitalCode;

    private String address;

    private String establishedDate;

    private String speciallity;

}
