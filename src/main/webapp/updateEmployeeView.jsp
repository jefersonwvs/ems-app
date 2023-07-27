<div id="editEmployeeModal" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <form action="${pageContext.request.contextPath}/update" method="post">
        <div class="modal-header">
          <h4 class="modal-title">Edit Employee</h4>
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label for="input-name">Name</label>
            <input id="input-name" class="form-control" type="text" name="name" ng-model="employee.name" required>
          </div>
          <div class="form-group">
            <label for="input-email">Email</label>
            <input id="input-email" class="form-control" type="email" name="email" ng-model="employee.email" required>
          </div>
          <div class="form-group">
            <label for="input-address">Address</label>
            <textarea id="input-address" class="form-control" name="address" ng-model="employee.address" required></textarea>
          </div>
          <div class="form-group">
            <label for="input-phone">Phone</label>
            <input id="input-phone" class="form-control" type="text" name="phone" ng-model="employee.phone" required>
          </div>
        </div>
        <div class="modal-footer">
          <input type="hidden" name="id" value="{{employee.id}}"/>
          <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
          <input type="submit" class="btn btn-success" value="Add">
        </div>
      </form>
    </div>
  </div>
</div>

