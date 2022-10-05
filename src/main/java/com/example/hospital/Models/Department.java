package com.example.hospital.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long departmentId;


    public String name;

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployee(Employee employee) {
        employees.add(employee);
    }

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Employee> employees;

    public Department(Long id, String name) {
        this.departmentId = id;
        this.name = name;
    }


}
