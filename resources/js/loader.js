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















































Loader.prototype.loadDes = function(cb){
		// LazyLoad.css('resources/js/designation/cssfile.css');
		 LazyLoad.js('resources/js/designation/EmpDes.js',cb);
}

Loader.prototype.loadPolicy = function(cb){
	/* LazyLoad.css('resources/js/designation/cssfile.css');*/
	 LazyLoad.js('resources/js/addinghrpolicy/addpolicy.js',cb);
}
var Loader = new Loader();