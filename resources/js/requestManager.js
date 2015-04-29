function RequestManager() {
    this.apiPrefix = '/services/v1/';
}
;



RequestManager.prototype.sendToServer = function (api, data, callback, options) {
    var serviceURL = this.apiPrefix + api;
    $.ajax({
        url:serviceURL,
        contentType:'application/json',
        dataType:'json',
        type:'POST',
        success:function (data) {
           console.log(data)
        success:function(data) {
            console.log(data)
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
            console.log(xhr, error, exception);
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

RequestManager.prototype.save = function(data, callback) {
	  this.sendToServer('skills/save', data, callback);
	 }
	  
RequestManager.prototype.getFilterEmployee = function(data, callback) {
	  this.sendToServer('employee/getFilterEmployees', data, callback);
	 }
RequestManager.prototype.loadTest=function(data,callback){
	this.sendToServer('user/test', data, callback);
}  

RequestManager.prototype.getTemplateById = function(data, callback) {
   this.sendToServer('template/viewTemplate', data, callback);
  }
RequestManager.prototype.loadTest=function(data,callback){
	this.sendToServer('user/test', data, callback);
}
RequestManager.prototype.authenticate=function(data,callback){
	this.sendToServer('employee/authenticate', data, callback);
}

RequestManager.prototype.getTemplateById=function(data,callback){
	this.sendToServer('template/viewTemplate', data, callback);
}

RequestManager.prototype.holidaysData=function(data,callback){
	this.sendToServer('holiday/save', data, callback);
}

RequestManager.prototype.getHolidaysData=function(data,callback){
	this.sendToServer('holiday/getHolidays', data, callback);
}

RequestManager.prototype.dynamicallyEdit=function(data,callback){
	this.sendToServer('holiday/updateHoliday', data, callback);
}

RequestManager.prototype.getHoliday=function(data,callback){
	this.sendToServer('holiday/getHolidayId', data, callback);
}

RequestManager.prototype.getEmployee=function(data,callback){
	this.sendToServer('employee/getEmployeeDetails', data, callback);
}
RequestManager.prototype.getSearchEmp =function(data,callback){
	this.sendToServer('employee/searchEmployee', data, callback);
}

RequestManager.prototype.getHrPolicy=function(data,callback){
	this.sendToServer('policy/getPolicy', data, callback);
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



RequestManager.prototype.getEmployees=function(data,callback){
	this.sendToServer('employee/getEmployees', data, callback);
}
RequestManager.prototype.updateEmp=function(data,callback){
	this.sendToServer('employee/updateEmployee', data, callback);
}
RequestManager.prototype.hrupdateEmp=function(data,callback){
	this.sendToServer('employee/hrupdateEmployee', data, callback);
}

RequestManager.prototype.saveDes=function(data,callback){
	this.sendToServer('designation_history/save', data, callback);
}
RequestManager.prototype.getDes=function(data,callback){
	this.sendToServer('designation_history/getDesignations', data, callback);
}
RequestManager.prototype.getDesName=function(data,callback){
	this.sendToServer('designation_history/getDesignationName', data, callback);
}
RequestManager.prototype.savePolicy=function(data,callback){
	this.sendToServer('hr_policy/save', data, callback);
}


RequestManager.prototype.getNotificationDisplayCriteria=function(data,callback){
	this.sendToServer('employee/getNotificationDisplayCriteria', data, callback);
}
RequestManager.prototype.addTemplate = function(data, callback) {
	 this.sendToServer('template/save', data, callback);
	}
RequestManager.prototype.viewEmployeedatails = function(data, callback) {
	 this.sendToServer('employee/viewEmployee', data, callback);
	}
RequestManager.prototype.getTemplates = function(data, callback) {
	 this.sendToServer('template/gatTemplate', data, callback);
	}


RequestManager.prototype.getSentManualMail=function(data,callback){
	this.sendToServer('SendNotificationHistory/sendMail', data, callback);
}



RequestManager.prototype.getSentMailContent=function(data,callback){
	this.sendToServer('employee/getSentMailContent', data, callback);
}
RequestManager.prototype.getAllEvents=function(data,callback){
	this.sendToServer('employee/getAllEvents', data, callback);
}
var RequestManager = new RequestManager();