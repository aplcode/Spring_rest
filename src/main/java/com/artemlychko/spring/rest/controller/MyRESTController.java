package com.artemlychko.spring.rest.controller;

import com.artemlychko.spring.rest.entity.Employee;
import com.artemlychko.spring.rest.exceprion_handling.EmployeeIncorrectData;
import com.artemlychko.spring.rest.exceprion_handling.NoSuchEmployeeException;
import com.artemlychko.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees(){
        return employeeService.getAllEmployees();

    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable("id") int id){
        Employee employee = employeeService.getEmployee(id);

        if(employee == null){
            throw new NoSuchEmployeeException("There is no employee with id " + id + " in Database");
        }

        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee){

        employeeService.saveEmployee(employee);
        return employee;

    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){

        employeeService.saveEmployee(employee);
        return employee;

    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable("id") int id){

        Employee employee = employeeService.getEmployee(id);
        if(employee == null){
            throw new NoSuchEmployeeException("There is no employee with id " + id + " in Database");
        }

        employeeService.deleteEmployee(id);
        return "Employee with id " + id + " was deleted";

    }
}
