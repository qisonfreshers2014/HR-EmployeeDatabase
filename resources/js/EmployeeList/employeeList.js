function employeeList() {
 Loader.loadHTML('.container','resources/js/EmployeeList/employeeList.html', true, function() {
    this.handleShow();
   }.ctx(this));
 
}

employeeList.prototype.handleShow = function() {

 $('.container').show();
 $("#searchdiv").keyup(function (event) {
	  if (event.keyCode == 13){
	   $("#search").trigger('click');
	   } 
	    }.ctx(this));
 
 $('#filter').click(function(){
 
	 routie("filteremployee");
 
 }.ctx(this));
 
 $('#empadd').click(function(){
 
 routie("addemployee");
  
 }.ctx(this));

 
 $('#backtoemployee').click(function(){
	 
	 App.listEmployee();
	 
 }.ctx(this));
 
 
 var contentinput = {
  "payload" : {}
 };
 RequestManager.getEmployee(contentinput, function(data, success) {
 
  if (success) {
   var content = data;
   var status = success;
   
   RequestManager.getDesignationName(contentinput,function(desdata, success) {
	    if (success) {
	    	
	    	this.tableDisplay(content, status,desdata);
	    	
	    }	 
	   }.ctx(this));
   
   } else {

   alert("No list found");
  }

 }.ctx(this));
 


 $('#search').click(function() {
  
	 if($("#searchelement").val() == ""){
   
	  alert("Please provide data to search");
	  this.callWholeList();
	  	 
	 }else if(!($("#searchelement").val().match('^[a-zA-Z0-9-@. ]*$'))){
		  
		  alert("Your search string contains illegal characters");
		  this.callWholeList();
	 
	 }else{
  
		 	var contentinput = {"payload" : {"searchKey":$('#searchelement').val()}   };
  
		 		RequestManager.getSearchEmp(contentinput, function(data, success) {
		 		if (success) {
		 				if(data.length == 0){
     
		 						alert("No record found");
		 						var content = '';
		 						content += '<tr class="theader"><th>EID</th><th>EmployeeName</th><th> Birth Day </th><th> Joining Date </th><th>Contact No</th><th>Emergency Contact No</th><th>Email ID</th><th>Current Designation</th><th>Current Years of Experience</th><th>PAN</th>';
		 						$('#employeelist').append('<table><tbody></tbody></table>');
		 						$('#employeelist').html(content);
		 						$('#backtoemployee').css("visibility","visible");
    
		 				}else{
		 						
		 					RequestManager.getDesignationName(contentinput,function(desdata, success) {
		 					    if (success) {
		 					    	this.searchOperation(data,desdata);
		 					    			 					    	
		 					    }	 
		 					   }.ctx(this));
		 					
		 				}
    
    
		 		}
    
    
   
		 		}.ctx(this));
  
	 	}
  
 }.ctx(this));

}

employeeList.prototype.tableDisplay = function(content,status,desdata) {
 
 $("#listemp").on("click",".viewindividual",function(event){
  var releaseId=event.target.id;     
  App.loadViewEmployee(releaseId);
 }.ctx(this));
 
 for (var i = 0; i < content.length; i++) {
  var obj = content[i];
  
  var dobformat = new Date(obj.dateOfBirth);
  var byear = dobformat.getFullYear();
  var bmonth = dobformat.getMonth()+1;
  var bdate = dobformat.getDate();
  
  var dojformat = new Date(obj.dateOfJoining);
  var jyear = dojformat.getFullYear();
  var jmonth = dojformat.getMonth()+1;
  var jdate = dojformat.getDate();

  var desName;
  var desId = obj.currentDesignation;
  
  for (var j = 0; j < desdata.length; j++) {
	  var desTypeObj = desdata[j];
	   if (desId == desTypeObj.id) {
	    desName = desTypeObj.name;
	    break;
	   }
}
  
  $('#employeelist').append('<table><tbody></tbody></table>');
  $('#employeelist tr:last').after(
    "<tr class='info'><td><a href='#employee' class='viewindividual' id='"+obj.employeeId+"'>" + obj.employeeId
      + "</a></td>" + "<td>" + obj.employeeName + "</td>"
      + "<td>"+byear+"-"+bmonth+"-"+bdate+"</td>" + "<td>"
      +jyear+"-"+jmonth+"-"+jdate+ "</td>" + "<td>" + obj.contactNo
      + "</td>" + "<td>" + obj.emergencycontactnumber
      + "</td>" + "<td>" + obj.email + "</td>" + "<td>"
      + desName + "</td>" + "<td>"
      + obj.yearsofexperience + "</td>" + "<td>" + obj.pan
      + "</td></tr>");
  
  

 }
 

}


employeeList.prototype.searchOperation = function(data,desdata){
	
	 $("#listemp").on("click",".viewindividual",function(event){
		  var releaseId=event.target.id;     
		  App.loadViewEmployee(releaseId);
		 }.ctx(this));
	
 
 var content = '';
 content += '<tr class="theader"><th>EID</th><th>EmployeeName</th><th> Birth Day </th><th> Joining Date </th><th>Contact No</th><th>Emergency Contact No</th><th>Email ID</th><th>Current Designation</th><th>Current Years of Experience</th><th>PAN</th>';
 for (var i = 0; i < data.length; i++) {
  var obj = data[i];
  
  var dobformat = new Date(obj.dateOfBirth);
  var byear = dobformat.getFullYear();
  var bmonth = dobformat.getMonth()+1;
  var bdate = dobformat.getDate();
  
  var dojformat = new Date(obj.dateOfJoining);
  var jyear = dojformat.getFullYear();
  var jmonth = dojformat.getMonth()+1;
  var jdate = dojformat.getDate();
 
  
  var desName;
  var desId = obj.currentDesignation;
  
  for (var j = 0; j < desdata.length; j++) {
	  var desTypeObj = desdata[j];
	   if (desId == desTypeObj.id) {
	    desName = desTypeObj.name;
	    break;
	   }
  }
  
  
           content += '<tr>';
              content += '<td><a href="#employee" class="viewindividual" id="'+obj.employeeId+'">' + obj.employeeId+ '</a></td>';
              content += '<td>' + obj.employeeName + '</td>';
              content += '<td>' +byear+"-"+bmonth+"-"+bdate+'</td>';
              content += '<td>' +jyear+"-"+jmonth+"-"+jdate+'</td>';
              content += '<td>' +obj.contactNo + '</td>';
              content += '<td>' +obj.emergencycontactnumber +'</td>';
              content += '<td>' +obj.email +  '</td>';
              content += '<td>' +desName +'</td>';
              content += '<td>' +obj.yearsofexperience +'</td>';
              content += '<td>' +obj.pan +'</td>';
              content += '</tr>';
              }
 
 $('#employeelist').html(content);
 $('#backtoemployee').css("visibility","visible");
  
}

employeeList.prototype.callWholeList = function(){
	
	 var contentinput = {
			  "payload" : {}
			 };
			 RequestManager.getEmployee(contentinput, function(data, success) {
			 
			  if (success) {
			   var content = data;
			   var status = success;
			   
			   RequestManager.getDesignationName(contentinput,function(desdata, success) {
				    if (success) {
				    	
				    	this.tableDisplay(content,status,desdata);
				    	
				    }	 
				   }.ctx(this));
			   
			   } else {

			   alert("No list found");
			  }

			 }.ctx(this));
			 
	
	
}

var employeeList = new employeeList();