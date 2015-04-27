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
			alert("successfully added");
		 	
		}else{
		  
		  alert("failed to add");
		 }		
		  
		}.ctx(this));
	
	$('#start_from').click(function(){
		
		this.calendarCall();
		
	}.ctx(this));
	
	/*$('#ends_at').click(function(){
		
		this.calendarCall();
		
	}.ctx(this));
	$('#dropdown').change(function(){
		
		this.dropValidate();
	}.ctx(this));*/
	
	$('#save').click(function(){
		
		this.validateHolidays();
		//this.composeJson();
	
	}.ctx(this));
	
	$('#add').click(function(){
		$("#start_from").val("");
		$('#ends_at').val("");
		$("#description").val("");
		$('#dropdown').val("");
		
	}.ctx(this));
	
	/*$('.dynamicEdit').click(function(){
		alert("sdfdf");
		this.dynamicEditable();
	}.ctx(this));*/

}

listHoliday.prototype.calendarCall = function(){
	var currentYear = (new Date).getFullYear();
	$( ".date" ).datepicker({dateFormat:'yy-mm-dd',showButtonPanel:true,changeMonth:true,changeYear:true,showAnim:'drop',minDate:(new Date((currentYear - 2), 12, 1)),maxDate:new Date((currentYear + 1), 11, 31)});
	
}

listHoliday.prototype.validateHolidays = function(){
	var currentYear = (new Date).getFullYear();
	$( ".date" ).datepicker({dateFormat:'yy-mm-dd',showButtonPanel:true,changeMonth:true,changeYear:true,showAnim:'drop',minDate:(new Date((currentYear - 2), 12, 1)),maxDate:new Date((currentYear + 1), 11, 31)});
if($("#start_from").val() == "" || $("#description").val() == "" || $("select").val() == "Default"){
	
	if($("#start_from").val() == "" ){
		$("#fromDate").css("visibility","visible");
		return false;
	}else{
		$("#fromDate").hide();
	}
	if($("#ends_at").val() == "" ){
		$("#ends_at").val($("#start_from").val());
		//$("#toDate").css("visibility","visible");
		return false;
	}else{
		$("#toDate").hide();
	}	
	
	if($("#description").val() == ""){
		$("#descrp").css("visibility","visible");
		return false;
	}
	if(!($("#description").val().match('^[a-zA-Z ]{4,25}$') || $("#description").val() == isNaN)) {
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
	
	var input = {"payload":{"from_date":$("#start_from").val(),
        "to_date":$("#ends_at").val(),
        "description":$('#description').val(),
        "type":$("#dropdown").val()}
        };
	
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
	
}
/*listHoliday.prototype.dynamicEditable = function(){
	
	alert("asasas");
	var par = $(this).parent().parent(); 
	var tdDate = par.children("td:nth-child(1)"); 
	var tdDescription = par.children("td:nth-child(2)");
	var tdType = par.children("td:nth-child(3)");

	tdDate.html("<input type='text' id='txtDate' value='"+tdDate.html()+"'/>");
	tdDescription.html("<input type='text' id='txtDescr' value='"+tdDescription.html()+"'/>");
	tdType.html("<input type='text' id='txtType' value='"+tdType.html()+"'/>");

	var currentTD = $(this).parents('tr').find('td');
	 if ($(this).html() == 'Edit') {                  
         $.each(currentTD, function () {
             $(this).attr('contenteditable', true);
         });
     } else {
        $.each(currentTD, function () {
             $(this).attr('contenteditable', false);
         });
     }
	
	 var input = {"payload":{"from_date":$("#start_from").val(),
        "to_date":$("#ends_at").val(),
        "description":$('#description').val(),
        "type":$("#dropdown").val()}
        };
	
	RequestManager.dynamicallyEdit(input, function(data, success) {
		 if(success){
			 var content = data;
			 var status = success;
			 this.contentDisplay(content, status);
		 alert("successfully edited");
		 	
		}else{
		  
		  alert("failed to add");
		 }		
		  
		}.ctx(this));
	
	
}*/
listHoliday.prototype.tableDisplay = function(content, status){
	$("#save").show();
	for(var i = 0; i < content.length; i++) {
			var obj = content[i];
			if(obj.type == "Mandatory"){
				$('#mandatory').append('<table></table>');
				$('#mandatory tr:last').after("<tr><td>"+obj.from_date+"</td><td>"+obj.description+"</td><td>"+obj.type+"</td><td><input type='button' value='Edit' id='"+obj.id+"' class='dynamicEdit'></td></tr>");
			
				$('.dynamicEdit').bind("click",function(){
					
					$("#start_from").val(obj.from_date);
					$("#description").val(obj.description);
					$("#dropdown").val(obj.type);
					$("#save").hide();
					
					$("#update").css("visibility","visible").click(function(){
						var updateContent = {"payload":{"id":obj.id,
							"from_date":obj.from_date,
							"to_date":obj.to_date,
							"description":obj.description,
							"type":obj.type
							}
				};
					RequestManager.dynamicallyEdit(updateContent, function(data, success) {
						if(success){
							$("#save").show();
							$("#update").hide();
							data.from_date
							alert("successfully updated");
 	
							}else{
  
								alert("failed to update");
							}		
  
						}.ctx(this));	
						
					
				});
			
			
			});
			}
			if(obj.type == "Weekdays"){
				$('#weekends').append('<table></table>');
				$('#weekends tr:last').after("<tr><td>"+obj.from_date+"</td><td>"+obj.description+"</td><td>"+obj.type+"</td><td><input type='button' value='Edit' id='"+obj.id+"' class='dynamicEdit'></td></tr>");
				$('.dynamicEdit').bind("click",function(){
					
					$("#start_from").val(obj.from_date);
					$("#description").val(obj.description);
					$("#dropdown").val(obj.type);
					$("#save").hide();
					
					$("#update").css("visibility","visible").click(function(){
						var updateContent = {"payload":{"id":obj.id,
							"from_date":obj.from_date,
							"to_date":obj.to_date,
							"description":obj.description,
							"type":obj.type
							}
				};
					RequestManager.dynamicallyEdit(updateContent, function(data, success) {
						if(success){
							$("#save").show();
							$("#update").hide();
							alert("successfully updated");
 	
							}else{
  
								alert("failed to update");
							}		
  
						}.ctx(this));	
				
				});
			});
			}
			if(obj.type == "Optional"){
				$('#optional').append('<table></table>');
				$('#optional tr:last').after("<tr><td>"+obj.from_date+"</td><td>"+content.description+"</td><td>"+obj.type+"</td><td><input type='button' value='Edit' id='"+obj.id+"' class='dynamicEdit'></td></tr>");
				
				$('.dynamicEdit').bind("click",function(){
					$("#start_from").val(obj.from_date);
					$("#description").val(obj.description);
					$("#dropdown").val(obj.type);
					$("#save").hide();
					$("#update").css("visibility","visible").click(function(){
						var updateContent = {"payload":{"id":obj.id,
							"from_date":obj.from_date,
							"to_date":obj.to_date,
							"description":obj.description,
							"type":obj.type
							}
				};
					RequestManager.dynamicallyEdit(updateContent, function(data, success) {
						if(success){
							$("#save").show();
							$("#update").hide();
							alert("successfully updated");
 	
							}else{
  
								alert("failed to update");
							}		
  
						}.ctx(this));	
					
				});
			});
		}
	
			}
	
}

listHoliday.prototype.contentDisplay = function(content, status){
	if(status){
		
		//alert("asasas"+content.type+content.description);
		
			
			if(content.type == "Mandatory"){
				$('#mandatory').append('<table></table>');
				//var table = $('#mandatory').children(); 
				$('#mandatory tr:last').after("<tr><td>"+content.from_date+"</td><td>"+content.description+"</td><td>"+content.type+"</td><td><input type='button' value='Edit' id='"+content.id+"' class='dynamicEdit'></td></tr>");
				$('.dynamicEdit').bind("click",function(){
					
					/*alert("asasas");
					var par = $(this).parent().parent(); 
					var tdDate = par.children("td:nth-child(1)"); 
					var tdDescription = par.children("td:nth-child(2)");
					var tdType = par.children("td:nth-child(3)");

					tdDate.html("<input type='text' id='txtDate' value='"+tdDate.html()+"'/>");
					tdDescription.html("<input type='text' id='txtDescr' value='"+tdDescription.html()+"'/>");
					tdType.html("<input type='text' id='txtType' value='"+tdType.html()+"'/>");*/
				
						$("#start_from").val(content.from_date);
						$("#description").val(content.description);
						$("#dropdown").val(content.type);
						$("#save").hide();
						$("#update").css("visibility","visible").click(function(){
							var updateContent = {"payload":{"id":content.id,
															"from_date":content.from_date,
															"to_date":content.to_date,
															"description":content.description,
															"type":content.type
															}
												};
							RequestManager.dynamicallyEdit(updateContent, function(data, success) {
								if(success){
									 alert("successfully updated");
									 $("#save").show();
									 $("#update").hide();
								}else{
								  
								  alert("failed to update");
								 }		
								  
								}.ctx(this));	
							
							
						});
						
					
				});
			}
			if(content.type == "Weekdays"){
				$('#weekends').append('<table></table>');
				//var table = $('#weekends').children(); 
				$('#weekends tr:last').after("<tr><td>"+content.from_date+"</td><td>"+content.description+"</td><td>"+content.type+"</td><td><input type='button' value='Edit' id='"+content.id+"' class='dynamicEdit'></td></tr>");
				$('.dynamicEdit').bind("click",function(){
					
					$("#update").css("visibility","visible").click(function(){
						var updateContent = {"payload":{"id":content.id,
														"from_date":content.from_date,
														"to_date":content.to_date,
														"description":content.description,
														"type":content.type
														}
											};
						RequestManager.dynamicallyEdit(updateContent, function(data, success) {
							if(success){
								 $("#save").show();
								 $("#update").hide();
								 
								 alert("successfully updated");
							 	
							}else{
							  
							  alert("failed to update");
							 }		
							  
							}.ctx(this));	
						
						
					});
					$("#start_from").val(content.from_date);
					$("#description").val(content.description);
					$("#dropdown").val(content.type);
					$("#save").hide();
					
				});
			}
			if(content.type == "Optional"){
				$('#optional').append('<table></table>');
				//var table = $('#optional').children(); 
				$('#optional tr:last').after("<tr><td>"+content.from_date+"</td><td>"+content.description+"</td><td>"+content.type+"</td><td><input type='button' value='Edit' id='"+content.id+"' class='dynamicEdit'></td></tr>");
				$('.dynamicEdit').bind("click",function(){
					
					$("#update").css("visibility","visible").click(function(){
						var updateContent = {"payload":{"id":content.id,
														"from_date":content.from_date,
														"to_date":content.to_date,
														"description":content.description,
														"type":content.type
														}
											};
						RequestManager.dynamicallyEdit(updateContent, function(data, success) {
							if(success){
								 $("#save").show();
								 $("#update").hide();
								 alert("successfully updated");
							 	
							}else{
							  
							  alert("failed to update");
							 }		
							  
							}.ctx(this));	
						
						
					});
					$("#start_from").val(content.from_date);
					$("#description").val(content.description);
					$("#dropdown").val(content.type);
					$("#save").hide();
					
				});
			
			}
			
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
			
		
			$('#mandatory tr:last').innerHTML('<tr><td style="padding:5px;"> '+arr[i].from_date+' </td><td style="padding:5px;"> '+arr[i].to_date+' </td><td style="padding:5px;"> '+arr[i].description+' </td><td style="padding:5px;"> '+arr[i].type+' </td></tr>');
	
		}*/
	
 //var content = JSON.parse(data);

 

		
	//	var content = data[i];
	//	alert(content.from_date);
		
		/*$("#mandatory").append( "<tr>"+ "<td><input type='text' value=" + content.from_date + "/></td>" +
										"<td><input type='text' value=" + content.to_date + "/></td>"+
										"<td><input type='text' value=" + content.description + "/></td>"+ 
										"<td><input type='text' value=" + content.type + "/></td>"
										);*/

		
	}		
		
	
	
}


var listHoliday= new listHoliday();