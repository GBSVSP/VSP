$(document).ready(function() {
	 $("#userComment").val("");
	 $("#userComment").attr("placeholder","Add a comment at the selected stage.");
	 
	});


$("#postComment").click(function () {

	var comments  = $("#userComment").val();
		if(comments == ''){
			alert ("Comments cannot be Blank ");
			return false;
		}
		else{
			return true;
		}
						
	}); 
$("#cancelComment").click(function () {

	 $("#userComment").val("");
	return false;	
						
	}); 

function getUserNames(count,createdUser){
	var fName = null;
	var lName = null;
	var url = "https://w3.api.ibm.com/common/run/bluepages/userid/"+createdUser+"/emailaddress&hrFirstName&hrLastName&uid?client_id=e9559df8-11f2-42aa-9d4d-ac334d7b98b9";
	jQuery.getJSON(url, function (data, status) {	
			
				  if (status == 'success') {
				  $.each(data, function (key1, value1) {
		                $.each(value1, function (key2, value2) {
		                    $.each(value2, function (key3, value3) {
		                        $.each(value3, function (key4, value4) {
		                            if (key4 == 'attribute')
		                            {
		                                $.each(value4, function (key5, value5) {
		        
		                                    if(value5.name == 'hrFirstName'){
		                                    	fName = value5.value;
                                    	
                                 		
                                    }
                                    if(value5.name == 'hrLastName'){
                                    	lName = value5.value;
                                    	
                                    }
		                                });
		                             
											  $("#commentedUser"+count).text(fName + " " +lName);	
										
											  
		                            }
		                        });
		                      
		                   });
		                });
		              
		            });
		             
			    }
				
			});				
	
}