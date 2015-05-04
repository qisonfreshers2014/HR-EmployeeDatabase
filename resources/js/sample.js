function Sample() {
	Loader.loadHTML('.container','sample.html', true, function(){
		this.handleShow();
	}.ctx(this));
}

Sample.prototype.handleShow = function() {
	/*var input = {"payload":{}};
	RequestManager.getEmployees(input, function(data, success) {
		if(success){
			console.dir(data);
			$(".leftContainer").show();
			$(".leftContainer").css('background-color', 'aqua');

			$(".rightContainer").show();
			$(".rightContainer").css('background-color', 'green');
			$(".leftContainer").append('<p>'+data+ '</p>');
			$(".rightContainer").append('<p>'+data+ '</p>');
		}else{
			alert("failed");
		}*/
	$('.container').show();
	$('.testService').click(function(){
	this.testService();
	}.ctx(this));
	
	
function UploadClickHandler(event){
var thisEle = event.target;
this.uploadMedia(function() {
$(thisEle).one('click', UploadClickHandler.ctx(this));
}.ctx(this));
}
//$('.testService').one('click', UploadClickHandler.ctx(this));
	

}

Sample.prototype.testService = function() {


	App.loadDesignation();
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
	
	}
	
	);

//App.loadLogin();
//App.loadFilter();
//  App.loadSkill();
 

/*var dataId = 3;	*/

//var dataId = 3;	

	//App.loadLogin();
	//App.loadEmpl();
	//App.loadEditEmp(dataId);
	//App.loadhrEditEmp(dataId);
	//App.loadtemplateList(); 

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
