function policyList() {
	Loader.loadHTML('.container', 'resources/js/addinghrpolicy/policy.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

policyList.prototype.handleShow = function() {

	$('.container').show();
	
	$('#addPolicy').click(function(){
		  
		  App.loadPolicy();
		 }.ctx(this));
	
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
			var path="file:///"+obj.fileURL;
			//If the file upload folder is not in the server
			/*$("#policylist").append('<p><a href="http://localhost:9956/downloadServlet?fileId='+obj.fileID+'">'+obj.policyName+"</a></p><br>");*/
		
			$("#policylist").append('<table><tbody></tbody></table>');
		
			$("#policylist tr:last").after('<tr><td style="font-size:14px">'+ ++j +'</td><td style="font-size:14px"><a href="'+obj.url+'" target="_blank">'+obj.policyName+'</a></td></tr>');
			
		}
	}else{		
		alert("No policies found");
	}
	
	
	
}

