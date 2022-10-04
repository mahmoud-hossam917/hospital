package com.example.hospital.Controllers;

import com.example.hospital.Error.DaplicateException;
import com.example.hospital.Error.NotFoundException;
import com.example.hospital.Models.Patient;
import com.example.hospital.Models.Response;
import com.example.hospital.Models.ResponseWithListOfObject;
import com.example.hospital.Models.ResponseWithObject;
import com.example.hospital.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("hospital")
public class PatientController {


    @Autowired
    PatientService patientService;

    @PostMapping("addpatient")
    public ResponseEntity addPatient(@RequestBody Patient patient) {
        if (patientService.createPatient(patient)) {

            ResponseWithObject<Patient> response = new ResponseWithObject<Patient>
                    ("success", "Patient created successfully", patient);
            return ResponseEntity.ok(response);
        }
        throw new DaplicateException("Patient is already exist");
    }

    @DeleteMapping("deletepatient")
    public ResponseEntity deletePatient(@RequestParam Long id) {
        if (patientService.deletePatient(id)) {
            Response response = new Response("success", "Patient deleted successfully");
            return ResponseEntity.ok(response);
        }
        throw new NotFoundException("Patient not found");
    }

    @PutMapping("updatepatient")
    public ResponseEntity updatePatient(@RequestBody Patient patient) {
        if (patientService.updatePatient(patient)) {
            ResponseWithObject<Patient> response = new ResponseWithObject<Patient>
                    ("success", "Patient updated successfully", patient);
            return ResponseEntity.ok(response);
        }
        throw new NotFoundException("Patient not found");
    }

    @GetMapping("getpatient")
    public ResponseEntity getPatient(@RequestParam Long id) {
        Patient patient = patientService.findPatient(id);
        if (patient != null) {
            ResponseWithObject<Patient> response = new ResponseWithObject<Patient>
                    ("success", "Patient retrieved successfully", patient);
            return ResponseEntity.ok(response);
        }
        throw new NotFoundException("Patient not found");
    }

    @GetMapping("getallpatients")
    public ResponseEntity getAllPatients() {
        List<Patient> patients = patientService.getPatients();
        if (patients.size() > 0) {
            ResponseWithListOfObject<Patient> response = new ResponseWithListOfObject<Patient>
                    ("success", "All patients retrieved successfully", patients);
            return ResponseEntity.ok(response);
        }
        throw new NotFoundException("There is no patients");
    }


}
