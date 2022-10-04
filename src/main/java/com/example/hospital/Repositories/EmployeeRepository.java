package com.example.hospital.Repositories;

import com.example.hospital.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    public Optional<Employee> findByName(String name);

}
