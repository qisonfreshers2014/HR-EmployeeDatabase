function hRHomeFooter() {
	Loader.loadHTML('.footer', 'resources/js/HRhome/hRhomeFooter.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

hRHomeFooter.prototype.handleShow = function() {
	
	
	//$('.container').show();
	$('.testService').click(function(){
		this.testService();
	}.ctx(this));
	
	

}

hRHomeFooter.prototype.testService = function() {
	
	
	
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
}

