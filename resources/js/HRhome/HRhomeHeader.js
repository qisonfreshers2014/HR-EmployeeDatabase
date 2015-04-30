function HRHomeHeader(name) {
	Loader.loadHTML('.header', 'resources/js/HRhome/HRhomeHeader.html', true, function(){
		this.handleShow(name);
	}.ctx(this));
}

HRHomeHeader.prototype.handleShow = function(name) {
	
	$('.empName').text(name);
	$('.container').show();
	/*$('.testService').click(function(){
		this.testService();
	}.ctx(this));*/
	$('#template').click(function(){
		this.testTemplate();
				}.ctx(this));
	$('#allHandsMeeting').click(function(){
		this.testAllHandsMeeting();
				}.ctx(this));
	

}


HRHomeHeader.prototype.testTemplate = function() {

	var dataid="34";
	App.loadViewTemplate(dataid);
}
HRHomeHeader.prototype.testAllHandsMeeting = function() {
	App.loadAllHandsMeeting();
}
/*	var input = {"payload":{}};
	RequestManager.loadTest(input, function(data, success) {
		if(success){
			alert(data);
			$(".leftContainer").show();
			$(".leftContainer").css('background-color', 'aqua');

			$(".rightContainer").show();
			$(".rightContainer").css('background-color', 'green');
			$(".leftContainer").append('<p>'+data+ '</p>');
			$(".rightContainer").append('<p>'+data+ '</p>');
		}else{
		
			alert("failed");
		}
	}.ctx(this));*/


