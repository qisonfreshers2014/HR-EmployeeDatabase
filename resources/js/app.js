function App() {

	this.handleShow();
}
App.prototype.handleShow = function () {
	 

	   
}







































App.prototype.loadDes=function(){
	Loader.loadDes(function(){
		new EmpDes();

	});

}

App.prototype.loadPolicy=function(){
		Loader.loadPolicy(function(){
			new addpolicy();
		});
}
var App = new App();