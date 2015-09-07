 function ViewEmployee(empid) {
 Loader.loadHTML('.container', 'resources/viewEmployee.html', true, function(){
  this.handleShow(empid);
 }.ctx(this));
}
$('.container').show();
ViewEmployee.prototype.handleShow = function(empid) {
	 $('#salaryrow').show();
	 $('.editempviewEmployee').hide();
	 $('.editviewEmployee').show();


 this.viewEmployeedetails(empid);


 
 $('#backtoemplist').click(function(){
 
  routie("employee");
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
 var dobformat = new Date(obj.dateOfBirth);
   var byear = dobformat.getFullYear();
   var bmonth =monthsArray[dobformat.getMonth()];
   var bdate = dobformat.getDate();
  
   var dojformat = new Date(obj.dateOfJoining);
   var dojyear = dojformat.getFullYear();
   var dojmonth = monthsArray[dojformat.getMonth()];
   var dojdate = dojformat.getDate();
   
   var actualDOBformat=new Date(obj.actualdateOfBirth);
   var actualDOByear=actualDOBformat.getFullYear();
   var actualDOBmonth=monthsArray[actualDOBformat.getMonth()];
   var actualDOBdate=actualDOBformat.getDate();
  
	
// Calculating Current years of experience
   var today=new Date();
   var diff=Math.floor(today.getTime() - dojformat.getTime());
   var day = 1000 * 60 * 60 * 24;
   var days = Math.floor(diff/day);
   var months = Math.floor(days/31);
   var exp=obj.yearsofexperience;
   var isDecimal=exp%1;
   var parts=exp.toString().split('.');
   
   var afterdecimal=parseInt(parts[1]);
   var beforedecimal=parseInt(parts[0]);
   
 if(isDecimal!=0){
 	 months=months+afterdecimal;
 	
 	  years = Math.floor(months/12);
 	
 	  totalYearsOfExpMnths=months%12;
 	 
     totalYearsOfExp=years+beforedecimal;
    
 	 
   }
   else{ 
 	  years = Math.floor(months/12);
 	
 	  totalYearsOfExpMnths=months%12;
 	 
 	  totalYearsOfExp=years+obj.yearsofexperience;
 	
   }
 
 
   var hr=true;
  
   $('#Editskills').click(function(){
	   App.loadSkill(empid,hr);
	  }.ctx(this));
   var image=obj.filePath;
   
   var skills=obj.skills;
$('#employeeimage').append('<img src="'+image+'" height="150" width="150">');
 $('#eid').val(obj.employeeId);
 $('#name').val(obj.employeeName);
 $('#gendr').val(obj.gender);
 $('#DOB').val(bdate+"-"+bmonth+"-"+byear);
 $('#actualDOB').val(actualDOBdate+"-"+actualDOBmonth+"-"+actualDOByear);
 $('#doj').val(dojdate+"-"+dojmonth+"-"+dojyear);
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
 $('#employeetype').val(obj.employeeType);
 $('#variable').val(obj.variableComponent);
 $('#qual').val(obj.highestQualification);                            
 $('#pannum').val(obj.pan);
 $('#pfnum').val(obj.pfNo);
 $('#accountnum').val(obj.bankAccountNo);
 $('#skype').val(obj.skype);
 $('#salary').val(obj.salary);
 $('#university').val(obj.university);
 $('#hobbies').val(obj.hobbies);
 $('#yearofexp').val(totalYearsOfExp+"."+totalYearsOfExpMnths);
 for(i=0;i<skills.length;i++){
	 var skillsObj=skills[i];
 $('#displaySkills').append('<tr><td>'+skillsObj.skills+'</td><td>'+skillsObj.rating+'</td></tr>');
 
 }
  $('#senddesgname').text(obj.employeeName);
  $('#senddoj').text(dojyear+"-"+dojmonth+"-"+dojdate);
 }else{
  
  alert("failed to add");
 }
 
 $('#Editdetails').click(function(){
	  
	  App.loadhrEditEmp(obj.id);
	 }.ctx(this));
}.ctx(this));
  
}