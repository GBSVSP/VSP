//ActionItem =1
//Document   =2
//DocumentType =3
//ExpenseDetails =4
//ExpenseType=5
//Files=6
//IncomeType=7
//Rooms=8
//Wardrobes=9

function ActionResult(pageNo, Action)
{
    var resultMessage = null;
    var pageName = null;

    switch (pageNo) {
        case 1:
            $('#myModal').attr('data-id', '1');
            pageName = "Action Item";
            break;
        case 2:
            $('#myModal').attr('data-id', '2');
            pageName = "Document";
            break;
        case 3:
            $('#myModal').attr('data-id', '3');
            pageName ="Document Type";  
            break;
        case 4:
            $('#myModal').attr('data-id', '4');
            pageName = "Expense Details";
            break;
        case 5:
            $('#myModal').attr('data-id', '5');
            pageName = "Expense Type";
            break;
        case 6:
            $('#myModal').attr('data-id', '6');
            pageName = "File";
            break;
        case 7:
            $('#myModal').attr('data-id', '7');
            pageName ="Income Type";
            break;
        case 8:
            $('#myModal').attr('data-id', '8');
            pageName ="Room";
            break;
        case 9:
            $('#myModal').attr('data-id', '9');
            pageName ="Wardrobe"; 
            break;
        default:
            pageName = null;
    }
    $(".modal-title").text(pageName);

    switch(Action) {
        case 1:
            resultMessage = pageName + " saved successfully";
            break;
        case 2:
            resultMessage = pageName + " updated successfully";
            break;
        case 3:
            resultMessage = pageName + " deleted successfully";
            break;
        default:
            resultMessage = null;
    }
    $("#message").text(resultMessage);
    $('#myModal').modal('show');
}
//$(document).on('hide.bs.modal', '#myModal', function (e) {
//    var $modal = $(this);
//    var url = "/Income/Index";
//    var id = parseInt($modal[0].dataset.id);
//    switch (id) {
//        case 1:
//            url = "/ActionItem/ViewActionItems";
//            break;
//        case 2:
//            url = "/Document/ViewDocuments";
//            break;
//        case 3:
//            url = "/Document/ViewDocumentTypes";
//            break;
//        case 4:
//            url = "/Expense/ViewExpenseDetails";
//            break;
//        case 5:
//            url = "/Expense/ViewExpenseTypes";
//            break;
//        case 6:
//            url = "/Document/ViewFiles";
//            break;
//        case 7:
//            url = "/Income/ViewIncomeTypes";
//            break;
//        case 8:
//            url = "/Document/ViewRooms";
//            break;
//        case 9:
//            url = "/Document/ViewWardrobes";
//            break;
//        default:
//            pageName = null;
//    }
//    window.location.href = url;
//});
function DeleteConfirm(objID, pageNo) {
    var pageName = null;
    switch (pageNo) {
        case 1:
            pageName = "Action Item";
            break;
        case 2:
            pageName = "Document";
            break;
        case 3:
            pageName = "Document Type";
            break;
        case 4:
            pageName = "Expense Details";
            break;
        case 5:
            pageName = "Expense Type";
            break;
        case 6:
            pageName = "File";
            break;
        case 7:
            pageName = "Income Type";
            break;
        case 8:
            pageName = "Rooms";
            break;
        case 9:
            pageName = "Wardrobes";
            break;
        default:
            pageName = null;
    }
    $('#modelConfirm').modal('show');
    $('#confirm').attr('data-id', objID);
    $('#confirm').attr('data-pageNo', pageNo);
    $(".modal-confirm-title").text(pageName);
    return false;
}
$(document).on('click', '#confirm', function (e) {
    var $modal = $(this);
    var url = "/Income/Index";
    var objid = parseInt($modal[0].dataset.id);
    var pageNo = parseInt($modal[0].dataset.pageno);
    var deleteAction = null;
    
    switch (pageNo) {
        case 1:
            deleteAction = "DeleteActionItems";
            url = "ViewActionItems";
            break;
        case 2:
            deleteAction = "DeleteDocument";
            url = "ViewDocuments";
            break;
        case 3:
            deleteAction = "DeleteDocumentTypes";
            url = "ViewDocumentTypes";
            break;
        case 4:
            deleteAction = "DeleteExpenseDetails";
            url = "ViewExpenseDetails";
            break;
        case 5:
            deleteAction = "DeleteExpenseTypes";
            url = "ViewExpenseTypes";
            break;
        case 6:
            deleteAction = "DeleteFile";
            url = "ViewFiles";
            break;
        case 7:
            deleteAction = "DeleteIncomeTypes";
            url = "ViewIncomeTypes";
            break;
        case 8:
            deleteAction = "DeleteRoom";
            url = "ViewRooms";
            break;
        case 9:
            deleteAction = "DeleteWardrobe";
            url = "ViewWardrobes";
            break;
    }
    $.post(deleteAction, { id: objid }, function (data) {
    });
    window.location.href = url;
});
function Warning(pageNo,strMsg)
{
    var pageName = null;
    switch (pageNo) {
        case 1:
            pageName = "Action Item";
            break;
        case 2:
            pageName = "Document";
            break;
        case 3:
            pageName = "Document Type";
            break;
        case 4:
            pageName = "Expense Details";
            break;
        case 5:
            pageName = "Expense Type";
            break;
        case 6:
            pageName = "File";
            break;
        case 7:
            pageName = "Income Type";
            break;
        case 8:
            pageName = "Room";
            break;
        case 9:
            pageName = "Wardrobe";
            break;
        case 0:
            pageName = "Search";
            break;
        default:
            pageName = null;
    }
    $(".modal-title").text(pageName);
    $("#WarningMessage").text(strMsg);
    $('#modelWarning').modal('show');
}
function ChangeConfirm(pageNo, msg, objID) {
    $('#modelChangeConfirm').attr('data-id', objID);
    var pageName = null;
    switch (pageNo) {
        case 1:
            pageName = "Action Item";
            break;
        case 2:
            pageName = "Document";
            break;
        case 3:
            pageName = "Document Type";
            break;
        case 4:
            pageName = "Expense Details";
            break;
        case 5:
            pageName = "Expense Type";
            break;
        case 6:
            pageName = "File";
            break;
        case 7:
            pageName = "Income Type";
            break;
        case 8:
            pageName = "Rooms";
            break;
        case 9:
            pageName = "Wardrobes";
            break;
        default:
            pageName = null;
    }
    $('#modelChangeConfirm').modal('show');
    $(".modal-confirm-title").text(pageName);
    $(".modal-body").text(msg);
    return false;
}
$(document).on('click', '#changeOK', function (e) {
    window.location.href = "/Expense/EditExpenseDetails/1?intMonth=" + $("#ddlMonth").val();
});
//$(document).on('click', '#changeOK', function (e) {
//    window.location.href = "/Expense/EditExpenseDetails/1?intMonth=0" + $("#ddlMonth").val();
//});
$(document).on('click', '#btnclosex', function (e) {
    var id = parseInt($(this)[0].parentElement.parentElement.dataset.id);
    $('#ddlMonth option[value=' + id + ']').prop('selected', true);
});
$(document).on('click', '#btnclose', function (e) {
    var id = parseInt($(this)[0].parentElement.parentElement.dataset.id);
    $('#ddlMonth option[value='+id+']').prop('selected', true);
});
$(document).on('keyup', '#txtDocument', function (e) {
    if ($('#txtDocument').val().length > 2) {
        $.ajax({
            type: 'POST',
            url: "/Home/SearchDocuments",
            dataType: 'json',
            data: { 'SearchText': $('#txtDocument').val() },
            success: function (data) {
                if (data.length > 0) {
                    $("#searchWarning").text("");
                    var innerHTML = "";
                    $("#divSearchContent").html(innerHTML);
                    innerHTML += '<div><ul class="breadcrumb"><li>Result related to </li><li><span class="divider">»</span></li><li>' + $('#txtDocument').val() + '</li></ul></div> <div class="panel panel-default pre-scrollable"><div class="table-responsive">';
                    innerHTML += '<table class="table table-striped ">';
                    innerHTML += '<tr><th>Sl.No</th><th>Document Name</th><th>Document Location</th><th>Document Type Name</th><th>Validity Date</th></tr>';
                    for (i = 0; i < data.length; i++) {

                        innerHTML += '<tr><td><span>' + (i + 1) + '</span></td>';
                        innerHTML += '<td><span>' + data[i].DocumentName + '</span></td>';
                        innerHTML += '<td><span>' + data[i].File.Wardrobe.WardrobeColor + ("--") + data[i].File.FileColor + ("--") + data[i].File.FileName + '</span></td>';
                        innerHTML += '<td><span>' + data[i].DocumentType.DocumentTypeName + '</span></td>';
                        innerHTML += '<td><span>' + ConvertToJsonDate(data[i].ValidityDate) + '</span></td></tr>';
                    }
                    innerHTML += '</table></div>';
                    $("#divSearchContent").append(innerHTML);
                    $("#divControllerContent").attr("style", "display:none;");
                    $("#divSearchContent").attr("style", "display:block;padding-top:25px;");
                }
                else {
                    //Warning(0, "No Match Found");
                    $("#searchWarning").text("No Match Found");
                    //$("#txtDocument").attr("data-container", "body");
                    //$("#txtDocument").attr("data-toggle","popover");
                    //$("#txtDocument").attr("data-placement","bottom");
                    //$("#txtDocument").attr("data-content", "No Match Found");


                    $("#divControllerContent").attr("style", "display:block;padding-top:25px;");
                    $("#divSearchContent").attr("style", "display:none;");
                }
            },
            error: function (xhr, status, err) {
                document.write(err.Message + status + '--' + xhr.responseText);

            }
            //error: function (ex) {
            //    var r = jQuery.parseJSON(data);
            //    alert("Message: " + r.Message);
            //    alert("StackTrace: " + r.StackTrace);
            //    alert("ExceptionType: " + r.ExceptionType);
            //}
        });
    }
    if ($('#txtDocument').val().length == 0)
    {
        $("#searchWarning").text("");
        $("#divControllerContent").attr("style", "display:block;padding-top:25px;");
        $("#divSearchContent").attr("style", "display:none;");
    }
});
// Converting C# date format ti Json Data
function ConvertToJsonDate(value) {
    var monthShortNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun","Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
        var pattern = /Date\(([^)]+)\)/;
        var results = pattern.exec(value);
        var dt = new Date(parseFloat(results[1]));
        return dt.getDate() + "-" + monthShortNames[dt.getMonth()] + "-" + dt.getFullYear();
}
// Navigation Active

var navurl = window.location.pathname.split("/");
var controller = navurl[1];
//alert(controller);
switch (controller) {
    case "Document":
        $('#liDocument').addClass("active");
        $('#liHome').removeClass("active");
        $('#liExpense').removeClass("active");
        $('#liConfiguration').removeClass("active");
        //ActionItem
        break;
    case "Expense":
        $('#liDocument').removeClass("active");
        $('#liHome').removeClass("active");
        $('#liExpense').addClass("active");
        $('#liConfiguration').removeClass("active");
        break;
    case "ActionItem":
    case "Income":
        $('#liDocument').removeClass("active");
        $('#liHome').removeClass("active");
        $('#liExpense').removeClass("active");
        $('#liConfiguration').addClass("active");
        break;
    default:
        $('#liDocument').removeClass("active");
        $('#liHome').addClass("active");
        $('#liExpense').removeClass("active");
        $('#liConfiguration').removeClass("active");


} 
