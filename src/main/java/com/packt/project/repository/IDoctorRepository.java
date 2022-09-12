package com.packt.project.repository;

import com.packt.project.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorRepository extends JpaRepository<DoctorEntity, Long> {
}
