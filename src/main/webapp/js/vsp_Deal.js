 /**** This is the java script file for validating the Deal form *****/
function validate(){

						var customer_Name = $("#customer_Name").val();
						var opportunity_Name = $("#opportunity_Name").val();
						var opportunity_Description = $("#opportunity_Description").val();
						//var a1Status = $("#a1Form:j_idt61:0:a1Status").val();
						//alert("a1Status:"+a1Status);
						var status = false;
						if (customer_Name == '') {
							
			    		   	 $("#err_customer_Name").html("Cannot be Blank");
			    		   } 
						if (opportunity_Name == '') {
							
			    		   	 $("#err_opportunity_Name").html("Cannot be Blank");
			    		   } 
						if (opportunity_Description == '') {
							
			    		   	 $("#err_opportunity_Description").html("Cannot be Blank");
			    		   } 
						 /*  if (a1Status == '') {
							
								 $("#err_a1_Status").html("Cannot be Blank");
			    		   }  */ 
						 if(customer_Name != '' && opportunity_Name != '' && opportunity_Description != '' ){
							 status = true;
						 }
						 return status;
						 
			    }
					
					$("#cancelButton").click(function () {
						var status;
				
						status = confirm("You will lose un-saved data. Are you sure? ");
						
						return status;
					}); 
					
					