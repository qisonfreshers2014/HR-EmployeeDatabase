function changePassword(employeeId) {
	Loader.loadHTML(".container", "resources/js/employee/changePassword.html",
			true, function() {
				this.handleShow(employeeId);
			}.ctx(this));
}
changePassword.prototype.handleShow = function(employeeId) {

	$('.container').show();

	$('#changepwd').click(function() {

		this.changePwd(employeeId);
	}.ctx(this));
	$('#backchangepassword').click(function() {

		routie("myprofile");

	}.ctx(this));

}
changePassword.prototype.changePwd = function(employeeId) {
    
	var pwepattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}/;
	var oldPassword = $("#oldpassword").val();
	var newPassword = $("#newpassword").val();
	var confirmPassword = $("#confirmpassword").val();
	var olepwderr = $("#olepwderr");
	var newpwderr = $("#newpwderr");
	var confirmpwderr = $("#confirmpwderr");
	if (oldPassword == "" || newPassword == "" || confirmPassword == "") {
		if (oldPassword == "") {
			$(olepwderr).text("Required field");
			$(olepwderr).css("color", "red");
		} else if (!(oldPassword.match(pwepattern))) {
			$(olepwderr)
					.text(
							"Must be at least 8 characters, 1 lowercase, 1 uppercase letter, At least 1 special character");
			$(olepwderr).css("color", "red");
		} else {
			$(olepwderr).text("");
			// $(pwderr).css("color", "green");
		}

		if (newPassword == "") {
			$(newpwderr).text("Required field");
			$(newpwderr).css("color", "red");
		} else if (!(newPassword.match(pwepattern))) {
			$(newpwderr)
					.text(
							"Must be at least 8 characters, 1 lowercase, 1 uppercase letter, At least 1 special character");
			$(newpwderr).css("color", "red");
		} else {
			$(newpwderr).text("");
			// $(pwderr).css("color", "green");
		}

		if (confirmPassword == "") {
			$(confirmpwderr).text("Required field");
			$(confirmpwderr).css("color", "red");
		} else if (!(confirmPassword.match(pwepattern))) {
			$(confirmpwderr)
					.text(
							"Must be at least 8 characters, 1 lowercase, 1 uppercase letter, At least 1 special character");
			$(confirmpwderr).css("color", "red");
		} else {
			$(confirmpwderr).text("");
			// $(pwderr).css("color", "green");
		}
	} else if (!(newPassword.match(pwepattern))) {
		$(newpwderr)
				.text(
						"Must be at least 8 characters, 1 lowercase, 1 uppercase letter, At least 1 special character");
		$(newpwderr).css("color", "red");
	} else if (!(confirmPassword.match(pwepattern))) {
		$(confirmpwderr)
				.text(
						"Must be at least 8 characters, 1 lowercase, 1 uppercase letter, At least 1 special character");
		$(confirmpwderr).css("color", "red");
	} else if (!(newPassword == confirmPassword)) {
		$(olepwderr).text("");
		$(newpwderr).text("");
		$(confirmpwderr).text("Password and confirm password must match");
		$(confirmpwderr).css("color", "red");

	} else {
		$(olepwderr).text("");
		$(newpwderr).text("");
		$(confirmpwderr).text("");

		var input = {
			"payload" : {
				"id" : employeeId,
				"oldPassword" : oldPassword,
				"newPassword" : newPassword,
				"confirmNewPassword" : confirmPassword

			}
		};

		RequestManager.changePassword(input, function(data, success) {
			if (success) {
				alert("Password Successfully Changed");
				routie("myprofile");
			} else {
				alert(data.message)
			}
		}.ctx(this));
	}
}