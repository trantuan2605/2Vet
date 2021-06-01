//$( document ).ready(bindClick);
var xQCodeHidden;
var currentPage;
$(document).ready(function() {
	bindClick();
	validateAdd();
});

function bindClick() {
	$(".text_data").on("click", function() {
		var rowData = $(this).children("td").map(function() {
			return $(this).text();
		}).get();
		var xQCode = rowData[1];
		xQCodeHidden = xQCode;
		action = "xray/showDetail?xQCode=" + xQCode;
		$('#myModal').modal({
		//$('#editCusModal').modal({
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
		        	$('#detailXRay').replaceWith(response);
		        },
		        error: function (e) {
		            alert(e);
		        }
		    });
	});
}

function nextPage(e) {
		$(".page-item").removeClass("active");
		$(e.parentNode).addClass("active");
		pageNum = e.textContent;
		currentPage = pageNum;
		action = "xray/searchAdvance";
		var xq= $('form[name=xRaySearchFrm]').serializeToJSON();
		var paramBean = {};
		paramBean.data = xq;
		paramBean.currentPage = currentPage;
		$.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: action,
	        dataType: 'html',
	        data : JSON.stringify(paramBean),
	        cache: false,
	        timeout: 600000,
	        success: function (response) {
	        	$("#refresh-section").replaceWith(response);
				$(e.parentNode).addClass("active");
	        	bindClick();
	        },
	        error: function (e) {
	            alert(e);
	        }
	    });
	}
	
function firstPage(){
	currentPage = 1;
	action = "xray/searchAdvance";
	var xray= $('form[name=xRaySearchFrm]').serializeToJSON();
	var paramBean = {};
	paramBean.data = xray;
	paramBean.currentPage = 1;
	$.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: action,
	        dataType: 'html',
	        data : JSON.stringify(paramBean),
	        cache: false,
	        timeout: 600000,
	        success: function (response) {
	        	$("#refresh-section").replaceWith(response);
	        	bindClick();
	        },
	        error: function (e) {
	            alert(e);
	        }
	    });
}

function lastPage(){
	pageNum = $(".page-item:nth-last-child(2)")[0].textContent;
	currentPage = pageNum;
	action = "xray/searchAdvance";
	var xray= $('form[name=xRaySearchFrm]').serializeToJSON();
	var paramBean = {};
	paramBean.data = xray;
	paramBean.currentPage = currentPage;
	$.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: action,
	        dataType: 'html',
	        data : JSON.stringify(paramBean),
	        cache: false,
	        timeout: 600000,
	        success: function (response) {
	        	$("#refresh-section").replaceWith(response);
	        	bindClick();
	        },
	        error: function (e) {
	            alert(e);
	        }
	    });
}

function showEditPopup() {
	if(!currentPage) {
		currentPage = 1;
	}
	$('#editXRayModal').modal({
       backdrop: 'static',
       keyboard: false
    });
	action = "xray/showDetail?xQCode=" + xQCodeHidden + "&mode=edit";
	$.ajax({
        type: "GET",
        contentType: "application/html",
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	$('#detailXRayEdit').replaceWith(response);
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
	
	$('#modalAddXRay').modal({
           backdrop: 'static',
           keyboard: false
    });
}

function addAction() {
	action = "xray/add";
	var paramBean = {};
	var xray = $('form[name=xRayFrm]').serializeToJSON();
	paramBean.data = xray;
	paramBean.currentPage = 1;

	var param = {};
	param.xQCode = xray.xQCode;

	var searchAdvanceParam = $('form[name=xRaySearchFrm]').serializeToJSON();
	var advanceParam = {};
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;

	if ($('#xRayForm').valid()) {
		$.ajax({
			type : "POST",
			contentType : 'application/json',
			url : action,
			dataType : 'html',
			data : JSON.stringify(paramBean),
			cache : false,
			timeout : 600000,
			success : function(response) {
				mess = $(response).find("input[id='msg-add']").val();
				showDialogConfirm(mess);
				$('#modalAddXRay').modal('hide');
				if (mess == 'SUCCESS') {
					$("#refresh-section").replaceWith(response);
					bindClick();
					$(".page-item").removeClass("active");
					$(".page-item:nth-child(2)").addClass("active");
				}

			},
			error : function(e) {
				console.log(e);
				mess = "SYS_ERR";
				showDialogConfirm(mess);
			}
		});

	}
}

function editAction() {
	action = "xray/edit";
	var xray = $('form[name=objFrmEdit]').serializeToJSON();
	var param = {};
	param.xQCode = xray.xQCode;

	var paramBean = {};
	paramBean.data = xray;
	paramBean.currentPage = currentPage;

	var searchAdvanceParam = $('form[name=xRaySearchFrm]').serializeToJSON();
	var advanceParam = {};
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;

	if (validateXRayForm(xray)) {
		$.ajax({
			type : "POST",
			contentType : 'application/json',
			url : action,
			dataType : 'html',
			data : JSON.stringify(paramBean),
			cache : false,
			timeout : 600000,
			success : function(response) {
				mess = $(response).find("input[id='msg-add']").val();
				showDialogConfirm(mess);
				$('#editXRayModal').modal('hide');
				if (mess == 'SUCCESS') {
					$("#refresh-section").replaceWith(response);
					bindClick();
				}

			},
			error : function(e) {
				console.log(e);
				mess = "SYS_ERR";
				showDialogConfirm(mess);
			}
		});
	}

}

function validateAdd() {
	// add the rule here
	$.validator.addMethod("valueNotEquals", function(value, element, arg) {
		return arg !== value;
	}, "Value must not equal arg.");
	$('#xRayForm').validate({
		rules : {
			xQCode : {
				required : true,
				maxlength : 5
			},
			xQName : {
				required : true
			},

		},
		messages : {
			xQCode : {
				required : "Mã X-Quang là bắt buộc",
				maxlength : "Mã X-Quang không chứa nhiều hơn 5 ký tự"
			},

			xQName : {
				required : "Tên X-Quang là bắt buộc",
			},
		},
		errorElement : 'span',
		errorPlacement : function(error, element) {
			error.addClass('invalid-feedback');
			element.closest('.msg-warning').append(error);
		},
		highlight : function(element, errorClass, validClass) {
			$(element).addClass('is-invalid');
		},
		unhighlight : function(element, errorClass, validClass) {
			$(element).removeClass('is-invalid');
		}
	});
}



function validateXRayForm(xray) {
	objErrors = [];
	result = true;
	objError = {}
	if(!xray.xQName) {
		result = false;
		objError = getObjectError(false, 'xQNameEdit', 'Tên X-Quang là bắt buộc');
	} else {
		objError = getObjectError(true, 'xQNameEdit', '');
	}
	objErrors.push(objError);
	
	showErr(objErrors);
	return result;
}
function getObjectError(valid, idInput, mess){
	objError = {};
	objError.valid = valid;
	objError.idInput = idInput;
	objError.errMessage = mess;
	return objError;
}

function searchAdvance() {
	action = "xray/searchAdvance";
	var xray= $('form[name=xRaySearchFrm]').serializeToJSON();
	var paramBean = {};
	paramBean.data = xray;
	$.ajax({
        type: "POST",
        contentType : 'application/json',
        url: action,
        dataType: 'html',
        data : JSON.stringify(paramBean),
        cache: false,
        timeout: 600000,
        success: function (response) {
        	$("#refresh-section").replaceWith(response);
        	bindClick();
        },
        error: function (e) {
            console.log(e);
            alert(e);
        }
    });
}