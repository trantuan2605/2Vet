//$( document ).ready(bindClick);
var tablePetHtml;
var serviceCodeHidden;
var currentPage;
var serviceDataTable;
$(document).ready(function() {
	serviceDataTable = 'serviceDataTbl';
	drawTable(serviceDataTable);
});

function bindClick() {
//	$(".text_data").on("click", function() {
	$('#serviceDataTbl').on("click", "tbody tr", function() {
		var rowData = $(this).children("td").map(function() {
			return $(this).text();
		}).get();
		var serviceCode = rowData[1];
		serviceCodeHidden = serviceCode;
		action = "service/showDetail?serviceCode=" + serviceCode;
		$('#serviceModal').modal({
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
		        	$('#detailService').replaceWith(response);
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
	$('#editServiceModal').modal({
       backdrop: 'static',
       keyboard: false
    });
	action = "service/showDetail?serviceCode=" + serviceCodeHidden + "&mode=edit";
	$.ajax({
        type: "GET",
        contentType: "application/html",
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	$('#detailServiceEdit').replaceWith(response);
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
	$('#modalAddService').modal({
           backdrop: 'static',
           keyboard: false
    });
}

function addAction() {
	action = "service/add";
	var paramBean = {};
	var service= $('form[name=serviceFrm]').serializeToJSON();
	paramBean.data = service;
	if(validateServiceFormAdd(service)){
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
        		$('#modalAddService').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		$("#refresh-section").replaceWith(response);
	        		drawTable(serviceDataTable);
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
	action = "service/edit";
	var service= $('form[name=serviceFrmEdit]').serializeToJSON();
	var paramBean = {};
	paramBean.data = service;
	
	if(validateServiceFormEdit(service)){
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
        		$('#editServiceModal').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		$("#refresh-section").replaceWith(response);
	        		drawTable(serviceDataTable);
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

function validateServiceFormAdd(service) {
	objErrors = [];
	result = true;
	objError = {}
	
	if(!service.serviceCode) {
		result = false;
		objError = getObjectError(false, 'servicecodeAdd', 'Mã dịch vụ là bắt buộc');
	} else {
		objError = getObjectError(true, 'servicecodeAdd', '');
	}
	objErrors.push(objError);
	
	if(!service.serviceName) {
		result = false;
		objError = getObjectError(false, 'servicenameAdd', 'Tên dịch vụ là bắt buộc');
	} else {
		objError = getObjectError(true, 'servicenameAdd', '');
	}
	objErrors.push(objError);
	
	showErr(objErrors);
	return result;
}

function validateServiceFormEdit(service) {
	objErrors = [];
	result = true;
	objError = {}
	if(!service.serviceName) {
		result = false;
		objError = getObjectError(false, 'servicenameEdit', 'Tên dịch vụ là bắt buộc');
	} else {
		objError = getObjectError(true, 'servicenameEdit', '');
	}
	objErrors.push(objError);
	
	showErr(objErrors);
	return result;
}
