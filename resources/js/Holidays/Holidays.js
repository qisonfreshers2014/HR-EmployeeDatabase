function listHoliday() {
	Loader.loadHTML('.leftContainer', 'resources/js/Holidays/Holidays.html', true, function(){
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
		
		this.calendarCall();
		
	}.ctx(this));
	
	/*$('#dropdown').change(function(){
		
		this.dropValidate();
	}.ctx(this));*/
	
	$('#save').click(function(){
		
		this.validateHolidays();
		
	}.ctx(this));
	
/*	$('#add').click(function(){
		$("#start_from").val("");
		$('#ends_at').val("");
		$("#description").val("");
		$('#dropdown').val("");
		
	}.ctx(this));
	*/


}

listHoliday.prototype.calendarCall = function(){
	var currentYear = (new Date).getFullYear();
	$( ".date" ).datepicker({dateFormat:'yy-mm-dd',showButtonPanel:true,changeMonth:true,changeYear:true,showAnim:'drop',minDate:(new Date((currentYear - 2), 12, 1)),maxDate:new Date((currentYear + 1), 11, 31)});
	
	//$("#ends_at").val($('#start_from').val());

}

listHoliday.prototype.validateHolidays = function(){
	var currentYear = (new Date).getFullYear();
	var month = (new Date).getMonth()+1;
	var date = (new Date).getDate();
	
	$( ".date" ).datepicker({dateFormat:'yy-mm-dd',showButtonPanel:true,changeMonth:true,changeYear:true,showAnim:'drop',minDate:(new Date((currentYear - 2), 12, 1)),maxDate:new Date((currentYear + 1), 11, 31)});
	
if($("#start_from").val() == "" || $("#description").val() == "" || $("select").val() == "Default"){
	
	if($("#start_from").val() == "" ){
		$("#fromDate").css("visibility","visible");
		return false;
	}else{
		$("#fromDate").hide();
	}
	/*if($("#ends_at").val() == "" ){
		$("#ends_at").val($("#start_from").val());
		//$("#toDate").css("visibility","visible");
		return false;
	}else{
		$("#toDate").hide();
	}	*/
	
	if($("#description").val() == ""){
		$("#descrp").css("visibility","visible");
		return false;
	}if(!($("#description").val().match('^[a-zA-Z ]{4,25}$') || $("#description").val() == isNaN)) {
		$("#descrp").css("visibility","visible");
		$("#descrp").html("Only space with minimum 4 characters are allowed").slideUp().slideDown();
		return false;
	}else{
		$("#descrp").hide();
	}
	
	if($('select').val() == "Default"){
		$("#type").css("visibility","visible");	
		return false;
	}
	else{
		$("#type").hide();
	}
		
	
}else{
	
		//this.dateValidations();
		
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

/*listHoliday.prototype.dateValidations = function(){
	
	var input={"payload":{}};
	RequestManager.getHolidaysData(input, function(data, success) {
	if(success){
		if(data.length == 0){
			
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
					 alert("successfully added");
				 	
				}else{
				  
				  alert("failed to add");
				 }		
				  
				}.ctx(this)); 
			
		}
		if(data.length > 0){
			
			for(var i = 0; i < data.length; i++) {
				var obj = data[i];
				
				var fromdateformat = new Date(obj.fromDate);
				var year = fromdateformat.getFullYear();
				var month = fromdateformat.getMonth()+1;
				var date = fromdateformat.getDate();
				var format = (year + "-" + month + "-" +date);
			
				if($("#start_from").val() ==  format){
					
					alert("Date already exists");
					break;
					
				}
				
			}
			
			
		}	
		
	}

});
	
	
}*/
	
/*	var input = {"payload":{"fromDate":$("#start_from").val()+' 00:00:00',
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
			 alert("successfully added");
		 	
		}else{
		  
		  alert("failed to add");
		 }		
		  
		}.ctx(this)); */

listHoliday.prototype.tableDisplay = function(content, status){
	$("#save").show();
	for(var i = 0; i < content.length; i++) {
		var obj = content[i];
		
		var fromdateformat = new Date(obj.fromDate);
		var year = fromdateformat.getFullYear();
		var month = fromdateformat.getMonth()+1;
		var date = fromdateformat.getDate();

		var dayNames = new Array('Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday');
		var day = dayNames[fromdateformat.getDay()];
		
			if(obj.type == "Mandatory"){
				$('#mandatory').append('<table></table>');
				$('#mandatory tr:last').after("<tr><td>"+year + "-" + month + "-" +date+"</td><td>"+obj.description+"</td><td>"+day+"</td><td><input type='button' value='Edit' id='"+obj.id+"' class='dynamicEdit'></td></tr>");
			
			}	
				
			if(obj.type == "Weekdays"){
				$('#weekends').append('<table></table>');
				$('#weekends tr:last').after("<tr><td>"+year + "-" + month + "-" +date+"</td><td>"+obj.description+"</td><td>"+day+"</td><td><input type='button' value='Edit' id='"+obj.id+"' class='dynamicEdit'></td></tr>");
				
			}	
				
		
			if(obj.type == "Optional"){
				$('#optional').append('<table></table>');
				$('#optional tr:last').after("<tr><td>"+year + "-" + month + "-" +date+"</td><td>"+obj.description+"</td><td>"+day+"</td><td><input type='button' value='Edit' id='"+obj.id+"' class='dynamicEdit'></td></tr>");
				
			}
			
	}
	
	$('.dynamicEdit').click(function(event){
	    var releaseId=event.target.id;
	 					  				    
	   this.editData(releaseId);
	
	   }.ctx(this));


	
	
}
	

listHoliday.prototype.editData = function(Id){
	
	$("#update").show();
	var input ={"payload":{"id":Id}};
	
	RequestManager.getHoliday(input, function(data, success) {
		if(success){
				
				var editdata = data[0];
								
				var fromvalue = editdata.fromDate;
				var fromres = new Date(fromvalue);
				var fromyear  = fromres.getFullYear();
				var frommonth = fromres.getMonth()+1 ;
				var fromdate = fromres.getDate();			
				
				
			/*	var tovalue = editdata.toDate;
				var tores = new Date(tovalue);
				var toyear  = tores.getFullYear();
				var tomonth = tores.getMonth()+1 ;
				var todate = tores.getDate ();	*/
				
				
				$("#start_from").val(fromyear+"-"+frommonth+"-"+ fromdate);
				//$("#ends_at").val(toyear+"-"+tomonth+"-"+ todate);
				$("#description").val(editdata.description);
				$("#dropdown").val(editdata.type);
				$("#save").hide();
				
				
				$("#update").css("visibility","visible").click(function(){
					
					var updateContent = {"payload":{"id":Id,
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
						$("#update").hide();
						//data.fromDate
						alert("successfully updated");
						//this.contentDisplay(data,success);
					
	
						}else{

							alert("failed to update");
						}		

					}.ctx(this));	
					
				
			});
				
		
		
		}
		
		
	}.ctx(this));
	
	
}

		


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
				$('#mandatory').append('<table></table>');
				$('#mandatory tr:last').after("<tr><td>"+year + "-" + month + "-" +date+"</td><td>"+content.description+"</td><td>"+day+"</td><td><input type='button' value='Edit' id='"+content.id+"' class='dynamicEdit'></td></tr>");
				
				
			}
				
		
			if(content.type == "Weekdays"){
				$('#weekends').append('<table></table>');
				//var table = $('#weekends').children(); 
				$('#weekends tr:last').after("<tr><td>"+year + "-" + month + "-" +date+"</td><td>"+content.description+"</td><td>"+day+"</td><td><input type='button' value='Edit' id='"+content.id+"' class='dynamicEdit'></td></tr>");
					
			}
				
			
			if(content.type == "Optional"){
				$('#optional').append('<table></table>');
				//var table = $('#optional').children(); 
				$('#optional tr:last').after("<tr><td>"+year + "-" + month + "-" +date+"</td><td>"+content.description+"</td><td>"+day+"</td><td><input type='button' value='Edit' id='"+content.id+"' class='dynamicEdit'></td></tr>");
							
				
			}

			$('.dynamicEdit').click(function(event){
			    var releaseId=event.target.id;
			   
			   this.editData(releaseId);
			
			  
			   }.ctx(this));
			
			
	}		
		
	
	
}

var listHoliday= new listHoliday();

		
			/*var array = $.map(content, function (item, index) {
               table.append("<tr><td>"+item.description+"</td></tr>");
               // return [[item.Name, item.Age, item.ID]];
                });*/
		/*var arr= [];
		$('#display').append('<table></table>');
		var table = $('#display').children(); 
		$.each(content, function(j, obj) {
			$.each(obj, function(key, val){
				arr.push(key);
				arr.push(val);
		});
		});*/
			/*$.each(obj, function(key, val){
				table.append("<tr><td>"+val+"</td></tr>");*/
	           
		
		/*$('#display').append('<table></table>');
		var table = $('#display').children(); 
		for(i=0;i<arr.length;i++){
			table.append("<tr><td>arr[5]</td><td>arr[6]</td></tr>");
		}
		          
		}*/
		
		
		
	
		//var out = '<tr id="sendtoDdb">'
	//	var obj = jQuery.parseJSON( content );
		
		/*var i;
		for(i=0;i<content.length;i++){
			
		
			$('#mandatory tr:last').innerHTML('<tr><td style="padding:5px;"> '+arr[i].fromDate+' </td><td style="padding:5px;"> '+arr[i].toDate+' </td><td style="padding:5px;"> '+arr[i].description+' </td><td style="padding:5px;"> '+arr[i].type+' </td></tr>');
	
		}*/
	
 		
	//	var content = data[i];
	//	alert(content.fromDate);
		
		/*$("#mandatory").append( "<tr>"+ "<td><input type='text' value=" + content.fromDate + "/></td>" +
										"<td><input type='text' value=" + content.toDate + "/></td>"+
										"<td><input type='text' value=" + content.description + "/></td>"+ 
										"<td><input type='text' value=" + content.type + "/></td>"
										);*/

		



