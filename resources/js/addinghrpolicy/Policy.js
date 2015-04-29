function policyList() {
	Loader.loadHTML('.leftContainer', 'resources/js/addinghrpolicy/Policy.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

policyList.prototype.handleShow = function() {

	$('.container').show();
	var contentinput = {"payload":{} };
	RequestManager.getAllHrPolicy(contentinput, function(data, success) {
		 if(success){
			 var content = data;
			 var status = success;
			 this.policyDisplay(content, status);
			alert("successfully added");
		 	
		}else{
		  
		  alert("failed to add");
		 }		
		  
		}.ctx(this));
	
}

policyList.prototype.policyDisplay = function(content, status) {
	if(status){
		for(var i=0;i<content.length;i++){
			
			var obj = content[i];
			var path="file:///"+obj.fileURL;
			$("#policylist").append('<a href="http://localhost:9966/downloadServlet?fileId='+obj.fileID+'">'+obj.policyName+"</a><br>");
			
			
			
		}
	}else{
		
		alert("No policies found");
	}
	
	
	
}


