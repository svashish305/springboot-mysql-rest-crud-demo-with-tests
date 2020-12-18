package com.example.btassignment.controller;

import com.example.btassignment.model.Employee;
import com.example.btassignment.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
  @Autowired
  EmployeeService employeeService;

  @GetMapping("")
    public List<Employee> list() {
      return employeeService.listAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable Integer id) {
      try {
          Employee employee = employeeService.getEmployee(id);
          return new ResponseEntity<Employee>(employee, HttpStatus.OK);
      } catch (NoSuchElementException e) {
          return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping("/")
    public void add(@RequestBody Employee employee) {
      employeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Employee employee, @PathVariable Integer id) {
      try {
          // Employee existEmployee = employeeService.getEmployee(id);
          employee.setId(id);
          employeeService.saveEmployee(employee);
          return new ResponseEntity<>(HttpStatus.OK);
      } catch (NoSuchElementException e) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
      employeeService.deleteEmployee(id);
    }
}
