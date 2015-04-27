function employeeHolidayList() {
	Loader.loadHTML('.leftContainer', 'resources/js/EmployeeHolidayList/EmployeeHolidayList.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

employeeHolidayList.prototype.handleShow = function() {
	
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

}

employeeHolidayList.prototype.tableDisplay = function(content, status){
	for(var i = 0; i < content.length; i++) {
			var obj = content[i];
			if(obj.type == "Mandatory"){
				$('#mandatory').append('<table></table>');
				$('#mandatory tr:last').after("<tr><td>"+obj.from_date+"</td><td>"+obj.description+"</td><td>"+obj.type+"</td></tr>");
			}
			if(obj.type == "Weekdays"){
				$('#weekends').append('<table></table>');
				$('#weekends tr:last').after("<tr><td>"+obj.from_date+"</td><td>"+obj.description+"</td><td>"+obj.type+"</td></tr>");
			}
			if(obj.type == "Optional"){
				$('#optional').append('<table></table>');
				$('#optional tr:last').after("<tr><td>"+obj.from_date+"</td><td>"+content.description+"</td><td>"+obj.type+"</td></tr>");
			}
	
			}
	
}




var employeeHolidayList= new employeeHolidayList();