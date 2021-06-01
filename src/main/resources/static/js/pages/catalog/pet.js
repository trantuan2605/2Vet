//$( document ).ready(bindClick);
var tablePetHtml;
var petCodeHidden;
var lstPetDel = [];
var currentPage;
var petDataTable;
$(document).ready(function() {
	petDataTable = 'petDataTbl';
	drawTable(petDataTable);
	$('#petdob').datetimepicker({
    	format: 'DD/MM/YYYY'
    });
    $("#imgInp").change(function() {
		  readURLAdd(this);
		});
});

function bindClick() {
//	$(".text_data").on("click", function() {
	$('#petDataTbl').on("click", "tbody tr", function() {
		var rowData = $(this).children("td").map(function() {
			return $(this).text();
		}).get();
		var petCode = rowData[1];
		petCodeHidden = petCode;
		action = "pet/showDetail?petCode=" + petCode;
		$('#petModal').modal({
		//$('#editPetModal').modal({
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
		        	$('#detailPet').replaceWith(response);
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
		//action = "customer/search?page=" + pageNum;
		action = "pet/searchAdvance";
		var pet= $('form[name=petSearchFrm]').serializeToJSON();
		var paramBean = {};
		paramBean.data = pet;
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
	        	//$("#customerTbl").replaceWith(response);
	        	$("#refresh-section").replaceWith(response);
	        	//$(".page-item").removeClass("active");
				$(e.parentNode).addClass("active");
	        	bindClick();
	        },
	        error: function (e) {
	            alert(e);
	        }
	    });
	}
	
function firstPage(){
	//action = "customer/search?page=1";
	currentPage = 1;
	action = "pet/searchAdvance";
	var pet= $('form[name=petSearchFrm]').serializeToJSON();
	var paramBean = {};
	paramBean.data = pet;
	paramBean.currentPage = 1;
	//idTable = 'customerTbl';
	//currentPage = 1;
	//firstPageProcess(action, idTable);
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
	action = "pet/searchAdvance";
	var pet= $('form[name=petSearchFrm]').serializeToJSON();
	var paramBean = {};
	paramBean.data = pet;
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
	lstPetDel = [];
	$('#editPetModal').modal({
       backdrop: 'static',
       keyboard: false
    });
	action = "pet/showDetail?petCode=" + petCodeHidden + "&mode=edit";
	$.ajax({
        type: "GET",
        contentType: "application/html",
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	$('#detailPetEdit').replaceWith(response);
        	var dobEdit = $("#dobEdit").val();
        	var timestamp = $.datepicker.parseDate('dd/mm/yy', dobEdit);
        	$('#dobPickerEdit').datetimepicker({
		        date: new Date(timestamp),
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
	$("input:text").val('');
	$(".checkbox-hidden").val('0');
	$("input, select").removeClass('is-invalid');
	$('input[type="radio"]').prop('checked', false);
	$('input[type="checkbox"]').prop('checked', false); 
	var imageDefault = $('#defaultImg').val();
	$('#blah').attr('src', imageDefault);
	$( "#imgInp" ).val("")
	$('#modalAddPet').modal({
           backdrop: 'static',
           keyboard: false
    });
}

function addNewRow(e) {
	//var table = $( '#tablePet' )[0];
	var table = $(e).closest('table')[0].id;
	var thisRow = $( e ).closest( 'tr' )[0];
	var ele = table + ' tbody tr';
    $( thisRow ).clone().insertAfter( thisRow ).find( 'input:text, input:hidden' ).val( '' ).removeAttr('value');
    var rowCount = $('#' + ele).length;
    if(rowCount > 1) {
    	$(".del-tr-clone").removeClass("display-init");
    }
}

function removeRow(e) {
		var rowsData = $( e ).closest( 'tr' ).children("td").children("input");
		var petDeleted = {};
		$.each(rowsData, function(i, row) {
			switch(i) {
				case 0:
					petDeleted.id = $(row).val();
					break;
				case 1:
					petDeleted.petCode = $(row).val();
					break;
				case 2:
					petDeleted.petName = $(row).val();
					break;
				case 3:
					petDeleted.sex = $(row).val();
					break;
				case 4:
					petDeleted.certificateBirth = $(row).val();
					break;
			}
			petDeleted.isDeleted = 1;
		});
		if(petDeleted.petCode && petDeleted.petName) {
			lstPetDel.push(petDeleted);
		}
	
	var table = $(e).closest('table')[0].id;
	var ele = table + ' tbody tr';
	var thisRow = $( e ).closest( 'tr' )[0].remove();
	var rowCount = $('#' + ele).length;
    if(rowCount == 1) {
    	$(".del-tr-clone").addClass("display-init");
    }
}

function addAction() {
	action = "pet/add";
	var paramBean = {};
	var pet= $('form[name=petFrm]').serializeToJSON();
	var specie = {};
	specie.specCode = pet.specCode;
	pet.specie = specie;
	pet.sex = -1 ;
	if(pet.customRadioAdd1) {
		pet.sex = pet.customRadioAdd1;
	}
	var customer = {};
	customer.cusCode = pet.cusCode;
	pet.customer = customer;
	paramBean.data = pet;
	paramBean.currentPage = 1;
	
	var petFrom= $('#petForm')[0];
	var data = new FormData(petFrom);
	
	if(pet.dobStr) {
		var timestamp = $.datepicker.parseDate('dd/mm/yy', pet.dobStr);
		pet.dob = new Date(timestamp);
	} else {
		pet.dob = new Date();
	}
	
	data.append('dob', pet.dob);
//	data.append('customer', customer);
//	data.append('vaccinated', 1);
//	data.append('wormed', 1);
//	data.append('sterilized', 1);
	data.append('cusCodeParam', pet.cusCode);
	data.append('sex', pet.sex);
	
	var searchAdvanceParam = $('form[name=petSearchFrm]').serializeToJSON();
	var advanceParam = {};
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;
	if(validatePetFormAdd(pet)){
			$.ajax({
	        type: "POST",
//	        contentType : 'application/json',
	        enctype: 'multipart/form-data',
	        url: action,
	        dataType: 'html',
//	        data : JSON.stringify(paramBean),
	        data : data,
	        cache: false,
	        timeout: 600000,
	        processData: false,
	        contentType: false,
	        success: function (response) {
	        	mess = $(response).find("input[id='msg-add']").val();
	        	showDialogConfirm(mess);
        		$('#modalAddPet').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		$("#refresh-section").replaceWith(response);
	        		drawTable(petDataTable);
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
	action = "pet/edit";
	var pet= $('form[name=petFrmEdit]').serializeToJSON();
	var param = {};
	param.petCode = pet.petCode;
	var customer = {};
	customer.cusName = pet.cusName;
	customer.cusCode = pet.cusCode;
	pet.customer = customer;
	pet.sex = pet.customRadioEdit1;
	var specie = {};
	specie.specCode = pet.specCode;
	pet.specie = specie;
	var customer = {};
	customer.cusCode = pet.cusCode;
	pet.customer = customer;
	var paramBean = {};
	paramBean.data = pet;
	paramBean.currentPage = currentPage;
	
	var searchAdvanceParam = $('form[name=petSearchFrm]').serializeToJSON();
	var advanceParam = {};
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;
	
	var petFrom= $('#petFormEdit')[0];
	var data = new FormData(petFrom);
	if(pet.dobStr) {
		var timestamp = $.datepicker.parseDate('dd/mm/yy', pet.dobStr);
		pet.dob = new Date(timestamp);
	} else {
		pet.dob = new Date();
	}
	data.append('dob', pet.dob);
	data.append('cusCodeParam', pet.cusCode);
	data.append('sex', pet.sex);
	
	if(validatePetFormEdit(pet)){
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
        		$('#editPetModal').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		$("#refresh-section").replaceWith(response);
	        		drawTable(petDataTable);
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

function validatePetFormAdd(pet) {
	objErrors = [];
	result = true;
	objError = {}
	
	/*if(!pet.petCode) {
		result = false;
		objError = getObjectError(false, 'petcodeAdd', 'Mã thú cưng là bắt buộc');
	} else {
		objError = getObjectError(true, 'petcodeAdd', '');
	}
	objErrors.push(objError);*/
	
	if(!pet.petName) {
		result = false;
		objError = getObjectError(false, 'petnameAdd', 'Tên thú cưng là bắt buộc');
	} else {
		objError = getObjectError(true, 'petnameAdd', '');
	}
	objErrors.push(objError);
	
	if(!pet.specCode || pet.specCode == '0') {
		result = false;
		objError = getObjectError(false, 'specieAdd', 'Giống thú cưng là bắt buộc');
	} else {
		objError = getObjectError(true, 'specieAdd', '');
	}
	objErrors.push(objError);
	
	if(!pet.cusCode || pet.cusCode == '0') {
		result = false;
		objError = getObjectError(false, 'cuscodeAdd', 'Mã khách hàng là bắt buộc');
	} else {
		objError = getObjectError(true, 'cuscodeAdd', '');
	}
	objErrors.push(objError);
	showErr(objErrors);
	return result;
}

function validatePetFormEdit(pet) {
	objErrors = [];
	result = true;
	objError = {}
	if(!pet.petName) {
		result = false;
		objError = getObjectError(false, 'petnameEdit', 'Tên thú cưng là bắt buộc');
	} else {
		objError = getObjectError(true, 'petnameEdit', '');
	}
	objErrors.push(objError);
	
	if(!pet.specCode || pet.specCode == '0') {
		result = false;
		objError = getObjectError(false, 'specEdit', 'Giống thú cưng là bắt buộc');
	} else {
		objError = getObjectError(true, 'specEdit', '');
	}
	objErrors.push(objError);
	
	showErr(objErrors);
	return result;
}

function showPetTbl() {
	$('.show-pet-table').addClass('none-display');
	$('.customer-info-add, .pagnation-sub').removeClass('none-display');
}

function searchAdvance() {
	action = "pet/searchAdvance";
	var pet= $('form[name=petSearchFrm]').serializeToJSON();
	var paramBean = {};
	paramBean.data = pet;
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

function changeFunc(e) {
	//var selectedValue = $('#breedEdit option:selected').val();
	var idSelect = e.id;
	edit = 1;
	if(idSelect == 'breedAdd') {
		edit = 0;
	}
	var selectedValue = $(e).val();
	var action = "pet/getSpecsByBreedCode?breedCode=" + selectedValue + '&edit=' + edit;
	$.ajax({
        type: "GET",
        contentType : 'application/json',
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	if (edit == 0) {
        		$("#species-droplist-add").replaceWith(response);
        	} else {
        		$("#species-droplist-edit").replaceWith(response);
        	}
        },
        error: function (e) {
            console.log(e);
            mess = "SYS_ERR";
            showDialogConfirm(mess);
        }
    });
}

function changeCusCode(e) {
	var id = e.id;
	var cusNameSelected = $('#' + id + ' option:selected').text();
	$('#cusnameAdd').val(cusNameSelected);
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