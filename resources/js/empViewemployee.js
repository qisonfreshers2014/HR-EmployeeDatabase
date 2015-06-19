 function empViewemployee(employeeId) {

 Loader.loadHTML('.container','resources/viewEmployee.html', true, function(){
  this.handleShow(employeeId);
 }.ctx(this));
}
empViewemployee.prototype.handleShow = function(employeeId) {
 $('.container').show();
 
 $('#emphidden').hide();
 $('#backtoemplist').css("visibility","hidden");
/* $('#Editdetails').css("visibility","hidden");
 $('#Editdesgn').css("visibility","hidden");
 $('#Editskills').css("visibility","hidden");*/
 $('.editviewEmployee').css("visibility","hidden");

    this.empviewEmployee(employeeId);
 
 $('#Editempdetails').click(function(){
   routie("employeeedit");

}.ctx(this));

 
  $('#changepwd').click(function(){
	 routie("changepassword");
	
	}.ctx(this));
}

empViewemployee.prototype.empviewEmployee=function(employeeId){
  var input= {"payload":{"employeeId":employeeId}};

  RequestManager.viewEmployeedetails(input, function(data, success) {
  if(success){
	  
   var obj=data;
   
   var monthsArray=new Array(12);
   monthsArray[0]="January";
  	monthsArray[1]="Febravary";
  	monthsArray[2]="March";
  	monthsArray[3]="April";
  	monthsArray[4]="May";
  	monthsArray[5]="June";
  	monthsArray[6]="July";
  	monthsArray[7]="August";
  	monthsArray[8]="September";
  	monthsArray[9]="October";
  	monthsArray[10]="November";
  	monthsArray[11]="December";
  	
    var dobformat = new Date(obj.dateOfBirth);
    var byear = dobformat.getFullYear();
    var bmonth =monthsArray[dobformat.getMonth()];
    var bdate = dobformat.getDate();
    
    
    var actualDOBformat=new Date(obj.actualdateOfBirth);
    var actualDOByear=actualDOBformat.getFullYear();
    var actualDOBmonth=monthsArray[actualDOBformat.getMonth()];
    var actualDOBdate=actualDOBformat.getDate();
    
     var hr=false;
    $('#Editempskills').click(function(){
 	   App.loadSkill(employeeId,hr);
 	  }.ctx(this));
    	var image=obj.filePath;
    	 var skills=obj.skills;
    $('#employeeimage').append('<img src="'+image+'" height="150" width="150">');
   $('#eid').val(obj.employeeId);
   $('#name').val(obj.employeeName);
   $('#gendr').val(obj.gender);
   $('#DOB').val(bdate+"-"+bmonth+"-"+byear);
   $('#actualDOB').val(actualDOBdate+"-"+actualDOBmonth+"-"+actualDOByear);
   $('#cnct').val(obj.contactNo);
   $('#curaddr').val(obj.currentAddress);
   $('#peraddr').val(obj.permanentAddress);
   $('#email').val(obj.email);
   $('#emercon').val(obj.emergencycontactnumber);
   $('#fathername').val(obj.fathersName);
   $('#contactname').val(obj.emergencyContactName);
   $('#relation').val(obj.relationWithEmergencyConatact);
   $('#blood').val(obj.bloodGroup);
   $('#qual').val(obj.highestQualification);
   $('#pannum').val(obj.pan);
   $('#pfnum').val(obj.pfNo);
   $('#accountnum').val(obj.bankAccountNo);
   $('#university').val(obj.university);
   $('#hobbies').val(obj.hobbies);
   
   for(i=0;i<skills.length;i++){
		 var skillsObj=skills[i];
	 $('#displaySkills').append('<tr><td>'+skillsObj.skills+'</td><td>'+skillsObj.rating+'</td></tr>');
	 
	 }
   }else{
    
    alert("failed to add");
   }
  }.ctx(this));
 
}