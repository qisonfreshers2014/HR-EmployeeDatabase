function hRHomeHeader() {
 Loader.loadHTML('.header', 'resources/js/HRhome/hRhomeHeader.html', true, function(){
  this.handleShow();
 }.ctx(this));
}

hRHomeHeader.prototype.handleShow = function() {
 
 $('.empName').text(App.userName);
 $('.container').show();


 $('#template').click(function(){
  var dataid="34";
  App.loadViewTemplate(dataid);
    }.ctx(this));
 
 $('#hrpolicy').click(function(){
  App.listPolicy();
    }.ctx(this));
 
 $('#holiday').click(function(){
  App.loadHoliday();
    }.ctx(this));
 
 $('#emp').click(function(){
  App.listEmployee();
    }.ctx(this));
 
 
 $('#notifications').click(function(){
		var input={};
		RequestManager.getAllEvents(input, function(data, success) {
			if (success) {		
			
								App.loadNotificationHomePage(data);
					}
		else
			{
			App.loadNotificationHomePage(data);
			alert("No Data Found");
					}
		
		}
		
		);
    }.ctx(this));
 
 $('#allHandsMeeting').click(function(){
  App.loadAllHandsMeeting();
    }.ctx(this));
 

}





var hRHomeHeader=new hRHomeHeader();