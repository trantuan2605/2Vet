var specCodeHidden;
var currentPage;
var tableWidgetService;
var tableWidgetMed;
var tableWidgetProd;
var rowCountBefore = 0;
var lstSubExamDel = [];
var lstTblPopup = [];
var lstItem3ndCode = [];
var iClick = 0;
var isOkBtn = false;
var lstItem3ndCodeTemp = [];
var idSubExam;
var idSubExamOld = 0;
//var selectedMap = new Map();
var lstItem3ndCodeExist = [];
$(document).ready(function() {
	//bindClick();
//	$("#imgInp").change(function() {
//		  readURLAdd(this);
//		});
});


function checkedExtraOK() {
	isOkBtn = true;
}

function checkedExtraCancel() {
	isOkBtn = false;
}

function checkedExtra(element) {
	if(element.checked) {
		lstItem3ndCode.push($(element).next().val());
	} else {
		if(selectedMap.get(idSubExam)) {
			lstItem3ndCode = selectedMap.get(idSubExam);
		}
		var index = lstItem3ndCode.indexOf($(element).next().val());
	    if (index > -1) {
	    	lstItem3ndCode.splice(index, 1);
	    }
	}
	selectedMap.set(idSubExam, lstItem3ndCode);
	console.log(selectedMap);
}

function redirectScreen() {
	var contextRoot = $('#contextRoot').val();
	var url = contextRoot.concat('/examination/progress');
	window.location.href = url;
}

function process3(){
	action = "process3";
	var examClinicalCode = $('#examCodeHidden').val();
	if(!examClinicalCode) {
		var paramsUrl = new window.URLSearchParams(window.location.search);
		examClinicalCode = paramsUrl.get('examCode');
	}
	var paramBean = {};
	var progress3 = $('form[name=frmProgres3]').serializeToJSON();
	
	var progress3Form= $('#progress3')[0];
	var data = new FormData(progress3Form);
	var lstParam = [];
	var lstObject = [];
	var totalRecord = 0;
	for(var pair of data.entries()) {
		totalRecord ++;
//		var dataParam = new FormData();
//	   console.log(pair[0]+ ', '+ pair[1]);
//	   if(pair[0] == 'executionDateStr') {
//		   dataParam.append(pair[0] , pair[1]);
//	   }
//	   lstParam.push(dataParam);
	}
	for(i = 0; i< totalRecord/9; i++) {
		var dataParam = new FormData();
		var dataObject = {};
		var index = 0;
		var indexMap = 0;
		for(var pair of data.entries()) {
			if(pair[0].includes('widgetServicesDataTbl') 
					   || pair[0].includes('widgetMedsDataTbl') || pair[0].includes('widgetProdsDataTbl')) {
				   break;
			   }
			index ++; 
			if(index <= i*9) {
			   continue;
		   }
		   if(index > i*9 && index <= (i+ 1)*9) {
			   console.log(pair[0]+ ', '+ pair[1]);
			   if(pair[0] === 'id' && !pair[1]) {
				   continue;
			   } else if(pair[0] === 'id' && pair[1]) {
				   lstItem3ndCodeExist.push(pair[1]);
			   }
//			   if(pair[0].includes('widgetServicesDataTbl') 
//					   || pair[0].includes('widgetMedsDataTbl') || pair[0].includes('widgetProdsDataTbl')) {
//				   continue;
//			   }
			   dataParam.append(pair[0] , pair[1]);
//			   if(pair[0] !== 'fileImage') {
				   dataObject[pair[0]] = pair[1];
				   if(pair[0] == "executionDateStr") {
					   if(pair[1]) {
						var timestamp = $.datepicker.parseDate('dd/mm/yy', pair[1]);
						dataObject.executionDate = new Date(timestamp);
					} else {
						dataObject.executionDate = new Date();
					}
				   }
//			   }
		   }
		   if(index == (i+ 1)*9) {
			   var extPro = dataObject.id;
			   dataObject.extItemCode = "";
			   if(selectedMap.get(extPro)) {
				   dataObject.extItemCode = selectedMap.get(extPro).toString();
			   } else if(typeof extPro === 'undefined' && typeof selectedMap.get(extPro) === 'undefined') {
				   var idx = Array.from(selectedMap)[indexMap][0];
				   dataObject.extItemCode = selectedMap.get(idx).toString();
				   dataObject.adCol = "0";
				   indexMap++;
			   }
		   }
		   if(index > (i+ 1)*9) {
			   break;
		   }
		}
		lstParam.push(dataParam);
		dataObject.examClinicalCode = examClinicalCode;
		if((Object.keys(dataObject).length === 12) && dataObject.constructor === Object) {
			lstObject.push(dataObject);
		}
	}
	
//	if(lstSubExamDel.length > 0) {
//		lstSubExamDel.forEach(function(item) {
//			lstObject.push(item);
//		});
//	}
	
	var formParam = new FormData();
//	formParam.append("lstSubClinical", lstParam);
//	formParam.append("lstSubClinicalObj", lstObject);
//	data.append("lstSubClinicalObj", lstObject);
	data.append("lstSubClinicalStr", JSON.stringify(lstObject));
	data.append("examClinicalCode", examClinicalCode);
	if(lstSubExamDel.length > 0) {
		data.append("lstSubClinDelStr", JSON.stringify(lstSubExamDel));
	}
//	formParam.append("test", "abc");
	data.append("test", "abc");
	
//	if (validateBreedForm(spec)) {
		$.ajax({
			type : "POST",
			enctype: 'multipart/form-data',
			url : action,
			dataType : 'html',
//			data : formParam,
			data : data,
			cache : false,
			timeout : 600000,
			processData : false,
			contentType : false,
			success : function(response) {
				mess = $(response).find("input[id='msg-add-progress3']").val();
				showDialogConfirm(mess);
				if (mess == 'SUCCESS') {
					$("#data-refresh-progress3").replaceWith(response);
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

function getObjectError(valid, idInput, mess){
	objError = {};
	objError.valid = valid;
	objError.idInput = idInput;
	objError.errMessage = mess;
	return objError;
}

function addNewRow(e) {
	var table = $(e).closest('table')[0].id;
	var thisRow = $( e ).closest( 'tr' )[0];
	var ele = table + ' tbody tr';
    var rowCount = $('#' + ele).length;
    var newIDSuffix = 1 + rowCount;
    if(rowCountBefore) {
    	newIDSuffix = 1 + rowCountBefore;
    	rowCountBefore = newIDSuffix;
    }
    var cloned = $(thisRow).clone();
    cloned.find('td.drop-list-branch-add').each(function(index) {
    	var increment = newIDSuffix + index;
    	var id = $(this).attr('id');
    	id = id.substring(0, id.length - 1) + increment;
        $(this).attr('id', id);
    });
    
    cloned.find('td.drop-list-doctor-add').each(function(index) {
    	var increment = newIDSuffix + index;
    	var id = $(this).attr('id');
    	id = id.substring(0, id.length - 1) + increment;
        $(this).attr('id', id);
    });
    
    cloned.find('select[name="branchCode"]').each(function(index) {
    	var increment = newIDSuffix + index;
    	var id = $(this).attr('id');
    	id = id.substring(0, id.length - 1) + increment;
        $(this).attr('id', id).val(0);
    });
    
    cloned.find('select[name="doctorCode"]').each(function(index) {
    	var increment = newIDSuffix + index;
    	var id = $(this).attr('id');
    	id = id.substring(0, id.length - 1) + increment;
        $(this).attr('id', id).val(0);
    });
    
    cloned.find('[name="serviceCode"]').each(function(index) {
    	$(this).find('option').removeAttr("selected");
    	$(this).find('option[value=0]').attr('selected','selected');
    });
    
    cloned.find('textarea[name="remark"]').each(function(index) {
    	$(this).val('');
    });
    
    // update id for input image-upload
    cloned.find('div.image-upload input').each(function(index) {
    	var increment = newIDSuffix + index;
    	var id = $(this).attr('id');
    	id = id.substring(0, id.length - 1) + increment;
        $(this).attr('id', id);
    });
    
 // update id for span file_name_upload
    cloned.find('div.image-upload span').each(function(index) {
    	var increment = newIDSuffix + index;
    	var id = $(this).attr('id');
    	id = id.substring(0, id.length - 1) + increment;
        $(this).attr('id', id);
      //reset file name upload
        $(this).text('');
    });
    
 // update id for label file-input
    cloned.find('div.image-upload label').each(function(index) {
    	var increment = newIDSuffix + index;
    	var forId = $(this).attr('for');
    	forId = forId.substring(0, forId.length - 1) + increment;
        $(this).attr('for', forId);
    });
    
    // update id for class input-group date
    cloned.find('div.input-group.date').each(function(index) {
    	var increment = newIDSuffix + index;
    	var id = $(this).attr('id');
    	id = id.substring(0, id.length - 1) + increment;
        $(this).attr('id', id);
    });
    
    // update id for datetimepicker
    cloned.find('input.datetimepicker-input').each(function(index) {
    	var increment = newIDSuffix + index;
    	var dataTarget = $(this).attr('data-target');
    	dataTarget = dataTarget.substring(0, dataTarget.length - 1) + increment;
        $(this).attr('data-target', dataTarget);
    });
    
    // update id for icon
    cloned.find('div.input-group-append.datetimepicker-input').each(function(index) {
    	var increment = newIDSuffix + index;
    	var dataTarget = $(this).attr('data-target');
    	dataTarget = dataTarget.substring(0, dataTarget.length - 1) + increment;
        $(this).attr('data-target', dataTarget);
    });
    
    cloned.insertAfter( thisRow ).find( 'input:text, input:hidden' ).val( '' ).removeAttr('value');
    var rowCountAfter = $('#' + ele).length;
    if(rowCountAfter > 1) {
    	$(".del-tr-clone").removeClass("display-init");
    }
    
    $('.reserver-date').each(function(index) {
    	$(this).datetimepicker({
        	format: 'DD/MM/YYYY',
        });
    });
}

function removeRow(e) {
	var rowsData = $( e ).closest( 'tr' ).children("td").children("input,select, div, textarea");
	var subExamDeleted = {};
	$.each(rowsData, function(i, row) {
		switch(i) {
			case 0:
				subExamDeleted.id = $(row).val();
				break;
			case 1:
				var idCellRemove = $(row).attr('id');
				var idRemove = idCellRemove.slice(idCellRemove.length - 1);
				selectedMap.delete(idRemove);
				break;
		}
		subExamDeleted.isDeleted = 1;
	});
	if(subExamDeleted.id) {
		lstSubExamDel.push(subExamDeleted);
		selectedMap.delete(subExamDeleted.id);
	}

var table = $(e).closest('table')[0].id;
var ele = table + ' tbody tr';
if(rowCountBefore) {
	rowCountBefore+=1;
} else {
	rowCountBefore = $('#' + ele).length;
}
var thisRow = $( e ).closest( 'tr' )[0].remove();
var rowCount = $('#' + ele).length;
if(rowCount == 1) {
	$(".del-tr-clone").addClass("display-init");
}

$('.reserver-date').each(function(index) { 
	$(this).datetimepicker({
    	format: 'DD/MM/YYYY',
    });
});
}

function showFileName(e) {
	var fileName = e.files[0].name;
	var id = $(e).attr('id');
	var index = id.substr(id.length - 1);
	$('#file_name_upload'+ index).text(fileName);
}

function changeBranchFunc(e) {
	var idSelect = e.id;
	var indexRow = idSelect.substring(9, idSelect.length);
	var selectedValue = $(e).val();
	var action = "getDoctorByBranch?branchCode=" + selectedValue + '&index=' + indexRow;
	$.ajax({
        type: "GET",
        contentType : 'application/json',
        url: action,
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	var select = $("#doctor-droplist-add"+indexRow).find('select');
        	$(select).find('option').remove().end().append('<option value="0">-- Chọn --</option>')
        	var lst = response;
        	if(lst.length > 0) {
        		for (var i=0; i< lst.length; i++) {
        			select.append(new Option(lst[i].doctorName, lst[i].doctorCode))
				}
    		}
        },
        error: function (e) {
            console.log(e);
            mess = "SYS_ERR";
            showDialogConfirm(mess);
        }
    });
}

function showProdPopup(element) {
	 iClick = 0;
//	 if(!isOkBtn) {
//		 lstItem3ndCode = [];
//	 }
	 var currentRow = $(element).parent().parent().find(".add-remove-row");
	 var idCell = currentRow.children()[0];
	 idSubExamOld = idSubExam;
	 idSubExam = $(idCell).val();
	 var idQuery = idSubExam;
	 if(!idSubExam) {
		 var cell = $(element).parent().parent().find(".align-middle .reserver-date");
		 var cellId = $(cell).attr('id');
		 var newId = cellId.slice(cellId.length - 1);
		 idSubExam = newId;
	 }
	getDataForPopup(idSubExam, idQuery);
}

function getDataForPopup(idSubExam, idQuery) {
	if(!selectedMap.has(idSubExam)) {
		lstItem3ndCode = [];
	} else {
		lstItem3ndCode = selectedMap.get(idSubExam);
	}
	var paramBean = {};
	paramBean.id = idQuery;
	paramBean.lstItem3ndCode = lstItem3ndCode;
	var action = "setupDataPS";
	$.ajax({
        type: "PUT",
        contentType : 'application/json',
        data : JSON.stringify(paramBean),
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
			$('#showDataPS').replaceWith(response);
			$('#modalLstProd').modal({
		        backdrop: 'static',
		        keyboard: false
		 });
			lstTblPopup = [];
			tableWidgetService = 'widgetServicesDataTbl';
			tableWidgetMed = 'widgetMedsDataTbl';
			tableWidgetProd = 'widgetProdsDataTbl';
			lstTblPopup.push(tableWidgetService);
			lstTblPopup.push(tableWidgetMed);
			lstTblPopup.push(tableWidgetProd);
			initDataTblPopup(lstTblPopup);
        },
        error: function (e) {
            console.log(e);
            mess = "SYS_ERR";
            showDialogConfirm(mess);
        }
    });
}

function initDataTblPopup(lstTblPopup) {
	lstTblPopup.forEach(function(item){
		drawTableNotAllowClick(item, false);
	});
}