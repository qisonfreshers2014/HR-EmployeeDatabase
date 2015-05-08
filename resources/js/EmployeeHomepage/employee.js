function empBasicDetails() { 
 Loader.loadHTML('.container', 'resources/js/EmployeeHomepage/employee.html', true, function(){
  this.handleShow();
 }.ctx(this));
}

 empBasicDetails.prototype.handleShow= function(){
 
 $('.name').text(App.userName);
 $('.gender').text(App.gender);
 $('.mobile').text(App.contactNo);

 
}

var empBasicDetails=new empBasicDetails();