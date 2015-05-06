function App() {

	this.handleShow();
}
App.prototype.handleShow = function() {
	this.userName = "";
	this.jobRole = "";
	this.contactNo = "";
	this.gender = "";
	this.employeeId = "";
}

App.prototype.loadTemplate = function() {
	Loader.loadTemplate();
}

App.prototype.loadEmployee = function(gender, contactNo) {
	this.gender = gender;
	this.contactNo = contactNo;
	Loader.loadEmployee();
}
App.prototype.loadFooter = function() {
	Loader.loadFooter(function() {
		new footer();
	});
}
App.prototype.loadEmployeePage = function(name, jobRole) {
	this.userName = name;
	this.jobRole = jobRole;
	Loader.loadEmployeePage();
}

App.prototype.loadAddTemplate = function() {
	Loader.loadCkeditor();
	Loader.loadAddTemplate(function() {
		new AddTemplate();
	});
}


App.prototype.listPolicy = function() {
	Loader.listPolicy(function() {
		new policyList();
	});
}

App.prototype.listEmployee = function() {
	Loader.listEmployee(function() {
		new employeeList();
	});
}

App.prototype.loadEmployeeHoliday = function() {
	Loader.loadEmployeeHoliday(function() {
		new employeeHolidayList();
	});
}
App.prototype.loadHoliday = function() {
	Loader.loadHoliday(function() {
		new listHoliday();
	});
}

App.prototype.loadFilter = function(cb) {
	Loader.loadFilter(function() {
		new FilterEmp(cb);
	});
}
App.prototype.loadSkill = function(cb) {
	Loader.loadSkill(function() {
		new skill(cb);
	});
}



App.prototype.loadPolicy = function() {
	Loader.loadPolicy(function() {
		new addpolicy();
	});
}

App.prototype.listEmployeePolicy = function() {
	Loader.listEmployeePolicy(function() {
		new employeePolicyList();
	});
}

App.prototype.loadNotificationHomePage = function(data) {
	Loader.loadNotificationHomePage(function() {
		new loadNotificationHomePage(data);
	});
}
App.prototype.loadManualMail = function(event, email, employeeName) {
	Loader.loadManualMail(function() {
		new sendNotificationMail(event, email, employeeName);
	});

}

App.prototype.loadEmpl = function() {
	Loader.loadEmpl(function() {
		new AddEmployee();
	});
}

App.prototype.loadhrEditEmp = function(empid) {
	Loader.loadhrEditEmp(function() {
		new HrEditEmployee(empid);
	});
}
App.prototype.loadtemplateList = function() {
	Loader.loadtemplateList(function() {
		new TemplateList();
	});
}

App.prototype.loadViewTemplate = function(dataid) {
	Loader.loadCkeditor();
	Loader.loadViewTemplate(function() {
		new editTemplate(dataid);
	});
}
App.prototype.loadAllHandsMeeting = function() {
	Loader.loadAllHandsMeeting(function() {
		new viewAllHandsMeeting();
	});
}
App.prototype.loadAllhandmeeting = function() {
	Loader.loadAllhandmeeting(function() {
		new allHandMeeting();
	});
}
App.prototype.loadAllhandmeetings = function(dataid) {
	Loader.loadAllhandmeetings(function() {
		new editHandMeeting(dataid);
	});
}

App.prototype.loadHRHomeHeader = function(name) {
	this.userName = name;
	Loader.loadHRHomeHeader();
}

App.prototype.loadHRHomePage = function() {
	Loader.loadHRHomePage(function() {
		new hRHomePage();
	});
}

App.prototype.loadHRHomeFooter = function() {
	Loader.loadHRHomeFooter(function() {
		new hRHomeFooter();
	});
}
App.prototype.loadempviewemployee = function(employeeId) {
	Loader.loadempviewemployee(function() {
		new empViewemployee(employeeId);
	});
}
App.prototype.loadEditEmp = function(employeeId) {
	Loader.loadEditEmp(function() {
		new EditEmployee(employeeId);
	});
}

App.prototype.loadDesignation=function(empid,name,doj){
	  Loader.loadDesignation(function(){
	   new editdesignation(empid,name,doj);

	  });

	 }
App.prototype.loadViewEmployee=function(empid){
	 Loader.loadViewEmployee(function(){
	  new ViewEmployee(empid);
	 });
	}


var App = new App();
