function EditEmployee(employeeId) {
	Loader.loadHTML(".container", "resources/js/employee/EditEmp.html", true,
			function() {
				this.handleShow(employeeId);
			}.ctx(this));
}
EditEmployee.prototype.handleShow = function(employeeId) {

	$('.container').show();
	this.validateEditEmp(employeeId);
	$('#update').click(function(e) {
		e.preventDefault();
		this.validateUpdateEmp(employeeId);
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
			console.dir(data);
			$("#contnum").val(object.contactNo);
			$("#emal").val(object.email);
			$("#password").val(object.password);
			$("#txtemercon").val(object.emergencycontactnumber);
			$("#txtemname").val(object.emergencyContactName);
			$("#relation").val(object.relationWithEmergencyConatact);
			$("#currentaddr").val(object.currentAddress);
			$("#peraddr").val(object.permanentAddress);
			$("#skype").val(object.skype);
		} else {
			console.dir(data);
			// alert("failed to edit");
		}

	}.ctx(this));

}
EditEmployee.prototype.validateUpdateEmp = function(employeeId) {

	var char = /^[A-Za-z]+( [A-Za-z]+)*$/;
	var qual = /^[a-zA-Z.""]+$/;
	var num = /^[0-9]+$/;
	var mail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var illegalChars = /\W/
	var letters = /^[0-9a-zA-Z]+$/;
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
	var skypeerr = $('#skypeerr');

	if (contnum == "" || txtemercon == "" || txtemname == "" || password == ""
			|| peraddr == "" || relation == "" || skype == "" || email == ""
			|| currentaddr == "") {
		$('.error').css('visibility', 'visible');

		if (contnum == "") {
			$(cnumerr).text("required field");
			$(cnumerr).css("color", "red");
		} else if (contnum == isNaN || !(contnum.match(num))) {
			$(cnumerr).text("only numbers allowed");
			$(cnumerr).css("color", "red");
		} else if (!(contnum.length == 10)) {
			$(cnumerr).text("please enter 10 digits only");
			$(cnumerr).css("color", "red");
		} else {
			$(cnumerr).text("");
			// $(cnumerr).css("color", "green");

		}

		if (txtemercon == "") {
			$(emnumerr).text("required field");
			$(emnumerr).css("color", "red");
		} else if (txtemercon == isNaN || !(txtemercon.match(num))) {
			$(emnumerr).text("only numbers allowed");
			$(emnumerr).css("color", "red");
		} else if (!(txtemercon.length == 10)) {
			$(emnumerr).text("please enter 10 digits only");
			$(emnumerr).css("color", "red");
		} else {
			$(emnumerr).text("");
			// $(emnumerr).css("color", "green");

		}

		/*
		 * if (email == "") { $(emlerr).text("required field");
		 * $(emlerr).css("color", "red"); } else if (!(email.match(mail) ||
		 * email == isNaN)) { $(emlerr).text("please enter a valid email
		 * address"); $(emlerr).css("color", "red"); } else {
		 * $(emlerr).text(""); // $(emlerr).css("color", "green"); }
		 */

		if (txtemname == "") {
			$(emnameerr).text("required field");
			$(emnameerr).css("color", "red");
		} else if (!(txtemname.match(char) || txtemname == isNaN)) {
			$(emnameerr).text("enter characters only");
			$(emnameerr).css("color", "red");
		} else if (txtemname.length < 2) {
			$(emnameerr).text("please enter min 3 characters");
			$(emnameerr).css("color", "red");
		} else {
			$(emnameerr).text("");
			// $(emnameerr).css("color", "green");

		}

		if (password == "") {
			$(pwderr).text("required field");
			$(pwderr).css("color", "red");
		} else if (!(password.match(pwepattern))) {
			$(pwderr)
					.text(
							"Must be at least 8 characters,At least 1 number, 1 lowercase, 1 uppercase letter, At least 1 special character");
			$(pwderr).css("color", "red");
		} else {
			$(pwderr).text("");
			// $(pwderr).css("color", "green");
		}

		if (currentaddr == "") {
			$(currentaddrerr).text("required field");
			$(currentaddrerr).css("color", "red");
		} else {
			$(currentaddrerr).text("");
			// $(currentaddrerr).css("color", "green");

		}

		if (peraddr == "") {
			$(peraddrerr).text("required field");
			$(peraddrerr).css("color", "red");
		} else {
			$(peraddrerr).text("");
			// $(peraddrerr).css("color", "green");

		}

		if (relation == "") {
			$(relationerr).text("required field");
			$(relationerr).css("color", "red");
		} else if (!(relation.match(char) || relation == isNaN)) {
			$(relationerr).text("enter characters only");
			$(relationerr).css("color", "red");
		} else {
			$(relationerr).text("");
			// $(relationerr).css("color", "green");
		}

		if (skype == "") {
			$(skypeerr).text("required field");
			$(skypeerr).css("color", "red");
		} else if (!(skype.match(qual) || skype.match(letters))) {
			$(skypeerr).text("please enter a valid skype ID");
			$(skypeerr).css("color", "red");
		} else if (!(skype.length > 5)) {
			$(skypeerr).text("please enter minimum 6 letters");
			$(skypeerr).css("color", "red");
		} else {
			$(skypeerr).text("");
		}

	} else {

		var input = {
			"payload" : {
				"employeeId" : employeeId,
				"contactNo" : contnum,
				"currentAddress" : currentaddr,
				"permanentAddress" : peraddr,
				"email" : email,
				"password" : password,
				"emergencycontactnumber" : txtemercon,
				"emergencyContactName" : txtemname,
				"relationWithEmergencyConatact" : relation,
				"skype" : skype
			}
		};
		RequestManager.updateEmp(input, function(data, success) {
			if (success) {
				console.dir(data);
				alert("Your Details Successfully Updated");
			} else {
				alert("failed to Update the Details");
			}
		}.ctx(this));
	}

}