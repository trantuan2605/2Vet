var specCodeHidden;
var currentPage;
var specDataTable;
$(document).ready(function() {
	specDataTable = 'specDataTbl';
//	drawTable(specDataTable);
	//bindClick();
	$("#imgInp").change(function() {
		  readURLAdd(this);
		});
	$('#reExamDate').datetimepicker({
    	format: 'DD/MM/YYYY'
    });
	$('#dischargeDate').datetimepicker({
    	format: 'DD/MM/YYYY'
    });
	$('#hospitalizeDate').datetimepicker({
    	format: 'DD/MM/YYYY'
    });
});

function redirectScreen() {
	var contextRoot = $('#contextRoot').val();
	var url = contextRoot.concat('/examination/progress');
	window.location.href = url;
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

function process4(){
	action = "process4";
	var paramBean = {};
	var progress4 = $('form[name=frmProgres4]').serializeToJSON();
	var progress4Form= $('#progress4')[0];
	var data = new FormData(progress4Form);
	

	var examCode = $('#examCodeHidden').val();
	if (progress4.hospitalizeDate) {
		var timestamp = $.datepicker.parseDate('dd/mm/yy',progress4.hospitalizeDate);
		progress4.hospitalizeDate = new Date(timestamp);
	} else {
		progress4.hospitalizeDate = new Date();
	}

	if (progress4.dischargeDate) {
		var timestamp = $.datepicker.parseDate('dd/mm/yy',progress4.dischargeDate);
		progress4.dischargeDate = new Date(timestamp);
	} else {
		progress4.dischargeDate = new Date();
	}
	data.append("examClinicalCode", examCode);
	data.append('hospitalizeDate', progress4.hospitalizeDate);
	data.append('dischargeDate', progress4.dischargeDate);
	
	
	
//	if (validateBreedForm(spec)) {
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
				mess = $(response).val();
				showDialogConfirm(mess);
//				if (mess == 'SUCCESS') {
//					$("#refresh-section").replaceWith(response);
//				}

			},
			error : function(e) {
				console.log(e);
				mess = "SYS_ERR";
				showDialogConfirm(mess);
			}
		});
//	}

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

function redirectSummary() {
	var examCode = $('#examCodeHidden').val();
	var contextRoot = $('#contextRoot').val();
	var url = contextRoot.concat('/examination/summary?examCode=' + examCode);
	window.location.href = url;
}