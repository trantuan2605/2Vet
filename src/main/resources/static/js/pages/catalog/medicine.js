var branchCodeHidden;
var currentPage;
var rowCountBefore;
$(document).ready(function() {
	drawTable();
});

function bindClick() {
	//$(".text_data").on("click", function() {
	$('#medicineDataTbl').on("click", "tbody tr", function() {
		var rowData = $(this).children("td").map(function() {
			return $(this).text();
		}).get();
		var branchCode = rowData[1];
		branchCodeHidden = branchCode;
		action = "medicine/showDetail?branchCode=" + branchCode;
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
		        	$('#detailMedicine').replaceWith(response);
		        },
		        error: function (e) {
		            alert(e);
		        }
		    });
	});
}

function showEditPopup() {
	if(!currentPage) {
		currentPage = 1;
	}
	$('#editMedicineModal').modal({
       backdrop: 'static',
       keyboard: false
    });
	action = "medicine/showDetail?branchCode=" +branchCodeHidden + "&mode=edit";
	$.ajax({
        type: "GET",
        contentType: "application/html",
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	$('#detailMedicineEdit').replaceWith(response);
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
	$('#modalAddMedicine').modal({
           backdrop: 'static',
           keyboard: false
    });
}

function addAction() {
	action = "medicine/add";
	var paramBean = {};
	var branch= $('form[name=medicineFrm]').serializeToJSON();
	paramBean.data = branch;
	paramBean.currentPage = 1;
	
	if(validateMedicineFormAdd(branch)){
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
        		$('#modalAddMedicine').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		$("#refresh-section").replaceWith(response);
		        	drawTable();
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
	action = "medicine/edit";
	var branch= $('form[name=medicineFrmEdit]').serializeToJSON();
	
	var paramBean = {};
	paramBean.data = branch;
	paramBean.currentPage = currentPage;
	
	//var searchAdvanceParam = $('form[name=branchSearchFrm]').serializeToJSON();
	//var advanceParam = {};
	//advanceParam.searchAdvanceParam = searchAdvanceParam;
	//paramBean.advanceParam = advanceParam;
	
	if(validateBranchFormEdit(branch)){
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
        		$('#editMedicineModal').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		$("#refresh-section").replaceWith(response);
		        	drawTable();
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

function validateMedicineFormAdd(branch) {
	objErrors = [];
	result = true;
	objError = {}
	
	if(!branch.branchCode) {
		result = false;
		objError = getObjectError(false, 'branchcodeAdd', 'Mã chi nhánh là bắt buộc');
	} else {
		objError = getObjectError(true, 'branchcodeAdd', '');
	}
	objErrors.push(objError);
	
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

function drawTable() {
	$('#medicineDataTbl thead tr').clone(true).appendTo( '#medicineDataTbl thead' );
    $('#medicineDataTbl thead tr:eq(1) th').each( function (i) {
        var title = $(this).text();
        if(i > 0) {
       		 $(this).html( '<input type="text" placeholder="Search '+title+'" />' );
   		 }
 
        $( 'input', this ).on( 'keyup change', function () {
            if ( table.column(i).search() !== this.value ) {
                table
                    .column(i)
                    .search( this.value )
                    .draw(false);
            }
        } );
    } );
	
	var table = $('#medicineDataTbl').DataTable( {
        orderCellsTop: true,
        fixedHeader: true,
        stateSave: true,
        responsive: true,
        autoWidth: false,
    } );
    
    // Restore state
    var state = table.state.loaded();
    if ( state ) {
      $('#medicineDataTbl thead tr:eq(1) th').each( function ( i ) {
        var colSearch = state.columns[i].search;
        
        if ( colSearch.search ) {
          $($('input',$('.filters th')[i])[i+1]).val( colSearch.search );
        }
      } );
      
      table.draw(false);
    }
    bindClick();
}