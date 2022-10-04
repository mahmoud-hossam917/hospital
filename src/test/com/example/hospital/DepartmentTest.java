package com.example.hospital;

import com.example.hospital.Error.NotFoundException;
import com.example.hospital.Repositories.DepartmentRepository;
import com.example.hospital.Services.DepartmentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

@RunWith(SpringRunner.class)

public class DepartmentTest {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentService departmentService;

    static class TestServiceContextConfiguration {

        public DepartmentService departmentService() {
            return new DepartmentService();
        }
    }

    @Test(expected = NotFoundException.class)
    public void whenGetById_DepartmentShouldBeFound() {
        departmentService.findDepartment(1L);
    }

}
