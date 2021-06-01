var listData = [];
var yourArray=[];
var rows_selected = [];
$(document).ready(function() {
	listUser();
	   $('#checkAll').on('click', function(){
		   var table = $('#userTbl').DataTable();
		      // Check/uncheck all checkboxes in the table
		      var rows = table.rows({ 'search': 'applied' }).nodes();
		      $('input[type="checkbox"]', rows).prop('checked', this.checked);
		      
		      //
		      getDataWhenCheckAll();
		   });
	   $('#btnDelUser').on('click', function(){
		   	var table = $('#userTbl').DataTable();
			  var tblData = table.rows('.selected').data();
			  var listParams =[];
			  for (var i=0; i < tblData.length ;i++){
				  var param = {};
		           param.rolename = tblData[i].rolename;
		           param.username = tblData[i].username;
		           param.status = tblData[i].status;
		           param.userId = tblData[i].userId;
		           listParams.push(param);
		        }
		   delUser(listParams);
	   });
	   
	   $('#btnAddUser').on('click', function(){
		   $('#modal-content').modal({
		        show: true,
		        backdrop: 'static',
		        keyboard: false
		    });
	   })
	   
	   $('#showhidepwd').click(function() {
		   showHidePwd('password', 'showhidepwd');
	   });
	   
	   $('#showhidepwdconfirm').click(function() {
		   showHidePwd('passwordConfirm', 'showhidepwdconfirm');
	   });
	   
	   $(".close").on('click', function() {
	        $('#modal-content').modal('hide');
	        $('.txt-input').val('');
	        $("input:checkbox[name=checkAll]").closest( "tr" ).removeClass('selected');
	    });
	   
	   $('#btnUpdate').on('click', function(){
		   saveUser();
		   $("input:checkbox[name=checkAll]").closest( "tr" ).removeClass('selected');
		   $('#modal-content').modal('hide');
	       $('.txt-input').val('');
	   })
} );

function listUser() {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "list",
        data: JSON.stringify(""),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (response) {
            for (var i = 0; i < response.length; i++) {
            	var retval ={};
				retval.username = response[i].username
				retval.rolename = response[i].rolename
				retval.status = response[i].status
				retval.userId = response[i].userId
				listData.push(retval);
			}
            $('#userTbl').DataTable( {
            	"data": listData,
                "columns": [
                	{
                		'render': function (data, type, row, meta){
                        return '<input type="checkbox" name="checkAll" onchange="getDataWhenCheckAll(this);" id="id['+meta.row+']" value="' 
                           + $('<div/>').text(row.username).html() + '">';
                    	}, 
                    	className: "checkbox_item ",
                    	'orderable': false,
                    	targets: 0
                    },
                    { render: function(data, type, row, meta){
                    	return '<a href="#" id="id_'+row.username+'" onclick="editUser(this);">' + row.username +'</a>'
                    }
                    	,className: "text-left" },
                    { data: "rolename", className: "text-center" },
                    { data: "status", className: "text-center" }
                ],
                "order": [1, "asc"],
            } );
        },
        error: function (e) {
            alert(e);
        }
    });
}
function delUser(listParam){
	params = {};
	params.appUser = listParam;
	$.ajax({
        type: "POST",
        contentType: "application/json",
        url: "user/delete",
        data: JSON.stringify(params),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	alert("successfull");
        },
        error: function (e) {
            alert(e);
        }
    });
}

function getDataWhenCheckAll(id ){
	if (!$(id).is(":checked")) {
		if ($.inArray($(id).val(), yourArray) != -1) {
	    	yourArray.splice($.inArray($(id).val(), yourArray), 1);
	    	$(id).closest( "tr" ).removeClass('selected');
	    	$('#checkAll').prop( "checked", false );
		}
	}
	$("input:checkbox[name=checkAll]:checked").each(function(){
	    if ($.inArray($(this).val(), yourArray) == -1) {
	    	yourArray.push($(this).val());
	    	$(this).closest( "tr" ).addClass('selected');
		}
	});
	if ($("input:checkbox[name=checkAll]:checked").length == 0) {
		yourArray =[];
		$("input:checkbox[name=checkAll]").closest( "tr" ).removeClass('selected');
	}
}

function showHidePwd(id1, id2){
	if ('password' == $('#'+id1).attr('type')) {
		$('#'+id1).prop('type', 'text');
		$('#'+id2).attr("src","images/hide-password.png");
	} else {
		$('#'+id1).prop('type', 'password');
		$('#'+id2).attr("src","images/show-password.png");
	}
}

function editUser(id){
	$(id).closest( "tr" ).addClass('selected');
	var table = $('#userTbl').DataTable();
	  var tblData = table.rows('.selected').data();
		  var param = {};
			param.rolename = tblData[0].rolename;
			param.userName = tblData[0].username;
			param.status = tblData[0].status;
			param.userId = tblData[0].userId;

$.ajax({
    type: "POST",
    contentType: "application/json",
    url: "user/edit",
    data: JSON.stringify(param),
    dataType: 'json',
    cache: false,
    timeout: 600000,
    success: function (response) {
    	$('#username').val(response.result.username);
    	$('#userIdHidden').val(response.result.userId);
    	var admin= false;
    	var member = false;
    	for (var i = 0; i < response.result.rolename.length; i++) {
    		if (response.result.rolename[i] =='ROLE_ADMIN') {
    			admin = true;
    			
    		} else if (response.result.rolename[i] =='ROLE_MEMBER') {
    			member = true;
    		}
		}
    	if (admin && member) {
    		$('#full').attr('checked', true);
		} else if(admin) {
			$('#admin').attr('checked', true);
		} else if(member) {
			$('#member').attr('checked', true);
		}
    	$('#modal-content').modal({
	        show: true,
	        backdrop: 'static',
	        keyboard: false
	    });
    },
    error: function (e) {
        alert(e);
    }
});  
}

function saveUser(){
	var param = {}
	var userId = $('#userIdHidden').val();
	var username = $('#username').val();
	var password = $('#password').val();
	var role;
	if ($('#full').prop('checked')) {
		role = 0;
	} else if($('#admin').prop('checked')){
		role = 1;
	} else if($('#member').prop('checked')) {
		role = 2;
	}
	param.userId = userId;
	param.userName = username;
	param.password = password;
	param.role = role;
	$.ajax({
		type: "POST",
    contentType: "application/json",
    url: "user/save",
    data: JSON.stringify(param),
    dataType: 'json',
    cache: false,
    timeout: 600000,
    success: function (response) {
    	alert("success!");
    },
    error: function (e) {
        alert(e);
    }
	});
}

