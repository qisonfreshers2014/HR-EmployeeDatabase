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
Loader.prototype.loadFilter = function(cb){
	 LazyLoad.css('resources/js/FilterEmployee/FilterEmployee.css');
	 LazyLoad.js('resources/js/FilterEmployee/Filter.js',cb);
}
Loader.prototype.loadSkill = function(cb){
	 LazyLoad.css('resources/js/skill/Skill.css');
	 LazyLoad.js('resources/js/skill/Skill.js',cb);
}

var Loader = new Loader();