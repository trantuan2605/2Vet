var iClick = 0;
var calendar;
var end;
var start;
var yearCurrent;
var monthCurrent;
var viewTypeGlobal;
var stompClient = null;
var connectWS;
var searchByAssign = true;
var searchByWaiting = true;
var urlWs;
var optionsM = {
        weekday: "short",
        month: "long",
        day: "2-digit",
    };

var optionsD = {
        weekday: "short",
        month: "long",
        day: "2-digit",
        hour: "2-digit",
        minute: "2-digit"
    };

var optionsDW = {
        weekday: "short",
        month: "long",
        day: "2-digit",
    };

var optionsH = {
        hour: "2-digit",
        minute: "2-digit",
    };
$(document).ready(function() {
	connect();
	// handle when click expand side menu
//	$('.navbar-nav li.push-menu').click(function(e) {
//		iClick+=1;
//		if(iClick <= 1) {
//			$('#calendar-col-9').addClass('col-md-9-custom-calendar');
//			$('#calendar-col-3').addClass('col-md-3-custom-calendar');
//		} else {
//			iClick = 0;
//			$('#calendar-col-9').removeClass('col-md-9-custom-calendar');
//			$('#calendar-col-3').removeClass('col-md-3-custom-calendar');
//		}
//	})
	
//	ini_events($('#external-events div.external-event'))

    /* initialize the calendar
     -----------------------------------------------------------------*/
    //Date for the calendar events (dummy data)
    var date = new Date()
    var d    = date.getDate(),
        m    = date.getMonth(),
        y    = date.getFullYear()

    var Calendar = FullCalendar.Calendar;
    var Draggable = FullCalendar.Draggable;

//    var containerEl = document.getElementById('external-events');
    var checkbox = document.getElementById('drop-remove');
    var calendarEl = document.getElementById('calendar');

    // initialize the external events
    // -----------------------------------------------------------------

//    new Draggable(containerEl, {
//      itemSelector: '.external-event',
//      eventData: function(eventEl) {
//        return {
//          title: eventEl.innerText,
//          backgroundColor: window.getComputedStyle( eventEl ,null).getPropertyValue('background-color'),
//          borderColor: window.getComputedStyle( eventEl ,null).getPropertyValue('background-color'),
//          textColor: window.getComputedStyle( eventEl ,null).getPropertyValue('color'),
//        };
//      }
//    });

    calendar = new Calendar(calendarEl, {
      headerToolbar: {
        left  : 'prev,next today',
        center: 'title',
        right : 'dayGridMonth,timeGridWeek,timeGridDay'
      },
      locale: 'vi',
      themeSystem: 'bootstrap',
      allDaySlot: true,
//      allDayMaintainDuration: true,
      // C1: su dung event as a json feed
//      events: {
//    	    url: "booking/getEventForMonth",
//    	    method: 'GET',
//    	    extraParams: function (infor){
//    	  			if(!yearCurrent) {
//    	  				yearCurrent = y;
//    	  			}
//    	  			if(!monthCurrent) {
//    	  				monthCurrent = m+1;
//    	  			}
//    	  		return {
//	    	  		month: monthCurrent,
//	    	  		year: yearCurrent
//    	  			}
//    	    },
//    	    failure: function() {
//    	      alert('there was an error while fetching events!');
//    	    },
//    	    success: function(response) {
//    	    	alert("abc");
//    	    },
//    	    backgroundColor: '#f39c12', //yellow
//            borderColor    : '#f39c12' 
//    	  },
      //C2: su dung events as a function
      events: function( fetchInfo, successCallback, failureCallback) { 
    	  var endTime = fetchInfo.end.toLocaleDateString('en-US', {year: 'numeric', month: '2-digit', day: '2-digit'}) + ' ' + fetchInfo.end.toLocaleTimeString('en-US', {hour: '2-digit', minute: '2-digit', hour12: true});
    	  var startTime = fetchInfo.start.toLocaleDateString('en-US', {year: 'numeric', month: '2-digit', day: '2-digit'}) + ' ' + fetchInfo.start.toLocaleTimeString('en-US', {hour: '2-digit', minute: '2-digit', hour12: true});
//    	  if(connectWS == 'success') {
//    		sendMessage(startTime, endTime);
//    	  } else {
//    		  $.ajax({
//        	      url: 'booking/getEventForMonth',
//        	      dataType: 'json',
//        	      data: {
//        	        // our hypothetical feed requires UNIX timestamps
//        	        startTime: startTime,
//        	        endTime: endTime
//        	      },
//        	      success: function(res) {
//        	    	  var events = [];
//        	          res.forEach(function(item) {
//        	            events.push(item);
//        	          });
//        	    	  successCallback(events);
//        	      }
//        	    }); 
//    	  }
    	  
    	  $.ajax({
    	      url: 'booking/getEventForMonth',
    	      dataType: 'json',
    	      data: {
    	        // our hypothetical feed requires UNIX timestamps
    	        startTime: startTime,
    	        endTime: endTime,
    	        searchByAssign : searchByAssign,
    	        searchByWaiting : searchByWaiting
    	      },
    	      success: function(res) {
    	    	  var events = [];
    	          res.forEach(function(item) {
    	            events.push(item);
    	          });
    	    	  successCallback(events);
    	      }
    	    }); 
    	  
      },
      //Random default events
//      events: [
//        {
//          title          : 'All Day Event',
//          start          : new Date(y, m, 1),
//          backgroundColor: '#f56954', //red
//          borderColor    : '#f56954', //red
//          allDay         : true,
//          id	         : '123_test'
//        },
//        {
//          title          : 'Long Event',
//          start          : new Date(y, m, d - 5),
//          end            : new Date(y, m, d - 2),
//          backgroundColor: '#f39c12', //yellow
//          borderColor    : '#f39c12' //yellow
//        },
//        {
//          title          : 'Meeting',
//          start          : new Date(y, m, d, 10, 30),
//          allDay         : false,
//          backgroundColor: '#0073b7', //Blue
//          borderColor    : '#0073b7' //Blue
//        },
//        {
//          title          : 'Lunch',
//          start          : new Date(y, m, d, 12, 0),
//          end            : new Date(y, m, d, 14, 0),
//          allDay         : false,
//          backgroundColor: '#00c0ef', //Info (aqua)
//          borderColor    : '#00c0ef' //Info (aqua)
//        },
//        {
//          title          : 'Birthday Party',
//          start          : new Date(y, m, d + 1, 19, 0),
//          end            : new Date(y, m, d + 1, 22, 30),
//          allDay         : false,
//          backgroundColor: '#00a65a', //Success (green)
//          borderColor    : '#00a65a' //Success (green)
//        },
//        {
//          title          : 'Click for Google',
//          start          : new Date(y, m, 28),
//          end            : new Date(y, m, 29),
//          url            : 'https://www.google.com/',
//          backgroundColor: '#3c8dbc', //Primary (light-blue)
//          borderColor    : '#3c8dbc' //Primary (light-blue)
//        }
//      ],
      editable  : true,
      selectable: true,
      select: function(info) {
    	  const ONE_DAY = 1000 * 60 * 60 * 24;
          // Display the modal.
          // You could fill in the start and end fields based on the parameters
//         alert("From " + info.startStr + " to " + info.endStr);
    	  $("#btn-del").css("display","none");
    	  $("#btn-save").removeAttr("style");
    	  var options = optionsM;
//    	  $("#periodEvent").text(info.start.toLocaleDateString("vi", options) + " - " + info.end.toLocaleDateString("vi", options));
    	  start = info.start;
    	  end = info.end;
    	  const differenceMs = Math.abs(end - start);
    	  if(differenceMs > ONE_DAY) {
    		  return;
    	  }
    	  var viewTypeCalendar = info.view.type;
    	  viewTypeGlobal = viewTypeCalendar;
    	  $("input:text").val("");
    	  $(".slt-popup-add-cus").val("0");
//    	  $('#startTime').val(start);
//    	  $('#endTime').val(end);
    	  var endTimeStr = end.toLocaleDateString('en-US', {year: 'numeric', month: '2-digit', day: '2-digit'}) + ' ' + end.toLocaleTimeString('en-US', {hour: '2-digit', minute: '2-digit', hour12: true});
    	  var startTimeStr = start.toLocaleDateString('en-US', {year: 'numeric', month: '2-digit', day: '2-digit'}) + ' ' + start.toLocaleTimeString('en-US', {hour: '2-digit', minute: '2-digit', hour12: true});
    	  // call ajax to init data
    	  var action = "booking/getDataInit?startTime=" + startTimeStr + "&endTime=" + endTimeStr;
			$.ajax({
				type: "GET",
		        contentType : 'application/json',
		        url: action,
		        dataType: 'html',
		        cache: false,
		        timeout: 600000,
		        success: function (response) {
					$('#myModal').modal('show');
					$('#detailEvent').replaceWith(response);
					if(info.allDay) {
			    		  $('#allDayHidden').val(1);
			    	  } else {
			    		  $('#allDayHidden').val(0);
			    	  }
					$("#periodEvent").text(info.start.toLocaleDateString("vi", options) + " - " + info.end.toLocaleDateString("vi", options));
					$( '#editorExperience' ).ckeditor();
					$('#startTime').val(start);
					$('#endTime').val(end);
					if("dayGridMonth" == viewTypeCalendar) {
						$('#btn-addTime').removeClass('btn-hide');
					}
		        },
		        error: function (e) {
		            console.log(e);
		            mess = "SYS_ERR";
		            showDialogConfirm(mess);
		        }
		    });
      },
      droppable : true, // this allows things to be dropped onto the calendar !!!
      drop      : function(info) {
        // is the "remove after drop" checkbox checked?
        if (checkbox.checked) {
          // if so, remove the element from the "Draggable Events" list
          info.draggedEl.parentNode.removeChild(info.draggedEl);
        }
      },
//      dateClick: function() {
//	    alert('a day has been clicked!');
//	  },
      
      //hanlde when click on event
//      eventClick: function(info) {
//		    alert('Event: ' + info.event.title);
//		    alert('Coordinates: ' + info.jsEvent.pageX + ',' + info.jsEvent.pageY);
//		    alert('View: ' + info.view.type);
//
//		    // change the border color just for fun
//		    info.el.style.borderColor = 'red';
//		  },
      // do not allow drag when past
//      eventAllow: function(dropInfo, draggedEvent) {
//		  if (draggedEvent.id === '999') {
//		    return dropInfo.start < new Date(y, m, d); // a boolean
//		  }
//		  else {
//		    return true;
//		  }
//		},
      eventTimeFormat: {
    	  hour: 'numeric',
    	  minute: '2-digit',
    	  meridiem: 'short'
    	},
      // remove an event on calendar
      eventClick: function(eventRemove, element) {
    	  $("#btn-save").css("display","none");
    	  $("#btn-del").removeAttr("style");
    	  var endEvent = eventRemove.event.end;
    	  var startEvent = eventRemove.event.start;
    	  var startDate = startEvent.getDate();
    	  var options = optionsM;
    	  var periodTxt = "";
    	  if(!endEvent) {
    		  endEvent = new Date(new Date().setDate(startEvent.getDate() + 1));
    	  }
    	  if(endEvent) {
    		  var endDate = endEvent.getDate();
    		  if(startDate == endDate) {
    			  options = optionsDW
				  periodTxt = startEvent.toLocaleDateString("vi", options) + " . " + getTimeHour(startEvent) + " - " + getTimeHour(endEvent);
//    			  $("#periodEvent").text(startEvent.toLocaleDateString("vi", options) + " . " + getTimeHour(startEvent) + " - " + getTimeHour(endEvent));
    		  } else {
    			  periodTxt = startEvent.toLocaleDateString("vi", options) + " - " + endEvent.toLocaleDateString("vi", options);
    		  }
    	  }
//    	  else {
//    		  options = optionsD;
//    		  periodTxt = startEvent.toLocaleDateString("vi", options);
//    	  }
//			$('#myModal').modal('show');
			// call ajax to show detail
			var publicId = eventRemove.event._def.publicId;
			var endTimeStr = endEvent.toLocaleDateString('en-US', {year: 'numeric', month: '2-digit', day: '2-digit'}) + ' ' + endEvent.toLocaleTimeString('en-US', {hour: '2-digit', minute: '2-digit', hour12: true});
	    	var startTimeStr = startEvent.toLocaleDateString('en-US', {year: 'numeric', month: '2-digit', day: '2-digit'}) + ' ' + startEvent.toLocaleTimeString('en-US', {hour: '2-digit', minute: '2-digit', hour12: true});
			var action = "booking/getDetail?publicId=" + publicId + "&startTime=" + startTimeStr + "&endTime=" + endTimeStr;
			$.ajax({
				type: "GET",
		        contentType : 'application/json',
		        url: action,
		        dataType: 'html',
		        cache: false,
		        timeout: 600000,
		        success: function (response) {
					$('#myModal').modal('show');
					$('#detailEvent').replaceWith(response);
					$( '#editorExperience' ).ckeditor();
					$('#startTime').val(startEvent);
					$('#endTime').val(endEvent);
					$("#periodEvent").text(periodTxt);
		        },
		        error: function (e) {
		            console.log(e);
		            mess = "SYS_ERR";
		            showDialogConfirm(mess);
		        }
		    });
    },
      // event resize
      eventResize: function(event, delta, revertFunc) {
//        if (!confirm("is this okay?")) {
//          revertFunc();
//        }
    	$('#startTime').val(event.event.start);
		$('#endTime').val(event.event.end);
		$('#idHidden').val(event.event._def.publicId);
		var editType = 'DRAGDROP';
		var allDay = event.event._def.allDay;
    	updateAnEvent(editType, allDay);
    },
      //event drag drop
      eventDrop: function(info) {
//    	$('#modal-default').modal('show');
    	$('#startTime').val(info.event.start);
//		$('#endTime').val(info.event.end);
		if(info.event.end) {
			$('#endTime').val(info.event.end);
		} else {
			var endTimeNew = new Date();
			endTimeNew.setTime(info.event.start.getTime() + 60*60000);
    		$('#endTime').val(new Date(endTimeNew));
		}
		$('#idHidden').val(info.event._def.publicId);
		var allDay = info.event._def.allDay;
		var editType = 'DRAGDROP';
    	updateAnEvent(editType, allDay);
    },
      eventDidMount:function(info){
    	var viewType = info.view.type;
    	if(viewType == 'dayGridMonth') {
    		$(info.el).css("border-style", "solid");
            $(info.el).css("border-color", info.borderColor);
            $(info.el).css("border-radius", "4px");
            $(info.el).css("border-width", "1px");
        	$(info.el).find('.fc-event-title').parent().addClass('event-wrap-text');
        	$(info.el).find('.fc-event-time').addClass('descript-margin');
        	$(info.el).find('.fc-event-title').each(function () {
        		if($(info.el).find('.fc-event-title').hasClass('fc-sticky')) {
//        			var descriptionEvent = $(info.el).find('.fc-event-time').text() + ',' + info.event._def.extendedProps.branchName;
            		$(info.el).find('.fc-event-title').append("<br/>" + ' ' + info.event._def.extendedProps.branchName); 
        		} else {
        			$(this).insertBefore($(this).prev('.fc-event-time'));
            		var descriptionEvent = $(info.el).find('.fc-event-time').text() + ', ' + info.event._def.extendedProps.branchName;
            		$(info.el).find('.fc-event-time').text(descriptionEvent);
        		}
        		
        	})
		} else {
    		$(info.el).find('.fc-event-title').append("<br/>" + ' ' + info.event._def.extendedProps.branchName);
		}
//    	if(info.event.allDay) {
//			info.event.setEnd(new Date(new Date().setDate(info.event.start.getDate() + 1)));
//    	}
	},
      //next and prev click handle
//      customButtons: {
//        prev: {
//          text: 'Prev',
//          click: function(info) {
//                      // so something before
////                      toastr.warning("PREV button is going to be executed")
//                      // do the original command
//                      // do something after
////                      toastr.warning("PREV button executed")
//    					calendar.prev();
//                      viewType = calendar.view.type;
//                      if('dayGridMonth' == viewType) {
//                    	  yearCurrent = calendar.getDate().getFullYear();
//                    	  monthCurrent = calendar.getDate().getMonth() + 1;
////                    	  getEventForMonth(monthCurrent,yearCurrent);
//                      }
//          }
//        },
//        next: {
//          text: 'Next',
//          click: function() {
//                      // so something before
////                      toastr.success("NEXT button is going to be executed")
//                      // do the original command
//                      // do something after
////                      toastr.success("NEXT button executed")
//                      viewType = calendar.view.type;
//                      calendar.next();
//                      if('dayGridMonth' == viewType) {
//                    	  yearCurrent = calendar.getDate().getFullYear();
//                    	  monthCurrent = calendar.getDate().getMonth() + 1;
////                    	  getEventForMonth(monthCurrent,yearCurrent);
//                      }
//          }
//        },
//      }
      
    });

    calendar.render();
    // $('#calendar').fullCalendar()

    /* ADDING EVENTS */
    var currColor = '#3c8dbc' //Red by default
    // Color chooser button
    $('#color-chooser > li > a').click(function (e) {
      e.preventDefault()
      // Save color
      currColor = $(this).css('color')
      // Add color effect to button
      $('#add-new-event').css({
        'background-color': currColor,
        'border-color'    : currColor
      })
    }),
    $('#btn-del').click(function() {
		/*calendar.getEvents().forEach(event => {
			if(eventRemove.event._def.defId == event._def.defId) {
				event.remove();
			}
		});*/
		var editType = 'CANCEL';
		updateAnEvent(editType);
	});
//    $('#add-new-event').click(function (e) {
//      e.preventDefault()
//      // Get value and make sure it is not null
//      var val = $('#new-event').val()
//      if (val.length == 0) {
//        return
//      }
//
//      // Create events
//      var event = $('<div />')
//      event.css({
//        'background-color': currColor,
//        'border-color'    : currColor,
//        'color'           : '#fff'
//      }).addClass('external-event')
//      event.text(val)
//      $('#external-events').prepend(event)
//
//      // Add draggable funtionality
////      ini_events(event)
//
//      // Remove event from text input
//      $('#new-event').val('')
//    })
    
});

/* initialize the external events
-----------------------------------------------------------------*/
//function ini_events(ele) {
// ele.each(function () {
//
//   // create an Event Object (https://fullcalendar.io/docs/event-object)
//   // it doesn't need to have a start or end
//   var eventObject = {
//     title: $.trim($(this).text()) // use the element's text as the event title
//   }
//
//   // store the Event Object in the DOM element so we can get to it later
//   $(this).data('eventObject', eventObject)
//
//   // make the event draggable using jQuery UI
//   $(this).draggable({
//     zIndex        : 1070,
//     revert        : true, // will cause the event to go back to its
//     revertDuration: 0  //  original position after the drag
//   })
//
// })
//}

function addAnEvent() {
	action = "booking/add";
	var paramBean = {};
	var eventCalendar= $('form[name=eventCalendarFrm]').serializeToJSON();
	if($('#checkAll').is(':checked')) {
		eventCalendar.endTime = new Date(eventCalendar.endTime);
		eventCalendar.startTime = new Date(eventCalendar.startTime);
		eventCalendar.allDay = 1;
	} else {
		eventCalendar.endTime = new Date(eventCalendar.endTime);
		eventCalendar.startTime = new Date(eventCalendar.startTime);
//		eventCalendar.allDay = 0;
		if("dayGridMonth" == viewTypeGlobal) {
			if(!$('#periodEvent').hasClass('btn-hide')) {
				eventCalendar.endTime = new Date(eventCalendar.endTime);
				eventCalendar.startTime = new Date(eventCalendar.startTime);
				eventCalendar.allDay = 1;
			} else {
//				var endDateTimeStr = new Date(eventCalendar.endTime).toLocaleDateString('en-US', {year: 'numeric', month: '2-digit', day: '2-digit'}) + ' ' + eventCalendar.endTimePicker;
				var endDateTimeStr = new Date(eventCalendar.startTime).toLocaleDateString('en-US', {year: 'numeric', month: '2-digit', day: '2-digit'}) + ' ' + eventCalendar.endTimePicker;
				eventCalendar.endTime = new Date(endDateTimeStr);
				var startDateTimeStr = new Date(eventCalendar.startTime).toLocaleDateString('en-US', {year: 'numeric', month: '2-digit', day: '2-digit'}) + ' ' + eventCalendar.startTimePicker;
				eventCalendar.startTime = new Date(startDateTimeStr);
//				eventCalendar.allDay = 0;
			}
		}
	}
	paramBean.data = eventCalendar;
	$.ajax({
        type: "POST",
        contentType : 'application/json',
        url: action,
        dataType: 'html',
        data : JSON.stringify(paramBean),
        cache: false,
        timeout: 600000,
        success: function (response) {
        	var objRespnse = JSON.parse(response);
        	mess = objRespnse.message;
        	/*showDialogConfirm(mess);*/
    		$('#myModal').modal('hide');
        	if(mess == 'SUCCESS') {
//        		var eventData = {
//                        title: eventCalendar.cusCode + "_" + eventCalendar.petCode,
//                        start: start,
//                        end: end
//                    };
//           	 calendar.addEvent(eventData);
//        		calendar.refetchEvents()
        		sendMessage();
        	}
        	
        },
        error: function (e) {
            console.log(e);
            mess = "SYS_ERR";
            showDialogConfirm(mess);
        }
    });
}

function getTimeHour(dateTime) {
	var hh = dateTime.getHours();
	if(hh < 10) {
		hh = '0' + hh;
	}
	var mm = dateTime.getMinutes();
	if(mm < 10) {
		mm = '0' + mm; 
	}
	return hh + ':' + mm;
}

function getEventForMonth(month, year) {
	var action = "booking/getEventForMonth?month=" + month + '&year=' + year;
	$.ajax({
		type: "GET",
        contentType : 'application/json',
        url: action,
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (response) {
        	var objRespnse = response;
        	mess = objRespnse.message;
        	/*showDialogConfirm(mess);*/
    		$('#myModal').modal('hide');
        	if(mess == 'SUCCESS') {
        		var eventData = {
                        title: eventCalendar.cusCode + "_" + eventCalendar.petCode,
                        start: start,
                        end: end
                    };
        		calendar.refetchEvents()
        	}
        },
        error: function (e) {
            console.log(e);
            mess = "SYS_ERR";
            showDialogConfirm(mess);
        }
    });
}

function getDataInit() {
	var action = "booking/getDataInit";
	$.ajax({
		type: "GET",
        contentType : 'application/json',
        url: action,
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (response) {
			if(response.message == "SUCCESS") {
				return response.result;
			}
        },
        error: function (e) {
            console.log(e);
            mess = "SYS_ERR";
            showDialogConfirm(mess);
        }
    });
}

function changeCusCode(e) {
	var idSelect = e.id;
	edit = 1;
	if(idSelect == 'breedAdd') {
		edit = 0;
	}
	var selectedValue = $(e).val();
	var action = "booking/getLstPetByCusCode?cusCode=" + selectedValue + '&edit=' + edit;
	$.ajax({
        type: "GET",
        contentType : 'application/json',
        url: action,
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function (response) {
//        	if (edit == 0) {
//        		$("#species-droplist-add").replaceWith(response);
//        	} else {
//        		$("#species-droplist-edit").replaceWith(response);
//        	}
			$("#pets-droplist-add").replaceWith(response);
        },
        error: function (e) {
            console.log(e);
            mess = "SYS_ERR";
            showDialogConfirm(mess);
        }
    });
}

function updateAnEvent(editType, allDay) {
	action = "booking/edit";
	var paramBean = {};
	var eventCalendar= $('form[name=eventCalendarFrm]').serializeToJSON();
	eventCalendar.endTime = new Date(eventCalendar.endTime);
	eventCalendar.startTime = new Date(eventCalendar.startTime);
	if(editType == 'CANCEL') {
		eventCalendar.status = 1;
	}
	eventCalendar.allDay = allDay ? 1 : 0;
	eventCalendar.editType = editType;
	paramBean.data = eventCalendar;
	$.ajax({
        type: "POST",
        contentType : 'application/json',
        url: action,
        dataType: 'html',
        data : JSON.stringify(paramBean),
        cache: false,
        timeout: 600000,
        success: function (response) {
        	var objRespnse = JSON.parse(response);
        	mess = objRespnse.message;
        	/*showDialogConfirm(mess);*/
    		$('#myModal').modal('hide');
        	if(mess == 'SUCCESS') {
//        		calendar.refetchEvents()
        		sendMessage();
        	}
        	
        },
        error: function (e) {
            console.log(e);
            mess = "SYS_ERR";
            showDialogConfirm(mess);
        }
    });
}

function addTime() {
	$('.add-time-after').removeClass('btn-hide');
	$('#periodEvent').addClass('btn-hide');
	var options = optionsM;
	var periodStartVal = new Date($('#startTime').val()).toLocaleDateString("vi", options);
	$('#periodStart').text(periodStartVal);
//	var periodEndVal = new Date($('#endTime').val()).toLocaleDateString("vi", options);
//	$('#periodEnd').text(periodEndVal);
	$('#btn-addTime').addClass('btn-hide');
	$('#checkAll').prop("checked", false);
	$('#allDayHidden').val(0);
	$('input#startTimePicker').timepicker({
		'timeFormat': 'h:i A',
		'step': 15,
		'minTime': '7:00 AM',
		'maxTime': '11:00 PM',
	});
	$('input#endTimePicker').timepicker({
		'timeFormat': 'h:i A',
		'step': 15,
		'minTime': '7:00 AM',
		'maxTime': '11:00 PM',
	});
}

function checkAllDay() {
	if($('#checkAll').is(':checked')) {
		$('.add-time-after').addClass('btn-hide');
		$('#periodEvent').removeClass('btn-hide');
//		$('#btn-addTime').removeClass('btn-hide');
		$('.check-all-after').removeClass('btn-hide');
	} else {
		$('.add-time-after').removeClass('btn-hide');
		$('#periodEvent').addClass('btn-hide');
	}
}

function connect() {
//    var socket = new SockJS('/2VET/ws');
	urlWs = $('#contextRoot').val()+'/ws';
    var socket = new SockJS(urlWs);
    stompClient = Stomp.over(socket);  
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        connectWS = 'success';
        stompClient.subscribe('/topic/messages', function(messageOutput) {
        	var mess = JSON.parse(messageOutput.body);
        	if(mess.text == 'updated') {
        		calendar.refetchEvents();
        	}
//            showMessageOutput(JSON.parse(messageOutput.body));
        });
        
//        stompClient.subscribe('/show/event', function(messageOutput) {
//            showMessageOutput(JSON.parse(messageOutput.body));
//        });
    });
}

function disconnect() {
    if(stompClient != null) {
        stompClient.disconnect();
    }
    connectWS = 'failed';
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage(start, end) {
	stompClient.send("/app/chat", {},
//	stompClient.send("/app/calendar", {},
      JSON.stringify({'start':start, 'end':end}));
}

function showMessageOutput(messageOutput) {
    var response = document.getElementById('response');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(messageOutput.from + ": " 
      + messageOutput.text + " (" + messageOutput.time + ")"));
    response.appendChild(p);
}

function acceptAnEvent() {
	action = "booking/edit";
	var paramBean = {};
	var eventCalendar= $('form[name=eventCalendarFrm]').serializeToJSON();
	eventCalendar.endTime = new Date(eventCalendar.endTime);
	eventCalendar.startTime = new Date(eventCalendar.startTime);
	var editType = 'ACCEPTED';
	eventCalendar.editType = editType;
	paramBean.data = eventCalendar;
	$.ajax({
        type: "POST",
        contentType : 'application/json',
        url: action,
        dataType: 'html',
        data : JSON.stringify(paramBean),
        cache: false,
        timeout: 600000,
        success: function (response) {
        	var objRespnse = JSON.parse(response);
        	mess = objRespnse.message;
//        	showDialogConfirm(mess);
    		$('#myModal').modal('hide');
        	if(mess == 'SUCCESS') {
//        		calendar.refetchEvents()
        		sendMessage();
        	}
        	
        },
        error: function (e) {
            console.log(e);
            mess = "SYS_ERR";
            showDialogConfirm(mess);
        }
    });
}

function getEventAssign(element) {
	var chkId = element.id;
//	if(chkId == 'event-assigned') {
//		if(!$(chkId).is(':checked')) {
//			searchByAssign = false;
//		} else {
//			searchByAssign = true;
//		}
//	}
	if($('#' + chkId).is(':checked')) {
		if(chkId == 'event-assigned') {
			searchByAssign = true;
			if(!$('#event-waiting-assign').is(':checked')) {
				searchByWaiting = false;
			}
		} else {
			if(!$('#event-assigned').is(':checked')) {
				searchByAssign = false;
			}
			searchByWaiting = true;
		}
	} else {
		if(chkId == 'event-assigned') {
			searchByAssign = false;
		} else {
			searchByWaiting = false;
		}
	}
	calendar.refetchEvents();
}