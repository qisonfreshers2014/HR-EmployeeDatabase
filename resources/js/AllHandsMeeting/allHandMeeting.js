function allHandMeeting() {
	Loader.loadHTML('.container', 'resources/js/AllHandsMeeting/allHandsMeeting.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

allHandMeeting.prototype.handleShow = function() {
	$( "#datepicker" ).datepicker({dateFormat:'yy-mm-dd',showButtonPanel:
		   true,changeMonth:true,changeYear:true,showAnim:'drop',minDate:new Date(1993,12,31),
		   maxDate:new Date(2050,12,31)});
$('#save').click(function(){
	
	this.addAllHandsMeeting();
	
}.ctx(this));

}
allHandMeeting.prototype.addAllHandsMeeting=function(){
	//var dateformat=/^\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}$/;
	var regex = /^[A-Za-z]+( [A-Za-z]+)*$/;
	 if($('#datepicker').val()==""||$('#employee').val()==""||$('#description').val()==""){
     	alert("failed to add,since every field is mandatory");
     }else
	 
	 if(!($('#employee').val()).match(regex))
		 {
		 alert("Enter valid name ");
		 }
	 else{
		 
		 if($('#employee').val().length<2){
			 alert("Invalid length -minimum 2 characters needed!(Upto 30) ");
		 }
		 
		 else{
    var input ={"payload":{"date":$('#datepicker').val() + ' 03:00:00',"employee":$('#employee').val(),"description":$('#description').val()}};

    RequestManager.addAllHands(input, function(data, success)
    {
    	
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
}