var fileId;
function HrEditEmployee(empid) {
	Loader.loadHTML(".container", "resources/js/employee/hreditemp.html", true,
			function() {
				this.handleShow(empid);
			}.ctx(this));
}
HrEditEmployee.prototype.handleShow = function(empid) {

	$('.container').show();
	$('#LastWorkingDay').hide();

	var eid = $("#eid").val();
	var input = {
		"payload" : {
			"id" : empid
		}
	};
	RequestManager.geteditEmployee(input, function(data, success) {
		if (success) {
			var object = data;
	    
			var monthsArray=new Array(12);
			monthsArray[0]="Jan";
			monthsArray[1]="Feb";
			monthsArray[2]="March";
			monthsArray[3]="April";
			monthsArray[4]="May";
			monthsArray[5]="June";
			monthsArray[6]="July";
			monthsArray[7]="Aug";
			monthsArray[8]="Sep";
			monthsArray[9]="Oct";
			monthsArray[10]="Nov";
			monthsArray[11]="Dec";
			
			var dobformat = new Date(object.dateOfBirth);
			var byear = dobformat.getFullYear();
			var bmonth = monthsArray[dobformat.getMonth()];
			var payloadmonth=dobformat.getMonth()+1;
			var bdate = dobformat.getDate();
			
			payloadBday=byear + "-" + payloadmonth + "-" + bdate;
	
			
			var actualdobformat = new Date(object.actualdateOfBirth);
			var actualdobyear = actualdobformat.getFullYear();
			var actualdobmonth = monthsArray[actualdobformat.getMonth()];
			var payloadactualdobmonth=actualdobformat.getMonth()+1;
			var actualdobdate = actualdobformat.getDate();
			
			payloadactualdob=actualdobyear + "-" + payloadactualdobmonth + "-" + actualdobdate;
			
			 var dojformat = new Date(object.dateOfJoining);
			   var dojyear = dojformat.getFullYear();
			   var payloadDOJmonth=dojformat.getMonth()+1;
			   var dojmonth = monthsArray[dojformat.getMonth()];
			   var dojdate = dojformat.getDate();
			   
			   payloadDOJ=dojyear + "-" + payloadDOJmonth + "-" + dojdate;
			
			var lastWorkingDayOfEmployee=new Date(object.lastWorkingDay);
			
			var LWDYear=lastWorkingDayOfEmployee.getFullYear();
			var LWDMonth=monthsArray[lastWorkingDayOfEmployee.getMonth()];
			var payloadLWDMonth=lastWorkingDayOfEmployee.getMonth()+1;
			var LWDday=lastWorkingDayOfEmployee.getDate();
			
			payloadLWD=LWDYear + "-" + payloadLWDMonth + "-" + LWDday;
			if(object.deleted==true){
				$('#LastWorkingDay').show();
				$('#hrdelete').hide();
			}
			 
			$("#emal").val(object.email);
			$("#eid").val(object.employeeId);
			$("#name").val(object.employeeName);
			$("#dob").val(payloadBday);
			$("#actualdob").val(payloadactualdob);
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
			$("#fileid").val(object.fileId);
			$("#yearofexp").val(object.yearsofexperience);
			$('#employeetype').val(object.employeeType);
		    $("#gender").val(object.gender);
		    $('#doj').val(payloadDOJ);
		    $('#collagename').val(object.university);
			$('#lastwrkday').val(payloadLWD);
			
		} else {
			alert("failed to edit");
		}
		
		$('#backtohrview').click(function(){
			
			App.loadViewEmployee(object.employeeId);
		}.ctx(this));

	}.ctx(this));
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
	$('#actualdob').datepicker({
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
		minDate : new Date(2009, 12, 31),
		maxDate : new Date()
	})

	$('#lastwrkday').datepicker({
		// dateFormat : $.datepicker.TIMESTAMP,
		dateFormat : 'yy-mm-dd',
		showButtonPanel : true,
		changeMonth : true,
		changeYear : true,
		showAnim : 'drop',
		minDate : new Date(2009, 12, 31),
		maxDate : new Date()
	})
	$('#filename').one('click', UploadClickHandler.ctx(this));
	function UploadClickHandler(event) {
		var thisEle = event.target;
		this.uploadMedia(function() {
			$(thisEle).on('click', UploadClickHandler.ctx(this));
		}.ctx(this));
	}
	$('#hrupdate').click(function(e) {
		e.preventDefault();
		this.validateUpdatehrEmp(empid);
	}.ctx(this));

	$('#hrdelete').click(function(e) {
		e.preventDefault();
		this.deleteEmployee(empid);
	}.ctx(this));
}
HrEditEmployee.prototype.uploadMedia = function(callback) {
	var allowedFileType = "image";
	var uploader = new Uploader(allowedFileType, function(data) {
		if (data.filePath) {
			var imageSrc = data.filePath;
			this.fileId = data.id;
			fileId = this.fileId;
			var index = imageSrc.lastIndexOf("/") + 1;
			var filename = imageSrc.substr(index);
			$('#filepath').text(filename);
			/*
			 * $('.mediaForProfileImage').attr('src', imageSrc); if (imageSrc !=
			 * null) { $('.mediaForProfileImage').lightBox(); }
			 */
		}
	}.ctx(this));
	callback();

}

var idReg=/^[a-zA-Z0-9]*$/;
var char = /^[A-Za-z .]+( [A-Za-z]+)*$/;
var qual = /^[A-Za-z]+(.[A-Za-z]+)*$/;
var num = /^[0-9]+$/;
var exprnce = /^[0-9.]+$/;
var skp = /^[A-Za-z0-9]+(.[A-Za-z0-9]+)*$/;
var mail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
var illegalChars = /\W/
var letters = "^[a-zA-Z0-9-]*$";
var mail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
var pwepattern = /^[a-zA-Z0-9_-]{8,15}$/;
var bloodGroup = /^(A|B|AB|O|0)(\+|-)+$/;
var salry = /^\d{0,10}(?:\.\d{0,2})?$/;
var dateformat = /^(19|20)\d\d-(0\d|1[012])-(0\d|1\d|2\d|3[01])$/;
HrEditEmployee.prototype.validateUpdatehrEmp = function(empid) {
	
	// var pfchar = /^[0-9]+(/[0-9]+)*$/;
	var err = $("#eiderr");
	var eid = $("#eid").val();
	var nameerr = $("#nameerr");
	var name = $("#name").val();
	var nerr = $("#qualerr");
	var genderErr=$('#generr');
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
	
	var pwderr = $("#pwderr");
	var salerr = $("#salerr");
	var salary = $("#salary").val();
	var emnumerr = $("#emnumerr");
	var txtemercon = $("#txtemercon").val();
	var password = $("#password").val();
	var currentaddrerr = $("#currentaddrerr");
	var currentaddr = $("#currentaddr").val();
	var peraddrerr = $("#peraddrerr");
	var peraddr = $("#peraddr").val();
	var relationerr = $("#relationerr");
	var relation = $("#relation").val();
	var blood = $('#blood').val();
	var blodderr = $('#blooderr');
	var skype = $('#skype').val();
	var skypeerr = $('#skypeerr');
	var dob = $('#dob').val();
	var actualDOB=$('#actualdob').val();
	var employeeType=$("#employeetype option:selected").val();
	var file = $('#filename').val();
	var yearofexp=$("#yearofexp").val();
	var variable = $('#variable').val();
	var variableerr = $('#variableerr');
	var Gender = $("#gender option:selected").val();
	var doj=$("#doj").val();
	var college = $('#collagename').val();
	var lastWorkingDay=	$('#lastwrkday').val();
	var flag = true;
	
	$('.error').css('visibility', 'visible');

		
		if (!flag == this.employeeTypeValidate(flag)
				& !flag == this.GenderValidate(flag)
				& !flag == this.employeeIdValidate(flag)
				& !flag == this.employeeYearsOfExperienceValidate(flag)
				& !flag == this.employeeEmailValidate(flag)
				& !flag == this.employeeEmergencyContactNameValidate(flag)
				& !flag == this.employeeEmergencyContactNumberValidate(flag)
				& !flag == this.employeeFathersNameValidate(flag)
				& !flag == this.employeeSalaryValidate(flag)
				& !flag == this.employeeHighestQualification(flag)
				& !flag == this.employeeContactNumberValidate(flag)
				& !flag == this.employeeCurrentAddressValidate(flag)
				& !flag == this.employeePerminentAddressValidate(flag)
				& !flag == this.employeeNameValidate(flag)
				& !flag == this.employeeDOBValidate(flag)
				& !flag == this.employeeDOJValidate(flag)
				& !flag == this.employeeUniversityValidate(flag)
				& !flag == this.employeeActualDOBValidate(flag)
				& !flag == this.employeeIdValidate(flag)
				& !flag == this.employeeEmailValidate(flag)
				& !flag == this.employeeNonMandatoryFieldsValidation(flag)){
			
		
	if(typeof fileId==='undefined'){
		
		fileId=$('#fileid').val();
	}
		var input = {
			"payload" : {
				"id" : empid,
				"employeeId" : eid,
				"employeeName" : name,
				"gender" : Gender,
				"fathersName" : fathername,
				"bloodGroup" : blood,
				"highestQualification" : qualification,
				"yearsofexperience" : yearofexp,
				"salary" : salary,
				"variableComponent" : variable,
				"dateOfBirth" : dob + " 00:00:00",
				"actualdateOfBirth" : actualDOB + " 00:00:00",
				"contactNo" : contnum,
				"currentAddress" : currentaddr,
				"permanentAddress" : peraddr,
				 "email" : email,
				// "password" : password,
				"pan" : pannum,
				"pfNo" : pfnum,
				"bankAccountNo" : accountnum,
				"emergencycontactnumber" : txtemercon,
				"emergencyContactName" : txtemname,
				"relationWithEmergencyConatact" : relation,
				"skype" : skype,
				"fileId" : fileId,
				"employeeType" : employeeType,
				"dateOfJoining": doj + " 00:00:00",
				"lastWorkingDay" : lastWorkingDay +" 00:00:00",
				"university" : college
			}
		};
		RequestManager.hrupdateEmp(input, function(data, success) {
			if (success) {
				alert("Employee ID:" + eid + " Details Successfully Updated");
				routie("employee");
			} else if(data.code==312){
 			   alert(data.message);
 		   }else {
				alert("failed to Update the Details");
			}
		}.ctx(this));

		}else{
			
			console.log(flag);
		}
}

HrEditEmployee.prototype.employeeIdValidate = function(flag) {
	var empId = $("#eid").val();
	var err = $("#eiderr");
	if (empId == "") {
		$(err).text("Required field");
		$(err).css("color", "red");

	} else if (!(empId.match(idReg))) {

		$(err).text("Please enter numbers and characters only");
		$(err).css("color", "red");

	} else if (empId == 0) {
		$(err).text("Employeeid 0 is not allowed");
		$(err).css("color", "red");

	} else {
		$(err).text("");
		flag = false;

	}
	return flag;
}
HrEditEmployee.prototype.employeeEmailValidate = function(flag) {

	var emlerr = $("#emlerr");
	var email = $("#emal").val();
	if (email == "") {
		$(emlerr).text("Required field");
		$(emlerr).css("color", "red");
	} else if (!(email.match(mail))) {
		$(emlerr).text("Please enter a valid email address");
		$(emlerr).css("color", "red");
	} else {
		$(emlerr).text("");
		flag = false;

		// $(emlerr).css("color", "green");

	}
	return flag;

}
HrEditEmployee.prototype.GenderValidate = function(flag) {
	var Gender = $("#gender option:selected").val();
	var error = document.getElementById('generr');
	if (Gender == "") {
		error.innerHTML = "Required field";
		$(error).css("color", "red");
	} else {
		$(error).text("");
		flag=false;
		

		// $(error).css("color", "green");
	}
	return flag;
}
HrEditEmployee.prototype.employeeTypeValidate = function(flag) {
	var empType = $("#employeetype option:selected").val();
	var empTypeError = document.getElementById('employeetypeerr');
	if (empType == "") {
		empTypeError.innerHTML = "Please select employee type";
		$(empTypeError).css("color", "red");
	} else {
		$(empTypeError).text("");
		flag=false;
		
	}
	return flag;
}

HrEditEmployee.prototype.employeeNameValidate = function(flag) {
	var nameerr = $("#nameerr");
	var name = $("#name").val();
	if (name == "") {
		$(nameerr).text("Required field");
		$(nameerr).css("color", "red");
	} else if (name.charAt(0) == " ") {
		$(nameerr).text("First letter shouldn't be space");
		$(nameerr).css("color", "red");
	} else if (!(name.match(char) || name == isNaN)) {
		$(nameerr).text("Please enter characters only");
		$(nameerr).css("color", "red");
	} else if (name.length < 2) {
		$(nameerr).text("Please enter min 3 chars");
		$(nameerr).css("color", "red");
	} else {
		$(nameerr).text("");
		flag = false;
	}
	return flag;
}
HrEditEmployee.prototype.employeeDOBValidate = function(flag) {
	var dob = $('#dob').val();
	if (dob == "") {
		$('#dateerr').text('Please enter the DOB').css('color', 'red');
	} /*else if (!(dob.match(dateformat))) {
		$('#dateerr').text('Please enter in the format of YYYY-MM-DD').css('color', 'red');
	} */else {
		$('#dateerr').text("");
		flag = false;
		// $('#dateerr').css("color", "green");
	}

	return flag;
}
HrEditEmployee.prototype.employeeActualDOBValidate = function(flag) {
	var actualDOB = $('#actualdob').val();
	if (actualDOB == "") {
		$('#actualdoberr').text('Please enter the Actual DOB').css('color',
				'red');
	}/* else if (!(actualDOB.match(dateformat))) {
		$('#actualdoberr').text('Please enter in the format of YYYY-MM-DD')
				.css('color', 'red');
	}*/ else {
		$('#actualdoberr').text("");
		flag = false;
		// $('#dateerr').css("color", "green");
	}
	return flag;

}

HrEditEmployee.prototype.employeeHighestQualification = function(flag) {
	var qualification = $("#qual").val();
	var nerr = $("#qualerr");
	if (qualification == "") {
		$(nerr).text("Required field");
		$(nerr).css("color", "red");
	} else if (qualification.charAt(0) == " ") {
		$(nerr).text("First letter shouldn't be space");
		$(nerr).css("color", "red");
	} else if (!(qualification.match(qual) || qualification.match(char))) {
		$(nerr).text("Please check the qualification ");
		$(nerr).css("color", "red");
	} else {
		$(nerr).text("");
		flag = false;
		
		// $(nerr).css("color", "green");

	}
	return flag;

}
HrEditEmployee.prototype.employeeSalaryValidate = function(flag) {
	var salerr = $("#salerr");
	var salary = $("#salary").val();
	if (salary == "") {
		$(salerr).text("Required field");
		$(salerr).css("color", "red");
	} else if (salary == isNaN || !(salary.match(salry))) {
		$(salerr).text("Please enter numbers only eg:90.00");
		$(salerr).css("color", "red");
	} else {
		$(salerr).text("");
		flag = false;
		
		// $(salerr).css("color", "green");

	}
	return flag;

}
HrEditEmployee.prototype.employeeUniversityValidate = function(flag) {
	var college = $('#collagename').val();
	if (college == "") {
		$('#collegeerr').text("Required field");
		$('#collegeerr').css("color", "red");
	} else {
		$('#collegeerr').text("");
		flag = false;
		return flag;
		// $(skillerr).css("color", "green");

	}

}
HrEditEmployee.prototype.employeeFathersNameValidate = function(flag) {
	var fathererr = $("#fathererr");
	var fathername = $("#fathername").val();
	if (fathername == "") {
		$(fathererr).text("");
		flag = false;
	
	}
		/*$(fathererr).text("Required field");
		$(fathererr).css("color", "red");*/
	/*else if (!(fathername.match(char) || fathername == isNaN)) {
		$(fathererr).text("Please enter characters only");
		$(fathererr).css("color", "red");
	}*/ else {
		$(fathererr).text("");
		flag = false;
		
		// $(fathererr).css("color", "green");
	}
	return flag;

}
HrEditEmployee.prototype.employeeContactNumberValidate = function(flag) {
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
HrEditEmployee.prototype.employeeCurrentAddressValidate = function(flag) {
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
HrEditEmployee.prototype.employeePerminentAddressValidate = function(flag) {
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
HrEditEmployee.prototype.employeeEmergencyContactNumberValidate = function(flag) {
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
HrEditEmployee.prototype.employeeEmergencyContactNameValidate = function(flag) {
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
HrEditEmployee.prototype.employeeEmailValidate = function(flag) {

	var emlerr = $("#emlerr");
	var email = $("#emal").val();
	if (email == "") {
		$(emlerr).text("Required field");
		$(emlerr).css("color", "red");
	} else if (!(email.match(mail))) {
		$(emlerr).text("Please enter a valid email address");
		$(emlerr).css("color", "red");
	} else {
		$(emlerr).text("");
		flag = false;
		
		// $(emlerr).css("color", "green");

	}
	return flag;

}

HrEditEmployee.prototype.employeeYearsOfExperienceValidate = function(flag) {
	var yearofexperr = $("#yearexperr");
	var yearexp = $("#yearofexp").val();
	if (yearexp == "") {
		$(yearofexperr).text("Required field");
		$(yearofexperr).css("color", "red");
	} else if (!(yearexp.match(exprnce))) {
		$(yearofexperr).text("Only numbers and dot are allowed");
		$(yearofexperr).css("color", "red");
	}

	else {
		$(yearofexperr).text("");
		flag = false;
		
		// $(yearexperr).css("color", "green");

	}
	return flag;

}

HrEditEmployee.prototype.employeeDOJValidate = function(flag) {

	var doj = $('#doj').val();
	if (doj == "") {
		$('#dojerr').text('Please enter the DOJ').css('color', 'red');

	} /*else if (!(doj.match(dateformat))) {
		$('#dojerr').text('Please enter in the format of YYYY-MM-DD').css(
				'color', 'red');
	} */else {
		$('#dojerr').text("");
		flag = false;

		// $('.dojerr').css("color", "green");
	}
	return flag;

}

HrEditEmployee.prototype.employeeNonMandatoryFieldsValidation = function(flag) {

	var panerr = $("#panerr");
	var pannum = $("#pannum").val();
	var pferr = $("#pferr");
	var pfnum = $("#pfnum").val();
	var acterr = $("#acterr");
	var variable = $('#variable').val();
	var variableerr = $('#variableerr');
	var accountnum = $("#accountnum").val();
	var skype = $('#skype').val();
	var skypeerr = $('#skypeerr');
	var relationerr = $("#relationerr");
	var relation = $("#relation").val();
	if (skype == "") {
		$(skypeerr).text("");
		flag=false;
	   }
	else if (!(skype.match(skp))) {
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

	if (pannum == "") {
		$(panerr).text("");
		flag = false;
		
	} else if (pannum == isNaN || !(pannum.match(letters))) {
		$(panerr).text("Please enter a valid pan");
		$(panerr).css("color", "red");
		flag = true;
		return flase;
	} else {
		$(panerr).text("");
		flag = false;
		
		// $(panerr).css("color", "green");
	}
	if (pfnum == "") {
		$(pferr).text("");
		flag = false;
	
	} else if (!(pfnum.match(letters))) {
		$(pferr).text("Please enter a valid pf number");
		$(pferr).css("color", "red");
		flag = true;
		return flase;
	} else if (!(pfnum.length == 16)) {
		$(pferr).text("Length should be 16 letters");
		$(pferr).css("color", "red");
		flag = true;
		return flase;
	} else {
		$(pferr).text("");
		flag = false;
		
		// $(pferr).css("color", "green");
	}
	if (accountnum == "") {
		$(acterr).text("");
		flag = false;
	
	} else if (accountnum == isNaN || !(accountnum.match(num))) {
		$(acterr).text("Please enter a valid account number");
		$(acterr).css("color", "red");
		flag = true;
		return flase;
	} else if (!(accountnum.length == 15)) {
		$(acterr).text("Please enter 15 digits only");
		$(acterr).css("color", "red");
		flag = true;
		return flase;
	} else {
		$(acterr).text("");
		flag = false;
		
		// $(acterr).css("color", "green");
	}

	if (variable == "") {
		$(variableerr).text("");
		flag = false;
		
	} else if (variable == isNaN || !(variable.match(salry))) {
		$(variableerr).text("Please enter a valid decimal value ex: 100.00");
		$(variableerr).css("color", "red");
		flag = true;
		return flase;
	} else {
		$(variableerr).text("");
		flag = false;
		
		// $(salerr).css("color", "green");

	}

	if (relation == "") {
		$(relationerr).text("");
		flag=false;
	}
	else if (!(relation.match(char))) {
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

HrEditEmployee.prototype.deleteEmployee = function(empid) {
	$('#lastwrkday').val("");
	var text = confirm("Are you sure you want to delete this employee?");
	if (text == true) {
		$('#LastWorkingDay').show();
		
		 $('#LastWorkingDay').dialog({
             modal: true,
             buttons:
           { "Cancel": function() {
               $(this).dialog("close")
           },
               "Submit": function() {
            	  
            	  var lastWrkingDay=$('#lastwrkday').val();
            	  if(lastWrkingDay==""){
            		  
            		  alert("Please Select the Last working day");
            	  }
            	  var input={"payload":{"employeeId":empid,"lastWorkingDay":lastWrkingDay + " 00:00:00"}}
            	   RequestManager.saveLastWorkingDay(input, function(data, success) {
            		   if(success){
            			   
            			   var employeeId=data.employeeId;
            			   var inputTodelete = {
            						"payload" : {
            							"employeeId" : employeeId
            						}
            					};
            					RequestManager.hrDeleteEmployee(inputTodelete, function(data, success) {
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
            							$("#actualdob").val("");
            							$('#doj').val("");
            							$("#emal").val("");
            							$("#password").val("");
            							$('#blood').val("");
            							$("#salary").val("");
            							$('#variable').val("");
            							$('#gender').val("");
            							$('#employeetype').val("");
            							$('#skype').val("");
            							$("#yearofexp").val("");
            							$('#filepath').text("");
            							// document.getElementById("hredit").disabled = true;
            							document.getElementById("hrupdate").disabled = true;
            							document.getElementById("hrdelete").disabled = true;
            							routie("employee");
            						} else {
            							alert("failed to Delete");
            						}
            					}.ctx(this));

            		   }else if(data.code==312){
            			   
            			   alert(data.message);
            		   }else{
            			   
            			   alert("Failed to delete employee");
            		   }
            		   
            	   }.ctx(this));
            	  $(this).dialog("close")
            } }
        });
		
}
}
