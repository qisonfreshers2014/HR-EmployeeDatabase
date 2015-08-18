function skill(empid,hr) {
	
	Loader.loadHTML('.container', 'resources/js/skill/Skill.html', true, function(){
	this.handleShow(empid,hr);
	}.ctx(this));
}


skill.prototype.handleShow = function(empid,hr) {
	$('.container').show();

	var input={"payload":{"empId":empid}};
	$('#empid').val(empid);
	
	 
	RequestManager.getSkillDetails(input, function(data, success) {
		if(success){
			var content = data;
			var status = success;
			
			this.viewSkillDetails(data,success);
		}
	
	}.ctx(this));
	
	$('#save1').click(function(){
		
		this.validateSkill();
		}.ctx(this));
	
	$('#Resetskill').click(function() {
		
		$("#skill").val("");
		$("#rating").val("");
		 
			}.ctx(this));

	$('#backtoemp').click(function() { 
		if(hr==true){
		App.loadViewEmployee(empid);
		}else{
			App.loadempviewemployee(empid);
		}
	}.ctx(this));
		
}

skill.prototype.validateSkill=function(){
	  var text = $("#skill").val();
	  var text1 = $("#attended1").val();
	  var regex = /^[A-Za-z.,0-9]+( [A-Za-z.,0-9]+)*$/;
    var char1=/^[a-zA-Z.]+$/;
    var char2=/^[a-z A-Z]+$/;
    var num	= /^[0-9-+]+$/;
	  var skill= $("#skill").val();
	  var empId= $("#empid").val();
	  var training= $("#attended1").val();
	  var rating= $("#rating").val();
	   
		
		if(skill=="" && training=="" && empId==""){
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
	  
	    
	    else if(rating==""){
          alert("Please Select Rating");
          return;
          	  }
	  /* else if(!(skill.match(regex))){
			alert("Please enter characters with one space for skill");
			 return;
			  }*/
	   else if (!(empId.match(num))){
		   alert("Please enter only numbers for EmployeeID");
		  return;
	   }
	 
	 
	 	  var input={"payload":{"skills":$('#skill').val(),
			"empId":$('#empid').val(),"rating":$('#rating').val(),
			  
			}};
	  	 RequestManager.save(input, function(data, success) {
	  		$("#updateskill").hide();
	  	   $("#save1").show();
	  		if(success){
	  	 alert("Skills successfully inserted");
	  	
	  		$('#displayDataskill').append("<tr class='skilltr'><td class='skilltd'>"+data.empId+"</td><td class='skilltd'>"+data.skills+"</td><td class='skilltd'>"+data.rating+"</td><td class='skilltd'><input type='button' value='Edit' id='"+data.id+"' class='dynamicEdit btn-info'></td><td class='skilltd'><input type='button' value='Edit' id='"+data.id+"' class='dynamicDelete btn-warning'></td></tr>");
	  		$("#skill").val("");
		    $("#rating").val("");
	  		var obj1=data.id;
	  		$("#save1").show();
	  		
			}
	  		
			else{
				  alert("Failed to inserted");
				 
				}
		}.ctx(this));

} 



skill.prototype.viewSkillDetails=function(data,success){
	 
	
		$("#save1").show();
		$("#Resetskill").show();
		$("#updateskill").hide();
	 
		$('.dynamicEdit').live('click', function(event)  {
			event.stopImmediatePropagation();	
  			$("#skill").val("");
		    $("#rating").val("");
		    var releaseId = event.target.id;
		this.editSkill(releaseId);
		}.ctx(this));
		
		$('.dynamicDelete').live('click', function(event)  {
			event.stopImmediatePropagation();	
  			$("#skill").val("");
		    $("#rating").val("");
		    var releaseId = event.target.id;
		this.deleteSkill(releaseId);
		}.ctx(this));
		
		$('#updateskill').click(function(e) {
			
			 var text = document.getElementById("skill").value;
			  var regex = /^[A-Za-z.,0-9]+( [A-Za-z.,0-9]+)*$/;
		     var char1=/^[a-zA-Z.]+$/;
		     var char2=/^[a-z A-Z]+$/;
		     var num	= /^[0-9-+]+$/;
			  var skill= $("#skill").val();
			  var empId= $("#empid").val();
			  var rating= $("#rating").val();
			   
				
				if(skill=="" && empId=="" && rating=="" ){
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
			    
			    else if(rating==""){
		           alert("Please Select Rating");
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
					

				 
			 else{  
				 
				 var id = $("#skillid").text();
				 var input={"payload":{"id":id,"skills":$('#skill').val(),
						"empId":$('#empid').val(),"rating":$('#rating').val(),
						}}; 
		    RequestManager.editskills(input, function(data, success)
		    {
		    	 
		        if(success){
		        alert("Edit skill successfully updated");
		        $("#save1").show();
				$("#Resetskill").show();
				$("#updateskill").hide();
		        $("#skill").val("");
		 		$("#rating").val("");
		 		App.loadSkill(empId);
		 		
		 		
		        }
		        	      
		        else{
		         alert("Edit Skills failed to update");
		        }
		    }.ctx(this));
			 }	
		}.ctx(this));
  		
  			 
  	     	    
  		for( var i=0;i<data.length;i++){
  		 
  		var skill = data[i];
  		
		$('#displayDataskill').append("<tr class='skilltr'><td class='skilltd'>"+skill.empId+"</td><td class='skilltd'>"+skill.skills+"</td><td class='skilltd'>"+skill.rating+"</td><td class='skilltd'><input type='button' value='Edit' id='"+skill.id+"' class='dynamicEdit  btn-info '></td><td class='skilltd'><input type='button' value='Delete' id='"+skill.id+"' class='dynamicDelete  btn-warning '></td></tr>");
		
		var obj1=skill.id;
  		}
  		
	
}





skill.prototype.editSkill = function(obj1){
	
	 var input = { "payload" : {"id" : obj1} };
	 RequestManager.getEditSKill(input,function(data,success){
		 
		 if(success){
			 console.log(data[0].trainingAttended);
			$('#skill').val(data[0].skills);
		    $('#empid').val(data[0].empId);
		    $('#rating').val(data[0].rating);
		    $("#skillid").text(data[0].id);
			$("#save1").hide();
			$("#Resetskill").hide();
			$("#updateskill").show();
	 
		    
		 }
		 
		 else{
			 alert("failed to edit");
		 }
		
		 }.ctx(this));
}
skill.prototype.deleteSkill=function(id){
	var text = confirm("Are you sure you want to delete this employee?");
	if(text==true){
		var inputTodelete={"payload":{"id":id}};
		
		RequestManager.deleteSkillById(inputTodelete,function(data,success){
			
			if(success){
				
				alert("Successfullt deleted the skill");
				App.loadSkill(data.empId);
			}
			
		}.ctx(this));
		
	}
}





