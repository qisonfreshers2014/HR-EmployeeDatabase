function App() {

	this.handleShow();
}
App.prototype.handleShow = function () {
	 

	   
}

App.prototype.loadViewTemplate = function(dataid){
	 Loader.loadCkeditor();
	Loader.loadViewTemplate(function(){
		new editTemplate(dataid);
	});
}
App.prototype.loadAllHandsMeeting = function(){
	Loader.loadAllHandsMeeting(function(){
		new viewAllHandsMeeting();
	});
}
App.prototype.loadAllhandmeeting = function(){
	Loader.loadAllhandmeeting(function(){
		new allHandMeeting();
	});
}
App.prototype.loadAllhandmeetings = function(dataid){
	Loader.loadAllhandmeetings(function(){
		new editHandMeeting(dataid);
	});
}




App.prototype.loadHRHomeHeader = function(name){
	Loader.loadHRHomeHeader(function(){
		new hRHomeHeader(name);
	});
}
App.prototype.loadHRHomePage = function(){
	Loader.loadHRHomePage(function(){
		new hRHomePage();
	});
}

App.prototype.loadHRHomeFooter = function(){
	Loader.loadHRHomeFooter(function(){
		new hRHomeFooter();
	});
}

App.prototype.loadEmp = function(){
	Loader.loadEmp(function(){
		new emp();
	});
}
App.prototype.loadHRPolicy = function(){
		Loader.loadHRPolicy(function(){
			new HRPolicy();
	});
}
App.prototype.loadHoliday = function(){
			Loader.loadHoliday(function(){
				new Holiday();
	});
}
App.prototype.loadTemplates = function(){
				Loader.loadTemplates(function(){
					new Templates();
	});
}
App.prototype.loadNotifications = function(){
					Loader.loadNotifications(function(){
						new Notifications();
	});
}



App.prototype.loadLogin=function(){
	Loader.loadLogin();
}

App.prototype.loadNext=function(){
	Loader.loadNext();
	
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