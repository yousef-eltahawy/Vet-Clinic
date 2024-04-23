package com.task.vetclinic.Repository;

import com.task.vetclinic.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    Optional<Doctor> findByName(String name);
}
