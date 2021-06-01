//$( document ).ready(bindClick);
var tablePetHtml;
var vacineCodeHidden;
var currentPage;
var vacineDataTable;
$(document).ready(function() {
	vacineDataTable = 'vacineDataTbl';
	drawTable(vacineDataTable);
});

function bindClick() {
//	$(".text_data").on("click", function() {
	$('#vacineDataTbl').on("click", "tbody tr", function() {
		var rowData = $(this).children("td").map(function() {
			return $(this).text();
		}).get();
		var vacineCode = rowData[1];
		vacineCodeHidden = vacineCode;
		action = "vacination/showDetail?vacineCode=" + vacineCode;
		$('#vacineModal').modal({
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
		        	$('#detailVacine').replaceWith(response);
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
	$('#editVacineModal').modal({
       backdrop: 'static',
       keyboard: false
    });
	action = "vacination/showDetail?vacineCode=" + vacineCodeHidden + "&mode=edit";
	$.ajax({
        type: "GET",
        contentType: "application/html",
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	$('#detailVacineEdit').replaceWith(response);
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
	$('#modalAddVacine').modal({
           backdrop: 'static',
           keyboard: false
    });
}

function addAction() {
	action = "vacination/add";
	var paramBean = {};
	var vacine= $('form[name=vacineFrm]').serializeToJSON();
	paramBean.data = vacine;
	if(validateVacineFormAdd(vacine)){
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
        		$('#modalAddVacine').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		$("#refresh-section").replaceWith(response);
	        		drawTable(vacineDataTable);
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
	action = "vacination/edit";
	var vacine= $('form[name=vacineFrmEdit]').serializeToJSON();
	var paramBean = {};
	paramBean.data = vacine;
	
	if(validateVacineFormEdit(vacine)){
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
        		$('#editVacineModal').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		$("#refresh-section").replaceWith(response);
	        		drawTable(vacineDataTable);
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

function validateVacineFormAdd(vacine) {
	objErrors = [];
	result = true;
	objError = {}
	
	if(!vacine.vacineCode) {
		result = false;
		objError = getObjectError(false, 'vacinecodeAdd', 'Mã tiêm phòng là bắt buộc');
	} else {
		objError = getObjectError(true, 'vacinecodeAdd', '');
	}
	objErrors.push(objError);
	
	if(!vacine.vacineName) {
		result = false;
		objError = getObjectError(false, 'vacinenameAdd', 'Tên tiêm phòng là bắt buộc');
	} else {
		objError = getObjectError(true, 'vacinenameAdd', '');
	}
	objErrors.push(objError);
	
	showErr(objErrors);
	return result;
}

function validateVacineFormEdit(vacine) {
	objErrors = [];
	result = true;
	objError = {}
	if(!vacine.vacineName) {
		result = false;
		objError = getObjectError(false, 'vacinenameEdit', 'Tên tiêm phòng là bắt buộc');
	} else {
		objError = getObjectError(true, 'vacinenameEdit', '');
	}
	objErrors.push(objError);
	
	showErr(objErrors);
	return result;
}
