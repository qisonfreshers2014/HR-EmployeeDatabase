 function empHomePage() { 
 Loader.loadHTML('.container', 'resources/js/EmployeeHomepage/employee.html', true, function(){
  this.handleShow();
 }.ctx(this));
}

empHomePage.prototype.handleShow= function(){
 
 $('.name').text(App.userName);
 $('.gender').text(App.gender);
 $('.mobile').text(App.contactNo);

 
}

var empHomePage=new empHomePage();
