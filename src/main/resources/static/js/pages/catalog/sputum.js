//$( document ).ready(bindClick);
var sputumCodeHidden;
var lstUrineDel = [];
var currentPage;
var sputumDataTable;
$(document).ready(function() {
	sputumDataTable = 'sputumDataTbl';
	drawTable(sputumDataTable);
});

function bindClick() {
//	$(".text_data").on("click", function() {
	$('#sputumDataTbl').on("click", "tbody tr", function() {
		var rowData = $(this).children("td").map(function() {
			return $(this).text();
		}).get();
		var sputumCode = rowData[1];
		sputumCodeHidden = sputumCode;
		action = "sputum/showDetail?sputumCode=" + sputumCode;
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
		        	$('#detailSputum').replaceWith(response);
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
	$('#editSputumModal').modal({
       backdrop: 'static',
       keyboard: false
    });
	action = "sputum/showDetail?sputumCode=" + sputumCodeHidden + "&mode=edit";
	$.ajax({
        type: "GET",
        contentType: "application/html",
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	$('#detailSputumEdit').replaceWith(response);
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
	$('#modalAddSputum').modal({
           backdrop: 'static',
           keyboard: false
    });
}


function addAction() {
	action = "sputum/add";
	var paramBean = {};
	var sputum= $('form[name=sputumFrm]').serializeToJSON();
	
	paramBean.data = sputum;
	paramBean.currentPage = 1;
	
	var searchAdvanceParam = $('form[name=sputumSearchFrm]').serializeToJSON();
	var advanceParam = {};
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;
	if(validateSputumFormAdd(sputum)){
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
        		$('#modalAddSputum').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		$("#refresh-section").replaceWith(response);
	        		drawTable(sputumDataTable);
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
	action = "sputum/edit";
	var sputum= $('form[name=sputumFrmEdit]').serializeToJSON();
	var param = {};
	param.sputumCode = sputum.sputumCode;
	var paramBean = {};
	paramBean.data = sputum;
	paramBean.currentPage = currentPage;
	
	var searchAdvanceParam = $('form[name=sputumSearchFrm]').serializeToJSON();
	var advanceParam = {};
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;
	
	if(validateSputumFormEdit(sputum)){
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
        		$('#editSputumModal').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		$("#refresh-section").replaceWith(response);
	        		drawTable(sputumDataTable);
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

function validateSputumFormAdd(sputum) {
	objErrors = [];
	result = true;
	objError = {}
	
	if(!sputum.sputumCode) {
		result = false;
		objError = getObjectError(false, 'sputumCodeAdd', 'Mã xét nghiệm địch đờm là bắt buộc');
	} else {
		objError = getObjectError(true, 'sputumCodeAdd', '');
	}
	objErrors.push(objError);
	
	if(!sputum.sputumName) {
		result = false;
		objError = getObjectError(false, 'sputumNameAdd', 'Tên xét nghiệm dịch đờm là bắt buộc');
	} else {
		objError = getObjectError(true, 'sputumNameAdd', '');
	}
	objErrors.push(objError);
	
	showErr(objErrors);
	
	return result;
}

function validateSputumFormEdit(sputum) {
	objErrors = [];
	result = true;
	objError = {}
	if(!sputum.sputumName) {
		result = false;
		objError = getObjectError(false, 'sputumNameEdit', 'Tên xét nghiệm địch đờm là bắt buộc');
	} else {
		objError = getObjectError(true, 'sputumNameEdit', '');
	}
	objErrors.push(objError);
	
	showErr(objErrors);
	return result;
}

