package com.example.hospital;

import com.example.hospital.Error.NotFoundException;
import com.example.hospital.Models.Department;
import com.example.hospital.Repositories.DepartmentRepository;
import com.example.hospital.Services.DepartmentService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class DepartmentTest {


    @MockBean
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentService departmentService;

    @TestConfiguration
    static class TestServiceContextConfiguration {


        public DepartmentService departmentService() {
            return new DepartmentService();
        }
    }

    @Test
    public void whenFindAll_DepartmentsShouldBeFound() throws Exception {
        Department department = new Department(1L, "math");
        Department department2 = new Department(2L, "math2");
        List<Department> departmentList = Arrays.asList(department, department2);
        given(departmentRepository.findAll()).willReturn(departmentList);
        assertThat(departmentService.getDepartments())
                .hasSize(2)
                .contains(department, department2);


    }

    @Test
    public void whenGetById_DepartmentShouldBeFound() {
        Department department = new Department(1L, "math");
        given(departmentRepository.findById(anyLong())).willReturn(Optional.ofNullable(department));
        Department result = departmentService.findDepartment(1L);
        assertThat(result).isEqualTo(department);
    }

    @Test
    public void whenGetID_DepartmentNotBeFound() {
        given(departmentRepository.findById(anyLong())).willReturn(Optional.empty());
        departmentService.findDepartment(1L);
    }
}
