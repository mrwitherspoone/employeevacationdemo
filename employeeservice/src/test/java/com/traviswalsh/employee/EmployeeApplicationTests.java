package com.traviswalsh.employee;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeApplicationTests {

        @Autowired
        private MockMvc mockMvc;

        private int testHourlyEmployeeId = 1;
        private int testSalariedEmployeeId = 11;
        private int testManagerEmployeeId = 21;
        private int testLifecycleEmployeeId = 2;
        @Test
        // Tests the requirements rather than functionality
        public void testEmployees() throws Exception {
                // are 30 employees created on startup
                mockMvc.perform(get("/employees").accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.length()").value(30));
                // is a 404 returned when an employee id is not found
                mockMvc.perform(get("/{id}/employee", 99).accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().is4xxClientError());

        }

        @Test
        public void testWork() throws Exception {
                // add 5 days worked
                mockMvc.perform(post("/{id}/work", testLifecycleEmployeeId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("5"))
                                .andExpect(status().isOk());
                // add more than the number of days available (expect exception 409 from
                // service)
                mockMvc.perform(post("/{id}/work", testLifecycleEmployeeId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("261"))
                                .andExpect(status().is4xxClientError());

                // test values of vacation accrual
                // hourly
                mockMvc.perform(post("/{id}/work", testHourlyEmployeeId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("260"))
                                .andExpect(status().isOk());
                mockMvc.perform(get("/{id}/employee", testHourlyEmployeeId).accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.vacationDays").value(10));
                // salaried
                mockMvc.perform(post("/{id}/work", testSalariedEmployeeId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("260"))
                                .andExpect(status().isOk());
                mockMvc.perform(get("/{id}/employee", testSalariedEmployeeId).accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.vacationDays").value(15));
                // manager
                mockMvc.perform(post("/{id}/work", testManagerEmployeeId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("260"))
                                .andExpect(status().isOk());
                mockMvc.perform(get("/{id}/employee", testManagerEmployeeId).accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.vacationDays").value(30));
        }

        @Test
        public void testTakeVacation() throws Exception {

                // first add vacation for the employee
                mockMvc.perform(post("/{id}/work", testLifecycleEmployeeId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("100"))
                                .andExpect(status().isOk());
                // now take vacation
                mockMvc.perform(post("/{id}/take-vacation", testLifecycleEmployeeId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("2.3"))
                                .andExpect(status().isOk());
                // take more vacation than is allowed
                mockMvc.perform(post("/{id}/take-vacation", testLifecycleEmployeeId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("100"))
                                .andExpect(status().is4xxClientError());

        }
}
