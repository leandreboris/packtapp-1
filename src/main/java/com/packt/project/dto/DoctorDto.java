package com.packt.project.dto;

import lombok.Data;

@Data
public class DoctorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String securityCode;
    private String location;
    private String speciality;
}
