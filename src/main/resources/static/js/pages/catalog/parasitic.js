//$( document ).ready(bindClick);
var parasiticTestCodeHidden;
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
		var parasiticTestCode = rowData[1];
		parasiticTestCodeHidden = parasiticTestCode;
		action = "parasitic/showDetail?parasiticTestCode=" + parasiticTestCode;
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
		        	$('#detailParasiticTest').replaceWith(response);
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
		action = "parasitic/searchAdvance";
		var parasiticTest= $('form[name=parasiticTestSearchFrm]').serializeToJSON();
		var paramBean = {};
		paramBean.data = parasiticTest;
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
	action = "parasitic/searchAdvance";
	var parasiticTest= $('form[name=parasiticTestSearchFrm]').serializeToJSON();
	var paramBean = {};
	paramBean.data = parasiticTest;
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
	action = "parasitic/searchAdvance";
	var parasiticTest= $('form[name=parasiticTestSearchFrm]').serializeToJSON();
	var paramBean = {};
	paramBean.data = parasiticTest;
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
	$('#editParasiticTestModal').modal({
       backdrop: 'static',
       keyboard: false
    });
	action = "parasitic/showDetail?parasiticTestCode=" + parasiticTestCodeHidden + "&mode=edit";
	$.ajax({
        type: "GET",
        contentType: "application/html",
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	$('#detailParasiticTestEdit').replaceWith(response);
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
	
	$('#modalAddParasiticTest').modal({
           backdrop: 'static',
           keyboard: false
    });
}

function addAction() {
	action = "parasitic/add";
	var paramBean = {};
	var parasiticTest = $('form[name=parasiticTestFrm]').serializeToJSON();
	paramBean.data = parasiticTest;
	paramBean.currentPage = 1;

	var param = {};
	param.parasiticTestCode = parasiticTest.parasiticTestCode;

	var searchAdvanceParam = $('form[name=parasiticTestSearchFrm]').serializeToJSON();
	var advanceParam = {};
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;

	if ($('#parasiticTestForm').valid()) {
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
				$('#modalAddParasiticTest').modal('hide');
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
	action = "parasitic/edit";
	var parasiticTest = $('form[name=objFrmEdit]').serializeToJSON();
	var param = {};
	param.parasiticTestCode = parasiticTest.parasiticTestCode;

	var paramBean = {};
	paramBean.data = parasiticTest;
	paramBean.currentPage = currentPage;

	var searchAdvanceParam = $('form[name=parasiticTestSearchFrm]').serializeToJSON();
	var advanceParam = {};
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;

	if (validateParasiticTestForm(parasiticTest)) {
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
				$('#editParasiticTestModal').modal('hide');
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
	$('#parasiticTestForm').validate({
		rules : {
			parasiticTestCode : {
				required : true,
				maxlength : 5
			},
			parasiticTestName : {
				required : true
			},

		},
		messages : {
			parasiticTestCode : {
				required : "Mã xét nghiệm kí sinh trùng là bắt buộc",
				maxlength : "Mã xét nghiệm kí sinh trùng không chứa nhiều hơn 5 ký tự"
			},

			parasiticTestName : {
				required : "Tên xét nghiệm kí sinh trùng là bắt buộc",
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



function validateParasiticTestForm(parasiticTest) {
	objErrors = [];
	result = true;
	objError = {}
	if(!parasiticTest.parasiticTestName) {
		result = false;
		objError = getObjectError(false, 'parasiticTestNameEdit', 'Tên xét nghiệm kí sinh trùng là bắt buộc');
	} else {
		objError = getObjectError(true, 'parasiticTestNameEdit', '');
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
	action = "parasitic/searchAdvance";
	var parasiticTest= $('form[name=parasiticTestSearchFrm]').serializeToJSON();
	var paramBean = {};
	paramBean.data = parasiticTest;
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