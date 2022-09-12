package com.packt.project.dto.mapper.doctor;

import com.packt.project.dto.DoctorDto;
import com.packt.project.dto.mapper.AbstractMapper;
import com.packt.project.entity.DoctorEntity;

import java.util.ArrayList;
import java.util.List;

public class DoctorMapper extends AbstractMapper<DoctorEntity, DoctorDto> {

    private DoctorMapper(){}

    public static DoctorMapper INSTANCE = new DoctorMapper();

    @Override
    public DoctorDto convertToDto(DoctorEntity doctorEntity) {
        DoctorDto dto = new DoctorDto();
        dto.setId(doctorEntity.getId());
        dto.setFirstName(doctorEntity.getFirstName());
        dto.setLastName(doctorEntity.getLastName());
        dto.setEmail(doctorEntity.getEmail());
        dto.setSecurityCode(doctorEntity.getSecurityCode());
        dto.setLocation(doctorEntity.getLocation());
        dto.setSpeciality(doctorEntity.getSpeciality());
        return dto;
    }

    @Override
    public List<DoctorDto> convertToDto(List<DoctorEntity> entity) {
        List<DoctorDto> dtoList = new ArrayList<>();
        entity.stream().forEach(doctor -> {
            DoctorDto dto = new DoctorDto();
            dto = convertToDto(doctor);
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Override
    public DoctorEntity convertToEntity(DoctorDto doctorDto) {
        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setFirstName(doctorDto.getFirstName());
        doctorEntity.setLastName(doctorDto.getLastName());
        doctorEntity.setEmail(doctorDto.getEmail());
        doctorEntity.setSecurityCode(doctorDto.getSecurityCode());
        doctorEntity.setLocation(doctorDto.getLocation());
        doctorEntity.setSpeciality(doctorDto.getSpeciality());
        return doctorEntity;
    }
}
