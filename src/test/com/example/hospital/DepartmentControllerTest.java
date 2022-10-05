package com.example.hospital;

import com.example.hospital.Models.Department;
import com.example.hospital.Models.ResponseWithListOfObject;
import com.example.hospital.Models.ResponseWithObject;
import com.example.hospital.Services.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DepartmentService departmentService;

    @Test
    public void testGetAllDepartment() throws Exception {
        Department department = new Department(1L, "math");
        Department department2 = new Department(2L, "math2");
        List<Department> departmentList = Arrays.asList(department, department2);
        given(departmentService.getDepartments()).willReturn(departmentList);
        mockMvc.perform(get("/hospital/getalldepartments").contentType(
                        MediaType.APPLICATION_JSON
                ))
                .andExpect(status().isOk())
                .andExpect(jsonPath("data", hasSize(2)));
    }

    @Test
    public void whenCreateDepartment_willCreated() throws Exception {
        Department department = new Department(1L, "math3");
        given(departmentService.createDepartment(department)).willReturn(true);
        mockMvc.perform(post("/hospital/adddepartment").contentType(
                        MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(department))

        ).andExpect(status().isOk());


    }
}
