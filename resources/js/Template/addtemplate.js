function AddTemplate() {
		Loader.loadHTML('.container', 'resources/js/Template/addTemplate.html', true, function(){
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
	$('.save').click(function(){
		
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
	}else if(data.code==9017){
	
		alert(data.message);
		
	}else if(data.code==9018){
		alert(data.message);
	}
	
	else{
		
		alert("failed to add");
	}
}.ctx(this));
	 
}

AddTemplate.prototype.duplicatevalidation=function(templates){ 
	
   var char = /^[A-Za-z]+( [A-Za-z]+)*$/;
	var name = $('.templatename').val();

	var articleDesc = $('textarea#editor1').val();
	
	
	
    if( $('.templatename').val()==""||$('.subject').val()==""||articleDesc==""){
    	alert("failed to add,since every field is mandatory");
    }
    
    else if(! $('.templatename').val().match(char)){
    	alert("name should contain alphabets,spaces only and length should not be more than 30 characters")
    }
    else if(! $('.subject').val().match(char)){
    	alert("subject should contain only alphabets,spaces and length should not be more than 50 characters")
    }
    
  else {
	
	 
	for(i=0;i<templates.length;i++){
		
		if(templates[i].name.replace(/\s/g, '').toLowerCase()==($('.templatename').val().replace(/\s/g, '').toLowerCase()))
		{
			alert("Template already exist");
			return false;
		}}
		
			this.addTem();
		
	
  }
}


