function showDeleteDialogMsg() {
	if (!confirm("Are you sure you want to delete the selected record(s)?"))
		return false;
	else
		return true;
}

function showUpdateDialogMsg() {
	if (!confirm("Are you sure you want to update the selected record(s)?"))
		return false;
	else
		return true;
}

function showCancelDialogMsg() {
	if (!confirm("You will lose the entered data, do you want to cancel?"))
		return false;
	else
		return true;
}

function checkSelectedCheckBox(opvalue) {

	var count = 0;
	var totalsize = document.getElementById("totalCheckedSize").value;
    
	for (var i = 0; i < totalsize; i++) {

		var myCollection = document.getElementById("userResult:"+i+":checkboxClickedFlag");
		if (myCollection.checked == true) {
			count++;
			break;
		}
	}

	if (count > 0) {
		var flag;
		var val = opvalue;
		if (val == 'update') {
			flag = showUpdateDialogMsg();
		} else {
			flag = showDeleteDialogMsg();
		}
		return flag;
	} else {
		alert("Please select the checkbox");
		return false;
	}

}

