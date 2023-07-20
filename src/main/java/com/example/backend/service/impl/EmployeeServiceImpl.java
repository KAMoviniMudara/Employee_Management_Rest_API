package com.example.backend.service.impl;
import com.example.backend.dto.Employee;
import com.example.backend.expection.ResourceNotFoundException;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id){
       return employeeRepository.findById(id).orElseThrow(() ->
               new ResourceNotFoundException("Employee","Id",id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {

        //Check whether employee with given id is exist in DB or not
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee","Id",id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        //Save existing employee to DB
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id){
        //Check whether a employee exist in DB or not
        employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee","Id",id));
        employeeRepository.deleteById(id);
    }
}
