package com.example.hospital.Controllers;

import com.example.hospital.Error.DaplicateException;
import com.example.hospital.Error.NotFoundException;
import com.example.hospital.Models.*;
import com.example.hospital.Services.DepartmentService;
import com.example.hospital.Services.EmployeeService;
import com.example.hospital.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hospital")
public class DepartmentController {


    @Autowired
    DepartmentService departmentService;

    @Autowired
    EmployeeService employeeService;

    @PostMapping("adddepartment")
    public ResponseEntity addEmployee(@RequestBody Department department) {
        if (departmentService.createDepartment(department)) {
            ResponseWithObject<Department> response =
                    new ResponseWithObject<Department>
                            ("success", "Department added successfully", department);
            return ResponseEntity.ok(response);
        }
        throw new DaplicateException("Department is already added");

    }

    @PutMapping("updatedepartment")
    public ResponseEntity updateDepartment(@RequestBody Department department) {
        if (departmentService.updateDepartment(department)) {
            ResponseWithObject<Department> response = new ResponseWithObject<Department>
                    ("success", "Department updated successfully", department);
            return ResponseEntity.ok(response);
        }
        throw new NotFoundException("Not found department");
    }

    @DeleteMapping("deletedepartment")
    public ResponseEntity deleteDepartment(@RequestParam Long id) {
        if (departmentService.deleteDepartment(id)) {
            Response response = new Response("success", "Department deleted successfully");
            return ResponseEntity.ok(response);
        }
        throw new NotFoundException("Could not find department");
    }

    @GetMapping("getdepartment")
    public ResponseEntity getDepartment(@RequestParam Long id) {
        Department department = departmentService.findDepartment(id);
        if (department != null) {
            ResponseWithObject<Department> response = new ResponseWithObject<Department>
                    ("success", "Department found successfully", department);
            return ResponseEntity.ok(response);
        }
        throw new NotFoundException("Could not find department");
    }

    @GetMapping("getalldepartments")
    public ResponseEntity getDepartments() {

        ResponseWithListOfObject<Department> response = new ResponseWithListOfObject<Department>
                ("response", "Departments found successfully", departmentService.getDepartments());

        return ResponseEntity.ok(response);
    }
   

}
