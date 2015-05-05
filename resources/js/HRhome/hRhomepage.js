function hRHomePage() {
	Loader.loadHTML('.container', 'resources/js/HRhome/hRhomepage.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

hRHomePage.prototype.handleShow = function() {
	
	
	$('.container').show();

}

hRHomePage.prototype.testService = function() {
	
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

