//$( document ).ready(bindClick);
var tablePetHtml;
var doctorCodeHidden;
var currentPage;
var rowCountBefore;
var doctorDataTable;
var contextRoot;
$(document).ready(function() {
	doctorDataTable = 'doctorDataTbl';
	drawTable(doctorDataTable);
	//Date range picker
    $('#reservationdate').datetimepicker({
    	format: 'DD/MM/YYYY'
    });
    $('#dateOfWorkAdd').datetimepicker({
    	format: 'DD/MM/YYYY'
    });
    contextRoot = $('#contextRootSession').val();
    $("#imgInp").change(function() {
		  readURLAdd(this);
		});
});

function bindClick() {
	//$(".text_data").on("click", function() {
	$('#doctorDataTbl').on("click", "tbody tr", function() {
		var rowData = $(this).children("td").map(function() {
			return $(this).text();
		}).get();
		var doctorCode = rowData[1];
		doctorCodeHidden = doctorCode;
		action = "doctor/showDetail?doctorCode=" + doctorCode;
		$('#myModal').modal({
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
		        	$('#detailDoctor').replaceWith(response);
		        	$( '#editorCrud' ).ckeditor();  
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
		action = "doctor/searchAdvance";
		var customer= $('form[name=doctorSearchFrm]').serializeToJSON();
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
	action = "doctor/searchAdvance";
	var customer= $('form[name=doctorSearchFrm]').serializeToJSON();
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
	action = "doctor/searchAdvance";
	var customer= $('form[name=doctorSearchFrm]').serializeToJSON();
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
	$('#editDoctorModal').modal({
       backdrop: 'static',
       keyboard: false
    });
	action = "doctor/showDetail?doctorCode=" + doctorCodeHidden + "&mode=edit";
	$.ajax({
        type: "GET",
        contentType: "application/html",
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	$('#detailDoctorEdit').replaceWith(response);
        	$( '#editorEdit' ).ckeditor();
        	var dobEdit = $("#dobEdit").val();
        	var timestamp = $.datepicker.parseDate('dd/mm/yy', dobEdit);
        	$('#reservationdateedit').datetimepicker({
		        date: new Date(timestamp),
		        format: 'DD/MM/YYYY'
		    });
        	var dowEdit = $("#dowEdit").val();
        	var timestampDow = $.datepicker.parseDate('dd/mm/yy', dowEdit);
        	$('#dateOfWorkEdit').datetimepicker({
        		date: new Date(timestampDow),
        		format: 'DD/MM/YYYY'
        	});
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
	$("#dob").val("");
	$("input, select").removeClass('is-invalid');
	$("input:text").val('');
	CKEDITOR.instances['experienceAdd'].setData('');
	$('input[type="radio"]').prop('checked', false);
	var imageDefault = $('#defaultImg').val();
	$('#blah').attr('src', imageDefault);
	$( "#imgInp" ).val("")
	$('#modalAddDoctor').modal({
           backdrop: 'static',
           keyboard: false
    });
}

function addAction() {
	action = "doctor/add";
	var paramBean = {};
	var doctor= $('form[name=doctorFrm]').serializeToJSON();
	var experience = CKEDITOR.instances['experienceAdd'].getData();
	doctor.gender = doctor.customRadioAdd1;
	doctor.gender = -1 ;
	if(doctor.customRadioAdd1) {
		doctor.gender = doctor.customRadioAdd1;
	}
	
	doctor.experienceStr = experience;
	if(doctor.dobStr) {
		var timestamp = $.datepicker.parseDate('dd/mm/yy', doctor.dobStr);
		doctor.dob = new Date(timestamp);
	} else {
		doctor.dob = new Date();
	}
	if(doctor.dowStr) {
		var timestamp = $.datepicker.parseDate('dd/mm/yy', doctor.dowStr);
		doctor.dow = new Date(timestamp);
	} else {
		doctor.dow = new Date();
	}
	paramBean.data = doctor;
	paramBean.currentPage = 1;
	
	var searchAdvanceParam = $('form[name=doctorSearchFrm]').serializeToJSON();
	var advanceParam = {};
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;
	
	var doctorForm= $('#doctorForm')[0];
	var data = new FormData(doctorForm);
	data.append('dob', doctor.dob);
	data.append('dow', doctor.dow);
	data.append('gender', doctor.gender);
	if(experience) {
		data.append('experience', experience);
	}
	
	if(validateDoctorFormAdd(doctor)){
			$.ajax({
	        type: "POST",
	        enctype: 'multipart/form-data',
	        url: action,
	        dataType: 'html',
	        data : data,
	        cache: false,
	        timeout: 600000,
	        processData: false,
	        contentType: false,
	        success: function (response) {
	        	mess = $(response).find("input[id='msg-add']").val();
	        	showDialogConfirm(mess);
        		$('#modalAddDoctor').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		//$("#customerTbl").replaceWith(response);
	        		$("#refresh-section").replaceWith(response);
		        	drawTable(doctorDataTable);
	        	}
	        	
	        },
	        error: function (e) {
	            console.log(e);
	            mess = "SYS_ERR";
	            showDialogConfirm(mess);
	        }
	    });
	}
	
}

function editAction() {
	action = "doctor/edit";
	var doctor= $('form[name=doctorFrmEdit]').serializeToJSON();
	var branchCode = $('#branchEdit').val();
	doctor.gender = -1 ;
	if(doctor.customRadioEdit1) {
		doctor.gender = doctor.customRadioEdit1;
	}
	var experience = CKEDITOR.instances['editorEdit'].getData();
	doctor.experienceStr = experience;
	if(doctor.dobStr) {
		var timestamp = $.datepicker.parseDate('dd/mm/yy', doctor.dobStr);
		doctor.dob = new Date(timestamp);
	} else {
		doctor.dob = new Date();
	}
	if(doctor.dowStr) {
		var timestamp = $.datepicker.parseDate('dd/mm/yy', doctor.dowStr);
		doctor.dow = new Date(timestamp);
	} else {
		doctor.dow = new Date();
	}
	
	var paramBean = {};
	paramBean.data = doctor;
	paramBean.currentPage = currentPage;
	
	var searchAdvanceParam = $('form[name=doctorSearchFrm]').serializeToJSON();
	var advanceParam = {};
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;
	
	var doctorForm= $('#doctorFormEdit')[0];
	var data = new FormData(doctorForm);
	data.append('dob', doctor.dob);
	data.append('dow', doctor.dow);
	data.append('gender', doctor.gender);
	data.append('branchCode', branchCode);
	if(experience) {
		data.append('experience', experience);
	}
	
	if(validateDoctorFormEdit(doctor)){
			$.ajax({
	        type: "POST",
//	        contentType : 'application/json',
	        enctype: 'multipart/form-data',
	        url: action,
	        dataType: 'html',
	        data : data,
	        cache: false,
	        timeout: 600000,
	        processData: false,
	        contentType: false,
	        success: function (response) {
	        	mess = $(response).find("input[id='msg-add']").val();
	        	showDialogConfirm(mess);
        		$('#editDoctorModal').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		$("#refresh-section").replaceWith(response);
		        	drawTable(doctorDataTable);
	        	}
	        	
	        },
	        error: function (e) {
	            console.log(e);
	            mess = "SYS_ERR";
	            showDialogConfirm(mess);
	        }
	    });
	}
	
}

function validateDoctorFormEdit(doctor) {
	objErrors = [];
	result = true;
	objError = {}
	if(!doctor.doctorName) {
		result = false;
		objError = getObjectError(false, 'doctornameEdit', 'Tên bác sĩ là bắt buộc');
	} else {
		objError = getObjectError(true, 'doctornameEdit', '');
	}
	objErrors.push(objError);
	
	if(!doctor.address) {
		result = false;
		objError = getObjectError(false, 'addressEdit', 'Địa chỉ bác sĩ là bắt buộc');
	} else {
		objError = getObjectError(true, 'addressEdit', '');
	}
	objErrors.push(objError);
	
	if(!doctor.email) {
		result = false;
		objError = getObjectError(false, 'emailEdit', 'Email bác sĩ là bắt buộc');
	} else {
		if(!isEmail(doctor.email)) {
			result = false;
			objError = getObjectError(false, 'emailEdit', 'Địa chỉ Email không đúng!');
		} else {
			objError = getObjectError(true, 'emailEdit', '');
		}
	}
	objErrors.push(objError);
	
	
	if(!doctor.phone) {
		result = false;
		objError = getObjectError(false, 'phoneEdit', 'SĐT bác sĩ là bắt buộc');
	} else {
		if(!isPhoneNo(doctor.phone)) {
			result = false;
			objError = getObjectError(false, 'phoneEdit', 'SĐT bác sĩ không đúng!');
		} else {
			objError = getObjectError(true, 'phoneEdit', '');
		}
	}
	objErrors.push(objError);
	
	if(!doctor.experienceStr) {
		result = false;
		objError = getObjectError(false, 'editorEdit', 'Kinh nghiệm bác sĩ là bắt buộc');
	} else {
		objError = getObjectError(true, 'editorEdit', '');
	}
	objErrors.push(objError);
	showErr(objErrors);
	return result;
}

function validateDoctorFormAdd(doctor) {
	objErrors = [];
	result = true;
	objError = {}
	if(doctor.branchCode ==0) {
		result = false;
		objError = getObjectError(false, 'branchAdd', 'Chi nhánh là bắt buộc');
	} else {
		objError = getObjectError(true, 'branchAdd', '');
	}
	objErrors.push(objError);
	
	if(!doctor.doctorName) {
		result = false;
		objError = getObjectError(false, 'doctornameAdd', 'Tên bác sĩ là bắt buộc');
	} else {
		objError = getObjectError(true, 'doctornameAdd', '');
	}
	objErrors.push(objError);
	
	if(!doctor.address) {
		result = false;
		objError = getObjectError(false, 'addressAdd', 'Địa chỉ bác sĩ là bắt buộc');
	} else {
		objError = getObjectError(true, 'addressAdd', '');
	}
	objErrors.push(objError);
	
	if(!doctor.email) {
		result = false;
		objError = getObjectError(false, 'emailAdd', 'Email bác sĩ là bắt buộc');
	} else {
		if(!isEmail(doctor.email)) {
			result = false;
			objError = getObjectError(false, 'emailAdd', 'Địa chỉ Email không đúng!');
		} else {
			objError = getObjectError(true, 'emailAdd', '');
		}
	}
	objErrors.push(objError);
	
	
	if(!doctor.phone) {
		result = false;
		objError = getObjectError(false, 'phoneAdd', 'SĐT bác sĩ là bắt buộc');
	} else {
		if(!isPhoneNo(doctor.phone)) {
			result = false;
			objError = getObjectError(false, 'phoneAdd', 'SĐT bác sĩ không đúng!');
		} else {
			objError = getObjectError(true, 'phoneAdd', '');
		}
	}
	objErrors.push(objError);
	
	if(!doctor.experienceStr) {
		result = false;
		objError = getObjectError(false, 'experienceAdd', 'Kinh nghiệm bác sĩ là bắt buộc');
	} else {
		objError = getObjectError(true, 'experienceAdd', '');
	}
	objErrors.push(objError);
	showErr(objErrors);
	return result;
}

function searchAdvance() {
	action = "doctor/searchAdvance";
	var doctor= $('form[name=doctorSearchFrm]').serializeToJSON();
	var paramBean = {};
	paramBean.data = doctor;
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
        	currentPage = 1;
        	bindClick();
        },
        error: function (e) {
            console.log(e);
            alert(e);
        }
    });
}

function getDistrict(e) {
	var provinceId = $(e)[0].value;
	var idFragment = "pages/catalog/doctorAddNew";
	var fragment = "reload-district";
	var eId = $(e).attr('id');
	if(eId.includes("Edit")) {
		fragment = "reload-district-edit";
		idFragment = "pages/catalog/doctorEdit";
	}
	var action = contextRoot + "/api/getDistrict?provinceId=" + provinceId +"&fragmentResult=" + idFragment.trim() +"&fragment=" + fragment;
	getDistrictOrWard(action, fragment);
	$("#wardAdd").val(0);
	$("#wardEdit").val(0);
}

function getWard(e) {
	var districtId = $(e)[0].value;
	var idFragment = "pages/catalog/doctorAddNew";
	var fragment = "reload-ward";
	var eId = $(e).attr('id');
	if(eId.includes("Edit")) {
		fragment = "reload-ward-edit";
		var idFragment = "pages/catalog/doctorEdit";
	}
	var action = contextRoot + "/api/getWard?districtId=" + districtId +"&fragmentResult=" + idFragment.trim() +"&fragment=" + fragment;
	getDistrictOrWard(action, fragment);
}

function changeValueChecked(e) {
	$(e).val($(e)[0].checked ? 1 : 0);
	var eValue = $(e).val();
	var eId = $(e).attr('id');
	var indexEID = eId.charAt(eId.length-1)
	var eHiddenStr = eId.slice(0, -1);
	var eHidden = eId + 'Hidden';
	$('#'+eHidden).val($(e).val());
}

