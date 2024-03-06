package com.zanchenko.alex.diploma.repository;

import com.zanchenko.alex.diploma.domain.Event;
import com.zanchenko.alex.diploma.domain.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FacilityRepository extends JpaRepository<Facility, Long> {

    @Query("SELECT f FROM Facility f WHERE f.facilityTitle LIKE %:facility_title%")
    List<Facility> findFacility(@Param("facility_title") String title);

}
