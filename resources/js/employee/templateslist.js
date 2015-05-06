function TemplateList() {
	Loader.loadHTML(".container", "resources/js/employee/templateslist.html", true, function() {
				this.handleShow();
			}.ctx(this));
}
TemplateList.prototype.handleShow = function() {

	
	$("#add").click(function(){ 
		
		App.loadAddTemplate();
		
	}.ctx(this));
	 
	this.getTemplateName();

}

TemplateList.prototype.getTemplateName=function(){
	var input = {"payload":{}};
	 RequestManager.getTemplatename(input, function(data, success) {
		  if(success){
			  $.each(data,function(obj, value){
					//  var obj = data[i];

						$('#displayData').append('<table></table>');
						$('#displayData ').after("<tr><td>"+value.name+"</td><td><input type='button' value='Edit' id='"+value.id+"' class='dynamicEdit'></td><td><input type='button' value='View' id='"+value.id+"' class='dynamicView'></td></tr>");
			  });	
		$('.dynamicEdit').click(function(event){
			//console.log(event);
			dataid = event.target.id;
			alert(dataid);
			console.log(dataid);
			this.editTemplate(dataid);
			
		}.ctx(this));
		
		$('.dynamicView').click(function(event){
			//console.log(event);
			dataid = event.target.id;
			this.viewTemplate(dataid);

		}.ctx(this));
		 
		
} 
		  else {
			  alert("failed");
		  }
	 }.ctx(this));
	 
}
TemplateList.prototype.editTemplate=function(dataid){
	alert(dataid);
	
	App.loadViewTemplate(dataid);

}
TemplateList.prototype.viewTemplate=function(dataid){
	App.loadTemplate(dataid);

}
//var TemplateList = new TemplateList();
