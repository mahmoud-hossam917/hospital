package com.example.hospital.Controllers;

import com.example.hospital.Error.DaplicateException;
import com.example.hospital.Error.NotFoundException;
import com.example.hospital.Models.Employee;
import com.example.hospital.Models.Response;
import com.example.hospital.Models.ResponseWithListOfObject;
import com.example.hospital.Models.ResponseWithObject;
import com.example.hospital.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hospital")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("addemployee")
    public ResponseEntity addEmployee(@RequestBody Employee employee) {
        if (employeeService.createEmployee(employee)) {
            ResponseWithObject<Employee> response = new ResponseWithObject<Employee>
                    ("success", "Employee added successfully", employee);
            return ResponseEntity.ok(response);
        }
        throw new DaplicateException("Employee is already added");

    }

    @PutMapping("updateemployee")
    public ResponseEntity updateEmployee(@RequestBody Employee employee) {
        if (employeeService.updateEmployee(employee)) {
            ResponseWithObject<Employee> response = new ResponseWithObject<Employee>
                    ("success", "Employee added successfully", employee);
            return ResponseEntity.ok(response);
        }
        throw new NotFoundException("Employee not found");
    }

    @DeleteMapping("deleteemployee")
    public ResponseEntity deleteEmployee(@RequestParam Long id) {
        if (employeeService.deleteEmployee(id)) {
            Response response = new Response("success", "Employee deleted successfully");
            return ResponseEntity.ok(response);
        }
        throw new NotFoundException("Employee not found");
    }

    @GetMapping("getemployee")
    public ResponseEntity getEmployee(@RequestParam Long id) {
        Employee employee = employeeService.findEmployee(id);
        if (employee == null) {
            throw new NotFoundException("Employee not found");
        }
        ResponseWithObject<Employee> response = new ResponseWithObject<Employee>
                ("success", "Employee found successfully", employee);
        return ResponseEntity.ok(response);
    }

    @GetMapping("getemployees")
    public ResponseEntity getEmployees() {
        ResponseWithListOfObject<Employee> response = new ResponseWithListOfObject<Employee>
                ("success", "Employees found successfully", employeeService.getEmployees());
        return ResponseEntity.ok(response);
    }

    @PutMapping("addpatienttoemployee")
    public ResponseEntity addPatientToEmployee(@RequestParam Long employeeId, @RequestParam Long patientId) {

        if (employeeService.addPatientToEmployee(employeeId, patientId)) {

            Response response = new Response("success", "Patient added to employee successfully");
            return ResponseEntity.ok(response);
        }
        throw new NotFoundException("There is something wrong");
    }

}
