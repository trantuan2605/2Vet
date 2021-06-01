var branchCodeHidden;
var currentPage;
var rowCountBefore;
var branchDataTable;
var branchServiceDataTable;
var bsTable;
var contextRoot;
$(document).ready(function() {
	branchDataTable = 'branchDataTbl';
	drawTable(branchDataTable);
	//Date range picker
    $('#doeAdd').datetimepicker({
    	format: 'DD/MM/YYYY'
    });
	contextRoot = $('#contextRootSession').val();
});

function bindClick() {
	//$(".text_data").on("click", function() {
	$('#branchDataTbl').on("click", "tbody tr", function() {
		var rowData = $(this).children("td").map(function() {
			return $(this).text();
		}).get();
		var branchCode = rowData[1];
		branchCodeHidden = branchCode;
		action = "branch/showDetail?branchCode=" + branchCode;
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
		        	$('#detailBranch').replaceWith(response);
		        	branchServiceDataTable = 'branchServiceDataTbl';
		        	drawTableBranchService(branchServiceDataTable);
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
		action = "branch/searchAdvance";
		var branch= $('form[name=branchSearchFrm]').serializeToJSON();
		var paramBean = {};
		paramBean.data = branch;
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
	action = "branch/searchAdvance";
	var branch= $('form[name=branchSearchFrm]').serializeToJSON();
	var paramBean = {};
	paramBean.data = branch;
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
	action = "branch/searchAdvance";
	var branch= $('form[name=branchSearchFrm]').serializeToJSON();
	var paramBean = {};
	paramBean.data = branch;
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
	$('#editBranchModal').modal({
       backdrop: 'static',
       keyboard: false
    });
	action = "branch/showDetail?branchCode=" +branchCodeHidden + "&mode=edit";
	$.ajax({
        type: "GET",
        contentType: "application/html",
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	$('#detailBranchEdit').replaceWith(response);
        	var doeEdit = $("#doeEdit").val();
        	var timestampDow = $.datepicker.parseDate('dd/mm/yy', doeEdit);
        	$('#reservationdateedit').datetimepicker({
        		date: new Date(timestampDow),
        		format: 'DD/MM/YYYY'
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
	$('#modalAddBranch').modal({
           backdrop: 'static',
           keyboard: false
    });
}

function addAction() {
	action = "branch/add";
	var paramBean = {};
	var branch= $('form[name=branchFrm]').serializeToJSON();
	
	if(branch.doeStr) {
		var timestamp = $.datepicker.parseDate('dd/mm/yy', branch.doeStr);
		branch.doe = new Date(timestamp);
	} else {
		branch.doe = new Date();
	}
	
	paramBean.data = branch;
	paramBean.currentPage = 1;
	
	var searchAdvanceParam = $('form[name=branchSearchFrm]').serializeToJSON();
	var advanceParam = {};
	
	var branchForm= $('#branchForm')[0];
	var data = new FormData(branchForm);
	
	data.append('doe', branch.doe);
	
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;
	if(validateBranchFormAdd(branch)){
			$.ajax({
	        type: "POST",
//	        contentType : 'application/json',
	        enctype: 'multipart/form-data',
	        url: action,
	        dataType: 'html',
			//data : JSON.stringify(paramBean),
			data : data,
	        cache: false,
	        timeout: 600000,
			processData : false,
			contentType : false,
	        success: function (response) {
	        	mess = $(response).find("input[id='msg-add']").val();
	        	showDialogConfirm(mess);
        		$('#modalAddBranch').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		$("#refresh-section").replaceWith(response);
		        	drawTable(branchDataTable);
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
	action = "branch/edit";
	var branch= $('form[name=branchFrmEdit]').serializeToJSON();
	
	if(branch.doeStr) {
		var timestamp = $.datepicker.parseDate('dd/mm/yy', branch.doeStr);
		branch.doe = new Date(timestamp);
	} else {
		branch.doe = new Date();
	}
	
	var paramBean = {};
	paramBean.data = branch;
	paramBean.currentPage = currentPage;
	
	var searchAdvanceParam = $('form[name=branchSearchFrm]').serializeToJSON();
	var advanceParam = {};
	advanceParam.searchAdvanceParam = searchAdvanceParam;
	paramBean.advanceParam = advanceParam;
	
	var branchForm= $('#branchFormEdit')[0];
	var data = new FormData(branchForm);
	data.append('doe', branch.doe);

	
	if(validateBranchFormEdit(branch)){
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
        		$('#editBranchModal').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		$("#refresh-section").replaceWith(response);
		        	drawTable(branchDataTable);
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

function validateBranchFormEdit(branch) {
	objErrors = [];
	result = true;
	objError = {}
	if(!branch.branchName) {
		result = false;
		objError = getObjectError(false, 'branchnameEdit', 'Tên chi nhánh là bắt buộc');
	} else {
		objError = getObjectError(true, 'branchnameEdit', '');
	}
	objErrors.push(objError);
	
	if(!branch.address) {
		result = false;
		objError = getObjectError(false, 'addressEdit', 'Địa chỉ chi nhánh là bắt buộc');
	} else {
		objError = getObjectError(true, 'addressEdit', '');
	}
	objErrors.push(objError);
	
	if(!branch.phone) {
		result = false;
		objError = getObjectError(false, 'phoneEdit', 'SĐT chi nhánh là bắt buộc');
	} else {
		if(!isPhoneNo(branch.phone)) {
			result = false;
			objError = getObjectError(false, 'phoneEdit', 'SĐT chi nhánh không đúng!');
		} else {
			objError = getObjectError(true, 'phoneEdit', '');
		}
	}
	objErrors.push(objError);
	showErr(objErrors);
	return result;
}

function validateBranchFormAdd(branch) {
	objErrors = [];
	result = true;
	objError = {}
	
	if(!branch.branchName) {
		result = false;
		objError = getObjectError(false, 'branchnameAdd', 'Tên chi nhánh là bắt buộc');
	} else {
		objError = getObjectError(true, 'branchnameAdd', '');
	}
	objErrors.push(objError);
	
	if(!branch.address) {
		result = false;
		objError = getObjectError(false, 'addressAdd', 'Địa chỉ chi nhánh là bắt buộc');
	} else {
		objError = getObjectError(true, 'addressAdd', '');
	}
	objErrors.push(objError);
	
	if(!branch.phone) {
		result = false;
		objError = getObjectError(false, 'phoneAdd', 'SĐT chi nhánh là bắt buộc');
	} else {
		if(!isPhoneNo(branch.phone)) {
			result = false;
			objError = getObjectError(false, 'phoneAdd', 'SĐT chi nhánh không đúng!');
		} else {
			objError = getObjectError(true, 'phoneAdd', '');
		}
	}
	objErrors.push(objError);
	showErr(objErrors);
	return result;
}

function searchAdvance() {
	action = "branch/searchAdvance";
	var branch= $('form[name=branchSearchFrm]').serializeToJSON();
	var paramBean = {};
	paramBean.data = branch;
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

function drawTableBranchService(idTable) {
		var table = $('#' + idTable).DataTable( {
        orderCellsTop: false,
        fixedHeader: true,
        stateSave: true,
        responsive: true,
        autoWidth: false,
        scrollX: false,
        language: {
		    "lengthMenu": "Hiển thị _MENU_ bản ghi",
		    "info": "Hiển thị từ _START_ đến _END_ trên tổng số _TOTAL_ bản ghi",
		    "search": "Tìm nhanh",
		    "paginate": {
		        "first":      "Đầu",
		        "last":       "Cuối",
		        "next":       "Sau",
		        "previous":   "Trước"
		    },
		  },
        columnDefs: [{
        	 "targets"  : [0,2],
             "orderable": false,

         }],
    } ).columns.adjust();
    if(idTable == 'branchServiceAddDataTbl') {
    	bsTable = table;
    }
    table.draw();
}

function showAddServicePopup() {
	$('.txt-popup-add-cus').val('');
	$(".slt-popup-add-cus").val("0");
	$("input, select").removeClass('is-invalid');
	$('#modalAddBranchService').modal({
           backdrop: 'static',
           keyboard: false
    });
	var branchName = $("#branchnameDtl").val();
	action = "branch/showListService?branchCode=" +branchCodeHidden + "&mode=edit";
	$.ajax({
        type: "GET",
        contentType: "application/html",
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	$("#refresh-service-section").replaceWith(response);
        	$("#branchNameHeader").text("Chi nhánh " + branchName);
        	$('.range-price').each(function(index) {
        		var idx = index + 1;
        		var priceVal = $('#range_price_hidden_'+idx).val();
        		if(!parseFloat(priceVal)) {
        			priceVal = 100000;
        		}
        		$('#range_price_' + idx).ionRangeSlider({
			      min     : 50000,
			      max     : 5000000,
			      type    : 'single',
			      step    : 1000,
			      postfix : ' VND',
			      prettify: false,
			      hasGrid : true,
			      from: priceVal
			    });
        	});
		    drawTableBranchService('branchServiceAddDataTbl');
        },
        error: function (e) {
            alert(e);
        }
    });
}

function addServiceAction() {
	var lstParam =[];
	bsTable.$('tr').each(function(index,rowhtml){
	  var branchService = {};
      var checked= $('input[type="checkbox"]:checked',rowhtml).length;
      var serviceId = $(rowhtml.cells[0]).find(".service-id").val();
      var serviceCode = $(rowhtml.cells[0]).find(".service-code-hidden").val();
      var price =  $(rowhtml.cells[2]).find(".irs-single").text().replace('VND','').replace(/\s/g, '');
      if(!parseFloat(price)) {
    	  price = $(rowhtml.cells[0]).find(".service-price-hidden").val();
      }
      branchService.serviceCode = serviceCode;
	  priceFloat = parseFloat(price);
	  branchService.price = priceFloat;
	  branchService.branchCode = branchCodeHidden;
      if (checked==1){
    	  if(serviceId && serviceId.trim()) {
    		  branchService.id = parseInt(serviceId);
    	  }
    	  lstParam.push(branchService);
      } else {
    	  if(serviceId && serviceId.trim()) {
    		  branchService.id = parseInt(serviceId);
    		  branchService.isDeleted = 1;
    		  lstParam.push(branchService);
    	  }
      }
    });
	if(lstParam && lstParam.length) {
		var branch = {};
		branch.lstServiceBranch = lstParam;
		callAjaxToSave(branch);
	}
}

function callAjaxToSave(param) {
	action = "branch/saveSeviceBranch";
	var paramBean = {};
	paramBean.data = param;
	$.ajax({
        type: "POST",
        contentType : 'application/json',
        url: action,
        dataType: 'html',
        data : JSON.stringify(paramBean),
        cache: false,
        timeout: 600000,
        success: function (response) {
        	mess = $(response).find("input[id='msg-add-branch-service']").val();
        	showDialogConfirm(mess);
    		$('#modalAddBranchService').modal('hide');
        	if(mess == 'SUCCESS') {
        		$('#detailBranch').replaceWith(response);
        		$('#myModal').modal({
		           backdrop: 'static',
		           keyboard: false
			    });
	        	branchServiceDataTable = 'branchServiceDataTbl';
	        	drawTableBranchService(branchServiceDataTable);
        	}
        },
        error: function (e) {
            console.log(e);
            alert(e);
        }
    });
}
function getDistrict(e) {
	var provinceId = $(e)[0].value;
	var idFragment = "pages/catalog/branchAddNew";
	var fragment = "reload-district";
	var eId = $(e).attr('id');
	if(eId.includes("Edit")) {
		fragment = "reload-district-edit";
		idFragment = "pages/catalog/branchEdit";
	}
	var action = contextRoot + "/api/getDistrict?provinceId=" + provinceId +"&fragmentResult=" + idFragment.trim() +"&fragment=" + fragment;
	getDistrictOrWard(action, fragment);
	$("#wardAdd").val(0);
	$("#wardEdit").val(0);
}

function getWard(e) {
	var districtId = $(e)[0].value;
	var idFragment = "pages/catalog/branchAddNew";
	var fragment = "reload-ward";
	var eId = $(e).attr('id');
	if(eId.includes("Edit")) {
		fragment = "reload-ward-edit";
		var idFragment = "pages/catalog/branchEdit";
	}
	var action = contextRoot + "/api/getWard?districtId=" + districtId +"&fragmentResult=" + idFragment.trim() +"&fragment=" + fragment;
	getDistrictOrWard(action, fragment);
}