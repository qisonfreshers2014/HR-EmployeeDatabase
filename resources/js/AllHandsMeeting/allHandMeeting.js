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
$('#back').click(function(){
	
	routie("allHandsMeeting");
	}.ctx(this));


}
allHandMeeting.prototype.addAllHandsMeeting=function(){
	var dateformat=/^\d{4}-\d{1,2}-\d{1,2}$/;
	var regex = /^[A-Za-z]+( [A-Za-z]+)*$/;

	var x=$('#datepicker').val();
	var date=new Date(x);
	var today= new Date();
	
	  if($('#datepicker').val()==""){
		 alert("Please enter Date");
	 }	  
	  else if($('#employeeAll').val()==""){
		 alert("Please enter employee of the month");
	 }
	  else if($('#employeeAll').val().charAt(0)==" "){
			 alert("Please don't enter space as first letter for employee of the month");
		 }
	 else if($('#description').val()==""){
		 alert("Please enter description");
	 }
	  	 
	 
	 else if(!($('#datepicker').val()).match(dateformat)){
    	 alert("Please enter date in yyyy-mm-dd Format");
     }
	 
     else if(!($('#employeeAll').val()).match(regex))
		 {
		 alert("Please enter only characters and one space between two words for employee of the month");
		 }
	 else  if($('#employeeAll').val().length<2){
			 alert("Invalid length minimum 2 characters needed!(Upto 30) ");
		 }
		 
		 else{
    var input ={"payload":{"date":$('#datepicker').val() + ' 03:00:00',"employee":$('#employeeAll').val(),"description":$('#description').val()}};

    RequestManager.addAllHands(input, function(data, success)
    {
    	
        if(success){
         alert("All Hands Meeting Schedule successfully inserted");
         $('#datepicker').val("");
         $('#employeeAll').val("");
         $('#description').val("");
        routie("allHandsMeeting");
      
        }else if(data.code == 204){
        	alert("Date already exists");
        }
        else{
         alert("All Hands Meeting Schedule failed to add");
        }
    }.ctx(this));
    
		 }
   
   }