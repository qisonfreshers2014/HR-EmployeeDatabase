function Login() {	
	Loader.loadHTML('.container', 'resources/js/Login/login.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

Login.prototype.handleShow = function() {		
	$('#userName').focus();	
	$("#login").keyup(function (event) {
		if (event.keyCode == 13){
		 $("#Submit").trigger('click');
		 }	
    }.ctx(this));
	$("#Submit").click(function(event){
		 if(this.validateLogin($('input.username').val(),$('input.password').val())){		
				this.authenticate();
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
	    var minMaxLength = /^[\s\S]{8,32}$/;
	    var number = /[0-9]/;
	    var special = /[ !"#$%&'()*+,\-./:;<=>?@[\\\]^_`{|}~]/;
	    var upper = /[A-Z]/;
	    var lower = /[a-z]/;
	    if(email == "" || email == null) {
	    	$(".errormessage").show();
	    	$('.errormessage').focus();
	    	$('.errormessage').text("Email must be filled(?)");
	        isValid = false;
	    }

	    else if(!emailReg.test(email)) {
	    	$(".errormessage").show();
	    	$('.errormessage').focus();
	    	$('.errormessage').text("Enter a valid email(?)");
	        isValid = false;
	    }
	    else if (email.length > 128){
	    	$(".errormessage").show();
	    	$('.errormessage').focus();
	    	$('.errormessage').text("Email length is large(?)");
	        isValid = false;    	
	    }
	    else if(!minMaxLength.test(password) ){
	    	$(".errormessage").show();
	    	$('.errormessage').focus();
	    	$('.errormessage').text("Password is too short(?)");
	        isValid = false;
		} 
	    else if(!special.test(password) ){
	    	$(".errormessage").show();
	    	$('.errormessage').focus();
	    	$('.errormessage').text("Password has no special character(?)");
	        isValid = false;
		} 
	    else if(!number.test(password) ){
	    	$(".message").show();
	    	$('.message').focus();
	    	$('.message').text("Password has no number(?)");
	        isValid = false;
		} 
	    else if(!upper.test(password) ){
	    	$(".message").show();
	    	$('.message').focus();
	    	$('.message').text("Password has no Uppercase(?)");
	        isValid = false;
		}
	    else if(!lower.test(password) ){
	    	$(".message").show();
	    	$('.message').focus();
	    	$('.message').text("Password has no Lowercase(?)");
	        isValid = false;
		}
		/* else if(password.length < 6 ){
			$(".message").show();
			$('.message').focus();
	    	$('.message').text("password is too short(?)");
	        isValid = false;
		} 
		else if(password.trim().length  > 128)
			{
			$(".errormessage").show();
			$('.errormessage').focus();
	    	$('.errormessage').text("password is too large(?)");	
			}*/
		    else
			{
			$(".errormessage").empty();
			isValid = true;    
			}
		return isValid; 
	}

var Login= new Login();