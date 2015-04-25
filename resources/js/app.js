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

App.prototype.listEmployees=function(){
	Loader.listEmployees(function(){
		new employeesList();
	});
}


var App = new App();