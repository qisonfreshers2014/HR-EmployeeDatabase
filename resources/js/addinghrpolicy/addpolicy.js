function addpolicy() {
	Loader.loadHTML('.leftContainer', 'resources/js/addinghrpolicy/addpolicy.html', true, function(){
		this.handleShow();
	}.ctx(this));
}
addpolicy.prototype.handleShow = function() {
	$('.container').show();
	$('#savePolicy').click(function(){
		this.validatePolicyInfo();
		this.sendJson();
	}.ctx(this));

}
addpolicy.prototype.validatePolicyInfo = function()
{
	
	//policy name validations		
		//this.policyNameVal();
	//file validations		
		//this.fileValidate();

}

addpolicy.prototype.sendJson = function() 
{
/*
	var fileName1= x.replace(/^.*[\\\/]/, '');//including type....
	var filepath1=document.getElementById('policyName').value;
	var filetype1= document.getElementById('policyName').value;*/
	//policies 
	
	var policyName1=('#policyName').val();
	var filePath=('#file').val();
	//var fileID=.........from the FILE pojo
	
	
	var input = {
		"payload": {
			"fileName": policyName1,
			"file": fileID
		}
	};

	RequestManager.savePolicy(input, function(data, success) {
	if (success) {
		alert("Successfully added");
	} else {
		alert("Failed to add");
	}
	}.ctx(this));
}
