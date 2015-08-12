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
	 routie('employee');
 });
 
 
 
 $('#myProfile').click(function(event){
 
	 routie('myprofile');
 });
 
 
 
 $('#holiday').click(function(event){
  
	 routie('hrholiday');
 });
 
 
 
 $('#hrpolicy').click(function(event){
  
	 routie('hrPolicies');
 });
 
 $('#Allhandsmeeting').click(function(event){
	  
	 routie('empAllhands');
 });
 
 
 $('#homeEmployee').click(function(event){
	  
	 routie("home");
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