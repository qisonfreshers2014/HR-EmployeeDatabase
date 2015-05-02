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

		}
	});

}


Loader.prototype.loadDesignation = function(cb){
		 LazyLoad.css('resources/js/editdesignation/editdesignation.css');
		 LazyLoad.js('resources/js/editdesignation/editdesignation.js',cb);
}

Loader.prototype.loadPolicy = function(cb){
	 LazyLoad.css('resources/js/addinghrpolicy/addpolicy.css');
	 LazyLoad.js('resources/js/addinghrpolicy/addpolicy.js',cb);
}

var Loader = new Loader();