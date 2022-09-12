package com.packt.project.rest.controller;

import com.packt.project.dto.DoctorDto;
import com.packt.project.exception.PacktException;
import com.packt.project.service.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {

    @Autowired
    private IDoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorDto> createNewDoctor(@Validated @RequestBody DoctorDto dto) throws PacktException {
        return new ResponseEntity<>(doctorService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DoctorDto>> findAllDoctors(){
        return new ResponseEntity<>(doctorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<DoctorDto> findDoctorById(@PathVariable("id") Long id){
        return new ResponseEntity<>(doctorService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DoctorDto>> searchDoctor(
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "speciality", required = false) String speciality){
        return new ResponseEntity<>(doctorService.findByLocationAndSpeciality(location, speciality), HttpStatus.OK);
    }


}
