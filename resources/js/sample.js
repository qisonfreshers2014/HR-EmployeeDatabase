function Sample() {

	Loader.loadHTML('.container','sample.html', true, function(){

		this.handleShow();
	}.ctx(this));
}

Sample.prototype.handleShow = function() {
	$('.container').show();
	$('.testService').click(function(){
        this.testService();
	}.ctx(this));

	
}
function UploadClickHandler(event){

var thisEle = event.target;
this.uploadMedia(function() {
$(thisEle).one('click', UploadClickHandler.ctx(this));
}.ctx(this));
}
$('.testService').one('click', UploadClickHandler.ctx(this));
	

}

Sample.prototype.testService = function() {

	
	App.loadHoliday();
	
	//App.loadEmployeeHoliday();
	
	//App.listEmployee();
	
	//App.listPolicy();

	//App.loadAddTemplate();
	// App.loadViewEmployee();
	 //App.loadempviewemployee();
	 
	 //App.loadDes();
	 //App.loadPolicy();


	//App.loadDesignation();
	//App.loadPolicy();

	/*var dataid="14";
App.loadViewTemplate(dataid);*/

	//App.loadLogin();
//App.loadAllHandsMeeting();
/*var name="vasavi";
App.loadHRHomeHeader(name);
App.loadHRHomePage();
App.loadHRHomeFooter();*/

	var input={};
	RequestManager.getAllEvents(input, function(data, success) {
		if (success) {		
		
							App.loadNotificationHomePage(data);
				}
	else
		{
		App.loadNotificationHomePage(data);
		alert("No Data Found");
				}

	
      App.loadAddTemplate();
	//App.loadViewEmployee();
	//App.loadempviewemployee();

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
	//);

//App.loadLogin();
//App.loadFilter();
//  App.loadSkill();
 

/*var dataId = 3;	*/

//var dataId = 3;	


	//App.loadLogin();


}
 

/*Sample.prototype.uploadMedia = function(callback) {
var allowedFileType = "image";
var uploader = new Uploader(allowedFileType, function(data){
if (data.filePath) {
var imageSrc = data.filePath;
this.fileId = data.id;
$('.mediaForProfileImage').attr('src', imageSrc);
if(imageSrc != null){
//$('.mediaForProfileImage').lightBox();
}
}
}.ctx(this));
callback();

}

<<<<<<< HEAD
=======
*/
var Sample= new Sample();
