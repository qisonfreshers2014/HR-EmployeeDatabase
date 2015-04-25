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
Loader.prototype.loadHoliday = function(){
	 LazyLoad.css('resources/js/Holidays/holidays.css');
	 LazyLoad.js('resources/js/Holidays/Holidays.js');

}

Loader.prototype.loadEmployeeHoliday = function(){
	 LazyLoad.css('resources/js/EmployeeHolidayList/EmployeeHolidayList.css');
	 LazyLoad.js('resources/js/EmployeeHolidayList/EmployeeHolidayList.js');

}

Loader.prototype.listEmployees = function(){
	 LazyLoad.css('resources/js/EmployeesList/EmployeesList.css');
	 LazyLoad.js('resources/js/EmployeesList/EmployeesList.js');

}


var Loader = new Loader();