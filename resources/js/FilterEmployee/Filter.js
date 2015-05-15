function FilterEmp() {
	Loader.loadHTML('.container', 'resources/js/FilterEmployee/FilterEmployee.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

FilterEmp.prototype.handleShow = function(empid) {
	$('.container').show();
	
	$( "#datepicker" ).datepicker({dateFormat:'yy-mm-dd',showButtonPanel:
	 true,changeMonth:true,changeYear:true,showAnim:'drop',minDate:new Date(1993,12,31),
	 maxDate:new Date(2050,12,31)});
		
	$('#Filter').click(function(){
	if($('#filter').val()!=0 || $('#gender').val()!=0 || $('#qualification1').val()!="" || $('#datepicker').val()!=""||
				$('#designation').val()!=0 || $('#year1').val()!="" || $('#year2').val()!=""){
			$('.heading1').css("visibility","visible");
	}
			this.FilterEmployee(empid);
		
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

	$('#backfilter').click(function() {
		App.listEmployee();
	}.ctx(this));
	
	
}



FilterEmp.prototype.FilterEmployee = function(empid){
	
	if($('#filter').val()==0 && $('#gender').val()==0 && $('#qualification1').val()=="" && $('#datepicker').val()==""&&
			$('#designation').val()==0 && $('#year1').val()=="" && $('#year2').val()==""){
				
			alert("Select at least one field");
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
		alert("Please do not enter future date of join");
		return;
	}

	 //validations for qualification
      var text = document.getElementById("qualification1").value;
	 
		if($('#qualification1').val()!="" ){
		var regex = /^[A-Za-z.]+( [A-Za-z.]+)*$/;
		var qual = $('#qualification1').val();
		 if(text.charAt(0)==" ")
		   {
		 alert("First letter should not be space"); 
			 return;
			 }
		 else if(qual==""){
			 $("#herror").text("");
			 return;
			 }
		 
		 else if(!(qual.match(regex))){
					  alert("Please enter characters and only one space is allowed");
					  return;
		 			}
		}
		  
		//validations for year of experience
		if($('#year1').val()!="" ){
		var year=	$('#year1').val();
			var num	= /^[0-9.]+$/;
			if(!(year.match(num))){
				alert("Enter only numbers for year of experience");
				return;
				}
			}
		if($('#year2').val()!="" ){
			var year1=	$('#year2').val();
				var num	= /^[0-9.]+$/;
				if(!(year1.match(num))){
					alert("Enter only numbers for year of experience");
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
		//alert("Employee details filtered successfully ");
	  	$('#displayData').html('<tr><th>Employee Id</th><th>Employee Name</th><th>Gender</th><th>DOB</th><th>DOJ</th><th>Email</th><th>Fathers Name</th><th>Designation</th><th>Highest Qualification</th><th>Skype Id</th><th>Contact No</th><th>Years Of Experience</th><th>Skills</th><th>View Details</th></tr>');
	    }
	    else{
	    //	alert(" No record found");
	    	$('#displayData').html('<tr><th>Employee Id</th><th>Employee Name</th><th>Gender</th><th>DOB</th><th>DOJ</th><th>Email</th><th>Fathers Name</th><th>Designation</th><th>Highest Qualification</th><th>Skype Id</th><th>Contact No</th><th>Years Of Experience</th><th>Skills</th><th>View Details</th></tr>');
	    	    /*$("#gender").val("");
			    $("#designation").val("");
			    $("#datepicker").val("");
			    $("#year1").val("");
			    $("#year2").val("");
			    $("#qualification1").val("");
			    $("#filter").val("");*/
			    $('#displayData tbody').empty();
	   
	    }
	   
			  		  
	   var i;
	   for(i = 0; i < data.length; i++) {
        var item = data[i];
				       
	 					 
	 
$('#displayData').append("<tr><td>"+item.employeeId+"</td><td>"+item.employeeName+"</td><td>"+item.gender+"</td><td>"
+new Date(item.dateOfBirth).getFullYear()+"-"+(new Date(item.dateOfBirth).getMonth()+1)+"-"+new Date(item.dateOfBirth).getDate()+"</td><td>"
+new Date(item.dateOfJoining).getFullYear()+"-"+(new Date(item.dateOfJoining).getMonth()+1)+"-"+new Date(item.dateOfJoining).getDate()+"</td><td>"+item.email+"</td><td>"+item.fathersName+"</td><td>"
+item.currentDesignation+"</td><td>"+item.highestQualification+"</td><td>"+item.skype+"</td><td>"+item.contactNo+"</td>" +
		"<td>"+item.yearsofexperience+"</td><td>"+item.skill+"</td><td><input type='button' value='View' id='"+item.id+"' class='dynamicView btn-primary btn-md'></td></tr>");
	
	   }
	   $('.dynamicView').bind("click", function(empid){
			  
			 //App.loadViewEmployee(empid);
			  
			 }.ctx(this));
			    
	   
			    
var content = '';
content += '<tr><th>Employee Id</th><th>Employee Name</th><th>Gender</th><th>DOB</th><th>DOJ</th><th>Email</th><th>Fathers Name</th><th>Designation</th><th>Highest Qualification</th><th>Skype Id</th><th>Contact No</th><th>Years Of Experience</th><th>Skills</th><th>View Details</th></tr>';
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
	              content += '<td>' +item.skill +'</td>';
	              content += '<td>' +"<input type='button' value='View' id='"+item.employeeId+"' class='dynamicView btn-primary btn-md'> "+'</td>';
	              content += '</tr>';
	              $('#displayData').html(content);
	              /*$("#gender").val("");
				    $("#designation").val("");
				    $("#datepicker").val("");
				    $("#year1").val("");
				    $("#year2").val("");
				    $("#qualification1").val("");
				    $("#filter").val("");*/
}
	             $('.dynamicView').bind("click", function(event){
	          
	            	     var empid = $(event.target).attr('id');
	            		 App.loadViewEmployee(empid);
	            		// this.viewEmployeedetails(empid);
	            	 
	               			
	     			  
	     			 }.ctx(this));
	     			    
				   } else{
					  
					    alert("no record found");
					    }
					}.ctx(this));

}
 

