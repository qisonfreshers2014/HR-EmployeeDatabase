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
Loader.prototype.loadViewTemplate = function (cb) {
	 //LazyLoad.css('resources/js/HRhome/HRhomeHeader.css');
	 LazyLoad.js('resources/js/HRhome/EditTemplate.js', cb);
	}
Loader.prototype.loadAllHandsMeeting = function (cb) {
	 LazyLoad.css('resources/js/HRhome/AllHandsmeeting.css');
	 LazyLoad.js('resources/js/HRhome/AllHandsmeeting.js', cb);
	}
Loader.prototype.loadHRHomeHeader = function (cb) {
	 LazyLoad.css('resources/js/HRhome/HRhomeHeader.css');
	 LazyLoad.js('resources/js/HRhome/HRhomeHeader.js', cb);
	}

Loader.prototype.loadHRHomePage = function (cb) {
	 LazyLoad.css('resources/js/HRhome/HRhomepage.css');
	 LazyLoad.js('resources/js/HRhome/HRhomepage.js', cb);
	}
Loader.prototype.loadHRHomeFooter = function (cb) {
	 LazyLoad.css('resources/js/HRhome/HRhomeFooter.css');
	 LazyLoad.js('resources/js/HRhome/HRhomeFooter.js', cb);
	}


var Loader = new Loader();