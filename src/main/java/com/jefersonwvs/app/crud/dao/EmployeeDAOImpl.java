package com.jefersonwvs.app.crud.dao;

import com.jefersonwvs.app.crud.beans.Employee;
import com.jefersonwvs.app.crud.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
  public Employee getEmployee(Integer employeeId) {
    try {
      String sqlSelectByIdStatement = "SELECT * FROM employee WHERE id = ?";

      PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectByIdStatement);
      preparedStatement.setInt(1, employeeId);

      ResultSet resultSet = preparedStatement.executeQuery();
      Employee employee = null;

      if (resultSet.next()) {
        employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setName(resultSet.getString("name"));
        employee.setEmail(resultSet.getString("email"));
        employee.setPhone(resultSet.getString("phone"));
        employee.setAddress(resultSet.getString("address"));
      }

      return employee;

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public List<Employee> getAllEmployees() {
    try {
      String sqlSelectAllStatement = "SELECT * FROM employee";
      PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectAllStatement);

      ResultSet resultSet = preparedStatement.executeQuery();
      List<Employee> employees = new ArrayList<>();

      while (resultSet.next()) {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setName(resultSet.getString("name"));
        employee.setEmail(resultSet.getString("email"));
        employee.setPhone(resultSet.getString("phone"));
        employee.setAddress(resultSet.getString("address"));

        employees.add(employee);
      }

      return employees;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
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
  public boolean deleteEmployee(Integer employeeId) {
    try {
      String sqlDeleteStatement = "DELETE FROM employee WHERE id = ?";

      PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteStatement);
      preparedStatement.setInt(1, employeeId);

      int result = preparedStatement.executeUpdate();

      return result == 1;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
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
    // System.out.println(employeeDAO.addEmployee(employee));     // Create
    // System.out.println(employeeDAO.getEmployee(10));           // Read
    // System.out.println(employeeDAO.getAllEmployees());
    // System.out.println(employeeDAO.updateEmployee(employee));  // Update
    // System.out.println(employeeDAO.deleteEmployee(3));         // Delete
  }
}
