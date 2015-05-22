function empHomePage() { 
 Loader.loadHTML('.header', 'resources/js/EmployeeHomepage/empHomePage.html', true, function(){
  this.handleShow();
 }.ctx(this));
}

empHomePage.prototype.handleShow = function() { 
 
 $('.empName').text(App.userName);

 this.checkUser(App.jobRole);
 
 $('[data-toggle=tab]').click(function(){
	  if ($(this).parent().hasClass('active')){
	 $($(this).attr("href")).toggleClass('active');
	  }
	})
 
 $('#hr').click(function(event){
  
  App.loadHRHomeHeader(App.userName);
  App.loadHRHomePage();
  App.loadHRHomeFooter();
 });
 
 
 
 $('#myProfile').click(function(event){
 
	App.loadempviewemployee(App.employeeId);
 });
 
 
 
 $('#holiday').click(function(event){
  
  App.loadEmployeeHoliday();
 });
 
 
 
 $('#hrpolicy').click(function(event){
  
	 App.listEmployeePolicy(); 
 });
 
 $('#Allhandsmeeting').click(function(event){
	  
	 App.loadempAllhands(); 
 });
 
 
 $('#homeEmployee').click(function(event){
	  
	 App.loadEmployeePage(App.userName,App.hr,App.isDeleted);
	    App.loadFooter();
	    App.loadEmployee(App.gender,App.contactNo,App.employeeId); 
 });
 
 
 $('#logout').click(function(event){
  this.logout();
 }.ctx(this));
  
}

empHomePage.prototype.checkUser = function(jobRole){
 
 
 if(Boolean(App.hr)&&(!Boolean(App.isDeleted)))
 $("#menudiv2").append(' <li><a href="#" id="hr" data-toggle="tab" class="tabColor">Admin</a></li>');
 return true;
}

empHomePage.prototype.logout=function(){
 var input={"payload":{}};
 RequestManager.logout(input, function(data,success){
  if(success){   
   document.location.reload();
  }
 }.ctx(this));
  
 
}

var empHomePage= new empHomePage();