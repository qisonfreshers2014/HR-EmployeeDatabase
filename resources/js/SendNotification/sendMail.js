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
		var contentmail=$('#contentfieldNotification').val();

		var inputmail= {
				"payload" : {
					"event" : data.name,
					"employeeEmail" :"Qison@gmail.com",
					"modifiedContent":contentmail
				}
	}
		
		RequestManager.getSentMailContent(inputmail,function(data,success){
			
			if(success){
				
				alert("successfully sent");
			}
			else{
				alert("failed to send");
			}
		
	}.ctx(this));
});
}