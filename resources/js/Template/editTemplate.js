function editTemplate(dataid) {
	Loader.loadHTML('.container', 'resources/js/Template/editTemplate.html', true, function(){
		this.handleShow(dataid);
}.ctx(this));
}
editTemplate.prototype.handleShow=function(dataid){
	$('.container').show();
	
	
	$('textarea#templateContent').ckeditor({
		  filebrowserImageUploadUrl :'/UploadServletForCKEditor',
		  filebrowserUploadUrl : '/UploadServletForCKEditor'
		 });
	$('textarea#templateContent').ckeditor(function(){
		
		this.EditTemplateDetails(dataid);
     }.ctx(this));
		 $('#updtae').click(function(){
			// alert(11);
			   this.editTemplateDetailsById(dataid);

}.ctx(this));
		 
 $('#back').click(function(){
				
	  App.loadtemplateList();
			}.ctx(this));
	 
}


editTemplate.prototype.EditTemplateDetails=function(dataid){

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
editTemplate.prototype.editTemplateDetailsById=function(dataid){
//	alert(4);
	var articleDesc = $('textarea#templateContent').val();//CKEDITOR.instances.templateContent.getData();
	var regex = /^[A-Za-z]+( [A-Za-z]+)*$/;
	
	var subjectRegex=/^[A-Za-z0-9.,"]+( [A-Za-z]+)*$/;
	
	
	 if($('.templatename').val()==""){
		 alert("Please enter template name");
	 }else if($('.subject').val()==""){
		 alert("Please enter subject");
	 }
	 else if(articleDesc==""){
		 alert("Please enter description");
	 }	 
	 
	 
	 else if($('.templatename').val().charAt(0)==" "||$('.subject').val().charAt(0)==" "){
		 alert("Please don't enter space as first letter");
	 }
	 else if(!($('.templatename').val()).match(regex))
		 {
		 alert("Please enter only characters with one space for templatename");
		 }
	 	 
	 else if($('.templatename').val().length<2){
			 alert("Invalid length -minimum 2 characters needed!(Upto 40) ");
		 }
	 else if(!($('.subject').val()).match(subjectRegex)){
		 alert("Please enter only characters and numbers with one space for subject");
	 }
		 else{
    var input ={"payload":{"id":dataid,"name":$('.templatename').val(),"subject":$('.subject').val(),"content":articleDesc}};
    RequestManager.editTemplateDetails(input, function(data, success)
    {
    	//alert(5);
        if(success){
         alert("Template successfully updated");
         $('.templatename').val("");
         $('.subject').val("");
         $('textarea#templateContent').val("");
      routie("template");
        }else if(data.code==9018){
            
            alert("Template already exists");
            
           }

        else{
         alert("Template failed to update");
        }
    }.ctx(this));
		 }
}