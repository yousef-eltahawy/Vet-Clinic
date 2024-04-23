package com.task.vetclinic.Repository;

import com.task.vetclinic.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner,Long> {

    Optional<Owner> findByName(String name);
}
