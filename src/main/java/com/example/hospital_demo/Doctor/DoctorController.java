package com.example.hospital_demo.Doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<Doctor> getAllDoctor(){
        return doctorService.getAllDoctor();
    }

    @PostMapping
    public void registerNewDoctor(@RequestBody Doctor doctor){
        doctorService.addNewDoctor(doctor);
    }

    @PutMapping(path = "doctorId")
    public void updateDoctorData(@PathVariable("doctorId") Long doctorId,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) String email) {
        doctorService.updateDoctorData(doctorId, name, email);
    }

    @DeleteMapping(path = "{doctorId}")
    public void deleteDoctor(@PathVariable("doctorId") Long doctorId){
        doctorService.deleteDoctor(doctorId);
    }
}
