function Loader() {
	this.handleShow();
}

Loader.prototype.handleShow = function() {

}

Loader.prototype.loadHTML = function(container, filePath, empty, callback) {
	var filePath = filePath;
	$.ajax({
		url : filePath,
		success : function(data) {
			if (empty) {
				$(container).empty();
			}
			$(container).append(data);

			if (callback) {
				callback();
				// console.log(container)
				// $(container).msgkey();
			}

			
			/*
			 * var compiledTemplate = Ember.Handlebars.compile(data);
			 * Ember.View.create({ template: compiledTemplate
			 * }).appendTo(container);
			 */
		}
	});


}



Loader.prototype.loadManualMail = function(cb) {
	LazyLoad.css('resources/js/SendNotification/sendNotification.css');
	LazyLoad.js('resources/js/SendNotification/sendNotificationMail.js', cb);
}

Loader.prototype.loadNotificationHomePage = function(cb) {
	LazyLoad.css('resources/js/SendNotification/notificationHomePage.css');
	LazyLoad.js('resources/js/SendNotification/notificationHomePage.js', cb);
}



Loader.prototype.loadEmployeeHoliday = function(){
 LazyLoad.css('resources/js/Holidays/holidays.css'); 
 LazyLoad.js('resources/js/EmployeeHolidayList/employeeHolidayList.js');

}

Loader.prototype.listEmployee = function(){
 LazyLoad.css('resources/js/Holidays/holidays.css');
 LazyLoad.js('resources/js/EmployeeList/employeeList.js');

}
Loader.prototype.loadHoliday = function(){
  LazyLoad.css('resources/js/Holidays/holidays.css');
  LazyLoad.js('resources/js/Holidays/holidays.js');
}
 Loader.prototype.listPolicy = function(cb){
 LazyLoad.css('resources/js/Holidays/holidays.css');
 LazyLoad.js('resources/js/addinghrpolicy/policy.js', cb);
}

Loader.prototype.loadCkeditor = function(){
  LazyLoad.js('resources/js/ckeditor/ckeditor.js');
 }

Loader.prototype.loadAddTemplate = function(cb){ 
 LazyLoad.css('resources/css/addtemp&viewEmp.css');
 //LazyLoad.css('resources/js/Template/addtemplate.css');
 LazyLoad.js('resources/js/Template/addtemplate.js', cb);
 
}

Loader.prototype.loadViewEmployee = function(cb) {
 LazyLoad.css('resources/css/addtemp&viewEmp.css');
 //LazyLoad.css('resources/css/viewEmployee.css');
 LazyLoad.js('resources/js/viewEmployee.js', cb);
}
 Loader.prototype.loadempviewemployee = function(cb) {
     LazyLoad.css('resources/css/addtemp&viewEmp.css');
 //LazyLoad.css('resources/css/empviewemployee.css');
 LazyLoad.js('resources/js/empViewemployee.js', cb);
}

Loader.prototype.loadViewTemplate = function (cb) {
 
 LazyLoad.css('resources/js/AllCss/allHand&editTemplate&home.css');
  LazyLoad.js('resources/js/Template/editTemplate.js', cb);
 }
 
Loader.prototype.loadAllHandsMeeting = function (cb) {
 
 LazyLoad.css('resources/js/AllCss/allHand&editTemplate&home.css');
  LazyLoad.js('resources/js/AllHandsMeeting/viewAllHandsmeeting.js', cb);
 }
Loader.prototype.loadAllhandmeeting = function (cb) {
  LazyLoad.css('resources/js/AllCss/allHand&editTemplate&home.css');
  LazyLoad.js('resources/js/AllHandsMeeting/allHandMeeting.js', cb);
 }
Loader.prototype.loadempAllhands=function(cb){
	 LazyLoad.css('resources/js/AllCss/allHand&editTemplate&home.css');
	 LazyLoad.js('resources/js/AllHandsMeeting/empAllHands.js', cb);
}
Loader.prototype.loadAllhandmeetings = function (cb) {

 LazyLoad.css('resources/js/AllCss/allHand&editTemplate&home.css');
  LazyLoad.js('resources/js/AllHandsMeeting/editHandMeeting.js', cb);
 }




Loader.prototype.loadHRHomeHeader = function (cb) {

 LazyLoad.css('resources/js/AllCss/allHand&editTemplate&home.css');
  LazyLoad.js('resources/js/HRhome/hRhomeHeader.js', cb);
 }

 

Loader.prototype.loadHRHomePage = function (cb) {

 LazyLoad.css('resources/js/AllCss/allHand&editTemplate&home.css');
  LazyLoad.js('resources/js/HRhome/hRhomepage.js', cb);
 }
Loader.prototype.loadHRHomeFooter = function (cb) {
 
 LazyLoad.css('resources/js/AllCss/allHand&editTemplate&home.css');
  LazyLoad.js('resources/js/HRhome/hRhomeFooter.js', cb);
 }


Loader.prototype.loadPolicy = function(cb){
LazyLoad.css('resources/js/addinghrpolicy/addpolicy.css');
LazyLoad.js('resources/js/addinghrpolicy/addpolicy.js', cb);
}

Loader.prototype.listEmployeePolicy = function(){
    LazyLoad.css('resources/js/Holidays/holidays.css');
    LazyLoad.js('resources/js/addinghrpolicy/employeeHRPolicy.js');
   }

Loader.prototype.loadFooter = function(){
  LazyLoad.css('resources/js/EmployeeHomepage/footer.css');
  LazyLoad.js('resources/js/EmployeeHomepage/footer.js');

}
 Loader.prototype.loadEmployeePage = function(){
  LazyLoad.css('resources/js/EmployeeHomepage/empHomePage.css');
  LazyLoad.js('resources/js/EmployeeHomepage/empHomePage.js');
}
Loader.prototype.loadEmployee = function(){
  LazyLoad.css('resources/js/EmployeeHomepage/employee.css');
  LazyLoad.js('resources/js/EmployeeHomepage/employee.js');

}

Loader.prototype.loadTemplate = function(){
   LazyLoad.css('resources/js/Templates/template.css');
   LazyLoad.js('resources/js/Templates/template.js');
  }

Loader.prototype.loadFilter = function(cb){
  LazyLoad.css('resources/js/skill/Skill.css');
  LazyLoad.js('resources/js/FilterEmployee/Filter.js',cb);
}
Loader.prototype.loadSkill = function(cb){
  LazyLoad.css('resources/js/skill/Skill.css');
  LazyLoad.js('resources/js/skill/Skill.js',cb);
}


Loader.prototype.loadEditEmp = function(dataId){
	LazyLoad.js('resources/js/employee/editemp.js', dataId);
	LazyLoad.css('resources/js/employee/addemp.css');
}

Loader.prototype.loadhrEditEmp = function(dataId){
	LazyLoad.js('resources/js/employee/hreditemp.js', dataId);
	LazyLoad.css('resources/js/employee/addemp.css');
}

Loader.prototype.loadtemplateList = function(g){
	LazyLoad.js('resources/js/employee/templateslist.js', g);
	LazyLoad.css('resources/js/employee/addemp.css');
}
Loader.prototype.loadEmpl = function(e){
	 LazyLoad.js('resources/js/employee/addemp.js',e);
	 LazyLoad.css('resources/js/employee/addemp.css');

}
Loader.prototype.loadDesignation = function(cb1,cb2,cb3){
	  LazyLoad.css('resources/js/editdesignation/editdesignation.css');
	  LazyLoad.js('resources/js/editdesignation/editdesignation.js',cb1,cb2,cb3);
	}
Loader.prototype.loadViewEmployee = function(cb) {
	 LazyLoad.css('resources/css/addtemp&viewEmp.css');
	 LazyLoad.js('resources/js/viewEmployee.js', cb);
	}
Loader.prototype.loadchangePassword = function(dataId){
	LazyLoad.js('resources/js/employee/changePassword.js', dataId);
	LazyLoad.css('resources/js/employee/addemp.css');
}

var Loader = new Loader();