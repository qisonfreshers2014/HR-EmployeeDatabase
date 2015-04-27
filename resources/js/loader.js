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


Loader.prototype.loadLogin = function(cb){
	LazyLoad.css('resources/js/Login/login.css');
	LazyLoad.js('resources/js/Login/login.js',cb);	
}

Loader.prototype.loadNext = function(cb){
	LazyLoad.css('resources/js/Login/next.css');
	LazyLoad.js('resources/js/Login/next.js',cb);	
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

















































Loader.prototype.loadDes = function(cb){
		// LazyLoad.css('resources/js/designation/cssfile.css');
		 LazyLoad.js('resources/js/designation/EmpDes.js',cb);
}

Loader.prototype.loadPolicy = function(cb){
	/* LazyLoad.css('resources/js/designation/cssfile.css');*/
	 LazyLoad.js('resources/js/addinghrpolicy/addpolicy.js',cb);
}

Loader.prototype.loadNotifArea = function(cb){
	 LazyLoad.css('resources/js/SendNotification/NotificationArea.css');
	 LazyLoad.js('resources/js/SendNotification/notificationALinkrea.js',cb);
}
Loader.prototype.loadManualMail = function(cb){
	 LazyLoad.css('resources/js/SendNotification/sendNotification.css');
	 LazyLoad.js('resources/js/SendNotification/sendNotificationMail.js',cb);
}



Loader.prototype.loadNotificationHomePage = function(cb){
	 LazyLoad.css('resources/js/SendNotification/notificationHomePage.css');
   LazyLoad.js('resources/js/SendNotification/notificationHomePage.js',cb);
}
var Loader = new Loader();