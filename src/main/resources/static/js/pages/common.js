function firstPageProcess(action, idTable) {
		$(".page-item").removeClass("active");
		$(".page-item:nth-child(2)").addClass("active");
		//action = "customer/search?page=1";
		callAjax(idTable);
	}
	
	function lastPageProcess(action, idTable) {
		$(".page-item").removeClass("active");
		$(".page-item:nth-last-child(2)").addClass("active");
		//action = "customer/search?page=" + pageNum;
		callAjax(idTable);
	}
	
	function callAjax(idTable) {
		$.ajax({
		        type: "GET",
		        contentType: "application/html",
		        url: action,
		        dataType: 'html',
		        cache: false,
		        timeout: 600000,
		        success: function (response) {
		        	$('#'+ idTable).replaceWith(response);
		        	bindClick();
		        },
		        error: function (e) {
		            alert(e);
		        }
		    });
	}
	
	function showDialogConfirm(message) {
		var Toast = Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: 3000
    });
	//Toast.fire({
	  //  icon: 'success',
	    //title: 'Cập nhật thành công!'
	  //})
	  if(message == 'SUCCESS') {
	  	toastr.success('Cập nhật thành công.')
	  }
	  else if(message == 'ERROR') {
	  	toastr.error('Cập nhật thất bại.')
	  }
	  else if(message == 'SYS_ERR') {
	  	toastr.error('Đã xảy ra lỗi, xin vui lòng thử lại!')
	  }

}

// show/hide error message when validate form
function showErr(valid, idInput, errMessage){
	//valid is false
	if(!valid) {
		error = '<span id="' + idInput + '-error" class="error invalid-feedback-customize">' + errMessage + '</span>';
		$("#"+idInput).parent().append(error);
		$("#"+idInput).addClass("is-invalid");
	} else {
		$("#"+ idInput +"-error").remove();
		$("#"+ idInput).removeClass("is-invalid");
	}
}

function showErr(objErrors) {
	$.each(objErrors, function(i, obj) {
		if(!obj.valid) {
			$("#"+ obj.idInput +"-error").remove();
			if($("#"+obj.idInput).parent().children('span').length == 0) {
				error = '<span id="' + obj.idInput + '-error" class="error invalid-feedback-customize">' + obj.errMessage + '</span>';
				$("#"+obj.idInput).parent().append(error);
				$("#"+obj.idInput).addClass("is-invalid");
			}
		} else {
			$("#"+ obj.idInput +"-error").remove();
			$("#"+ obj.idInput).removeClass("is-invalid");
		}
	});
}

function showErrTable(objErrors) {
	$.each(objErrors, function(i, obj) {
		if(!obj.valid) {
			if($(obj.row).parent().children('span').length == 0) {
				error = '<span id="' + obj.idInput + '-error" class="error invalid-feedback-customize">' + obj.errMessage + '</span>';
				$(obj.row).parent().append(error);
				$(obj.row).addClass("is-invalid");
			}
		} else {
			$("#"+ obj.idInput +"-error").remove();
			$(obj.row).removeClass("is-invalid");
		}
	});
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


function isEmail(email) {
  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
  return regex.test(email);
}

function isPhoneNo(phone) {
  var regex = /^((\+[1-9]{1,4}[ \-]*)|(\([0-9]{2,3}\)[ \-]*)|([0-9]{2,4})[ \-]*)*?[0-9]{3,4}?[ \-]*[0-9]{3,4}?$/;
  return regex.test(phone);
}

function drawTable(idTable) {
	$('#' + idTable +  ' thead tr').clone(true).appendTo( '#' + idTable + ' thead' );
    $('#' + idTable + ' thead tr:eq(1) th').each( function (i) {
        var title = $(this).text();
        if(i > 0) {
       		 //$(this).html( '<input type="text" class="filter-on-col filter-col-' + i +'" placeholder="Search '+title+'" />' );
       		 $(this).html( '<input type="text" class="filter-on-col filter-col-' + i +'" placeholder="Tìm kiếm" />' );
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
	
	var table = $('#' + idTable).DataTable( {
        orderCellsTop: true,
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
		  }
    } ).columns.adjust();
    
    // Restore state
    var state = table.state.loaded();
    if ( state ) {
      $('#' + idTable + ' thead tr:eq(1) th').each( function ( i ) {
        var colSearch = state.columns[i].search;
        
        if ( colSearch.search && i > 0) {
          $($('input.filter-on-col',$('.filters th .filter-on-col')[i])[i-1]).val( colSearch.search );
        }
      } );
      
      table.draw(false);
    }
    bindClick();
}

function drawTableNotAllowClick(idTable, allowBindClick) {
	$('#' + idTable +  ' thead tr').clone(true).appendTo( '#' + idTable + ' thead' );
    $('#' + idTable + ' thead tr:eq(1) th').each( function (i) {
        var title = $(this).text();
        if(i > 0) {
       		 //$(this).html( '<input type="text" class="filter-on-col filter-col-' + i +'" placeholder="Search '+title+'" />' );
       		 $(this).html( '<input type="text" class="filter-on-col filter-col-' + i +'" placeholder="Tìm kiếm" />' );
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
	
	var table = $('#' + idTable).DataTable( {
        orderCellsTop: true,
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
		  }
    } ).columns.adjust();
    
    // Restore state
    var state = table.state.loaded();
    if ( state ) {
      $('#' + idTable + ' thead tr:eq(1) th').each( function ( i ) {
        var colSearch = state.columns[i].search;
        
        if ( colSearch.search && i > 0) {
          $($('input.filter-on-col',$('.filters th .filter-on-col')[i])[i-1]).val( colSearch.search );
        }
      } );
      
      table.draw(false);
    }
    
    if(allowBindClick) {
    	bindClick();
    }
}

function drawTableNotSearch(idTable, allowBindClick) {
	var table = $('#' + idTable).DataTable( {
        orderCellsTop: true,
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
		  }
    } ).columns.adjust();
    
    // Restore state
    var state = table.state.loaded();
    if ( state ) {
      table.draw(false);
    }
    
    if(allowBindClick) {
    	bindClick();
    }
}

function getDistrictOrWard(action, fragment) {
	$.ajax({
        type: "GET",
        contentType : 'application/json',
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
		$("#"+fragment).replaceWith(response);
        },
        error: function (e) {
            console.log(e);
            mess = "SYS_ERR";
            showDialogConfirm(mess);
        }
    });
}

function readURLAdd(input) {
	  if (input.files && input.files[0]) {
	    var reader = new FileReader();
	    
	    reader.onload = function(e) {
	      $('#blah').attr('src', e.target.result);
	    }
	    
	    reader.readAsDataURL(input.files[0]); // convert to base64 string
	  }
	}

function readURLEdit(input) {
	  if (input.files && input.files[0]) {
	    var reader = new FileReader();
	    
	    reader.onload = function(e) {
	      $('#imagePreviewEdit').attr('src', e.target.result);
	    }
	    
	    reader.readAsDataURL(input.files[0]); // convert to base64 string
	  }
	}