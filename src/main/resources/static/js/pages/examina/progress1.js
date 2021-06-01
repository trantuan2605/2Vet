var cusCodeHidden;
var currentPage;
var specDataTable;
$(document).ready(function() {
	specDataTable = 'specDataTbl';
//	drawTable(specDataTable);
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
		var cusCode = rowData[1];
		cusCodeHidden = cusCode;
		var action = "showCusDetail?cusCode=" + cusCode;
//		$('#myModal').modal({
//		//$('#editCusModal').modal({
//           backdrop: 'static',
//           keyboard: false
//	    });
		$('#modalLstCus').modal('hide');
		$.ajax({
		        type: "GET",
		        contentType: "application/html",
		        url: action,
		        dataType: 'html',
		        cache: false,
		        timeout: 600000,
		        success: function (response) {
		        	$('#cusDetail').replaceWith(response);
		        },
		        error: function (e) {
		            alert(e);
		        }
		    });
	});
}

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

function process1(){
	action = "process1";
	var paramBean = {};
	var progress1 = $('form[name=frmProgres1]').serializeToJSON();
	
	var progress1Form= $('#progress1')[0];
	var data = new FormData(progress1Form);

//	if (validateBreedForm(spec)) {
	if ($('#progress1').valid()) {
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
				activeStep =1;
				//set all steps before clicked (and clicked too) to active
			  setActiveStep(activeStep);

			  //open active panel
			  setActivePanel(activeStep);
				mess = $(response).find("input[id='msg-add']").val();
				showDialogConfirm(mess);
				if (mess == 'SUCCESS') {
					$('#examIdHidden').val($(response).find("input[id='examIdHidden']").val());
					$('#examCodeHidden').val($(response).find("input[id='examCodeHidden']").val());
					$('#petCodeHidden').val($(response).find("input[id='petCodeHidden']").val());
					$('#examCode').text($(response).find("span[id='examCode']").text());
//					$("#progress2Step").replaceWith(response);
					$('#dischargeDate').datetimepicker({
				    	format: 'DD/MM/YYYY'
				    });
					$('#hospitalizeDate').datetimepicker({
				    	format: 'DD/MM/YYYY'
				    });
				}

			},
			error : function(e) {
				console.log(e);
				mess = "SYS_ERR";
				showDialogConfirm(mess);
			}
		});
	}
//	}

}

function validateAdd() {
	// add the rule here
	$.validator.addMethod("valueNotEquals", function(value, element, arg) {
		return arg !== value;
	}, "Value must not equal arg.");
	$('#progress1').validate({
		rules : {
			petName : {
				valueNotEquals: "0"
			},

		},
		messages : {
			petName : {
				valueNotEquals: "Mã thú nuôi là bắt buộc",

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

function changeFunc(e) {
	//var selectedValue = $('#breedEdit option:selected').val();
	var idSelect = e.id;
	var selectedValue = $(e).val();
	var action = "showDetailPetByCode?petCode=" + selectedValue;
	$.ajax({
        type: "GET",
        contentType : 'application/json',
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
        		$("#pet-info-add").replaceWith(response);
        },
        error: function (e) {
            console.log(e);
            mess = "SYS_ERR";
            showDialogConfirm(mess);
        }
    });
}

function showListCusPopup() {
	var action = "getLstCus";
	$.ajax({
        type: "GET",
        contentType : 'application/json',
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
			$('#showDataCus').replaceWith(response);
			$('#modalLstCus').modal({
		        backdrop: 'static',
		        keyboard: false
		 });
			tableWidgetCus = 'customerDataTbl';
			drawTable(tableWidgetCus);
        },
        error: function (e) {
            console.log(e);
            mess = "SYS_ERR";
            showDialogConfirm(mess);
        }
    });
}