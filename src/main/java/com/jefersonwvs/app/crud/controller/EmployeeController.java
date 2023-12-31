package com.jefersonwvs.app.crud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jefersonwvs.app.crud.beans.Employee;
import com.jefersonwvs.app.crud.dao.EmployeeDAO;
import com.jefersonwvs.app.crud.dao.EmployeeDAOImpl;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class EmployeeController extends HttpServlet {

  private EmployeeDAO employeeDAO = null;

  @Override
  public void init() throws ServletException {
    super.init();
    employeeDAO = new EmployeeDAOImpl();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    doPost(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    System.out.println("[APP LOG] Employee controller doPost method invoked");

    String action = request.getServletPath();
    System.out.println("[APP LOG] doPost action: " + action);

    switch (action) {
      case "/add":
        addNewEmployee(request, response);
        break;
      case "/get":
        getEmployee(request, response);
        break;
      case "/update":
        updateEmployee(request, response);
        break;
      case "/delete":
        deleteEmployee(request, response);
        break;
      case "/list":
      default:
        getAllEmployees(request, response);
    }
  }

  private void addNewEmployee(HttpServletRequest request, HttpServletResponse response) {
    Employee newEmployee = EmployeeController.getEmployeeFromRequest(request);
    System.out.println(
        "[APP LOG] addNewEmployee method invoked - employee details: " + newEmployee);
    boolean result = employeeDAO.addEmployee(newEmployee);
    System.out.println(
        "[APP LOG] Employee \""
            + newEmployee.getName()
            + "\" was "
            + (!result ? "not " : "")
            + "added");
    List<Employee> employees = employeeDAO.getAllEmployees();
    try {
      RequestDispatcher dispatcher = request.getRequestDispatcher("/employeesView.jsp");
      request.setAttribute("employees", employees);
      dispatcher.forward(request, response);
    } catch (ServletException | IOException e) {
      e.printStackTrace();
    }
  }

  private void getEmployee(HttpServletRequest request, HttpServletResponse response) {
    Integer employeeId = EmployeeController.getEmployeeFromRequest(request).getId();
    System.out.println("[APP LOG] getEmployee method invoked - employee details: ID " + employeeId);
    Employee foundEmployee = employeeDAO.getEmployee(employeeId);
    System.out.println(
        "[APP LOG] Employee ["
            + employeeId
            + "] was "
            + ((foundEmployee == null) ? "not " : "")
            + "found");
    try {
      ObjectMapper mapper = new ObjectMapper();
      String employeeStr = mapper.writeValueAsString(foundEmployee);
      response.getOutputStream().write(employeeStr.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void getAllEmployees(HttpServletRequest request, HttpServletResponse response) {
    System.out.println("[APP LOG] getAllEmployees method invoked");
    List<Employee> employees = employeeDAO.getAllEmployees();
    System.out.println("[APP LOG] Employees list size: " + employees.size());

    try {
      RequestDispatcher dispatcher = request.getRequestDispatcher("/employeesView.jsp");
      request.setAttribute("employees", employees);
      dispatcher.forward(request, response);
    } catch (ServletException | IOException e) {
      e.printStackTrace();
    }
  }

  private void updateEmployee(HttpServletRequest request, HttpServletResponse response) {
    Employee updatedEmployee = EmployeeController.getEmployeeFromRequest(request);
    System.out.println(
        "[APP LOG] updateEmployee method invoked - employee details: " + updatedEmployee);
    boolean result = employeeDAO.updateEmployee(updatedEmployee);
    System.out.println(
        "[APP LOG] Employee \""
            + updatedEmployee.getName()
            + "\" was "
            + (!result ? "not " : "")
            + "updated");
    List<Employee> employees = employeeDAO.getAllEmployees();
    try {
      RequestDispatcher dispatcher = request.getRequestDispatcher("/employeesView.jsp");
      request.setAttribute("employees", employees);
      dispatcher.forward(request, response);
    } catch (ServletException | IOException e) {
      e.printStackTrace();
    }
  }

  private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
    String employeeIds = request.getParameter("employeeIds");
    StringTokenizer tokenizer = new StringTokenizer(employeeIds, ",");

    while (tokenizer.hasMoreTokens()) {
      int employeeId = Integer.parseInt(tokenizer.nextToken());
      System.out.println("[APP LOG] deleteEmployee method invoked - Employee ID: " + employeeId);
      boolean result = employeeDAO.deleteEmployee(employeeId);
      System.out.println(
          "[APP LOG] Employee [ID " + employeeId + "] was " + (!result ? "not " : "") + "deleted");
    }

    List<Employee> employees = employeeDAO.getAllEmployees();
    try {
      RequestDispatcher dispatcher = request.getRequestDispatcher("/employeesView.jsp");
      request.setAttribute("employees", employees);
      dispatcher.forward(request, response);
    } catch (ServletException | IOException e) {
      e.printStackTrace();
    }
  }

  private static Employee getEmployeeFromRequest(HttpServletRequest request) {
    Employee employee = new Employee();
    Optional<Integer> idOptional =
        Optional.ofNullable(request.getParameter("id")).map(Integer::parseInt).or(Optional::empty);

    employee.setId(idOptional.orElse(null));
    employee.setName(request.getParameter("name"));
    employee.setEmail(request.getParameter("email"));
    employee.setPhone(request.getParameter("phone"));
    employee.setAddress(request.getParameter("address"));

    return employee;
  }
}
