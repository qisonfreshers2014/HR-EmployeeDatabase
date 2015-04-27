function App() {

	this.handleShow();
}
App.prototype.handleShow = function () {
	 

	   
}

App.prototype.loadViewEmployee=function(){
	Loader.loadViewEmployee(function(){
		new ViewEmployee();
	});
}

App.prototype.loadAddTemplate = function(){
	
	Loader.loadAddTemplate(function(){
		new AddTemplate();
	});
}
App.prototype.loadempviewemployee=function(){
	Loader.loadempviewemployee(function(){
		new empViewemployee();
	});
}

var App = new App();