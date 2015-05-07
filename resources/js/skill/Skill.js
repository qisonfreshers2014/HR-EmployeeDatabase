function skill() {
	Loader.loadHTML('.container', 'resources/js/skill/Skill.html', true, function(){
	this.handleShow();
	}.ctx(this));
}


skill.prototype.handleShow = function() {
	$('.container').show();
	this.viewSkillDetails();
	$('#save').click(function(){
		this.validateSkill();
		this.validateTrainingAttended();
		this.validateEmpId();
		this.validateRating();	
		this.addSkill();
	 
		}.ctx(this));
	 }


skill.prototype.viewSkillDetails=function(){
	
	var input={"payload":{}};
	 
	RequestManager.getSkillDetails(input, function(data, success) {
		
  		if(success){
  	    
  	    
  		for( var i=0;i<data.length;i++){
  		 
  		var skill = data[i];
  		//$('#displayData').append('<table></table>');
		 
		$('#displayData').append("<tr><td>"+skill.skills+"</td><td>"
		+skill.trainingAttended+"</td><td>"+skill.empId+"</td><td>"
		+skill.rating+"</td><td><input type='button' value='Edit' id='"+skill.id+"' class='dynamicEdit btn-primary btn-md'></td></tr>");
		
		var obj1=skill.id;
  		}
		$('.dynamicEdit').bind("click", function(event){	 
		this.editSkill($(event.target).attr("id"));
		}.ctx(this));
  		}else{
  			alert("no view details");
  		}
	}.ctx(this));
}



skill.prototype.validateSkill=function(){
         var char=/^[A-Za-z]+([A-Za-z]+)*$/;
 	     var skill= $("#skill").val();
		 if(skill==""){
             $("#skillerror1").text("you cannot leave this empty");
             return false;
			  }
		 else if(skill.match(char)){
			   $("#skillerror1").text("nice format");
			   return true;
			  }
			  else{
			   $("#skillerror1").text("Enter only one space&characters only");
               return false;
			  }
	}
skill.prototype.validateTrainingAttended=function(){ 
	
	var training= $("#attended1").val();
	 if(training==""){
        $("#skillerror2").text("you cannot leave this empty");
        return false;
		  }
	 else if(training.match('[0-9-+]+$')){
		   $("#skillerror2").text("Enter only characters(true/false) only");
		   return false;
		  }
		  else{
		   $("#skillerror2").text("");
          return true;
		  }
}

skill.prototype.validateEmpId=function(){ 
	 var empId= $("#empid").val();
	 if(empId==""){
         $("#error1").text("you cannot leave this empty");
         return false;
		  }
	 else if(empId.match('[0-9-+]+$')){
		   $("#error1").text("");
		   return false;
		  }
		  else{
		   $("#error1").text("Enter only numbers only");
           return true;
		  }
	 
}

skill.prototype.validateRating=function(){ 
	 var rating= $("#rating").val();
	 if(rating==""){
        $("#error2").text("you cannot leave this empty");
        return false;
		  }
	 else if(rating.match('[0-9-+]+$')){
		   $("#error2").text("");
		   return false;
		  }
		  else{
		   $("#error2").text("Enter only numbers only");
          
		  
	 return true;
		  }
}
	 
skill.prototype.addSkill = function(){
	 
// if(this.validateSkill() || this.validateTrainingAttended() || this.validateEmpId() || this.validateRating()){
//	 alert("sucess");
	  var input={"payload":{"skills":$('#skill').val(),"trainingAttended":$('#attended1').val(),
			"empId":$('#empid').val(),"rating":$('#rating').val(),
			  
			}};
	  	 RequestManager.save(input, function(data, success) {
	  	 
	  		if(success){
	  	 alert("successfully inserted");
	  		//$('#displayData').append("<table></table>");
	  		$('#displayData').append("<tr><td>"+data.skills+"</td><td>"
			+data.trainingAttended+"</td><td>"+data.empId+"</td><td>"
			+data.rating+"</td><td><input type='button' value='Edit' id='"+data.id+"' class='dynamicEdit btn-primary btn-md'></td></tr>");
	  		$("#skill").val("");
		    $("#attended1").val("");
		    $("#empid").val("");
		    $("#rating").val("");
	  		var obj1=data.id;
			$('.dynamicEdit').bind("click", function(event){
				 
				this.editSkill($(event.target).attr("id"));
			   
		    }.ctx(this));
		      //$( "input#clear" ).trigger( "click" );		
			}
	  		else if(data.code == 120001){
		         alert("Skill Name & EmpId already exist");
		        }
			else{
				  alert("failed to inserted");
				 
				}
		}.ctx(this));
 
} 
skill.prototype.editSkill = function(obj1){
	 var input = { "payload" : {"id" : obj1} };
	 RequestManager.getEditSKill(input,function(data,success){
		 
		 if(success){
			$('#skill').val(data[0].skills);
		    $('#attended1').val(data[0].trainingAttended);
		    $('#empid').val(data[0].empId);
		    $('#rating').val(data[0].rating);
		 }
		 else{
			 alert("failed to edit");
		 }
		
		 }.ctx(this));
}
