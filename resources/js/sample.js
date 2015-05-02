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
	
	
	App.loadHoliday();
	
	//App.loadEmployeeHoliday();
	
	//App.listEmployee();
	
	//App.listPolicy();

	//App.loadAddTemplate();
	// App.loadViewEmployee();
	 //App.loadempviewemployee();
	 
	 //App.loadDes();
	 //App.loadPolicy();


}


var Sample= new Sample();