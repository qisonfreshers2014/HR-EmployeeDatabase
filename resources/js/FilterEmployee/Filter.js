function FilterEmp(empid) {
	Loader.loadHTML('.container', 'resources/js/FilterEmployee/FilterEmployee.html', true, function(){
		this.handleShow(empid);
	}.ctx(this));
}

FilterEmp.prototype.handleShow = function(empid) {
	$('.container').show();

	//Appending designation types to the drop down
	var emptyinput={};
	 RequestManager.getDesignationTypes(emptyinput, function(data, success){
		               
		 if(success){
			 
			       designationNames=data;
			          arraylength=data.length;
			           lastId=data[arraylength-1].id;
			           newvalue=lastId+1;
			 for(i=0;i<data.length;i++){
			  var object=data[i];
			 $('#filterdesignation').append($("<option value="+object.id+"> "+object.name+"</option>"));			 
			 }
			 
		 }else if(data.code==131){
                 alert("Sorry, you are not a authorized user for this action");
               App.loadEmployeePage(App.userName,App.hr,App.isDeleted);
                  routie("home");
               }else{
                    alert("Failed to get Designation types");
             }
		 
	 }.ctx(this));
	
	$( "#dojFrom" ).datepicker({dateFormat:'yy-mm-dd',showButtonPanel:
	 true,changeMonth:true,changeYear:true,showAnim:'drop',minDate:new Date(1993,12,31),
	 maxDate:new Date()});
	$( "#dojTo" ).datepicker({dateFormat:'yy-mm-dd',showButtonPanel:
		 true,changeMonth:true,changeYear:true,showAnim:'drop',minDate:new Date(1993,12,31),
		 maxDate:new Date()});
		
	$('#Filter').click(function(){
		
		
		
		var pagno=1;
		
		
	
			this.FilterEmployee(pagno,empid,designationNames);
		
		
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



FilterEmp.prototype.FilterEmployee = function(pagno,empid,designationNames){
	
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
		 

	var payload ={"pageNo":pagno,"pageSize":10};
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
	
	    RequestManager.getPaginatedFilterEmployees({"payload" : payload}, function(data, success) {
	    if(success){
	    	
	    	
	    	 var self=this;
	    	
	    	
	    	 $('.selector').pagination(
				  		{
				      items:data.count,
				      itemsOnPage:10,
				      currentPage:data.pageNo,
				      cssStyle: 'light-theme',
				      	
				  	  onPageClick: function(pageNumber) { 
				  		  
				  		self.FilterEmployee(pageNumber,empid,designationNames);
				           
				           
				       }
				  });
	    	var employees=data.employees;
	    	
	    if(employees.length != 0){
		
	    	$('.heading1').css("visibility","visible");
	    	  $('#filterPagination').css("visibility","visible");
	  	$('#displayData').html('<tr class="displaytr"><th class="displayth">Employee Id</th><th class="displayth">Employee Name</th><th class="displayth">Gender</th><th class="displayth">DOB</th><th class="displayth">DOJ</th><th class="displayth">Email</th><th class="displayth">Fathers Name</th><th class="displayth">Designation</th><th class="displayth">Highest Qualification</th><th class="displayth">Contact No</th><th class="displayth">Years Of Experience</th><th class="displayth">LastWorking day</th><th class="displayth">View Details</th></tr>');
	  	
	    }
	    else{
	          alert("No record found");
	          $('.heading1').css("visibility","hidden");
	          $('#filterPagination').css("visibility","hidden");
	          $('#displayData').html('<tr class="displaytr"><th class="displayth">Employee Id</th><th class="displayth">Employee Name</th><th class="displayth">Gender</th><th class="displayth">DOB</th><th class="displayth">DOJ</th><th class="displayth">Email</th><th class="displayth">Fathers Name</th><th class="displayth">Designation</th><th class="displayth">Highest Qualification</th><th class="displayth">Contact No</th><th class="displayth">Years Of Experience</th><th class="displayth">LastWorking day</th><th class="displayth">View Details</th></tr>');
	    	   
			    $('#displayData tbody').empty();
	   
	    }
	    
	    
	   
	   

				var monthsArray = new Array(12);
				monthsArray[0] = "Jan";
				monthsArray[1] = "Feb";
				monthsArray[2] = "March";
				monthsArray[3] = "April";
				monthsArray[4] = "May";
				monthsArray[5] = "June";
				monthsArray[6] = "July";
				monthsArray[7] = "Aug";
				monthsArray[8] = "Sep";
				monthsArray[9] = "Oct";
				monthsArray[10] = "Nov";
				monthsArray[11] = "Dec";
		
	      
               var content = '';
		      content += '<tr class="displaytr">';
			  content += '<th class="displayth">Employee Id</th>';
			  content += '<th class="displayth">Employee Name</th>';
			  content += '<th class="displayth">Gender</th>';
			  content += '<th class="displayth">DOB</th>';
			  content += '<th class="displayth">DOJ</th>';
			  content += '<th class="displayth">Email</th>';
			  content += '<th class="displayth">Fathers Name</th>';
			  content += '<th class="displayth">Designation</th>';
			  content += '<th class="displayth">Highest Qualification</th>';
			  content += '<th class="displayth">Contact No</th>';
			  content += '<th class="displayth">Years Of Experience</th>';

			  if ($('#emptype').val() == "Inactive") {
			  content += '<th class="displayth">LastWorking day</th>';
				}
				content += '<th class="displayth">View Details</th>';
				content += '</tr>';
for (var i = 0; i < employees.length; i++) {
			     var item = employees[i];
			     
			     var desgId=item.currentDesignation;
			        
			        for(desg=0;desg<designationNames.length;desg++){
			        	desginationObj=designationNames[desg];
			        	if(desgId==desginationObj.id){
			        		  desName = desginationObj.name;
			        		    break;
			        		
			        	}
			        	
			        }
			     var dojformat = new Date(item.dateOfJoining);
			        // Calculating Current years of experience
				    var today=new Date();
				    var diff=Math.floor(today.getTime() - dojformat.getTime());
				    var day = 1000 * 60 * 60 * 24;
				    var days = Math.floor(diff/day);
				    var months = Math.floor(days/31);
				    var expAtQisonInYears=Math.floor(months/12);
				    var expAtQisonInMonths=months%12;
				    var exp=item.yearsofexperience;
				    var isDecimal=exp%1;
				    var parts=exp.toString().split('.');
				    
				    var afterdecimal=parseInt(parts[1]);
				    var beforedecimal=parseInt(parts[0]);
				    
				  if(isDecimal!=0){
				  	 months=months+afterdecimal;
				  	 
				  	  years = Math.floor(months/12);
				  	
				  	  totalYearsOfExpMnths=months%12;
				  	 
				      totalYearsOfExp=years+beforedecimal;
				     
				  	 
				    }
				    else{ 
				  	  years = Math.floor(months/12);
				  	
				  	  totalYearsOfExpMnths=months%12;
				  	 
				  	  totalYearsOfExp=years+item.yearsofexperience;
				  	
				    }
							       
			     content += '<tr  class="displaytr">';
	             content += '<td class="displaytd">'  + item.employeeId+ '</td>';
	             content += '<td class="displaytd">' + item.employeeName + '</td>';
	             content += '<td class="displaytd">' +item.gender+'</td>';
content += '<td class="displaytd">' +new Date(item.dateOfBirth).getDate()+"-"+monthsArray[new Date(item.dateOfBirth).getMonth()]+"-"+new Date(item.dateOfBirth).getFullYear()+'</td>';
content += '<td class="displaytd">' +new Date(item.dateOfJoining).getDate()+"-"+monthsArray[new Date(item.dateOfJoining).getMonth()] +"-"+new Date(item.dateOfJoining).getFullYear()+'</td>';
	              content += '<td class="displaytd">' +item.email +'</td>';
	              content += '<td class="displaytd">' +item.fathersName +  '</td>';
	              content += '<td class="displaytd">' +desName +'</td>';
	              content += '<td class="displaytd">' +item.highestQualification +'</td>';
	              content += '<td class="displaytd">' +item.contactNo +'</td>';
	              content += '<td class="displaytd">' +totalYearsOfExp+"."+totalYearsOfExpMnths+'</td>';
	              
	              console.log($('#emptype').val());
	              if($('#emptype').val()=="Inactive"){
	              content += '<td class="displaytd">' +new Date(item.lastWorkingDay).getDate()+"-"+monthsArray[new Date(item.lastWorkingDay).getMonth()] +"-"+new Date(item.lastWorkingDay).getFullYear()+'</td>';
	              }
	              content += '<td class="displaytd">' +"<input type='button' value='View' id='"+item.employeeId+"' class='dynamicView  btn-success'> "+'</td>';
	              content += '</tr>';
	              $('#displayData').html(content);
	             
}



$(function(){
	  
	  var perPage = 10;
		
	 
	  var checkFragment = function() {
	    
	      var hash = window.location.hash;
	 
	      hash = hash.match(/^#page-(\d+)$/);
	      if(hash)
	        
	          $("#pagination").pagination("selectPage", parseInt(hash[1]));
	  };
	 
	  $(window).bind("popstate", checkFragment);
	  
	  checkFragment();
	  });
	             $('.dynamicView').bind("click", function(event){
	          
	            	     var empid = $(event.target).attr('id');
	            		 App.loadViewEmployee(empid);
	            			
	     			  
	     			 }.ctx(this));
	     			    
				   }else if(data.code==131){
                                     alert("Sorry, you are not a authorized user for this action");
                                         App.loadEmployeePage(App.userName,App.hr,App.isDeleted);
                                          routie("home");
                                } else{
					  
					    alert("no record found");
					    
					    }
					}.ctx(this));

}
 
