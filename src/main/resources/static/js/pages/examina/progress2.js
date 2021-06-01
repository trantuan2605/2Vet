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

function process2(){
	action = "process2";
	var paramBean = {};
	var examCode = $('#examCodeHidden').val();
	var progress2 = $('form[name=frmProgres2]').serializeToJSON();
	
	if(progress2.createTimeStr) {
		var timestamp = $.datepicker.parseDate('dd/mm/yy', progress2.createTimeStr);
		progress2.createTime = new Date(timestamp);
	} else {
		progress2.createTime = new Date();
	}
	
	if(progress2.hospitalizeDateStr) {
		var timestamp = $.datepicker.parseDate('dd/mm/yy', progress2.hospitalizeDateStr);
		progress2.hospitalizeDate = new Date(timestamp);
	} else {
		progress2.hospitalizeDate = new Date();
	}
	
	if(progress2.dischargeDateStr) {
		var timestamp = $.datepicker.parseDate('dd/mm/yy', progress2.dischargeDateStr);
		progress2.dischargeDate = new Date(timestamp);
	} else {
		progress2.dischargeDate = new Date();
	}
	var progress2Form= $('#progress2')[0];
	var data = new FormData(progress2Form);
	
	data.append('hospitalizeDate', progress2.hospitalizeDate);
	data.append('createTime', progress2.createTime);
	data.append('dischargeDate', progress2.dischargeDate);
	

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
				mess = $(response).find("input[id='msg-add-progress3']").val();
				showDialogConfirm(mess);
				if (mess == 'SUCCESS') {
					//$("#refresh-section").replaceWith(response);
					$('#examCode').text($(response).find("span[id='examCode']").text());
					$("#data-refresh-progress3").replaceWith(response);
					$('.reserver-date').each(function(index) {
				    	$(this).datetimepicker({
				        	format: 'DD/MM/YYYY',
				        });
				    });
					$('#timeIn_pgr_4').val($('#timeInHidden').val());
				}

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

function changeMultiple() {
	var multipleValues = $("#multiple").val() || [];
	// When using jQuery 3:
	// var multipleValues = $( "#multiple" ).val();
	$('#serviceCodeListHidden').val(multipleValues.join(", "));
	//$("p").html("Multiple:</b> " + multipleValues.join(", "));
}

function changeMultipleSymptom() {
	var multipleValues = $("#multiple-symptom").val() || [];
	$('#symptomCodeListHidden').val(multipleValues.join(", "));
}
//	$( "select" ).change( displayVals );
//	displayVals();	
function changeTime() {
	var timeInValue = $("#timeIn_pgr_4").val();
	var timeOutValue = $("#timeOut").val();
	var dt1 =  moment(timeInValue, "DD/MM/YYYY");
	var dateObject1 = dt1.toDate();
	
	var dt2 =  moment(timeOutValue, "DD/MM/YYYY");
	var dateObject2 = dt2.toDate();
	diff = (dateObject2-dateObject1) / (1000 * 60 * 60 * 24);
	var dayExam;
	
	if(diff == 0 || Object.keys(timeOutValue).length === 0 || Object.keys(timeInValue).length === 0){
		dayExam = 1;
	} else if (diff > 0) {
		dayExam = diff;
	}
	 $("#dayExam").val(dayExam);
	
	//$("p").html("Multiple:</b> " + timeInValue.join(", "));
}