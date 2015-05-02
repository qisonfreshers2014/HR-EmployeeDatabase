
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





App.prototype.loadFilter=function(cb){
	Loader.loadFilter(function(){
		new FilterEmp(cb);
	});
}



App.prototype.loadSkill=function(cb){
	Loader.loadSkill(function(){
		new skill(cb);
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


App.prototype.loadViewEmployee=function(){
	Loader.loadViewEmployee(function(){
		new ViewEmployee();
	});
}

App.prototype.loadAddTemplate = function(){
	
	Loader.loadAddTemplate(function(){
		new AddTemplate();
	});
}
App.prototype.loadempviewemployee=function(){
	Loader.loadempviewemployee(function(){
		new empViewemployee();
	});
}


App.prototype.loadEmpl=function(){
	Loader.loadEmpl(function(){
		new AddEmployee();
	});
}
App.prototype.loadEditEmp=function(dataId){
	Loader.loadEditEmp(function(){
		new EditEmployee(dataId);
	});
}

App.prototype.loadhrEditEmp=function(dataId){
	Loader.loadhrEditEmp(function(){
		new HrEditEmployee(dataId);
	});
}

App.prototype.loadtemplateList=function(){
	Loader.loadtemplateList(function(){
		new TemplateList();
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