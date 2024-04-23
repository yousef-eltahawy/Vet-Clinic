package com.task.vetclinic.Repository;

import com.task.vetclinic.entities.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit,Long> {
}
