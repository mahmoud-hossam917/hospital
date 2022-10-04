package com.example.hospital.Services;

import com.example.hospital.Error.NotFoundException;
import com.example.hospital.Models.Department;
import com.example.hospital.Models.Employee;
import com.example.hospital.Repositories.DepartmentRepository;
import com.example.hospital.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public Department findDepartment(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            return department.get();
        }
        return null;
    }

    public boolean isDepartmentExist(String name) {
        Optional<Department> department = departmentRepository.findByName(name);
        return department.isPresent();
    }

    public boolean createDepartment(Department department) {
        if (isDepartmentExist(department.getName())) {
            return false;
        }
        departmentRepository.save(department);
        return true;
    }

    public boolean deleteDepartment(Long id) {

        Department department = findDepartment(id);
        if (department == null) {
            return false;
        }
        departmentRepository.delete(department);
        return true;
    }

    public boolean updateDepartment(Department department) {
        Department item = findDepartment(department.getDepartmentId());
        if (item == null) {
            return false;
        }
        departmentRepository.save(department);
        return true;
    }

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }


}
