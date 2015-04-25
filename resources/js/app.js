function App() {

	this.handleShow();
}
App.prototype.handleShow = function () {
	 

	   
}
App.prototype.loadViewTemplate = function(name){
	Loader.loadViewTemplate(function(){
		new EditTemplate();
	});
}
App.prototype.loadAllHandsMeeting = function(name){
	Loader.loadAllHandsMeeting(function(){
		new AllHandsMeeting();
	});
}


App.prototype.loadHRHomeHeader = function(name){
	Loader.loadHRHomeHeader(function(){
		new HRHomeHeader(name);
	});
}
App.prototype.loadHRHomePage = function(){
	Loader.loadHRHomePage(function(){
		new HRHomePage();
	});
}

App.prototype.loadHRHomeFooter = function(){
	Loader.loadHRHomeFooter(function(){
		new HRHomeFooter();
	});
}

App.prototype.loadEmp = function(){
	Loader.loadEmp(function(){
		new Emp();
	});
}
App.prototype.loadHRPolicy = function(){
		Loader.loadHRPolicy(function(){
			new HRPolicy();
	});
}
App.prototype.loadHoliday = function(){
			Loader.loadHoliday(function(){
				new Holiday();
	});
}
App.prototype.loadTemplates = function(){
				Loader.loadTemplates(function(){
					new Templates();
	});
}
App.prototype.loadNotifications = function(){
					Loader.loadNotifications(function(){
						new Notifications();
	});
}



var App = new App();