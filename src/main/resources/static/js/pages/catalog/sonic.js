//$( document ).ready(bindClick);
var superSonicCodeHidden;
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
		var superSonicCode = rowData[1];
		superSonicCodeHidden = superSonicCode;
		action = "sonic/showDetail?superSonicCode=" + superSonicCode;
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
		        	$('#detailSuperSonic').replaceWith(response);
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
		action = "sonic/searchAdvance";
		var customer= $('form[name=superSonicSearchFrm]').serializeToJSON();
		var paramBean = {};
		paramBean.data = customer;
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
	action = "sonic/searchAdvance";
	var sonic= $('form[name=superSonicSearchFrm]').serializeToJSON();
	var paramBean = {};
	paramBean.data = sonic;
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
	action = "sonic/searchAdvance";
	var sonic= $('form[name=superSonicSearchFrm]').serializeToJSON();
	var paramBean = {};
	paramBean.data = sonic;
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
	$('#editSuperSonicModal').modal({
       backdrop: 'static',
       keyboard: false
    });
	action = "sonic/showDetail?superSonicCode=" + superSonicCodeHidden + "&mode=edit";
	$.ajax({
        type: "GET",
        contentType: "application/html",
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	$('#detailSuperSonicEdit').replaceWith(response);
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
	
	$('#modalAddSuperSonic').modal({
           backdrop: 'static',
           keyboard: false
    });
}

function addAction() {
	action = "sonic/add";
	var paramBean = {};
	var sonic = $('form[name=superSonicFrm]').serializeToJSON();
	paramBean.data = sonic;
	paramBean.currentPage = 1;

	var param = {};
	param.superSonicCode = sonic.superSonicCode;

	var searchAdvanceParam = $('form[name=superSonicSearchFrm]').serializeToJSON();
	var advanceParam = {};
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;

	if ($('#superSonicForm').valid()) {
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
				$('#modalAddSuperSonic').modal('hide');
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
	action = "sonic/edit";
	var sonic = $('form[name=objFrmEdit]').serializeToJSON();
	var param = {};
	param.superSonicCode = sonic.superSonicCode;

	var paramBean = {};
	paramBean.data = sonic;
	paramBean.currentPage = currentPage;

	var searchAdvanceParam = $('form[name=superSonicSearchFrm]').serializeToJSON();
	var advanceParam = {};
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;

	if (validateSuperSonicForm(sonic)) {
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
				$('#editSuperSonicModal').modal('hide');
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
	$('#superSonicForm').validate({
		rules : {
			superSonicCode : {
				required : true,
				maxlength : 5
			},
			superSonicName : {
				required : true
			},

		},
		messages : {
			superSonicCode : {
				required : "Mã siêu âm là bắt buộc",
				maxlength : "Mã siêu âm không chứa nhiều hơn 5 ký tự"
			},

			superSonicName : {
				required : "Tên siêu âm là bắt buộc",
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



function validateSuperSonicForm(sonic) {
	objErrors = [];
	result = true;
	objError = {}
	if(!sonic.superSonicName) {
		result = false;
		objError = getObjectError(false, 'superSonicNameEdit', 'Tên siêu âm là bắt buộc');
	} else {
		objError = getObjectError(true, 'superSonicNameEdit', '');
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
	action = "sonic/searchAdvance";
	var sonic= $('form[name=superSonicSearchFrm]').serializeToJSON();
	var paramBean = {};
	paramBean.data = sonic;
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