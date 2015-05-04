function editdesignation() {
	Loader.loadHTML('.container',
			'resources/js/editdesignation/editdesignation.html', true,
			function() {
				this.handleShow();
			}.ctx(this));
}
var salaryVal = 0;
var dateVal = 0;
var dropDownVal = 0;
var variablePayVal = 0;
editdesignation.prototype.handleShow = function() {
	$('.container').show();
	var empId =94;
	var empName1 = "Bhargavi";
	var doj1 = "2015-04-01";
	$('#employeeName').val(empName1);
	$('#doj').val(doj1);
	$("#datepicker").datepicker({
		dateFormat : 'yy-mm-dd',
		showButtonPanel : true,
		changeMonth : true,
		changeYear : true,
		showAnim : 'drop',
		minDate : new Date(1993, 12, 31),
		maxDate : new Date(2050, 12, 31)
	});
	this.getDesignationHistory();

	$('#save_designations').click(
			function() {
				this.validateDesignation();
				if (salaryVal == 1 & dateVal == 1 & dropDownVal == 1
						& variablePayVal == 1) {
					this.sendJson();
				}

			}.ctx(this));
	
	$('#reset_designations').click(function(){
	$("#datepicker").val("");
	$("#designation").val("");
	$("#salary").val("");
	$("#variablePay").val("");
//for error messages	
	$('#isSEmpty').text("");
	$('#isDEmpty').text("");
	$('#isVPEmpty').text("");
	$('#isDesignationEmpty').text("");
	});
	
}
editdesignation.prototype.validateDesignation = function() {

	this.salaryValidate();

	this.dateValidate();

	this.variablePayValidate();
	this.dropDownValidate();

}

// salary validations
editdesignation.prototype.salaryValidate = function() {
	var salary = $('#salary').val();
	var letters = /^\d{1,10}(?:\.\d{0,2})?$/;
	if (salary.match(letters)) {
		$('#isSEmpty').text("");
		salaryVal = 1;
	} else {
		$('#isSEmpty')
				.text(
						"Enter only numbers");
		salaryVal = 0;
		return false;

	}
}
// date validations
editdesignation.prototype.dateValidate = function() {
	if ($("#datepicker").val() == "") {
		$('#isDEmpty').text("Enter date");
		dateVal = 0;
		return false;

	} else {
		$('#isDEmpty').text("");
		dateVal = 1;
	}
}

// variable pay validations
editdesignation.prototype.variablePayValidate = function() {
	var varPay = $("#variablePay").val();
	var letters = /^\d{1,10}(?:\.\d{0,2})?$/;
	if (varPay.match(letters)) {
		$('#isVPEmpty').text("");
		variablePayVal = 1;
	} else {
		$('#isVPEmpty')
				.text(
						"Enter only numbers");
		variablePayVal = 0;
		return false;

	}
}

// dropdown validations
editdesignation.prototype.dropDownValidate = function() {
	var desId = $("#designation").val();
	if (desId == "0") {
		$('#isDesignationEmpty').text("Please Select Designation");
		dropDownVal = 0;
		return false;
	} else {
		$('#isDesignationEmpty').text("");
		dropDownVal = 1;
	}
}

editdesignation.prototype.sendJson = function() {
	var empId = 94;
	var empName1 = "Bhargavi";
	var doj1 = "2015-04-01";
	var date1 = $("#datepicker").val() + ' 00:00:00';
	var designationId1 = $("#designation").val();
	var salary1 = $("#salary").val();
	var variablePay1 = $("#variablePay").val();
	var input = {
		"payload" : {
			"empId" : empId,
			"appraisalDate" : date1,
			"designationId" : designationId1,
			"salary" : salary1,
			"variablePay" : variablePay1
		}
	};
	RequestManager
			.saveDesignation(
					input,
					function(data, success) {

						if (success) {

							console.log("Successfully added to database");
							var input1 = {
								"payload" : {}
							};
							RequestManager
									.getDesignationName(
											input1,
											function(data1, success) {
												if (success) {
													console
															.log("Successfully retrieved  from DesType .........");
													this.appendDesTable(data,
															data1, status);
												} else {
													console
															.log("Failed to retrieved  from DesType .........");
												}

											}.ctx(this));

						} 
						else if(data.code == 227)
						{
							alert("This record was saved already");

						}

					}.ctx(this));

}

editdesignation.prototype.appendDesTable = function(data, data1, status) {
	var j;
	var desId = data.designationId;
	var desName;
	var desTypeObj;
	for (j = 0; j < data1.length; j++) {
		desTypeObj = data1[j];
		if (desId == desTypeObj.id) {
			desName = desTypeObj.name;
			break;
		}

	}
	var table = $('.table').children();
	var value = data.appraisalDate;
	var res = new Date(value);
	var year = res.getFullYear();
	var month = res.getMonth() + 1;
	var date = res.getDate();
	var apprDate = year + "-" + month + "-" + date;
	table.append("<tr><td>" + desName + "</td><td>" + data.salary + "</td><td>"
			+ data.variablePay + "</td><td>" + apprDate + "</td></tr>");
	alert("Sucessfully saved the details");
	$("#datepicker").val("");
	$("#designation").val("");
	$("#salary").val("");
	$("#variablePay").val("");

}

editdesignation.prototype.getDesignationHistory = function() {
	var input = {
		"payload" : {
			"empId" : 94
		// rahul's view page
		}
	};

	RequestManager
			.getDesignation(
					input,
					function(data, success) {
						if (success) {
							console
									.log("successfully retrieved  from DesHistory .........");
							var content = data;
							var status = success;

							var input1 = {
								"payload" : {}
							};
							RequestManager
									.getDesignationName(
											input1,
											function(data1, success) {
												if (success) {
													console
															.log("successfully retrieved  from DesType .........");
													this.contentDisplay(
															content, data1,
															status);
												} else {
													console
															.log("failed to retrieved from DesType");
												}
											}.ctx(this));
						} else {

							console.log("failed to retrieved from DesHistory ");
						}

					}.ctx(this));

}

editdesignation.prototype.contentDisplay = function(content, data1, status) {
	var table = $('.table').children();
	var i;
	var j;
	var obj;
	var desName;
	var desTypeObj;
	for (i = 0; i < content.length; i++) {
		obj = content[i];
		var desId = obj.designationId;
		for (j = 0; j < data1.length; j++) {
			desTypeObj = data1[j];
			if (desId == desTypeObj.id) {
				desName = desTypeObj.name;
				break;
			}

		}
		var value = obj.appraisalDate;
		var res = new Date(value);
		var year = res.getFullYear();
		var month = res.getMonth() + 1;
		var date = res.getDate();
		var apprDate = year + "-" + month + "-" + date;
		table.append("<tr><td>" + desName + "</td><td>" + obj.salary
				+ "</td><td>" + obj.variablePay + "</td><td>" + apprDate
				+ "</td></tr>");

	}
}