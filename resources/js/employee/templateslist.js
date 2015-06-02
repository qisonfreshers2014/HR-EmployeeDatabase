function TemplateList() {
	Loader.loadHTML(".container", "resources/js/employee/templateslist.html", true, function() {
				this.handleShow();
			}.ctx(this));
}
TemplateList.prototype.handleShow = function() {

	
	
	$("#btnadd").click(function(){ 
		
		routie("addtemplate");
		
	}.ctx(this));
	 
	this.getTemplateName();

}

TemplateList.prototype.getTemplateName=function(){
	var input = {"payload":{}};
	 RequestManager.getTemplatename(input, function(data, success) {
		  if(success){
			  $.each(data,function(obj, value){
					//  var obj = data[i];

						$('#displayData').append('<table><tbody></tbody></table>');
						$('#displayData tr:last').after("<tr class='tr'><td>"+value.name+"</td><td><br><input type='button' value='Edit' id='"+value.id+"' class='dynamicEdit btn btn-primary'></td><td><br><input type='button' value='View' id='"+value.id+"' class='dynamicView btn btn-primary'></td></tr></table>");
			  });	
		$('.dynamicEdit').click(function(event){
			//console.log(event);
			dataid = event.target.id;
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
	App.loadViewTemplate(dataid);

}
TemplateList.prototype.viewTemplate=function(dataid){
	App.loadTemplate(dataid);

}
//var TemplateList = new TemplateList();
