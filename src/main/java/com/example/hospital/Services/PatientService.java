package com.example.hospital.Services;

import com.example.hospital.Models.Patient;
import com.example.hospital.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    PatientRepository  patientRepository;

    public Patient findPatient(Long id){

        Optional<Patient> patient = patientRepository.findById(id);
        if(patient.isPresent())return  patient.get();
        return null;
    }
    public boolean isPatientExist(String name){
        Optional<Patient> patient = patientRepository.findByName(name);
        return  patient.isPresent();
    }
    public boolean createPatient(Patient patient){
        if(isPatientExist(patient.getName()))return false;
        patientRepository.save(patient);
        return true;
    }
    public boolean updatePatient(Patient patient){
        Patient item=findPatient(patient.getId());
        if(item==null)return false;
        patientRepository.save(patient);
        return true;
    }
    public boolean deletePatient(Long id){
        Patient patient=findPatient(id);
        if(patient==null)return false;
        patientRepository.delete(patient);
        return true;
    }
    public List<Patient> getPatients(){
        return  patientRepository.findAll();
    }



}
