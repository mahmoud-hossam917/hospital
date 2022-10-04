package com.example.hospital.Repositories;

import com.example.hospital.Models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    public Optional<Patient>findByName(String name);
}
