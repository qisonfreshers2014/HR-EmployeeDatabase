function NotificationALinkrea() {
	Loader.loadHTML('.leftContainer', 'resources/js/SendNotification/NotificationLinks.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

NotificationALinkrea.prototype.handleShow=function() {
	$(".container.show").show();	
	
	$("#WeekBirthday").click(function(){
		this.getWeekBD();
	}.ctx(this));	
	$("#WorkAniversary").click(function(){
		this.getaniversay();
	}.ctx(this));
	
	$("#TodaysBirthday").click(function(){
		this.getTodayBD();
	}.ctx(this));	
	
	$("#Welcome").click(function(){
		this.getWelcomeEmployee();
	}.ctx(this));
}	



NotificationALinkrea.prototype.getWeekBD=function()
	{
	var data={}	;	

		RequestManager.getWeekBirthdayNotification(data, function(data, success) {
			if(success){
				var event="HappyBirthday";
				App.loadNotiMail(data,event);
			}else{
			//alert('Fail to login :'+ data.message);
			$.ambiance({
			message : "Fail : "+data.message,
			type : 'error'
			});
			}
			}.ctx(this));
	}		



NotificationALinkrea.prototype.getaniversay=function()
{
		var data={}	;
		
		RequestManager.getAniversaryNotification(data, function(data, success) {
			if(success){
				var event="Happy Work Aniversary";
				App.loadNotiMail(data,event);
			}else{
			//alert('Fail to login :'+ data.message);
			$.ambiance({
			message : "Fail : "+data.message,
			type : 'error'
			});
			}
			}.ctx(this));
	}	
	
	
NotificationALinkrea.prototype.getTodayBD=function()
	{
			var data={};
			var event="Happy Birth Day"
			RequestManager.getTodayBirthdayNotification(data, function(data, success) {
				if(success){
					var event="Happy Birth Day";
					App.loadNotiMail(data,event);
				}else{
				//alert('Fail to login :'+ data.message);
				$.ambiance({
				message : "Fail : "+data.message,
				type : 'error'
				});
				}
				}.ctx(this));
		
		}	
		


