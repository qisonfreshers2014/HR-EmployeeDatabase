function Template() {	
	Loader.loadHTML('.container', 'resources/js/Templates/template.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

Template.prototype.handleShow = function() {		
	var input = {"payload":{"id":15}};
	RequestManager.getTemplateById(input, function(data, success) {

		if(success){
			
			alert("hhjbb");
			 var obj=data[0];
			 
			/* $('.name').text(obj.name);
			 $('.subject').val(obj.name);
			 $('.content').val(obj.name);*/
		  }
		 }.ctx(this));
}

var Template=new Template();