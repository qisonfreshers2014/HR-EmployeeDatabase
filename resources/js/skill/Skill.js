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
		/*this.validateTrainingAttended();
		this.validateEmpId();
		this.validateRating();	
		this.addSkill();
	*/ 
		}.ctx(this));
	
	$('#reset').click(function() {
		//e.preventDefault();
		//$('.error').css('visibility', 'hidden');
		$("#skill").val("");
		$("#attended1").val("");
		$("#rating").val("");
		$("#qualification1").val("");
		$("#empid").val("");
		 
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
	  var text = document.getElementById("skill").value;
	  var text1 = document.getElementById("attended1").value;
	  var regex = /^[A-Za-z.0-9]+( [A-Za-z.0-9]+)*$/;
      var char1=/^[a-zA-Z.]+$/;
      var char2=/^[a-z A-Z]+$/;
      var num	= /^[0-9-+]+$/;
 	  var skill= $("#skill").val();
 	  var empId= $("#empid").val();
 	  var training= $("#attended1").val();
 	  var rating= $("#rating").val();
 	   
 		 
 		if(skill=="" && training=="" && empId=="" && rating=="" ){
 			 alert("Failed to save,since every field is mandatory");
 			 return;
 		 }
 		 else if(text.charAt(0)==" ")
		    {
 	    	alert("First letter should not enter space for skill");
 	    	 return;
		    }
 		else if(text1.charAt(0)==" ")
	    {
	    	alert("First letter should not enter space for training");
	    	 return;
	    }
 	     else if(skill==""){
            alert("please enter skill");
            return;
            	  }
 	     else if(training==""){
 	    	alert("please enter trainingAttended (true/false)");
 	    	 return;
 	     }
 	     else if(empId==""){
 	    	 alert("please enter Id");
 	    	 return;
 	     }
 	    else if(rating==""){
            alert("please enter rating");
            return;
            	  }
  	   else if(!(skill.match(regex))){
			alert("please enter charcter with one space for skill");
			 return;
			  }
 	   else if (!(empId.match(num))){
 		   alert("Please enter only numbers with out space EmpID");
 		  return;
 	   }
 	   else if(!(rating.match(num))){
 		  alert("Please enter only numbers for rating");
 		 return;
 	   }
 	  else  if(rating.length>2){
 		    alert("Invalid rating minimum 2 numbers only accepted ");
 		    return;
 		   }
 	  else if(!(rating>0 && rating<=10)){
 		  alert("enter rating b/w 1 to 10");
 		  return;
 	  }
 		
 	   else if(!(training.match(char2))){
 		   alert("please enter only chacters");
 		  return;
 	   }
 	 else if(!(training=="true" || training=="false")){
 		   alert("please enter only (true/fase)");
 		   return;
 	   }
 	
	 

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
	  		/*else if(data.code == 120001){
		         alert("Skill Name & EmpId already exist");
		        }*/
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