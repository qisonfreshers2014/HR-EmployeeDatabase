function Template(dataid) {	
	Loader.loadHTML('.container', 'resources/js/Templates/template.html', true, function(){
		this.handleShow(dataid);
	}.ctx(this));
}

Template.prototype.handleShow = function(dataid) {	
	
	$('#backButton').click(function(){
		 routie("template");
		 }.ctx(this));
	

	 $('textarea#contentTemplate').ckeditor({
	  filebrowserImageUploadUrl :'/UploadServletForCKEditor',
	  filebrowserUploadUrl : '/UploadServletForCKEditor'
	 });
	 $('textarea#contentTemplate').ckeditor(function(){
			
			this.viewTemplate(dataid);
	     }.ctx(this));
	
}

Template.prototype.viewTemplate = function(dataid) {	
	var input = {"payload":{"id":dataid}};
	RequestManager.getTemplateById(input, function(data, success) {

		if(success){
			 var obj=data[0];
			 
			 $('.templateName1').val(obj.name);
			 $('.templateSubject1').val(obj.subject);
			 $('textarea#contentTemplate').val(obj.content);
		  }
		 }.ctx(this));
}

var Template=new Template(dataid);