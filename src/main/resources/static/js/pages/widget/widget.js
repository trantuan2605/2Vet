var group1stCode;
$(document).ready(function() {
//	var countGroupItems = $('h5.group-item-title-name').length;
//	$('h5.group-item-title-name').each(function(index){
//		var i = index+1;
//		var elementToHide = 'div.service-element-'+i;
//		$(elementToHide).slice(0, 6).show();
//	});
	loadItems();
});
function loadMore(element) {
	var className = element.classList[2];
	var index = className.substring(className.length-1, className.length);
	var classNameToExecute = 'div.service-element-'+index+':hidden';
  $(classNameToExecute).slice(0, 6).slideDown();
  if ($(classNameToExecute).length == 0) {
      $("#load").fadeOut('slow');
  }
  $('html,body').animate({
      scrollTop: $(element).offset().top
  }, 1500);
}

function goDetail(element) {
	var className = element.classList[1];
	var group2ndCode = className.split('-')[1];
	var groupName = $('#title-screen').text();
	var contextRoot = $('#contextRoot').val();
	var url = contextRoot.concat('/widget/detail');
//	window.location.href = '/2VET/widget/detail?group2ndCode='+ group2ndCode + '&groupName=' + groupName;
	window.location.href = url + '?group2ndCode='+ group2ndCode + '&groupName=' + groupName;
}

function showAddPopup() {
	$('.txt-popup-add-cus').val('');
	$(".slt-popup-add-cus").val("0");
	$("input, select").removeClass('is-invalid');
	$("input:radio").attr("checked", false);
	$('#modalAddGroup1st').modal({
           backdrop: 'static',
           keyboard: false
    });
}

function showAdd2ndPopup(element) {
	var className = element.classList[2];
	group1stCode = className.split('-')[1];
	$('.txt-popup-add-cus').val('');
	$(".slt-popup-add-cus").val("0");
	$("input, select").removeClass('is-invalid');
	$("input:radio").attr("checked", false);
	$('#modalAddGroup2nd').modal({
		backdrop: 'static',
		keyboard: false
	});
	action = "widget/show2ndPopup?group1stCode=" + group1stCode;
	$.ajax({
        type: "GET",
        contentType: "application/html",
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	$('#group2ndAddDiv').replaceWith(response);
        },
        error: function (e) {
            alert(e);
        }
    });
}

function addAction() {
	action = "widget/add";
	var paramBean = {};
	var group1st= $('form[name=group1stAddFrm]').serializeToJSON();
	paramBean.data = group1st;
	if(validateGroup1stFormAdd(group1st)){
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
        		$('#modalAddGroup1st').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		$("#refresh-dashboard").replaceWith(response);
	        		loadItems();
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

function validateGroup1stFormAdd(group1st) {
	objErrors = [];
	result = true;
	objError = {}
	
	if(!group1st.group1stName) {
		result = false;
		objError = getObjectError(false, 'group1stNameAdd', 'Thông tin tên là bắt buộc');
	} else {
		objError = getObjectError(true, 'group1stNameAdd', '');
	}
	objErrors.push(objError);
	
	showErr(objErrors);
	return result;
}

function loadItems() {
	var countGroupItems = $('h5.group-item-title-name').length;
	$('h5.group-item-title-name').each(function(index){
		var i = index+1;
		var elementToHide = 'div.service-element-'+i;
		$(elementToHide).slice(0, 6).show();
	});
}

function addGroup2ndAction() {
	action = "widget/addGroup2nd";
	var paramBean = {};
	var group2nd= $('form[name=group2ndAddFrm]').serializeToJSON();
	paramBean.data = group2nd;
	if(validateGroup2ndFormAdd(group2nd)){
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
        		$('#modalAddGroup2nd').modal('hide');
	        	if(mess == 'SUCCESS') {
	        		$("#refresh-dashboard").replaceWith(response);
	        		loadItems();
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

function validateGroup2ndFormAdd(group2nd) {
	objErrors = [];
	result = true;
	objError = {}
	
	if(!group2nd.group2ndName) {
		result = false;
		objError = getObjectError(false, 'group2ndNameAdd', 'Thông tin tên là bắt buộc');
	} else {
		objError = getObjectError(true, 'group2ndNameAdd', '');
	}
	objErrors.push(objError);
	
	showErr(objErrors);
	return result;
}
