 function ViewEmployee(empid) {
 Loader.loadHTML('.container', 'resources/viewEmployee.html', true, function(){
  this.handleShow(empid);
 }.ctx(this));
}
$('.container').show();
ViewEmployee.prototype.handleShow = function(empid) {

 this.viewEmployeedetails(empid);

 $('#Editdetails').click(function(){
  
  App.loadhrEditEmp(empid);
 }.ctx(this));
 
 
 $('#back').click(function(){
  
   App.listEmployee();
 }.ctx(this));
 

 $('#Editskills').click(function(){
  App.loadSkill(empid);
 }.ctx(this));
 
 
   $('#Editdesgn').click(function(){
    
   var sendname = $('#senddesgname').text();
   var sendjoin = $('#senddoj').text();
  
  App.loadDesignation(empid, sendname,sendjoin);
 }.ctx(this));
   
}
 
ViewEmployee.prototype.viewEmployeedetails=function(empid){

 var input= {"payload":{"employeeId":empid}};

RequestManager.viewEmployeedatails(input, function(data, success) {
 if(success){
 
 var obj=data;

 if(obj.deleted==true){
	    
	    $('#Editdetails').css("visibility","hidden");
	    $('#Editskills').css("visibility","hidden");
	    $('#Editdesgn').css("visibility","hidden");
	   }
 
 var dobformat = new Date(obj.dateOfBirth);
   var byear = dobformat.getFullYear();
   var bmonth = dobformat.getMonth()+1;
   var bdate = dobformat.getDate();
  
   var dojformat = new Date(obj.dateOfJoining);
   var dojyear = dojformat.getFullYear();
   var dojmonth = dojformat.getMonth()+1;
   var dojdate = dojformat.getDate();
  
  $('#employeeimage').append(obj.filePath);
 $('#eid').val(obj.employeeId);
 $('#name').val(obj.employeeName);
 $('#gender').val(obj.gender);
 $('#DOB').val(byear+"-"+bmonth+"-"+bdate);
 $('#doj').val(dojyear+"-"+dojmonth+"-"+dojdate);
 $('#cnct').val(obj.contactNo);
 $('#curaddr').val(obj.currentAddress);
 $('#peraddr').val(obj.permanentAddress);
 $('#email').val(obj.email);                                                      
 $('#pannum').val(obj.pan);
 $('#emercon').val(obj.emergencycontactnumber);
 $('#fathername').val(obj.fathersName);
 $('#contactname').val(obj.emergencyContactName);
 $('#relation').val(obj.relationWithEmergencyConatact);
 $('#blood').val(obj.bloodGroup);
 $('#designation').val(obj.designationName);
 $('#variable').val(obj.variableComponent);
 $('#qual').val(obj.highestQualification);                            
 $('#pannum').val(obj.pan);
 $('#pfnum').val(obj.pfNo);
 $('#accountnum').val(obj.bankAccountNo);
 $('#rating').val(obj.rating);
 $('#skype').val(obj.skype);
 $('#salary').val(obj.salary);
 $('#yearofexp').val(obj.yearsofexperience);
 $('#skill').val(obj.skill);
 
 
 
  $('#senddesgname').text(obj.employeeName);
  $('#senddoj').text(dojyear+"-"+dojmonth+"-"+dojdate);
 }else{
  
  alert("failed to add");
 }
}.ctx(this));
  
}