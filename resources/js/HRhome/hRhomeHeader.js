function hRHomeHeader() {
 Loader.loadHTML('.header', 'resources/js/HRhome/hRhomeHeader.html', true, function(){
  this.handleShow();
 }.ctx(this));
}

hRHomeHeader.prototype.handleShow = function() {
	 App.listEmployee();
 $('.empName').text(App.userName);
 $('.container').show();

 $('[data-toggle=tab]').click(function(){
	  if ($(this).parent().hasClass('active')){
		$($(this).attr("href")).toggleClass('active');
	  }
	})

 $('#template').click(function(){
 App.loadtemplateList();
 }.ctx(this));

 
 $('#hrpolicy').click(function(){
  App.listPolicy();
    }.ctx(this));
 
 $('#holiday').click(function(){
  App.loadHoliday();
    }.ctx(this));
 
 $('#emp').click(function(){
  App.listEmployee();
    }.ctx(this));
 
 
 $('#notifications').click(function(){
		var input={};
		RequestManager.getAllEvents(input, function(data, success) {
			if (success) {		
			
								App.loadNotificationHomePage(data);
					}
		else
			{
			App.loadNotificationHomePage(data);
			alert("No Data Found");
					}
		
		}
		
		);
    }.ctx(this));
 
 $('#allHandsMeeting').click(function(){
  App.loadAllHandsMeeting();
    }.ctx(this));
 
 $('#logout1').click(function(event){
	  this.logout();
	 }.ctx(this));
	  
 $('#homeHr').click(function(){
	  App.loadEmployeePage(App.userName,App.hr,App.isDeleted);
	     App.loadFooter();
	     App.loadEmployee(App.gender,App.contactNo,App.employeeId); 
	  }.ctx(this));

}


hRHomeHeader.prototype.logout=function(){
	 var input={"payload":{}};
	 RequestManager.logout(input, function(data,success){
	  if(success){   
	   document.location.reload();
	  }
	 }.ctx(this));
	  
	 
	}



var hRHomeHeader=new hRHomeHeader();