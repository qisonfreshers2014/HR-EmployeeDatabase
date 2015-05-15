function skill(empid) {
	
	Loader.loadHTML('.container', 'resources/js/skill/Skill.html', true, function(){
	this.handleShow(empid);
	}.ctx(this));
}


skill.prototype.handleShow = function(empid) {
	$('.container').show();
	this.viewSkillDetails(empid);
	$('#save1').click(function(){
		
		this.validateSkill();
		}.ctx(this));
	
	$('#reset').click(function() {
		//e.preventDefault();
		//$('.error').css('visibility', 'hidden');
		$("#skill").val("");
		$("#attended1").val("");
		$("#rating").val("");
		
		//$("#empid").val("");
		 
			}.ctx(this));

	$('#backskill').click(function() {
		App.loadViewEmployee(empid);
	}.ctx(this));
	
	
}



skill.prototype.viewSkillDetails=function(empid){
	 
	var input={"payload":{"empId":empid}};
	$('#empid').val(empid);
	
	 
	RequestManager.getSkillDetails(input, function(data, success) {
		$("#updateskill").css("visibility","hidden");
		$("#save1").css("visibility","visible");
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
  			alert("No view details");
  		}
	}.ctx(this));
}



skill.prototype.validateSkill=function(){
	  var text = document.getElementById("skill").value;
	  var text1 = document.getElementById("attended1").value;
	  var regex = /^[A-Za-z.,0-9]+( [A-Za-z.,0-9]+)*$/;
      var char1=/^[a-zA-Z.]+$/;
      var char2=/^[a-z A-Z]+$/;
      var num	= /^[0-9-+]+$/;
 	  var skill= $("#skill").val();
 	  var empId= $("#empid").val();
 	  var training= $("#attended1").val();
 	  var rating= $("#rating").val();
 	   
 		
 		if(skill=="" && training=="" && empId=="" && rating=="" ){
 			 alert("Please enter Employee Id");
 			 return;
 		 }
 		 if(text.charAt(0)==" ")
		    {
 	    	alert("First letter should not be a space");
 	    	 return;
		    }
 		 else if(empId==""){
 	    	 alert("Please enter employee Id");
 	    	 return;
 	     }
 	     else if(skill==""){
            alert("Please enter skill");
            return;
           	  }
 	   else if(training==""){
 	    	alert("Please enter trainingAttended");
 	    	 return;
 	     }
 	    
 	    else if(rating==""){
            alert("Please enter Rating");
            return;
            	  }
  	   else if(!(skill.match(regex))){
			alert("Please enter characters with one space for skill");
			 return;
			  }
 	   else if (!(empId.match(num))){
 		   alert("Please enter only numbers for EmployeeID");
 		  return;
 	   }
 	   else if(!(rating.match(num))){
 		  alert("Please enter only numbers for Rating");
 		 return;
 	   }
 	  /*else  if(rating.length>2){
 		    alert("Enter only two digits ");
 		    return;
 		   }*/
 	  else if(!(rating>0 && rating<=10)){
 		  alert("Enter rating between 1 to 10");
 		  return;
 	  }
 		  
 		 
	
	 	  var input={"payload":{"skills":$('#skill').val(),"trainingAttended":$('#attended1').val(),
			"empId":$('#empid').val(),"rating":$('#rating').val(),
			  
			}};
	  	 RequestManager.save(input, function(data, success) {
	  		$("#updateskill").css("visibility","hidden");
			$("#save1").css("visibility","visible");
	  		if(success){
	  	 alert("Skills successfully inserted");
	  		//$('#displayData').append("<table></table>");
	  		$('#displayData').append("<tr><td>"+data.skills+"</td><td>"
			+data.trainingAttended+"</td><td>"+data.empId+"</td><td>"
			+data.rating+"</td><td><input type='button' value='Edit' id='"+data.id+"' class='dynamicEdit btn-primary btn-md'></td></tr>");
	  		$("#skill").val("");
		    $("#attended1").val("");
		   // $("#empid").val("");
		    $("#rating").val("");
	  		var obj1=data.id;
	  		$("#save1").show();
			$('.dynamicEdit').bind("click", function(event){
               
				this.editSkill($(event.target).attr("id"));
			   
		    }.ctx(this));
		      //$( "input#clear" ).trigger( "click" );		
			}
	  		/*else if(data.code == 221){
		         alert("Employee ID doesnot  exist");
		        }*/
			else{
				  alert("Failed to inserted");
				 
				}
		}.ctx(this));
 
} 

skill.prototype.editSkill = function(obj1){
	
	 var input = { "payload" : {"id" : obj1} };
	 RequestManager.getEditSKill(input,function(data,success){
		 
		 if(success){
			 console.log(data[0].trainingAttended);
			$('#skill').val(data[0].skills);
		    $('#attended1').val('"'+data[0].trainingAttended+'"');
		    $('#empid').val(data[0].empId);
		    $('#rating').val(data[0].rating);
			$("#save1").css("visibility","hidden");
			$("#updateskill").css("visibility","visible");
	 
		    $('#updateskill').click(function() {

				this.EditEmployeeskillsById(obj1);
				
			}.ctx(this));
		 }
		 
		 else{
			 alert("failed to edit");
		 }
		
		 }.ctx(this));
}






skill.prototype.EditEmployeeskillsById=function(obj1){
	 var text = document.getElementById("skill").value;
	  var text1 = document.getElementById("attended1").value;
	  var regex = /^[A-Za-z.,0-9]+( [A-Za-z.,0-9]+)*$/;
     var char1=/^[a-zA-Z.]+$/;
     var char2=/^[a-z A-Z]+$/;
     var num	= /^[0-9-+]+$/;
	  var skill= $("#skill").val();
	  var empId= $("#empid").val();
	  var training= $("#attended1").val();
	  var rating= $("#rating").val();
	   
		
		if(skill=="" && training=="" && empId=="" && rating=="" ){
			 alert("Please enter Employee Id");
			 return;
		 }
		 if(text.charAt(0)==" ")
		    {
	    	alert("First letter should not be a space");
	    	 return;
		    }
		 else if(empId==""){
	    	 alert("Please enter employee Id");
	    	 return;
	     }
	     else if(skill==""){
           alert("Please enter skill");
           return;
          	  }
	   else if(training==""){
	    	alert("Please enter trainingAttended");
	    	 return;
	     }
	    
	    else if(rating==""){
           alert("Please enter Rating");
           return;
           	  }
 	   else if(!(skill.match(regex))){
			alert("Please enter characters with one space for skill");
			 return;
			  }
	   else if (!(empId.match(num))){
		   alert("Please enter only numbers for EmployeeID");
		  return;
	   }
	   else if(!(rating.match(num))){
		  alert("Please enter only numbers for Rating");
		 return;
	   }
	  /*else  if(rating.length>2){
		    alert("Enter only two digits ");
		    return;
		   }*/
	  else if(!(rating>0 && rating<=10)){
		  alert("Enter rating between 1 to 10");
		  return;
	  }
		 
	 else{
		 var input={"payload":{"id":obj1,"skills":$('#skill').val(),"trainingAttended":$('#attended1').val(),
				"empId":$('#empid').val(),"rating":$('#rating').val(),
				}}; 
    RequestManager.editskills(input, function(data, success)
    {
    	 
        if(success){
        alert("Edit skill successfully updated");
        $("#save1").css("visibility","visible");
		//$("#update").css("visibility","hidden");
        $("#skill").val("");
 		$("#attended1").val("");
 		$("#rating").val("");
 		App.loadSkill(empId);
 		
 		
        }
        	      
        else{
         alert("Edit Skills failed to update");
        }
    }.ctx(this));
	 }	
	    		 }