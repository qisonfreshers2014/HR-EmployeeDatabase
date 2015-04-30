function AllHandMeeting() {
	Loader.loadHTML('.leftcontainer1', 'resources/js/AllHandsMeeting/AllHandsMeeting.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

AllHandMeeting.prototype.handleShow = function() {
	$( "#datepicker" ).datepicker({dateFormat:'yy-mm-dd',showButtonPanel:
		   true,changeMonth:true,changeYear:true,showAnim:'drop',minDate:new Date(1993,12,31),
		   maxDate:new Date(2050,12,31)});
$('#save').click(function(){
	
	this.addAllHandsMeeting();
	
}.ctx(this));

}
AllHandMeeting.prototype.addAllHandsMeeting=function(){
	//var dateformat=/^\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}$/;
	 if($('#date').val()==""||$('#employee').val()==""||$('#description').val()==""){
     	alert("failed to add,since every field is mandatory");
     }/*else
	 
	 if(!($('#date').val()).match(dateformat))
		 {
		 alert("date format should must yyyy/MM/dd HH:mm:ss ");
		 }*/
	 else{
		// alert(1);
    var input ={"payload":{"date":$('#datepicker').val() + ' 03:00:00',"employee":$('#employee').val(),"description":$('#description').val()}};
  // alert(2);
    RequestManager.addAllHands(input, function(data, success)
    {
    	//alert(3);
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