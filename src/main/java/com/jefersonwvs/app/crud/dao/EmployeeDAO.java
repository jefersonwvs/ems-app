package com.jefersonwvs.app.crud.dao;

import com.jefersonwvs.app.crud.beans.Employee;

import java.util.List;

public interface EmployeeDAO {

  // Create
  boolean addEmployee(Employee employee);

  // Read
  Employee getEmployee(int employeeId);
  List<Employee> getAllEmployees();

  // Update
  boolean updateEmployee(Employee employee);

  // Delete
  boolean deleteEmployee(int employeeId);
}
