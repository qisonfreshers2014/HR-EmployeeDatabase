function AddTemplate() {
		Loader.loadHTML('.container', 'resources/js/Template/AddTemplate.html', true, function(){
		this.handleShow();
	}.ctx(this));
}
AddTemplate.prototype.handleShow = function() {
	
	$('.container').show();
	//new nicEditor({fullPanel : true}).panelInstance("editor1");
	$('#editor1').ckeditor({
		filebrowserImageUploadUrl : '/UploadServletForCKEditor',
		filebrowserUploadUrl : '/UploadServletForCKEditor'
	});
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
	      
	var articleDesc = $('textarea#editor1').val();
      
	 var input = {"payload":{"fileId":parseInt($('#fileId').text()),
	       "name":$('.templatename').val(),
	       "subject":$('.subject').val(),
	       "content":articleDesc
	       }};
	
	

RequestManager.addTemplate(input, function(data, success) {
	if(success){
		alert("successfully added");
		//var content = data;
		
		//$( "input#clear" ).trigger( "click" );
	}else{
		
		alert("failed to add");
	}
}.ctx(this));
	 
}

AddTemplate.prototype.duplicatevalidation=function(templates){ 
	
	var articleDesc = $('textarea#editor1').val();
	
	
	
    if($('.templatename').val()==""||$('.subject').val()==""||articleDesc==""){
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


