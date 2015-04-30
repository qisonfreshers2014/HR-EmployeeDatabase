function EditTemplate(dataid) {
	Loader.loadHTML('.leftcontainer1', 'resources/js/Template/EditTemplate.html', true, function(){
		this.handleShow(dataid);
}.ctx(this));
}
EditTemplate.prototype.handleShow=function(dataid){
	$('.container').show();
	
	$('textarea#templateContent').ckeditor(function(){
		
		this.EditTemplateDetails(dataid);
     }.ctx(this));
		 $('#updtae').click(function(){
			// alert(11);
			   this.editTemplateDetailsById(dataid);

}.ctx(this));
	 
}


EditTemplate.prototype.EditTemplateDetails=function(dataid){
//alert(1);
	 var input = {"payload":{"id":dataid}};
	 RequestManager.getTemplateById(input, function(data, success) {
		//alert(2);
		// var articleDesc = CKEDITOR.instances.editor1.getData();
	
		   if(success){
			//   alert(3);
			   //alert("sucess");
				var obj=data[0];
				   //alert(obj.id);
				   
			   $('.templatename').val(obj.name);
			   $('.subject').val(obj.subject);
			  
			//CKEDITOR.instances.templateContent.setData(obj.content);
			  // console.log(obj.content);
			   $('textarea#templateContent').val(obj.content);
			   
			  // console.log('value set\n=================================================\n'+$('#templateContent').val());
			
		 		
		   }
		   else{
		    alert("Failed to Edit");
		   }

}.ctx(this));
	 
}
EditTemplate.prototype.editTemplateDetailsById=function(dataid){
//	alert(4);
	var articleDesc = $('textarea#templateContent').val();//CKEDITOR.instances.templateContent.getData();
    var input ={"payload":{"id":dataid,"name":$('.templatename').val(),"subject":$('.subject').val(),"content":articleDesc}};
    RequestManager.editTemplateDetails(input, function(data, success)
    {
    	//alert(5);
        if(success){
         alert("Successfully Inserted");
        }
        else{
         alert("Failed to Add");
        }
    }.ctx(this));
}


