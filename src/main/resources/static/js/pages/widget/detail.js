//$( document ).ready(bindClick);
var group3ndCodeHidden;
var currentPage;
var widgetDetailDataTable;
$(document).ready(function() {
	//dropzone
	/*Dropzone.autoDiscover = false;
	var previewNode = document.querySelector("#template");
	  previewNode.id = "";
	  var previewTemplate = previewNode.parentNode.innerHTML;
	  previewNode.parentNode.removeChild(previewNode);
	  var myDropzone = new Dropzone(document.body, { // Make the whole body a dropzone
		    url: "/target-url", // Set the url
		    thumbnailWidth: 80,
		    thumbnailHeight: 80,
		    parallelUploads: 20,
		    previewTemplate: previewTemplate,
		    autoQueue: false, // Make sure the files aren't queued until manually added
		    previewsContainer: "#previews", // Define the container to display the previews
		    clickable: ".fileinput-button" // Define the element that should be used as click trigger to select files.
		  });
	  
	  myDropzone.on("addedfile", function(file) {
		    // Hookup the start button
	    file.previewElement.querySelector(".start").onclick = function() { myDropzone.enqueueFile(file); };
	  });*/
	  
	  // Setup the buttons for all transfers
	  // The "add files" button doesn't need to be setup because the config
	  // `clickable` has already been specified.
//	  document.querySelector("#actions .start").onclick = function() {
//	    myDropzone.enqueueFiles(myDropzone.getFilesWithStatus(Dropzone.ADDED));
//	  };
//	  document.querySelector("#actions .cancel").onclick = function() {
//	    myDropzone.removeAllFiles(true);
//	  };
	  
	  $("#imgInp").change(function() {
		  readURLAdd(this);
		});
	  
	widgetDetailDataTable = 'widgetDetailDataTbl';
	drawTable(widgetDetailDataTable);
});

function bindClick() {
//	$(".text_data").on("click", function() {
	$('#widgetDetailDataTbl').on("click", "tbody tr", function() {
		var rowData = $(this).children("td").map(function() {
			return $(this).text();
		}).get();
		var group3ndCode = rowData[1];
		group3ndCodeHidden = group3ndCode;
		action = "detail/showDetail?group3ndCode=" + group3ndCode;
		$('#detailItemModal').modal({
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
		        	$('#itemInfo').replaceWith(response);
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
	$('#editGroup3ndModal').modal({
       backdrop: 'static',
       keyboard: false
    });
	action = "detail/showDetail?group3ndCode=" + group3ndCodeHidden + "&mode=edit";
	$.ajax({
        type: "GET",
        contentType: "application/html",
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	$('#detailGroup3ndEdit').replaceWith(response);
        	 $("#imageEdit").change(function() {
	       		  readURLEdit(this);
	       	  });
        },
        error: function (e) {
            alert(e);
        }
    });
}

function showAddPopup() {
	var imageDefault = $('#defaultImg').val();
	$('#blah').attr('src', imageDefault);
	$( "#imgInp" ).val("")
	$('.txt-popup-add-cus').val('');
	$(".slt-popup-add-cus").val("0");
	$("input, select").removeClass('is-invalid');
	$("input:radio").attr("checked", false);
	$('#modalAddGroup3nd').modal({
           backdrop: 'static',
           keyboard: false
    });
}

function addAction() {
	action = "detail/add";
	var paramBean = {};
	var group3ndValidate= $('form[name=group3ndAddFrm]').serializeToJSON();
//	group3nd.fileImage = $('#imgInp')[0].files[0];
	var group3nd= $('#group3ndAddForm')[0];
	var data = new FormData(group3nd);
	paramBean.data = group3nd;
	if(validateGroup3ndFormAdd(group3ndValidate)){
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
        		$('#modalAddGroup3nd').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		$("#refresh-section").replaceWith(response);
	        		drawTable(widgetDetailDataTable);
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
	action = "detail/edit";
	var group3ndValid= $('form[name=group3ndFrmEdit]').serializeToJSON();
	var group3nd= $('#group3ndFormEdit')[0];
	var data = new FormData(group3nd);
	var paramBean = {};
	paramBean.data = group3nd;
	
	if(validateGroup3ndFormEdit(group3ndValid)){
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
        		$('#editGroup3ndModal').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		$("#refresh-section").replaceWith(response);
	        		drawTable(widgetDetailDataTable);
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

function validateGroup3ndFormAdd(group3nd) {
	objErrors = [];
	result = true;
	objError = {}
	
	if(!group3nd.group3ndName) {
		result = false;
		objError = getObjectError(false, 'group3ndNameAdd', 'Thông tin tên là bắt buộc');
	} else {
		objError = getObjectError(true, 'group3ndNameAdd', '');
	}
	objErrors.push(objError);
	
	showErr(objErrors);
	return result;
}

function validateGroup3ndFormEdit(group3nd) {
	objErrors = [];
	result = true;
	objError = {}
	if(!group3nd.group3ndName) {
		result = false;
		objError = getObjectError(false, 'group3ndNameEdit', 'Thông tin tên là bắt buộc');
	} else {
		objError = getObjectError(true, 'group3ndNameEdit', '');
	}
	objErrors.push(objError);
	
	showErr(objErrors);
	return result;
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

