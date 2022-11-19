package com.example.hospital_demo.Doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAllDoctor(){
        return doctorRepository.findAll();
    }

    public void addNewDoctor(Doctor doctor) {
        Optional<Doctor> doctorOptional = doctorRepository.findDoctorByEmail(doctor.getEmail());

        if(doctorOptional.isPresent()){
            throw new IllegalStateException("This email already exists!!!");
        }
        doctorRepository.save(doctor);
    }

    @Transactional
    public void updateDoctorData(Long doctorId, String name, String email) {
        Doctor doctor =
                doctorRepository.findById(doctorId).orElseThrow(()->
                        new IllegalStateException("Doctor with id" + doctorId + "does not exist."));

        if(name != null &&
                name.length() > 0 &&
                !Objects.equals(doctor.getName(), name)){
            doctor.setName(name);
        }

        if(email != null &&
                email.length() > 0 &&
                !Objects.equals(doctor.getEmail(), email)){
            Optional<Doctor> doctorOptional =
                    doctorRepository.findDoctorByEmail(email);

            if(doctorOptional.isPresent()){
                throw new IllegalStateException("Email exist!!!");
            }
            doctor.setEmail(email);
        }
    }

    public void deleteDoctor(Long doctorId) {
        boolean doctorExists = doctorRepository.existsById(doctorId);

        if(!doctorExists){
            throw new IllegalStateException("Doctor with id" + doctorId + "does not exist.");
        }
        doctorRepository.deleteById(doctorId);
    }
}
