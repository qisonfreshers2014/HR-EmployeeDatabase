function editHandMeeting(dataid) {
	Loader.loadHTML('.container', 'resources/js/AllHandsMeeting/editHandMeeting.html', true, function(){
		this.handleShow(dataid);
}.ctx(this));
}
editHandMeeting.prototype.handleShow=function(dataid){
	$('.container').show();
		this.EditHandMeetingDetails(dataid);
	//click event for update have to write
		$( "#datepicker" ).datepicker({dateFormat:'yy-mm-dd',showButtonPanel:
			   true,changeMonth:true,changeYear:true,showAnim:'drop',minDate:new Date(1993,12,31),
			   maxDate:new Date(2050,12,31)});

		
		$('#updateAllHand').click(function(){
			//alert(1);
			
			this.editAllHandsMeetingById(dataid);
			
			
		}.ctx(this));

}


editHandMeeting.prototype.EditHandMeetingDetails=function(dataid){


	 var input = {"payload":{"id":dataid}};
	// alert(2);
	 //alert(dataid);
	 RequestManager.editAllHandsMeeting(input, function(data, success) {
		
	
		   if(success){
			   //alert("sucess");
			 var obj=data[0];
			
			/* var value = new Date(value.date).getFullYear()+"-"+new Date(value.date).getMonth()+"-"+new Date(value.date).getDate();
			 $('.datepicker').val(value);*/
			 value=obj.date;
			  var res = new Date(value);
			  var year  = res.getFullYear();
			  var month = res.getMonth()+1 ;
			  var date = res.getDate ();
				   
			 $('.datepicker').val(year+"-"+month+"-"+date);
			   $('.editemployee').val(obj.employee);
			   $('.editdescription').val(obj.description);
		    //$( "input#clear" ).trigger( "click" );
		   }else{
		    alert("failed to edit");
		   }

}.ctx(this));
	 
}
editHandMeeting.prototype.editAllHandsMeetingById=function(dataid){
	var dateformat=/^\d{4}-\d{1,2}-\d{1,2}$/;
	var regex = /^[A-Za-z]+( [A-Za-z]+)*$/;
	
	
	
	
	if($('#datepicker').val()==""){
		 alert("Please Enter Date");
	 }else if($('#editemployee').val()==""){
		 alert("Please Enter Employee Of the Month");
	 }
	 else if($('#editdescription').val()==""){
		 alert("Please Enter Description");
	 }
	 else if(!($('#datepicker').val()).match(dateformat)){
    	 alert("Please enter Date in yyyy-mm-dd Format");
     }
	
	else  if(!($('#editemployee').val()).match(regex))
	    		 {
	    		 alert("Please enter only characters and one space between two words for Employee Of the Month");
	    		 }
	    	 else 
	    		 if($('#editemployee').val().length<2)
	    		 {
	    		
		 alert("Invalid length minimum 2 characters needed!(Upto 30)");
	    	 	}
	 
	 	 else{
    var input ={"payload":{"id":dataid,"date":$('#datepicker').val() +" 03:00:00" ,"employee":$('#editemployee').val(),"description":$('#editdescription').val()}};
    //alert(4);
    RequestManager.editAllHands(input, function(data, success)
    {
    	//alert(5);
        if(success){
         alert("Successfully Updated");
         $('#datepicker').val("");
         $('#editemployee').val("");
         $('#editdescription').val("");
         App.loadAllHandsMeeting();
        }else if(data.code == 204){
        	alert("Failed to update..Date already exists");
        }
        else{
         alert("Failed to Update");
        }
    }.ctx(this));
	 }	
	    		 }