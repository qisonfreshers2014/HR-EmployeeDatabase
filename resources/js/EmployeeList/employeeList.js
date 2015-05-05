function employeeList() {
	Loader.loadHTML('.container','resources/js/EmployeeList/EmployeeList.html', true, function() {
				this.handleShow();
			}.ctx(this));
	
}

employeeList.prototype.handleShow = function() {

	$('.container').show();
	var contentinput = {
		"payload" : {}
	};
	RequestManager.getEmployee(contentinput, function(data, success) {
	
		if (success) {
			var content = data;
			var status = success;
			this.tableDisplay(content, status);
			

		} else {

			alert("No list found");
		}

	}.ctx(this));

	$('#search').click(function() {
		
		if($("#searchelement").val() == ""){
			
			alert("Please provide data to search");
			
		}else{
		
		var contentinput = {
								"payload" : {"searchKey":$('#searchelement').val()}
							};
		
		RequestManager.getSearchEmp(contentinput, function(data, success) {
			if (success) {
				if(data.length == 0){
					
					alert("No record found");
					
				
				}else{
					
					this.searchOperation(data);
					
				}
				
				
			}
				
				
			
		}.ctx(this));
		
		}
		
	}.ctx(this));

}

employeeList.prototype.tableDisplay = function(content, status) {
	

	
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

		$('#employeelist').append('<table class="paginated"><tbody></tbody></table>');
		$('#employeelist tr:last').after(
				"<tr class='info'><td><a href='viewemp1'>" + obj.employeeId
						+ "</a></td>" + "<td>" + obj.employeeName + "</td>"
						+ "<td>"+byear+"-"+bmonth+"-"+bdate+"</td>" + "<td>"
						+jyear+"-"+jmonth+"-"+jdate+ "</td>" + "<td>" + obj.contactNo
						+ "</td>" + "<td>" + obj.emergencycontactnumber
						+ "</td>" + "<td>" + obj.email + "</td>" + "<td>"
						+ obj.currentDesignation + "</td>" + "<td>"
						+ obj.yearsofexperience + "</td>" + "<td>" + obj.pan
						+ "</td></tr>");
		

	}
	
	$("#listemp").on("click",".del",function(event){
		var releaseId=event.target.id;	    
		 $(releaseId).hide();
	}.ctx(this));
	


}


employeeList.prototype.searchOperation = function(data){
	
	var content = '';
	content += '<tr><th>EID</th><th>EmployeeName</th><th> Birth Day </th><th> Joining Date </th><th>Contact No</th><th>Emergency Contact No</th><th>Email ID</th><th>Current Designation</th><th>Current Years of Experience</th><th>PAN</th>';
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
	
			        content += '<tr>';
		            content += '<td><a href="viewemp1">' + obj.employeeId+ '</a></td>';
		            content += '<td>' + obj.employeeName + '</td>';
		            content += '<td>' +byear+"-"+bmonth+"-"+bdate+'</td>';
		            content += '<td>' +jyear+"-"+jmonth+"-"+jdate+'</td>';
		            content += '<td>' +obj.contactNo + '</td>';
		            content += '<td>' +obj.emergencycontactnumber +'</td>';
		            content += '<td>' +obj.email +  '</td>';
		            content += '<td>' +obj.currentDesignation +'</td>';
		            content += '<td>' +obj.yearsofexperience +'</td>';
		            content += '<td>' +obj.pan +'</td>';
		            content += '</tr>';
		            }
	
	$('#employeelist').html(content);
	alert("Search found");
	
	
}



var employeeList = new employeeList();