function App() {

	this.handleShow();
}
App.prototype.handleShow = function () {
	 

	   
}

App.prototype.loadViewTemplate = function(name){
	Loader.loadViewTemplate(function(){
		new EditTemplate();
	});
}
App.prototype.loadAllHandsMeeting = function(name){
	Loader.loadAllHandsMeeting(function(){
		new AllHandsMeeting();
	});
}

App.prototype.loadFilter=function(cb){
	Loader.loadFilter(function(){
		new FilterEmp(cb);
	});
}

App.prototype.loadHRHomeHeader = function(name){
	Loader.loadHRHomeHeader(function(){
		new HRHomeHeader(name);
	});
}
App.prototype.loadHRHomePage = function(){
	Loader.loadHRHomePage(function(){
		new HRHomePage();
	});
}

App.prototype.loadSkill=function(cb){
	Loader.loadSkill(function(){
		new skill(cb);
	});
}
App.prototype.loadHRHomeFooter = function(){
	Loader.loadHRHomeFooter(function(){
		new HRHomeFooter();
	});
}

App.prototype.loadEmp = function(){
	Loader.loadEmp(function(){
		new Emp();
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




































App.prototype.loadDes=function(){
	Loader.loadDes(function(){
		new EmpDes();

	});

}

App.prototype.loadPolicy=function(){
		Loader.loadPolicy(function(){
			new addpolicy();
		});
}


App.prototype.loadNotifArea = function(){
	Loader.loadNotifArea(function(){
		new NotificationALinkrea();
	});		
}
	
	
App.prototype.loadManualMail = function(event,email,employeeName){
	Loader.loadManualMail(function(){
		new sendNotificationMail(event,email,employeeName);
	});
	
}
	App.prototype.loadNotificationHomePage = function(data){
		Loader.loadNotificationHomePage(function(){
			new loadNotificationHomePage(data);
		});
		
}
var App = new App();