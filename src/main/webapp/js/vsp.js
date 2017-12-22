//<![CDATA[	

    
 /***** PARTICIPANT MASTER start *****/
	      
       $("#newParticipantButton").click(function(){
    	    		 
		var buttonclicked = $("#newParticipantButton").val();
		//alert("Button clicked: " + buttonclicked);
		
		var status = false;
		var count = 0;
		
		if (buttonclicked == '+ New Participant'){
			//alert("Modal called"); 
			//$('.modal-body').load('searchUser.xhtml');
			$("#userEmail").val("");
			$("#firstName").text("");
			$("#lastName").text("");
		    $("#err_email").html("");
		    $("#userNotFound").text("");
			$("#addPartBtn").hide();
			$("#searchUserModal").modal('show');
            count =1; 
            			            
		} else {

				if (buttonclicked == 'Save Participant') {
					//alert("In loop Save User");
					var table = $("#participantResult tbody");
		
					table.find('tr').each(function(index) {
									var $tds = $(this).find('td');
									//Check if serialNo is 0 then it is a new row
									var serialNo = $tds.eq(1).find("input:hidden").val();
		
									if (serialNo == 0) {
										//alert("serialNo is 0");
		
										var checkbox_sel_val = $tds.eq(0);
										var checkbox = checkbox_sel_val.find("input:checked").val();
										var email = $tds.eq(2).find("input:text").val();
										var username = $tds.eq(3).find("input:text").val();
										var imt_sel_val = $tds.eq(4);
										var imt_Id = imt_sel_val.find("option:selected").val();
										
										var sentiment_sel_val = $tds.eq(11);
										var sentiment_Id = sentiment_sel_val.find("option:selected").val();
		
										//alert("Row: "+ index+ "\n checkbox: "+ checkbox+ "\n serialNo :"+ serialNo
										//	+ "\n username: "+ username+ "\n email: "+ email+ "\n sentiment_Id: "+ sentiment_Id
										 //  + "\n imt_Id: "+ imt_Id);
										
										var error_email = $tds.eq(2).find("span").first();
										error_email.html("");
										if (email == ''|| email == 'undefined') {
											error_email.html("Email cannot be blank");
											count++;
										} else {
											if (!isValidEmailAddress(email)) {
												error_email.html("Invalid email format");
												count++;
											}
										}
		
										var error_username = $tds.eq(3).find("span").first();
										error_username.html("");
										if (username == ''|| username == 'undefined') {
											error_username.html("Username cannot be blank");
										
											count++;
										} else {
											if (!isValidUserName(username)) {
												error_username.html("Invalid username format");
												count++;
											}
										}
		
										var error_imt_Id = $tds.eq(4).find("span").first();
										error_imt_Id.html("");
										if (imt_Id == ''|| imt_Id == 'Please Choose') {
											error_imt_Id.html("Please select IMT");
											count++;
										}
		
										var error_sentiment_Id = $tds.eq(11).find("span").first();
										error_sentiment_Id.html("");
										if (sentiment_Id == ''|| sentiment_Id == 'Please Choose') {
											error_sentiment_Id.html("Please select Sentiment");
											count++;
										}
		
									}
		
								});
		
					 }
		   }
	
			if (count > 0) {
				status = false;
			} else {
				status = true;
			}
	
			//alert("status: " +status);
			return status;
        });

			
		$("#updateButton").click(function(){
			
			var buttonclicked = $("#updateButton").val();
			//alert("Button clicked: " + buttonclicked);
			var status = false;
			var count = 0;
			
			var selected =0;
				
				var table = $("#participantResult tbody");
				
				// Check if any checkbox is selected
				table.find('tr').each(function(index) {
					 var $tds = $(this).find('td');
					 var serialNo = $tds.eq(1).find("input:hidden").val();
		
					 var checkbox_sel_val = $tds.eq(0);
					 var checkbox = checkbox_sel_val.find("input:checked").val();
					 //alert("checkbox :"+checkbox);
					 if (checkbox == 'on') {
						    selected++;

							var imt_sel_val = $tds.eq(4);
							var imt_Id = imt_sel_val.find("option:selected").val();
							
							var sentiment_sel_val = $tds.eq(11);
							var sentiment_Id = sentiment_sel_val.find("option:selected").val();
		
							//alert("Row: "+ index+ "\n checkbox: "+ checkbox+ "\n serialNo :"+ serialNo
							//+ "\n sentiment_Id: "+ sentiment_Id
							 //+ "\n imt_Id: "+ imt_Id);
		
							var error_imt_Id = $tds.eq(4).find("span").first();
							error_imt_Id.html("");
							if (imt_Id == ''|| imt_Id == 'Please Choose') {
								error_imt_Id.html("Please select IMT");
								count++;
							}
		
							var error_sentiment_Id = $tds.eq(11).find("span").first();
							error_sentiment_Id.html("");
							if (sentiment_Id == ''|| sentiment_Id == 'Please Choose') {
								error_sentiment_Id.html("Please select Sentiment");
								count++;
							}
		
					 } else{
						 
							var error_imt_Id = $tds.eq(4).find("span").first();
							error_imt_Id.html("");
						
							var error_sentiment_Id = $tds.eq(11).find("span").first();
							error_sentiment_Id.html(""); 
							
						 
					 }
					  
				  });
				
			if (selected == 0) {
				alert("Please select participant(s) to update");
				status = false;
			} else {
				
				if(selected > 0){
			    //Check if validation on IMT and 
			    if(count > 0){
			    	status = false;	
			    }else{
			    	
			    	status = confirm("Are you sure you want to update the selected record(s)?");
			    }
				
			  }
			}
		
		    //alert("status: " +status);
			return status;
		});
		
		
		$("#deleteButton").click(function(){
			var buttonclicked = $("#deleteButton").val();
			//alert("Button clicked: " + buttonclicked);
			var status = false;
			var count = 0;
			var selected = 0;
			
			var table = $("#participantResult tbody");
			
			//Check if any checkbox is selected
			table.find('tr').each(function(index) {
				 var $tds = $(this).find('td');
				 var serialNo = $tds.eq(1).find("input:hidden").val();
		
				 var checkbox_sel_val = $tds.eq(0);
				 var checkbox = checkbox_sel_val.find("input:checked").val();
				 
				 if (checkbox == 'on') {
					    selected++;		   
				 }
				  
			  });
			
		
		if (selected == 0) {
			alert("Please select participant(s) to delete");
			status = false;
		} else {
		    status = confirm("Are you sure you want to delete the selected record(s)?");
		}
		
		//alert("status: " +status);
		return status;
			
		});
		
		
		$(document).on('change','.checkboxEventClass',function(){ 
			//alert("In checkbox to page load");
			var count =0;
				
			$(".checkboxEventClass").each(function(){
				 if (this.checked) {
		              count++;
		         }
			})
			//alert("Selected count: " +count);	
			
			if(count > 0){
				$("#updateButton").show();
				$("#deleteButton").show();
			}else{
				$("#divTable").load("participantMaster.xhtml #participantResult");
				$("#updateButton").hide();
				$("#deleteButton").hide();
				
			}
			
		});
		
		
		
		$("#cancelButton").click(function(){
			
			var buttonclicked = $("#cancelButton").val();
			//alert("Button clicked: " + buttonclicked);
			
			return confirm("Any unsaved data will be lost. Are you sure?");
		});
   			
		
  /***** PARTICIPANT MASTER end *****/		
		
  /***** USER ADMIN  start *****/
		
	    $("#newUserButton").click(function(){
	    	
	    	var buttonclicked = $("#newUserButton").val();
	    	//alert("Button clicked: " + buttonclicked);
	    	var status = false;
	    	var count = 0;
            
	    	if (buttonclicked == '+ New User'){
				//alert("Modal called"); 
				//$('.modal-body').load('searchUser.xhtml');
	    		$("#userEmail").val("");
				$("#firstName").text("");
				$("#lastName").text("");
			    $("#err_email").html("");
			    $("#userNotFound").text("");
				$("#addUserBtn").hide();
				$("#searchUserModal").modal('show');
				$("#searchUserModal").modal('show');
	            count =1; 
	            			            
			} else {
	    	
			    	if (buttonclicked == 'Save User') {
			    		//alert("In loop Save User");
			    		var table = $("#userResult tbody");
		
			    		table.find('tr').each(function(index) {
			    							var $tds = $(this).find('td');
			    							//Check if serialNo is 0 then it is a new row
			    							var serialNo = $tds.eq(1).find("input:hidden").val();
		
			    							if (serialNo == 0) {
			    								//alert("serialNo is 0");
		
			    								var checkbox_sel_val = $tds.eq(0);
			    								var checkbox = checkbox_sel_val.find("input:checked").val();
			    								var email = $tds.eq(2).find("input:text").val();
			    								var username = $tds.eq(3).find("input:text").val();
			    								var imt_sel_val = $tds.eq(4);
			    								var imt_Id = imt_sel_val.find("option:selected").val();
			    								var role_sel_val = $tds.eq(6);
			    								var role_Id = role_sel_val.find("option:selected").val();
		
			    								//alert("Row: "+ index+ "\n checkbox: "+ checkbox+ "\n serialNo :"+ serialNo
			    								//	+ "\n username: "+ username+ "\n email: "+ email+ "\n role_Id: "+ role_Id
			    								 //   + "\n imt_Id: "+ imt_Id);
		
			    								var error_email = $tds.eq(2).find("span").first();
			    								error_email.html("");
			    								if (email == ''|| email == 'undefined') {
			    									//$("#err_email").html("Email cannot be blank");
			    									error_email.html("Email cannot be blank");
			    									count++;
			    								} else {
			    									if (!isValidEmailAddress(email)) {
			    										//$('#err_email').html("Invalid email format");
			    										error_email.html("Invalid email format");
			    										count++;
			    									}
			    								}
			    								
			    								var error_username = $tds.eq(3).find("span").first();
			    								error_username.html("");
			    								if (username == ''|| username == 'undefined') {
			    									//$("#err_username").html("Username cannot be blank");
			    									error_username.html("Username cannot be blank");
			    								
			    									count++;
			    								} else {
			    									if (!isValidUserName(username)) {
			    										//$('#err_username').html("Invalid username format");
			    										error_username.html("Invalid username format");
			    										count++;
			    									}
			    								}
		
			    								var error_imt_Id = $tds.eq(4).find("span").first();
			    								error_imt_Id.html("");
			    								if (imt_Id == ''|| imt_Id == 'Please Choose') {
			    									//$("#err_imt_Id").html("Please select IMT");
			    									error_imt_Id.html("Please select IMT");
			    									count++;
			    								}
		
			    								var error_role_Id = $tds.eq(6).find("span").first();
			    								error_role_Id.html("");
			    								if (role_Id == ''|| role_Id == 'Please Choose') {
			    									//$("#err_role_Id").html("Please select Role");
			    									error_role_Id.html("Please select Role");
			    									count++;
			    								}
		
			    							}
		
			    						});
		
			    	       }

		       }
	    	
	    	if (count > 0) {
	    		status = false;
	    	} else {
	    		status = true;
	    	}

	    	//alert("status: " +status);
	    	return status;
	        });
	    		
	    		$("#updateUserButton").click(function(){
	    			
	    			var buttonclicked = $("#updateUserButton").val();
	    			//alert("Button clicked: " + buttonclicked);
	    			var status = false;
	    			var count = 0;
	    			
	    			var selected =0;
	    				
	    				var table = $("#userResult tbody");
	    				
	    				// Check if any checkbox is selected
	    				table.find('tr').each(function(index) {
	    					 var $tds = $(this).find('td');
	    					 var serialNo = $tds.eq(1).find("input:hidden").val();
	    		
	    					 var checkbox_sel_val = $tds.eq(0);
	    					 var checkbox = checkbox_sel_val.find("input:checked").val();
	    					 //alert("checkbox :"+checkbox);
	    					 if (checkbox == 'on') {
	    						    selected++;
	    						    
	    							//alert("serialNo is 0");
	    							var imt_sel_val = $tds.eq(4);
	    							var imt_Id = imt_sel_val.find("option:selected").val();
	    							var role_sel_val = $tds.eq(6);
	    							var role_Id = role_sel_val.find("option:selected").val();
	    		
	    							//alert("Row: "+ index+ "\n checkbox: "+ checkbox+ "\n serialNo :"+ serialNo
	    							//	+ "\n role_Id: "+ role_Id
	    							 //   + "\n imt_Id: "+ imt_Id);
	    		
	    							var error_imt_Id = $tds.eq(4).find("span").first();
	    							error_imt_Id.html("");
	    							if (imt_Id == ''|| imt_Id == 'Please Choose') {
	    								error_imt_Id.html("Please select IMT");
	    								count++;
	    							}
	    		
	    							var error_role_Id = $tds.eq(6).find("span").first();
	    							error_role_Id.html("");
	    							if (role_Id == ''|| role_Id == 'Please Choose') {
	    								error_role_Id.html("Please select Role");
	    								count++;
	    							}
	    		
	    					 } else{
	    						 
	    							var error_imt_Id = $tds.eq(4).find("span").first();
	    							error_imt_Id.html("");
	    						
	    							var error_role_Id = $tds.eq(6).find("span").first();
	    							error_role_Id.html(""); 
	    						 
	    					 }
	    					  
	    				  });
	    				
	    			if (selected == 0) {
	    				alert("Please select user(s) to update");
	    				status = false;
	    			} else {
	    				
	    				if(selected > 0){
	    			    //Check if validation on IMT and Role
	    			    if(count > 0){
	    			    	status = false;	
	    			    }else{
	    			    	
	    			    	status = confirm("Are you sure you want to update the selected record(s)?");
	    			    }
	    				
	    			  }
	    			}
	    		
	    		    //alert("status: " +status);
	    			return status;
	    		});
	    		
	    		
	    		$("#deleteUserButton").click(function(){
	    			var buttonclicked = $("#deleteUserButton").val();
	    			//alert("Button clicked: " + buttonclicked);
	    			var status = false;
	    			var count = 0;
	    			var selected = 0;
	    			
	    			var table = $("#userResult tbody");
	    			
	    			//Check if any checkbox is selected
	    			table.find('tr').each(function(index) {
	    				 var $tds = $(this).find('td');
	    				 var serialNo = $tds.eq(1).find("input:hidden").val();
	    		
	    				 var checkbox_sel_val = $tds.eq(0);
	    				 var checkbox = checkbox_sel_val.find("input:checked").val();
	    				 
	    				 if (checkbox == 'on') {
	    					    selected++;		   
	    				 }
	    				  
	    			  });
	    			
	    		
	    		if (selected == 0) {
	    			alert("Please select user(s) to delete");
	    			status = false;
	    		} else {
	    		    	status = confirm("Are you sure you want to delete the selected record(s)?");
	    		}
	    		
	    		//alert("status: " +status);
	    		return status;
	    			
	    		});
	    		
	    		
	    	$(document).on('change','.checkboxUserEventClass',function(){ 
	    			//alert("In checkbox to page load");
	    			var count =0;
	    			
	    			$(".checkboxUserEventClass").each(function(){
	    				 if (this.checked) {
	    		              count++;
	    		         }
	    			})
	    			//alert("Selected count: " +count);	
	    			
	    			if(count > 0){
	    				$("#updateUserButton").show();
	    				$("#deleteUserButton").show();
	    			}else{
	    				$("#updateUserButton").hide();
	    				$("#deleteUserButton").hide();
	    				$("#divUserTable").load("userInfo.xhtml #userResult");
	    				
	    			}
	    			
	    		});
	    		
	    		
	    		
	    		$("#cancelUserButton").click(function(){
	    			
	    			var buttonclicked = $("#cancelUserButton").val();
	    			//alert("Button clicked: " + buttonclicked);
	    			
	    			return confirm("Any unsaved data will be lost. Are you sure?");
	    		});
	       		    			
 /***** USER ADMIN end *****/		
	    		
	    		
 /***** Common scripts start ******/
	    		
	    	$("#addPartBtn").click(function(event) {
    	          //alert('Add user clicked');
    	          //alert("EM "+ $('#userEmail').val() +" FN "+$("#firstName").text()+" LN "+ $("#lastName").text());
	    		   var table = $("#participantResult tbody");
	    		   
	    		   $("#addEmail").val("");
	    		   var matchedFlag= false;
	    		   var err_email = $("#err_email");
	    		   var partEmail = $("#userEmail").val();
	    		    
				   table.find('tr').each(function(index) {
						var $tds = $(this).find('td');
						var email = $tds.eq(2).text().trim();
						var addEmail = $("#addEmail").val(email);

						if(addEmail.val() == partEmail){
							matchedFlag = true;
						}
				   })			

				   //alert(matchedFlag);
				   
	    		   err_email.html("");
				   if(matchedFlag == true){
					  err_email.html("Participant already exist!");
					  $('#myModal').modal('show');
					  return false;
				   }else{ 
					  $("#addEmail").val(partEmail); 
					  $('#myModal').modal('hide');
					  return true;
				   }
				   
    	    });
	    	
	    	$("#addUserBtn").click(function(event) {
  	          //alert("Add user clicked");
  	          //alert("EM "+ $('#userEmail').val() +" FN "+$("#firstName").text()+" LN "+ $("#lastName").text());
	    		   var table = $("#userResult tbody");
	    		   $("#addEmail").val("");
	    		   var matchedFlag= false;
	    		   var err_email = $("#err_email");
	    		   var userEmail = $("#userEmail").val();
	    		     		   
				   table.find('tr').each(function(index) {
						var $tds = $(this).find('td');
						var email = $tds.eq(2).text().trim();
						var addEmail = $("#addEmail").val(email);
						
						if(addEmail.val() == userEmail){
							matchedFlag = true;
						}
				   })			
				   
				   //alert(matchedFlag);
				   
	    		   err_email.html("");
				   if(matchedFlag == true){
					   err_email.html("User already exist!");
					  $('#myModal').modal('show');
					  return false;
				   }else{
					  $("#addEmail").val(userEmail);
					  $('#myModal').modal('hide');
					  return true;
				   }
				   
  	    });
    	       	    	 
	     		
    		$("#userSearch").click(function () {
    				var email =	$("#userEmail").val();
    				var err_email = $("#err_email");
    				//alert(email);
    				
    				$("#addEmail").val(email);
    				$("#userNotFound").html("");
    				$("#firstName").html("");
    				$("#lastName").html("");
    				$("#addUserBtn").hide();
    				$("#addPartBtn").hide();
    				err_email.html("");
    				if (email == ''|| email == 'undefined') {
    					err_email.html("Email cannot be blank");
    					return false;
    				} else {
    					if (!isValidEmailAddress(email)) {
    						err_email.html("Invalid email format");
    					return false;
    					}
    				}
    				
    				var fName;
    				var lName;
    				var url = "https://w3.api.ibm.com/common/run/bluepages/userid/"+email+"/emailaddress&hrFirstName&hrLastName&uid?client_id=e9559df8-11f2-42aa-9d4d-ac334d7b98b9";
    				
    				$.getJSON(url, function (data, status) {
    				
    					  if (status == 'success') {
    						  $.each(data, function (key1, value1) {
    				                $.each(value1, function (key2, value2) {
    				                	if(value2 == ''){
    				                		//alert("Empty!");
    				                		//$("#userNotFound").text("User not found, try again!")
    				                		err_email.html("User not found, try again!");
    				                		$("#firstName").text("");
    				                		$("#lastName").text("");
    				                		$("#addUserBtn").hide();
    				                		$("#addPartBtn").hide();
    				                	}
    				                    $.each(value2, function (key3, value3) {
    				                        $.each(value3, function (key4, value4) {
    				                            if (key4 == 'attribute')
    				                            {
    				                                $.each(value4, function (key5, value5) {
    				                                    if(value5.name == 'hrFirstName'){
    				                                    	fName = value5.value;
    				                                    	$("#firstName").text("First Name: "+fName);
    				                                    	$("#addFirstName").val(fName);
    				                                    }
    				                                    if(value5.name == 'hrLastName'){
    				                                    	lName = value5.value;
    				                                    	$("#lastName").text("Last Name: "+lName);
    				                                    	$("#addLastName").val(lName);
    				                                    }
    				                                });
    				                                
    				                                $("#addUserBtn").show();
    				                                $("#addPartBtn").show();
    				                            }
    				                        });
    				                   });
    				                });
    				            });
    					    } 
    					});
    				return false;
    			});   	  	
    		
    		
    		function isValidEmailAddress(emailAddress) {
    			var pattern = new RegExp(
    					"^[A-Za-z0-9]+(\.[_A-Za-z0-9-]+)*@([A-Za-z0-9]+\.(IBM|ibm)\.(COM|com)|(IBM|ibm)\.(COM|com))$");
    			return pattern.test(emailAddress);
    		}
    		
    		function isValidUserName(userName) {
    			var pattern = new RegExp("[a-zA-Z]+\\s+[a-zA-Z]+$");
    			return pattern.test(userName);
    		}
    			       
/***** Common scripts end ******/	    	       

//]]>
		