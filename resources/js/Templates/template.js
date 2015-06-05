function Template(dataid) {	
	Loader.loadHTML('.container', 'resources/js/Templates/template.html', true, function(){
		this.handleShow(dataid);
	}.ctx(this));
}

Template.prototype.handleShow = function(dataid) {	
	
	$('#backButton').click(function(){
		  App.loadtemplateList();
		 }.ctx(this));
	
	var input = {"payload":{"id":dataid}};
	RequestManager.getTemplateById(input, function(data, success) {

		if(success){
			 var obj=data[0];
			 
			 $('.templateName1').prepend(obj.name);
			 $('.templateSubject1').prepend(obj.subject);
			 $('.templateContent1').prepend(obj.content);
		  }
		 }.ctx(this));
}

var Template=new Template(dataid);