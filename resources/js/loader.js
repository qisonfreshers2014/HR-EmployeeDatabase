function Loader() {
	this.handleShow();
}

Loader.prototype.handleShow = function() {

}





Loader.prototype.loadHTML = function(container, filePath, empty, callback) {
	var filePath = filePath;
	$.ajax({
		url : filePath,
		success : function(data) {
			if (empty) {
				$(container).empty();
			}
			$(container).append(data);

			if (callback) {
				callback();
				// console.log(container)
				$(container).msgkey();
			}
			/*
			 * var compiledTemplate = Ember.Handlebars.compile(data);
			 * Ember.View.create({ template: compiledTemplate
			 * }).appendTo(container);
			 */
		}
	});

}







































































































































Loader.prototype.loadEmpl = function(e){
	
	 LazyLoad.js('resources/js/employee/addemp.js',e);
	 LazyLoad.css('resources/js/employee/addemp.css');

}

Loader.prototype.loadEditEmp = function(d){
	LazyLoad.js('resources/js/employee/editemp.js', d);
	LazyLoad.css('resources/js/employee/addemp.css');
}

Loader.prototype.loadhrEditEmp = function(f){
	LazyLoad.js('resources/js/employee/hreditemp.js', f);
	LazyLoad.css('resources/js/employee/addemp.css');
}

Loader.prototype.loadtemplateList = function(g){
	LazyLoad.js('resources/js/employee/templateslist.js', g);
	LazyLoad.css('resources/js/employee/addemp.css');
}
var Loader = new Loader();