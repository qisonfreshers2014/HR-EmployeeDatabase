function App() {

	this.handleShow();
}
App.prototype.handleShow = function () {
	 

	   
}

























































































































































App.prototype.loadEmpl=function(){
	Loader.loadEmpl(function(){
		new AddEmployee();
	});
}
App.prototype.loadEditEmp=function(){
	Loader.loadEditEmp(function(){
		new EditEmployee();
	});
}

App.prototype.loadhrEditEmp=function(){
	Loader.loadhrEditEmp(function(){
		new HrEditEmployee();
	});
}

App.prototype.loadtemplateList=function(){
	Loader.loadtemplateList(function(){
		new TemplateList();
	});
}

var App = new App();