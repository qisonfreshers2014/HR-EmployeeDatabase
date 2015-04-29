function Sample() {
	Loader.loadHTML('.leftContainer','sample.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

Sample.prototype.handleShow = function() {
	/*var input = {"payload":{}};
	RequestManager.getEmployees(input, function(data, success) {
		if(success){
			console.dir(data);
			$(".leftContainer").show();
			$(".leftContainer").css('background-color', 'aqua');

			$(".rightContainer").show();
			$(".rightContainer").css('background-color', 'green');
			$(".leftContainer").append('<p>'+data+ '</p>');
			$(".rightContainer").append('<p>'+data+ '</p>');
		}else{
			alert("failed");
		}*/
	$('.container').show();
	$('.testService').click(function(){
		this.testService();
	}.ctx(this));
	

}

Sample.prototype.testService = function() {

	//App.loadDes();
App.loadPolicy();
	//App.loadLogin();
//App.loadHRPolicyVeiwPage();


	//App.loadTemplate();
	
	//App.loadAddTemplate();
	//App.loadViewEmployee();
	//App.loadempviewemployee();
	
	//App.loadDes();
	//App.loadPolicy();
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
 

var Sample= new Sample();
