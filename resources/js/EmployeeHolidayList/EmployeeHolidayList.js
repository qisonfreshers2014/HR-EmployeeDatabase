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
		
		var value = obj.fromDate;
		var res = new Date(value);
		var year  = res.getFullYear();
		var month = res.getMonth()+1 ;
		var date = res.getDate (); 

		var dayNames = new Array('Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday');
		var day = dayNames[res.getDay()];
		
		if(obj.type == "Mandatory"){
				$('#mandatory').append('<table></table>');
				$('#mandatory tr:last').after("<tr><td>"+year + "-" + month + "-" +date+"</td><td>"+obj.description+"</td><td>"+day+"</td></tr>");
			}
			if(obj.type == "Weekdays"){
				$('#weekends').append('<table></table>');
				$('#weekends tr:last').after("<tr><td>"+year + "-" + month + "-" +date+"</td><td>"+obj.description+"</td><td>"+day+"</td></tr>");
			}
			if(obj.type == "Optional"){
				$('#optional').append('<table></table>');
				$('#optional tr:last').after("<tr><td>"+year + "-" + month + "-" +date+"</td><td>"+obj.description+"</td><td>"+day+"</td></tr>");
			}
	
			}
	
}




var employeeHolidayList= new employeeHolidayList();