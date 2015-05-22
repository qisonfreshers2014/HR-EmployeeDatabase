function listHoliday() {
	Loader.loadHTML('.container', 'resources/js/Holidays/holidays.html', true,
			function() {
				this.handleShow();
			}.ctx(this));
}

listHoliday.prototype.handleShow = function() {

	$('.container').show();
	var contentinput = {
		"payload" : {}
	};
	RequestManager.getHolidaysData(contentinput, function(data, success) {
		if (success) {
			var content = data;
			var status = success;
			this.tableDisplay(content, status);

		} else {

			alert("Failed to retrieve holidays");
		}

	}.ctx(this));

	$('#start_from').focusin(function() {

		var currentYear = (new Date).getFullYear();

		$(".date").datepicker({
			dateFormat : 'yy-mm-dd',
			showButtonPanel : true,
			changeMonth : true,
			changeYear : true,
			showAnim : 'drop',
			minDate : (new Date((currentYear - 2), 12, 1)),
			maxDate : new Date((currentYear + 1), 11, 31)
		});

	}.ctx(this));

	
	$('#save').click(function() {

		this.validateHolidays();

	}.ctx(this));

}

// function to validate and save
listHoliday.prototype.validateHolidays = function() {
	var currentYear = (new Date).getFullYear();
	var month = (new Date).getMonth() + 1;
	var date = (new Date).getDate();
	var letters = /^\d\d\d\d-(0\d|1[012])-(0\d|1\d|2\d|3[01])$/;
	$(".date").datepicker({
		dateFormat : 'yy-mm-dd',
		showButtonPanel : true,
		changeMonth : true,
		changeYear : true,
		showAnim : 'drop',
		minDate : (new Date((currentYear - 2), 12, 1)),
		maxDate : new Date((currentYear + 1), 11, 31)
	});

	if ($("#start_from").val() == "" || $("select").val() == "Select"
			|| $("#description").val() == "") {
		$(".holidayerror").css("visibility", "visible");
		if ($("#start_from").val() == "") {
			$("#fromDate").text("Please enter date");

		} else if (!($("#start_from").val().match(letters))) {
			$(".holidayerror").css("visibility", "visible");
			$("#fromDate").text("Please enter date in yyyy-mm-dd format");

		} else {
			$("#fromDate").text("");
		}

		if ($("#description").val() == "") {
			$("#holdescrp").text("Please enter description");

		} else if (!($("#description").val().match('^[A-Za-z]+( [A-Za-z]+)*$') || $(
				"#description").val() == isNaN)) {
			$(".holidayerror").css("visibility", "visible");
			$("#holdescrp").text("Only space and characters are allowed");
		} else {
			$("#holdescrp").text("");
		}

		if ($('select').val() == "Select") {
			$("#type").text("Please select type of holiday");

		} else {
			$("#type").text("");
		}

	} else if (!($("#start_from").val().match(letters))) {
		$(".holidayerror").css("visibility", "visible");
		$("#type").text("");
		$("#holdescrp").text("");
		$("#fromDate").text("Please enter date in yyyy-mm-dd format");

	} else if (!($("#description").val().match('^[A-Za-z]+( [A-Za-z]+)*$') || $(
			"#description").val() == isNaN)) {
		$(".holidayerror").css("visibility", "visible");
		$("#type").text("");
		$("#fromDate").text("");
		$("#holdescrp").text("Only space and characters are allowed");
	} else {
		$(".holidayerror").css("visibility", "hidden");

		var input = {
			"payload" : {
				"fromDate" : $("#start_from").val() + ' 00:00:00',
				"toDate" : $("#start_from").val() + ' 00:00:00',
				"description" : $('#description').val(),
				"type" : $("#dropdown").val()
			}
		};

		$("#start_from").val("");
		// $('#ends_at').val("");
		$("#description").val("");
		$('#dropdown').val("");

		RequestManager.holidaysData(input, function(data, success) {
			if (success) {
				var content = data;
				var status = success;
				this.contentDisplay(content, status);
				alert("Entered holiday saved successfully");

			} else if (data.code == 9016) {

				alert(" Date already exists ");

			} else {

				alert("Failed to save");

			}

		}.ctx(this));

	}

}

// function to display whole Holidays data in a table and update call back
listHoliday.prototype.tableDisplay = function(content, status) {
	$("#save").show();

	$("#display").on("click", ".dynamicEdit", function(event) {
		$("#fromDate").text("");
		$("#type").text("");
		$("#holdescrp").text("");
		var releaseId = event.target.id;
		this.editData(releaseId);

	}.ctx(this));

	$("#display").on("click", ".deleteHoliday", function(event) {
		var text = confirm("Are you sure you want to delete?");
		if (text == true) {

			var deleteId = event.target.id;
			var date = deleteId + ' 00:00:00';
			this.deleteHolidayField(date);

		}

	}.ctx(this));

	$("#update")
			.click(
					function() {
												
						if ($("#start_from").val() == "" || $("select").val() == "Select"
							|| $("#description").val() == "") {
						$(".holidayerror").css("visibility", "visible");
						if ($("#start_from").val() == "") {
							$("#fromDate").text("Please enter date");

						} else if (!($("#start_from").val().match(letters))) {
							$(".holidayerror").css("visibility", "visible");
							$("#fromDate").text("Please enter date in yyyy-mm-dd format");

						} else {
							$("#fromDate").text("");
						}

						if ($("#description").val() == "") {
							$("#holdescrp").text("Please enter description");

						} else if (!($("#description").val().match('^[A-Za-z]+( [A-Za-z]+)*$') || $(
								"#description").val() == isNaN)) {
							$(".holidayerror").css("visibility", "visible");
							$("#holdescrp").text("Only space and characters are allowed");
						} else {
							$("#holdescrp").text("");
						}

						if ($('select').val() == "Select") {
							$("#type").text("Please select type of holiday");

						} else {
							$("#type").text("");
						}

					} else if (!($("#description").val().match('^[A-Za-z]+( [A-Za-z]+)*$') || $(
							"#description").val() == isNaN)) {
						$(".holidayerror").css("visibility", "visible");
						$("#type").text("");
						$("#fromDate").text("");
						$("#holdescrp").text("Only space and characters are allowed");
					
					} 
						 else {

							var id = $("#holidayID").text();

							var updateContent = {
								"payload" : {
									"id" : id,
									"fromDate" : $("#start_from").val()
											+ ' 00:00:00',
									"toDate" : $("#start_from").val()
											+ ' 00:00:00',
									"description" : $("#description").val(),
									"type" : $("#dropdown").val()
								}
							};

							$("#start_from").val("");
							// $('#ends_at').val("");
							$("#description").val("");
							$('#dropdown').val("");

							RequestManager
									.dynamicallyEdit(
											updateContent,
											function(data, success) {
												if (success) {
													$("#save").show();
													$("#update").css(
															"visibility",
															"hidden");
													alert("Edited holiday updated successfully ");
													App.loadHoliday();
												} else if (data.code == 9016) {

													alert("Date you are trying to update already exists");
												} else {

													alert("Failed to update");
												}

											}.ctx(this));

						}

					});

	for (var i = 0; i < content.length; i++) {
		var obj = content[i];

		var fromdateformat = new Date(obj.fromDate);
		var year = fromdateformat.getFullYear();
		var month = fromdateformat.getMonth() + 1;
		var date = fromdateformat.getDate();

		var dayNames = new Array('Sunday', 'Monday', 'Tuesday', 'Wednesday',
				'Thursday', 'Friday', 'Saturday');
		var day = dayNames[fromdateformat.getDay()];

		if (obj.type == "Mandatory") {
			$('#mandatorytable').append('<table><tbody></tbody></table>');
			$('#mandatorytable tr:last')
					.after(
							"<tr><td>"
									+ year
									+ "-"
									+ month
									+ "-"
									+ date
									+ "</td><td>"
									+ obj.description
									+ "</td><td>"
									+ day
									+ "</td><td><input type='button' value='Edit' id='"
									+ obj.id
									+ "' class='dynamicEdit'></td><td><input type='button' value='Delete' class='deleteHoliday' id='"
									+ (year + "-" + month + "-" + date)
									+ "'></td></tr>");

		}

		if (obj.type == "Weekend") {
			$('#weekendstable').append('<table><tbody></tbody></table>');
			$('#weekendstable tr:last')
					.after(
							"<tr><td>"
									+ year
									+ "-"
									+ month
									+ "-"
									+ date
									+ "</td><td>"
									+ obj.description
									+ "</td><td>"
									+ day
									+ "</td><td><input type='button' value='Edit' id='"
									+ obj.id
									+ "' class='dynamicEdit'></td><td><input type='button' value='Delete' class='deleteHoliday' id='"
									+ (year + "-" + month + "-" + date)
									+ "'></td></tr>");

		}

		if (obj.type == "Optional") {
			$('#optionaltable').append('<table><tbody></tbody></table>');
			$('#optionaltable tr:last')
					.after(
							"<tr><td>"
									+ year
									+ "-"
									+ month
									+ "-"
									+ date
									+ "</td><td>"
									+ obj.description
									+ "</td><td>"
									+ day
									+ "</td><td><input type='button' value='Edit' id='"
									+ obj.id
									+ "' class='dynamicEdit'></td><td><input type='button' value='Delete' class='deleteHoliday' id='"
									+ (year + "-" + month + "-" + date)
									+ "'></td></tr>");

		}

	}

}

// function to edit Holiday data
listHoliday.prototype.editData = function(releaseId) {
	$("#update").css("visibility", "visible");
	var inputvalue = {
		"payload" : {
			"id" : releaseId
		}
	};

	RequestManager.getHoliday(inputvalue, function(data, success) {
		if (success) {

			var editdata = data[0];

			var fromvalue = editdata.fromDate;
			var fromres = new Date(fromvalue);
			var fromyear = fromres.getFullYear();
			var frommonth = fromres.getMonth() + 1;
			var fromdate = fromres.getDate();

			$("#start_from").val(fromyear + "-" + frommonth + "-" + fromdate);
			// $("#ends_at").val(toyear+"-"+tomonth+"-"+ todate);
			$("#description").val(editdata.description);
			$("#dropdown").val(editdata.type);
			$("#holidayID").text(editdata.id);
			$("#save").hide();
		}

	}.ctx(this));

}

// function to display the saved content in a table
listHoliday.prototype.contentDisplay = function(content, status) {
	if (status) {

		var value = content.fromDate;
		var res = new Date(value);
		var year = res.getFullYear();
		var month = res.getMonth() + 1;
		var date = res.getDate();

		var dayNames = new Array('Sunday', 'Monday', 'Tuesday', 'Wednesday',
				'Thursday', 'Friday', 'Saturday');
		var day = dayNames[res.getDay()];

		if (content.type == "Mandatory") {
			$('#mandatorytable').append('<table><tbody></tbody></table>');
			$('#mandatorytable tr:last')
					.after(
							"<tr><td>"
									+ year
									+ "-"
									+ month
									+ "-"
									+ date
									+ "</td><td>"
									+ content.description
									+ "</td><td>"
									+ day
									+ "</td><td><input type='button' value='Edit' id='"
									+ content.id
									+ "' class='dynamicEdit'></td><td><input type='button' value='Delete' class='deleteHoliday' id='"
									+ (year + "-" + month + "-" + date)
									+ "'></td></tr>");

		}

		if (content.type == "Weekend") {
			$('#weekendstable').append('<table><tbody></tbody></table>');
			// var table = $('#weekends').children();
			$('#weekendstable tr:last')
					.after(
							"<tr><td>"
									+ year
									+ "-"
									+ month
									+ "-"
									+ date
									+ "</td><td>"
									+ content.description
									+ "</td><td>"
									+ day
									+ "</td><td><input type='button' value='Edit' id='"
									+ content.id
									+ "' class='dynamicEdit'></td><td><input type='button' value='Delete' class='deleteHoliday' id='"
									+ (year + "-" + month + "-" + date)
									+ "'></td></tr>");

		}

		if (content.type == "Optional") {
			$('#optionaltable').append('<table><tbody></tbody></table>');
			// var table = $('#optional').children();
			$('#optionaltable tr:last')
					.after(
							"<tr><td>"
									+ year
									+ "-"
									+ month
									+ "-"
									+ date
									+ "</td><td>"
									+ content.description
									+ "</td><td>"
									+ day
									+ "</td><td><input type='button' value='Edit' id='"
									+ content.id
									+ "' class='dynamicEdit'></td><td><input type='button' value='Delete' class='deleteHoliday' id='"
									+ (year + "-" + month + "-" + date)
									+ "'></td></tr>");

		}

	}

}

// function to delete a record
listHoliday.prototype.deleteHolidayField = function(date) {

	var input = {
		"payload" : {
			"fromDate" : date
		}
	};
	RequestManager.getHolidayByDate(input, function(data, success) {
		if (success) {
			App.loadHoliday();

		}

	}.ctx(this));

}

var listHoliday = new listHoliday();
