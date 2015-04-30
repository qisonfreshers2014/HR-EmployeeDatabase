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
	/*var dataid="14";
App.loadViewTemplate(dataid);*/
	//App.loadLogin();
//App.loadAllHandsMeeting();
var name="vasavi";
App.loadHRHomeHeader(name);
App.loadHRHomePage();
App.loadHRHomeFooter();

}


var Sample= new Sample();