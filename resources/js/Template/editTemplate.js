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
	 
}


editTemplate.prototype.EditTemplateDetails=function(dataid){
alert(dataid);
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
		 alert("Please Enter Template Name");
	 }else if($('.subject').val()==""){
		 alert("Please Enter Subject");
	 }
	 else if(articleDesc==""){
		 alert("Please Enter Description");
	 }	 
	 
	 
	 else if($('.templatename').val().charAt(0)==" "||$('.subject').val().charAt(0)==" "){
		 alert("Please don't enter space as First letter");
	 }
	 else if(!($('.templatename').val()).match(regex))
		 {
		 alert("Please enter only characters with one space for templatename");
		 }
	 	 
	 else if($('.templatename').val().length<2){
			 alert("Invalid length -minimum 2 characters needed!(Upto 40) ");
		 }
	 else if(!($('.subject').val()).match(subjectRegex)){
		 alert("Please enter only Characters and Numbers with one space for subject");
	 }
		 else{
    var input ={"payload":{"id":dataid,"name":$('.templatename').val(),"subject":$('.subject').val(),"content":articleDesc}};
    RequestManager.editTemplateDetails(input, function(data, success)
    {
    	//alert(5);
        if(success){
         alert("Successfully Inserted");
         $('.templatename').val("");
         $('.subject').val("");
         $('textarea#templateContent').val("");
         App.loadtemplateList();
        }
        else{
         alert("Failed to Add");
        }
    }.ctx(this));
		 }
}
