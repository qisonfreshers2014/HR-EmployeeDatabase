function viewAllHandsMeeting(data) {
	Loader.loadHTML('.container', 'resources/js/AllHandsMeeting/viewAllHandsMeeting.html', true, function(){
		this.handleShow(data);
	}.ctx(this));
}

viewAllHandsMeeting.prototype.handleShow = function(data) {

	$('#allHandsMeeting').parent().addClass('active');

	$('.container').show();
	
	var self=this;
	
	var pageNo=1;
	
	 $('.selector').pagination(
		  		{
		      items:data.count,
		      itemsOnPage:10,
		      cssStyle: 'light-theme',
		      	
		  	  onPageClick: function(pageNumber) { 
		  		  
		  		self.getAllHandsMeeting(pageNumber);
		           
		           
		       }
		  });


	this.getAllHandsMeeting(pageNo);
	
	
$('#add').click(function(){

	routie("addallHandsMeeting");


}.ctx(this));

}
viewAllHandsMeeting.prototype.getAllHandsMeeting=function(pageNo){
	 var contentinput = {"payload":{"pageNo":pageNo,"pageSize":10}};
	 RequestManager.getAllHandsMeetingSchedule(contentinput, function(data, success) {
		 if(success){
			var id=0;
			var name='';
			console.log(data);
			$('#displayData').empty();
			$('table').append('<tr><th class="theader">Date</th><th class="theader">Day</th><th class="theader">Employee of the Month</th><th class="theader">Description</th><th class="theader">Edit</th></tr>')
			var content=data.allhands;

			$.each(content,function(obj, value){
				
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
				monthsArray[0]="January";
				monthsArray[1]="Febravary";
				monthsArray[2]="March";
				monthsArray[3]="April";
				monthsArray[4]="May";
				monthsArray[5]="June";
				monthsArray[6]="July";
				monthsArray[7]="August";
				monthsArray[8]="September";
				monthsArray[9]="October";
				monthsArray[10]="November";
				monthsArray[11]="December";
				var month=new Date(value.date).getMonth()+1;
				
				$('table').append("<tr style='text-align:center'><td>"+new Date(value.date).getDate()+"-"+monthsArray[new Date(value.date).getMonth()]+"-"+new Date(value.date).getFullYear()+"</td><td>"+weekday[new Date(value.date).getDay()]+"</td><td>"+value.employee+"</td><td>"+value.description+"</td><td><a href='#editAllHandsMeeting'><input type='button' class='editAllHand btn btn-primary btn-md' id="+value.id+" value='Edit'/></a></td></tr>");
			});
			
			 $(function(){
				  
				  var perPage = 10;
					
				 
				  var checkFragment = function() {
				 
				      var hash = window.location.hash;
			
				      hash = hash.match(/^#page-(\d+)$/);
				      if(hash)
				        
				          $("#pagination").pagination("selectPage", parseInt(hash[1]));
				  };
				
				  $(window).bind("popstate", checkFragment);
		
				  checkFragment();
				  });
				 
			//alert(new Date(value.date).getFullYear()+"-"+new Date(value.date).getDate()+"-"+new Date(value.date).getMonth());
			$('.editAllHand').click(function(event){
				var releaseId=event.target.id;
				console.log(event);
				console.log(event.target);
				console.log(releaseId);
				this.editAllHandsMeeting(releaseId);
			}.ctx(this));
		 
		 }
		 
	 }.ctx(this));
		 
		 


}

viewAllHandsMeeting.prototype.addAllHandsMeeting=function(){
	App.loadAllhandmeeting();

	}
viewAllHandsMeeting.prototype.editAllHandsMeeting=function(dataid){

	App.loadAllhandmeetings(dataid);

	}





