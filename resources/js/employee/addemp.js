var fileId;
function AddEmployee() {
	Loader.loadHTML(".container", "resources/js/employee/addemp.html",
			true, function() {
				this.handleShow();
			}.ctx(this));
}
AddEmployee.prototype.handleShow = function() {
	$('.container').show();

	$('#dob').datepicker({
		// dateFormat : $.datepicker.TIMESTAMP,
		dateFormat : 'yy-mm-dd',
		showButtonPanel : true,
		changeMonth : true,
		changeYear : true,
		showAnim : 'drop',
		minDate : new Date(1980, 12, 31),
		maxDate : new Date(1994, 12, 31)
	})

	$('#doj').datepicker({
		// dateFormat : $.datepicker.TIMESTAMP,
		dateFormat : 'yy-mm-dd',
		showButtonPanel : true,
		changeMonth : true,
		changeYear : true,
		showAnim : 'drop',
		minDate : new Date(2010, 12, 31),
		maxDate : new Date(2015, 5, 31)
	})

	$('#filename').on('click', UploadClickHandler.ctx(this));
	function UploadClickHandler(event) {
		var thisEle = event.target;
		this.uploadMedia(function() {
			$(thisEle).on('click', UploadClickHandler.ctx(this));
		}.ctx(this));
	}

	$('#add').click(function(e) {
		e.preventDefault();
		this.validateEmp();

	}.ctx(this));
	
	$('#reset').click(function(e) {
		e.preventDefault();
		$('.error').css('visibility', 'hidden');

	}.ctx(this));

}
AddEmployee.prototype.uploadMedia = function(callback) {
	var allowedFileType = "image";
	var uploader = new Uploader(allowedFileType, function(data) {
		if (data.filePath) {
			var imageSrc = data.filePath;
			this.fileId = data.id;
			fileId = this.fileId;
			$('.mediaForProfileImage').attr('src', imageSrc);
			if (imageSrc != null) {
				$('.mediaForProfileImage').lightBox();
			}
		}
	}.ctx(this));
	callback();

}

// validating each employee field
AddEmployee.prototype.validateEmp = function() {
	var char = /^[A-Za-z]+( [A-Za-z]+)*$/;
	var qual = /^[a-zA-Z.""]+$/;
	var num = /^[0-9]+$/;
	var mail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var illegalChars = /\W/
	var letters = /^[0-9a-zA-Z]+$/;
	var err = $("#eiderr");
	var eid = $("#eid").val();
	var nameerr = $("#nameerr");
	var name = $("#name").val();
	var nerr = $("#qualerr");
	var qualification = $("#qual").val();
	var emlerr = $("#emlerr");
	var email = $("#emal").val();
	var fathererr = $("#fathererr");
	var fathername = $("#fathername").val();
	var panerr = $("#panerr");
	var pannum = $("#pannum").val();
	var pferr = $("#pferr");
	var pfnum = $("#pfnum").val();
	var acterr = $("#acterr");
	var accountnum = $("#accountnum").val();
	var cnumerr = $("#cnumerr");
	var contnum = $("#contnum").val();
	var txtemname = $("#txtemname").val();
	var emnameerr = $("#emnameerr");
	var mail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var pwepattern = /^[a-zA-Z0-9_-]{8,15}$/;
	var pwderr = $("#pwderr");
	var salerr = $("#salerr");
	var salary = $("#salary").val();
	var emnumerr = $("#emnumerr");
	var txtemercon = $("#txtemercon").val();
	var password = $("#password").val();
	var currentaddrerr = $("#currentaddrerr");
	var currentaddr = $("#currentaddr").val();
	var ratingerr = $("#ratingerr");
	var rating = $("#rating").val();
	var peraddrerr = $("#peraddrerr");
	var peraddr = $("#peraddr").val();
	var relationerr = $("#relationerr");
	var relation = $("#relation").val();
	var yearexperr = $("#yearexperr");
	var yearexp = $("#yearexp").val();
	var skillerr = $("#skillerr");
	var skill = $("#skill").val();
	var designation = $('#designation option:selected').val();
	var descerr = document.getElementById('descerr');
	var blood = $('#blood').val();
	var blodderr = $('#blooderr');
	var skype = $('#skype').val();
	var skypeerr = $('#skypeerr');
	var dob = $('#dob').val();
	// var adob = $('#adob').val();
	var doj = $('#doj').val();
	var file = $('#filename').val();
	var variable = $('#variable').val();
	var Gender = $("#gender option:selected").val();
	var bloodGroup = /^(A|B|AB|O|0)(\+|-)+$/;

	var flag = true;

	if (blood == "" || eid == "" || name == "" || qualification == ""
			|| email == "" || fathername == "" || contnum == ""
			|| txtemercon == "" || txtemname == "" || password == ""
			|| salary == "" || currentaddr == "" || peraddr == ""
			|| relation == "" || yearexp == "" || skill == "" || rating == ""
			|| skype == "" || dob == "" || doj == "") {
		$('.error').css('visibility', 'visible');

		/*
		 * if (file == "") { $('#fileerr').text('please upload image
		 * only').css('color', 'red'); } else {
		 * $('#fileerr').text('ok').css('color', 'green'); }
		 */

		// $('.dateerr').text('please input the date').css('color', 'red');
		if (skype == "") {
			$(skypeerr).text("required field");
			$(skypeerr).css("color", "red");
		} else if (!(skype.match(char))) {
			$(skypeerr).text("please enter a valid skype ID");
			$(skypeerr).css("color", "red");
		} else if (!(skype.length > 6)) {
			$(skypeerr).text("please enter minimum 6 letters");
			$(skypeerr).css("color", "red");
		} else {
			$(skypeerr).text("ok");
			$(skypeerr).css("color", "green");

		}

		if (blood == "") {
			$(blodderr).text("required field");
			$(blodderr).css("color", "red");
		} else if (!(blood.match(bloodGroup))) {
			$(blodderr).text("please enter a valid blood group");
			$(blodderr).css("color", "red");
		} else if (!(blood.length < 4)) {
			$(blodderr).text("please enter 3 char only");
			$(blodderr).css("color", "red");
		} else {
			$(blodderr).text("ok");
			$(blodderr).css("color", "green");

		}

		if (eid == "") {
			$(err).text("required field").show();
			$(err).css("color", "red");
		} else if (!(eid.match(num) || eid == isNaN)) {
			$(err).text("EID accepts numbers only");
			$(err).css("color", "red");
		} else {
			$(err).text("ID looks great");
			$(err).css("color", "green");

		}

		if (name == "") {
			$(nameerr).text("required field");
			$(nameerr).css("color", "red");
		} else if (!(name.match(char) || name == isNaN)) {
			$(nameerr).text("min length 6, accept char only");
			$(nameerr).css("color", "red");
		} else if (name < 5) {
			$(nameerr).text("please enter min 6 chars");
			$(nameerr).css("color", "red");
		} else {
			$(nameerr).text("name looks Great");
			$(nameerr).css("color", "green");

		}

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

		if (email == "") {
			$(emlerr).text("required field");
			$(emlerr).css("color", "red");
		} else if (!(email.match(mail) || email == isNaN)) {
			$(emlerr).text("please enter a valid email address");
			$(emlerr).css("color", "red");
		} else {
			$(emlerr).text("nice email id");
			$(emlerr).css("color", "green");

		}

		if (fathername == "") {
			$(fathererr).text("required field");
			$(fathererr).css("color", "red");
		} else if (!(fathername.match(char) || fathername == isNaN)) {
			$(fathererr).text("enter characters only");
			$(fathererr).css("color", "red");
		}else if (fathername < 5) {
			$(fathererr).text("please enter min 6 characters ");
			$(fathererr).css("color", "red");
		}
		else {
			$(fathererr).text("nice name");
			$(fathererr).css("color", "green");

		}

		/*
		 * if (pannum == "") { $(panerr).text("required field");
		 * $(panerr).css("color", "red"); } else if (pannum == isNaN ||
		 * !(pannum.match(letters))) { $(panerr).text("PAN accepts both char and
		 * numbers"); $(panerr).css("color", "red"); } else if (!(pannum.length ==
		 * 10)) { $(panerr).text("please enter 10 letters only");
		 * $(panerr).css("color", "red"); } else { $(panerr).text("ok");
		 * $(panerr).css("color", "green"); }
		 * 
		 * if (pfnum == "") { $(pferr).text("required field");
		 * $(pferr).css("color", "red"); } else if (pfnum == isNaN ||
		 * !(pfnum.match(letters))) { $(pferr).text("PF accepts both char and
		 * numbers"); $(pferr).css("color", "red"); } else if (!(pfnum.length ==
		 * 18)) { $(pferr).text("please enter 18 letters only");
		 * $(pferr).css("color", "red"); } else { $(pferr).text("ok");
		 * $(pferr).css("color", "green"); }
		 * 
		 * if (accountnum == "") { $(acterr).text("required field");
		 * $(acterr).css("color", "red"); } else if (accountnum == isNaN ||
		 * !(accountnum.match(num))) { $(acterr).text("only numbers allowed");
		 * $(acterr).css("color", "red"); } else if (!(accountnum.length == 15)) {
		 * $(acterr).text("please enter 15 digits only"); $(acterr).css("color",
		 * "red"); } else { $(acterr).text("ok"); $(acterr).css("color",
		 * "green"); }
		 */

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
		} else {
			$(emnumerr).text("ok");
			$(emnumerr).css("color", "green");

		}

		if (txtemname == "") {
			$(emnameerr).text("required field");
			$(emnameerr).css("color", "red");
		} else if (!(txtemname.match(char) || txtemname == isNaN)) {
			$(emnameerr).text("enter characters only");
			$(emnameerr).css("color", "red");
		}else if (txtemname < 5) {
			$(emnameerr).text("please enter min 6 characters");
			$(emnameerr).css("color", "red");
		}
		else {
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
		} else {
			$(pwderr).text("ok");
			$(pwderr).css("color", "green");

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

		if (yearexp == "") {
			$(yearexperr).text("required field");
			$(yearexperr).css("color", "red");
		} else if (yearexp == isNaN || !(yearexp.match(num))) {
			$(yearexperr).text("only numbers allowed");
			$(yearexperr).css("color", "red");
		} else if (!(yearexp.length < 3)) {
			$(yearexperr).text("max 2 digits allowed");
			$(yearexperr).css("color", "red");
		} else {
			$(yearexperr).text("ok");
			$(yearexperr).css("color", "green");

		}

		if (skill == "") {
			$(skillerr).text("required field");
			$(skillerr).css("color", "red");
		}
		/*
		 * else if (!(skill == isNaN)) { $(skillerr).text("skill type not
		 * supported"); $(skillerr).css("color","red"); }
		 */else {
			$(skillerr).text("ok");
			$(skillerr).css("color", "green");

		}

		if (rating == "") {
			$(ratingerr).text("required field");
			$(ratingerr).css("color", "red");
		}

		else if (!(rating == isNaN)) {
			$(ratingerr).text("please enter between 1 to 10 ");
			$(ratingerr).css("color", "red");
		}

		else {
			$(ratingerr).text("ok");
			$(ratingerr).css("color", "green");

		}

		this.GenderValidate();
		this.ValidateDrpn();
	}
	// sending data to database in the form of json
	else {

		$('.error').css('visibility', 'hidden');

		var input = {
			"payload" : {
				"employeeId" : eid,
				"employeeName" : name,
				"gender" : Gender,
				"dateOfBirth" : dob + " 00:00:00",
				"dateOfJoining" : doj + " 00:00:00",
				"yearsofexperience" : yearexp,
				"contactNo" : contnum,
				"currentAddress" : currentaddr,
				"permanentAddress" : peraddr,
				"email" : email,
				"password" : password,
				"emergencycontactnumber" : txtemercon,
				"fathersName" : fathername,
				"emergencyContactName" : txtemname,
				"relationWithEmergencyConatact" : relation,
				"bloodGroup" : blood,
				"currentDesignation" : designation,
				"highestQualification" : qualification,
				"pan" : pannum,
				"pfNo" : pfnum,
				"bankAccountNo" : accountnum,
				"skype" : skype,
				"variableComponent" : variable,
				"salary" : salary,
				"rating" : rating,
				"skill" : skill,
				"filePath" : fileId
			}
		};
		var input2 = {
			"payload" : {
				"designationId" : designation,
				"empId" : eid,
				"appraisalDate" : doj + " 00:00:00",
				"salary" : salary,
				"variablePay" : variable
			}

		};

		var input3 = {
			"payload" : {
				"empId" : eid,
				"rating" : rating,
				"skills" : skill
			}

		};

		RequestManager.saveSkill(input3, function(data, success) {
			if (success) {
				alert("Employee ID: " + $("#eid").val()
						+ " Details Successfully Added to Skills");
			} else {
				alert("failed to add");
			}
		}.ctx(this));

		RequestManager
				.saveDesignation(
						input2,
						function(data, success) {
							if (success) {
								alert("Employee ID: "
										+ $("#eid").val()
										+ " Details Successfully Added to Designation History");
							} else {
								alert("failed to add");
							}
						}.ctx(this));

		RequestManager.saveEmp(input, function(data, success) {
			if (success) {
				// $('success').val("Successfully Added")
				alert("Employee ID: " + $("#eid").val()
						+ " Details Successfully Added to EmployeeList");
				$("#eid").val("");
				$("#name").val("");
				$("#qual").val("");
				$("#fathername").val("");
				$("#pfnum").val("");
				$("#pannum").val("");
				$("#accountnum").val("");
				$("#contnum").val("");
				$("#txtemercon").val("");
				$("#txtemname").val("");
				$("#currentaddr").val("");
				$("#peraddr").val("");
				$("#relation").val("");
				$("#yearexp").val("");
				$("#skill").val("");
				$("#rating").val("");
				$("#skype").val("");
				$('designation').val("");
				$('#dob').val("");
				$('#adob').val("");
				$('#doj').val("");
				$("#emal").val("");
				$("#password").val("");
				$('#blood').val("");
				$("#salary").val("");
				$('#variable').val("");
				$('#designation option:selected').val("");
			} else {
				alert("failed to add");
			}
		}.ctx(this));
	}
}
AddEmployee.prototype.GenderValidate = function() {
	var Gender = $("#gender option:selected").val();
	var error = document.getElementById('generr');
	if (Gender == "") {
		error.innerHTML = "required field";
		$(error).css("color", "red");
	} else {
		$(error).text("ok");
		$(error).css("color", "green");
	}
}
AddEmployee.prototype.ValidateDrpn = function() {
	var designation = document.getElementById('designation').value;
	var descerr = document.getElementById('descerr');
	if (designation == "") {
		descerr.innerHTML = "please select designation";
		$(descerr).css("color", "red");
	} else {
		$(descerr).text("ok");
		$(descerr).css("color", "green");
	}
}

// var AddEmployee = new AddEmployee();
