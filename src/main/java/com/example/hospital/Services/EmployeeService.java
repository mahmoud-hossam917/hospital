package com.example.hospital.Services;

import com.example.hospital.Models.Department;
import com.example.hospital.Models.Employee;
import com.example.hospital.Models.Patient;
import com.example.hospital.Repositories.DepartmentRepository;
import com.example.hospital.Repositories.EmployeeRepository;
import com.example.hospital.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PatientService patientService;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public Employee findEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        }
        return null;
    }

    public boolean isEmployeeExist(String name) {
        Optional<Employee> employee = employeeRepository.findByName(name);
        return employee.isPresent();
    }

    public boolean createEmployee(Employee employee) {

        if (isEmployeeExist(employee.getName())) {
            return false;
        }

        employeeRepository.save(employee);
        return true;
    }

    public boolean deleteEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        System.out.println("Delete employee" + employee.isPresent());
        if (!employee.isPresent()) {
            return false;
        }
        employeeRepository.delete(employee.get());
        return true;
    }

    public boolean updateEmployee(Employee employee) {
        if (findEmployee(employee.getEmployeeId()) == null) {
            return false;
        }
        employeeRepository.save(employee);
        return true;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public boolean addPatientToEmployee(Long employeeId, Long patientId) {

        Employee employee = findEmployee(employeeId);
        Patient patient = patientService.findPatient(patientId);
        if (employee == null || patient == null) {
            return false;
        }
        employee.getPatients().add(patient);
        patient.getEmployees().add(employee);
        employeeRepository.save(employee);
        patientRepository.save(patient);
        return true;

    }


}
