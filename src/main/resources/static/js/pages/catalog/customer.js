//$( document ).ready(bindClick);
var tablePetHtml;
var cusCodeHidden;
var lstPetDel = [];
var currentPage;
var rowCountBefore;
var customerDataTable;
var contextRoot;
$(document).ready(function() {
	//bindClick();
	customerDataTable = 'customerDataTbl';
	drawTable(customerDataTable);
	validateAdd();
	validateAddPet();
	contextRoot = $('#contextRootSession').val();
//    $('#reservationdate1').datetimepicker({
//    	format: 'DD/MM/YYYY',
////    	debug:true,
//    	beforeShow: function(input, inst) { 
//            inst.dpDiv.css({"z-index":999});
//        }
//    });
    
/*    $('.datetimepicker-input').datepicker({
    //comment the beforeShow handler if you want to see the ugly overlay
    beforeShow: function() {
        setTimeout(function(){
            $('.bootstrap-datetimepicker-widget.dropdown-menu').css('z-index', 99999999999999);
        }, 0);
    }
});*/
});

function bindClick() {
	//$(".text_data").on("click", function() {
	$('#customerDataTbl').on("click", "tbody tr", function() {
		var rowData = $(this).children("td").map(function() {
			return $(this).text();
		}).get();
		var cusCode = rowData[1];
		cusCodeHidden = cusCode;
		action = "customer/showDetail?cusCode=" + cusCode;
		//action = "customer/showDetail?cusCode=" + cusCode + "&mode=edit";
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
		        	$('#detailCustomer').replaceWith(response);
		        	//$('#detailCustomerEdit').replaceWith(response);
		        	$('#reservationdate1').datetimepicker({
		            	format: 'DD/MM/YYYY',
//		            	debug:true,
		            	beforeShow: function(input, inst) { 
		                    inst.dpDiv.css({"z-index":999});
		                }
		            });
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
		action = "customer/searchAdvance";
		var customer= $('form[name=customerSearchFrm]').serializeToJSON();
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
	action = "customer/searchAdvance";
	var customer= $('form[name=customerSearchFrm]').serializeToJSON();
	var paramBean = {};
	paramBean.data = customer;
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
	        	//$("#customerTbl").replaceWith(response);
	        	$("#refresh-section").replaceWith(response);
	        	//$(".page-item").removeClass("active");
				//$(e.parentNode).addClass("active");
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
	//action = "customer/search?page=" + pageNum;
	//idTable = 'customerTbl';
	//lastPageProcess(action, idTable);
	action = "customer/searchAdvance";
	var customer= $('form[name=customerSearchFrm]').serializeToJSON();
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
	        	//$("#customerTbl").replaceWith(response);
	        	$("#refresh-section").replaceWith(response);
	        	//$(".page-item").removeClass("active");
				//$(e.parentNode).addClass("active");
	        	bindClick();
	        },
	        error: function (e) {
	            alert(e);
	        }
	    });
}

function showEditPopup() {
	rowCountBefore = 0;
	if(!currentPage) {
		currentPage = 1;
	}
	lstPetDel = [];
	$('#editCusModal').modal({
       backdrop: 'static',
       keyboard: false
    });
	action = "customer/showDetail?cusCode=" + cusCodeHidden + "&mode=edit";
	$.ajax({
        type: "GET",
        contentType: "application/html",
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	$('#detailCustomerEdit').replaceWith(response);
        	var rowCount = $('#tablePetEdit tbody tr').length;
        	if(rowCount > 1) {
        		row = $('#tablePetEdit tbody tr')[0];
        		$(row).remove();
        		$(".show-pet-table").addClass("none-display");
        		$(".customer-info-add").removeClass("none-display");
        		var rowCountAfterDel = $('#tablePetEdit tbody tr').length;
        		if(rowCountAfterDel ==1) {
        			$('#tablePetEdit tbody tr:first-child td:first-child button:last-child').addClass("display-init");
        			
        		}
        	}
        	$('.reserver-date').each(function(index) {
    	    	$(this).datetimepicker({
    	        	format: 'DD/MM/YYYY',
    	        });
    	    });
        },
        error: function (e) {
            alert(e);
        }
    });
}

function showAddPopup() {
	hidePetTbl();
	rowCountBefore = 0;
	$('.txt-popup-add-cus').val('');
	$(".slt-popup-add-cus").val("0");
	$("input, select").removeClass('is-invalid');
	var rowCount = $('#tablePet tbody tr').length;
	if(!tablePetHtml && rowCount == 1){
		tablePetHtml = $("#tablePet")[0].outerHTML;
	}
	if(rowCount > 1) {
		$("#tablePet").replaceWith(tablePetHtml);
	}
	
	$('#modalAddCustomer').modal({
           backdrop: 'static',
           keyboard: false
    });
	$('#reservationdate1').datetimepicker({
    	format: 'DD/MM/YYYY',
//    	debug:true,
    	beforeShow: function(input, inst) { 
            inst.dpDiv.css({"z-index":999});
        }
    });
}

function addNewRow(e) {
	//var table = $( '#tablePet' )[0];
	var table = $(e).closest('table')[0].id;
	var thisRow = $( e ).closest( 'tr' )[0];
	var ele = table + ' tbody tr';
    //$( thisRow ).clone().insertAfter( thisRow ).find( 'input:text, input:hidden' ).val( '' ).removeAttr('value');
    var rowCount = $('#' + ele).length;
    var newIDSuffix = 1 + rowCount;
    if(rowCountBefore) {
    	newIDSuffix = 1 + rowCountBefore;
    }
    var cloned = $(thisRow).clone();
    cloned.find('td.drop-list-add').each(function(index) {
    	var increment = newIDSuffix + index;
    	var id = $(this).attr('id');
    	id = id.substring(0, id.length - 1) + increment;
        $(this).attr('id', id);
    });
    
    cloned.find('select[name="breed"]').each(function(index) {
    	var increment = newIDSuffix + index;
    	var id = $(this).attr('id');
    	id = id.substring(0, id.length - 1) + increment;
        $(this).attr('id', id).val(0);
    });
    
    cloned.find('[name="specCode"]').each(function(index) {
    	$(this).find('option').remove().end().append('<option value="0">-- Chọn --</option>')
    });
    
    // clone radio button
    var newIDRadio = 1 + (rowCount *2);
    if(rowCountBefore){
    	newIDRadio = 1 + (rowCountBefore *2);
    }
    cloned.find('input:radio').each(function(index) {
    	var id = $(this).attr('id');
    	if(id.includes('Edit') && index == 0) {
    		newIDRadio = newIDRadio + 2;
    	}
    	var increment = newIDRadio + index;
    	id = id.substring(0, id.length - 1) + increment;
        $(this).attr('id', id);
        var name = $(this).attr('name');
        name =  name.substring(0, name.length - 1) + newIDRadio;
        $(this).attr('name', name).prop('checked', false);
    });

    cloned.find('label.custom-control-label').each(function(index) {
    	var increment = newIDRadio + index;
    	var forId = $(this).attr('for');
    	forId = forId.substring(0, forId.length - 1) + increment;
        $(this).attr('for', forId);
    });
    
    //cloned.find('input[type="hidden"]').each(function(index) {
    	//var increment = newIDRadio + index;
    	//var hiddenId = $(this).attr('id');
    	//hiddenId = hiddenId.substring(0, hiddenId.length - 1) + increment;
        //$(this).attr('id', hiddenId);
    //});
    
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
    
 // update id for vaccin
    cloned.find('input.chk-vaccin').each(function(index) {
    	var increment = newIDSuffix + index;
    	var id = $(this).attr('id');
    	id = id.substring(0, id.length - 1) + increment;
        $(this).attr('id', id);
    });
    
    // update label for vaccin
    cloned.find('label.lbl-vaccin').each(function(index) {
    	var increment = newIDSuffix + index;
    	var forId = $(this).attr('for');
    	forId = forId.substring(0, forId.length - 1) + increment;
        $(this).attr('for', forId);
    });
    
 // update id for vaccin hidden
    cloned.find('input.vaccin-hidden').each(function(index) {
    	var increment = newIDSuffix + index;
    	var id = $(this).attr('id');
    	id = id.substring(0, id.length - 1) + increment;
        $(this).attr('id', id);
    });
    
 // update id for worm
    cloned.find('input.chk-worm').each(function(index) {
    	var increment = newIDSuffix + index;
    	var id = $(this).attr('id');
    	id = id.substring(0, id.length - 1) + increment;
        $(this).attr('id', id);
    });
    
    // update label for worm
    cloned.find('label.lbl-worm').each(function(index) {
    	var increment = newIDSuffix + index;
    	var forId = $(this).attr('for');
    	forId = forId.substring(0, forId.length - 1) + increment;
        $(this).attr('for', forId);
    });
    
 // update id for worm hidden
    cloned.find('input.worm-hidden').each(function(index) {
    	var increment = newIDSuffix + index;
    	var id = $(this).attr('id');
    	id = id.substring(0, id.length - 1) + increment;
        $(this).attr('id', id);
    });
    
 // update id for sterilized
    cloned.find('input.chk-sterili').each(function(index) {
    	var increment = newIDSuffix + index;
    	var id = $(this).attr('id');
    	id = id.substring(0, id.length - 1) + increment;
        $(this).attr('id', id);
    });
    
    // update label for sterilized
    cloned.find('label.lbl-sterili').each(function(index) {
    	var increment = newIDSuffix + index;
    	var forId = $(this).attr('for');
    	forId = forId.substring(0, forId.length - 1) + increment;
        $(this).attr('for', forId);
    });
    
 // update id for sterilized hidden
    cloned.find('input.sterili-hidden').each(function(index) {
    	var increment = newIDSuffix + index;
    	var id = $(this).attr('id');
    	id = id.substring(0, id.length - 1) + increment;
        $(this).attr('id', id);
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
		var rowsData = $( e ).closest( 'tr' ).children("td").children("input,select, div");
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
				case 4:
					petDeleted.specCode = $(row).val();
					break;
				case 5:
					petDeleted.certificateBirth = $(row).val();
					break;
				case 6:
					petDeleted.sex = $(row).children('div').children('input:radio:checked').val();
					break;
			}
			petDeleted.isDeleted = 1;
		});
		if(petDeleted.petCode && petDeleted.petName) {
			lstPetDel.push(petDeleted);
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

function addAction() {
	action = "customer/add";
	var paramBean = {};
	var customer= $('form[name=customerFrm]').serializeToJSON();
	paramBean.data = customer;
	paramBean.currentPage = 1;
	if(!$( ".customer-info-add" ).hasClass( "none-display" )) {
		var lstPet = [];
		var lstPetData= $('form[name=petLstFrm]').serializeArray();
		var sexIndex = 0;
		for(i=0; i<lstPetData.length/10; i++) {
			var pet = {};
			pet.petName = lstPetData[(i*10)-sexIndex].value;
			var specie = {};
			specie.specCode = lstPetData[(i*10+1)-sexIndex].value;
			pet.specie = specie;
			pet.specCode = lstPetData[(i*10+2)-sexIndex].value;
			if(lstPetData[(i*10+3)-sexIndex].value != null) {
				var timestamp = $.datepicker.parseDate('dd/mm/yy', lstPetData[(i*10+3)-sexIndex].value);
				pet.dob = new Date(timestamp);
			}
			pet.color = lstPetData[(i*10+4)-sexIndex].value;
			pet.microchipNo = lstPetData[(i*10+5)-sexIndex].value;
			pet.vaccinated = lstPetData[(i*10+6)-sexIndex].value;
			pet.wormed = lstPetData[(i*10+7)-sexIndex].value;
			pet.sterilized = lstPetData[(i*10+8)-sexIndex].value;
			if(lstPetData[(i*10+9)-sexIndex] 
					&& lstPetData[(i*10+9)-sexIndex].name != 'petName') {
				pet.sex = lstPetData[(i*10+9)-sexIndex].value;
//				sexIndex = 0
			} else {
				pet.sex = -1;
				sexIndex +=1;
			}
//			if(lstPetData[i*10+9]) {
//				pet.sex = lstPetData[i*10+9].value;
//			} else {
//				pet.sex = -1;
//			}
			lstPet.push(pet);
		}
		customer.lstPet = lstPet;
	}
	var param = {};
	param.cusCode = customer.cusCode;
	var validPet = false;
	if($( ".customer-info-add" ).hasClass( "none-display" )){
		validPet = true;
	} else {
		if($('#petForm').valid()) {
			validPet = true;
		}
	}
	
	var searchAdvanceParam = $('form[name=customerSearchFrm]').serializeToJSON();
	var advanceParam = {};
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;
	if($('#cusForm').valid() && validPet){
			$.ajax({
	        type: "POST",
	        contentType : 'application/json',
	        url: action,
	        dataType: 'html',
	        data : JSON.stringify(paramBean),
	        cache: false,
	        timeout: 600000,
	        success: function (response) {
	        	mess = $(response).find("input[id='msg-add']").val();
	        	showDialogConfirm(mess);
        		$('#modalAddCustomer').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		//$("#customerTbl").replaceWith(response);
	        		$("#refresh-section").replaceWith(response);
		        	//bindClick();
		        	drawTable(customerDataTable);
		        	$(".page-item").removeClass("active");
					$(".page-item:nth-child(2)").addClass("active");
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
	action = "customer/edit";
	var customer= $('form[name=customerFrmEdit]').serializeToJSON();
	var lstPet = [];
	var lstPetData= $('form[name=petLstFrmEdit]').serializeArray();
	if(!$( ".customer-info-add" ).hasClass( "none-display" )) {
		var sexIndex = 0;
		for(i=0; i<lstPetData.length/12; i++) {
			var pet = {};
			pet.id = lstPetData[(i*12) - sexIndex].value;
			pet.petName = lstPetData[(i*12+1) - sexIndex].value;
			pet.petCode = lstPetData[(i*12+2) - sexIndex].value;
			pet.specCode = lstPetData[(i*12+4) - sexIndex].value;
			if(lstPetData[(i*12+5)-sexIndex].value != null) {
				var timestamp = $.datepicker.parseDate('dd/mm/yy', lstPetData[(i*12+5)-sexIndex].value);
				pet.dob = new Date(timestamp);
			}
			pet.color = lstPetData[(i*12+6) - sexIndex].value;
			pet.microchipNo = lstPetData[(i*12+7)-sexIndex].value;
			pet.vaccinated = lstPetData[(i*12+8)-sexIndex].value;
			pet.wormed = lstPetData[(i*12+9)-sexIndex].value;
			pet.sterilized = lstPetData[(i*12+10)-sexIndex].value;
			if(lstPetData[(i*12+11)-sexIndex] 
					&& lstPetData[(i*12+11)-sexIndex].name != 'petId') {
				pet.sex = lstPetData[(i*12+11)-sexIndex].value;
				
			} else {
				pet.sex = -1;
				sexIndex +=1;
			}
			lstPet.push(pet);
		}
	}
	if(lstPetDel.length > 0) {
		lstPetDel.forEach(function(item) {
			lstPet.push(item);
		});
	}
	customer.lstPet = lstPet;
	var param = {};
	param.cusCode = customer.cusCode;
	
	var paramBean = {};
	paramBean.data = customer;
	paramBean.currentPage = currentPage;
	validPet = validatePetForm(customer.lstPet, $('form[name=petLstFrmEdit] :input[type=text]'));
	
	var searchAdvanceParam = $('form[name=customerSearchFrm]').serializeToJSON();
	var advanceParam = {};
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;
	
	if(validateCustomerForm(customer) && validPet){
			$.ajax({
	        type: "POST",
	        contentType : 'application/json',
	        url: action,
	        dataType: 'html',
	        data : JSON.stringify(paramBean),
	        cache: false,
	        timeout: 600000,
	        success: function (response) {
	        	mess = $(response).find("input[id='msg-add']").val();
	        	showDialogConfirm(mess);
        		$('#editCusModal').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		//$("#customerTbl").replaceWith(response);
	        		$("#refresh-section").replaceWith(response);
		        	//bindClick();
		        	drawTable(customerDataTable);
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

function validateAdd() {
	// add the rule here
	 $.validator.addMethod("valueNotEquals", function(value, element, arg){
	  return arg !== value;
	 }, "Value must not equal arg.");
  $('#cusForm').validate({
    rules: {
      email: {
        required: true,
        email: true,
      },
//      cusCode: {
//        required: true,
//        maxlength: 5
//      },
      phone: {
        required: true,
        number: true
      },
      cusType: {
       valueNotEquals: "0"
      },
      provinceIdAdd: {
    	  valueNotEquals: "0"
         },
      districtIdAdd: {
         valueNotEquals: "0"
            },
      wardId: {
        valueNotEquals: "0"
               },
      cusName: {
        required: true
      },
      address: {
        required: true
      },
      
    },
    messages: {
      email: {
        required: "Email là bắt buộc",
        email: "Email không hợp lệ"
      },
//      cusCode: {
//        required: "Mã KH là bắt buộc",
//        maxlength: "Mã KH không chứa nhiều hơn 5 ký tự"
//      },
      phone: {
        required: "SĐT bắt buộc nhập",
        number: "SĐT phải là số"
      },
      cusType: {
      	valueNotEquals: "Loại KH là bắt buộc",
      },
      provinceIdAdd: {
    	  valueNotEquals: "Tỉnh/ Thành phố bắt buộc",
      },
      districtIdAdd: {
    	  valueNotEquals: "Quận/ Huyện bắt buộc",
      },
      wardId: {
    	  valueNotEquals: "Xã/ phường bắt buộc",
      },
      cusName: {
      	required: "Tên KH là bắt buộc",
      },
      address: {
      	required: "Địa chỉ là bắt buộc",
      },
    },
    errorElement: 'span',
    errorPlacement: function (error, element) {
      error.addClass('invalid-feedback');
      element.closest('.msg-warning').append(error);
    },
    highlight: function (element, errorClass, validClass) {
      $(element).addClass('is-invalid');
    },
    unhighlight: function (element, errorClass, validClass) {
      $(element).removeClass('is-invalid');
    }
  });
}

function validateAddPet() {
  $('#petForm').validate({
    rules: {
      petCode: {
        required: true,
        maxlength: 5
      },
      petName: {
        required: true,
      },
      
    },
    messages: {
      petCode: {
        required: "Mã thú cưng bắt buộc",
        maxlength: "Mã thú cưng lớn hơn 5 ký tự"
      },
      petName: {
        required: "Tên thú cưng bắt buộc",
      },
    },
    errorElement: 'span',
    errorPlacement: function (error, element) {
      error.addClass('invalid-feedback');
      element.closest('.msg-warning').append(error);
      $('.pet-msg-warning').append(error).append('<br />');
      //$('.invalid-feedback').css("display", "inline");
    },
    highlight: function (element, errorClass, validClass) {
      $(element).addClass('is-invalid');
      $('.pet-msg-warning').addClass('is-invalid');
    },
    unhighlight: function (element, errorClass, validClass) {
      $(element).removeClass('is-invalid');
    }
  });
}

function showPetTbl() {
	$('.show-pet-table').addClass('none-display');
	$('.customer-info-add, .pagnation-sub').removeClass('none-display');
}

function hidePetTbl() {
	$('.show-pet-table').removeClass('none-display');
	$('.customer-info-add, .pagnation-sub').addClass('none-display');
}

function validateCustomerForm(customer) {
	objErrors = [];
	result = true;
	objError = {}
	if(!customer.cusName) {
		result = false;
		objError = getObjectError(false, 'cusnameEdit', 'Tên KH là bắt buộc');
	} else {
		objError = getObjectError(true, 'cusnameEdit', '');
	}
	objErrors.push(objError);
	
	if(!customer.address) {
		result = false;
		objError = getObjectError(false, 'addressEdit', 'Địa chỉ KH là bắt buộc');
	} else {
		objError = getObjectError(true, 'addressEdit', '');
	}
	objErrors.push(objError);
	
	if(!customer.email) {
		result = false;
		objError = getObjectError(false, 'emailEdit', 'Email KH là bắt buộc');
	} else {
		if(!isEmail(customer.email)) {
			result = false;
			objError = getObjectError(false, 'emailEdit', 'Địa chỉ Email không đúng!');
		} else {
			objError = getObjectError(true, 'emailEdit', '');
		}
	}
	objErrors.push(objError);
	
	if(customer.cusType ==0) {
		result = false;
		objError = getObjectError(false, 'cusTypeEdit', 'Loại KH là bắt buộc');
	} else {
		objError = getObjectError(true, 'cusTypeEdit', '');
	}
	objErrors.push(objError);
	
	if(!customer.phone) {
		result = false;
		objError = getObjectError(false, 'phoneEdit', 'SĐT KH là bắt buộc');
	} else {
		if(!isPhoneNo(customer.phone)) {
			result = false;
			objError = getObjectError(false, 'phoneEdit', 'SĐT KH không đúng!');
		} else {
			objError = getObjectError(true, 'phoneEdit', '');
		}
	}
	objErrors.push(objError);
	
	if(customer.provinceIdEdit ==0) {
		result = false;
		objError = getObjectError(false, 'provinceEdit', 'Tỉnh/ Thành phố là bắt buộc');
	} else {
		objError = getObjectError(true, 'provinceEdit', '');
	}
	objErrors.push(objError);
	
	if(customer.districtIdEdit ==0) {
		result = false;
		objError = getObjectError(false, 'districtEdit', 'Quận/ Huyện là bắt buộc');
	} else {
		objError = getObjectError(true, 'districtEdit', '');
	}
	objErrors.push(objError);
	
	if(customer.wardId ==0) {
		result = false;
		objError = getObjectError(false, 'wardEdit', 'Xã/ Phường là bắt buộc');
	} else {
		objError = getObjectError(true, 'wardEdit', '');
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

function getObjectErrorTable(valid, row, mess, idInput){
	objError = {};
	objError.valid = valid;
	objError.idInput = idInput;
	objError.row = row;
	objError.errMessage = mess;
	return objError;
}

function validatePetForm(lstPetData, petForm) {
	console.log(lstPetData);
	console.log(petForm);
	result = true;
	objError = {};
	objErrors = [];
	$.each(petForm, function(i, row) {
		if($(row).hasClass( "required" ) && !$( ".customer-info-add" ).hasClass( "none-display" )) {
			if(!$(row).val()){
				result = false;
				objError = getObjectErrorTable(false, row, 'Thông tin không được để trống', 'petName' + i);
			} else {
				objError = getObjectErrorTable(true, row, 'Thông tin không được để trống', 'petName' + i);
			}
			objErrors.push(objError);		
		}
	});
	
	showErrTable(objErrors);
	return result;
}

function searchAdvance() {
	action = "customer/searchAdvance";
	var customer= $('form[name=customerSearchFrm]').serializeToJSON();
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

function changeFunc(e) {
	var idSelect = e.id;
	edit = 1;
	if(idSelect.includes('breedAdd')) {
		edit = 0;
	}
	var indexRow = idSelect.substring(idSelect.length - 1);
	var selectedValue = $(e).val();
	//var action = "customer/getSpecsByBreedCode?breedCode=" + selectedValue + '&edit=' + edit + '&index=' + indexRow;
	var action = "customer/getSpecsByBreedCodeJson?breedCode=" + selectedValue + '&edit=' + edit + '&index=' + indexRow;
	$.ajax({
        type: "GET",
        contentType : 'application/json',
        url: action,
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	var select = $("#species-droplist-add"+indexRow).find('select');
        	if(edit == 1) {
        		select = $("#species-droplist-edit"+indexRow).find('select');
        	}
        	$(select).find('option').remove().end().append('<option value="0">-- Chọn --</option>')
        	var lst = response.results;
        	if(lst.length > 0) {
        		for (var i=0; i< lst.length; i++) {
        			select.append(new Option(lst[i].specName, lst[i].specCode))
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

function changeGender(e) {
	var eValue = $(e).val();
	var eName = $(e).attr('name');
	var indexEname = eName.charAt(eName.length-1)
	var eHidden = 'genderIdHidden'+indexEname;
	$('#'+eHidden).val(eValue);
	
}

function changeValueChecked(e) {
	$(e).val($(e)[0].checked ? 1 : 0);
	var eValue = $(e).val();
	var eId = $(e).attr('id');
	var indexEID = eId.charAt(eId.length-1)
	var eHiddenStr = eId.slice(0, -1);
	var eHidden = eHiddenStr + 'Hidden' + indexEID;
	$('#'+eHidden).val($(e).val());
}

function getDistrict(e) {
	var provinceId = $(e)[0].value;
	var idFragment = "pages/catalog/customerAddNew";
	var fragment = "reload-district";
	var eId = $(e).attr('id');
	if(eId.includes("Edit")) {
		fragment = "reload-district-edit";
		idFragment = "pages/catalog/customerEdit";
	}
	var action = contextRoot + "/api/getDistrict?provinceId=" + provinceId +"&fragmentResult=" + idFragment.trim() +"&fragment=" + fragment;
	getDistrictOrWard(action, fragment);
	$("#wardAdd").val(0);
	$("#wardEdit").val(0);
}

function getWard(e) {
	var districtId = $(e)[0].value;
	var idFragment = "pages/catalog/customerAddNew";
	var fragment = "reload-ward";
	var eId = $(e).attr('id');
	if(eId.includes("Edit")) {
		fragment = "reload-ward-edit";
		var idFragment = "pages/catalog/customerEdit";
	}
	var action = contextRoot + "/api/getWard?districtId=" + districtId +"&fragmentResult=" + idFragment.trim() +"&fragment=" + fragment;
	getDistrictOrWard(action, fragment);
}