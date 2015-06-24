function TemplateList(data) {
	Loader.loadHTML(".container", "resources/js/employee/templateslist.html", true, function() {
				this.handleShow(data);
			}.ctx(this));
}
TemplateList.prototype.handleShow = function(data) {

	$('#template').parent().addClass('active');
	
	$("#btnaddtemp").click(function(){ 
		
		routie("addtemplate");
		
	}.ctx(this));
	
var self=this;
	
	var pageNo=1;
	
	 $('.selector').pagination(
		  		{
		      items:data.count,
		      itemsOnPage:10,
		      cssStyle: 'light-theme',
		      	
		  	  onPageClick: function(pageNumber) { 
		  		  
		  		self.getTemplateName(pageNumber);
		           
		           
		       }
		  });

	 
	this.getTemplateName(pageNo);

}

TemplateList.prototype.getTemplateName=function(pageNo){
	 var contentinput = {"payload":{"pageNo":pageNo,"pageSize":10}};
	 RequestManager.getAlltemplates(contentinput, function(data, success) {
		  if(success){
			  
			  
			  $('#displayDatatemplist').empty();
				$('table').append('<tr class="templisttr"><th class="temlistth">Template Name</th><th class="temlistth">Edit</th><th class="theader">View</th></tr>')
			  
			var content=data.alltemplates;
			  
			  $.each(content,function(obj, value){
					//  var obj = data[i];

						$('#displayDatatemplist').append('<table><tbody></tbody></table>');
						$('#displayDatatemplist tr:last').after("<tr class='templisttr'><td class='templisttd'>"+value.name+"</td><td class='templisttd'><br><a href='#edittemplate'><input type='button' value='Edit' id='"+value.id+"' class='dynamicEdit btn btn-info'></a></td><td class='templisttd'><br><input type='button' value='View' id='"+value.id+"' class='dynamicView btn btn-success'></td></tr></table>");
			  });	
			  
			  
 $(function(){
				  
				  var perPage = 10;
					
				 
				  var checkFragment = function() {
				 
				      var hash = window.location.hash;
			
				      hash = hash.match(/^#page-(\d+)$/);
				      if(hash)
				        
				          $("#pagination").pagination("selectPage", parseInt(hash[1]));
				  };
				
				  $(window).bind("popstate", checkFragment);
		
				  checkFragment();
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
