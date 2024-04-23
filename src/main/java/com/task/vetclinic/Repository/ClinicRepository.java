package com.task.vetclinic.Repository;

import com.task.vetclinic.entities.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClinicRepository extends JpaRepository<Clinic,Long> {
    Optional<Clinic> findByName(String name);

    @Query("select c from Clinic c where " +
            "lower(c.phone) like lower(concat('%',:keyword ,'%')) or " +
            "lower(c.address) like lower(concat('%',:keyword ,'%'))")
    List<Clinic> searchByPhoneOrAddress(@Param("keyword") String keyword);
}
