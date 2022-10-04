package com.example.hospital.Repositories;

import com.example.hospital.Models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {


   public Optional<Department> findByName(String name);
}
