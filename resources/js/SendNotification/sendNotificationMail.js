function sendNotificationMail(event,email,employeeName) {

	Loader.loadHTML('.leftContainer', 'resources/js/SendNotification/sendNotificationMail.html', true, function(){
		this.handleShow(event,email,employeeName);
	}.ctx(this));
}

sendNotificationMail.prototype.handleShow=function(event,email,employeeName) {	
		
	$("#Submit").click(function(){	
		
	var input={	"payload": {
			"event": event,
			"employeeEmail": email,
			"employeeName":employeeName,
			}};

		RequestManager.getSentManualMail(input, function(data, success) {
			if(success)
				{
				alert("Mail Succesfully Send");
				}			
			
		});
		App.loadNotificationHomePage();
	}.ctx(this));
	
	
		if(event=="Aniversay")
		{
	    var i;

	    var out ='<select  id="sendtoDdb"><option/>'+ employeeName + '('+email+')</option></select>';

	       	    document.getElementById("sendTo").innerHTML = out;	
	    	    var subject='<select  id="Subject">';
	    	    subject += '<option value="Anniversary">' +"Happy Work Anniversary"+"</option>";
	    	    	document.getElementById("sendSubject").innerHTML = subject;	
		}
	else
	{
	    var i;
	    var out ='<select  id="sendtoDdb"><option/>'+ employeeName + '('+email+')</option></select>'; 

	  	    document.getElementById("sendTo").innerHTML = out;
	    var subject='<select  id="Subject">';
	    subject += '<option value=Birthday">' +"Happy Birth Day"+"</option>";
	    	document.getElementById("sendSubject").innerHTML = subject;	
		}				
				
}




