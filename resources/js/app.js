function App() {

	this.handleShow();
}
App.prototype.handleShow = function () {
	 

	   
}

App.prototype.loadHoliday=function(){
	Loader.loadHoliday(function(){
		new listHoliday();
	});
}

App.prototype.loadEmployeeHoliday=function(){
	Loader.loadEmployeeHoliday(function(){
		new employeeHolidayList();
	});
}

App.prototype.listEmployee=function(){
	Loader.listEmployee(function(){
		new employeeList();
	});
}

App.prototype.listPolicy=function(){
		Loader.listPolicy(function(){
		new policyList();
	});
}
var App = new App();