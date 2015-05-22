function addpolicy() {
Loader.loadHTML('.container','resources/js/addinghrpolicy/addpolicy.html', true, function() {
				this.handleShow();
			}.ctx(this));
}
var fileID;
var nameValidate = 0;
var fileValidate = 0;
addpolicy.prototype.handleShow = function() {
	$('.container').show();
	$('#fileUpload').one('click', UploadClickHandler.ctx(this));
	$('#savePolicy').click(function() {
		this.validatePolicyInfo();
		if (nameValidate == 1 && fileValidate == 1) {
			this.saveToDatabase();
		}
	}.ctx(this));
	function UploadClickHandler(event) {
		var thisEle = event.target;
		this.uploadMedia(function() {
			$(thisEle).one('click', UploadClickHandler.ctx(this));
		}.ctx(this));
	}	
	
	 $('#back').click(function(){
			
		 App.listPolicy();
		}.ctx(this));
}

addpolicy.prototype.uploadMedia = function(callback) {
	var allowedFileType = "application";
	var uploader = new Uploader(allowedFileType, function(data) {
		if (data.filePath) {
			var imageSrc = data.filePath;
			this.fileId = data.id;
			fileID = this.fileId;
			var index = imageSrc.lastIndexOf("/") + 1;
			var filename = imageSrc.substr(index);
			$('.policyFileName').text(filename);
		}
	}.ctx(this));
	callback();

}

addpolicy.prototype.validatePolicyInfo = function()
{
// policy name validations
var char = /^[A-Za-z0-9_ ]+( [A-Za-z0-9_ ]+)*$/;
	var policyName = $('#policyName').val();
    var x=fileID;
	if ((policyName == "") &&(x == ""||x==null||x==" ")) 
	{
		alert("Please enter all the mandatory fields");
		nameValidate = 0;
		fileValidate = 0;
		return false;

	}
	if (policyName == "") 
	{
		alert("Please enter policy name");
		nameValidate = 0;
		return false;

	} else {
		nameValidate = 1;
	}
	if (!(policyName.match(char))) {
		alert("Policy name contains alphabet,numbers, _ and single space only");
		nameValidate = 0;
		return false;
	} else {
		nameValidate = 1;

	}
// file validation


	if (x == ""||x==null||x==" ") {
		alert("Please upload a file");
		fileValidate = 0;
		return false;

	} else {
		fileValidate = 1;
	}

}

addpolicy.prototype.saveToDatabase = function() {
	var policyName1 = $('#policyName').val();
	console.log("POLICY NAME IS" + policyName1);
	console.log("fileID is" + fileID);
//preparing payload for input to save into the database	
	var input = {
		"payload" : {
			"fileId" : fileID,
			"policyName" : policyName1
		}
	};

	RequestManager.savePolicy(input, function(data, success) {
		if (success) {
			$('#policyName').val("");
			fileID="";
			$('.policyFileName').val("");
			alert("Successfully saved the policy");
			App.listPolicy();
		} else {
		 if (data.code == 228) {
				alert("Policy name already exists");

			}
		 else{
			alert("Failed to add");
		 }
		}
	}.ctx(this));
}