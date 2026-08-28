package com.enterprise.employeemanagement.service;

import com.enterprise.employeemanagement.entity.Employee;
import com.enterprise.employeemanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> existingEmployee =
                employeeRepository.findById(id);

        if (existingEmployee.isPresent()) {
            Employee existing = existingEmployee.get();

            existing.setName(employee.getName());
            existing.setEmail(employee.getEmail());
            existing.setDepartment(employee.getDepartment());
            existing.setPosition(employee.getPosition());
            existing.setSalary(employee.getSalary());

            return employeeRepository.save(existing);
        }

        return null;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}