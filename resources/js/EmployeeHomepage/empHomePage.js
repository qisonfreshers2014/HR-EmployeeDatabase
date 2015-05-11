function empHomePage() { 
 Loader.loadHTML('.header', 'resources/js/EmployeeHomepage/empHomePage.html', true, function(){
  this.handleShow();
 }.ctx(this));
}

empHomePage.prototype.handleShow = function() { 
 
 $('.empName').text(App.userName);

 this.checkUser(App.jobRole);
 
 
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
 
 
 $('#logout').click(function(event){
  this.logout();
 }.ctx(this));
  
}

empHomePage.prototype.checkUser = function(jobRole){
 
 
 if(Boolean(App.hr)&&(!Boolean(App.isDeleted)))
 $("#menudiv1").append(' <a href="#" id="hr" class="tabColor">Admin</a>');
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