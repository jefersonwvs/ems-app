<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Servlet, JSP, JDBC and MVC Example</title>

  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="css/employee.css">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
  <script src="js/employee.js"></script>
  <script>
    const app = angular.module('emsApp', []);
    app.controller('employeeController', ['$scope', function($scope) {
      $scope.getEmployeeDetails = function(employeeId) {
        let employeeDetails = null;

        $.ajax({
          url: '/ems-app/get',
          type: 'POST',
          data: { 'id': employeeId },
          async: false,
          success: function(data, textStatus, jqXHR) {
            employeeDetails = data;
          },
          error: function(jqXHR, textStatus, error) {
            console.error('Error in getting employee details from server');
          }
        });

        $scope.employee = JSON.parse(employeeDetails);
        return $scope.employee;
      };
    }]);
  </script>
</head>

<body ng-app="emsApp" ng-controller="employeeController">
<div class="container">
  <div class="table-wrapper">
    <div class="table-title">
      <div class="row">
        <div class="col-sm-6">
          <h2>Manage <b>Employees</b></h2>
        </div>
        <div class="col-sm-6">
          <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal">
            <i class="material-icons">&#xE147;</i>
            <span>Add New Employee</span>
          </a>
          <a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal">
            <i class="material-icons">&#xE15C;</i>
            <span>Delete</span></a>
        </div>
      </div>
    </div>
    <table class="table table-striped table-hover">
      <thead>
      <tr>
        <th>
          <span class="custom-checkbox">
            <input type="checkbox" id="selectAll">
            <label for="selectAll"></label>
          </span>
        </th>
        <th>Name</th>
        <th>Email</th>
        <th>Address</th>
        <th>Phone</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="employee" items="${employees}">
        <tr>
          <td>
            <span class="custom-checkbox">
              <input type="checkbox" id="checkbox1" name="options[]" value="1">
              <label for="checkbox1"></label>
            </span>
          </td>
          <td>${employee.name}</td>
          <td>${employee.email}</td>
          <td>${employee.address}</td>
          <td>${employee.phone}</td>
          <td>
            <a href="#editEmployeeModal" class="edit" data-toggle="modal">
              <i class="material-icons" ng-click="getEmployeeDetails('${employee.id}')" data-toggle="tooltip"
                 title="Edit">&#xE254;</i>
            </a>
            <a href="#deleteEmployeeModal" class="delete" data-toggle="modal">
              <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>
            </a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <div class="clearfix">
      <div class="hint-text">
        Showing <b>5</b> out of <b>25</b> entries
      </div>
      <ul class="pagination">
        <li class="page-item disabled"><a href="#">Previous</a></li>
        <li class="page-item"><a href="#" class="page-link">1</a></li>
        <li class="page-item"><a href="#" class="page-link">2</a></li>
        <li class="page-item active"><a href="#" class="page-link">3</a></li>
        <li class="page-item"><a href="#" class="page-link">4</a></li>
        <li class="page-item"><a href="#" class="page-link">5</a></li>
        <li class="page-item"><a href="#" class="page-link">Next</a></li>
      </ul>
    </div>
  </div>
</div>

<!-- Add Modal HTML -->
<jsp:include page="addEmployeeView.jsp" />

<!-- Edit Modal HTML -->
<jsp:include page="updateEmployeeView.jsp" />

<!-- Delete Modal HTML -->
<div id="deleteEmployeeModal" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <form>
        <div class="modal-header">
          <h4 class="modal-title">Delete Employee</h4>
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;
          </button>
        </div>
        <div class="modal-body">
          <p>Are you sure you want to delete these Records?</p>
          <p class="text-warning">
            <small>This action cannot be undone.</small>
          </p>
        </div>
        <div class="modal-footer">
          <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel"> <input type="submit"
                                                                                                   class="btn btn-danger"
                                                                                                   value="Delete">
        </div>
      </form>
    </div>
  </div>
</div>
</body>

</html>