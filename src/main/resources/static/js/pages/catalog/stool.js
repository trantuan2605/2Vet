//$( document ).ready(bindClick);
var stoolCodeHidden;
var lstUrineDel = [];
var currentPage;
var stoolDataTable;
$(document).ready(function() {
	stoolDataTable = 'stoolDataTbl';
	drawTable(stoolDataTable);
});

function bindClick() {
//	$(".text_data").on("click", function() {
	$('#stoolDataTbl').on("click", "tbody tr", function() {
		var rowData = $(this).children("td").map(function() {
			return $(this).text();
		}).get();
		var stoolCode = rowData[1];
		stoolCodeHidden = stoolCode;
		action = "stool/showDetail?stoolCode=" + stoolCode;
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
		        	$('#detailStool').replaceWith(response);
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
	$('#editStoolModal').modal({
       backdrop: 'static',
       keyboard: false
    });
	action = "stool/showDetail?stoolCode=" + stoolCodeHidden + "&mode=edit";
	$.ajax({
        type: "GET",
        contentType: "application/html",
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	$('#detailStoolEdit').replaceWith(response);
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
	$('#modalAddStool').modal({
           backdrop: 'static',
           keyboard: false
    });
}


function addAction() {
	action = "stool/add";
	var paramBean = {};
	var stool= $('form[name=stoolFrm]').serializeToJSON();
	
	paramBean.data = stool;
	paramBean.currentPage = 1;
	
	var searchAdvanceParam = $('form[name=stoolSearchFrm]').serializeToJSON();
	var advanceParam = {};
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;
	if(validateStoolFormAdd(stool)){
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
        		$('#modalAddStool').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		$("#refresh-section").replaceWith(response);
	        		drawTable(stoolDataTable);
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
	action = "stool/edit";
	var stool= $('form[name=stoolFrmEdit]').serializeToJSON();
	var param = {};
	param.stoolCode = stool.stoolCode;
	var paramBean = {};
	paramBean.data = stool;
	paramBean.currentPage = currentPage;
	
	var searchAdvanceParam = $('form[name=stoolSearchFrm]').serializeToJSON();
	var advanceParam = {};
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;
	
	if(validateStoolFormEdit(stool)){
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
        		$('#editStoolModal').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		$("#refresh-section").replaceWith(response);
	        		drawTable(stoolDataTable);
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

function validateStoolFormAdd(stool) {
	objErrors = [];
	result = true;
	objError = {}
	
	if(!stool.stoolCode) {
		result = false;
		objError = getObjectError(false, 'stoolCodeAdd', 'Mã xét nghiệm phân là bắt buộc');
	} else {
		objError = getObjectError(true, 'stoolCodeAdd', '');
	}
	objErrors.push(objError);
	
	if(!stool.stoolName) {
		result = false;
		objError = getObjectError(false, 'stoolNameAdd', 'Tên xét nghiệm phân là bắt buộc');
	} else {
		objError = getObjectError(true, 'stoolNameAdd', '');
	}
	objErrors.push(objError);
	
	showErr(objErrors);
	
	return result;
}

function validateStoolFormEdit(stool) {
	objErrors = [];
	result = true;
	objError = {}
	if(!stool.stoolName) {
		result = false;
		objError = getObjectError(false, 'stoolNameEdit', 'Tên xét nghiệm phân là bắt buộc');
	} else {
		objError = getObjectError(true, 'stoolNameEdit', '');
	}
	objErrors.push(objError);
	
	showErr(objErrors);
	return result;
}

