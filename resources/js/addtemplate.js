function AddTemplate() {
		Loader.loadHTML('.leftContainer', 'resources/AddTemplate.html', true, function(){
		this.handleShow();
	}.ctx(this));
}
AddTemplate.prototype.handleShow = function() {
	
	$('.container').show();
	//bkLib.onDomLoaded(function() {

		//new nicEditor({fullPanel : true}).panelInstance('field');
		//});
	$('.submit').click(function(){
		var input={"payload":{}};
		RequestManager.getTemplates(input, function(data, success){
			var templates=data;
			this.duplicatevalidation(templates);
		}.ctx(this));
		
		//this.addTem();
	
	}.ctx(this));

}
AddTemplate.prototype.addTem=function(){
	      

      
	 var input = {"payload":{"fileId":"17",
	       "name":$('.templatename').val(),
	       "subject":$('.subject').val(),
	       "content":$('.content').val(),
	       }};
	
	

RequestManager.addTemplate(input, function(data, success) {
	if(success){
		alert("successfully added");
		var content = data;
		
		//$( "input#clear" ).trigger( "click" );
	}else{
		
		alert("failed to add");
	}
}.ctx(this));
	 
}

AddTemplate.prototype.duplicatevalidation=function(templates){ 
	
    if($('.templatename').val()==""||$('.subject').val()==""||$('.content').val()==""){
    	alert("failed to add,since every field is mandatory");
    }
    
 else{
	
	 
	for(i=0;i<templates.length;i++){
		
		if(templates[i].name.replace(/\s/g, '').toLowerCase()==($('.templatename').val().replace(/\s/g, '').toLowerCase()))
		{
			alert("Template already exist");
			return false;
		}}
		
			this.addTem();
		
	
  }
}


