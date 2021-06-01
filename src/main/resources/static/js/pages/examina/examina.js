var specCodeHidden;
var currentPage;
var specDataTable;
$(document).ready(function() {
	specDataTable = 'specDataTbl';
	drawTable(specDataTable);
	//bindClick();
//	$("#imgInp").change(function() {
//		  readURLAdd(this);
//		});
//	validateAdd();
	
});

function bindClick() {
	$('#specDataTbl').on("click", "tbody tr", function() {
//	$("#specDataTbl").on("click", function() {
		var rowData = $(this).children("td").map(function() {
			return $(this).text();
		}).get();
		var processNum = $(this).find('input.processNum[type=hidden]').val();
		var examId = $(this).find('input.examId[type=hidden]').val();
		var examCode = rowData[1];
		var petCode = rowData[2];
		var timeIn = $(this).find('input.timeIn[type=hidden]').val();
		//DOM elements
		redirectScreen(parseInt(processNum), examCode, examId, petCode, timeIn);
		
	});
}

/**
 * mode 0: new , 1 list
 */
function redirectScreen(processNum, examCode, examId, petCode, timeIn) {
	var contextRoot = $('#contextRoot').val();
	var url = contextRoot.concat('/examination/progress?activePanelNum=' + processNum);
	if(processNum != 0) {
		url =url.concat('&examCode=' + examCode + '&examId=' + examId + '&petCode=' + petCode + '&timeIn=' + timeIn);
	}
	window.location.href = url;
}

