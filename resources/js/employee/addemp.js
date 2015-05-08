var fileId;
function AddEmployee() {
	Loader.loadHTML(".container", "resources/js/employee/addemp.html", true,
			function() {
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
		$('#doj').val("");
		$("#emal").val("");
		$("#password").val("");
		$('#blood').val("");
		$("#salary").val("");
		$('#variable').val("");
		$('#designation').val("");
		$('#gender').val("");

	}.ctx(this));

	var pannum = $("#pannum").val();
	var panerr = $("#panerr");
	var letters = /^[0-9a-zA-Z]+$/;
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
	var qual = /^[a-zA-Z0-9.""]+$/;
	var num = /^[0-9]+$/;
	var mail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var illegalChars = /\W/
	var letters = /^[0-9a-zA-Z]+$/;
	// var pfchar = /^[0-9]+(/[0-9]+)*$/;
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
	var pwepattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}/;
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
	var doj = $('#doj').val();
	var file = $('#filename').val();
	var variable = $('#variable').val();
	var variableerr = $('#variableerr');
	var Gender = $("#gender option:selected").val();
	var bloodGroup = /^(A|B|AB|O|0)(\+|-)+$/;
	var salry = /^\d{0,10}(?:\.\d{0,2})?$/;
	var dateformat = /^(19|20)\d\d-(0\d|1[012])-(0\d|1\d|2\d|3[01])$/;
	var flag = true;

	if (blood == "" || eid == "" || name == "" || qualification == ""
			|| email == "" || fathername == "" || contnum == ""
			|| txtemercon == "" || txtemname == "" || password == ""
			|| salary == "" || currentaddr == "" || peraddr == ""
			|| relation == "" || yearexp == "" || skill == "" || rating == ""
			|| skype == "" || dob == "" || doj == "") {
		$('.error').css('visibility', 'visible');

		/*
		 * if (file == "") { $('#fileerr').text(""); } else {
		 * $('#fileerr').text('please upload image only').css('color', 'red'); }
		 */

		if (dob == "") {
			$('#dateerr').text('please enter the DOB').css('color', 'red');
		} else if (!(dob.match(dateformat))) {
			$('#dateerr').text('please enter in the format of YYYY-MM-DD').css(
					'color', 'red');
		} else {
			$('#dateerr').text("");
			// $('#dateerr').css("color", "green");
		}
		if (doj == "") {
			$('#dojerr').text('please enter the DOJ').css('color', 'red');

		} else if (!(doj.match(dateformat))) {
			$('#dojerr').text('please enter in the format of YYYY-MM-DD').css(
					'color', 'red');
		} else {
			$('#dojerr').text("");
			// $('.dojerr').css("color", "green");
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
			// $(skypeerr).css("color", "green");

		}

		if (blood == "") {
			$(blodderr).text("required field");
			$(blodderr).css("color", "red");
		} else if (!(blood.match(bloodGroup))) {
			$(blodderr).text("please enter a valid blood group ex:AB+, AB-");
			$(blodderr).css("color", "red");
		} else if (!(blood.length < 4)) {
			$(blodderr).text("please enter 3 char only");
			$(blodderr).css("color", "red");
		} else {
			$(blodderr).text("");
			// $(blodderr).css("color", "green");

		}

		if (eid == "") {
			$(err).text("required field");
			$(err).css("color", "red");
		} else if (!(eid.match(num) || eid == isNaN)) {
			$(err).text("EID accepts numbers only");
			$(err).css("color", "red");
		} else {
			$(err).text("");
			// $(err).css("color", "green");

		}

		if (name == "") {
			$(nameerr).text("required field");
			$(nameerr).css("color", "red");
		} else if (!(name.match(char) || name == isNaN)) {
			$(nameerr).text("min length 6, accept char only");
			$(nameerr).css("color", "red");
		} else if (name.length < 5) {
			$(nameerr).text("please enter min 6 chars");
			$(nameerr).css("color", "red");
		} else {
			$(nameerr).text("");
			// $(nameerr).css("color", "green");

		}

		if (qualification == "") {
			$(nerr).text("required field");
			$(nerr).css("color", "red");
		} else if (!(qualification.match(qual) || qualification.match(char))) {
			$(nerr).text("please check the qualification");
			$(nerr).css("color", "red");
		} else {
			$(nerr).text("");
			// $(nerr).css("color", "green");

		}

		if (email == "") {
			$(emlerr).text("required field");
			$(emlerr).css("color", "red");
		} else if (!(email.match(mail) || email == isNaN)) {
			$(emlerr).text("please enter a valid email address");
			$(emlerr).css("color", "red");
		} else {
			$(emlerr).text("");
			// $(emlerr).css("color", "green");

		}

		if (fathername == "") {
			$(fathererr).text("required field");
			$(fathererr).css("color", "red");
		} else if (!(fathername.match(char) || fathername == isNaN)) {
			$(fathererr).text("enter characters only");
			$(fathererr).css("color", "red");
		} else if (fathername < 5) {
			$(fathererr).text("please enter min 6 characters ");
			$(fathererr).css("color", "red");
		} else {
			$(fathererr).text("");
			// $(fathererr).css("color", "green");

		}

		if (pannum == "") {
			$(panerr).text("");
		} else if (pannum == isNaN || !(pannum.match(letters))) {
			$(panerr).text("please enter a valid pan");
			$(panerr).css("color", "red");
		} else if (!(pannum.length == 10)) {
			$(panerr).text("please enter 10 letters only");
			$(panerr).css("color", "red");
		} else {
			$(panerr).text("");
			// $(panerr).css("color", "green");
		}
		if (pfnum == "") {
			$(pferr).text("");
		} else if (pfnum == isNaN || !(pfnum.match(num))) {
			$(pferr).text("please enter a valid pf number");
			$(pferr).css("color", "red");
		} else if (!(pfnum.length == 16)) {
			$(pferr).text("please check the pf format");
			$(pferr).css("color", "red");
		} else {
			$(pferr).text("");
			// $(pferr).css("color", "green");
		}
		if (accountnum == "") {
			$(acterr).text("");
		} else if (accountnum == isNaN || !(accountnum.match(num))) {
			$(acterr).text("please enter a valid account number");
			$(acterr).css("color", "red");
		} else if (!(accountnum.length == 15)) {
			$(acterr).text("please enter 15 digits only");
			$(acterr).css("color", "red");
		} else {
			$(acterr).text("");
			// $(acterr).css("color", "green");
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

		if (salary == "") {
			$(salerr).text("required field");
			$(salerr).css("color", "red");
		} else if (salary == isNaN || !(salary.match(salry))) {
			$(salerr).text("please enter a valid value ex: 100.00");
			$(salerr).css("color", "red");
		} else {
			$(salerr).text("");
			// $(salerr).css("color", "green");

		}

		if (variable == "") {
			$(variableerr).text("");
			$(variableerr).css("color", "red");
		} else if (variable == isNaN || !(variable.match(salry))) {
			$(variableerr)
					.text("please enter a valid decimal value ex: 100.00");
			$(variableerr).css("color", "red");
		} else {
			$(variableerr).text("");
			// $(salerr).css("color", "green");

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

		if (yearexp == "") {
			$(yearexperr).text("required field");
			$(yearexperr).css("color", "red");
		} else if (yearexp == isNaN || !(yearexp.match(num))) {
			$(yearexperr).text("only numbers allowed");
			$(yearexperr).css("color", "red");
		}/*
			 * else if (!(yearexp.length < 3)) { $(yearexperr).text("max 2
			 * digits allowed"); $(yearexperr).css("color", "red"); }
			 */else {
			$(yearexperr).text("");
			// $(yearexperr).css("color", "green");

		}

		if (skill == "") {
			$(skillerr).text("required field");
			$(skillerr).css("color", "red");
		}
		/*
		 * else if (!(skill == isNaN)) { $(skillerr).text("skill type not
		 * supported"); $(skillerr).css("color","red"); }
		 */else {
			$(skillerr).text("");
			// $(skillerr).css("color", "green");

		}

		if (rating == "") {
			$(ratingerr).text("required field");
			$(ratingerr).css("color", "red");
		}

		else if (!(rating == isNaN || rating.match(num))) {
			$(ratingerr).text("please enter between 1 to 10 ");
			$(ratingerr).css("color", "red");
		}

		else {
			$(ratingerr).text("");
			// $(ratingerr).css("color", "green");

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
				return true;
				//alert("Employee ID: " + $("#eid").val()+ " Details Successfully Added to Skills");
			} else {
				return false;
			}
		}.ctx(this));

		RequestManager
				.saveDesignation(
						input2,
						function(data, success) {
							if (success) {
								return true;
								//alert("Employee ID: "+ $("#eid").val()+ " Details Successfully Added to Designation History");
							} else {
								return false;
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
				$('#doj').val("");
				$("#emal").val("");
				$("#password").val("");
				$('#blood').val("");
				$("#salary").val("");
				$('#variable').val("");
				$('#designation').val("");
				$('#gender').val("");
			} else if (data.code == 297) {

				alert(data.message);

			} else if (data.code == 296) {

				alert(data.message);

			} else {
				alert("Failed to add");
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
		$(error).text("");

	}
}
AddEmployee.prototype.ValidateDrpn = function() {
	var designation = document.getElementById('designation').value;
	var descerr = document.getElementById('descerr');
	if (designation == "") {
		descerr.innerHTML = "please select designation";
		$(descerr).css("color", "red");
	} else {
		$(descerr).text("");

	}
}

// var AddEmployee = new AddEmployee();
