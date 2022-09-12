package com.packt.project.service;

import com.packt.project.dto.DoctorDto;
import com.packt.project.exception.PacktException;

import java.util.List;

public interface IDoctorService {
    List<DoctorDto> findByLocationAndSpeciality(String location, String speciality);
    DoctorDto create(DoctorDto dto) throws PacktException;
    DoctorDto findById(Long id);
    List<DoctorDto> findAll();
}
