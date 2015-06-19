function employeeHolidayList() {
	Loader.loadHTML('.container', 'resources/js/EmployeeHolidayList/EmployeeHolidayList.html', true, function(){
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
		
		 	
		}else{
		  
		  alert("failed to add");
		 }		
		  
		}.ctx(this));

}

//Holidays list for Employees to display
employeeHolidayList.prototype.tableDisplay = function(content, status){
	
	var monthsArray=new Array(12);
	monthsArray[0]="January";
	monthsArray[1]="Febravary";
	monthsArray[2]="March";
	monthsArray[3]="April";
	monthsArray[4]="May";
	monthsArray[5]="June";
	monthsArray[6]="July";
	monthsArray[7]="August";
	monthsArray[8]="September";
	monthsArray[9]="October";
	monthsArray[10]="November";
	monthsArray[11]="December";
	
	
	for(var i = 0; i < content.length; i++) {
	
		var obj = content[i];
		
		
		var value = obj.fromDate;
		var res = new Date(value);
		var year  = res.getFullYear();
		var month = monthsArray[res.getMonth()] ;
		var date = res.getDate (); 

		var dayNames = new Array('Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday');
		var day = dayNames[res.getDay()];
		
		if(obj.type == "Mandatory"){
				$('#mandatory').append('<table><tbody></tbody></table>');
				$('#mandatory tr:last').after("<tr><td>"+date + "-" + month + "-" +year+"</td><td>"+obj.description+"</td><td>"+day+"</td></tr>");
			}
			if(obj.type == "Weekend"){
				$('#weekends').append('<table><tbody></tbody></table>');
				$('#weekends tr:last').after("<tr><td>"+date + "-" + month + "-" +year+"</td><td>"+obj.description+"</td><td>"+day+"</td></tr>");
			}
			if(obj.type == "Optional"){
				$('#optional').append('<table><tbody></tbody></table>');
				$('#optional tr:last').after("<tr><td>"+date + "-" + month + "-" +year+"</td><td>"+obj.description+"</td><td>"+day+"</td></tr>");
			}
	
			}
	
}




var employeeHolidayList= new employeeHolidayList();