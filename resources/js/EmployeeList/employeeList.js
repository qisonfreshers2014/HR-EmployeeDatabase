function employeeList(data) {
	
	
 Loader.loadHTML('.container','resources/js/EmployeeList/employeeList.html', true, function() {
	
    this.handleShow(data);
   }.ctx(this));
 
}



employeeList.prototype.handleShow = function(data) {
	
	$('#emp').parent().addClass('active');
	
/*	$('.viewindividual').click(function(event){
		
		console.log("syashsah");
		 var releaseId=event.target.id;     
		  App.loadViewEmployee(releaseId);
		
		
	});
	*/
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
 
 $(".emplistheadtd").keyup(function (event) {
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
		 						 tabledata += '<tr class="theader"><th>EID</th><th>Employee Name</th><th> Birth Day </th><th> Actual Birth Day </th><th> Joining Date </th><th>Contact No</th><th>Emergency Contact No</th><th>Email ID</th><th>Current Designation</th><th>Years of Experience at the time of joining</th><th>Experience at Qison</th><th>Current Years of Experience</th><th>PAN</th>';
		 						$('#employeelist').append('<table><tbody id="emplisttable"></tbody></table>');
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

var monthsArray=new Array(12);
monthsArray[0]="Jan";
monthsArray[1]="Feb";
monthsArray[2]="March";
monthsArray[3]="April";
monthsArray[4]="May";
monthsArray[5]="June";
monthsArray[6]="July";
monthsArray[7]="Aug";
monthsArray[8]="Sepr";
monthsArray[9]="Oct";
monthsArray[10]="Nov";
monthsArray[11]="Dec";
 

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
 tabledata += '<tr class="theader"><th>EID</th><th>Employee Name</th><th> Birth Day </th><th> Actual Birth Day </th><th> Joining Date </th><th>Contact No</th><th>Emergency Contact No</th><th>Email ID</th><th>Current Designation</th><th>Experience at joining</th><th>Experience at Qison</th><th>Current Years of Experience</th><th>PAN</th>';
 
 
	
 for (var i = 0; i < content.length; i++) {
  var obj = content[i];
  
 
  var dobformat = new Date(obj.dateOfBirth);
  var byear = dobformat.getFullYear();
  var bmonth = monthsArray[dobformat.getMonth()];
  var bdate = dobformat.getDate();
  var actualdobformat = new Date(obj.actualdateOfBirth);
  var actualdobyear = actualdobformat.getFullYear();
  var actualdobmonth = monthsArray[actualdobformat.getMonth()];
  var actualdobdate = actualdobformat.getDate();
  var dojformat = new Date(obj.dateOfJoining);
  var jyear = dojformat.getFullYear();
  var jmonth = monthsArray[dojformat.getMonth()];
  var jdate = dojformat.getDate();
  
  
  // Calculating Current years of experience
  var today=new Date();
  var diff=Math.floor(today.getTime() - dojformat.getTime());
  var day = 1000 * 60 * 60 * 24;
  var days = Math.floor(diff/day);
  var months = Math.floor(days/31);
  var expAtQisonInYears=Math.floor(months/12);
  var expAtQisonInMonths=months%12;
  var exp=obj.yearsofexperience;
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
	 
	  totalYearsOfExp=years+obj.yearsofexperience;
	
  }
  
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
  tabledata += '<td id="xyz"><a href="#view" class="viewindividual" id="'+obj.employeeId+'">' + obj.employeeId+ '</a></td>';
  tabledata += '<td>' + obj.employeeName + '</td>';
  tabledata += '<td>' +bdate+"-"+bmonth+"-"+byear+'</td>';
  tabledata += '<td>' +actualdobdate+"-"+actualdobmonth+"-"+actualdobyear+'</td>';
  tabledata += '<td>' +jdate+"-"+jmonth+"-"+jyear+'</td>';
  tabledata += '<td>' +obj.contactNo + '</td>';
  tabledata += '<td>' +obj.emergencycontactnumber +'</td>';
  tabledata += '<td>' +obj.email +  '</td>';
  tabledata += '<td>' +desName +'</td>';
  tabledata += '<td>' +exp+  '</td>';
  tabledata += '<td>' +expAtQisonInYears+"."+expAtQisonInMonths+'</td>';
  tabledata += '<td>' +totalYearsOfExp+"."+totalYearsOfExpMnths+'</td>';
  tabledata += '<td>' +obj.pan +'</td>';
  tabledata += '</tr>';
 
  $('#employeelist').html(tabledata);

}
 
 $('#emp').unbind();
}

employeeList.prototype.searchOperation = function(data,desdata){
	
	 $("#listemp").on("click",".viewindividual",function(event){
		  var releaseId=event.target.id;     
		  App.loadViewEmployee(releaseId);
		 }.ctx(this));
	 
	
 
 var content = '';
 content += '<tr class="theader"><th>EID</th><th>Employee Name</th><th> Birth Day </th><th> Actual Birth Day </th><th> Joining Date </th><th>Contact No</th><th>Emergency Contact No</th><th>Email ID</th><th>Current Designation</th><th>Experience at joining</th><th>Experience at Qison</th><th>Current Years of Experience</th><th>PAN</th>';
 for (var i = 0; i < data.length; i++) {
  var obj = data[i];
  
  var dobformat = new Date(obj.dateOfBirth);
  var byear = dobformat.getFullYear();
  var bmonth = monthsArray[dobformat.getMonth()];
  var bdate = dobformat.getDate();
  
  var actualdobformat = new Date(obj.actualdateOfBirth);
  var actualdobyear = actualdobformat.getFullYear();
  var actualdobmonth = monthsArray[actualdobformat.getMonth()];
  var actualdobdate = actualdobformat.getDate();
  
  var dojformat = new Date(obj.dateOfJoining);
  var jyear = dojformat.getFullYear();
  var jmonth = monthsArray[dojformat.getMonth()];
  var jdate = dojformat.getDate();
 
  // Calculating Current years of experience
  var today=new Date();
  var diff=Math.floor(today.getTime() - dojformat.getTime());
  var day = 1000 * 60 * 60 * 24;
  var days = Math.floor(diff/day);
  var months = Math.floor(days/31);
  var expAtQisonInYears=Math.floor(months/12);
  var expAtQisonInMonths=months%12;
  var exp=obj.yearsofexperience;
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
		 
		  totalYearsOfExp=years+obj.yearsofexperience;
		
	  }
	  
  
 
  
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
              content += '<td id="xyz"><a href="#view" class="viewindividual" id="'+obj.employeeId+'">' + obj.employeeId+ '</a></td>';
              content += '<td>' + obj.employeeName + '</td>';
              content += '<td>' +bdate+"-"+bmonth+"-"+byear+'</td>';
              content += '<td>' +actualdobdate+"-"+actualdobmonth+"-"+actualdobyear+'</td>';
              content += '<td>' +jdate+"-"+jmonth+"-"+jyear+'</td>';
              content += '<td>' +obj.contactNo + '</td>';
              content += '<td>' +obj.emergencycontactnumber +'</td>';
              content += '<td>' +obj.email +  '</td>';
              content += '<td>' +desName +'</td>';
              content += '<td>' +exp+  '</td>';
              content += '<td>' +expAtQisonInYears+"."+expAtQisonInMonths+'</td>';
              content += '<td>' +totalYearsOfExp+"."+totalYearsOfExpMnths+'</td>';
              content += '<td>' +obj.pan +'</td>';
              content += '</tr>';
              }
 
 $('#employeelist').html(content);
 $('#backtoemployee').css("visibility","visible");
 $('.selector').css("visibility","hidden");
  
}


