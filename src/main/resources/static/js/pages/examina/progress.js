var activePanelNum;
var selectedMap = new Map();
$(document).ready(function() {
	$('.multisteps-form__panel').removeClass('js-active');
	$('.multisteps-form__progress-btn').removeClass('js-active');
	//show active panel
	activePanelNum = parseInt($('#activePanelNum').val());
	  DOMstrings.stepFormPanels.forEach((elem, index) => {
	    if (index === activePanelNum) {

	      elem.classList.add('js-active');
	    }
	  });
	  
	  //set picked items to active
	  DOMstrings.stepsBtns.forEach((elem, index) => {

	    if (index <= activePanelNum) {
	      elem.classList.add('js-active');
	    }

	  });
	  $('.reserver-date').each(function(index) {
	    	$(this).datetimepicker({
	        	format: 'DD/MM/YYYY',
	        });
	    });
	  var selectedMapStr = $('#mapSelectedHidden').val();
//	  var seclectedMap = JSON.parse(selectedMapStr);
//	  console.log(seclectedMap);
	  var itemCodesDB = selectedMapStr.substring(1).slice(0,-1).replaceAll("=", ":");
	  console.log(itemCodesDB);
	  var arrSlt = itemCodesDB.split('],');
	  $.each( arrSlt, function( index, value ) {
		  var subArr = value.split(':[');
		  selectedMap.set(subArr[0].trim(), subArr[1].replace("]","").replace(/\s+/g, '').split(","));
		});
	  
//	  setActiveStep(activePanelNum);
});
