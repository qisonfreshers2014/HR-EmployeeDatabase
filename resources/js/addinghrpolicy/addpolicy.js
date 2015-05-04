var fileID;
var nameValidate = 0;
var fileValidate = 0;

function addpolicy() {
Loader.loadHTML('.container','resources/js/addinghrpolicy/addpolicy.html', true, function() {
				this.handleShow();
			}.ctx(this));
}

addpolicy.prototype.handleShow = function() {
	$('.container').show();
	$('#fileUpload').one('click', UploadClickHandler.ctx(this));
	$('#savePolicy').click(function() {
		this.validatePolicyInfo();
		if (nameValidate == 1 && fileValidate == 1) {
			this.saveToDatabase();
		}
	}.ctx(this));
	$('#reset_policy').click(function(){
			$('#policyName').val("");			
	});
	function UploadClickHandler(event) {
		var thisEle = event.target;
		this.uploadMedia(function() {
			$(thisEle).one('click', UploadClickHandler.ctx(this));
		}.ctx(this));
	}

}

addpolicy.prototype.uploadMedia = function(callback) {
	var allowedFileType = "application";
	var uploader = new Uploader(allowedFileType, function(data) {
		if (data.filePath) {
			var imageSrc = data.filePath;
			this.fileId = data.id;
			fileID = this.fileId;
		}
	}.ctx(this));
	callback();

}

addpolicy.prototype.validatePolicyInfo = function() {
	this.policyNameVal();
	this.fileValidate();
}

// policy name validations
addpolicy.prototype.policyNameVal = function() {

	var policyName = $('#policyName').val();
	var lengthx = policyName.length;
	if (policyName == "") {
		$('#isPEmpty').text("You can't leave policy name empty.");
		nameValidate = 0;
		return false;

	} else {
		$('#isPEmpty').text("");
		nameValidate = 1;
	}

	if (lengthx > 20) {
		$('#Plen').text("Are you sure you entered policy name correctly?");
		nameValidate = 0;
		return false;
	} else {
		$('#Plen').text("");
		nameValidate = 1;
	}
	var letters = /^[A-z0-9 ]+$/;
	if (!(policyName.match(letters))) {
		$('#Pletter').text("Policy name can have alphabet,numbers and spaces only");
		nameValidate = 0;
		return false;

	} else {
		$('#Pletter').text("");
		nameValidate = 1;

	}

}

// file validation
addpolicy.prototype.fileValidate = function() {
	var x = fileID;

	if (x == "" || x == undefined) {
		$('#isFEmpty').text("Upload a file");
		fileValidate = 0;
		return false;

	} else {
		$('#isFEmpty').text("");
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
		
			alert("Successfully added");
		} else {
			alert("Failed to add");
		}
	}.ctx(this));
}
