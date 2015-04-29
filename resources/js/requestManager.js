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
        alert(preferences);
    }

}

RequestManager.prototype.loadTest=function(data,callback){
	this.sendToServer('user/test', data, callback);
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

var RequestManager = new RequestManager();