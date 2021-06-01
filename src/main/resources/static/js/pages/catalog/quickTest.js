//$( document ).ready(bindClick);
var quickCodeHidden;
var currentPage;
var quickTestDataTable;
$(document).ready(function() {
	quickTestDataTable = 'quickTestDataTbl';
	drawTable(quickTestDataTable);
});

function bindClick() {
//	$(".text_data").on("click", function() {
	$('#quickTestDataTbl').on("click", "tbody tr", function() {
		var rowData = $(this).children("td").map(function() {
			return $(this).text();
		}).get();
		var quickCode = rowData[1];
		quickCodeHidden = quickCode;
		action = "quicktest/showDetail?quickCode=" + quickCode;
		$('#quickTestModal').modal({
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
		        	$('#detailQuickTest').replaceWith(response);
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
	$('#editQuickTestModal').modal({
       backdrop: 'static',
       keyboard: false
    });
	action = "quicktest/showDetail?quickCode=" + quickCodeHidden + "&mode=edit";
	$.ajax({
        type: "GET",
        contentType: "application/html",
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	$('#detailQuickTestEdit').replaceWith(response);
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
	$('#modalAddQuickTest').modal({
           backdrop: 'static',
           keyboard: false
    });
}

function addAction() {
	action = "quicktest/add";
	var paramBean = {};
	var quick= $('form[name=quickTestFrm]').serializeToJSON();
	paramBean.data = quick;
	if(validateQuickTestFormAdd(quick)){
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
        		$('#modalAddQuickTest').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		$("#refresh-section").replaceWith(response);
	        		drawTable(quickTestDataTable);
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
	action = "quicktest/edit";
	var quick= $('form[name=quickTestFrmEdit]').serializeToJSON();
	var paramBean = {};
	paramBean.data = quick;
	
	if(validateQuickTestFormEdit(quick)){
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
        		$('#editQuickTestModal').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		$("#refresh-section").replaceWith(response);
	        		drawTable(quickTestDataTable);
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

function validateQuickTestFormAdd(quick) {
	objErrors = [];
	result = true;
	objError = {}
	
	if(!quick.quickCode) {
		result = false;
		objError = getObjectError(false, 'quickcodeAdd', 'Mã test nhanh là bắt buộc');
	} else {
		objError = getObjectError(true, 'quickcodeAdd', '');
	}
	objErrors.push(objError);
	
	if(!quick.quickName) {
		result = false;
		objError = getObjectError(false, 'quicknameAdd', 'Tên test nhanh là bắt buộc');
	} else {
		objError = getObjectError(true, 'quicknameAdd', '');
	}
	objErrors.push(objError);
	
	showErr(objErrors);
	return result;
}

function validateQuickTestFormEdit(quick) {
	objErrors = [];
	result = true;
	objError = {}
	if(!quick.quickName) {
		result = false;
		objError = getObjectError(false, 'quicknameEdit', 'Tên test nhanh là bắt buộc');
	} else {
		objError = getObjectError(true, 'quicknameEdit', '');
	}
	objErrors.push(objError);
	
	showErr(objErrors);
	return result;
}
