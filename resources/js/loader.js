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

Loader.prototype.loadAddTemplate = function(cb){
	LazyLoad.css('resources/css/addtemplate.css');
	LazyLoad.js('resources/js/addtemplate.js', cb);
	//LazyLoad.js('resources/js/lib/nicEdit.js',cb);
}

Loader.prototype.loadViewEmployee = function(cb){
	LazyLoad.css('resources/css/viewEmployee.css');
	LazyLoad.js('resources/js/viewEmployee.js', cb);
}
Loader.prototype.loadempviewemployee = function(cb){
	LazyLoad.css('resources/css/empviewemployee.css');
	LazyLoad.js('resources/js/empViewemployee.js', cb);
}
var Loader = new Loader();