package com.traviswalsh.employee;

import java.util.ArrayList;
import java.util.List;
import com.github.javafaker.Faker;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
public class EmployeeController {
    private List<Employee> employees;

    public EmployeeController() {
        employees = new ArrayList<>();
        Faker faker = new Faker();
        String firstName = "";
        String lastName = "";

        for (int i = 1; i < 11; i++) {
            firstName = faker.name().firstName();
            lastName = faker.name().lastName();
            employees.add(new HourlyEmployee(firstName, lastName, i));
        }
        for (int i = 11; i < 21; i++) {
            firstName = faker.name().firstName();
            lastName = faker.name().lastName();
            employees.add(new SalariedEmployee(firstName, lastName, i));
        }
        for (int i = 21; i < 31; i++) {
            firstName = faker.name().firstName();
            lastName = faker.name().lastName();
            employees.add(new Manager(firstName, lastName, i));
        }
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employees;
    }

    @PostMapping("/{index}/work")
    public void work(@PathVariable int index, @RequestBody int daysWorked) throws IllegalArgumentException {
        
        employees.get(index - 1  ).work(daysWorked);
        
    }

    @PostMapping("/{index}/take-vacation")
    public void takeVacation(@PathVariable int index, @RequestBody float vacationDays) throws IllegalArgumentException {
        employees.get(index - 1 ).takeVacation(vacationDays);
    }



}
