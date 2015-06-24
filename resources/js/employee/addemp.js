var fileId;
function AddEmployee() {
	Loader.loadHTML(".container", "resources/js/employee/addemp.html", true,
			function() {
				this.handleShow();
			}.ctx(this));
}
AddEmployee.prototype.handleShow = function() {
	$('.container').show();
	
	$('#removedesignation').hide();
	$('#savedesignation').hide();
	$('#deletedesignation').hide();
	
	this.dropDown();
	$('#adddesignation').click(function(){
		$('#extradesg').css("visibility","visible");
		$('#deletedesignation').hide();
		$('#savedesignation').show();
	}.ctx(this));
	
	// This function is to add new designation type
	$('#savedesignation').click(function(){
		var val=$('#extradesg').val();
		
		if(val==""){
			
			alert("Please enter designation type to be added");
		}
		 $('#designation').append($("<option value="+newvalue+">" + val + "</option>"));
		 var designationinput={"payload":{"name":val}};
		 RequestManager.addDesignationType(designationinput, function(data, success){
			 if(success){
				 
				 alert("New designation is succesfully added in the dropdown list");
				
			 }
		
			 
		 }.ctx(this));
		 
		 $('#extradesg').val("");
		 $('#extradesg').css("visibility","hidden");
			$('#savedesignation').hide();	
			return false;
	}.ctx(this));
	/*$('#removedesignation').click(function(){
		$('#extradesg').css("visibility","visible");
		$('#savedesignation').hide();
		$('#deletedesignation').show();
		designation=$( "#designation option:selected" ).text();
		
	    $('#extradesg').val(designation);
		
	}.ctx(this));
	
	$('#deletedesignation').click(function(){ 
		
		//$("#designation option[value='"+designation+"']").remove();
		
		  var inputtodelete= $('#designation option:selected').val();
		  
		  var inputpayload={"payload":{"id":inputtodelete}}
		  
		 RequestManager.deleteDesignationType (inputpayload, function(data, success){
		
			 if(success){
				 alert("Designation successfully removed from the dropdown list");
				 $("#designation").append($("<option value="+0+">"+Select Designation+"</option>"));
				 //$('#designation').append($("<option value="Select Designation">Select Designation</option>"));
				 $('#designation').empty();
				 this.dropDown();
			 }
			 
		 }.ctx(this));
	 $('#extradesg').val("");
	 $('#extradesg').css("visibility","hidden");
		$('#deletedesignation').hide();	
		return false;
}.ctx(this));
	*/
	$('#dob').datepicker({
		// dateFormat : $.datepicker.TIMESTAMP,
		dateFormat : 'yy-mm-dd',
		showButtonPanel : true,
		changeMonth : true,
		changeYear : true,
		showAnim : 'drop',
		minDate : new Date(1960, 12, 31),
		maxDate : new Date(1994, 12, 31)
	})
	$('#actualdob').datepicker({
		// dateFormat : $.datepicker.TIMESTAMP,
		dateFormat : 'yy-mm-dd',
		showButtonPanel : true,
		changeMonth : true,
		changeYear : true,
		showAnim : 'drop',
		minDate : new Date(1960, 12, 31),
		maxDate : new Date(1994, 12, 31)
	})


	$('#doj').datepicker({
		// dateFormat : $.datepicker.TIMESTAMP,
		dateFormat : 'yy-mm-dd',
		showButtonPanel : true,
		changeMonth : true,
		changeYear : true,
		showAnim : 'drop',
		minDate : new Date(2010, 10, 30),
		maxDate : new Date(2015, 5, 31)
	})

	$('#filename').one('click', UploadClickHandler.ctx(this));
	function UploadClickHandler(event) {
		var thisEle = event.target;
		this.uploadMedia(function() {
			$(thisEle).one('click', UploadClickHandler.ctx(this));
		}.ctx(this));
	}

	$('#add').click(function(e) {
		e.preventDefault();
		this.validateEmp();

	}.ctx(this));
$('#backaddemp').click(function(){
		routie("employee");
	}.ctx(this));

	$('#resetaddemp').click(function(e) {
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
		$('#actualdob').val("");
		$('#doj').val("");
		$("#emal").val("");
		/*$("#password").val("");*/
		$('#blood').val("");
		$("#salary").val("");
		$('#variable').val("");
		$('#designation').val("");
		$('#gender').val("");
		$('#filepath').text("");
		$('#employeetype').val("");
		$('#collagename').val("");

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
// validating each employee field
AddEmployee.prototype.validateEmp = function() {
	var char = /^[A-Za-z .]+( [A-Za-z]+)*$/;
	var qual = /^[A-Za-z]+(.[A-Za-z]+)*$/;
	var skp = /^[A-Za-z0-9]+(.[A-Za-z0-9]+)*$/;
	var sklr = /^[A-Za-z0-9]+(,.[A-Za-z0-9]+)*$/;
	var num = /^[0-9]+$/;
	var mail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var illegalChars = /\W/
	var letters = "^[a-zA-Z0-9-]*$";
	var exprnce= /^[0-9.]+$/;
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
/*	var password = $("#password").val();*/
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
	var actualDOB=$('#actualdob').val();
	var doj = $('#doj').val();
	var employeeType=$('#employeetype').val();
	var college=$('#collagename').val();
	var file = $('#filename').val();
	var variable = $('#variable').val();
	var variableerr = $('#variableerr');
	var Gender = $("#gender option:selected").val();
	var bloodGroup = /^(A|B|AB|O|0)(\+|-)+$/;
	var salry = /^\d{0,10}(?:\.\d{0,2})?$/;
	var dateformat = /^(19|20)\d\d-(0\d|1[012])-(0\d|1\d|2\d|3[01])$/;
	var flag = true;

	/*
	 * if (blood == "" || eid == "" || name == "" || qualification == "" ||
	 * email == "" || fathername == "" || contnum == "" || txtemercon == "" ||
	 * txtemname == "" || password == "" || salary == "" || currentaddr == "" ||
	 * peraddr == "" || relation == "" || yearexp == "" || skill == "" || rating == "" ||
	 * skype == "" || dob == "" || doj == "") {
	 */
	$('.error').css('visibility', 'visible');

	if (dob == "") {
		$('#dateerr').text('Please enter the DOB').css('color', 'red');
	} else if (!(dob.match(dateformat))) {
		$('#dateerr').text('Please enter in the format of YYYY-MM-DD').css(
				'color', 'red');
	} else {
		$('#dateerr').text("");
		flag = false;
		// $('#dateerr').css("color", "green");
	}
	if (actualDOB == "") {
		$('#actualdoberror').text('Please enter the Actual DOB').css('color', 'red');
	} else if (!(actualDOB.match(dateformat))) {
		$('#actualdoberror').text('Please enter in the format of YYYY-MM-DD').css(
				'color', 'red');
	} else {
		$('#actualdoberror').text("");
		flag = false;
		// $('#dateerr').css("color", "green");
	}
	if (doj == "") {
		$('#dojerr').text('Please enter the DOJ').css('color', 'red');

	} else if (!(doj.match(dateformat))) {
		$('#dojerr').text('Please enter in the format of YYYY-MM-DD').css(
				'color', 'red');
	} else {
		$('#dojerr').text("");
		// $('.dojerr').css("color", "green");
		flag = false;
	}

	if (skype != "") {
		 if (!(skype.match(skp))) {
		$(skypeerr).text("Please enter a valid skype ID");
		$(skypeerr).css("color", "red");
	} else if (!(skype.length > 5)) {
		$(skypeerr).text("Please enter minimum 6 letters");
		$(skypeerr).css("color", "red");
	} else {
		$(skypeerr).text("");
		flag = false;
		// $(skypeerr).css("color", "green");

	}
	}
	if(blood!=""){

 if (!(blood.match(bloodGroup))) {
		$(blodderr).text("Please enter a valid blood group ex:AB+, AB-");
		$(blodderr).css("color", "red");
	} else if (!(blood.length < 4)) {
		$(blodderr).text("Please enter 3 characters only");
		$(blodderr).css("color", "red");
	} else {
		$(blodderr).text("");
		flag = false;
		// $(blodderr).css("color", "green");

	}
	}
	if (eid == "") {
		$(err).text("Required field");
		$(err).css("color", "red");
	} else if (!(eid.match(letters))) {
		$(err).text("EID accepts numbers and characters only");
		$(err).css("color", "red");
	} else {
		$(err).text("");
		flag = false;
		// $(err).css("color", "green");

	}

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
		// $(nameerr).css("color", "green");

	}

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

	if (fathername == "") {
		$(fathererr).text("Required field");
		$(fathererr).css("color", "red");
	} else if (!(fathername.match(char) || fathername == isNaN)) {
		$(fathererr).text("Please enter characters only");
		$(fathererr).css("color", "red");
	} else if (fathername < 5) {
		$(fathererr).text("Please enter min 6 characters ");
		$(fathererr).css("color", "red");
	} else {
		$(fathererr).text("");
		flag = false;
		// $(fathererr).css("color", "green");

	}

	if (pannum == "") {
		$(panerr).text("");
	} else if (pannum == isNaN || !(pannum.match(letters))) {
		$(panerr).text("Please enter a valid pan");
		$(panerr).css("color", "red");
	} else if (!(pannum.length == 10)) {
		$(panerr).text("Please enter 10 letters only");
		$(panerr).css("color", "red");
	} else {
		$(panerr).text("");
		flag = false;
		// $(panerr).css("color", "green");
	}
	if (pfnum == "") {
		$(pferr).text("");
	} else if (!(pfnum.match(letters))) {
		$(pferr).text("Please enter a valid pf number");
		$(pferr).css("color", "red");
	} else if (!(pfnum.length == 16)) {
		$(pferr).text("Please check the pf format");
		$(pferr).css("color", "red");
	} else {
		$(pferr).text("");
		flag = false;
		// $(pferr).css("color", "green");
	}
	if (accountnum == "") {
		$(acterr).text("");
	} else if (accountnum == isNaN || !(accountnum.match(num))) {
		$(acterr).text("Please enter a valid account number");
		$(acterr).css("color", "red");
	} else if (!(accountnum.length == 15)) {
		$(acterr).text("Please enter 15 digits only");
		$(acterr).css("color", "red");
	} else {
		$(acterr).text("");
		flag = false;
		// $(acterr).css("color", "green");
	}

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

	if (txtemname == "") {
		$(emnameerr).text("Required field");
		$(emnameerr).css("color", "red");
	}else if (txtemname.charAt(0) == " ") {
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

	if (variable == "") {
		$(variableerr).text("");
		$(variableerr).css("color", "red");
	} else if (variable == isNaN || !(variable.match(salry))) {
		$(variableerr).text("Please enter a valid decimal value ex: 100.00");
		$(variableerr).css("color", "red");
	} else {
		$(variableerr).text("");
		flag = false;
		// $(salerr).css("color", "green");

	}

	if (currentaddr == "") {
		$(currentaddrerr).text("Required field");
		$(currentaddrerr).css("color", "red");
	} else {
		$(currentaddrerr).text("");
		flag = false;
		// $(currentaddrerr).css("color", "green");

	}

	if (peraddr == "") {
		$('#peraddrerr').text("Required field");
		$('#peraddrerr').css("color", "red");
	} else {
		$('#peraddrerr').text("");
		flag = false;
		// $(peraddrerr).css("color", "green");

	}

if (relation != "") {
	 if (!(relation.match(char))) {
		$(relationerr).text("enter characters only");
		$(relationerr).css("color", "red");
	} else {
		$(relationerr).text("");
		flag = false;
		// $(relationerr).css("color", "green");

	}
}
	if (yearexp == "") {
		$(yearexperr).text("Required field");
		$(yearexperr).css("color", "red");
	} else if (!(yearexp.match(exprnce))) {
		$(yearexperr).text("Only numbers and dot are allowed");
		$(yearexperr).css("color", "red");
	}
	/*
	 * else if (!(yearexp.length < 3)) { $(yearexperr).text("max 2 digits
	 * allowed"); $(yearexperr).css("color", "red"); }
	 */
	else {
		$(yearexperr).text("");
		flag = false;
		// $(yearexperr).css("color", "green");

	}

	if (skill == "") {
		$(skillerr).text("Required field");
		$(skillerr).css("color", "red");
	} else if (!(skill.match(sklr))) {
		$(skillerr).text("Please enter a valid skill");
		$(skillerr).css("color", "red");
	} else {
		$(skillerr).text("");
		flag = false;
		// $(skillerr).css("color", "green");

	}
	if (college == "") {
		$('#collegeerr').text("Required field");
		$('#collegeerr').css("color", "red");
	} else {
		$('#collegeerr').text("");
		flag = false;
		// $(skillerr).css("color", "green");

	}

	if (rating == "") {
		$(ratingerr).text("Required field");
		$(ratingerr).css("color", "red");
	} else if (!(rating == isNaN || rating.match(num))) {
		$(ratingerr).text("Please enter only number ");
		$(ratingerr).css("color", "red");
	} else if (!(rating > 0 && rating <= 10)) {
		$(ratingerr).text("Please enter between 1 to 10 ");
		$(ratingerr).css("color", "red");
	} else {
		$(ratingerr).text("");
		flag = false;
	}

	flag = true;
	/*if (password == "") {
		$(pwderr).text("Required field");
		$(pwderr).css("color", "red");
	} else if (!(password.match(pwepattern))) {
		$(pwderr)
				.text(
						"Must be at least 8 characters,At least 1 number, 1 lowercase, 1 uppercase letter, At least 1 special character");
		$(pwderr).css("color", "red");
	}*/ if (!(rating == isNaN || rating.match(num))) {
		
		$(ratingerr).text("Please enter only number ");
		$(ratingerr).css("color", "red");
	} else if (!(rating > 0 && rating <= 10)) {
		$(ratingerr).text("Please enter between 1 to 10 ");
		$(ratingerr).css("color", "red");
	} else if (!(relation.match(char) || relation == isNaN)) {
		
		$(relationerr).text("enter characters only");
		$(relationerr).css("color", "red");
	} else if (salary == isNaN || !(salary.match(salry))) {
		
		$(salerr).text("Please enter numbers only eg:90.00");
		$(salerr).css("color", "red");
	} else if (!(skill.match(sklr))) {
		$(skillerr).text("Please enter a valid skill");
		$(skillerr).css("color", "red");
	} else if (txtemercon == isNaN || !(txtemercon.match(num))) {
		
		$(emnumerr).text("Only numbers allowed");
		$(emnumerr).css("color", "red");
	} else if (!(txtemercon.length == 10)) {
		$(emnumerr).text("Please enter 10 digits only");
		$(emnumerr).css("color", "red");
	} else if (contnum == isNaN || !(contnum.match(num))) {
		
		$(cnumerr).text("Please enter numbers only");
		$(cnumerr).css("color", "red");
	} else if (!(contnum.length == 10)) {
		$(cnumerr).text("Please enter 10 digits only");
		$(cnumerr).css("color", "red");
	} else if (!(qualification.match(qual) || qualification.match(char))) {
		
		$(nerr).text("Please check the qualification ");
		$(nerr).css("color", "red");
	} else if (!(email.match(mail))) {
		$(emlerr).text("Please enter a valid email address");
		$(emlerr).css("color", "red");
	} else if (!(eid.match(letters))) {
		$(err).text("EID accepts numbers and alphabets only");
		$(err).css("color", "red");
	} else if (!(txtemname.match(char) || txtemname == isNaN)) {
		
		$(emnameerr).text("Enter characters only");
		$(emnameerr).css("color", "red");
	}  
	 else {
		flag = false;
		// $(pwderr).css("color", "green");

	}

    this.employeeTypeValidate();
	this.GenderValidate();
	this.ValidateDrpn();
	// sending data to database in the form of json
	if (!flag) {
		// $('.error').css('visibility', 'hidden');

		var input = {
			"payload" : {
				"employeeId" : eid,
				"employeeName" : name,
				"gender" : Gender,
				"dateOfBirth" : dob + " 00:00:00",
				"actualdateOfBirth" : actualDOB + " 00:00:00",
				"dateOfJoining" : doj + " 00:00:00",
				"yearsofexperience" : yearexp,
				"contactNo" : contnum,
				"currentAddress" : currentaddr,
				"permanentAddress" : peraddr,
				"email" : email,
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
				"fileId" : fileId,
				"employeeType" : employeeType,
				"university":college
				
			}
		};
		if (variable == "") {
			variable = "0";
		}
		var input2 = {
			"payload" : {
				"designationId" : designation,
				"empId" : eid,
				"appraisalDate" : doj + " 00:00:00",
				"salary" : salary,
				"variablePay" : variable
			}

		};
		RequestManager.saveDesignation(input2, function(data, success) {
			if (success) {
				return true;
				// alert("Employee ID: "+ $("#eid").val()+ " Details
				// Successfully Added to Designation History");
			} else {
				return false;
			}
		}.ctx(this));

		RequestManager.saveEmp(input, function(data, success) {
			if (success) {
				// $('success').val("Successfully Added")
				alert("Employee ID: " + $("#eid").val()
						+ " Details Successfully Added to Employee List");
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
				$('#actualdob').val("");
				$('#doj').val("");
				$("#emal").val("");
				$('#blood').val("");
				$("#salary").val("");
				$('#variable').val("");
				$('#designation').val("");
				$('#gender').val("");
				$('employeetype').val("");
				routie("employee");
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
		error.innerHTML = "Please select gender";
		$(error).css("color", "red");
	} else {
		$(error).text("");

	}
}
AddEmployee.prototype.ValidateDrpn = function() {
	var designation = document.getElementById('designation').value;
	var descerr = document.getElementById('descerr');
	if (designation == "") {
		descerr.innerHTML = "Please select designation";
		$(descerr).css("color", "red");
	} else {
		$(descerr).text("");

	}
}
AddEmployee.prototype.employeeTypeValidate = function() {
	var empType = $("#employeetype option:selected").val();
	var empTypeError = document.getElementById('employeetypeerr');
	if (empType == "") {
		empTypeError.innerHTML = "Please select employee type";
		$(empTypeError).css("color", "red");
	} else {
		$(empTypeError).text("");

	}
}

// This function is to append designation types from database to a drop down list
AddEmployee.prototype.dropDown = function(){
	var emptyinput={};
	 RequestManager.getDesignationName(emptyinput, function(data, success){
		               
		 if(success){
			          arraylength=data.length;
			           lastId=data[arraylength-1].id;
			           newvalue=lastId+1;
			 for(i=0;i<data.length;i++){
			  var object=data[i];
			 $('#designation').append($("<option value="+object.id+"> "+object.name+"</option>"));			 
			 }
			 
		 }
		 
	 }.ctx(this));
	
}



// var AddEmployee = new AddEmployee();