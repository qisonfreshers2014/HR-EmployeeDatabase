function App() {

	this.handleShow();
}
App.prototype.handleShow = function () {
	 this.userName = "";
	 this.jobRole = "";
	 this.contactNo = "";
	 this.gender = "";
}

App.prototype.loadFooter=function(){
	Loader.loadFooter(function(){
	});
}

App.prototype.loadEmployee=function(gender,contactNo){
		this.gender = gender;
		this.contactNo = contactNo;
		Loader.loadEmployee();
	
}

App.prototype.loadEmployeePage=function(name,jobRole){
	this.userName = name;
	this.jobRole=jobRole;
	Loader.loadEmployeePage();
}

App.prototype.loadHoliday=function(){
	Loader.loadHoliday(function(){
		new listHoliday();
	});
}

App.prototype.loadTemplate=function(){
	Loader.loadTemplate(function(){
		new Template();
	});
}

App.prototype.loadEmployeeHoliday=function(){
	Loader.loadEmployeeHoliday(function(){
		new employeeHolidayList();
	});
}

App.prototype.listEmployees=function(){
	Loader.listEmployees(function(){
		new employeesList();
	});
}


var App = new App();