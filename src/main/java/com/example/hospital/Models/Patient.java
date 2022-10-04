package com.example.hospital.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patient")

public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public String name;
    public String age;
    public String address;
    public String email;
    public String phoneNumber;
    private String report;
    private String status;

    @JsonBackReference(value = "employee")
    @ManyToMany(mappedBy = "patients", fetch = FetchType.LAZY)
    private Set<Employee> employees;

}
