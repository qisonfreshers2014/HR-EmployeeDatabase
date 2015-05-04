

function App() {

	this.handleShow();
}
App.prototype.handleShow = function () {
	 this.userName = "";
	 this.jobRole = "";
	 this.contactNo = "";
	 this.gender = "";
}

/*App.prototype.loadFooter=function(){
	Loader.loadFooter(function(){
	});
}*/



/*


App.prototype.loadEmployee=function(gender,contactNo){
		this.gender = gender;
		this.contactNo = contactNo;
		Loader.loadEmployee();
	

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
<<<<<<< HEAD


=======
=======

>>>>>>> b16aa7fae6a76e891d5b7e15ccf172b6d7ebba5c
function App() {

	this.handleShow();
}
App.prototype.handleShow = function() {
	this.userName = "";
	this.jobRole = "";
	this.contactNo = "";
	this.gender = "";
}

App.prototype.loadFooter = function() {
	Loader.loadFooter(function() {
	});
}


>>>>>>> acd1d13728a3f90530254d6552b6ddf3e1ebe2c6
App.prototype.loadEmployee=function(gender,contactNo){
		this.gender = gender;
		this.contactNo = contactNo;
		Loader.loadEmployee();
<<<<<<< HEAD

}	
App.prototype.loadViewTemplate = function(name){
	 Loader.loadCkeditor();
		
}
App.prototype.loadViewTemplate = function(dataid){
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
=======
	
}

	App.prototype.loadViewTemplate = function(name) {
		Loader.loadViewTemplate(function() {
			new EditTemplate();
		});
	}
	App.prototype.loadAllHandsMeeting = function(name) {
		Loader.loadAllHandsMeeting(function() {
			new AllHandsMeeting();
		});
	}

	App.prototype.loadFilter = function(cb) {
		Loader.loadFilter(function() {
			new FilterEmp(cb);
		});
	}

	App.prototype.loadHRHomeHeader = function(name) {
		Loader.loadHRHomeHeader(function() {
			new HRHomeHeader(name);
		});
	}
	App.prototype.loadHRHomePage = function() {
		Loader.loadHRHomePage(function() {
			new HRHomePage();
		});
	}

	App.prototype.loadSkill = function(cb) {
		Loader.loadSkill(function() {
			new skill(cb);
		});
	}
	App.prototype.loadHRHomeFooter = function() {
		Loader.loadHRHomeFooter(function() {
			new HRHomeFooter();
		});
	}

	App.prototype.loadEmp = function() {
		Loader.loadEmp(function() {
			new Emp();
		});
	}
	App.prototype.loadHRPolicy = function() {
		Loader.loadHRPolicy(function() {
			new HRPolicy();
		});
	}
	App.prototype.loadHoliday = function() {
		Loader.loadHoliday(function() {
			new Holiday();
		});
	}
	App.prototype.loadTemplates = function() {
		Loader.loadTemplates(function() {
			new Templates();
		});
	}
	App.prototype.loadNotifications = function() {
		Loader.loadNotifications(function() {
			new Notifications();
		});
	}
	App.prototype.loadAddTemplate = function(){
		Loader.loadCkeditor();
	Loader.loadAddTemplate(function(){
		new AddTemplate();
			});
		}
	
	App.prototype.loadViewEmployee = function() {
		Loader.loadViewEmployee(function() {
			new ViewEmployee();
		});
	}
	
	App.prototype.loadempviewemployee = function() {
		Loader.loadempviewemployee(function() {
			new empViewemployee();
		});
	}
	var App = new App();

/*
App.prototype.loadLogin=function(){
	Loader.loadLogin();
}

	App.prototype.loadEmployeePage = function(name, jobRole) {
		this.userName = name;
		this.jobRole = jobRole;
		Loader.loadEmployeePage();
	}

	App.prototype.loadHoliday = function() {
		Loader.loadHoliday(function() {
			new listHoliday();
		});
	}

	App.prototype.loadTemplate = function() {
		Loader.loadTemplate(function() {
			new Template();
		});
	}

	App.prototype.loadEmployeeHoliday = function() {
		Loader.loadEmployeeHoliday(function() {
			new employeeHolidayList();
		});
	}

	App.prototype.listEmployee = function() {
		Loader.listEmployee(function() {
			new employeeList();
		});
	}

	App.prototype.listPolicy = function() {
		Loader.listPolicy(function() {
			new policyList();
		});
	}
	App.prototype.listEmployees = function() {
		Loader.listEmployees(function() {
			new employeesList();
		});
	}

	App.prototype.loadHRPolicyVeiwPage = function() {
		Loader.loadHRPolicyVeiwPage(function() {
			new policyList();
		});
	}


	
	

	App.prototype.loadEmpl = function() {
		Loader.loadEmpl(function() {
			new AddEmployee();
		});
	}
	App.prototype.loadEditEmp = function() {
		Loader.loadEditEmp(function() {
			new EditEmployee();
		});
	}

	App.prototype.loadhrEditEmp = function() {
		Loader.loadhrEditEmp(function() {
			new HrEditEmployee();
		});
	}

	App.prototype.loadtemplateList = function() {
		Loader.loadtemplateList(function() {
			new TemplateList();
		});
	}

	App.prototype.loadDes = function() {
		Loader.loadDes(function() {
			new EmpDes();

		});

App.prototype.loadAddTemplate = function(){
	Loader.loadCkeditor();
Loader.loadAddTemplate(function(){
	new AddTemplate();
		});
	}

	App.prototype.loadPolicy = function() {
		Loader.loadPolicy(function() {
			new addpolicy();
		});
	}

App.prototype.loadempviewemployee=function(){
	Loader.loadempviewemployee(function(){
		new empViewemployee();
	});
}
	App.prototype.loadNotifArea = function() {
		Loader.loadNotifArea(function() {
			new NotificationALinkrea();
		});
	}

	App.prototype.loadManualMail = function(event, email, employeeName) {
		// Loader.loadCkeditor();
		Loader.loadManualMail(function() {
			new sendNotificationMail(event, email, employeeName);
		});

	}










App.prototype.loadHRPolicyVeiwPage = function(){
	Loader.loadHRPolicyVeiwPage(function(){
		new policyList();
>>>>>>> acd1d13728a3f90530254d6552b6ddf3e1ebe2c6
	});
}








App.prototype.loadViewEmployee=function(){
	Loader.loadViewEmployee(function(){
		new ViewEmployee();
	});
}

<<<<<<< HEAD

App.prototype.loadHRHomeHeader = function(name){
	Loader.loadHRHomeHeader(function(){
		new hRHomeHeader(name);
=======
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
App.prototype.loadEditEmp=function(){
	Loader.loadEditEmp(function(){
		new EditEmployee();
	});
}

App.prototype.loadhrEditEmp=function(){
	Loader.loadhrEditEmp(function(){
		new HrEditEmployee();
>>>>>>> acd1d13728a3f90530254d6552b6ddf3e1ebe2c6
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
<<<<<<< HEAD
=======


<<<<<<< HEAD
		Loader.loadPolicy(function(){
			new addpolicy();
	App.prototype.loadHrPolicyVeiwPage = function() {
		Loader.loadHrPolicyVeiwPage(function() {
			new policyList();
		});
	}

}
*/
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
/*App.prototype.loadHoliday = function(){
			Loader.loadHoliday(function(){
				new Holiday();
	});
}
App.prototype.loadTemplates = function(){
				Loader.loadTemplates(function(){
					new Templates();
	});
}*/
App.prototype.loadNotifications = function(){
	Loader.loadNotifications(function(){
		new Notifications();
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


/*
=======


>>>>>>> origin/master
App.prototype.loadLogin=function(){
	Loader.loadLogin();
}

<<<<<<< HEAD
App.prototype.loadEmployeePage=function(name,jobRole){
	this.userName = name;
	this.jobRole=jobRole;
	Loader.loadEmployeePage();
=======
App.prototype.loadNext=function(){
	Loader.loadNext();
	
>>>>>>> origin/master
}

App.prototype.loadHoliday=function(){
	Loader.loadHoliday(function(){
		new listHoliday();
	});
<<<<<<< HEAD

}
App.prototype.loadHRPolicyVeiwPage = function(){
	Loader.loadHRPolicyVeiwPage(function(){
		new policyList();
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
App.prototype.loadEditEmp=function(){
	Loader.loadEditEmp(function(){
		new EditEmployee();
	});
}

App.prototype.loadhrEditEmp=function(){
	Loader.loadhrEditEmp(function(){
		new HrEditEmployee();
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

	

=======
}
<<<<<<< HEAD

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
<<<<<<< HEAD


*/
/*


















App.prototype.loadHRPolicyVeiwPage = function(){
	Loader.loadHRPolicyVeiwPage(function(){
		new policyList();
	});
}
>>>>>>> b16aa7fae6a76e891d5b7e15ccf172b6d7ebba5c

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



	App.prototype.loadPolicy=function(){
			Loader.loadPolicy(function(){
				new addpolicy();
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




App.prototype.loadNotifArea = function(){
	Loader.loadNotifArea(function(){
		new NotificationALinkrea();
	});		
}
	
	

		
}*/

	App.prototype.loadDesignation=function(){
		Loader.loadDesignation(function(){
			new editdesignation();

		});

	}

	App.prototype.loadPolicy=function(){
			Loader.loadPolicy(function(){
				new addpolicy();
			});
	}
	App.prototype.loadNotificationHomePage = function(data) {
		Loader.loadNotificationHomePage(function() {
			new loadNotificationHomePage(data);
		});
	}
	App.prototype.loadManualMail = function(event,email,employeeName){
		Loader.loadManualMail(function(){
			new sendNotificationMail(event,email,employeeName);
		});
		
	}
var App = new App();

