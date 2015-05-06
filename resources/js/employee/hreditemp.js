var fileId;
function HrEditEmployee(empid) {
	Loader.loadHTML(".container", "resources/js/employee/hreditemp.html", true,
			function() {
				this.handleShow(empid);
			}.ctx(this));
}
HrEditEmployee.prototype.handleShow = function(empid) {

	$('.container').show();
	var eid = $("#eid").val();
	var input = {
		"payload" : {
			"employeeId" : empid
		}
	};
	RequestManager.geteditEmployee(input, function(data, success) {
		if (success) {
			var object = data;
			console.dir(data);
			var dobformat = new Date(object.dateOfBirth);
			var byear = dobformat.getFullYear();
			var bmonth = dobformat.getMonth() + 1;
			var bdate = dobformat.getDate();
			$("#emal").val(object.email);
			$("#eid").val(object.employeeId);
			$("#name").val(object.employeeName);
			$("#dob").val(byear+"-"+bmonth+"-"+bdate);
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
	
	this.validatehrEditEmp(empid);
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
	$('#filename').on('click', UploadClickHandler.ctx(this));
	function UploadClickHandler(event) {
		var thisEle = event.target;
		this.uploadMedia(function() {
			$(thisEle).on('click', UploadClickHandler.ctx(this));
		}.ctx(this));
	}
/*
	$('#hredit').click(function(e) {
		e.preventDefault();
		this.validatehrEditEmp(dataId);
	}.ctx(this));*/

	$('#hrupdate').click(function(e) {
		e.preventDefault();
		this.validateUpdatehrEmp(empid);
	}.ctx(this));

	$('#hrdelete').click(function(e) {
		e.preventDefault();
		this.deleteEmployee(empid);
	}.ctx(this));

	/*
	 * $('#filename').focusout(function(e) { if (file == "") {
	 * $('#fileerr').text('please upload image only').css('color', 'red'); }
	 * else { $('#fileerr').text('ok').css('color', 'green'); } });
	 */
	/*
	 * var pannum = $("#pannum").val(); var panerr = $("#panerr"); var letters =
	 * /^[0-9a-zA-Z]+$/; $("#pannum").focusout(function() { if (pannum == "") {
	 * $(panerr).text("required field"); $(panerr).css("color", "red"); } else
	 * if (pannum == isNaN || !(pannum.match(letters))) { $(panerr).text("PAN
	 * accepts both char and numbers"); $(panerr).css("color", "red"); } else if
	 * (!(pannum.length == 10)) { $(panerr).text("please enter 10 letters
	 * only"); $(panerr).css("color", "red"); } else { $(panerr).text("ok");
	 * $(panerr).css("color", "green"); } });
	 * 
	 * var pferr = $("#pferr"); var pfnum = $("#pfnum").val();
	 * $("#pfnum").focusout(function() { if (pfnum == "") {
	 * $(pferr).text("required field"); $(pferr).css("color", "red"); } else if
	 * (pfnum == isNaN || !(pfnum.match(letters))) { $(pferr).text("PF accepts
	 * both char and numbers"); $(pferr).css("color", "red"); } else if
	 * (!(pfnum.length == 18)) { $(pferr).text("please enter 18 letters only");
	 * $(pferr).css("color", "red"); } else { $(pferr).text("ok");
	 * $(pferr).css("color", "green"); } });
	 * 
	 * var acterr = $("#acterr"); var accountnum = $("#accountnum").val(); var
	 * id = /^[0-9]+$/; $("#accountnum").focusout(function() { if (accountnum ==
	 * "") { $(acterr).text("required field"); $(acterr).css("color", "red"); }
	 * else if (accountnum == isNaN || !(accountnum.match(id))) {
	 * $(acterr).text("only numbers allowed"); $(acterr).css("color", "red"); }
	 * else if (!(accountnum.length == 15)) { $(acterr).text("please enter 15
	 * digits only"); $(acterr).css("color", "red"); } else {
	 * $(acterr).text("ok"); $(acterr).css("color", "green"); } });
	 */

}
HrEditEmployee.prototype.uploadMedia = function(callback) {
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

HrEditEmployee.prototype.validatehrEditEmp = function(empid) {
	$('.error').css('visibility', 'hidden');

	

}

HrEditEmployee.prototype.validateUpdatehrEmp = function(empid) {

	var char = /^[A-Za-z]+( [A-Za-z]+)*$/;
	var qual = /^[A-Za-z]+(.[A-Za-z]+)*$/;
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
			|| skype == "" || Gender == "" || nameerr == "" || blood == ""
			|| nerr == "" || fathererr == "" || salerr == "" || dob == "") {
		$('.error').css('visibility', 'visible');

		if (dob == "") {
			$('.dateerr').text('please pick the DOB').css('color', 'red');
		} else {
			$('.dateerr').text("ok");
			$('.dateerr').css("color", "green");
			
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
		flag = true;
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
				"employeeId" : empid,
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
				"skype" : skype,
				"filePath" : fileId
			}
		};
		RequestManager.hrupdateEmp(input, function(data, success) {
			if (success) {
				console.dir(data);
				alert("Employee ID:" + eid + " Details Successfully Updated");
			} else {
				alert("failed to Update the Details");
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

HrEditEmployee.prototype.deleteEmployee = function(empid) {
	var input = {
		"payload" : {
			"employeeId" : empid
		}
	};
	RequestManager.hrDeleteEmployee(input, function(data, success) {
		if (success) {
			console.dir(data);
			alert("EmployeeID: " + $("#eid").val()
					+ " Details Successfully Deleted");
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
			$('#dob').val("");
			$('#doj').val("");
			$("#emal").val("");
			$("#password").val("");
			$('#blood').val("");
			$("#salary").val("");
			$('#variable').val("");
			$('#gender').val("");
			$('#skype').val("");
			//document.getElementById("hredit").disabled = true;
			document.getElementById("hrupdate").disabled = true;
			document.getElementById("hrdelete").disabled = true;
		} else {
			alert("failed to Delete");
		}
	}.ctx(this));
}
