function editHandMeeting(dataid) {
	Loader.loadHTML('.leftcontainer1', 'resources/js/AllHandsMeeting/editHandMeeting.html', true, function(){
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

		
		$('#update').click(function(){
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
	
	
//alert(3);
	/*var dateformat=/^\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}$/;
			 if(!($('#datepicker').val()).match(dateformat))
			 {
			 alert("date format should must yyyy/MM/dd HH:mm:ss ");
			 }*/
	 if($('#datepicker').val()==""||$('#employee').val()==""||$('#description').val()==""){
	     	alert("failed to add,since every field is mandatory");
	     }
	 else{
    var input ={"payload":{"id":dataid,"date":$('#datepicker').val() +" 03:00:00" ,"employee":$('#editemployee').val(),"description":$('#editdescription').val()}};
    //alert(4);
    RequestManager.editAllHands(input, function(data, success)
    {
    	//alert(5);
        if(success){
         alert("Successfully Inserted");
        }else if(data.code == 204){
        	alert("Date already exists");
        }
        else{
         alert("Failed to Add");
        }
    }.ctx(this));
	 }	
}

