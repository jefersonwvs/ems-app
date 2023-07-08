package com.jefersonwvs.app.crud.controller;

import com.jefersonwvs.app.crud.beans.Employee;
import com.jefersonwvs.app.crud.dao.EmployeeDAO;
import com.jefersonwvs.app.crud.dao.EmployeeDAOImpl;
import java.io.IOException;
import java.util.Optional;
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
      case "/add":
        addNewEmployee(request, response);
        break;
      case "/get":
        getEmployee(request, response);
        break;
      case "/list":
        getAllEmployees(request, response);
        break;
      case "/update":
        updateEmployee(request, response);
        break;
      case "/delete":
        deleteEmployee(request, response);
        break;
      default:
        getAllEmployees(request, response);
    }
  }

  private void addNewEmployee(HttpServletRequest request, HttpServletResponse response) {
    Employee newEmployee = EmployeeController.getEmployeeFromRequest(request);
    System.out.println("addNewEmployee method invoked, employee details ==> " + newEmployee);
    boolean result = employeeDAO.addEmployee(newEmployee);
    System.out.println(
        "Employee \"" + newEmployee.getName() + "\" was " + (!result ? "not " : "") + "added");

    RequestDispatcher dispatcher = request.getRequestDispatcher("/employeesView.jsp");
    try {
      dispatcher.forward(request, response);
    } catch (ServletException | IOException e) {
      e.printStackTrace();
    }
  }

  private void getEmployee(HttpServletRequest request, HttpServletResponse response) {}

  private void getAllEmployees(HttpServletRequest request, HttpServletResponse response) {}

  private void updateEmployee(HttpServletRequest request, HttpServletResponse response) {}

  private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {}

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
