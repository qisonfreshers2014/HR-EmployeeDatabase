function Uploader(allowedFileType, callback, forAdmin, isQSPAdmin, mediaUpload) {
    //console.log(allowedFileType);
    this.allowExtArray = [];
    $('.uploadMediaModal').remove();
    if (allowedFileType != undefined || allowedFileType.length != 0) {
        this.getAllowedExtensions(allowedFileType);
    } else {
        this.populateFileLimits();
    }
    this.allowFileSize;
    this.mediaUpload = mediaUpload;
    this.errorMsg = '';
    this.iN = 1;
    this.minInput = 1; // Should not be changed, it should be one only
    this.maxInput = 5;
    this.acceptParam = "/*";
    this.callback = callback;
    var uploaderPath;
    if (forAdmin) {
        if (isQSPAdmin) {
            uploaderPath = "../resources/js/uploader/uploader.html";
        } else {
            uploaderPath = "../../../resources/js/uploader/uploader.html";
        }
    } else {
        uploaderPath = "../../resources/js/uploader/uploader.html";
    }
    Loader.loadHTML('body', uploaderPath,
        false,
        function() {
            this.handleShow(allowedFileType);
            if (allowedFileType == "image") {
                $(".modalHeading").text("Upload Image");
            } else if (allowedFileType == "audio") {
                $(".modalHeading").text("Upload Audio");
            } else if (allowedFileType == "video") {
                $(".modalHeading").text("Upload Video");
            } else if (allowedFileType == "image/video") {
                $(".modalHeading").text("Upload Image/Video");
            } else if (allowedFileType == "image/audio") {
                $(".modalHeading").text("Upload Image/Audio");
            } else if (allowedFileType == "audio/video") {
                $(".modalHeading").text("Upload Audio/Video");
            }
        }.ctx(this));
}
Uploader.prototype.handleShow = function(allowedFileType) {
    var self = this;
    this.populateFileLimits();
    /*$('.uploadMediaModal').modal({
    keyboard : false,
    show : true,
    backdrop : "static"
    });*/
    $('.uploadMediaModal').modal('show');
    // to upload multiple files
    if (this.mediaUpload) {
        $('.uploadMediaInput').prop('multiple', 'multiple');
    }
    if (allowedFileType == 'video') {
        $('.uploadMediaModal .fileInfo').show();
    }
    if (allowedFileType.indexOf("/") != -1) {
        var fileTypes = allowedFileType.split("/");
        this.acceptParam = "";
        for (var i = 0; i < fileTypes.length; i++) {
            if ((i + 1) != fileTypes.length) {
                this.acceptParam = this.acceptParam + fileTypes[i] + "/*,";
            } else {
                this.acceptParam = this.acceptParam + fileTypes[i] + "/*";
            }
        }
    } else {
        this.acceptParam = allowedFileType + "/*";
    }
    $('.uploadMediaInput').attr('accept', allowedFileType + '/*').change(function() {
        this.validateFile(allowedFileType);
    }.ctx(this));
    var options = {
        beforeSend: function() {
            var isFileValidated = self.uploadFile();
            if (isFileValidated) {
                $('#uploadForm .btn-primary').attr("disabled", "disabled");
                $("#progressbox").show();
                $("#progressbar").html("<img src='/resources/images/loading.gif'>");
                $("#statustxt").html("Uploading...");
            } else {
                return false;
            }
        },
        success: function() {},
        complete: function(xhr) {
            $("#progressbox").hide();
            $('#uploadForm .btn-primary').removeAttr("disabled");
            var responseBody = JSON.parse(xhr.responseText.replace(/""/g, '"'));
            $('.uploadMediaModal').modal('hide');
            //console.log(navigator.appName);
            if (xhr.responseText.match('"code":3002')) {
                var json = $.parseJSON(JSON.stringify(eval("(" + xhr.responseText + ")")))
                    //console.log(json.payload.errorList[0].localizedMessage)
                    //alert ("Uploaded file size exceeded the allowed limit");
                var errorMsg = json.payload.errorList[0].localizedMessage;
                alert(errorMsg);
            } else if ((xhr.responseText.match('"code":3004') == null) && (xhr.responseText.match('"code":232') == null)) {
                self.callback(responseBody.payload);
            } else {
                alert("Invalid File");
            }
        }
    };
    // bind 'myForm' and provide a simple callback function
    var form = $('#uploadForm').ajaxForm(options);
    $('.cancelUploadBtn').click(function() {
        if (detectIE()) {
            var xhr = form.data('jqxhr');
            xhr.abort();
            //window.document.execCommand('Stop');
        } else {
            window.stop();
        }
    }.ctx(this));
};
Uploader.prototype.fileUploaded = function(data) {
    this.callback(data);
}
Uploader.prototype.validateFile = function(allowedFileType) {
    var fileExt = '';
    var isProceedUpload = true;
    var fileSize = 0;
    var filePath = $('.uploadMediaInput').val();
    var fileObj = $('.uploadMediaInput');
    if (filePath.length > 0) {
        fileExt = this.getFileExtension(filePath);
        fileExt = fileExt.toLowerCase();
        if ($.inArray(fileExt, this.allowExtArray) == -1) {
            alert("Only doc/pdf/odt/docx files are allowed ");
            //alert("Sorry" + " " + filePath + " is not allowed. "); // Message.get('common.notallowextensions.message'));+
            // this.allowExtArray.join(", "));
            $('.uploadMediaInput').val('');
            //return;
        }
        fileSize = this.getFileSize(fileObj);
       //alert("file size is "+fileSize+"allowed file size is "+this.allowFileSize);
        if (fileSize > (5242880))//checking that not more than 5 MB
        {
            //alert("Sorry" + filePath + "SIze" + fileSize + "Size" + this.allowFileSize);
        	alert("Sorry, File size is not more than 5 MB");
            $('.uploadMediaInput').val('');
            return;
        }
    }
}
Uploader.prototype.uploadFile = function() {
    var formData = $('form').serializeArray();
    var filesObj = $('.uploadMediaInput').val();
    var isFU = false;
    if (filesObj != "") {
        isFU = true;
    }
    if (!isFU) {
        alert("No Files to upload");
        return false;
    } else {
        return true;
    }
}
Uploader.prototype.getFileExtension = function(fileName) {
    var found = fileName.lastIndexOf('.') + 1;
    return (found > 0 ? fileName.substr(found) : "");
}
Uploader.prototype.getFileSize = function(fileObj) {
    var iSize = 0;
    // if ($.browser.msie) {
    // var objFSO;
    // try {
    // objFSO = new ActiveXObject("Scripting.FileSystemObject");
    // } catch (err) {
    // // alert(err);
    // // return 0;
    // }
    // var sPath = fileObj;
    // var objFile = objFSO.getFile(sPath);
    // var iSize = objFile.size;
    // iSize = iSize / 1024;
    // } else {
    //console.dir(fileObj)
    //alert("file size is "+fileObj[0].files[0].size)
   iSize = (fileObj[0].files[0].size);
//    // }
//    iSize = (Math.round((iSize / 1024) * 100) / 100);
    return iSize;
}


Uploader.prototype.populateFileLimits = function() {
    /*
     * var d = '{}'; $.ajax({ url: '/services/v1/fileUpload/limits', type:
     * 'POST', contentType: 'application/json', dataType: 'json', success:
     * function(data){ var allowedExt = data.payload.allowedExtensions;
     * this.allowExtArray = allowedExt.split(','); var maxFileSize =
     * data.payload.allowedMaxFileSize; this.allowFileSize = maxFileSize;
     * $('#AllowedExt').html('Allowed File Extensions : '+allowedExt);
     * $('#MaxFile').html('Maximum Allowed File Size : '+maxFileSize+' MB'); },
     * data: JSON.stringify(d), error: function(xhr, error, exception) {
     * alert('xhr:'+xhr); alert('error:'+error); alert('exception:'+exception); }
     * }.ctx(this));
     */
	
	this.allowFileSize = 5+' MB';
}
Uploader.prototype.getAllowedExtensions = function(allowedFileType) {
if(allowedFileType == "image")
	this.allowExtArray = ['jpg', 'png', 'jpeg'];
else
	this.allowExtArray = ['pdf','doc','odt','docx'];
}