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
		/*var from= $("#year1").val(); 
		var to = $("#year2").val(); 
		$("#year1").focusout(function(){
        if($(this).val().match('[0-9.]+$')){
			   $("#yerror").text("");
			  }
			 else if(parseInt(from)>parseInt(to)){
			   alert("nter only numbers less the TO");
                }
			  else{
				  alert("enter only numbers");
			  }
	});
	$("#year2").focusout(function(){
        if($(this).val().match('[0-9.]+$')){
			   $("#yerror").text("");
			  }
			  else{
			  alert("enter only numbers");
              
			  }
	});
	*/
	/*$("#datepicker").focusout(function(){
	var x=document.getElementById("datepicker").value;
	var doj=new Date(x);
	var today= new Date();
	var dojday=doj.getDate();
	var day;
	if(doj>today)
	{
		alert("invalid doj");
	}
	});
	
	var dateformat=/^\d{4}-\d{2}-\d{2}$/;
	$("#datepicker").focusout(function(){
	   if(!($('#datepicker').val()).match(dateformat)){
	      alert("Please enter Date in yyyy-mm-dd Format");
	     }
	});
	$("#qualification1").focusout(function(){
		var text = document.getElementById("qualification1").value;
		var regex = /^[A-Za-z]+( [A-Za-z]+)*$/;
		var char1=/^[a-zA-Z.]+$/;
		var char2=/^[a-z A-Z]+$/;
		 if(text.charAt(0)==" ")
		   {
			 alert("First letter should not enter space"); 
		   }
		 else if($(this).val().match(char1)){
			  $("#herror").text("");
		  }
			  else if(!($(this).val()).match(regex))  {
				  alert("please enter chacter and only one space");
			    }
			 
			  else if($(this).val().match(char2)){
				  $("#herror").text("");
			  }
			  else{
				  alert("enter charcters only");
			  }
	});
	*/
	$('#Filter').click(function(){
		$('.heading1').css("visibility","visible");
		this.FilterEmployee();
		//this.Filterdata();
	}.ctx(this));
	
	$('#reset').click(function() {
		//e.preventDefault();
		//$('.error').css('visibility', 'hidden');
		$("#gender").val("");
		$("#datepicker").val("");
		$("#filter").val("");
		$("#qualification1").val("");
		$("#year1").val("");
		$("#year2").val("");
		$("#designation").val("");
	

	}.ctx(this));
}



FilterEmp.prototype.FilterEmployee = function(){
	 
	if($('#filter').val()==0 && $('#gender').val()==0 && $('#qualification1').val()=="" && $('#datepicker').val()==""&&
			$('#designation').val()==0 && $('#year1').val()=="" && $('#year2').val()==""){
				
			alert("select at least one field");
			return;
			
	}
	if($('#year1').val()!="" && $('#year2').val()!=""){
		var from = $('#year1').val();
		var to = $('#year2').val();
		if(parseInt(from)>parseInt(to)){
			alert("From value always less than To value");
			return;
		 
		}
	} 
   //validations for date of join
	var x=document.getElementById("datepicker").value;
	var doj=new Date(x);
	var today= new Date();
	var dojday=doj.getDate();
	var day;
	 if(doj>today)
	{
		alert("invalid doj");
		return;
	}

	 //validations for qualification
      var text = document.getElementById("qualification1").value;
	 
		if($('#qualification1').val()!="" ){
		var regex = /^[A-Za-z.]+( [A-Za-z.]+)*$/;
		var qual = $('#qualification1').val();
		 if(text.charAt(0)==" ")
		   {
		 alert("First letter should not enter space"); 
			 return;
			 }
		 else if(qual==""){
			 $("#herror").text("");
			 return;
			 }
		 
		 else if(!(qual.match(regex))){
					  alert("please enter chacter and only one space is allowed");
					  return;
		 			}
		}
		  
		//validations for year of experience
		if($('#year1').val()!="" ){
		var year=	$('#year1').val();
			var num	= /^[0-9.]+$/;
			if(!(year.match(num))){
				alert("enter only numbers for FROM");
				return;
				}
			}
		if($('#year2').val()!="" ){
			var year1=	$('#year2').val();
				var num	= /^[0-9.]+$/;
				if(!(year1.match(num))){
					alert("enter only numbers for TO");
					return;
				}
			}
		 
			  	
			
	
	
	var payload = {};
	if($('#filter').val() != 0){
		payload.filterEmployee = $('#filter').val();
	}
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

 
	
	    RequestManager.getFilterEmployee({"payload" : payload}, function(data, success) {
	    if(success){
	    if(data.length != 0){
		alert(" successfully found records ");
	  	$('#displayData').html('<tr><th>Employee Id</th><th>Employee Name</th><th>Gender</th><th>DOB</th><th>DOJ</th><th>Email</th><th>Fathers Name</th><th>Designation</th><th>Highest Qualification</th><th>Skype Id</th><th>Contact No</th><th>Years Of Experience</th></tr>');
	    }
	    else{
	    	alert(" No record found");
	    	$('#displayData').html('<tr><th>Employee Id</th><th>Employee Name</th><th>Gender</th><th>DOB</th><th>DOJ</th><th>Email</th><th>Fathers Name</th><th>Designation</th><th>Highest Qualification</th><th>Skype Id</th><th>Contact No</th><th>Years Of Experience</th></tr>');
	    	    $("#gender").val("");
			    $("#designation").val("");
			    $("#datepicker").val("");
			    $("#year1").val("");
			    $("#year2").val("");
			    $("#qualification1").val("");
			    $("#filter").val("");
			    $('#displayData tbody').empty();
	   
	    }
	   
			  		  
	   var i;
	   for(i = 0; i < data.length; i++) {
        var item = data[i];
				       
	 					 
	//$('#displayData').append('<table></table>');
			$('#displayData').append("<tr><td>"+item.employeeId+"</td><td>"+item.employeeName+"</td><td>"+item.gender+"</td><td>"
			+new Date(item.dateOfBirth).getFullYear()+"-"+(new Date(item.dateOfBirth).getMonth()+1)+"-"+new Date(item.dateOfBirth).getDate()+"</td><td>"
			+new Date(item.dateOfJoining).getFullYear()+"-"+(new Date(item.dateOfJoining).getMonth()+1)+"-"+new Date(item.dateOfJoining).getDate()+"</td><td>"+item.email+"</td><td>"+item.fathersName+"</td><td>"
			+item.currentDesignation+"</td><td>"+item.highestQualification+"</td><td>"+item.skype+"</td><td>"+item.contactNo+"</td><td>"+item.yearsofexperience+"</td></tr>");
				   }
			    
	 //refresh records
			    
var content = '';
content += '<tr><th>Employee Id</th><th>Employee Name</th><th>Gender</th><th>DOB</th><th>DOJ</th><th>Email</th><th>Fathers Name</th><th>Designation</th><th>Highest Qualification</th><th>Skype Id</th><th>Contact No</th><th>Years Of Experience</th></tr>';
for (var i = 0; i < data.length; i++) {
			     var item = data[i];
			     content += '<tr>';
	              content += '<td>'  + item.employeeId+ '</td>';
	              content += '<td>' + item.employeeName + '</td>';
	              content += '<td>' +item.gender+'</td>';
content += '<td>' +new Date(item.dateOfBirth).getFullYear()+"-"+(new Date(item.dateOfBirth).getMonth()+1)+"-"+new Date(item.dateOfBirth).getDate()+'</td>';
content += '<td>' +new Date(item.dateOfJoining).getFullYear()+"-"+(new Date(item.dateOfJoining).getMonth()+1) +"-"+new Date(item.dateOfJoining).getDate()+'</td>';
	              content += '<td>' +item.email +'</td>';
	              content += '<td>' +item.fathersName +  '</td>';
	              content += '<td>' +item.currentDesignation +'</td>';
	              content += '<td>' +item.highestQualification +'</td>';
	              content += '<td>' +item.skype +'</td>';
	              content += '<td>' +item.contactNo +'</td>';
	              content += '<td>' +item.yearsofexperience +'</td>';
	              content += '</tr>';
	              $('#displayData').html(content);
	              $("#gender").val("");
				    $("#designation").val("");
				    $("#datepicker").val("");
				    $("#year1").val("");
				    $("#year2").val("");
				    $("#qualification1").val("");
				    $("#filter").val("");
				   }    
	    
				  }else{
					  
					    
					   		
					  }
					}.ctx(this));

 

}

	