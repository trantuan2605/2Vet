//$( document ).ready(bindClick);
var tableUrineHtml;
var urineCodeHidden;
var lstUrineDel = [];
var currentPage;
var urineDataTable;
$(document).ready(function() {
	urineDataTable = 'urineDataTbl';
	drawTable(urineDataTable);
});

function bindClick() {
//	$(".text_data").on("click", function() {
	$('#urineDataTbl').on("click", "tbody tr", function() {
		var rowData = $(this).children("td").map(function() {
			return $(this).text();
		}).get();
		var urineCode = rowData[1];
		urineCodeHidden = urineCode;
		action = "urine/showDetail?urineCode=" + urineCode;
		$('#myModal').modal({
		//$('#editPetModal').modal({
           backdrop: 'static',
           keyboard: false
	    });
		$.ajax({
		        type: "GET",
		        contentType: "application/html",
		        url: action,
		        dataType: 'html',
		        cache: false,
		        timeout: 600000,
		        success: function (response) {
		        	$('#detailUrine').replaceWith(response);
		        },
		        error: function (e) {
		            alert(e);
		        }
		    });
	});
}


function showEditPopup() {
	if(!currentPage) {
		currentPage = 1;
	}
	lstUrineDel = [];
	$('#editUrineModal').modal({
       backdrop: 'static',
       keyboard: false
    });
	action = "urine/showDetail?urineCode=" + urineCodeHidden + "&mode=edit";
	$.ajax({
        type: "GET",
        contentType: "application/html",
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	$('#detailUrineEdit').replaceWith(response);
        },
        error: function (e) {
            alert(e);
        }
    });
}

function showAddPopup() {
	$('.txt-popup-add-cus').val('');
	$(".slt-popup-add-cus").val("0");
	$("input, select").removeClass('is-invalid');
	$("input:radio").attr("checked", false);
	$('#modalAddUrine').modal({
           backdrop: 'static',
           keyboard: false
    });
}


function addAction() {
	action = "urine/add";
	var paramBean = {};
	var urine = $('form[name=urineFrm]').serializeToJSON();
	
	paramBean.data = urine;
	paramBean.currentPage = 1;
	
	var searchAdvanceParam = $('form[name=urineSearchFrm]').serializeToJSON();
	var advanceParam = {};
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;
	if(validateUrineFormAdd(urine)){
			$.ajax({
	        type: "POST",
	        contentType : 'application/json',
	        url: action,
	        dataType: 'html',
	        data : JSON.stringify(paramBean),
	        cache: false,
	        timeout: 600000,
	        success: function (response) {
	        	mess = $(response).find("input[id='msg-add']").val();
	        	showDialogConfirm(mess);
        		$('#modalAddUrine').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		$("#refresh-section").replaceWith(response);
	        		drawTable(urineDataTable);
	        	}
	        	
	        },
	        error: function (e) {
	            console.log(e);
	            mess = "SYS_ERR";
	            showDialogConfirm(mess);
	        }
	    });
	}
	
}

function editAction() {
	action = "urine/edit";
	var urine= $('form[name=urineFrmEdit]').serializeToJSON();
	var param = {};
	param.urineCode = urine.urineCode;
	var paramBean = {};
	paramBean.data = urine;
	paramBean.currentPage = currentPage;
	
	var searchAdvanceParam = $('form[name=urineSearchFrm]').serializeToJSON();
	var advanceParam = {};
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;
	
	if(validateUrineFormEdit(urine)){
			$.ajax({
	        type: "POST",
	        contentType : 'application/json',
	        url: action,
	        dataType: 'html',
	        data : JSON.stringify(paramBean),
	        cache: false,
	        timeout: 600000,
	        success: function (response) {
	        	mess = $(response).find("input[id='msg-add']").val();
	        	showDialogConfirm(mess);
        		$('#editUrineModal').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		$("#refresh-section").replaceWith(response);
	        		drawTable(urineDataTable);
	        	}
	        	
	        },
	        error: function (e) {
	            console.log(e);
	            mess = "SYS_ERR";
	            showDialogConfirm(mess);
	        }
	    });
	}
	
}

function validateUrineFormAdd(urine) {
	objErrors = [];
	result = true;
	objError = {}
	
	if(!urine.urineCode) {
		result = false;
		objError = getObjectError(false, 'urineCodeAdd', 'Mã xét nghiệm nước tiểu là bắt buộc');
	} else {
		objError = getObjectError(true, 'urineCodeAdd', '');
	}
	objErrors.push(objError);
	
	if(!urine.urineName) {
		result = false;
		objError = getObjectError(false, 'urineNameAdd', 'Tên xét nghiệm nước tiểu là bắt buộc');
	} else {
		objError = getObjectError(true, 'urineNameAdd', '');
	}
	objErrors.push(objError);
	
	showErr(objErrors);
	
	return result;
}

function validateUrineFormEdit(urine) {
	objErrors = [];
	result = true;
	objError = {}
	if(!urine.urineName) {
		result = false;
		objError = getObjectError(false, 'urineNameEdit', 'Tên xét nghiệm nước tiểu là bắt buộc');
	} else {
		objError = getObjectError(true, 'urineNameEdit', '');
	}
	objErrors.push(objError);
	
	showErr(objErrors);
	return result;
}

