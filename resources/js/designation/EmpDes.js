function EmpDes() {
	Loader.loadHTML('.leftContainer', 'resources/js/designation/viewdesignation.html', true, function(){
		this.handleShow();
	}.ctx(this));
}
/*
 * function EmpDes(obj) { this.obj = obj;
 */
EmpDes.prototype.handleShow = function() {
	$('.container').show();
	$( "#datepicker" ).datepicker({dateFormat:'yy-mm-dd',showButtonPanel:true,changeMonth:true,changeYear:true,showAnim:'drop',minDate:new Date(1993,12,31),maxDate:new Date(2050,12,31)});		
	//this.getDetailsFromEmployee();
	this.getDesignationHistory();// to call all the fields of the
									// designations....
	
	$('#save_designations').click(function(){
		//this.validateDesignation();
		this.sendJson();
		
	}.ctx(this));

}


EmpDes.prototype.sendJson = function() 
{

	var empName1="Bhargavi";
	var doj1= "2015-04-01";
	// pojo's variables
	var empId="20";// rahul's view page
	var date1=$("#datepicker").val()+'00:00:00';
	var designationId1=$("#designation").val();
	var salary1 =$("#salary").val();
	var variablePay1 = $("#variablePay").val();
	var input = {
		"payload": {
			"empId": empId,
			"appraisalDate": date1,
			"designationId":designationId1,
			"salary":salary1,
			"variablePay":variablePay1
		}
	};
	 RequestManager.saveDes(input, function(data, success) {

		   if(success)
		   {
			   
			   		alert("successfully added");
				    var table = $('#table').children(); 
				    table.append("<tr><td>"+data.designationId+"</td><td>"+data.salary+"</td><td>"+data.variablePay+"</td><td>"+data.appraisalDate+"</td></tr>");					
			 	    $("#table td").css('width', '45px');
				    $('#table td').css('border', '1px solid black');
				    $('#table tr').css('border', '1px solid black');      	          
			 	    $("#table td").css('width', '20%');		   
		    
		  }else{
		    
			  alert("failed to add");
		   }  
		    
		  }.ctx(this));	

}

EmpDes.prototype.getDesignationHistory=function()
{
	   var input ={
				"payload": {
					"empId": 20// rahul's view page
				}};
	   
	   RequestManager.getDes(input, function(data, success) {
		   if(success)
		   { 
			   alert("successfully retrieved  from DesHistory .........");
			   var content = data;
			   var status = success;

			   var input1 ={
						"payload": {}};
			   RequestManager.getDesName(input1,function(data1, success) {
		   		if(success)
		   		{
					alert("successfully retrieved  from DesType .........");
		   			this.contentDisplay(content,data1,status);		   			
		   		}else{		      
					  alert("failed to retrieved from DesType");
		   		}
		   	}.ctx(this));
		  }else{
		      
			  alert("failed to retrieved from DesHistory ");
		   }  
		    
		  }.ctx(this));
		
}
	   
	   

EmpDes.prototype.contentDisplay = function(content,data1,status)
{	  
	     $('#table').append('<table></table>');
	    var table = $('#table').children(); 
	    table.append("<tr><th>Designation</th><th>Salary</th><th>Variable Pay</th><th>Date</th></tr>");
	    $('#table').css('width', '10px');
	    var i;
	    var obj;
	    var desName;
	    var desTypeObj;
	    for(i = 0; i<content.length; i++)
	    {
	    	obj = content[i];
	    	var desId=obj.designationId;
// /////////////////////////////////////////////////////////get designation name
	    	for(i=0;i<data1.length; i++)
	    	{
	    		desTypeObj=data1[i];
	    		if(desId==desTypeObj.id)
	    		{
	    			desName=desTypeObj.name;
	    			break;
	   			}	
	    		
	         }	     
// ////////////////////////////////////////////////////////////////////
		    table.append("<tr><td>"+desName+"</td><td>"+obj.salary+"</td><td>"+obj.variablePay+"</td><td>"+obj.appraisalDate+"</td></tr>");
			   
	 	    $('#table').css('border-collapse', 'collapse');	   
		    // $("#table").css('background-color', 'red');
	 	    $("#table td").css('width', '45px');
		    $('#table').css('border-color', 'black');
		    $('#table').css('border', '1px solid black');  
		    $('#table th').css('border', '1px solid black');
		    $('#table td').css('border', '1px solid black');
		    $('#table tr').css('border', '1px solid black');      	          
		   // $("#table td").css('background-color', 'green');
		    // $("#table tr").css('background-color', 'yellow');
	 	    $("#table").css('width', '60%');
	 	    $("#table td").css('width', '20%');
	     }
	 
}

