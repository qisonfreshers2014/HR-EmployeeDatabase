function EditEmployee(employeeId) {
	Loader.loadHTML(".container", "resources/js/employee/EditEmp.html", true,
			function() {
				this.handleShow(employeeId);
			}.ctx(this));
}
EditEmployee.prototype.handleShow = function(employeeId) {

	$('.container').show();
	this.validateEditEmp(employeeId);
	$('#updateemp').click(function(e) {
		e.preventDefault();
		this.validateUpdateEmp(employeeId);
	}.ctx(this));
	$('#backtoprofile').click(function() {

		routie("myprofile");
	}.ctx(this));
}
EditEmployee.prototype.validateEditEmp = function(employeeId) {

	$('.error').css('visibility', 'hidden');
	var input = {
		"payload" : {
			"employeeId" : employeeId
		}
	};
	RequestManager.geteditEmployee(input, function(data, success) {
		if (success) {
			var object = data;
			
			$("#contnum").val(object.contactNo);
			$("#emal").val(object.email);
			// $("#password").val(object.password);
			$("#txtemercon").val(object.emergencycontactnumber);
			$("#txtemname").val(object.emergencyContactName);
			$("#relation").val(object.relationWithEmergencyConatact);
			$("#currentaddr").val(object.currentAddress);
			$("#peraddr").val(object.permanentAddress);
			$("#skype").val(object.skype);
			$("#hobbies").val(object.hobbies);

		} else {
			console.dir(data);
			// alert("failed to edit");
		}

	}.ctx(this));

}

var char = /^[A-Za-z .]+( [A-Za-z]+)*$/;
var qual = /^[a-zA-Z.""]+$/;
var num = /^[0-9]+$/;
var mail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
var skp = /^[A-Za-z0-9]+(.[A-Za-z0-9]+)*$/;

var illegalChars = /\W/
var letters = /^[0-9a-zA-Z]+$/;
EditEmployee.prototype.validateUpdateEmp = function(employeeId) {

	var cnumerr = $("#cnumerr");
	var contnum = $("#contnum").val();
	var emlerr = $("#emlerr");
	var email = $("#emal").val();
	var pwepattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}/;
	var pwderr = $("#pwderr");
	var password = $("#password").val();
	var emnumerr = $("#emnumerr");
	var txtemercon = $("#txtemercon").val();
	var txtemname = $("#txtemname").val();
	var emnameerr = $("#emnameerr");
	var relationerr = $("#relationerr");
	var relation = $("#relation").val();
	var currentaddrerr = $("#currentaddrerr");
	var currentaddr = $("#currentaddr").val();
	var peraddrerr = $("#peraddrerr");
	var peraddr = $("#peraddr").val();
	var skype = $('#skype').val();
	var hobbies = $("#hobbies").val();
	var skypeerr = $('#skypeerr');
	var flag = true;

	$('.error').css('visibility', 'visible');

	if (!flag == this.employeeEmergencyContactNameValidate(flag)
			& !flag == this.employeeContactNumberValidate(flag)
			& !flag == this.employeeCurrentAddressValidate(flag)
			& !flag == this.employeePerminentAddressValidate(flag)
			& !flag == this.employeeNonMandatoryFieldsValidation(flag)) {

		var input = {
			"payload" : {
				"employeeId" : employeeId,
				"contactNo" : contnum,
				"currentAddress" : currentaddr,
				"permanentAddress" : peraddr,
				"email" : email,
				// "password" : password,
				"emergencycontactnumber" : txtemercon,
				"emergencyContactName" : txtemname,
				"relationWithEmergencyConatact" : relation,
				"skype" : skype,
				"hobbies" : hobbies
			}
		};
		RequestManager.updateEmp(input, function(data, success) {
			if (success) {
				console.dir(data);
				alert("Employee Details Successfully Updated");
				routie("myprofile");
			} else {
				alert("failed to Update the Details");
			}
		}.ctx(this));

	}
}
EditEmployee.prototype.employeeContactNumberValidate = function(flag) {
	var cnumerr = $("#cnumerr");
	var contnum = $("#contnum").val();
	if (contnum == "") {
		$(cnumerr).text("Required field");
		$(cnumerr).css("color", "red");
	} else if (contnum == isNaN || !(contnum.match(num))) {
		$(cnumerr).text("Please enter numbers only");
		$(cnumerr).css("color", "red");
	} else if (!(contnum.length == 10)) {
		$(cnumerr).text("Please enter 10 digits only");
		$(cnumerr).css("color", "red");
	} else {
		$(cnumerr).text("");
		flag = false;

		// $(cnumerr).css("color", "green");

	}
	return flag;
}
EditEmployee.prototype.employeeEmergencyContactNameValidate = function(flag) {
	var txtemname = $("#txtemname").val();
	var emnameerr = $("#emnameerr");
	if (txtemname == "") {
		$(emnameerr).text("Required field");
		$(emnameerr).css("color", "red");
	} else if (txtemname.charAt(0) == " ") {
		$(emnameerr).text("First letter shouldn't be space");
		$(emnameerr).css("color", "red");
	} else if (!(txtemname.match(char) || txtemname == isNaN)) {
		$(emnameerr).text("Enter characters only");
		$(emnameerr).css("color", "red");
	} else if (txtemname.length < 2) {
		$(emnameerr).text("Please enter min 3 characters");
		$(emnameerr).css("color", "red");
	} else {
		$(emnameerr).text("");
		flag = false;

		// $(emnameerr).css("color", "green");

	}
	return flag;

}
EditEmployee.prototype.employeeEmergencyContactNumberValidate = function(flag) {
	var txtemercon = $("#txtemercon").val();
	var emnumerr = $("#emnumerr");
	if (txtemercon == "") {
		$(emnumerr).text("Required field");
		$(emnumerr).css("color", "red");
	} else if (txtemercon == isNaN || !(txtemercon.match(num))) {
		$(emnumerr).text("Only numbers allowed");
		$(emnumerr).css("color", "red");
	} else if (!(txtemercon.length == 10)) {
		$(emnumerr).text("Please enter 10 digits only");
		$(emnumerr).css("color", "red");
	} else {
		$(emnumerr).text("");
		flag = false;

		// $(emnumerr).css("color", "green");

	}
	return flag;
}
EditEmployee.prototype.employeePerminentAddressValidate = function(flag) {
	var peraddrerr = $("#peraddrerr");
	var peraddr = $("#peraddr").val();
	if (peraddr == "") {
		$('#peraddrerr').text("Required field");
		$('#peraddrerr').css("color", "red");
	} else {
		$('#peraddrerr').text("");
		flag = false;

		// $(peraddrerr).css("color", "green");

	}
	return flag;

}
EditEmployee.prototype.employeeCurrentAddressValidate = function(flag) {
	var currentaddrerr = $("#currentaddrerr");
	var currentaddr = $("#currentaddr").val();
	if (currentaddr == "") {
		$(currentaddrerr).text("Required field");
		$(currentaddrerr).css("color", "red");
	} else {
		$(currentaddrerr).text("");
		flag = false;

		// $(currentaddrerr).css("color", "green");

	}
	return flag;

}

/*EditEmployee.prototype.employeeHobbiesValidate = function(flag) {
	var hobbies=$('#hobbies').val();
	var hobbieserr=$('#hobbieserr')
	if(hobbies==""){
		$(hobbieser).text("");
		flag=false;
	}else{
		
		flag=false;
	}

}*/
EditEmployee.prototype.employeeNonMandatoryFieldsValidation = function(flag) {

	var skype = $('#skype').val();
	var skypeerr = $('#skypeerr');
	var relationerr = $("#relationerr");
	var relation = $("#relation").val();
	if (skype == "") {
		$(skypeerr).text("");
		flag = false;

	} else if (!(skype.match(skp))) {
		$(skypeerr).text("Please enter a valid skype ID");
		$(skypeerr).css("color", "red");
		flag = true;
		return flase;
	} else if (!(skype.length > 5)) {
		$(skypeerr).text("Please enter minimum 6 letters");
		$(skypeerr).css("color", "red");
		flag = true;
		return flase;
	} else {
		$(skypeerr).text("");
		flag = false;

	}

	if (relation == "") {
		$(relationerr).text("");
		flag = false;
	} else if (!(relation.match(char))) {
		$(relationerr).text("enter characters only");
		$(relationerr).css("color", "red");
		flag = true;
		return flase;
	} else {
		$(relationerr).text("");
		flag = false;

		// $(relationerr).css("color", "green");

	}

	return flag;
}