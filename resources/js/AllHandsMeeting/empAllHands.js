function empAllHands() {
	Loader.loadHTML('.container', 'resources/js/AllHandsMeeting/empAllHands.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

empAllHands.prototype.handleShow = function() {
	
	

	$('.container').show();
	this.getAllHandsMeeting();
$('#add').css("visibility","hidden");
$('#edit').css("visibility","hidden");

}
empAllHands.prototype.getAllHandsMeeting=function(){
	var input = {"payload":{}};
	 RequestManager.getAllHandsMeetingDetails(input, function(data, success) {
		 if(success){
			var id=0;
			var name='';
			console.log(data);

			$.each(data,function(obj, value){
				
				console.log('obj'+'  '+obj);
				console.log('Obj'+ '   '+value);
				var weekday = new Array(7);
				weekday[0]=  "Sunday";
				weekday[1] = "Monday";
				weekday[2] = "Tuesday";
				weekday[3] = "Wednesday";
				weekday[4] = "Thursday";
				weekday[5] = "Friday";
				weekday[6] = "Saturday";
				
				var monthsArray=new Array(12);
				monthsArray[0]="Jan";
				monthsArray[1]="Feb";
				monthsArray[2]="March";
				monthsArray[3]="April";
				monthsArray[4]="May";
				monthsArray[5]="June";
				monthsArray[6]="July";
				monthsArray[7]="Aug";
				monthsArray[8]="Sep";
				monthsArray[9]="Oct";
				monthsArray[10]="Nov";
				monthsArray[11]="Dec";
				
				var month=new Date(value.date).getMonth()+1;
				$('table').append("<tr style='text-align:center'><td>"+new Date(value.date).getDate()+"-"+monthsArray[new Date(value.date).getMonth()]+"-"+new Date(value.date).getFullYear()+"</td><td>"+weekday[new Date(value.date).getDay()]+"</td><td>"+value.employee+"</td><td>"+value.description+"</td></tr>");
			});
		
			
		 }
		 
	 }.ctx(this));
		 
		 


}



