function App() {
	this.userName = "";
	this.jobRole = "";
	this.contactNo = "";
	this.gender = "";
	this.employeeId = "";
	 this.hr = "";
	this.isDeleted = "";
	this.handleShow();
}
App.prototype.handleShow = function() { 
	
	 var input={}
		RequestManager.getLoggedInUserDetails(input,function(data,success){
			if(success){
				var obj=data;
				
			var employeeId=obj.employeeId;	
			var	name=obj.employeeName;
			var designation=obj.currentDesignation;
			var isDeleted=obj.deleted;
			var contactNo=obj.contactNo;
			var gender=obj.gender;
			var hr;
			if(designation==5){
				hr=true;
			}else{
				hr=false;
			}
			
			App.loadRouter(name, hr,isDeleted,gender, contactNo,employeeId,function(){
				new Router();
			});
			}else{
				
				new Login();
			}
			
		}.ctx(this));
    //this.routing();
}

App.prototype.routing=function(){
	
	 var input={};
	 
    RequestManager.isLoggedin(input, function(data,success){
  	  if(success){
  	   new Router();
  	  }  
  	 }.ctx(this));
}


App.prototype.loadTemplate = function(dataid) {
	 Loader.loadTemplate();
	}
App.prototype.loadempviewemployee=function(employeeId){
	 Loader.loadempviewemployee(function(){
	  new empViewemployee(employeeId);
	 });
	 }
App.prototype.loadEmployee = function(gender, contactNo,employeeId) {
	this.gender = gender;
	this.contactNo = contactNo;
	this.employeeId=employeeId;
	Loader.loadEmployee();
}
App.prototype.loadFooter = function() {
	Loader.loadFooter(function() {
		new footer();
	});
}
App.prototype.loadEmployeePage = function(name, hr,isDeleted) {
	 this.userName = name;
	 this.hr = hr;
	 this.isDeleted=isDeleted;
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

App.prototype.listEmployee = function(data) {
	
	Loader.loadPagination();
	
	Loader.listEmployee(function() {
		new employeeList(data);
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
App.prototype.loadSkill = function(empid,hr) {
	Loader.loadSkill(function() {
		new skill(empid,hr);
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
App.prototype.loadAllHandsMeeting = function(data) {
	
	Loader.loadPagination();
	Loader.loadAllHandsMeeting(function() {
		new viewAllHandsMeeting(data);
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
App.prototype.loadempAllhands= function() {
	Loader.loadempAllhands(function() {
		new empAllHands();
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

App.prototype.loadchangePassword = function(employeeId) {
	Loader.loadchangePassword(function() {
		new changePassword(employeeId);
	});
}
App.prototype.loadRouter= function(name, hr,isDeleted,gender, contactNo,employeeId,callback) {
	 this.userName = name;
	 this.hr = hr;
	 this.isDeleted=isDeleted;
	 this.gender = gender;
	 this.contactNo = contactNo;
	 this.employeeId=employeeId;
	 callback();
	}

var App = new App();
