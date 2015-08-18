function editdesignation(empid, name, doj) {
	Loader.loadHTML('.container',
			'resources/js/editdesignation/editdesignation.html', true,
			function() {
				this.handleShow(empid, name, doj);
			}.ctx(this));
}
var salaryVal = 0;
var dateVal = 0;
var dropDownVal = 0;
var variablePayVal = 0;
editdesignation.prototype.handleShow = function(empid, name, dojoin) {
	
	$('#save_designations').show();
	$('#updateDesignation').hide();
	$('.container').show();
	
	
	var emptyinput={};
	 RequestManager.getDesignationName(emptyinput, function(data, success){
		               
		 if(success){
			 for(i=0;i<data.length;i++){
			  var object=data[i];
			 $('#designation').append($("<option value="+object.id+"> "+object.name+"</option>"));
			 
			 }
			 
		 }
		 
	 }.ctx(this));
	 
	 
	var empId = empid;
	var empName1 = name;
	var doj1 = dojoin;
	$('#employeeName1').val(empName1);
	$('#doj').val(doj1);
	var currentYear = (new Date).getFullYear();
	$("#datepicker").datepicker({
		dateFormat : 'yy-mm-dd',
		showButtonPanel : true,
		changeMonth : true,
		changeYear : true,
		showAnim : 'drop',
		minDate : new Date((currentYear-1),1,1),
		maxDate : new Date()
	});
	$("#datepicker").datepicker("setDate", new Date());
	this.getDesignationHistory(empid);
	
	$('#save_designations').click(
			function() {
				this.validateDesignation();
				if (salaryVal == 1 & dateVal == 1 & dropDownVal == 1
						& variablePayVal == 1) {
					this.sendJson(empid);
				}

			}.ctx(this));

	$('#reset_designations').click(function() {
		$("#datepicker").val("");
		$("#designation").val("");
		$("#salary").val("");
		$("#variablePay").val("");
		$("#datepicker").datepicker("setDate", new Date());
		// for error messages
		$('#isSEmpty').text("");
		$('#isDEmpty').text("");
		$('#isVPEmpty').text("");
		$('#isDesignationEmpty').text("");
	});
	$('#backDes').click(function(){
		App.loadViewEmployee(empid);
	});
	
var self=this;
	
	$('.dynamicEditdesgn').live('click', function(event)  {
		event.stopImmediatePropagation();	
		$('#save_designations').hide();
		$('#updateDesignation').show();
		$("#datepicker").val("");
		$("#designation").val("");
		$("#salary").val("");
		$("#variablePay").val("");
		$("#datepicker").datepicker("setDate", new Date());
	    var releaseId = event.target.id;
	    self.editDesignation(releaseId,empid,empName1,doj1);
	}.ctx(this));
	
	
	$('.dynamicDeleteDesgn').live('click', function(event)  {
		event.stopImmediatePropagation();	
		$("#datepicker").val("");
		$("#designation").val("");
		$("#salary").val("");
		$("#variablePay").val("");
		$("#datepicker").datepicker("setDate", new Date());
	
	    var releaseId = event.target.id;
	    self.deleteDesignation(releaseId,empName1,doj1);
	}.ctx(this));
	
}

 


editdesignation.prototype.sendJson = function(empid) {
	var empId = empid;
	var date1 = $("#datepicker").val() + ' 00:00:00';
	var designationId1 = $("#designation").val();
	var salary1 = $("#salary").val();
	var variablePay1 = $("#variablePay").val();
	if(variablePay1=="")
	{
		variablePay1=0;
	}
	var input = {
		"payload" : {
			"empId" : empId,
			"appraisalDate" : date1,
			"designationId" : designationId1,
			"salary" : salary1,
			"variablePay" : variablePay1
		}
	};
	RequestManager.saveDesignation(input,function(data, success) {

						if (success) {

							console.log("Successfully added to database");
							RequestManager.updateDesignationDetails(input,function(data2,success){
							if(success)
							{
								//console.log("updated in employee table");
							}
							else
							{
								//console.log("failed to update in employee table");
							}
							}.ctx(this));
							var input1 = {
								"payload" : {}
							};
							RequestManager.getDesignationName(input1,function(data1, success) {
												if (success) {
													//console.log("Successfully retrieved  from DesType .........");
													this.appendDesTable(data,data1, status);
												} else {
													//console.log("Failed to retrieved  from DesType .........");
												}

											}.ctx(this));

						} else if (data.code == 227) {
							alert("Designation details already exists");

						}

					}.ctx(this));

}


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
	var month = monthsArray[res.getMonth()];
	var date = res.getDate();
	var apprDate = date + "-" + month + "-" + year;
	table.append("<tr><td>" + desName + "</td><td>" + data.salary + "</td><td>"
			+ data.variablePay + "</td><td>" + apprDate + "</td><td><input type=button id='"+data.id+"' class='dynamicEditdesgn btn-info' value='Edit'></td><td><input type=button id='"+data.id+"' class='dynamicDeleteDesgn btn-warning' value='Delete'></td></tr>");
	alert("Sucessfully saved the designation details");
	$("#datepicker").val("");
	$("#designation").val("");
	$("#salary").val("");
	$("#variablePay").val("");
	$("#datepicker").datepicker("setDate", new Date());
}

editdesignation.prototype.getDesignationHistory = function(empid) {
	var input = {
		"payload" : {
			"empId" : empid
		// rahul's view page
		}
	};

	RequestManager.getDesignation(input,
					function(data, success) {
						if (success) {
						
							var content = data;
							var status = success;

							var input1 = {
								"payload" : {}
							};
							RequestManager.getDesignationName(input1,function(data1, success) {
												if (success) {
													//console.log("successfully retrieved  from DesType .........");
													this.contentDisplay(content, data1,status);
												} else {
													//console.log("failed to retrieved from DesType");
												}
											}.ctx(this));
						} else {

							//console.log("failed to retrieved from DesHistory ");
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
		var month = monthsArray[res.getMonth()];
		var date = res.getDate();
		var apprDate = date + "-" + month + "-" + year;
		table.append("<tr><td>" + desName + "</td><td>" + obj.salary
				+ "</td><td>" + obj.variablePay + "</td><td>" + apprDate
				+ "</td><td><input type=button id='"+obj.id+"' class='dynamicEditdesgn btn-info' value='Edit'></td><td><input type=button id='"+obj.id+"' class='dynamicDeleteDesgn btn-warning' value='Delete'></td></tr>");

	}

}
editdesignation.prototype.editDesignation=function(id,empid,empName1,doj1){
	var self=this;
	var input={"payload":{"id":id}};
	designationObj=null;
	RequestManager.getDesignationById(input,function(data,success){
		
		if(success){
			designationObj=data;
			var value = data.appraisalDate;
			var res = new Date(value);
			var year = res.getFullYear();
			var month =res.getMonth()+1;
			var date = res.getDate();
		   apprDate = year + "-" + month + "-" + date;
			$("#datepicker").val(apprDate);
			$("#designation").val(data.designationId);
			
			$("#salary").val(data.salary);
			$("#variablePay").val(data.variablePay);
			//$('#designation').attr("disabled", true);
			
			$('#updateDesignation').click(function(){
				
				self.validateDesignation();
				if (salaryVal == 1 & dateVal == 1 & dropDownVal == 1
						& variablePayVal == 1) {
					
					self.updateEditedDesignation(designationObj,empName1,doj1);
				}
				
			});
				
		}
		
	}.ctx(this));
	

}
editdesignation.prototype.updateEditedDesignation=function(data,empName1,doj1){
	var value = data.appraisalDate;
	var res = new Date(value);
	var year = res.getFullYear();
	var month =res.getMonth()+1;
	var date = res.getDate();
	 apprDate = year + "-" + month + "-" + date;
	var designationId1 = $("#designation").val();
	var salary1 = $("#salary").val();
	var variablePay1 = $("#variablePay").val();
	var inputEditdesg = {
			"payload" : {
				"id":data.id,
				"empId" : data.empId,
				"appraisalDate" : apprDate +" 00:00:00",
				"designationId" : designationId1,
				"salary" : salary1,
				"variablePay" : variablePay1,
				"currentdesgId":data.designationId
			}
		};
	
	RequestManager.editDesignation(inputEditdesg,function(result,success){
		
		if(success){
				alert("Designation Successfully Updated");
				 App.loadDesignation(result.empId, empName1,doj1);

		}else if(result.code==227){
			alert("Sorry, can not update..Duplicate Desigation details");
		}else{
			
			alert("faild to update");
		}
	}.ctx(this));
	
}

editdesignation.prototype.deleteDesignation=function(id,empName1,doj1){
	var text = confirm("Are you sure you want to delete this employee?");
	if(text==true){
	
	var inputFordelete={"payload":{"id":id}};
	
	RequestManager.deleteDesignationById(inputFordelete,function(data,success){
		
		if(success){
			
			alert("designation Successfully deleted");
			App.loadDesignation(data.empId, empName1,doj1);
		}
		
	}.ctx(this));
	
	}
}
editdesignation.prototype.validateDesignation = function() {
	var salary = $('#salary').val();
	var desId = $("#designation").val();
	var varPay = $("#variablePay").val();
	var date1=$("#datepicker").val();
	if ((desId == "0")&&(salary=="")) {
		alert("Please enter all the mandatory fields");
		dateVal = 0;
		return false;

	}
	if (date1 == "") {
		alert("Please enter date");
		dateVal = 0;
		return false;

	} else {
		dateVal = 1;
	}
	/*var letters = /^(19|20)\d\d-(0\d|1[012])-(0\d|1\d|2\d|3[01])$/;
	if(date1.match(letters))//format for date picker
	{
		dateVal = 1;
	}
	else
	{
		alert("Please enter date in yyyy-mm-dd format");
		dateVal = 0;
		return false;
	}
	*/
	if (desId == "0") {
		alert("Please Select Designation");
		dropDownVal = 0;
		return false;
	} else {
		dropDownVal = 1;
	}
	var letters = /^\d{0,10}(?:\.\d{0,2})?$/;	
	if(salary=="")
	{
		alert("Please enter salary");
		salaryVal=0;
	    return false;
	}
	else
	{
		salaryVal=1;
	}
	if (salary.match(letters)) {
		salaryVal = 1;
	} else {
		alert("Salary accepts numbers only");
		salaryVal = 0;
		return false;

	}

	var letters = /^\d{0,10}(?:\.\d{0,2})?$/;
	if (varPay.match(letters)) {
		$('#isVPEmpty').text("");
		variablePayVal = 1;
	} else {
		alert("Variable Pay accepts numbers only");
		variablePayVal = 0;
		return false;
	}

}
