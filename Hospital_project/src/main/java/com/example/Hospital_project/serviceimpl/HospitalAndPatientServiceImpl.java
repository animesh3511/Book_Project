package com.example.Hospital_project.serviceimpl;

import com.example.Hospital_project.model.Hospital;
import com.example.Hospital_project.model.HospitalRequest;
import com.example.Hospital_project.model.Patient;
import com.example.Hospital_project.model.request.*;
import com.example.Hospital_project.repository.HospitalRepository;
import com.example.Hospital_project.repository.PatientRepository;
import com.example.Hospital_project.service.HospitalAndPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

//in a spring boot application 'beans' are managed by speing iOc.Dependencies such as 'hospitalRepository' are
//are injected by spring cpntainer.
//this service layer 'HospitalAndPatientServiceImpl' which is annotated with '@service' annotation is a spring
//managed bean
@Service
public class HospitalAndPatientServiceImpl implements HospitalAndPatientService {

    @Autowired
    HospitalRepository hospitalRepository;
    @Autowired
    PatientRepository patientRepository;
    private AtomicLong sequence;

    @Override
    //this annotation ensure that entire method is executed in one transaction if any part of mrthod fails then the
    //method rolls back to maintain data consistency
    @Transactional
    public Object saveOrUpdate(HospitalRequest hospitalRequest, List<PatientRequest> patientRequest) {

        //updating user
        if (hospitalRepository.existsById(hospitalRequest.getHospitalId())) {

            Hospital hospital = hospitalRepository.findById(hospitalRequest.getHospitalId()).get();
            patientRepository.deleteByHospitalId(hospital.getHospitalId());
            hospital.setHospitalId(hospitalRequest.getHospitalId());
            hospital.setName(hospitalRequest.getName());
            List<Long> hospitalIds = new ArrayList<>();
            hospitalIds.add(hospitalRequest.getHospitalId());
            if (hospitalRepository.existsByEmailAndHospitalIdNotIn(hospitalRequest.getEmail(), hospitalIds)) {
                return "email already exists";
            } else {
                hospital.setEmail(hospitalRequest.getEmail());
            }
            hospital.setMobNo(hospitalRequest.getMobNo());
            hospital.setAddress(hospitalRequest.getAddress());
            hospital.setEstablishedDate(hospitalRequest.getEstablishedDate());
            hospital.setSpeciallity(hospitalRequest.getSpeciallity());
            Hospital hospital1 = hospitalRepository.save(hospital);

            for (PatientRequest pr : patientRequest) {

                Patient patient = new Patient();

                patient.setPatientId(pr.getPatientId());
                patient.setName(pr.getName());
                if (patientRepository.existsByEmail(pr.getEmail())) {
                    return "patient's email already exists";
                } else {
                    patient.setEmail(pr.getEmail());
                }
                patient.setMobNo(pr.getMobNo());
                patient.setAddress(pr.getAddress());
                patient.setGender(pr.getGender());
                patient.setDisease(pr.getDisease());
                patient.setJoiningDate(pr.getJoiningDate());
                patient.setHospitalId(hospital1.getHospitalId());
                patientRepository.save(patient);
            }
            return "Hospital and patient information updated";
        }


        //saving user starts here
        else {
            Hospital hospital = new Hospital();

            hospital.setHospitalId(hospitalRequest.getHospitalId());
            hospital.setName(hospitalRequest.getName());
            if (hospitalRepository.existsByEmail(hospitalRequest.getEmail())) {
                return "Hospital's email already exists";
            } else {
                hospital.setEmail(hospitalRequest.getEmail());
            }
            hospital.setMobNo(hospitalRequest.getMobNo());
            hospital.setAddress(hospitalRequest.getAddress());
            hospital.setEstablishedDate(hospitalRequest.getEstablishedDate());
            hospital.setSpeciallity(hospitalRequest.getSpeciallity());
            hospital.setIsActive(true);
            hospital.setIsDeleted(false);
            hospital.setIsBranch(false);
            hospital.setHospitalCode(generateHospitalCode());
            hospital = hospitalRepository.save(hospital);

            for (PatientRequest pr : patientRequest) {

                Patient patient = new Patient();

                patient.setPatientId(pr.getPatientId());
                patient.setName(pr.getName());
                if (patientRepository.existsByEmail(pr.getEmail())) {
                    return "patient's email already exists";
                } else {
                    patient.setEmail(pr.getEmail());
                }
                patient.setMobNo(pr.getMobNo());
                patient.setAddress(pr.getAddress());
                patient.setGender(pr.getGender());
                patient.setDisease(pr.getDisease());
                patient.setJoiningDate(pr.getJoiningDate());
                patient.setHospitalId(hospital.getHospitalId());
                patientRepository.save(patient);
            }
            return "Hospital details and patient details are saved";

        }
        //saveAndUpdate() method ends here
    }


    //jya method sathi he annotation waparlel asty ti method bean initialize zalyanantar and all its dependencies
    //inject zalyanantar call hote.so, this method is called by spring container when bean is inirialized and all
    //  its dependencies are injected mhnje hi method 'HospitalAndPatientService' ha bean initialize zalyanantr
    //and 'hospitalRepository' and 'patientRepository' hya dependency inject zalyanantr call hoel
    @PostConstruct
    private void initSequence() {

        Long maxSequenceNumber = hospitalRepository.findMaxSequenceNumber();

        //we use 'AtomicLong' bcoz it provides methods like 'getAndIncrement()' and this methods are thread safe
        sequence = new AtomicLong(maxSequenceNumber != null ? maxSequenceNumber + 1 : 1);
//initSequence() method ends here
    }


    //'synchronized' ensures that the method is thread safe preventing concurrent access issues when increamenting
    //sequence number.it prevents multiple threads from accessing the method and incrementing sequence number
    //which eventually leads to race condition and due to this dupicate sequence numbers are generated.so, to ensure
    //uniqueness of sequence number this method is synchronized
    private synchronized String generateHospitalCode() {

        int year = Year.now().getValue();

        //here,'getAndIncrement()' is method of 'AtomicLong' class we have initialized the 'sequence' instance
        //above in the code with constructor of 'AtomicLong' class with value '1' so here 'getAndIncrement()'
        //will first get the value '1' use it and then it increases it to '2'
        Long sequenceNumber = sequence.getAndIncrement();
        return String.format("hos-%02d-%05d", year % 100, sequenceNumber);
        //here,"hos" is the constant string that begins string format for hospital code
        //"%02d" this is a format specifier used for formatting integers.'%' indicates a format specifier will
        //follow.'02' specifies that integer should be 2 digits wide.'d' indicates the type of data to format
        //Example: If 'year % 100' evaluates to 21, '%02d' ensures it's formatted as '21'.
        // If it's 5, %02d ensures it's formatted as '05'.

//generateHospitalCode() method ends here
    }


    @Override
    public Object searchByNameMobNoAddressSpeciallity(List<String> nameList, List<String> mobNoList, List<String> addressList, List<String> speciallityList) {

        //  return hospitalRepository.searchByNameMobNoAddressSpeciallity(nameRequests,mobNoRequests,addressRequests,speciallityRequests);
        try {

            nameList = nameList.stream().map((nl) -> nl.toLowerCase()).collect(Collectors.toList());
            addressList = addressList.stream().map((al) -> al.toLowerCase()).collect(Collectors.toList());
            speciallityList = speciallityList.stream().map((sl) -> sl.toLowerCase()).collect(Collectors.toList());

            System.out.println("nameList " + nameList);
            System.out.println("nameList " + mobNoList);
            System.out.println("addressList " + addressList);
            System.out.println("speciallityList " + speciallityList);

            nameList = nameList.isEmpty() ? null : nameList;
            mobNoList = mobNoList.isEmpty() ? null : mobNoList;
            addressList = addressList.isEmpty() ? null : addressList;
            speciallityList = speciallityList.isEmpty() ? null : speciallityList;

       /* return hospitalRepository.searchByNameMobNoAddressSpeciallity(

                nameList.isEmpty() ? null : nameList,
                mobNoList.isEmpty() ? null : mobNoList,
                addressList.isEmpty() ? null : addressList,
                specialityList.isEmpty() ? null : specialityList);*/
           /* if (nameList.isEmpty()) {
                nameList.add("");
            }
            if (mobNoList.isEmpty()) {
                mobNoList.add("");
            }
            if (addressList.isEmpty()) {
                addressList.add("");
            }
            if (speciallityList.isEmpty()) {
                speciallityList.add("");
            }
*/
            List<Hospital> hospitalList = new ArrayList<>();
            //  hospitalList = hospitalRepository.searchByNameMobNoAddressSpeciallity(nameList, mobNoList, addressList, speciallityList);
            hospitalList = hospitalRepository.searchByNameMobNoAddressSpeciallity(nameList, mobNoList, addressList, speciallityList);

            return hospitalList;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



    @Override
    public Object searchByNameMobNoAddressSpeciallityNew(List<String> nameList, List<String> mobNoList, List<String> addressList, List<String> speciallityList) {

        // Convert lists to lower case
        nameList = nameList != null ? nameList.stream().map(String::toLowerCase).collect(Collectors.toList()) : null;
        addressList = addressList != null ? addressList.stream().map(String::toLowerCase).collect(Collectors.toList()) : null;
        speciallityList = speciallityList != null ? speciallityList.stream().map(String::toLowerCase).collect(Collectors.toList()) : null;

        // Convert empty lists to null
        if (nameList != null && nameList.isEmpty()) nameList = null;
        if (mobNoList != null && mobNoList.isEmpty()) mobNoList = null;
        if (addressList != null && addressList.isEmpty()) addressList = null;
        if (speciallityList != null && speciallityList.isEmpty()) speciallityList = null;

        return hospitalRepository.searchByNameMobNoAddressSpeciallityNew(nameList, mobNoList, addressList, speciallityList);

    }


    //class ends here
}

