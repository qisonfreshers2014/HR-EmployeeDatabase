function RequestManager() {
    this.apiPrefix = '/services/v1/';
}




RequestManager.prototype.sendToServer = function (api, data, callback, options) {
    var serviceURL = this.apiPrefix + api;
    $.ajax({
        url:serviceURL,
        contentType:'application/json',
        dataType:'json',
        type:'POST',
        success:function(data) {
            //console.log(data)
            if (data.status && data.status == 'SUCCESS') {
                callback(data.payload, true);
            } else {
                if (data.payload.code == 0) {
                    handleFailure(data.payload);
                } else {
                    callback(data.payload, false);
                }
            }

        }, //TODO Instead of creating payload at the calls, create here.
        data:JSON.stringify(data),
        error:function (xhr, error, exception) {
            //TODO remove console.log
           // console.log(xhr, error, exception);
        }
    });

    function handleFailure(data) {
        var errCode = data.code;
        if (errCode == 0) {
            errMsg = "UnKnown Error in : " + serviceURL;
        }
        var preferences = {
            "body":errMsg,
            "header":"Error"
        };
        //PopupDialog.showErrorMsg(preferences);
        //alert(preferences);
    }

}

RequestManager.prototype.getSentManualMail=function(data,callback){
 this.sendToServer('SendNotificationHistory/sendMail', data, callback);
}

RequestManager.prototype.getSentMailContent=function(data,callback){
 this.sendToServer('employee/getSentMailContent', data, callback);
}

RequestManager.prototype.getSentMailContent=function(data,callback){
	 this.sendToServer('SendNotificationHistory/sendmanualMail', data, callback);
	}
RequestManager.prototype.getAllEvents=function(data,callback){
 this.sendToServer('employee/getAllEvents', data, callback);
} 
RequestManager.prototype.getContentForMail=function(data,callback){
 this.sendToServer('template/getContentForMail', data, callback);
}
RequestManager.prototype.getNotificationDisplayCriteria=function(data,callback){
 this.sendToServer('employee/getNotificationDisplayCriteria', data, callback);
}

 RequestManager.prototype.getTemplates = function(data, callback) {
  this.sendToServer('template/gatTemplate', data, callback);
 }
 RequestManager.prototype.addTemplate = function(data, callback) {
  this.sendToServer('template/save', data, callback);
 }

RequestManager.prototype.viewEmployeedatails = function(data, callback) {
  this.sendToServer('employee/viewEmployee', data, callback);
 }

 RequestManager.prototype.logout=function(data,callback){
 this.sendToServer('employee/logout', data, callback);
}
RequestManager.prototype.authenticate=function(data,callback){
 this.sendToServer('employee/authenticate', data, callback);
}
RequestManager.prototype.getTemplateById = function(data, callback) {
   this.sendToServer('template/viewTemplate', data, callback);
  }

RequestManager.prototype.saveDesignation=function(data,callback){
	this.sendToServer('designation_history/save', data, callback);
}
RequestManager.prototype.getDesignation=function(data,callback){
	this.sendToServer('designation_history/getDesignations', data, callback);
}
RequestManager.prototype.getDesignationName=function(data,callback){
	this.sendToServer('designation_history/getDesignationName', data, callback);
}
RequestManager.prototype.savePolicy=function(data,callback){
	this.sendToServer('hr_policy/save', data, callback);
}

RequestManager.prototype.saveEmp=function(data,callback){
 this.sendToServer('employee/save', data, callback);
}
RequestManager.prototype.getHreditEmployee=function(data,callback){
 this.sendToServer('employee/getEmployee', data, callback);
}
RequestManager.prototype.geteditEmployee=function(data,callback){
 this.sendToServer('employee/getEmployee', data, callback);
}
RequestManager.prototype.updateEmp=function(data,callback){
 this.sendToServer('employee/updateEmployee', data, callback);
}
RequestManager.prototype.hrupdateEmp=function(data,callback){
 this.sendToServer('employee/hrupdateEmployee', data, callback);
}
RequestManager.prototype.hrDeleteEmployee=function(data,callback){
	this.sendToServer('employee/deleteEmployee', data, callback);
}
RequestManager.prototype.saveSkill=function(data,callback){
	 this.sendToServer('skills/save', data, callback);
	}

RequestManager.prototype.getSkillDetails = function(data, callback) {
   this.sendToServer('skills/getSkillsById', data, callback);
  }  
RequestManager.prototype.save = function(data, callback) {
   this.sendToServer('skills/save', data, callback);
  } 
RequestManager.prototype.getEditSKill = function(data, callback) {
   this.sendToServer('skills/geteditskills', data, callback);
  }

RequestManager.prototype.editskills = function(data, callback) {
    this.sendToServer('skills/update', data, callback);
   }
RequestManager.prototype.deleteSkillById = function(data, callback) {
    this.sendToServer('skills/deleteSkillById', data, callback);
   }
 
// RM for filters
RequestManager.prototype.getFilterEmployee = function(data, callback) {
   this.sendToServer('employee/getEmployees', data, callback);
  }

RequestManager.prototype.addAllHands = function(data, callback) {
    this.sendToServer('allhandsmeeting/save', data, callback);
   }
RequestManager.prototype.editAllHands = function(data, callback) {
    this.sendToServer('allhandsmeeting/update', data, callback);
   }
RequestManager.prototype.editAllHandsMeeting = function(data, callback) {
    this.sendToServer('allhandsmeeting/editAllHandsMeeting', data, callback);
   }
RequestManager.prototype.getAllHandsMeetingDetails = function(data, callback) {
    this.sendToServer('allhandsmeeting/getAllHandsMeeting', data, callback);
   }
RequestManager.prototype.getTemplateById = function(data, callback) {
   this.sendToServer('template/viewTemplate', data, callback);
  }
RequestManager.prototype.editTemplateDetails = function(data, callback) {
    this.sendToServer('template/updateTemplate', data, callback);
   }

RequestManager.prototype.holidaysData=function(data,callback){
 this.sendToServer('holiday/save', data, callback);
}
RequestManager.prototype.getHolidaysData=function(data,callback){
 this.sendToServer('holiday/getHolidays', data, callback);
}
RequestManager.prototype.getHoliday=function(data,callback){
 this.sendToServer('holiday/getHolidayId', data, callback);
}
RequestManager.prototype.getHolidayByDate=function(data,callback){
	 this.sendToServer('holiday/holidayByDate', data, callback);

	}
RequestManager.prototype.dynamicallyEdit=function(data,callback){
 this.sendToServer('holiday/updateHoliday', data, callback);

}
RequestManager.prototype.getEmployee=function(data,callback){
  this.sendToServer('employee/getEmployeeDetails', data, callback);
 }

RequestManager.prototype.getSearchEmp=function(data,callback){
  this.sendToServer('employee/searchEmployee', data, callback);
 }
 RequestManager.prototype.getPolicy=function(data,callback){
 this.sendToServer('hr_policy/getPolicy', data, callback);
}
 RequestManager.prototype.viewEmployeedetails = function(data, callback) {
	  this.sendToServer('employee/viewEmployee', data, callback);
	 }
 RequestManager.prototype.getTemplatename = function(data, callback) {
	   this.sendToServer('template/gatTemplate', data, callback);
	  }
 
 RequestManager.prototype.changePassword = function(data, callback) {
	   this.sendToServer('employee/changePassword', data, callback);
	  }
 
 RequestManager.prototype.updateDesignationDetails=function(data,callback){
	 this.sendToServer('employee/updateDesignation', data, callback);
} 
 RequestManager.prototype.addDesignationType = function(data, callback) {
	  this.sendToServer('designationtype/save', data, callback);
	 }
 
 RequestManager.prototype.getDesignationTypes = function(data, callback) {
	  this.sendToServer('designationtype/getDesignationTypes', data, callback);
	 }

 RequestManager.prototype.deleteDesignationType = function(data, callback) {
	  this.sendToServer('designationtype/deleteDesignationType', data, callback);
	 }
 RequestManager.prototype.getLoggedInUserDetails=function(data,callback){
	 this.sendToServer('employee/getLoggedInUserDetails', data, callback);
} 
 RequestManager.prototype.isLoggedin=function(data,callback){
	 this.sendToServer('employee/isloggedin', data, callback);
	}
 RequestManager.prototype.getPaginatedEmployees=function(data,callback){
	 this.sendToServer('employee/getEmployeesListPaginated', data, callback);
}
 
 RequestManager.prototype.getAllHandsMeetingSchedule=function(data,callback){
	 this.sendToServer('allhandsmeeting/getallhandsschedule', data, callback);
} 
 RequestManager.prototype.getAlltemplates=function(data,callback){
	 this.sendToServer('template/getalltemplates', data, callback);
} 
 RequestManager.prototype.getPaginatedFilterEmployees=function(data,callback){
	 this.sendToServer('employee/getFilterEmployeesListPaginated', data, callback);
} 
 RequestManager.prototype.getPaginatedSearchedEmployees=function(data,callback){
	 this.sendToServer('employee/getSearchedEmployeesListPaginated', data, callback);
} 
 RequestManager.prototype.getAllProfilePics=function(data,callback){
	 this.sendToServer('employee/getProfilePics', data, callback);
} 
 RequestManager.prototype.saveLastWorkingDay=function(data,callback){
	 this.sendToServer('employee/SaveLastWorkingDayOfEmployee', data, callback);
} 
 RequestManager.prototype.editDesignation=function(data,callback){
	 this.sendToServer('designation_history/upadateDesignation', data, callback);
} 
 RequestManager.prototype.getDesignationById=function(data,callback){
	 this.sendToServer('designation_history/getDesignationById', data, callback);
} 
 RequestManager.prototype.deleteDesignationById=function(data,callback){
	 this.sendToServer('designation_history/deleteDesignationById', data, callback);
} 

var RequestManager = new RequestManager();