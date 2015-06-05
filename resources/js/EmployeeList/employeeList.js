function employeeList(data) {
	
	
 Loader.loadHTML('.container','resources/js/EmployeeList/employeeList.html', true, function() {
	
    this.handleShow(data);
   }.ctx(this));
 
}



employeeList.prototype.handleShow = function(data) {
	var self=this;
	
	var pagno=1;
	
	 $('.selector').pagination(
		  		{
		      items:data.count,
		      itemsOnPage:10,
		      cssStyle: 'light-theme',
		      	
		  	  onPageClick: function(pageNumber) { 
		  		  
		  		self.paginationFunc(pageNumber);
		           
		           
		       }
		  });


	
	this.paginationFunc(pagno);

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
	 
	 routie("employee");
	 
 }.ctx(this));
 
 
 $('#search').click(function() {
	
  
	 if($("#searchelement").val() == ""){
   
	  alert("Please provide data to search");
	
	 }else if(!($("#searchelement").val().match('^[a-zA-Z0-9-@. ]*$'))){
		  
		  alert("Your search string contains illegal characters");
	 
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
		 						 $('.selector').css("visibility","hidden");
    
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
 

 employeeList.prototype.paginationFunc=function(pagno){
	
 

 var contentinput = {"payload":{"pageNo":pagno,"pageSize":10}};
 RequestManager.getPaginatedEmployees(contentinput, function(data, success) {
	 
	
 
  if (success) {
	 
	
   var content = data.employees;
  var pgno=data.pageNo;
  var numberOfEmployees=data.count;
 
  var status = success;
  
  $(function(){
  
  var perPage = 10;
	
 
  var checkFragment = function() {
      // if there's no hash, make sure we go to page 1
      var hash = window.location.hash;
      // we'll use regex to check the hash string as follows:
      // ^            strictly from the beginning of the string (i.e. succeed "#page-3" but fail "hi!#page-3")
      // #page-       exactly match the text "#page-"
      // (            start a matching group (so we can access what's in these parentheses on their own)
      //      \d      any digit ([0-9])
      //      +       one or more of the previous literal (one or more digits)
      // )            end the matching group
      // $            we should now be at the end of the string - if not, then don't match (i.e. fail "#page-3hi!")
      hash = hash.match(/^#page-(\d+)$/);
      if(hash)
          // the selectPage function is one of many described in the documentation
          // we've captured the page number in a regex group: (\d+)
          $("#pagination").pagination("selectPage", parseInt(hash[1]));
  };
  // we'll call this function whenever the back or forward buttons are pressed
  // thanks to mike o'connor for highlighting the need for this
  $(window).bind("popstate", checkFragment);
  
  // and we'll also call it to check right now!
  checkFragment();
  });
 

  var desInput={"payload":{}};
  
  RequestManager.getDesignationName(desInput,function(desdata, success) {
	    if (success) {
	    	
	    	this.tableDisplay(content, status,desdata);
	    	
	    	
	    }	 
	   }.ctx(this));
  

   } else {

   alert("No list found");
  }
  
 

 }.ctx(this));
 
 }

employeeList.prototype.tableDisplay = function(content,status,desdata) {
	
	
	
 $("#listemp").on("click",".viewindividual",function(event){
  var releaseId=event.target.id;     
  App.loadViewEmployee(releaseId);
 }.ctx(this));
 
 
$('#employeelist').empty();
 var tabledata = '';
 tabledata += '<tr class="theader"><th>EID</th><th>EmployeeName</th><th> Birth Day </th><th> Joining Date </th><th>Contact No</th><th>Emergency Contact No</th><th>Email ID</th><th>Current Designation</th><th>Current Years of Experience</th><th>PAN</th>';
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

  tabledata += '<tr>';
  tabledata += '<td><a href="#view" class="viewindividual" id="'+obj.employeeId+'">' + obj.employeeId+ '</a></td>';
  tabledata += '<td>' + obj.employeeName + '</td>';
  tabledata += '<td>' +byear+"-"+bmonth+"-"+bdate+'</td>';
  tabledata += '<td>' +jyear+"-"+jmonth+"-"+jdate+'</td>';
  tabledata += '<td>' +obj.contactNo + '</td>';
  tabledata += '<td>' +obj.emergencycontactnumber +'</td>';
  tabledata += '<td>' +obj.email +  '</td>';
  tabledata += '<td>' +desName +'</td>';
  tabledata += '<td>' +obj.yearsofexperience +'</td>';
  tabledata += '<td>' +obj.pan +'</td>';
  tabledata += '</tr>';
 
  $('#employeelist').html(tabledata);

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
              content += '<td><a href="#view" class="viewindividual" id="'+obj.employeeId+'">' + obj.employeeId+ '</a></td>';
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
 $('.selector').css("visibility","hidden");
  
}
