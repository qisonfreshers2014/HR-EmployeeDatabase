function policyList() {
	Loader.loadHTML('.container', 'resources/js/HRPolicy/policy.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

policyList.prototype.handleShow = function() {

	$('.container').show();
	var contentinput = {"payload":{} };
	RequestManager.getPolicy(contentinput, function(data, success) {
		 if(success){
			 var content = data;
			 var status = success;
			 this.policyDisplay(content, status);
			
		 	
		}else{
		  
		  alert("failed to add");
		 }		
		  
		}.ctx(this));
	
}

policyList.prototype.policyDisplay = function(content, status) {
	if(status){
		var j=0;
		for(var i=0;i<content.length;i++){
			
			var obj = content[i];
			
			$("#policylist").append(++j +" .<a href='"+obj.fileId+"'>"+obj.policyName+"</a><br>");
						
		}
	}else{
		
		alert("No policies found");
	}
	
	
	
}


var policyList = new policyList();