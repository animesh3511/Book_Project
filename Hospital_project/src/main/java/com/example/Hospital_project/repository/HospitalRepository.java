package com.example.Hospital_project.repository;

import com.example.Hospital_project.model.Hospital;
import com.example.Hospital_project.model.request.AddressRequest;
import com.example.Hospital_project.model.request.MobNoRequest;
import com.example.Hospital_project.model.request.NameRequest;
import com.example.Hospital_project.model.request.SpeciallityRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Long> {


    boolean existsByEmail(String email);

    @Query(value = "SELECT MAX(SUBSTRING(hospital_code, 8)) FROM Hospital",nativeQuery = true)
    Long findMaxSequenceNumber();

    boolean existsByEmailAndHospitalIdNotIn(String email, List<Long> hospitalIds);

   /* @Query(value = "SELECT h FROM Hospital h \n" +
            "WHERE (:nameList IS NULL OR LOWER(h.name) IN :nameList) \n" +
            "AND (:mobNoList IS NULL OR h.mobNo IN :mobNoList) \n" +
            "AND (:addressList IS NULL OR LOWER(h.address) IN :addressList) \n" +
            "AND (:speciallityList IS NULL OR LOWER(h.speciallity) IN :speciallityList)\n",nativeQuery = false)
    List<Hospital> searchByNameMobNoAddressSpeciallity(
            @Param("nameList") List<String> nameList,
            @Param("mobNoList") List<String> mobNoList,
            @Param("addressList") List<String> addressList,
            @Param("speciallityList") List<String> speciallityList);*/


   /* @Query(value = " SELECT * FROM hospital\n" +
            "    WHERE (:nameRequests IS NULL OR name IN (:nameRequests))\n" +
            "    AND (:mobNoRequests IS NULL OR mobile_no IN (:mobNoRequests))\n" +
            "    AND (:addressRequests IS NULL OR address IN (:addressRequests))\n" +
            "    AND (:speciallityRequests IS NULL OR speciality IN (:speciallityRequests))\n",nativeQuery = true)*/

   /* @Query(value = " SELECT * FROM hospital h \n" +
            "    WHERE (:nameList IS NULL OR h.name IN (:nameList))\n" +
            "    AND (:mobNoList IS NULL OR h.mobile_no IN (:mobNoList))\n" +
            "    AND (:addressList IS NULL OR h.address IN (:addressList))\n" +
            "    AND (:speciallityList IS NULL OR h.speciallity IN (:speciallityList))\n",nativeQuery = true)
    List<Hospital> searchByNameMobNoAddressSpeciallity( List<String> nameList,
                                                       List<String> mobNoList,
                                                       List<String> addressList,
                                                       List<String> speciallityList);
*/






    @Query(value = "SELECT h FROM Hospital as h \n" +
            "WHERE (COALESCE(:nameList,'')='' OR TRIM(LOWER(h.name)) IN :nameList OR :nameList IS NULL) \n" +
            "AND (COALESCE(:mobNoList,'')='' OR h.mobNo IN :mobNoList OR :mobNoList IS NULL) \n" +
            "AND (COALESCE(:addressList,'')='' OR TRIM(LOWER(h.address)) IN :addressList OR :addressList IS NULL) \n" +
            "AND (COALESCE(:speciallityList,'')='' OR TRIM(LOWER(h.speciallity)) IN :speciallityList OR :speciallityList IS NULL)", nativeQuery = false)
    List<Hospital> searchByNameMobNoAddressSpeciallity( @Param(value = "nameList") List<String> nameList,
                                                        @Param(value = "mobNoList")List<String> mobNoList,
                                                        @Param(value = "addressList") List<String> addressList,
                                                        @Param(value = "speciallityList") List<String> speciallityList);






    @Query("SELECT h FROM Hospital h \n" +
            "WHERE (:nameList IS NULL OR LOWER(h.name) IN :nameList) \n" +
            "AND (:mobNoList IS NULL OR h.mobNo IN :mobNoList) \n" +
            "AND (:addressList IS NULL OR LOWER(h.address) IN :addressList) \n" +
            "AND (:speciallityList IS NULL OR LOWER(h.speciallity) IN :speciallityList)\n")
    List<Hospital> searchByNameMobNoAddressSpeciallityNew(
            @Param("nameList") List<String> nameList,
            @Param("mobNoList") List<String> mobNoList,
            @Param("addressList") List<String> addressList,
            @Param("speciallityList") List<String> speciallityList);


//"(COALESCE(:locations, '') = '' OR TRIM(LOWER(hd.propertyAddress)) IN :locations OR :locations IS NULL) and \n" +

/*

    @Query("SELECT h FROM Hospital h " +
            "WHERE (:nameList IS NULL OR LOWER(h.name) IN :nameList) " +
            "AND (:mobNoList IS NULL OR LOWER(h.mobNo) IN :mobNoList) " +
            "AND (:addressList IS NULL OR LOWER(h.address) IN :addressList) " +
            "AND (:speciallityList IS NULL OR LOWER(h.speciallity) IN :speciallityList)")
*/

/*
    @Query("SELECT h FROM Hospital h " +
            "WHERE (:nameList IS NULL OR LOWER(h.name) IN (:nameList)) " +
            "AND (:mobNoList IS NULL OR LOWER(h.mobNo) IN (:mobNoList)) " +
            "AND (:addressList IS NULL OR LOWER(h.address) IN (:addressList)) " +
            "AND (:speciallityList IS NULL OR LOWER(h.speciallity) IN (:speciallityList))")
    */
/*
    @Query(value = "SELECT * FROM hospital h " +
            "WHERE (:nameList IS NULL OR LOWER(h.name) IN (:nameList)) " +
            "AND (:mobNoList IS NULL OR LOWER(h.mobile_no) IN (:mobNoList)) " +
            "AND (:addressList IS NULL OR LOWER(h.address) IN (:addressList)) " +
            "AND (:speciallityList IS NULL OR LOWER(h.speciallity) IN (:speciallityList))",
            nativeQuery = true
            */




/*

     @Query("SELECT h FROM Hospital h " +
            "WHERE (:nameList IS NULL OR h.name IN :nameList) " +
            "AND (:mobNoList IS NULL OR h.mobNo IN :mobNoList) " +
            "AND (:addressList IS NULL OR h.address IN :addressList) " +
            "AND (:speciallityList IS NULL OR h.speciallity IN :speciallityList)")
    List<Hospital> searchByNameMobNoAddressSpeciallity( List<String> nameList,
                                                        List<String> mobNoList,
                                                        List<String> addressList,
                                                        List<String> speciallityList);
*/



    /*@Query(value = "SELECT * FROM hospital h " +
            "WHERE (:nameListArr IS NULL OR h.name IN ?1) " +
            "AND (:mobNoListArr IS NULL OR h.mobile_no IN ?2) " +
            "AND (:addressListArr IS NULL OR h.address IN ?3) " +
            "AND (:speciallityListArr IS NULL OR h.speciallity IN ?4)",
            nativeQuery = true)*/

   /* @Query(value = "SELECT * FROM hospital h " +
            "WHERE (?1 IS NULL OR h.name IN ?1) " +
            "AND (?2 IS NULL OR h.mobile_no IN ?2) " +
            "AND (?3 IS NULL OR h.address IN ?3) " +
            "AND (?4 IS NULL OR h.speciallity IN ?4)",
            nativeQuery = true)
     Hospital[] searchByNameMobNoAddressSpeciallity(String[] nameListArr,
                                               String[] mobNoListArr,
                                               String[] addressListArr,
                                               String[] speciallityListArr);
*/


}
     //[SELECT h FROM com.example.Hospital_project.model.Hospital as h WHERE
// (COALESCE(:nameList_0, :nameList_1,'')=''
// OR (LOWER(h.name) IN (:nameList_0, :nameList_1)) OR (:nameList_0, :nameList_1 IS NULL))
// AND (COALESCE(:mobNoList_0, :mobNoList_1,'')='' OR
// (LOWER(h.mobNo) IN (:mobNoList_0, :mobNoList_1)) OR
// (:mobNoList_0, :mobNoList_1 IS NULL)) AND (COALESCE(null,'')='' OR
// (LOWER(h.address) IN (null)) OR (null IS NULL))
// AND (COALESCE(null,'')='' OR
// (LOWER(h.speciallity) IN (null)) OR (null IS NULL))


 // select hospital0_.hospital_id as hospital1_0_, hospital0_.address as address2_0_,
// hospital0_.created_at as created_3_0_, hospital0_.email as email4_0_,
// hospital0_.established_date as establis5_0_, hospital0_.hospital_code as hospital6_0_,
// hospital0_.is_active as is_activ7_0_, hospital0_.is_branch as is_branc8_0_,
// hospital0_.is_deleted as is_delet9_0_, hospital0_.mobile_no as mobile_10_0_,
// hospital0_.name as name11_0_, hospital0_.speciallity as special12_0_,
// hospital0_.updated_at as updated13_0_ from hospital hospital0_
// where (coalesce(?, '')='' or lower(hospital0_.name) in (?) or ? is null) and
// (coalesce(?, '')='' or lower(hospital0_.mobile_no) in (?) or ? is null) and
// (coalesce(null, '')='' or lower(hospital0_.address) in (null) or null=null) and
// (coalesce(null, '')='' or lower(hospital0_.speciallity) in (null) or null=null)


    //select hospital0_.hospital_id as hospital1_0_, hospital0_.address as address2_0_,
// hospital0_.created_at as created_3_0_, hospital0_.email as email4_0_,
// hospital0_.established_date as establis5_0_, hospital0_.hospital_code as hospital6_0_,
// hospital0_.is_active as is_activ7_0_, hospital0_.is_branch as is_branc8_0_,
// hospital0_.is_deleted as is_delet9_0_, hospital0_.mobile_no as mobile_10_0_,
// hospital0_.name as name11_0_, hospital0_.speciallity as special12_0_,
// hospital0_.updated_at as updated13_0_ from hospital hospital0_
// where (coalesce(?, ?, '')='' or lower(hospital0_.name) in (? , ?) or (? , ?) is null)
// and (coalesce(?, '')='' or lower(hospital0_.mobile_no) in (?) or ? is null)
// and (coalesce(?, '')='' or lower(hospital0_.address) in (?) or ? is null)
// and (coalesce(?, '')='' or lower(hospital0_.speciallity) in (?) or ? is null)




    /*@Query(value = "select hd from HotelDetails as hd left join UsersHotelAmenities as uha on uha.hotelDetailsId = hd.hotelDetailsId where hd.isDeleted = false and \n" +
            "(COALESCE(:locations, '') = '' OR TRIM(LOWER(hd.propertyAddress)) IN :locations OR :locations IS NULL) and \n" +
            "(COALESCE(:listingTypes, '') = '' OR TRIM(LOWER(hd.listingType)) IN :listingTypes OR :listingTypes IS NULL) and \n" +
            "(COALESCE(:placeTypes, '') = '' OR TRIM(LOWER(hd.placeType)) IN :placeTypes OR :placeTypes IS NULL) and \n" +
            "(:bedrooms IS NULL OR TRIM(LOWER(hd.bedrooms)) = :bedrooms) and \n" +
            "(:bathrooms IS NULL OR TRIM(LOWER(hd.bathrooms)) = :bathrooms) and \n" +
            "(COALESCE(:amenityIds, 0) = 0 OR uha.amenityId IN :amenityIds OR :amenityIds IS NULL) and \n" +
            "(hd.isFavourite = :isFavourite OR :isFavourite IS NULL OR COALESCE(:isFavourite, false) = false) and \n" +
            //"((:stDate IS NULL AND :edDate IS NULL)) AND \n" +
            "(:minPrice IS NULL OR :maxPrice IS NULL OR (hd.perNightPrice >= :minPrice AND hd.perNightPrice <= :maxPrice)) GROUP BY hd.hotelDetailsId", nativeQuery = false)
    Page<HotelDetails> searchHotelsByFiltersWithoutDates(@Param("locations") List<String> locations,  @Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice, @Param("listingTypes") List<String> listingTypes, @Param("bedrooms") String bedrooms, @Param("bathrooms") String bathrooms, @Param("placeTypes") List<String> placeTypes, @Param("amenityIds") List<Long> amenityIds, @Param("isFavourite") Boolean isFavourite, Pageable pageable);
*/
