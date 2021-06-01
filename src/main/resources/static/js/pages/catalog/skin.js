//$( document ).ready(bindClick);
var skinTestCodeHidden;
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
		var skinTestCode = rowData[1];
		skinTestCodeHidden = skinTestCode;
		action = "skin/showDetail?skinTestCode=" + skinTestCode;
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
		        	$('#detailSkinTest').replaceWith(response);
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
		action = "skin/searchAdvance";
		var skinTest= $('form[name=skinTestSearchFrm]').serializeToJSON();
		var paramBean = {};
		paramBean.data = skinTest;
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
	action = "skin/searchAdvance";
	var skinTest= $('form[name=skinTestSearchFrm]').serializeToJSON();
	var paramBean = {};
	paramBean.data = skinTest;
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
	action = "skin/searchAdvance";
	var skinTest= $('form[name=skinTestSearchFrm]').serializeToJSON();
	var paramBean = {};
	paramBean.data = skinTest;
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
	$('#editSkinTestModal').modal({
       backdrop: 'static',
       keyboard: false
    });
	action = "skin/showDetail?skinTestCode=" + skinTestCodeHidden + "&mode=edit";
	$.ajax({
        type: "GET",
        contentType: "application/html",
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	$('#detailSkinTestEdit').replaceWith(response);
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
	
	$('#modalAddSkinTest').modal({
           backdrop: 'static',
           keyboard: false
    });
}

function addAction() {
	action = "skin/add";
	var paramBean = {};
	var skinTest = $('form[name=skinTestFrm]').serializeToJSON();
	paramBean.data = skinTest;
	paramBean.currentPage = 1;

	var param = {};
	param.skinTestCode = skinTest.skinTestCode;

	var searchAdvanceParam = $('form[name=skinTestSearchFrm]').serializeToJSON();
	var advanceParam = {};
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;

	if ($('#skinTestForm').valid()) {
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
				$('#modalAddSkinTest').modal('hide');
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
	action = "skin/edit";
	var skinTest = $('form[name=objFrmEdit]').serializeToJSON();
	var param = {};
	param.skinTestCode = skinTest.skinTestCode;

	var paramBean = {};
	paramBean.data = skinTest;
	paramBean.currentPage = currentPage;

	var searchAdvanceParam = $('form[name=skinTestSearchFrm]').serializeToJSON();
	var advanceParam = {};
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;

	if (validateSkinTestForm(skinTest)) {
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
				$('#editSkinTestModal').modal('hide');
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
	$('#skinTestForm').validate({
		rules : {
			skinTestCode : {
				required : true,
				maxlength : 5
			},
			skinTestName : {
				required : true
			},

		},
		messages : {
			skinTestCode : {
				required : "Mã xét nghiệm da là bắt buộc",
				maxlength : "Mã xét nghiệm da không chứa nhiều hơn 5 ký tự"
			},

			skinTestName : {
				required : "Tên xét nghiệm da là bắt buộc",
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



function validateSkinTestForm(skinTest) {
	objErrors = [];
	result = true;
	objError = {}
	if(!skinTest.skinTestName) {
		result = false;
		objError = getObjectError(false, 'skinTestNameEdit', 'Tên xét nghiệm da là bắt buộc');
	} else {
		objError = getObjectError(true, 'skinTestNameEdit', '');
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
	action = "skin/searchAdvance";
	var skinTest= $('form[name=skinTestSearchFrm]').serializeToJSON();
	var paramBean = {};
	paramBean.data = skinTest;
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