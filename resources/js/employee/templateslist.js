function TemplateList() {
	Loader.loadHTML(".leftContainer", "resources/js/employee/templateslist.html",
			true, function() {
				this.handleShow();
			}.ctx(this));
}
EditEmployee.prototype.handleShow = function() {

	$('.container').show();
	
}


