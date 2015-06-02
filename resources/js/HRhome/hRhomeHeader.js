function hRHomeHeader() {
 Loader.loadHTML('.header', 'resources/js/HRhome/hRhomeHeader.html', true, function(){
  this.handleShow();
 }.ctx(this));
}

hRHomeHeader.prototype.handleShow = function() {
	
 $('.empName').text(App.userName);
 $('.container').show();

 $('[data-toggle=tab]').click(function(){
	  if ($(this).parent().hasClass('active')){
		$($(this).attr("href")).toggleClass('active');
	  }
	})

 $('#template').click(function(){
routie("template");
 }.ctx(this));

 
 $('#hrpolicy').click(function(){
	 routie("hrpolicy");
    }.ctx(this));
 
 $('#holiday').click(function(){
	 routie("holiday");
    }.ctx(this));
 
 $('#emp').click(function(){
	 routie("employee");
    }.ctx(this));
 
 
 $('#notifications').click(function(){
	 routie("notifications");
    }.ctx(this));
 
 $('#allHandsMeeting').click(function(){
	 routie("allHandsMeeting");
    }.ctx(this));
 
 $('#logout1').click(function(event){
	  this.logout();
	 }.ctx(this));
	  
 $('#homeHr').click(function(){
	 App.loadEmployeePage(App.userName,App.hr,App.isDeleted);
	routie("home");
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