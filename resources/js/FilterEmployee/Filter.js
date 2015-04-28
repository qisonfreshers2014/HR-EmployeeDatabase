function FilterEmp() {
	Loader.loadHTML('.leftContainer', 'resources/js/FilterEmployee/FilterEmployee.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

FilterEmp.prototype.handleShow = function() {
	$('.container').show();
	$('#Filter').click(function(){
		/*event.preventDefault();*/
		this.FilterEmployee();
		//this.Filterdata();
	}.ctx(this));
	
}

FilterEmp.prototype.validateFilterEmp=function(){

	var valid = true;
	 //validation for gender
	var result = document.getElementById("gender").value;
	  if (result == "0") 
	  {
	   document.getElementById("gerror").innerHTML = "Please Select Gender";
	   document.getElementById("gerror").style.visibility = 'visible';
	   valid = false;
	  }
	  else
	   document.getElementById("gerror").innerHTML = " ";
	
	  
	
	//For designation validations
	  var result = document.getElementById("designation").value;
	  if (result == "0") 
	  {
	   document.getElementById("designerror").innerHTML = "Please Select Designation";
	   document.getElementById("designerror").style.visibility = 'visible';
	   valid = false;
	  }
	  else
	   document.getElementById("designerror").innerHTML = " ";
	 
	
	 	
	//for date of joining validations
	  var x=document.getElementById("date").value;
	  var error=document.getElementById("derror")
	  var doj=new Date(x);
	  var today= new Date();
	  var dojday=doj.getDate();
	  var dojmonth=doj.getMonth()+1;
	  var dojyear=doj.getFullYear();
	  var todayday=today.getDate();
	  var todaymonth=today.getMonth()+1;
	  var todayyear=today.getFullYear();
	  var day;
	  if(doj>today)
	  {
	  	error.innerHTML="Invalid Date";
	  	error.style.visibility = "visible"; 
	  	valid = false;
	   
	  }
	  else{
	  	error.style.visibility="hidden";
	  }
	    

	  if(dojday>todayday)
	  {
	  todayday=todayday+30;
	   day=todayday-dojday;
	  todaymonth=todaymonth-1;
	  }
	  else 
	  {
	  day=todayday-dojday;
	  }
	  var month;

	  if(dojmonth>todaymonth)
	  {

	  todaymonth=todaymonth+12;
	  month=todaymonth-dojmonth;
	  todayyear=todayyear-1;
	  }
	  else
	   {
	   month=todaymonth-dojmonth;
	   }
	  var year =todayyear-dojyear;
	   
	  
	  //validations for date of join
	 	var error=document.getElementById("yerror");
		var year1= document.getElementById("year1").value;
		var num =/^[0-3]?[0-9]\/[01]?[0-9]\/[12][90][0-9][0-9]$/;
		if(year1 == "" ) 
		 {
			error.style.visibility="visible";
			valid = false;
		 } 
		  else if(isNaN(year1))
		 {
			  error.innerHTML="This year format is not recognized.Enter only numbers.";
			  error.style.visibility="visible";
			  valid = false;
		 }
		 else  
			 error.style.visibility="hidden"; 
		 
 //validation for date of joining
		/*var error=document.getElementById("yerror");
		var year= document.getElementById("year2").value;
		var num =/^[0-3]?[0-9]\/[01]?[0-9]\/[12][90][0-9][0-9]$/;
		if(year == "" ) 
		 {
			error.style.visibility="visible";
			valid = false;
		 } 
		  else if(isNaN(year))
		 {
			  error.innerHTML="This year format is not recognized.Enter only numbers.";
			  error.style.visibility="visible";
			  valid = false;
		 }
		 else  
			 error.style.visibility="hidden"; 
	  */
	
	//For highest_Qualification validations
		/*var nerr = $("#herror");
		var qualification = $("#qualification1").val();
		var qual=/^[a-zA-Z.""]+$/;
		if (qualification == "") { 
			$(nerr).text("required field");
			$(nerr).css("color","red");
			valid false;
			} 
			else if (!(qualification.match(qual) || qualification == isNaN)) {
			$(nerr).text("please check the qualification");
			$(nerr).css("color","red");
			valid false;
			} 
			else 
			$(nerr).text("");
			 */
			 
			 

	var herr=document.getElementById("herror");
	var qualification = document.getElementById("qualification1").value;
	var char =/^[a-zA-Z.""]+$/;
	if(qualification === "") 
	 {
		herr.style.visibility="visible";
		valid = false;
	 }   
	 else if(!(qualification.match(char) || qualification == isNaN))
	 {
		 herr.innerHTML= "invalid qualification / It should be characters only";
		 herr.style.visibility="visible";
		 valid = false;
	  
	 }
	 else  
		 herr.style.visibility="hidden"; 
		return valid;
}

FilterEmp.prototype.FilterEmployee = function(){
	
	if(this.validateFilterEmp()){
	  var input={"payload":{"gender":$('#gender').val(),"currentDesignation":$('#designation').val(),
			"DOJ":$('#date').val(),"yearsofexperience":$('#year1').val(),
			 "highestQualification":$('#qualification1').val(),
			}};
	  
	  
	  RequestManager.getFilterEmployee(input, function(data, success) {
		 if(success){
			 alert("success");
			   var i;
				  
				        for(i = 0; i < data.length; i++) {
				         var item = data[i];
				     	        
				 
	$('#displayData').append('<table style="width:80%"></table>');
	 $('#displayData').after('<tr><td>'+item.employeeId+'</td><td>'+item.employeeName+'</td><td>'+item.gender+'</td><td>'
	+item.DOB+'</td><td>'+item.DOJ+'</td><td>'+item.email+'</td><td>'+item.fatherName+'</td><td>'
	+item.designation+'</td><td>'+item.highestQualification+'</td><td>'+item.skypeId+'</td><td>'
	+item.highestQualification+'</td></tr>');
				       
				  }
					 }else{
					  alert("failed to retrieved");
					 }
					}.ctx(this));
				}
			};
		    			  
 
 
 

	