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
		console.log(item.status);

		out = '<tr><td id="name">' + item.employeeName + '</td><td>' + year
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


	$("#retriveDataNotification").click(function() {
		this.eventChangeCriteria();
	}.ctx(this));
	$("#fromDateNotification").focusin(function() {
		this.calendar();
	}.ctx(this));
	$("#toDateNotification").focusin(function() {
		this.calendar();

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

	var selectedEvent = $("#EventsNotification").val();
	var fromDate = $("#fromDateNotification").val();
	var toDate = $("#toDateNotification").val();
	
	
	   var fromdatevalidation=new Date(fromDate);
	   var todatevalidation=new Date(toDate);
	   
	   if(fromdatevalidation>todatevalidation || fromDate =="" || toDate =="" )
	   {
	   alert("Invalid Date!!!!!")
	   }
	   else
		   {
	
	
	var input = {
		"payload" : {"selectedEvent" : selectedEvent,
			"todate" : toDate,
				"fromdate" : fromDate,
					}
	};
	


	RequestManager
			.getNotificationDisplayCriteria(
					input,
					function(data, success) {					
						if (success) {					
							
							var i;
							var out = '<table border="1" class="table table-hover" id="displayData1"><tbody><tr><th>Employee Name</th><th>Event Date</th><th>Event</th><th>Email ID</th><th>Status</th><th>Action</th></tr>'
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

								document.getElementById("dataTableNotification").innerHTML = out;
							}
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
							alert("No Records Found")
							}

					}.ctx(this));
		   }
}