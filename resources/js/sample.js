function Sample() {
	Loader.loadHTML('.leftContainer', 'sample.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

Sample.prototype.handleShow = function() {
	$('.container').show();
	$('.testService').click(function(){
		this.testService();
	}.ctx(this));

}

Sample.prototype.testService = function() {
	
	//App.loadHoliday();
	
	//App.loadEmployeeHoliday();
	
	App.listEmployees();
	/*var input = {"payload":{}};
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


var Sample= new Sample();