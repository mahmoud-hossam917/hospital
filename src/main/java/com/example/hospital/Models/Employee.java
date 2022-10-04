package com.example.hospital.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long employeeId;

    public String jobTitle;
    public double salary;
    public String name;
    public String age;
    public String address;
    public String email;
    public String phoneNumber;
    @Column(name = "department_id")
    public Long departmentId;

    @ManyToMany
    @JoinTable(name = "employee_patient",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    Set<Patient> patients;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id", nullable = false, insertable = false, updatable = false)
    @JsonBackReference
    private Department department;

}
