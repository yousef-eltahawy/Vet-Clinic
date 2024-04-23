package com.task.vetclinic.Repository;

import com.task.vetclinic.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PetRepository extends JpaRepository<Pet,Long> {

}
