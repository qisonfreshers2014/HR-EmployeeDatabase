function Template(dataid) {	
	Loader.loadHTML('.container', 'resources/js/Templates/template.html', true, function(){
		this.handleShow(dataid);
	}.ctx(this));
}

Template.prototype.handleShow = function(dataid) {	
	
	$('#backButton').click(function(){
		 routie("template");
		 }.ctx(this));
	
	var input = {"payload":{"id":dataid}};
	RequestManager.getTemplateById(input, function(data, success) {

		if(success){
			 var obj=data[0];
			 
			 $('.templateName1').val(obj.name);
			 $('.templateSubject1').val(obj.subject);
			 $('.templateContent1').prepend(obj.content);
		  }
		 }.ctx(this));
}

var Template=new Template(dataid);