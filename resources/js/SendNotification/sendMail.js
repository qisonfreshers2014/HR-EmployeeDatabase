function SendMail(data){
	Loader.loadHTML('.container','resources/js/SendNotification/sendNotificationMail.html', true, function() {
		this.handleShow(data);
	}.ctx(this));
}
SendMail.prototype.handleShow = function(data) { 
	
	$('#Tomail').css("visibility","visible");
	$('#subjectformail').css("visibility","visible");
	
	$('#Tomail').val("Qison@gmail.com");
	$('#subjectformail').val(data.subject);
	$('#contentfieldNotification').ckeditor();
	$('#contentfieldNotification').val(data.content);
	
$("#backtonotifications").click(function(){
		
       routie("notifications");
		
	}.ctx(this));
	
	
	$('#SubmitNotification').click(function(){
		document.getElementById('SendMailNotification').style.display="hidden";
		document.getElementById('light').style.display='block';
		document.getElementById('fade').style.display='block';
		var contentmail=$('#contentfieldNotification').val();
		
		var to=$('#Tomail').val();

		var inputmail= {
				"payload" : {
					"event" : data.name,
					"employeeEmail" :to,
					"modifiedContent":contentmail
				}
	}
		
		RequestManager.getSentMailContent(inputmail,function(data,success){
			
			if(success){
				

				document.getElementById('light').style.display='hidden';
				document.getElementById('fade').style.display='hidden';
				document.getElementById('SendMailNotification').style.display="block";
				
				alert("Mail succesfully send");
				var input={};
				RequestManager.getAllEvents(input, function(data, success) {
					if (success) {	
								App.loadNotificationHomePage(data);
					}
					else
					{
					App.loadNotificationHomePage(data);
					alert("No event in the current month");
							}});
			}
			else{
				alert("failed to send");
			}
		
	}.ctx(this));
});
}