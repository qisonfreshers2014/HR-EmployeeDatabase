function Login() {	
	Loader.loadHTML('.container', 'resources/js/Login/login.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

Login.prototype.handleShow = function() {		
	$('#userName').focus();	
	$("#Submit").click(function (event) {
		//if (event.keyCode == 13){
		//	 $("#Submit").click();
			  if(this.validateLogin($('input.username').val(),$('input.password').val())){		
					this.authenticate();
		//	  }		
		  }
		 }.ctx(this));
}
	Login.prototype.authenticate = function() {	
		
		var input = {"payload":{"authType":"REGULAR",
								"email":$('.username').val(),
								"password":$('.password').val()
								}};
		
		RequestManager.authenticate(input, function(data, success) {
			if(success){
				var  token = data.sessionToken;
			      setCookie('hredSessionToken', token, null);
				console.log("************************");
			//	console.dir(data);
				//if(data.employee.currentDesignation=="2")
				//{
			
				var name=data.employee.employeeName;
			    var jobRole=data.employee.currentDesignation;
			    var gender=data.employee.gender;
			    var contactNo=data.employee.contactNo;
				App.loadEmployeePage(name,jobRole);
				App.loadFooter();
				App.loadEmployee(gender,contactNo);
				//App.loadTemplate();
			}
		}.ctx(this));
	}
	
	Login.prototype.validateLogin = function(email,password){
		$(".message").hide();
		console.log(email+" validation "+password);
	    var isValid = false;
	    var emailReg = /^[_A-Za-z]+[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$/;
	  //  var emailVal = email;
	    if(email == "" || email == null) {
	    	$(".message").show();
	    	$('.message').focus();
	    	$('.message').text("email must be filled(?)");
	        isValid = false;
	    }

	    else if(!emailReg.test(email)) {
	    	$(".message").show();
	    	$('.message').focus();
	    	$('.message').text("enter a valid email(?)");
	        isValid = false;
	    }
	    else if (email.length > 128){
	    	$(".message").show();
	    	$('.message').focus();
	    	$('.message').text("email length is large(?)");
	        isValid = false;    	
	    }
	    else if(password.length < 1 ){
	    	$(".message").show();
	    	$('.message').focus();
	    	$('.message').text("password cant be empty(?)");
	        isValid = false;
		} 
		 else if(password.length < 6 ){
			$(".message").show();
			$('.message').focus();
	    	$('.message').text("password is too short(?)");
	        isValid = false;
		} 
		else if(password.trim().length  > 128)
			{
			$(".message").show();
			$('.message').focus();
	    	$('.message').text("password is too large(?)");	
			}
		    else
			{
			$(".message").empty();
			isValid = true;    
			}
		return isValid; 
	}

var Login= new Login();