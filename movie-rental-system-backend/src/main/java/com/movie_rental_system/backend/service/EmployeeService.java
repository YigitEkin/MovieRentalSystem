package com.movie_rental_system.backend.service;

import com.movie_rental_system.backend.entity.Employee;
import com.movie_rental_system.backend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // find all employees
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    // find specific employee
    public Employee getEmployeeByName(String employeeName) {
        return employeeRepository.findById(employeeName).get();
    }

    // save employee
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // delete employee
    public Employee deleteEmployee(String employeeName) {
        Employee employee = employeeRepository.findById(employeeName).get();
        employeeRepository.deleteById(employeeName);
        return employee;
    }

    // update employee
    public Employee updateEmployee(String employeeName, Employee employee) {
        if(employeeRepository.existsById(employeeName)) {
            return employeeRepository.save(employee);
        }
        return null;
    }






}
