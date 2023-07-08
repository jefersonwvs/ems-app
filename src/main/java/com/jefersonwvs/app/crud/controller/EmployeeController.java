package com.jefersonwvs.app.crud.controller;

import com.jefersonwvs.app.crud.dao.EmployeeDAO;
import com.jefersonwvs.app.crud.dao.EmployeeDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class EmployeeController extends HttpServlet {

  private EmployeeDAO employeeDAO = null;

  @Override
  public void init() throws ServletException {
    super.init();
    employeeDAO = new EmployeeDAOImpl();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    System.out.println("Employee controller, doPost method invoked");

    String action = request.getServletPath();
    System.out.println("doPost action ==> " + action);

    switch (action) {
      case "add":
        addNewEmployee(request, response);
        break;
      case "get":
        getEmployee(request, response);
        break;
      case "list":
        getAllEmployees(request, response);
        break;
      case "update":
        updateEmployee(request, response);
        break;
      case "delete":
        deleteEmployee(request, response);
        break;
      default:
        addNewEmployee(request, response);
    }
  }

  private void addNewEmployee(HttpServletRequest request, HttpServletResponse response) {}

  private void getEmployee(HttpServletRequest request, HttpServletResponse response) {}

  private void getAllEmployees(HttpServletRequest request, HttpServletResponse response) {}

  private void updateEmployee(HttpServletRequest request, HttpServletResponse response) {}

  private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {}
}
