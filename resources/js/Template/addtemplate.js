 function AddTemplate() {
  Loader.loadHTML('.container', 'resources/js/Template/addTemplate.html', true, function(){
  this.handleShow();
 }.ctx(this));
}
AddTemplate.prototype.handleShow = function() {
 
 $('.container').show();

 $('#editor1').ckeditor({
  filebrowserImageUploadUrl :'/UploadServletForCKEditor',
  filebrowserUploadUrl : '/UploadServletForCKEditor'
 });
 $('.save').click(function(){
  
  var input={"payload":{}};
  RequestManager.getTemplates(input, function(data, success){
   var templates=data;
   this.duplicatevalidation(templates);
  }.ctx(this));
  
 
 
 }.ctx(this));
 
 $('#back').click(function(){
		
		routie("template");
	}.ctx(this));
 
}


AddTemplate.prototype.addTem=function(){
       
 var articleDesc = $('textarea#editor1').val();
   
  var input = {"payload":{"fileId":parseInt($('#fileId').text()),
        "name":$('.templatename').val(),
        "subject":$('.subject').val(),
        "content":articleDesc
        }};
 
 

RequestManager.addTemplate(input, function(data, success) {
 if(success){
  alert("Template Successfully added");
  $('.templatename').val("");
  $('.subject').val("");
  $('.content').val("");
  parseInt($('#fileId').text(""));
 routie("template");
 }else if(data.code==9017){
 
  alert(data.message);
  
 }else if(data.code==9018){
  alert(data.message);
 }
 
 else{
  
  alert("Failed to add");
 }
}.ctx(this));
  
}

AddTemplate.prototype.duplicatevalidation=function(templates){ 
 /*
   var char = /^[A-Za-z0-9_]+( [A-Za-z]+)*$/;
   var char1 = /^[A-Za-z0-9.,"_]+( [A-Za-z]+)*$/;*/
 var name = $('.templatename').val();

 var articleDesc = $('textarea#editor1').val();
 
if($('.templatename').val()=="" && $('.subject').val()=="" && articleDesc==""){ 
 
      alert("Please enter all the mandatory fields");
        
     }
else if($('.templatename').val()==""){                          
 
      alert("Please enter Template name");
   }
else if($('.subject').val()==""){
 
      alert("Please enter Subject");
  }
else if (articleDesc==""){
 
  alert("Please enter Description");
  
   }
     /* else if(! $('.templatename').val().match(char)){
       
         alert("Template name can contain alphabets,numbers and singlespace only");
    
    }
      else if(! $('.subject').val().match(char1)){
     
          alert('Subject can contain  alphabets,numbers and singlespace only');
    }*/
    
 
else{ 
 
  for(i=0;i<templates.length;i++){
  
         if(templates[i].name.replace(/\s/g, '').toLowerCase()==($('.templatename').val().replace(/\s/g, '').toLowerCase()))
       {
             alert("Template already exists");
              return false;
        }
   }
     this.addTem();
  
 
  }

}