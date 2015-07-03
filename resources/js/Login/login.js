function Login() { 
 Loader.loadHTML('.container', 'resources/js/Login/login.html', true, function(){
  this.handleShow();
 }.ctx(this));
}

Login.prototype.handleShow = function() {  
 $('#userName').focus(); 
 $("#login").keyup(function (event) {
  if (event.keyCode == 13){
   $("#Submit").trigger('click');
   } 
    }.ctx(this));
 
 $("#Submit").click(function(event){
   if(this.validateLogin($('input.username').val(),$('input.password').val())){  
    this.authenticate();
  } 
 }.ctx(this));
} 
 Login.prototype.authenticate = function() { 
  
  var input = {"payload":{"authType":"REGULAR",
        "email":$('.username').val().trim(),
        "password":$('.password').val()
        }};
  
  RequestManager.authenticate(input, function(data, success) {
   if(success){
    var  token = data.sessionToken;
         setCookie('hredSessionToken', token, null);
    var name=data.employeeDetails.employeeName;
       var gender=data.employeeDetails.gender;
       var contactNo=data.employeeDetails.contactNo;
       var employeeId=data.employeeDetails.employeeId;
       var hr=data.roleHr;
       var isDeleted=data.employeeDetails.isDeleted;
       App.loadRouter(name, hr,isDeleted,gender, contactNo,employeeId,function(){
    	   new Router();
       });
       
   }
   else alert(data.message);
  }.ctx(this));
 }
 
 Login.prototype.validateLogin = function(email,password){	 
  $(".errormessage1").hide();
  $(".errormessage").hide();
     var isValid = false;
     var emailReg = /^[_A-Za-z]+[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$/;
     var minMaxLength = /^[\s\S]{8,32}$/;
     var number = /[0-9]/;
     var special = /[ !"#$%&'()*+,\-./:;<=>?@[\\\]^_`{|}~]/;
     var upper = /[A-Z]/;
     var lower = /[a-z]/;
     var email=email.trim();
     if(email == "" || email == null) {
      $(".errormessage1").show();
      $('.errormessage1').focus();
      $('.errormessage1').text("Email must be filled(?)");
         isValid = false;
     }

     else if(!emailReg.test(email)) {
      $('.errormessage1').show();
      $('.errormessage1').focus();
      $('.errormessage1').text("Enter a valid email(?)");
         isValid = false;
     }
     else if (email.length > 128){
      $(".errormessage1").show();
      $('.errormessage1').focus();
      $('.errormessage1').text("Email length is too large(?)");
         isValid = false;     
     }
     else if(password == "" || password == null ){
      $(".errormessage").show();
      $('.errormessage').focus();
      $('.errormessage').text("Password cannot be empty");
         isValid = false;
  } 
     else if((!minMaxLength.test(password))||(!special.test(password))||(!number.test(password))||(!upper.test(password))||(!lower.test(password))){
/*      $(".errormessage").show();
      $('.errormessage').focus();
      $('.errormessage').html("<span>Password should be atleast 8 characters and contain one number</span><br>" +
   "<span>one Uppercase one Lowercase and one Special character</span>");*/
      alert("Password should be atleast 8 characters and contain one number one uppercase one lowercase and one special character");
         isValid = false;
  } 
     /*else if(!special.test(password) ){
      $(".errormessage").show();
      $('.errormessage').focus();
      $('.errormessage').text("Password has no special character(?)");
         isValid = false;
  } 
     else if(!number.test(password) ){
      $(".errormessage").show();
      $('.errormessage').focus();
      $('.errormessage').text("Password has no number(?)");
         isValid = false;
  } 
     else if(!upper.test(password) ){
      $(".errormessage").show();
      $('.errormessage').focus();
      $('.errormessage').text("Password has no Uppercase(?)");
         isValid = false;
  }
     else if(!lower.test(password) ){
      $(".errormessage").show();
      $('.errormessage').focus();
      $('.errormessage').text("Password has no Lowercase(?)");
         isValid = false;
  }*/
  /*ï¿½else if(password.length < 6 ){
   $(".message").show();
   $('.message').focus();
      $('.message').text("password is too short(?)");
         isValid = false;
  } 
  else if(password.trim().length  > 128)
   {
   $(".errormessage").show();
   $('.errormessage').focus();
      $('.errormessage').text("password is too large(?)"); 
   }*/
  else
   {
   $(".errormessage1").empty();
   isValid = true;
   }
  return isValid; 
 }

//var Login= new Login();