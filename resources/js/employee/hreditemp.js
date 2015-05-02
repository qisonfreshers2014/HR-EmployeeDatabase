function HrEditEmployee(dataId) {
	Loader.loadHTML(".leftContainer", "resources/js/employee/hreditemp.html",
			true, function() {
				this.handleShow(dataId);
			}.ctx(this));
}
HrEditEmployee.prototype.handleShow = function(dataId) {

	$('.container').show();

	/*
	 * $('.date').datepicker({ //dateFormat : $.datepicker.TIMESTAMP, dateFormat :
	 * 'yy-mm-dd', showButtonPanel : true, changeMonth : true, changeYear :
	 * true, showAnim : 'bounce', minDate : new Date(1980, 12, 31), maxDate :
	 * new Date(2015, 5, 31) })
	 */
	$('#hredit').click(function(e) {
		e.preventDefault();
		this.validatehrEditEmp(dataId);
	}.ctx(this));

	$('#hrupdate').click(function(e) {
		e.preventDefault();
		this.validateUpdatehrEmp(dataId);
	}.ctx(this));
}

HrEditEmployee.prototype.validatehrEditEmp = function(dataId) {
	$('.error').css('visibility', 'hidden');

	var eid = $("#eid").val();
	var input = {
		"payload" : {
			"id" : dataId
		// "employeeId" : eid
		}
	};
	RequestManager.geteditEmployee(input, function(data, success) {
		if (success) {
			var object = data;
			console.dir(data);
			$("#emal").val(object.email);
			$("#eid").val(object.employeeId);
			$("#name").val(object.employeeName);
			$("#dob").val(object.dateOfBirth);
			$("#blood").val(object.bloodGroup);
			$("#qual").val(object.highestQualification);
			$("#fathername").val(object.fathersName);
			$("#salary").val(object.salary);
			$("#pannum").val(object.pan);
			$("#pfnum").val(object.pfNo);
			$("#accountnum").val(object.bankAccountNo);
			$("#variable").val(object.variableComponent);
			$("#contnum").val(object.contactNo);
			$("#password").val(object.password);
			$("#txtemercon").val(object.emergencycontactnumber);
			$("#txtemname").val(object.emergencyContactName);
			$("#relation").val(object.relationWithEmergencyConatact);
			$("#currentaddr").val(object.currentAddress);
			$("#peraddr").val(object.permanentAddress);
			$("#skype").val(object.skype);
		} else {
			alert("failed to edit");
		}

	}.ctx(this));

}

HrEditEmployee.prototype.validateUpdatehrEmp = function(dataId) {

	var char = /^[a-zA-Z."" ]+$/;
	var qual = /^[a-zA-Z.""]+$/;
	var num = /^[0-9]+$/;
	var mail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var letters = /^[0-9a-zA-Z]+$/;
	var cnumerr = $("#cnumerr");
	var contnum = $("#contnum").val();
	var mail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var emlerr = $("#emlerr");
	var email = $("#emal").val();
	var pwepattern = /^[a-zA-Z0-9_-]{8,15}$/;
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
	var salary = $("#salary").val();
	var salerr = $("#salerr");
	var eid = $("#eid").val();
	var name = $("#name").val();
	var nameerr = $("#nameerr");
	var qualification = $("#qual").val();
	var nerr = $("#qualerr"); 
	var email = $("#emal").val();
	var fathername = $("#fathername").val();
	var fathererr = $("#fathererr");
	var pannum = $("#pannum").val();
	var pfnum = $("#pfnum").val();
	var accountnum = $("#accountnum").val();
	var blood = $('#blood').val();
	var blodderr = $('#blooderr');
	var dob = $('#dob').val();
	var variable = $('#variable').val();
	var Gender = $("#gender option:selected").val();
	
	var flag = true;

	if (blood == "" || eid == "" || name == "" || qualification == ""
			|| email == "" || fathername == "" || contnum == ""
			|| txtemercon == "" || txtemname == "" || password == ""
			|| currentaddr == "" || peraddr == "" || relation == ""
			|| skype == "" || Gender == "" || nameerr == "" || blood == "" ||
			nerr =="" || fathererr == "" || salerr == "") {
		$('.error').css('visibility', 'visible');

		if (qualification == "") {
			$(nerr).text("required field");
			$(nerr).css("color", "red");
		} else if (!(qualification.match(qual) || qualification == isNaN)) {
			$(nerr).text("please check the qualification");
			$(nerr).css("color", "red");
		} else {
			$(nerr).text("nice qualification");
			$(nerr).css("color", "green");
		}
		
		if (fathername == "") {
			$(fathererr).text("required field");
			$(fathererr).css("color", "red");
		} else if (!(fathername.match(char) || fathername == isNaN)) {
			$(fathererr).text("enter characters only");
			$(fathererr).css("color", "red");
		} else {
			$(fathererr).text("nice name");
			$(fathererr).css("color", "green");
		}
		
		if (salary == "") {
			$(salerr).text("required field");
			$(salerr).css("color", "red");
		} else if (salary == isNaN || !(salary.match(num))) {
			$(salerr).text("only numbers allowed");
			$(salerr).css("color", "red");
		} else {
			$(salerr).text("ok");
			$(salerr).css("color", "green");
		}
		
		if (blood == "") {
			$(blodderr).text("required field");
			$(blodderr).css("color", "red");
		} else if (!(blood.match(char))) {
			$(blodderr).text("please check blood group");
			$(blodderr).css("color", "red");
		} else if (!(blood.length < 4)) {
			$(blodderr).text("it will accept 3 char only");
			$(blodderr).css("color", "red");
		} else {
			$(blodderr).text("ok");
			$(blodderr).css("color", "green");
		}
		
		if (name == "") {
			$(nameerr).text("required field");
			$(nameerr).css("color", "red");
		} else if (!(name.match(char) || name == isNaN)) {
			$(nameerr).text("min length 6, accept char only");
			$(nameerr).css("color", "red");
		} else {
			$(nameerr).text("name looks Great");
			$(nameerr).css("color", "green");

		}

		if (contnum == "") {
			$(cnumerr).text("required field");
			$(cnumerr).css("color", "red");
		} else if (contnum == isNaN || !(contnum.match(num))) {
			$(cnumerr).text("only numbers allowed");
			$(cnumerr).css("color", "red");
		} else if (!(contnum.length == 10)) {
			$(cnumerr).text("please enter 10 digits only");
			$(cnumerr).css("color", "red");

		}

		else {
			$(cnumerr).text("ok");
			$(cnumerr).css("color", "green");

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
		}

		else {
			$(emnumerr).text("ok");
			$(emnumerr).css("color", "green");

		}
		if (email == "") {
			$(emlerr).text("required field");
			$(emlerr).css("color", "red");
		} else if (!(email.match(mail) || email == isNaN)) {
			$(emlerr).text("enter correct mail id");
			$(emlerr).css("color", "red");
		} else {
			$(emlerr).text("nice email id");
			$(emlerr).css("color", "green");

		}

		if (txtemname == "") {
			$(emnameerr).text("required field");
			$(emnameerr).css("color", "red");
		} else if (!(txtemname.match(char) || txtemname == isNaN)) {
			$(emnameerr).text("enter characters only");
			$(emnameerr).css("color", "red");
		} else {
			$(emnameerr).text("nice name");
			$(emnameerr).css("color", "green");

		}

		if (password == "") {
			$(pwderr).text("required field");
			$(pwderr).css("color", "red");
		} else if ((password.match(pwepattern))) {
			$(pwderr)
					.text(
							"Must be at least 8 characters,At least 1 number, 1 lowercase, 1 uppercase letter, At least 1 special character from @#$%&");
			$(pwderr).css("color", "red");
		}

		else {
			$(pwderr).text("ok");
			$(pwderr).css("color", "green");

		}

		if (currentaddr == "") {
			$(currentaddrerr).text("required field");
			$(currentaddrerr).css("color", "red");
		} else {
			$(currentaddrerr).text("ok");
			$(currentaddrerr).css("color", "green");

		}

		if (peraddr == "") {
			$(peraddrerr).text("required field");
			$(peraddrerr).css("color", "red");
		} else {
			$(peraddrerr).text("ok");
			$(peraddrerr).css("color", "green");

		}

		if (relation == "") {
			$(relationerr).text("required field");
			$(relationerr).css("color", "red");
		} else if (!(relation.match(char) || relation == isNaN)) {
			$(relationerr).text("enter characters only");
			$(relationerr).css("color", "red");
		} else {
			$(relationerr).text("ok");
			$(relationerr).css("color", "green");

		}

		if (skype == "") {
			$(skypeerr).text("required field");
			$(skypeerr).css("color", "red");
		} else if (skype == isNaN || !(skype.match(char))) {
			$(skypeerr).text("please check skype account name");
			$(skypeerr).css("color", "red");
		} else if (!(skype.length > 6)) {
			$(skypeerr).text("please enter minimum 6 letters");
			$(skypeerr).css("color", "red");
		} else {
			$(skypeerr).text("ok");
			$(skypeerr).css("color", "green");

		}
		this.GenderValidate();

	} else {
		
		$('.error').css('visibility', 'hidden');
		var input = {
			"payload" : {
				"id" : dataId,
				"employeeId" : eid,
				"employeeName" : name,
				"gender" : Gender,
				"fathersName" : fathername,
				"bloodGroup" : blood,
				"highestQualification" : qualification,
				"salary" : salary,
				"variableComponent" : variable,
				"contactNo" : contnum,
				"currentAddress" : currentaddr,
				"permanentAddress" : peraddr,
				"email" : email,
				"password" : password,
				"pan" : pannum,
				"pfNo" : pfnum,
				"bankAccountNo" : accountnum,
				"emergencycontactnumber" : txtemercon,
				"emergencyContactName" : txtemname,
				"relationWithEmergencyConatact" : relation,
				"skype" : skype
			}
		};
		RequestManager.hrupdateEmp(input, function(data, success) {
			if (success) {
				console.dir(data);
				alert("Employee ID:" + eid + " Details Successfully Updated");
			} else {
				alert("failed to add");
			}
		}.ctx(this));

	}
}

HrEditEmployee.prototype.GenderValidate = function() {
	var Gender = $("#gender option:selected").val();
	var error = document.getElementById('generr');
	if (Gender == "") {
		error.innerHTML = "required field";
		$(error).css("color", "red");
	} else {
		$(error).text("ok");
		$(error).css("color", "green");
		flag = 1;
	}
}
