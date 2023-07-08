package com.jefersonwvs.app.crud.dao;

import com.jefersonwvs.app.crud.beans.Employee;
import com.jefersonwvs.app.crud.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

  private static Connection connection = DBConnection.getConnection();

  @Override
  public boolean addEmployee(Employee employee) {
    try {
      String sqlInsertStatement =
          "INSERT INTO employee(name, email, phone, address) VALUES(?, ?, ?, ?)";

      PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertStatement);
      preparedStatement.setString(1, employee.getName());
      preparedStatement.setString(2, employee.getEmail());
      preparedStatement.setString(3, employee.getPhone());
      preparedStatement.setString(4, employee.getAddress());

      int result = preparedStatement.executeUpdate();

      return result == 1;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public Employee getEmployee(int employeeId) {
    return null;
  }

  @Override
  public List<Employee> getAllEmployees() {
    return null;
  }

  @Override
  public boolean updateEmployee(Employee employee) {
    try {
      String sqlUpdateStatement =
          "UPDATE employee SET name = ?, email = ?, phone = ?, address = ? WHERE id = ?";

      PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateStatement);
      preparedStatement.setString(1, employee.getName());
      preparedStatement.setString(2, employee.getEmail());
      preparedStatement.setString(3, employee.getPhone());
      preparedStatement.setString(4, employee.getAddress());
      preparedStatement.setInt(5, employee.getId());

      int result = preparedStatement.executeUpdate();

      return result == 1;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean deleteEmployee(int employeeId) {
    return false;
  }

  // "Test"
  public static void main(String[] args) {
    Employee employee = new Employee();
    employee.setId(5);
    employee.setName("Sasuke Uchiha");
    employee.setEmail("uchiha.sasuke@konoha.com");
    employee.setPhone("(123) 111-2222");
    employee.setAddress("Konohagakure");

    EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    // System.out.println(employeeDAO.addEmployee(employee));
    System.out.println(employeeDAO.updateEmployee(employee));
  }
}
