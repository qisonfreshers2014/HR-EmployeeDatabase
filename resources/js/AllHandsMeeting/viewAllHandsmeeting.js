function viewAllHandsMeeting() {
	Loader.loadHTML('.container', 'resources/js/AllHandsMeeting/viewAllHandsMeeting.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

viewAllHandsMeeting.prototype.handleShow = function() {

	$('.container').show();
	this.getAllHandsMeeting();
$('#add').click(function(){

	this.addAllHandsMeeting();


}.ctx(this));

}
viewAllHandsMeeting.prototype.getAllHandsMeeting=function(){
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
				var month=new Date(value.date).getMonth()+1;
				$('table').append("<tr style='text-align:center'><td>"+new Date(value.date).getFullYear()+"-"+month+"-"+new Date(value.date).getDate()+"</td><td>"+weekday[new Date(value.date).getDay()]+"</td><td>"+value.employee+"</td><td>"+value.description+"</td><td><input type='button' class='edit btn btn-primary btn-md' id="+value.id+" value='Edit'/></td></tr>");
			});
			//alert(new Date(value.date).getFullYear()+"-"+new Date(value.date).getDate()+"-"+new Date(value.date).getMonth());
			$('.edit').click(function(event){
				var releaseId=event.target.id;
				console.log(event);
				console.log(event.target);
				console.log(releaseId);
				this.editAllHandsMeeting(releaseId);
			}.ctx(this));
		 
		 }
		 
	 }.ctx(this));
		 
		 
/*
		   if(success){
			   for(var i = 0; i < data.length; i++) {
					  var obj = data[i];

						$('#displayData').append('<table></table>');
						$('#displayData ').after("<tr><td>"+new Date(data[i].date)+"</td><td>"+data[i].employee+"</td><td>"+data[i].description+"</td><td><input type='button' value='Edit' id='"+data[i].id+"' class='dynamicEdit'></td></tr>");
						//var dataid=obj.id;

		$('.dynamicEdit').click(function(event){
			console.log(event);
			dataid = event.target;
			console.log(dataid);
			this.editAllHandsMeeting(dataid);

		}.ctx(this));

					}

		    //$( "input#clear" ).trigger( "click" );
		   }else{
		    alert("failed to retrieve");
		   }

	 	}.ctx(this));*/


}

viewAllHandsMeeting.prototype.addAllHandsMeeting=function(){
	App.loadAllhandmeeting();

	}
viewAllHandsMeeting.prototype.editAllHandsMeeting=function(dataid){

	App.loadAllhandmeetings(dataid);

	}






