function HRHomePage() {
	Loader.loadHTML('.container', 'resources/js/HRhome/HRhomepage.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

HRHomePage.prototype.handleShow = function() {
	
	
	$('.container').show();

}

HRHomePage.prototype.testService = function() {
	
	console.log('Loaded');
	
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

