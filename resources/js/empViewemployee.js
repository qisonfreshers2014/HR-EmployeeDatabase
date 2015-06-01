 function empViewemployee(employeeId) {

 Loader.loadHTML('.container','resources/viewEmployee.html', true, function(){
  this.handleShow(employeeId);
 }.ctx(this));
}
empViewemployee.prototype.handleShow = function(employeeId) {
 $('.container').show();
 
 $('#emphidden').hide();
 $('#back').css("visibility","hidden");
 $('#Editdetails').css("visibility","hidden");
 $('#Editdesgn').css("visibility","hidden");
 $('#Editskills').css("visibility","hidden");

    this.empviewEmployee(employeeId);
 
 $('#Editempdetails').click(function(){
 
 App.loadEditEmp(employeeId);
}.ctx(this));

 
  $('#changepwd').click(function(){
	 
	 App.loadchangePassword(employeeId);
	}.ctx(this));
}

empViewemployee.prototype.empviewEmployee=function(employeeId){
  var input= {"payload":{"employeeId":employeeId}};

  RequestManager.viewEmployeedetails(input, function(data, success) {
  if(success){
	  
   var obj=data;
    var dobformat = new Date(obj.dateOfBirth);
    var byear = dobformat.getFullYear();
    var bmonth = dobformat.getMonth()+1;
    var bdate = dobformat.getDate();
    var hr=false;
    $('#Editempskills').click(function(){
 	   App.loadSkill(employeeId,hr);
 	  }.ctx(this));
    	 
    $('#employeeimage').append(obj.filePath);
   $('#eid').val(obj.employeeId);
   $('#name').val(obj.employeeName);
   $('#gender').val(obj.gender);
   $('#DOB').val(byear+"-"+bmonth+"-"+bdate);
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
   $('#skill').val(obj.skill);
   $('#rating').val(obj.rating);
   }else{
    
    alert("failed to add");
   }
  }.ctx(this));
 
}