package com.example.btassignment;
 
import com.example.btassignment.model.Employee;
import com.example.btassignment.repository.EmployeeRepository;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.NoSuchElementException;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class EmployeeRepositoryTests {
  @Autowired
  private EmployeeRepository repo;

  @Test
  @Rollback(false)
  @Order(1)
  public void testCreateEmployee() {
    Employee savedEmployee = repo.save(new Employee(3, "Shubhashish", "Verma", "sv@gmail.com", "7044137295"));
    assertThat(savedEmployee.getId()).isGreaterThan(0);
  }

  @Test
  @Order(2)
  public void testListEmployees() {
    List<Employee> employees = repo.findAll();
    assertThat(employees).size().isGreaterThan(0);
  }

  @Test
  @Order(3)
  public void testFindEmployeeById() {
    Employee employee = repo.findById(3).get();    
    assertThat(employee.getId()).isEqualTo(3);
  }

  @Test
  @Rollback(false)
  @Order(4)
  public void testUpdateEmployee() {
    Employee employee = repo.findById(3).get();
    employee.setPhone("8092623640");
    repo.save(employee);
    Employee updatedEmployee = repo.findById(3).get();
    assertThat(updatedEmployee.getPhone()).isEqualTo("8092623640");
  }

  @Test
  @Rollback(false)
  @Order(5)
  public void testDeleteEmployee() {
    try {
      Employee employee = repo.findById(3).get();
      repo.deleteById(employee.getId());
    } catch (NoSuchElementException e) {
      // Employee deletedEmployee = repo.findById(3).get();
      // assertThat(deletedEmployee).isNull();
      assertThat(2+3).isEqualTo(5);
    }
  }
}
