function sendNotificationMail(event, email, employeeName) {

	Loader.loadHTML('.container',
			'resources/js/SendNotification/sendNotificationMail.html', true,
			function() {
				this.handleShow(event, email, employeeName);
			}.ctx(this));
}



sendNotificationMail.prototype.handleShow = function(event, email, employeeName) 
{   
	$('#contentfieldNotification').ckeditor();
	var sendbutton = $('#SubmitNotification');

		
	
	//Requesting the Template Content from the Template table in HRMS database
	var inputForContent = {
		"payload" : {
			"subject" : event
		}
	};

	RequestManager.getContentForMail(inputForContent, function(data, success) {
		if (success) {
			
			var content="Hi "+employeeName+"</br>";
		    
			content+=data.content;			
			$('#contentfieldNotification').ckeditor();
			$('#contentfieldNotification').val(content);
			
		}

	});



	   console.log(event);
       console.log(email);
       console.log(employeeName);	

       //sending the payload to the server
	$("#SubmitNotification").click(function() {
		alert("Sending Mail..........")
		sendbutton[0].disabled = true;
		var modifiedcontent =$('#contentfieldNotification').val();
		var input = {
			"payload" : {
				"event" : event,
				"employeeEmail" : email,
				"employeeName" : employeeName,
				"modifiedContent":modifiedcontent
			}
		};
console.log(modifiedcontent);
		RequestManager.getSentManualMail(input, function(data, success) {
			if (success) {
				alert("Mail Succesfully Send");
				var input={};
				RequestManager.getAllEvents(input, function(data, success) {
					if (success) {	
								App.loadNotificationHomePage(data);
					}
					else
					{
					App.loadNotificationHomePage(data);
					alert("No Data Found");
							}});
			}

		});
	
	
	}.ctx(this));


		//Checking the event and accordingly displaying the data 
		if (event == "Aniversay") {
			var send = '<select  id="sendtoDdbNotification"><option>'+employeeName+' (' + email + ') </option></select>';
//Apending the name in the To drop  down
		 $("#sendToNotification").append(send);
			var subject = '<select  id="SubjectNotification">';
			subject += '<option value="Anniversary">' + "Happy Work Anniversary"
					+ "</option>";
			//Apending the Subject to the subject drop down
			  $("#sendSubjectNotification").append(subject);
		} else if(event=="Birthday") {
			var send = '<select  id="sendtoDdbNotification"><option>'+employeeName+' (' + email + ') </option></select>';
			//Apending the name in the To drop  down
 $("#sendToNotification").append(send);
			var subject = '<select  id="SubjectNotification">';
			subject += '<option value=Birthday">' + "Happy Birth Day" + "</option>";
			//Apending the Subject to the subject drop down
			  $("#sendSubjectNotification").append(subject);
		}
		else
		{
			var send = '<select  id="sendtoDdbNotification"><option>'+employeeName+' (' + email + ') </option></select>';
			//Apending the name in the To drop  down
 $("#sendToNotification").append(send);
			var subject = '<select  id="SubjectNotification">';
			subject += '<option value=Welcome">'+" Welcome To Qison Family"+ "</option>";
			//Apending the Subject to the subject drop down
			  $("#sendSubjectNotification").append(subject);
		}
		
		console.log(event);


}