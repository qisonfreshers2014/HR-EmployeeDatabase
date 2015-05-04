function FilterEmp() {
	Loader.loadHTML('.container', 'resources/js/FilterEmployee/FilterEmployee.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

FilterEmp.prototype.handleShow = function() {
	$('.container').show();
	$( "#datepicker" ).datepicker({dateFormat:'yy-mm-dd',showButtonPanel:
	 true,changeMonth:true,changeYear:true,showAnim:'drop',minDate:new Date(1993,12,31),
	 maxDate:new Date(2050,12,31)});
		  	  
		$("#year1").focusout(function(){
        if($(this).val().match('[0-9-+]+$')){
			   $("#yerror").text("");
			  }
			  else{
			   $("#yerror").text("should enter only numbers");
              
			  }
	});
	$("#year2").focusout(function(){
        if($(this).val().match('[0-9-+]+$')){
			   $("#yerror").text("");
			  }
			  else{
			   $("#yerror").text("should enter only numbers");
              
			  }
	});
	$("#qualification1").focusout(function(){
        if($(this).val().match('[a-zA-Z.""]+$')){
			   $("#herror").text("");
			  }
			  else{
			   $("#herror").text("Should enter only characters only");
              
			  }
	});
	
	$('#Filter').click(function(){
		
		/*event.preventDefault();*/
		
		this.FilterEmployee();
		//this.Filterdata();
	}.ctx(this));
	
}

/*FilterEmp.prototype.validateFilter=function(){
	
	 var x=document.getElementById("datepicker").value;
	  var error=document.getElementById("derror")
	  var doj=new Date(x);
	  var today= new Date();
	  var dojday=doj.getDate();
	  if(doj>today)
	  {
	  	error.innerHTML="Invalid Date Of Join";
	  	error.style.visibility = "visible"; 
	  valid = false;
	  }
	  else
	  	error.style.visibility="hidden";
	       
	  
	  return true;
}
*/

FilterEmp.prototype.FilterEmployee = function(){
	 
 var inputdate=$('#datepicker').val()+' 00:00:00'
 if(inputdate == null)
	 {
	 inputdate=null;
	 }
	var payload = {};
	if($('#gender').val().trim().length != 0){
		payload.gender = $('#gender').val();
	}
	if($('#qualification1').val().trim().length != 0){
		payload.highestQualification = $('#qualification1').val();
	}
	if($('#designation').val() != 0){
		payload.currentDesignation = $('#designation').val();
	}
	if($('#datepicker').val().trim().length != 0){
		payload.dateOfJoining = $('#datepicker').val() + ' 00:00:00';
	}
	if($('#year1').val().trim().length != 0 && $('#year2').val().trim().length !=0 ){
		payload.from = ($('#year1').val());
		payload.to = ($('#year2').val());
	}
	console.log(payload);
/*var input={"payload":{"gender":$('#gender').val(),"currentDesignation":$('#designation').val(),
			"dateOfJoining":$('#datepicker').val()+' 00:00:00',"from":$('#year1').val(),"to":$('#year2').val(),
			 "highestQualification":$('#qualification1').val(),
			}};
	if($('#datepicker').val().trim().length == 0 ){
	var	input={"payload":{"gender":$('#gender').val(),"currentDesignation":$('#designation').val(),
			 "highestQualification":$('#qualification1').val()
			}};
	}
	if($('#qualification1').val().trim().length == 0 ){
		var	input={"payload":{"gender":$('#gender').val(),"currentDesignation":$('#designation').val(),
				 "highestQualification":$('#qualification1').val()
			}};
		}
	*/
	
	    RequestManager.getFilterEmployee({"payload" : payload}, function(data, success) {
	    	 
		 if(success){
			 alert("success");
			   var i;
				   for(i = 0; i < data.length; i++) {
				       var item = data[i];
	 					 
			//$('#displayData').append('<table></table>');
			$('#displayData').append("<tr><td>"+item.employeeId+"</td><td>"+item.employeeName+"</td><td>"+item.gender+"</td><td>"
			+new Date(item.dateOfBirth).getFullYear()+"-"+(new Date(item.dateOfBirth).getMonth()+1)+"-"+new Date(item.dateOfBirth).getDate()+"</td><td>"
			+new Date(item.dateOfJoining).getFullYear()+"-"+(new Date(item.dateOfJoining).getMonth()+1)+"-"+new Date(item.dateOfJoining).getDate()+"</td><td>"+item.email+"</td><td>"+item.fathersName+"</td><td>"
			+item.currentDesignation+"</td><td>"+item.highestQualification+"</td><td>"+item.skype+"</td><td>"
			+item.contactNo+"</td></tr>");
			    $("#gender").val("");
			    $("#designation").val("");
			    $("#datepicker").val("");
			    $("#year1").val("");
			    $("#year2").val("");
			    $("#qualification1").val("");
				   }    
				  }else{
					  alert("failed to retrieved");					
					  }
					}.ctx(this));
			 
	
	
}
 

	