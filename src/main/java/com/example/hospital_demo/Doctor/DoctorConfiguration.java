package com.example.hospital_demo.Doctor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DoctorConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(DoctorRepository repository){
        return args -> {
            Doctor doctor1 = new Doctor(1L,
                    "Doctor1",
                    "doctor1@gmail.com",
                    LocalDate.of(1985, 10, 10));

            Doctor doctor2 = new Doctor(
                    "Doctor2",
                    "doctor2@gmail.com",
                    LocalDate.of(1983, 11, 11));

            Doctor doctor3 = new Doctor(
                    "Doctor3",
                    "doctor3@gmail.com",
                    LocalDate.of(1980, 7, 7));

            repository.saveAll(List.of(doctor1, doctor2, doctor3));
        };
    }
}
