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

RequestManager.prototype.holidaysData=function(data,callback){
	this.sendToServer('holidays/save', data, callback);
}

RequestManager.prototype.getHolidaysData=function(data,callback){
	this.sendToServer('holidays/getHolidays', data, callback);
}

RequestManager.prototype.dynamicallyEdit=function(data,callback){
	this.sendToServer('holidays/editHoliday', data, callback);
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
var RequestManager = new RequestManager();