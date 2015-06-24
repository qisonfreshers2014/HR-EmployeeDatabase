function employeePolicyList() {
	Loader.loadHTML('.container', 'resources/js/addinghrpolicy/policy.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

employeePolicyList.prototype.handleShow = function() {

	$('.container').show();
	$('#addPolicy').css("visibility","hidden");
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

employeePolicyList.prototype.policyDisplay = function(content, status) {
	if(status){
		var j=0;
		for(var i=0;i<content.length;i++){
			
			var obj = content[i];
			
			$("#policylist").append('<table><tbody></tbody></table>');
			$("#policylist tr:last").after('<tr><td style="font-size:14px">'+ ++j +'</td><td style="font-size:14px"><a href="'+obj.url+'" target="_blank">'+obj.policyName+'</a></td></tr>');
						
		}
	}else{
		
		alert("No policies found");
	}
	
	
	
}


var employeePolicyList = new employeePolicyList();