package com.example.btassignment.service;

import com.example.btassignment.model.Employee;
import com.example.btassignment.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeService {
  @Autowired
  private EmployeeRepository employeeRepository;

  public List<Employee> listAllEmployees() {
    return employeeRepository.findAll();
  }

  public void saveEmployee(Employee employee) {
    employeeRepository.save(employee);
  }

  public Employee getEmployee(Integer id) {
    return employeeRepository.findById(id).get();
  }

  public void deleteEmployee(Integer id) {
    employeeRepository.deleteById(id);
  }
  
}
