function loadNotificationHomePage(data) {
	Loader.loadHTML('.leftContainer', 'resources/js/SendNotification/notificationHomePage.html', true, function(){
		this.handleShow(data);
	}.ctx(this));
}

loadNotificationHomePage.prototype.handleShow=function(data) {	
	console.dir(data);	
	var data={}	;	
	RequestManager.getAllEvents(data, function(data, success) {
		if(success){	
	  var i;
	    var out;
	  	    for(i = 0; i < data.length; i++) {
	  	    	var item = data[i];
	    
	        out =  '<tr><td id="name">'+item.employeeName+'</td><td>'+item.date+'</td>'
	        +'<td>'+item.event+'</td><td>'+item.employeeEmail+'</td><td>'+item.status+'</td>';
	        if(item.status=="Not Send")
      	  {
	        	 out += '<td><input type="button" value="Send"  class="dynamicSend" id='+item.employeeEmail+'/></td></tr>';
      	  }
        else
      	  {
      	  out += '<td></td></tr>';
      	  }
	        $('.dynamicSend').on('click', function () {
	            
	           var employeeName=( $(this).parent().parent().find('td:first').text());
	            var event=( $(this).parent().parent().find('td:eq(2)').text());
	            var email=( $(this).parent().parent().find('td:eq(3)').text());
	            console.log(event);
	            console.log(email);
	            console.log(employeeName);
	            
	            App.loadManualMail(event,email,employeeName);
	            
	        });
	        	
	        	
	        		        	
	        	
	        	
            
	        $("#displayData").append(out);
	  	    }	}
	  			}.ctx(this));
	   
	  	    
	$(".sendMail").click(function(){
		alert("Send Mail");
		//this.sendMail();
	}.ctx(this));		

	$("#retriveData").click(function(){
		this.eventCriteria();
	}.ctx(this));	
	$("#fromDate").focusin(function(){
		this.calendar();
	}.ctx(this));
	$("#toDate").focusin(function(){
		this.calendar();
		
	}.ctx(this));
	    	    	
}

loadNotificationHomePage.prototype.calendar=function()
{
	$( ".date" ).datepicker({dateFormat:'yy-mm-dd',showButtonPanel:true,changeMonth:true,changeYear:true,showAnim:'drop'});
		
}


loadNotificationHomePage.prototype.eventCriteria=function()
{
var todate=$("#toDate").val();
var fromdate=$("#fromDate").val();

var selectedEvent = $("#Events").val();
var selectedState = $("#States").val();
var input = {
		"payload": {
			"todate": todate,
			"fromdate": fromdate,
			"selectedEvent":selectedEvent,
			"selectedState":selectedState
		}};

RequestManager.getNotificationDisplayCriteria(input, function(data, success) {
	
	if(success){	
  var i;
    var out='<table border="1" style="width:100% height:200px" id="displayData1"><tbody id="displayData"><tr><th>Employee Name</th><th>Event Date</th><th>Event</th><th>Email ID</th><th>Status</th><th>Action</th></tr>'
  	    for(i = 0; i < data.length; i++) {
  	    	  	var item = data[i];
                  out +=  '<tr><td>'+item.employeeName+'</td><td>'+Date+'</td>'
        +'<td>'+item.event+'</td><td>'+item.employeeEmail+'</td><td>'+item.status+'</td>';
                  if(item.status=="Not Send")
                	  {
                	  out += '<td><input type="button" value="Send"  class="dynamicSend" id='+item.employeeEmail+'/></td></tr>';
                	  }
                  else
                	  {
                	  out += '<td></td></tr>';
                	  }
                  $('.dynamicSend').on('click', function () {
      	            
       	           var employeeName=( $(this).parent().parent().find('td:first').text());
       	            var event=( $(this).parent().parent().find('td:eq(2)').text());
       	            var email=( $(this).parent().parent().find('td:eq(3)').text());
       	                   
       	        });
                
                
        document.getElementById("dataTable").innerHTML=out;
  	    }	
    }
	
  			}.ctx(this));
}
