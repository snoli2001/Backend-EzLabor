package com.ezlabor.hiring.domain.repository;

import com.ezlabor.hiring.domain.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {

}
