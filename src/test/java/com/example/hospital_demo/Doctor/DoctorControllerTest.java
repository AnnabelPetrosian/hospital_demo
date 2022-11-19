package com.example.hospital_demo.Doctor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DoctorControllerTest {

    @Mock
    private DoctorRepository doctorRepository;
    private DoctorService testCheck;

    @BeforeEach
    void setUp() {
        testCheck = new DoctorService(doctorRepository);
    }

    @Test
    void getAllDoctor() {
        testCheck.getAllDoctor();

        verify(doctorRepository).findAll();
    }

    @Test
    void registerNewDoctor() {
        Doctor doctor1 = new Doctor(1L,
                "Doctor1",
                "doctor1@gmail.com",
                LocalDate.of(1985, 10, 10));

        testCheck.addNewDoctor(doctor1);

        ArgumentCaptor<Doctor> doctorArgumentCaptor =
                ArgumentCaptor.forClass(Doctor.class);

        verify(doctorRepository).save(doctorArgumentCaptor.capture());

        Doctor capturedDoctor = doctorArgumentCaptor.getValue();

        assertThat(capturedDoctor).isEqualTo(doctor1);
    }
}