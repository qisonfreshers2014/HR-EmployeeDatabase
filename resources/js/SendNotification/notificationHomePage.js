function loadNotificationHomePage(data) {
	Loader.loadHTML('.container',
			'resources/js/SendNotification/notificationHomePage.html', true,
			function() {
				this.handleShow(data);
			}.ctx(this));
}

loadNotificationHomePage.prototype.handleShow = function(data) {
	
		
	var i;
	var out;
	for (i = 0; i < data.length; i++) {
		var item = data[i];
		var value = item.date;
		var res = new Date(value);
		var year = res.getFullYear();
		var month = res.getMonth() + 1;
		var dd = res.getDate();
		
		out = '<tr><td id="notificationempname">' + item.employeeName + '</td><td>' + year
				+ '-' + month + '-' + dd + '</td>' + '<td>' + item.event
				+ '</td><td>' + item.employeeEmail + '</td><td>' + item.status
				+ '</td>';
		if (item.status == "Not Sent") {
			out += '<td><input type="button" value="Send"  class="dynamicSend btn btn-primary btn-md"/></td></tr>'
		} else {
			out += '<td></td></tr>';
		}
	
		$("#displayDataNotification").append(out);
	}
	$('.dynamicSend').on('click', function() {
		var employeeName = ($(this).parent().parent().find('td:first').text());
		var event = ($(this).parent().parent().find('td:eq(2)').text());
		var email = ($(this).parent().parent().find('td:eq(3)').text());
		App.loadManualMail(event, email, employeeName);

	});

	$("#eventsNotification").change(function() {
		var selectedEvent = $("#eventsNotification").val();
		var input = {
				"payload" : {"selectedEvent" : selectedEvent,
				
							}	};
		this.getdisplayedata(input);
	}.ctx(this));
	$("#retriveDataNotification").click(function() {
		this.eventChangeCriteria();
	}.ctx(this));
	$("#fromDateNotification").focusin(function() {
		this.calendar();
	}.ctx(this));
	$("#toDateNotification").focusin(function() {
		this.calendar();

	}.ctx(this));
	$("#backnotificationhome").focusin(function() {
		var input={};
		RequestManager.getAllEvents(input, function(data, success) {
			if (success) {		
			
								App.loadNotificationHomePage(data);
					}
		else
			{
			App.loadNotificationHomePage(data);
			alert("No Data Found");
					}
		
		}
		
		);

	}.ctx(this));

}

loadNotificationHomePage.prototype.calendar = function() {	
	$(".date").datepicker({
		dateFormat : 'yy-mm-dd',
		showButtonPanel : true,
		changeMonth : true,
		changeYear : true,
		showAnim : 'drop',
			   
	});


}


loadNotificationHomePage.prototype.eventChangeCriteria = function() {

	var selectedEvent = $("#eventsNotification").val();
	var fromDate = $("#fromDateNotification").val();
	var toDate = $("#toDateNotification").val();
	console.log(fromDate);
	console.log(toDate);



	var dateformat = /^(19|20)\d\d-(0\d|1[012])-(0\d|1\d|2\d|3[01])$/;

	  
	   var fromdatevalidation=new Date(fromDate);
	   var todatevalidation=new Date(toDate);
	   
	   if(fromdatevalidation>todatevalidation )
	   {
	   alert("From date should not be greater than the to date")
	   this.currentMonthEvents();
	   }
	   else if(fromDate =="")
	     {
	   alert("Please enter from date");
	
	     }
	   else if(toDate =="")
	     {
	   alert("Please enter to date");
	  
	     }
	    else if(!fromDate.match(dateformat) || !toDate.match(dateformat) )
	   {
	  alert("Invalid date,date format should be yyyy-mm-dd")
	   this.currentMonthEvents();
	   }
	   else
		   { 
			

	var input = {
		"payload" : {"selectedEvent" : selectedEvent,
			"todate" : toDate,
				"fromdate" : fromDate,
					}
	};
	
this.getdisplayedata(input);


		   }
}



loadNotificationHomePage.prototype.currentMonthEvents = function() {
	var input={};
	RequestManager.getAllEvents(input, function(data, success) {
		if (success) {		
		
							App.loadNotificationHomePage(data);
				}
	else
		{
		App.loadNotificationHomePage(data);
		alert("No event in current month");
				}
	
	}.ctx(this));
	
	
	
}



loadNotificationHomePage.prototype.getdisplayedata = function(input) {
	RequestManager.getNotificationDisplayCriteria(input,function(data, success) {					
				if (success) {					
					
					var i;
					var out = '<table border="1" class="table table-hover" id="displayData1"><tbody><tr><th class="thNotification">Employee Name</th><th class="thNotification">Event Date</th><th class="thNotification">Event</th><th class="thNotification">Email ID</th><th class="thNotification">Status</th><th class="thNotification">Action</th></tr>'
					for (i = 0; i < data.length; i++) {
						var item = data[i];
						var value = item.date;
						var res = new Date(value);
						var year = res.getFullYear();
						var month = res.getMonth() + 1;
						var dd = res.getDate();
						out += '<tr><td id="name">' + item.employeeName
								+ '</td><td>' + year + '-' + month
								+ '-' + dd + '</td>' + '<td>'
								+ item.event + '</td><td>'
								+ item.employeeEmail + '</td><td>'
								+ item.status + '</td>';
						if (item.status == "Not Sent") {
							out += '<td><input type="button" value="Send"  class="dynamicSend btn btn-primary btn-md"/></td></tr>';
						} else {
							out += '<td></td></tr>';
						}

						
					}
					out +="</table>";
					document.getElementById("dataTableNotification").innerHTML = out;
					$("#backnotificationhome").css("visibility","visible");
					
					$('.dynamicSend').on(
							'click',
							function() {

								var employeeName = ($(this).parent()
										.parent().find('td:first')
										.text());
								var event = ($(this).parent().parent()
										.find('td:eq(2)').text());
								var email = ($(this).parent().parent()
										.find('td:eq(3)').text());
								console.log(event);
								console.log(email);
		          				console.log(employeeName);
								App.loadManualMail(event, email,
										employeeName);

							});
				
					
				}
				else
					{
					alert(" "+data.message);
				this.currentMonthEvents();
					}

			}.ctx(this));
	
}
