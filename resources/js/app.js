function App() {

	this.handleShow();
}
App.prototype.handleShow = function () {
	 

	   
}

App.prototype.loadFilter=function(cb){
	Loader.loadFilter(function(){
		new FilterEmp(cb);
	});
}


App.prototype.loadSkill=function(cb){
	Loader.loadSkill(function(){
		new skill(cb);
	});
}

var App = new App();