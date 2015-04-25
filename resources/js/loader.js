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
Loader.prototype.loadLogin = function(cb){
	LazyLoad.css('resources/js/Login/login.css');
	LazyLoad.js('resources/js/Login/login.js',cb);	
}
Loader.prototype.loadNext = function(cb){
	LazyLoad.css('resources/js/Login/next.css');
	LazyLoad.js('resources/js/Login/next.js',cb);	
}

var Loader = new Loader();