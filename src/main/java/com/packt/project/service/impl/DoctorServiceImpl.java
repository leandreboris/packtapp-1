package com.packt.project.service.impl;

import com.packt.project.dao.IDoctorDao;
import com.packt.project.dto.DoctorDto;
import com.packt.project.dto.mapper.doctor.DoctorMapper;
import com.packt.project.entity.DoctorEntity;
import com.packt.project.exception.PacktException;
import com.packt.project.repository.IDoctorRepository;
import com.packt.project.service.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
public class DoctorServiceImpl implements IDoctorService {

    @Autowired
    private IDoctorDao doctorDao;

    @Autowired
    private IDoctorRepository doctorRepository;


    @Override
    public List<DoctorDto> findByLocationAndSpeciality(String location, String speciality) {
        List<DoctorEntity> doctorEntityList = doctorDao.findByLocationAndSpeciality(location, speciality);
        return  DoctorMapper.INSTANCE.convertToDto(doctorEntityList);
    }

    @Override
    public DoctorDto create(DoctorDto dto) throws PacktException {
        if(dto == null){
            throw new PacktException("Cannot create an empty doctor");
        }
        DoctorEntity doctorEntity =  DoctorMapper.INSTANCE.convertToEntity(dto);
        doctorEntity.setSecurityCode(UUID.randomUUID().toString());
        doctorEntity.setLocation((doctorEntity.getLocation()).toUpperCase());
        doctorEntity.setSpeciality(StringUtils.capitalize(doctorEntity.getSpeciality()));

        return DoctorMapper.INSTANCE.convertToDto(doctorRepository.save(doctorEntity));
    }

    @Override
    public DoctorDto findById(Long id) {
        DoctorEntity doctorEntity = doctorRepository.findById(id).orElseThrow();
        DoctorDto dto = DoctorMapper.INSTANCE.convertToDto(doctorEntity);
        return dto;
    }

    @Override
    public List<DoctorDto> findAll() {
        List<DoctorEntity> doctorEntityList = doctorRepository.findAll();
        List<DoctorDto> doctorDtoList = DoctorMapper.INSTANCE.convertToDto(doctorEntityList);
        return doctorDtoList;
    }
}
