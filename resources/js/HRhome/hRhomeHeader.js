function hRHomeHeader(name) {
	Loader.loadHTML('.header', 'resources/js/HRhome/hRhomeHeader.html', true, function(){
		this.handleShow(name);
	}.ctx(this));
}

hRHomeHeader.prototype.handleShow = function(name) {
	
	$('.empName').text(name);
	$('.container').show();

	
	/*$('#emp').click(function(){
		this.testEmployee();
				}.ctx(this));
	
	$('#holiday').click(function(){
		this.testHoliday();
				}.ctx(this));
	$('#hrpolicy').click(function(){
		this.testHRPolicy();
				}.ctx(this));*/
	$('#template').click(function(){
		this.testTemplate();
				}.ctx(this));
	
	/*$('#notifications').click(function(){
		this.testNotification();
				}.ctx(this));*/
	$('#allHandsMeeting').click(function(){
		this.testAllHandsMeeting();
				}.ctx(this));
	

}

/*HRHomeHeader.prototype.testEmployee = function() {
	App.loadAllHandsMeeting();
}
HRHomeHeader.prototype.testHoliday = function() {
	App.loadAllHandsMeeting();
}
HRHomeHeader.prototype.testHRPolicy = function() {
	App.loadAllHandsMeeting();
}
HRHomeHeader.prototype.testNotification = function() {
	App.loadAllHandsMeeting();
}*/

hRHomeHeader.prototype.testTemplate = function() {

	var dataid="34";
	App.loadViewTemplate(dataid);
}
hRHomeHeader.prototype.testAllHandsMeeting = function() {
	App.loadAllHandsMeeting();
}



