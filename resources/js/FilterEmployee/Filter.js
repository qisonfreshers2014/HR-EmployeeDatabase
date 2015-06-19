function FilterEmp(empid) {
	Loader.loadHTML('.container', 'resources/js/FilterEmployee/FilterEmployee.html', true, function(){
		this.handleShow(empid);
	}.ctx(this));
}

FilterEmp.prototype.handleShow = function(empid) {
	$('.container').show();
	
	//Appending designation types to the drop down
	var emptyinput={};
	 RequestManager.getDesignationName(emptyinput, function(data, success){
		               
		 if(success){
			          arraylength=data.length;
			           lastId=data[arraylength-1].id;
			           newvalue=lastId+1;
			 for(i=0;i<data.length;i++){
			  var object=data[i];
			 $('#filterdesignation').append($("<option value="+object.id+"> "+object.name+"</option>"));			 
			 }
			 
		 }
		 
	 }.ctx(this));
	
	$( "#dojFrom" ).datepicker({dateFormat:'yy-mm-dd',showButtonPanel:
	 true,changeMonth:true,changeYear:true,showAnim:'drop',minDate:new Date(1993,12,31),
	 maxDate:new Date()});
	$( "#dojTo" ).datepicker({dateFormat:'yy-mm-dd',showButtonPanel:
		 true,changeMonth:true,changeYear:true,showAnim:'drop',minDate:new Date(1993,12,31),
		 maxDate:new Date()});
		
	$('#Filter').click(function(){
	if($('#emptype').val()!="" || $('#gender').val()!=0 || $('#qualification1').val()!="" || $('#dojFrom').val()!=""|| $('#dojTo').val()=="" ||
				$('#filterdesignation').val()!=0 || $('#year1').val()!="" || $('#year2').val()!=""){
			
	}
	
			this.FilterEmployee(empid);
		
		
	}.ctx(this));
	
	$('#reset').click(function() {
		
		
		$("#gender").val("");
		$("#dojFrom").val("");
		$("#dojTo").val("");
		$("#emptype").val("");
		$("#qualification1").val("");
		$("#year1").val("");
		$("#year2").val("");
		$("#filterdesignation").val("");


	}.ctx(this));

	$('#backfilter').click(function() {
		routie("employee");
	}.ctx(this));
	
	
}



FilterEmp.prototype.FilterEmployee = function(empid){
	
	if($('#emptype').val()=="notselected" && $('#gender').val()==0 && $('#qualification1').val()=="" && $('#dojFrom').val()==""&& $('#dojTo').val()=="" &&
			$('#filterdesignation').val()==0 && $('#year1').val()=="" && $('#year2').val()==""){
				
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
	
	
   //validations for date of joining
	
	
	var fromdate=$('#dojFrom').val();
	var todate= $('#dojTo').val();
	var fromdoj=new Date(fromdate).getTime();
	var todoj= new Date(todate).getTime();
	var today=new Date().getTime();
	if($('#dojFrom').val()!="" && $('#dojTo').val()!=""){

	 if(fromdoj>todoj)
	{
		alert("From date can not be greater than To date ");
		return;
	}
	 if(fromdoj>today || todoj>today){
		 
		 alert("Please do not enter future date");
		 return;
	 }

	}	 
	
	if($('#dojFrom').val()=="" && $('#dojTo').val()!=""){
		
		alert("Please enter From date");
		return;
	}
if($('#dojFrom').val()!="" && $('#dojTo').val()==""){
	
	$('#dojTo').val($('#dojFrom').val());
		
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
	if($('#emptype').val() != ""){
		payload.filterEmployee = $('#emptype').val();
		}
	if($('#gender').val().trim().length != 0){
		payload.gender = $('#gender').val();
	}
	if($('#qualification1').val().trim().length != 0){
		payload.highestQualification = $('#qualification1').val();
	}
	if($('#filterdesignation').val() != 0){
		payload.currentDesignation = $('#filterdesignation').val();
	}
	if($('#dojFrom').val().trim().length != 0){
		payload.dateOfJoiningFrom = $('#dojFrom').val() + ' 00:00:00';
	}
	if($('#dojTo').val().trim().length != 0){
		payload.dateOfJoiningTo = $('#dojTo').val() + ' 00:00:00';
	}
	if($('#year1').val().trim().length != 0 && $('#year2').val().trim().length !=0 ){
		payload.from = ($('#year1').val());
		payload.to = ($('#year2').val());
	
 }

           var content='';
	
	    RequestManager.getFilterEmployee({"payload" : payload}, function(data, success) {
	    if(success){
	    if(data.length != 0){
		
	    	$('.heading1').css("visibility","visible");
	  	$('#displayData').html('<tr class="displaytr"><th class="displayth">Employee Id</th><th class="displayth">Employee Name</th><th class="displayth">Gender</th><th class="displayth">DOB</th><th class="displayth">DOJ</th><th class="displayth">Email</th><th class="displayth">Fathers Name</th><th class="displayth">Designation</th><th class="displayth">Highest Qualification</th><th class="displayth">Skype Id</th><th class="displayth">Contact No</th><th class="displayth">Years Of Experience</th><th class="displayth">Skills</th><th class="displayth">View Details</th></tr>');
	  	
	    }
	    else{
	          alert("No record found");
	          $('#displayData').html('<tr class="displaytr"><th class="displayth">Employee Id</th><th class="displayth">Employee Name</th><th class="displayth">Gender</th><th class="displayth">DOB</th><th class="displayth">DOJ</th><th class="displayth">Email</th><th class="displayth">Fathers Name</th><th class="displayth">Designation</th><th class="displayth">Highest Qualification</th><th class="displayth">Skype Id</th><th class="displayth">Contact No</th><th class="displayth">Years Of Experience</th><th class="displayth">Skills</th><th class="displayth">View Details</th></tr>');
	    	   
			    $('#displayData tbody').empty();
	   
	    }
	   
	    var monthsArray=new Array(12);
		monthsArray[0]="January";
		monthsArray[1]="Febravary";
		monthsArray[2]="March";
		monthsArray[3]="April";
		monthsArray[4]="May";
		monthsArray[5]="June";
		monthsArray[6]="July";
		monthsArray[7]="August";
		monthsArray[8]="September";
		monthsArray[9]="October";
		monthsArray[10]="November";
		monthsArray[11]="December";
		
	   var i;
	   for(i = 0; i < data.length; i++) {
        var item = data[i];
				       
	 					 
	 
$('#displayData').append("<tr class='displaytr'><td class='displaytd'>"+item.employeeId+"</td><td class='displaytd'>"+item.employeeName+"</td><td class='displaytd'>"+item.gender+"</td><td class='displaytd'>"
+new Date(item.dateOfBirth).getFullYear()+"-"+monthsArray[new Date(item.dateOfBirth).getMonth()]+"-"+new Date(item.dateOfBirth).getDate()+"</td><td class='displaytd'>"
+new Date(item.dateOfJoining).getFullYear()+"-"+monthsArray[new Date(item.dateOfJoining).getMonth()]+"-"+new Date(item.dateOfJoining).getDate()+"</td><td class='displaytd'>"+item.email+"</td><td>"+item.fathersName+"</td><td class='displaytd'>"
+item.currentDesignation+"</td><td class='displaytd'>"+item.highestQualification+"</td><td class='displaytd'>"+item.skype+"</td><td class='displaytd'>"+item.contactNo+"</td>" +
		"<td class='displaytd'>"+item.yearsofexperience+"</td><td class='displaytd'>"+item.skill+"</td><td class='displaytd'><input type='button' value='View' id='"+item.id+"' class='dynamicView btn-primary btn-md'></td></tr>");
	
      
        			
	   }
	  
			    
var content = '';
content += '<tr class="displaytr"><th class="displayth">Employee Id</th><th class="displayth">Employee Name</th><th class="displayth">Gender</th><th class="displayth">DOB</th><th class="displayth">DOJ</th><th class="displayth">Email</th><th class="displayth">Fathers Name</th><th class="displayth">Designation</th><th class="displayth">Highest Qualification</th><th class="displayth">Skype Id</th><th class="displayth">Contact No</th><th class="displayth">Years Of Experience</th><th class="displayth">Skills</th><th class="displayth">View Details</th></tr>';
for (var i = 0; i < data.length; i++) {
			     var item = data[i];
			     content += '<tr  class="displaytr">';
	             content += '<td class="displaytd">'  + item.employeeId+ '</td>';
	             content += '<td class="displaytd">' + item.employeeName + '</td>';
	             content += '<td class="displaytd">' +item.gender+'</td>';
content += '<td class="displaytd">' +new Date(item.dateOfBirth).getFullYear()+"-"+monthsArray[new Date(item.dateOfBirth).getMonth()]+"-"+new Date(item.dateOfBirth).getDate()+'</td>';
content += '<td class="displaytd">' +new Date(item.dateOfJoining).getFullYear()+"-"+monthsArray[new Date(item.dateOfJoining).getMonth()] +"-"+new Date(item.dateOfJoining).getDate()+'</td>';
	              content += '<td class="displaytd">' +item.email +'</td>';
	              content += '<td class="displaytd">' +item.fathersName +  '</td>';
	              content += '<td class="displaytd">' +item.currentDesignation +'</td>';
	              content += '<td class="displaytd">' +item.highestQualification +'</td>';
	              content += '<td class="displaytd">' +item.skype +'</td>';
	              content += '<td class="displaytd">' +item.contactNo +'</td>';
	              content += '<td class="displaytd">' +item.yearsofexperience +'</td>';
	              content += '<td class="displaytd">' +item.skill +'</td>';
	              content += '<td class="displaytd">' +"<input type='button' value='View' id='"+item.employeeId+"' class='dynamicView btn-primary btn-md'> "+'</td>';
	              content += '</tr>';
	              $('#displayData').html(content);
	             
}
	             $('.dynamicView').bind("click", function(event){
	          
	            	     var empid = $(event.target).attr('id');
	            		 App.loadViewEmployee(empid);
	            			
	     			  
	     			 }.ctx(this));
	     			    
				   } else{
					  
					    alert("no record found");
					    }
					}.ctx(this));

}
 

