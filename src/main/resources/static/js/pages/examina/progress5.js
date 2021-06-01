var specDataTable;
var iLoad = 0;
$(document).ready(function() {
	specDataTable = 'specDataTbl';
});



function expandData(element){
	var arrIdDtl = $('[id^=tbl-subDtl]');
	if(iLoad ==0) {
		arrIdDtl.each(function(item){
			var id = $(this).attr('id');
			drawTableNotSearch(id, false);
			console.log(id);
		});
	}
	iLoad ++;
	$(element).addClass("collapse-btn-data");
	var collapseBtn = $(element).next();
	$(collapseBtn).removeClass('collapse-btn-data');
	var executionDate = $(element).parent().find("label").text();
	var examCode = $('#examCode').text();
	var elementId = $(element).attr('id');
	var idTable = 'tbl-subDtl' + elementId.slice(elementId.length - 1);
	getDataDetail(examCode, executionDate, idTable);
}

function collapseData(element){
	$(element).addClass("collapse-btn-data");
	var expandBtn = $(element).prev();
	$(expandBtn).removeClass('collapse-btn-data');
}


function getDataDetail(examCode, executionDate, idTable) {
	var indexTbl = idTable.slice(idTable.length - 1)
	var action = "summary/showDataDetail?examCode=" + examCode + "&executionDate=" +  executionDate;
	$.ajax({
        type: "GET",
        contentType : 'application/json',
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
			$($('.tbodyDataClass')[indexTbl-1]).replaceWith(response);
        },
        error: function (e) {
            console.log(e);
            mess = "SYS_ERR";
            showDialogConfirm(mess);
        }
    });
}
