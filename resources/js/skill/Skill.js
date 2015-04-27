function skill() {
	Loader.loadHTML('.leftContainer', 'resources/js/skill/Skill.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

skill.prototype.handleShow = function() {
	$('.container').show();
	$('#save').click(function(){
		this.addSkill();
	}.ctx(this));

}

skill.prototype.skill=function(){
	
	 
	//validations for skills
	var skillerr=document.getElementById("skillerror1");
	var skills = document.getElementById("skill").value;
	var char =/^[a-zA-Z.""]+$/;
	if(skills === "") 
	 {
		skillerr.style.visibility="visible";
		valid = false;
	 }   
	 else if(!(skills.match(char) || skills == isNaN))
	 {
		 skillerr.innerHTML= "invalid skill / It should be characters only";
		 skillerr.style.visibility="visible";
		 valid = false;
	  
	 }
	 else  
		 skillerr.style.visibility="hidden";
	
	//validations for training attended
	var trainingerr=document.getElementById("skillerror1");
	var training = document.getElementById("attened1").value;
	var char =/^[a-zA-Z]+$/;
	if(training === "") 
	 {
		trainingerr.style.visibility="visible";
		valid = false;
	 }   
	 else if(!(training.match(char) || training == isNaN))
	 {
		 trainingerr.innerHTML= "invalid training attended / It should be characters only";
		 trainingerr.style.visibility="visible";
		 valid = false;
	  }
	 else  
		 trainingerr.style.visibility="hidden";
	
		//validations for EmployeeId
		var error =document.getElementById("error");
		var eid= document.getElementById("empid").value;
		var num =/^[0-3]?[0-9]\/[01]?[0-9]\/[12][90][0-9][0-9]$/;
		if(eid == "" ) 
		 {
			error.style.visibility="visible";
			valid = false;
		 } 
		  else if(isNaN(eid))
		 {
			  error.innerHTML="This empid format is not recognized.Enter only numbers.";
			  error.style.visibility="visible";
			  valid = false;
		 }
		 else  
			 error.style.visibility="hidden"; 
		
		//validations for rating
		var error1 =document.getElementById("error");
		var rating1= document.getElementById("rating").value;
		var num =/^[0-3]?[0-9]\/[01]?[0-9]\/[12][90][0-9][0-9]$/;
		if(rating1 == "" ) 
		 {
			error1.style.visibility="visible";
			valid = false;
		 } 
		  else if(isNaN(rating1))
		 {
			  error1.innerHTML="This rating format is not recognized.Enter only numbers.";
			  error1.style.visibility="visible";
			  valid = false;
		 }
		 else  
			 error1.style.visibility="hidden"; 
		return true;
	}


skill.prototype.addSkill = function(){
	if(this.skill()){
	  var input={"payload":{"skills":$('#skill').val(),"trainingAttended":$('#attened1').val(),
			"empId":$('#empid').val(),"rating":$('#rating').val(),
			  
			}};
	  
	  	 RequestManager.save(input, function(data, success) {
		 if(success){
		
			$('#displayData').append('<table></table>');
			var table = $('#displayData').children(); 
			$('#displayData').after("<tr><td>"+data.skills+"</td><td>"+data.trainingAttended+"</td><td>"+data.empId+"</td><td>"+data.rating+"</td><td><input type='button' value='Edit'</td></tr>");
			 
			 }else{
				  alert("failed to retrieved");
				 }
				}.ctx(this));
		}
};