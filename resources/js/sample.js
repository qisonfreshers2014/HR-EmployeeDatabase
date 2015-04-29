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
	//App.loadDes();
App.loadPolicy();
	//App.loadLogin();
//App.loadHRPolicyVeiwPage();

}
 

var Sample= new Sample();
