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
		 this.FilterEmployee();
		
		//this.Filterdata();
	}.ctx(this));
	
}




FilterEmp.prototype.FilterEmployee = function(){
 

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
	console.log(payload);
	
	    RequestManager.getFilterEmployee({"payload" : payload}, function(data, success) {
	   if(success){
	    if(data.length != 0){
			     
			     alert(" successfully found records ");
			    
	    }
	    else{
	    	alert(" No record found");
	    	$('#displayData').html('<tr><th>EmployeeId</th><th>EmployeeName</th><th>Gender</th><th>DOB</th><th>DOJ</th><th>Email</th><th>FatherName</th><th>Designation</th><th>HighestQualification</th><th>SkypeId</th><th>ContactNo</th></tr>');
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
			+item.currentDesignation+"</td><td>"+item.highestQualification+"</td><td>"+item.skype+"</td><td>"
			+item.contactNo+"</td></tr>");
				   }
			    
	 //refresh records
			    
var content = '';
content += '<tr><th>EmployeeId</th><th>EmployeeName</th><th>Gender</th><th>DOB</th><th>DOJ</th><th>Email</th><th>FatherName</th><th>Designation</th><th>HighestQualification</th><th>SkypeId</th><th>ContactNo</th></tr>';
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
 

 

	