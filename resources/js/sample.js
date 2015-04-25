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

	App.loadLogin();


}
 

var Sample= new Sample();