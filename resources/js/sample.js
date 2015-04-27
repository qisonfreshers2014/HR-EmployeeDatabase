function Sample() {
	Loader.loadHTML('.leftContainer', 'sample.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

Sample.prototype.handleShow = function() {
	$('.container').show();
	$('.testService').click(function(){
		//this.testService();
	}.ctx(this));
	
	
	function UploadClickHandler(event){
var thisEle = event.target;
this.uploadMedia(function() {
$(thisEle).one('click', UploadClickHandler.ctx(this));
}.ctx(this));
}
$('.testService').one('click', UploadClickHandler.ctx(this));
	

}

Sample.prototype.testService = function() {
	
	
	//App.loadDes();
	//App.loadPolicy();
/*	var input = {"payload":{}};
	RequestManager.loadTest(input, function(data, success) {
		if(success){
			alert(data);
			$(".leftContainer").show();
			$(".leftContainer").css('background-color', 'aqua');

			$(".rightContainer").show();
			$(".rightContainer").css('background-color', 'green');
			$(".leftContainer").append('<p>'+data+ '</p>');
			$(".rightContainer").append('<p>'+data+ '</p>');
		}else{
			alert("failed");
		}
	}.ctx(this));*/

	App.loadLogin();


}
 

Sample.prototype.uploadMedia = function(callback) {
var allowedFileType = "application";
var uploader = new Uploader(allowedFileType, function(data){
if (data.filePath) {
var imageSrc = data.filePath;
this.fileId = data.id;
$('.mediaForProfileImage').attr('src', imageSrc);
if(imageSrc != null){
$('.mediaForProfileImage').lightBox();
}
}
}.ctx(this));
callback();

}


var Sample= new Sample();
