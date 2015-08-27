function sendNotificationMail(event, email, employeeName) {

	Loader.loadHTML('.container',
			'resources/js/SendNotification/sendNotificationMail.html', true,
			function() {
				this.handleShow(event, email, employeeName);
			}.ctx(this));
}



sendNotificationMail.prototype.handleShow = function(event, email, employeeName) 
{
	$("#backtonotifications").click(function() {
		
		var input={};
		RequestManager.getAllEvents(input, function(data, success) {
			if (success) {		
			                    
								App.loadNotificationHomePage(data);
					}else if(data.code==131){

                                                alert("Sorry, you are not a authorized user for this action");
                                                     App.loadEmployeePage(App.userName,App.hr,App.isDeleted);
                                                     routie("home");
                                            }      
		else
			{
			App.loadNotificationHomePage(data);
			alert("No Data Found");
					}
		});
	
	}.ctx(this));
	
	//$('#contentfieldNotification').ckeditor();
	var sendbutton = $('#SubmitNotification');

	//Requesting the Template Content from the Template table in HRMS database
	var inputForContent = {
		"payload" : {
			"event" : event,
			"employeeEmail":email
		}
	};

	RequestManager.getContentForMail(inputForContent, function(data, success) {
		if (success) {
		
		    
			var content=data.content;
			$('#contentfieldNotification').ckeditor();
			$('#contentfieldNotification').val(content);
			
		}

	});
       //sending the payload to the server
	$("#SubmitNotification").click(function() {  
		
		
		document.getElementById('SendMailNotification').style.display="hidden";
		document.getElementById('light').style.display='block';
		document.getElementById('fade').style.display='block';
		
	
		
		sendbutton[0].disabled = true;
		
		var modifiedcontent =$('#contentfieldNotification').val();
		
		console.log(modifiedcontent);
		
		var input = {
			"payload" : {
				"event" : event,
				"employeeEmail" : email,
				"employeeName" : employeeName,
				"modifiedContent":modifiedcontent
			}
		};
	

		RequestManager.getSentManualMail(input, function(data, success) {
			if (success) { 
				
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
			else
				{
				alert(" "+data.message);
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

		});
	
	
	}.ctx(this));


		//Checking the event and accordingly displaying the data 
		if (event.replace(/\s/g, '').toLowerCase() == "workanniversary") {
			var send = '<select  id="sendtoDdbNotification"><option>'+employeeName+' (' + email + ') </option></select>';
//Apending the name in the To drop  down
		 $("#sendToNotification").append(send);
			var subject = '<select  id="SubjectNotification">';
			subject += '<option value="Anivarsary">' + "Happy Work Anniversary"
					+ "</option>";
			//Apending the Subject to the subject drop down
			  $("#sendSubjectNotification").append(subject);
		} else if(event.replace(/\s/g, '').toLowerCase() =="birthday") {
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
	
}