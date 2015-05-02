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



RequestManager.prototype.geteditEmployee=function(data,callback){
	this.sendToServer('employee/getEmployee', data, callback);
}

RequestManager.prototype.getAllHrPolicy=function(data,callback){
	this.sendToServer('hr_policy/getPolicy', data, callback);
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

var RequestManager = new RequestManager();