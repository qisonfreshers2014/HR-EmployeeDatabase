function listHoliday() {
	Loader.loadHTML('.container', 'resources/js/Holidays/holidays.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

listHoliday.prototype.handleShow = function() {
	 
	 $('.container').show();
	 var contentinput = {"payload":{} };
	 RequestManager.getHolidaysData(contentinput, function(data, success) {
	   if(success){
	    var content = data;
	    var status = success;
	    this.tableDisplay(content, status);
	            
	  }else{
	    
	    alert("failed to add");
	   }  
	    
	  }.ctx(this));
	 
	 $('#start_from').focusin(function(){
	  
	  var currentYear = (new Date).getFullYear();
	  $( ".date" ).datepicker({dateFormat:'yy-mm-dd',showButtonPanel:true,changeMonth:true,changeYear:true,showAnim:'drop',minDate:(new Date((currentYear - 2), 12, 1)),maxDate:new Date((currentYear + 1), 11, 31)});
	  
	  
	  
	 }.ctx(this));
	 
	 $('#description').focusout(function(){
	  if($("#description").val() == ""){
	   $("#descrp").css("visibility","visible");
	   return false;
	  }else if(!($("#description").val().match('^[A-Za-z]+( [A-Za-z]+)*$') || $("#description").val() == isNaN)) {
	   $("#descrp").css("visibility","visible");
	   $("#descrp").html("Only space and characters are allowed");
	   return false;
	  }else
	   $("#descrp").css("visibility","hidden");
	  
	 }.ctx(this));
	 
	 $('#dropdown').focusout(function(){
	  if($('select').val() == "Select"){
	   $("#type").css("visibility","visible"); 
	   return false;
	  
	  }else{
	   $("#type").css("visibility","hidden");
	  }
	  
	 }.ctx(this));
	 
	 
	 $('#save').click(function(){
	  
	  this.validateHolidays();
	  
	 }.ctx(this));
	 

	}



	//function to validate and save 
	listHoliday.prototype.validateHolidays = function(){
	 var currentYear = (new Date).getFullYear();
	 var month = (new Date).getMonth()+1;
	 var date = (new Date).getDate();

	 $( ".date" ).datepicker({dateFormat:'yy-mm-dd',showButtonPanel:true,changeMonth:true,changeYear:true,showAnim:'drop',minDate:(new Date((currentYear - 2), 12, 1)),maxDate:new Date((currentYear + 1), 11, 31)});
	 
	if($("#start_from").val() == "" || $("#description").val() == "" || $("select").val() == "Select"){
	 
	 if($("#start_from").val() == "" ){
	  $("#fromDate").css("visibility","visible");
	  return false;
	 }else{
	  $("#fromDate").css("visibility","hidden");
	 }
	  
	 if($('select').val() == "Select"){
	  $("#type").css("visibility","visible"); 
	  return false;
	 }
	 else{
	  $("#type").css("visibility","hidden");
	 }
	 
	 if($("#description").val() == ""){
	  $("#descrp").css("visibility","visible");
	  
	 }else
	  $("#descrp").css("visibility","hidden");
	 
	 
	}else if(!($("#description").val().match('^[A-Za-z]+( [A-Za-z]+)*$'))){
	 if(!($("#description").val() == isNaN)){
	  
	  $("#descrp").css("visibility","visible");
	  $("#descrp").html("Only space and characters are allowed");
	 
	 }else{
	  $("#descrp").css("visibility","hidden");
	  
	 }
	   

	}else{
	 
	  var input = {"payload":{"fromDate":$("#start_from").val()+' 00:00:00',
	         "toDate":$("#start_from").val()+' 00:00:00',
	         "description":$('#description').val(),
	         "type":$("#dropdown").val()}
	         };
	  
	  $("#start_from").val("");
	  //$('#ends_at').val("");
	  $("#description").val("");
	  $('#dropdown').val("");
	  
	  RequestManager.holidaysData(input, function(data, success) {
	    if(success){
	     var content = data;
	     var status = success;
	     this.contentDisplay(content, status);
	     alert("Successfully saved");
	     
	    }else if(data.code == 9016){
	    
	     alert(" Date already exists ");
	    
	    }else{
	     
	     alert("Failed to save");
	    
	    }  
	     
	   }.ctx(this)); 
	  
	 } 
	 


	}




//function to display whole Holidays data in a table and update call back
listHoliday.prototype.tableDisplay = function(content, status){
	$("#save").show();
	
	$("#display").on("click",".dynamicEdit",function(event){
		var releaseId=event.target.id;	    
		this.editData(releaseId);
	}.ctx(this));
	
	$("#update").click(function(){
		
		
		if($("#start_from").val() == "" || $("#description").val() == "" || $("select").val() == "Select"){
			
			if($("#start_from").val() == "" ){
				$("#fromDate").css("visibility","visible");
				return false;
			}else{
				$("#fromDate").css("visibility","hidden");
			}
			
					
			if($('select').val() == "Select"){
				$("#type").css("visibility","visible");	
				return false;
			}
			else{
				$("#type").css("visibility","hidden");
			}
				
			 if($("#description").val() == ""){
				  $("#descrp").css("visibility","visible");
				  
				 }else
				  $("#descrp").css("visibility","hidden");
				 
				 
				}else if(!($("#description").val().match('^[A-Za-z]+( [A-Za-z]+)*$'))){
				 if(!($("#description").val() == isNaN)){
				  
				  $("#descrp").css("visibility","visible");
				  $("#descrp").html("Only space and characters are allowed");
				 
				 }else{
				  $("#descrp").css("visibility","hidden");
				  
				 }
				   

				}else{
		
			var id = $("#holidayID").text();
		
			var updateContent = {"payload":{"id":id,
										"fromDate":$("#start_from").val()+' 00:00:00',
										"toDate":$("#start_from").val()+' 00:00:00',
										"description":$("#description").val(),
										"type":$("#dropdown").val()
										}
							};
		
		
		
			$("#start_from").val("");
			//$('#ends_at').val("");
			$("#description").val("");
			$('#dropdown').val("");
		
		
			RequestManager.dynamicallyEdit(updateContent, function(data, success) {
				if(success){
				$("#save").show();
				$("#update").css("visibility","hidden");
				alert("successfully updated");
				
			
				}else if(data.code == 9016){
				
					alert("Date you are trying yo update already exists");
				}else{

				alert("failed to update");
				}		

			}.ctx(this));	
		
	}

	});
	
	
	for(var i = 0; i < content.length; i++) {
		var obj = content[i];
		
		var fromdateformat = new Date(obj.fromDate);
		var year = fromdateformat.getFullYear();
		var month = fromdateformat.getMonth()+1;
		var date = fromdateformat.getDate();

		var dayNames = new Array('Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday');
		var day = dayNames[fromdateformat.getDay()];
		
			if(obj.type == "Mandatory"){
				$('#mandatorytable').append('<table><tbody></tbody></table>');
				$('#mandatorytable tr:last').after("<tr><td>"+year + "-" + month + "-" +date+"</td><td>"+obj.description+"</td><td>"+day+"</td><td><input type='button' value='Edit' id='"+obj.id+"' class='dynamicEdit'></td></tr>");
			
			}	
				
			if(obj.type == "Weekdays"){
				$('#weekendstable').append('<table><tbody></tbody></table>');
				$('#weekendstable tr:last').after("<tr><td>"+year + "-" + month + "-" +date+"</td><td>"+obj.description+"</td><td>"+day+"</td><td><input type='button' value='Edit' id='"+obj.id+"' class='dynamicEdit'></td></tr>");
				
			}	
				
		
			if(obj.type == "Optional"){
				$('#optionaltable').append('<table><tbody></tbody></table>');
				$('#optionaltable tr:last').after("<tr><td>"+year + "-" + month + "-" +date+"</td><td>"+obj.description+"</td><td>"+day+"</td><td><input type='button' value='Edit' id='"+obj.id+"' class='dynamicEdit'></td></tr>");
				
			}
			
	}
	

}
	
//function to edit Holiday data
listHoliday.prototype.editData = function(Id){
	$("#update").css("visibility","visible");
	var input ={"payload":{"id":Id}};
	
	RequestManager.getHoliday(input, function(data, success) {
		if(success){
				
				var editdata = data[0];
								
				var fromvalue = editdata.fromDate;
				var fromres = new Date(fromvalue);
				var fromyear  = fromres.getFullYear();
				var frommonth = fromres.getMonth()+1 ;
				var fromdate = fromres.getDate();			
				
							
				$("#start_from").val(fromyear+"-"+frommonth+"-"+ fromdate);
				//$("#ends_at").val(toyear+"-"+tomonth+"-"+ todate);
				$("#description").val(editdata.description);
				$("#dropdown").val(editdata.type);
				$("#holidayID").text(editdata.id);
				$("#save").hide();
		}
		
		
	}.ctx(this));
	
	
}

//function to display the saved content in a table
listHoliday.prototype.contentDisplay = function(content, status){
	if(status){
		
		var value = content.fromDate;
		var res = new Date(value);
		var year  = res.getFullYear();
		var month = res.getMonth()+1 ;
		var date = res.getDate (); 

		var dayNames = new Array('Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday');
		var day = dayNames[res.getDay()];
		
			if(content.type == "Mandatory"){
				$('#mandatorytable').append('<table><tbody></tbody></table>');
				$('#mandatorytable tr:last').after("<tr><td>"+year + "-" + month + "-" +date+"</td><td>"+content.description+"</td><td>"+day+"</td><td><input type='button' value='Edit' id='"+content.id+"' class='dynamicEdit'></td></tr>");
				
				
			}
				
		
			if(content.type == "Weekdays"){
				$('#weekendstable').append('<table><tbody></tbody></table>');
				//var table = $('#weekends').children(); 
				$('#weekendstable tr:last').after("<tr><td>"+year + "-" + month + "-" +date+"</td><td>"+content.description+"</td><td>"+day+"</td><td><input type='button' value='Edit' id='"+content.id+"' class='dynamicEdit'></td></tr>");
					
			}
				
			
			if(content.type == "Optional"){
				$('#optionaltable').append('<table><tbody></tbody></table>');
				//var table = $('#optional').children(); 
				$('#optionaltable tr:last').after("<tr><td>"+year + "-" + month + "-" +date+"</td><td>"+content.description+"</td><td>"+day+"</td><td><input type='button' value='Edit' id='"+content.id+"' class='dynamicEdit'></td></tr>");
							
				
			}

		}		
		
	
	
}

var listHoliday= new listHoliday();

		


