function empBasicDetails() { 
 Loader.loadHTML('.container', 'resources/js/EmployeeHomepage/homePage.html', true, function(){
  this.handleShow();
 }.ctx(this));
 
}

 empBasicDetails.prototype.handleShow= function(){
	 $('#imagesinhome,.images,.galleria').css("visibility","hidden");
	 
	 
	 // Gets all the profile pics and appends to images div.
	 
 var input={};
 
 RequestManager.getAllProfilePics(input,function(data,success){
	 
	 if(success){
		 for(i=0;i<data.length;i++){
			 var filePath=data[i];
			 $('.galleria').append('<img src="'+filePath+'"  class="images">');
		 }
		 
		 this.initializeGalleria();
	 }
	 
 }.ctx(this));
 

}
 
 // Method to Initialize the Galleria.
 
 empBasicDetails.prototype.initializeGalleria=function(){
	Galleria.loadTheme('resources/js/galleria/themes/classic/galleria.classic.min.js');
	Galleria.run('.galleria', {
		autoplay: 3000
	});
	$('#imagesinhome,.images,.galleria').css("visibility","visible");
 }

