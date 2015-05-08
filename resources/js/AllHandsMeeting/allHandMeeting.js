function allHandMeeting() {
	Loader.loadHTML('.container', 'resources/js/AllHandsMeeting/allHandsMeeting.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

allHandMeeting.prototype.handleShow = function() {
	$( "#datepicker" ).datepicker({dateFormat:'yy-mm-dd',showButtonPanel:
		   true,changeMonth:true,changeYear:true,showAnim:'drop',minDate:new Date(1993,12,31),
		   maxDate:new Date(2050,12,31)});
$('#saveAllHand').click(function(){
	
	this.addAllHandsMeeting();
	
}.ctx(this));

}
allHandMeeting.prototype.addAllHandsMeeting=function(){
	var dateformat=/^\d{4}-\d{2}-\d{2}$/;
	var regex = /^[A-Za-z]+( [A-Za-z]+)*$/;
	
	 if($('#datepicker').val()==""&&$('#employee').val()==""&&$('#description').val()==""){
     	alert("Failed to add,since every field is mandatory");
     }
	 else if($('#datepicker').val()==""){
		 alert("Please Enter Date");
	 }else if($('#employee').val()==""){
		 alert("Please Enter Employee Of the Month");
	 }
	 else if($('#description').val()==""){
		 alert("Please Enter Description");
	 }
	  	 
	 
	 else if(!($('#datepicker').val()).match(dateformat)){
    	 alert("Please enter Date in yyyy-mm-dd Format");
     }
	 
     else if(!($('#employee').val()).match(regex))
		 {
		 alert("Please enter only characters and one space between two words for Employee Of the Month");
		 }
	 else  if($('#employee').val().length<2){
			 alert("Invalid length minimum 2 characters needed!(Upto 30) ");
		 }
		 
		 else{
    var input ={"payload":{"date":$('#datepicker').val() + ' 03:00:00',"employee":$('#employee').val(),"description":$('#description').val()}};

    RequestManager.addAllHands(input, function(data, success)
    {
    	
        if(success){
         alert("Successfully Inserted");
         $('#datepicker').val("");
         $('#employee').val("");
         $('#description').val("");
           
      
        }else if(data.code == 204){
        	alert("Failed to Add..Date already exists");
        }
        else{
         alert("Failed to Add");
        }
    }.ctx(this));
    
		 }
   
   }