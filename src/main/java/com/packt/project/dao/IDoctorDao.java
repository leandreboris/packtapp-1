package com.packt.project.dao;

import com.packt.project.entity.DoctorEntity;

import java.util.List;

public interface IDoctorDao {
    List<DoctorEntity> findByLocationAndSpeciality(String location, String speciality);
}
