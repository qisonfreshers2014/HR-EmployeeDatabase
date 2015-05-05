function empViewemployee() {
	Loader.loadHTML('.container','resources/empviewemployee.html', true, function(){
		this.handleShow();
	}.ctx(this));
}
empViewemployee.prototype.handleShow = function() {
	$('.container').show();
	
	var input= {"payload":{"employeeId":2}};

	RequestManager.viewEmployeedatails(input, function(data, success) {
		if(success){
		var obj=data[0]
		//alert(obj.employeeName);
		  var dobformat = new Date(obj.dateOfBirth);
		  var byear = dobformat.getFullYear();
		  var bmonth = dobformat.getMonth()+1;
		  var bdate = dobformat.getDate();
		  
		$('#eid').val(obj.employeeId);
		$('#name').val(obj.employeeName);
		$('#gender').val(obj.gender);
		$('#DOB').val(byear+"-"+bmonth+"-"+bdate);
	     // $('#doj').val(obj.dateOfJoining);
		//$('#ADOB').val(obj.yearsofexperience);
		$('#cnct').val(obj.contactNo);
		$('#curaddr').val(obj.currentAddress);
		$('#peraddr').val(obj.permanentAddress);
		$('#email').val(obj.email);
		//$('#pannum').val(obj.password);
		$('#emercon').val(obj.emergencycontactnumber);
		$('#fathername').val(obj.fathersName);
		$('#contactname').val(obj.emergencyContactName);
		$('#relation').val(obj.relationWithEmergencyConatact);
		$('#blood').val(obj.bloodGroup);
		//$('#designation').val(obj.currentDesignation);
		//$('#contactname').val(obj.reportinManagerId);
		$('#qual').val(obj.highestQualification);
		$('#pannum').val(obj.pan);
		$('#pfnum').val(obj.pfNo);
		$('#accountnum').val(obj.bankAccountNo);
		//$('#variable').val(obj.skype);
		}else{
			
			alert("failed to add");
		}
	}.ctx(this));
	
	
	 $('#Editdetails').click(function(){
			
		App.loadEditEmp(2);
		}.ctx(this));
		
	


	}
/*empViewemployee.prototype.viewEmployeedetails=function(id){
	
       
      
	
	var input= {"payload":{"employeeId":id}};

RequestManager.viewEmployeedatails(input, function(data, success) {
	if(success){
	var obj=data[0]
	//alert(obj.employeeName);
	  var dobformat = new Date(obj.dateOfBirth);
	  var byear = dobformat.getFullYear();
	  var bmonth = dobformat.getMonth()+1;
	  var bdate = dobformat.getDate();
	  
	$('#eid').val(obj.employeeId);
	$('#name').val(obj.employeeName);
	$('#gender').val(obj.gender);
	$('#DOB').val(byear+"-"+bmonth+"-"+bdate);
     // $('#doj').val(obj.dateOfJoining);
	//$('#ADOB').val(obj.yearsofexperience);
	$('#cnct').val(obj.contactNo);
	$('#curaddr').val(obj.currentAddress);
	$('#peraddr').val(obj.permanentAddress);
	$('#email').val(obj.email);
	//$('#pannum').val(obj.password);
	$('#emercon').val(obj.emergencycontactnumber);
	$('#fathername').val(obj.fathersName);
	$('#contactname').val(obj.emergencyContactName);
	$('#relation').val(obj.relationWithEmergencyConatact);
	$('#blood').val(obj.bloodGroup);
	//$('#designation').val(obj.currentDesignation);
	//$('#contactname').val(obj.reportinManagerId);
	$('#qual').val(obj.highestQualification);
	$('#pannum').val(obj.pan);
	$('#pfnum').val(obj.pfNo);
	$('#accountnum').val(obj.bankAccountNo);
	//$('#variable').val(obj.skype);
	}else{
		
		alert("failed to add");
	}
}.ctx(this));
	 
}
*/