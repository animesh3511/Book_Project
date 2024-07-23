package com.example.Hospital_project.model;


import com.example.Hospital_project.model.request.PatientRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "hospital")
public class Hospital {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id")
    private Long hospitalId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "Mobile_no",unique = true)
    private String mobNo;

    @Column(name = "hospital_code",unique = true)
    private String hospitalCode;

    @Column(name = "address")
    private String address;

    @Column(name = "established_date")
    private String establishedDate;

    @Column(name = "speciallity")
    private String speciallity;

    @Column(name = "isBranch")
    private Boolean isBranch;

    @Column(name = "isActive")
    private Boolean isActive;

    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @CreationTimestamp
    @Column(name = "createdAt",updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;




}

