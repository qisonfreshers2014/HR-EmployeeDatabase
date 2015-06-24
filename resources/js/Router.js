function Router() {
	this.handleShow(function() {
		this.routeManager();
	}.ctx(this));	
}

Router.prototype.handleShow = function(callBack) {
	App.loadEmployeePage(App.userName,App.hr,App.isDeleted);
     App.loadEmployee(App.gender,App.contactNo,App.employeeId);
	// App.loadHRHomeHeader(App.userName);
	//App.whiteLabelConfigs();
	/*if (getParameterByName("action") == "resetPassword") {
		Loader.loadResetPassword(function() {
			setTimeout('new ResetPassword(getParameterByName("resetPasswordToken"))', 1000);
		});
	}*/
	callBack();
}

Router.prototype.routeManager = function() {
	var self = this;
	routie({
	    '': function() {
	    	routie("home");
	    },
	    'home': function() {
	    	//App.loadEmployeePage(App.userName,App.hr,App.isDeleted);
	    	 App.loadEmployee(App.gender,App.contactNo,App.employeeId);
	    },
		'myprofile': function() {
			//App.loadEmployeePage(App.userName,App.hr,App.isDeleted);
			App.loadempviewemployee(App.employeeId);
	    },                                                                                                       
	    'hrholiday': function() {
	    	//App.loadEmployeePage(App.userName,App.hr,App.isDeleted);
	    	App.loadEmployeeHoliday();
	    },
	    'hrPolicies': function() {
	    	//App.loadEmployeePage(App.userName,App.hr,App.isDeleted);
	    	 App.listEmployeePolicy(); 
	    },
	    'empAllhands': function() {
	    	//App.loadEmployeePage(App.userName,App.hr,App.isDeleted);
	    	var contentinput = {"payload":{"pageNo":1,"pageSize":10}};
			 RequestManager.getAllHandsMeetingSchedule(contentinput, function(data, success) {
				 if(success){
					 
					 
					 App.loadempAllhands(data); 
			
			 
				 }
			 });
	    	
	    },
	    'employeeedit': function() {
	    	 App.loadEditEmp(App.employeeId);
	    },
	   
	    'changepassword': function() {
	    	 App.loadchangePassword(App.employeeId);
	    },
	   

	    'employee': function() {
	    	App.loadHRHomeHeader(App.userName);
	    	 var contentinput = {"payload":{"pageNo":1,"pageSize":10}};
	    	 RequestManager.getPaginatedEmployees(contentinput, function(data, success) {
	    		 
	    			
	    		 
	    		  if (success) {
	    			 
	    			 App.listEmployee(data);
	    		  }
	    	 });
	    },
	    'addemployee': function() {
	    	App.loadHRHomeHeader(App.userName);
	    	  App.loadEmpl();
	    },
	    'filteremployee': function() {
	    	App.loadHRHomeHeader(App.userName);
	    	 App.loadFilter();
	    },
	    'holiday': function() {
	    	App.loadHRHomeHeader(App.userName);
	    	  App.loadHoliday();
	    },
	    'hrpolicy': function() {
	    	App.loadHRHomeHeader(App.userName);
	    	  App.listPolicy();
		},
		'addpolicy': function() {
	    	App.loadHRHomeHeader(App.userName);
	    	 App.loadPolicy();
		},
		'template': function() {
	    	App.loadHRHomeHeader(App.userName);
	    	
	    	var contentinput = {"payload":{"pageNo":1,"pageSize":10}};
			 RequestManager.getAlltemplates(contentinput, function(data, success) {
				 if(success){
			
					 App.loadtemplateList(data);
			 
				 }
			 });
	    	
	    },
	    'addtemplate': function() {
	    	App.loadHRHomeHeader(App.userName);
	    	App.loadAddTemplate();
	    },
		'notifications': function() { 
			App.loadHRHomeHeader(App.userName);
			var input={};
			RequestManager.getAllEvents(input, function(data, success) {
				if (success) {		
				                    
									App.loadNotificationHomePage(data);
						}
			else
				{
				App.loadNotificationHomePage(data);
				alert("No Data Found");
						}
			});
		},
		
		'allHandsMeeting': function() {
			App.loadHRHomeHeader(App.userName);
			var contentinput = {"payload":{"pageNo":1,"pageSize":10}};
			 RequestManager.getAllHandsMeetingSchedule(contentinput, function(data, success) {
				 if(success){
			
			 App.loadAllHandsMeeting(data);
			 
				 }
			 });
	    },
	    'addallHandsMeeting': function() {
			App.loadHRHomeHeader(App.userName);
			App.loadAllhandmeeting();
	    },
	    
	});
}

Router.prototype.routeCategories = function(categoryId, engagementModel, pageNo, pageSize, tag) {
	categoryId = parseInt(categoryId);
	App.categoryId = categoryId;
	engagementModel = parseInt(engagementModel);
	pageNo = parseInt(pageNo);
	pageSize = parseInt(pageSize);
	var options = {};

	options.engagementModel = engagementModel;
	options.categoryId = categoryId;
	options.pageNo = pageNo;
	options.pageSize = pageSize;
	if(tag) {
		options.tagName = tag;
	}
	
	switch (engagementModel) {
	case 100:
		App.articlesIndex(options);
		break;
	case 200:
		App.loadPollsIndex(options);
		break;
	case 300:
		break;
	case 5000:
		App.loadStaticPage(categoryId);
		break;
	}
}

Router.prototype.routePollCategories = function(pageNo, pageSize) {
	pageNo = parseInt(pageNo)
	pageSize = parseInt(pageSize)
	var options = {};

	options.pageNo = pageNo;
	options.pageSize = pageSize;
	
	App.loadPollsIndex(options);
}

Router.prototype.routeArticles = function(id, categoryId, selectedCommentId, rightPanelCommentReplyFlag, reload) {
	id = parseInt(id);
	selectedCommentId = parseInt(selectedCommentId);
	categoryId = parseInt(categoryId);
	App.categoryId = categoryId;
	rightPanelCommentReplyFlag = (rightPanelCommentReplyFlag == "true") ? true : false;
	reload = (reload == "true") ? true : false;
	App.getArticleDetails(id, categoryId, selectedCommentId, rightPanelCommentReplyFlag, reload);
}

Router.prototype.routeTagItems = function(tagName) {
	App.getTagItems(tagName);
}

Router.prototype.routeMarkerItems = function(markerId, pageNo, pageSize) {
	markerId = parseInt(markerId)
	pageNo = parseInt(pageNo)
	pageSize = parseInt(pageSize)
	var options = {};

	options.markerId = markerId;
	options.pageNo = pageNo;
	options.pageSize = pageSize;
		
	App.articlesIndex(options);
}

Router.prototype.routePolls = function(pollId) {
	App.loadPollDetailsPage(pollId);
}

Router.prototype.routePollResults = function(pollId) {
	App.pollResults(pollId);
}