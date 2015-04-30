function empHomePage() {	
	Loader.loadHTML('.header', 'resources/js/EmployeeHomepage/empHomePage.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

empHomePage.prototype.handleShow = function() {	
	
	$('.empName').text(App.userName);

	this.checkUser(App.jobRole);
	
	
	$('.hr').click(function(event){
		
		App.loadHrHomepage();
	});
	
	
	
	$('#emp').click(function(event){
		
		App.loadMyProfile();
	});
	
	
	
	$('#holiday').click(function(event){
		
		App.loadHoliday();
	});
	
	
	
	$('#hrpolicy').click(function(event){
		
		App.loadhrpolicy();
	});
	
	
	$('#logout').click(function(event){
		this.logout();
	}.ctx(this));
		
}

empHomePage.prototype.checkUser = function(jobRole){
 
	$("#hr").hide();
	
	if(jobRole == "1")
	$("#hr").show();
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