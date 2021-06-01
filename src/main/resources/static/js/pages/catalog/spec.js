//$( document ).ready(bindClick);
var specCodeHidden;
var currentPage;
var specDataTable;
$(document).ready(function() {
	specDataTable = 'specDataTbl';
	drawTable(specDataTable);
	//bindClick();
	$("#imgInp").change(function() {
		  readURLAdd(this);
		});
	validateAdd();
});

function bindClick() {
	$(".text_data").on("click", function() {
		var rowData = $(this).children("td").map(function() {
			return $(this).text();
		}).get();
		var specCode = rowData[1];
		specCodeHidden = specCode;
		action = "spec/showDetail?specCode=" + specCode;
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
		        	$('#detailSpec').replaceWith(response);
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
		action = "spec/searchAdvance";
		var spec= $('form[name=specSearchFrm]').serializeToJSON();
		var paramBean = {};
		paramBean.data = spec;
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
	action = "spec/searchAdvance";
	var customer= $('form[name=specSearchFrm]').serializeToJSON();
	var paramBean = {};
	paramBean.data = customer;
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
	action = "spec/searchAdvance";
	var customer= $('form[name=specSearchFrm]').serializeToJSON();
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
	$('#editSpecModal').modal({
       backdrop: 'static',
       keyboard: false
    });
	action = "spec/showDetail?specCode=" +specCodeHidden + "&mode=edit";
	$.ajax({
        type: "GET",
        contentType: "application/html",
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	$('#detailSpecEdit').replaceWith(response);
        	$("#imgInpEdit").change(function() {
	       		  readURLEdit(this);
	       	  });
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
	
	$('#modalAddSpec').modal({
           backdrop: 'static',
           keyboard: false
    });
}

function addAction() {
	action = "spec/add";
	var paramBean = {};
	var spec = $('form[name=specFrm]').serializeToJSON();
	paramBean.currentPage = 1;

	var breed = {};
	breed.breedCode = spec.breed;
	spec.breed = breed;
	paramBean.data = spec;
	

	var searchAdvanceParam = $('form[name=specSearchFrm]').serializeToJSON();
	var advanceParam = {};
	
	var specForm= $('#specForm')[0];
	var data = new FormData(specForm);
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;

	if ($('#specForm').valid()) {
		$.ajax({
			type : "POST",
//	        contentType : 'application/json',
	        enctype: 'multipart/form-data',
			url : action,
			dataType : 'html',
			data : data,
			cache : false,
			timeout : 600000,
			processData : false,
			contentType : false,
			success : function(response) {
				mess = $(response).find("input[id='msg-add']").val();
				showDialogConfirm(mess);
				$('#modalAddSpec').modal('hide');
				if (mess == 'SUCCESS') {
					$("#refresh-section").replaceWith(response);
					drawTable(specDataTable);
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
	action = "spec/edit";
	var paramBean = {};
	var spec = $('form[name=objFrmEdit]').serializeToJSON();
	
	
	paramBean.currentPage = currentPage;

	var searchAdvanceParam = $('form[name=specSearchFrm]').serializeToJSON();
	var advanceParam = {};
	
	var specForm= $('#specFormEdit')[0];
	var data = new FormData(specForm);
	
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;

	if (validateBreedForm(spec)) {
		$.ajax({
			type : "POST",
			enctype: 'multipart/form-data',
			url : action,
			dataType : 'html',
			data : data,
			cache : false,
			timeout : 600000,
			processData : false,
			contentType : false,
			success : function(response) {
				mess = $(response).find("input[id='msg-add']").val();
				showDialogConfirm(mess);
				$('#editSpecModal').modal('hide');
				if (mess == 'SUCCESS') {
					$("#refresh-section").replaceWith(response);
					drawTable(specDataTable);
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
	$('#specForm').validate({
		rules : {
			specName : {
				required : true
			},
			breedCdStr : {
				valueNotEquals: "0"
			},

		},
		messages : {
			specName : {
				required : "Tên giống là bắt buộc",
			},
			breedCdStr : {
				valueNotEquals: "Loài là bắt buộc",
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



function validateBreedForm(spec) {
	objErrors = [];
	result = true;
	objError = {}
	if(!spec.specName) {
		result = false;
		objError = getObjectError(false, 'breednameEdit', 'Tên giống là bắt buộc');
	} else {
		objError = getObjectError(true, 'breednameEdit', '');
	}
	objErrors.push(objError);
	
	if(spec.breedCdStr ==0) {
		result = false;
		objError = getObjectError(false, 'breedTypeEdit', 'Loài là bắt buộc');
	} else {
		objError = getObjectError(true, 'breedTypeEdit', '');
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
	action = "spec/searchAdvance";
	var customer= $('form[name=specSearchFrm]').serializeToJSON();
	var paramBean = {};
	paramBean.data = customer;
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