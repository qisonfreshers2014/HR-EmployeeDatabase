function EditTemplate() {
	Loader.loadHTML('.leftContainer', 'resources/js/HRhome/EditTemplate.html', true, function(){
		this.handleShow();
	}.ctx(this));
}
EditTemplate.prototype.handleShow=function(){
	$('.container').show();
	/*$('.submit').click(function(){
		
		this.EditTemplateDetails();
	}.ctx(this));

}*/

/*FilterEmp.prototype.handleShow = function() {
	 $('.container').show();
	 $('#Filter').click(function(){
	  event.preventDefault();
	  this.FilterEmployee();
	  //this.Filterdata();
	 }.ctx(this));
	 
	}*/

/*EditTemplate.prototype.editTem=function(){
	
        if($('.templatename').val()==""||$('.subject').val()==""||$('.content').val()==""){
        	alert("failed to add,since every field is mandatory");
        }
        else{
	 var input = {"payload":{"fileId":"17",
	       "name":$('.templatename').val(),
	       "subject":$('.subject').val(),
	       "content":$('.content').val(),
	       }};
	*/
EditTemplate.prototype.EditTemplateDetails=function(){

	 var input = {"payload":{"id":12}};
	 RequestManager.getTemplateById(input, function(data, success) {
		
	
		   if(success){
			   
				   var obj=data[0];
				   
				   
			   $('.templatename').val(obj.name);
			   $('.subject').val(obj.subject);
			   $('.content').val(obj.content);
		    //$( "input#clear" ).trigger( "click" );
		   }else{
		    alert("failed to edit");
		   }

}.ctx(this));
	 
}


