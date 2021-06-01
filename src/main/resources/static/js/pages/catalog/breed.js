//$( document ).ready(bindClick);
var breedCodeHidden;
var currentPage;
var breedDataTable;
$(document).ready(function() {
	breedDataTable = 'breedDataTbl';
	drawTable(breedDataTable);
	$("#imgInp").change(function() {
		  readURLAdd(this);
		});
	//bindClick();
	validateAdd();
});

function bindClick() {
//	$(".text_data").on("click", function() {
	$('#breedDataTbl').on("click", "tbody tr", function() {
		var rowData = $(this).children("td").map(function() {
			return $(this).text();
		}).get();
		var breedCode = rowData[1];
		breedCodeHidden = breedCode;
		action = "breed/showDetail?breedCode=" + breedCode;
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
		        	$('#detailBreed').replaceWith(response);
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
		action = "breed/searchAdvance";
		var customer= $('form[name=breedSearchFrm]').serializeToJSON();
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
	action = "breed/searchAdvance";
	var customer= $('form[name=breedSearchFrm]').serializeToJSON();
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
	action = "breed/searchAdvance";
	var customer= $('form[name=breedSearchFrm]').serializeToJSON();
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
	$('#editBreedModal').modal({
       backdrop: 'static',
       keyboard: false
    });
	action = "breed/showDetail?breedCode=" + breedCodeHidden + "&mode=edit";
	$.ajax({
        type: "GET",
        contentType: "application/html",
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	$('#detailBreedEdit').replaceWith(response);
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
	
	$('#modalAddBreed').modal({
           backdrop: 'static',
           keyboard: false
    });
}

function addAction() {
	action = "breed/add";
	var paramBean = {};
	var breed = $('form[name=breedFrm]').serializeToJSON();
	paramBean.data = breed;
	paramBean.currentPage = 1;

	var param = {};
	param.breedCode = breed.breedCode;

	var searchAdvanceParam = $('form[name=breedSearchFrm]').serializeToJSON();
	var advanceParam = {};
	
	var breedForm= $('#breedForm')[0];
	var data = new FormData(breedForm);
	
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;

	if ($('#breedForm').valid()) {
		$.ajax({
			type : "POST",
//	        contentType : 'application/json',
	        enctype: 'multipart/form-data',
			url : action,
			dataType : 'html',
			//data : JSON.stringify(paramBean),
			data : data,
			cache : false,
			timeout : 600000,
			processData : false,
			contentType : false,
			success : function(response) {
				mess = $(response).find("input[id='msg-add']").val();
				showDialogConfirm(mess);
				$('#modalAddBreed').modal('hide');
				if (mess == 'SUCCESS') {
					$("#refresh-section").replaceWith(response);
					drawTable(breedDataTable);
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
	action = "breed/edit";
	var breed = $('form[name=objFrmEdit]').serializeToJSON();
	var param = {};
	param.breedCode = breed.breedCode;

	var paramBean = {};
	paramBean.data = breed;
	paramBean.currentPage = currentPage;

	var searchAdvanceParam = $('form[name=breedSearchFrm]').serializeToJSON();
	var advanceParam = {};
	
	var breedForm= $('#cusFormEdit')[0];
	var data = new FormData(breedForm);
	
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;

	if (validateBreedForm(breed)) {
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
				$('#editBreedModal').modal('hide');
				if (mess == 'SUCCESS') {
					$("#refresh-section").replaceWith(response);
					drawTable(breedDataTable);
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
	$('#breedForm').validate({
		rules : {
			breedName : {
				required : true
			},

		},
		messages : {
			breedName : {
				required : "Tên loài là bắt buộc",
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



function validateBreedForm(breed) {
	objErrors = [];
	result = true;
	objError = {}
	if(!breed.breedName) {
		result = false;
		objError = getObjectError(false, 'breednameEdit', 'Tên loài là bắt buộc');
	} else {
		objError = getObjectError(true, 'breednameEdit', '');
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
	action = "breed/searchAdvance";
	var customer= $('form[name=breedSearchFrm]').serializeToJSON();
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