package com.example.Hospital_project.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class ListOfRequests {

   /* private List<NameRequest> nameRequests;
    private List<MobNoRequest> mobNoRequests;
    private List<AddressRequest> addressRequests;
    private List<SpeciallityRequest> speciallityRequests;*/

    private List<String> nameList;
    private List<String> mobNoList;
    private List<String> addressList;
    private List<String> speciallityList;


}
