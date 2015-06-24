function skill(empid,hr) {
	
	Loader.loadHTML('.container', 'resources/js/skill/Skill.html', true, function(){
	this.handleShow(empid,hr);
	}.ctx(this));
}


skill.prototype.handleShow = function(empid,hr) {
	$('.container').show();


	this.viewSkillDetails(empid);
	
	
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



skill.prototype.viewSkillDetails=function(empid){
	 
	var input={"payload":{"empId":empid}};
	$('#empid').val(empid);
	
	 
	RequestManager.getSkillDetails(input, function(data, success) {
		$("#updateskill").css("visibility","hidden");
		$("#save1").css("visibility","visible");
	 	if(success){
  			 
  	     	    
  		for( var i=0;i<data.length;i++){
  		 
  		var skill = data[i];
  		
		$('#displayDataskill').append("<tr class='skilltr'><td class='skilltd'>"+skill.skills+"</td><td class='skilltd'>"+skill.empId+"</td><td class='skilltd'>"+skill.rating+"</td><td class='skilltd'><input type='button' value='Edit' id='"+skill.id+"' class='dynamicEdit  btn-info '></td></tr>");
		
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
  	   else if(!(skill.match(regex))){
			alert("Please enter characters with one space for skill");
			 return;
			  }
 	   else if (!(empId.match(num))){
 		   alert("Please enter only numbers for EmployeeID");
 		  return;
 	   }
 	 
 	 
	 	  var input={"payload":{"skills":$('#skill').val(),
			"empId":$('#empid').val(),"rating":$('#rating').val(),
			  
			}};
	  	 RequestManager.save(input, function(data, success) {
	  		$("#updateskill").css("visibility","hidden");
			$("#save1").css("visibility","visible");
	  		if(success){
	  	 alert("Skills successfully inserted");
	  	
	  		$('#displayDataskill').append("<tr class='skilltr'><td class='skilltd'>"+data.skills+"</td><td class='skilltd'>"+data.empId+"</td><td class='skilltd'>"+data.rating+"</td><td class='skilltd'><input type='button' value='Edit' id='"+data.id+"' class='dynamicEdit btn-info'></td></tr>");
	  		$("#skill").val("");
		    $("#rating").val("");
	  		var obj1=data.id;
	  		$("#save1").show();
			$('.dynamicEdit').bind("click", function(event){
               
				this.editSkill($(event.target).attr("id"));
			   
		    }.ctx(this));
		     	
			}
	  		
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
		 var input={"payload":{"id":obj1,"skills":$('#skill').val(),
				"empId":$('#empid').val(),"rating":$('#rating').val(),
				}}; 
    RequestManager.editskills(input, function(data, success)
    {
    	 
        if(success){
        alert("Edit skill successfully updated");
        $("updateskill").css("visibility","hidden");
        $("#save1").css("visibility","visible");
        $("#skill").val("");
 		$("#rating").val("");
 		App.loadSkill(empId);
 		
 		
        }
        	      
        else{
         alert("Edit Skills failed to update");
        }
    }.ctx(this));
	 }	
	    		 }