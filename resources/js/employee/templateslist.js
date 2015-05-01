function TemplateList() {
	Loader.loadHTML(".leftContainer", "resources/js/employee/templateslist.html",
			true, function() {
				this.handleShow();
			}.ctx(this));
}
TemplateList.prototype.handleShow = function() {


	$('.container').show();
	
}

var TemplateList = new TemplateList();

