runAllForms();
pageSetUp();
holidayFormDetailsTable = null;
var laddaButton = 0;
var holidayToDelete = 0;

var pagefunction = function() {
	
	// full calendar
	
	var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();

    var hdr = {
			left: 'prev,next today',
			center: 'title',
			right: 'month,basicWeek,basicDay'
		};

    var initDrag = function (e) {
        // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
        // it doesn't need to have a start or end

        var eventObject = {
            title: $.trim(e.children().text()), // use the element's text as the event title
            description: $.trim(e.children('span').attr('data-description')),
            icon: $.trim(e.children('span').attr('data-icon')),
            className: $.trim(e.children('span').attr('class')),
            calendarEventType :$.trim(e.children('span').attr('data-eventType')) // use the element's children as the event class
        };
        // store the Event Object in the DOM element so we can get to it later
        e.data('eventObject', eventObject);

        // make the event draggable using jQuery UI
        e.draggable({
            zIndex: 999,
            revert: true, // will cause the event to go back to its
            revertDuration: 0 //  original position after the drag
        });
    };

    var addEvent = function (title, priority, description, icon,calendarEventType) {
        title = title.length === 0 ? "Untitled Event" : title;
        description = description.length === 0 ? "No Description" : description;
        icon = icon.length === 0 ? " " : icon;
        priority = priority.length === 0 ? "label label-default" : priority;

        var html = $('<li><span class="' + priority + '" data-description="' + description + '" data-icon="' +icon+
         '" data-eventType="' + calendarEventType +
             '">' + title + '</span></li>').prependTo('ul#external-events').hide().fadeIn();

        $("#event-container").effect("highlight", 800);

        initDrag(html);
    };

    /* initialize the external events
	 -----------------------------------------------------------------*/

    $('#external-events > li').each(function () {
        initDrag($(this));
    });

    $('#add-event').click(function () {
        var title = $('#title').val(),
            priority = $('input:radio[name=priority]:checked').val(),
            description = $('#description').val(),
            icon = $('input:radio[name=iconselect]:checked').val(),
            calendarEventType = $('#calendarEventType').val();
        if(title == ""){
        	$('#title').addClass("calendarValidation");
        	$(".titleValidation").show();
        }
        if(description == ""){
        	$('#description').addClass("calendarValidation");
        	$(".descriptionValidation").show();
        }
        if(calendarEventType == ""){
        	$('#calendarEventType').addClass("calendarValidation");
        	$(".typeValidation").show();
        }
        if(description != "" && title !="" && calendarEventType != ""){
        	resetHolidayForm();
            addEvent(title, priority, description, icon,calendarEventType);
        }
        
    });

    /* initialize the calendar
	 -----------------------------------------------------------------*/

    $('#comapnyCalendar').fullCalendar({

        header: hdr,
        defaultView: 'month',
        //aspectRatio: 1.5,
        //weekMode: 'variable',
//        height:"100px",
       
//        borderColor: 'green',
        //selectable: true,
        buttonText: 
        {
          today:"Today",
          month:"Month",
          week:"Week",
          day:"Day",
        },
        slotEventOverlap : false,
        editable: true,
        droppable: true, // this allows things to be dropped onto the calendar !!!
//        allDaySlot : false,
        drop: function (date, allDay) { // this function is called when something is dropped

            // retrieve the dropped element's stored Event Object
            var originalEventObject = $(this).data('eventObject');

            // we need to copy it, so that multiple events don't have a reference to the same object
            var copiedEventObject = $.extend({}, originalEventObject);

            // assign it the date that was reported
            copiedEventObject.start = date;
            copiedEventObject.allDay = allDay;
//            console.log(copiedEventObject);

            // is the "remove after drop" checkbox checked?
            if ($('#drop-remove').is(':checked')) {
                // if so, remove the element from the "Draggable Events" list
                $(this).remove();
            }
            var calendarData = {};
        	calendarData.eventTitle = copiedEventObject.title;
        	calendarData.calendarEventType = copiedEventObject.calendarEventType;
        	calendarData.eventType = copiedEventObject.icon.split("-")[1];
        	calendarData.eventDescription = copiedEventObject.description;
        	if(copiedEventObject.id == undefined){
        		calendarData.eventId = null;
        	}else{
        		calendarData.eventId = copiedEventObject.id;
        	}
        	var date = copiedEventObject.start;
        	var d= date.getDate();
            var m=parseInt(date.getMonth())+1;
            var y=date.getFullYear();
            if(m<10){
             m="0"+m;
            }else{
             m=m;
            }
            if(d<10){
             d="0"+d;
            }else{
              d=d;
            }
            date=m+"-"+d+"-"+y;
            calendarData.fromDate = moment(date);
            if(copiedEventObject.end == null){
            	calendarData.toDate = moment(date);
            }else{
            	var date1 = copiedEventObject.end;
            	var d1= date1.getDate();
                var m1=parseInt(date1.getMonth())+1;
                var y1=date1.getFullYear();
                if(m1<10){
                 m1="0"+m1;
                }else{
                 m1=m1;
                }
                if(d1<10){
                 d1="0"+d1;
                }else{
                  d1=d1;
                }
                date1=m1+"-"+d1+"-"+y1;
                calendarData.toDate = moment(date1);
            }
        	if(copiedEventObject.className.split(" ")[0] == "bg-color-darken"){
//        		console.log(copiedEventObject.className[0]);
        		calendarData.eventColorCode = "DARK";
        	}
        	if(copiedEventObject.className.split(" ")[0] == "bg-color-blue"){
        		calendarData.eventColorCode = "BLUE";
        	}
        	if(copiedEventObject.className.split(" ")[0] == "bg-color-orange"){
        		calendarData.eventColorCode = "ORANGE";
        	}
        	if(copiedEventObject.className.split(" ")[0] == "bg-color-greenLight"){
        		calendarData.eventColorCode = "GREENLIGHT";
        	}
        	if(copiedEventObject.className.split(" ")[0] == "bg-color-blueLight"){
        		calendarData.eventColorCode = "BLUELIGHT";
        	}
        	if(copiedEventObject.className.split(" ")[0] == "bg-color-red"){
        		calendarData.eventColorCode = "RED";
        	}
//        	console.log(calendarData);
        	$
        	.ajax({
        		url : "saveCalendarEvent",
        		type : "POST",
        		contentType : "application/json",
        		data : JSON.stringify(calendarData),
        		success : function(result) {
        			result = JSON.parse(result);
//        			console.log(result);
        			// CLearing Project Table
        			if (result.ajaxResult == "success") {
//        				resetHolidayForm();
        				if(copiedEventObject.id == undefined){
        					gritterForSucessMsgs("Calendar Event has been added successfully.");
        					copiedEventObject.id = result.eventId;
        					// render the event on the calendar
        		            // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
//        		            console.log(copiedEventObject);
        					$('#comapnyCalendar').fullCalendar('renderEvent', copiedEventObject, true);
        				}else{
        					gritterForSucessMsgs("Calendar Event has been updated successfully.");
        				}
        			} 
        		},
        		error : function() {
        			gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');

        					}
        	});
        },
        eventDrop: function(event, delta, revertFunc) {

//            console.log(event);
            saveOrUpdateCalendar(event);
        },
        eventResize: function(event, delta, revertFunc) {
//        	console.log(event);
        	saveOrUpdateCalendar(event);
        },
        select: function (start, end, allDay) {
            var title = prompt('Event Title:');
            if (title) {
                calendar.fullCalendar('renderEvent', {
                        title: title,
                        start: start,
                        end: end,
                        allDay: allDay
                    }, true // make the event "stick"
                );
            }
            calendar.fullCalendar('unselect');
        },


        eventRender: function (event, element, icon) {
//        	element.append( "<span class='closeon'>X</span>" );
            if (!event.description == "") {
                element.find('.fc-event-title').append("<br/><span class='ultra-light'>" + event.description +
                    "</span>");
            }
            if (!event.icon == "") {
                element.find('.fc-event-title').append("<i class='air air-top-right fa " + event.icon +
                    " '></i>");
            }
        },

        windowResize: function (event, ui) {
            $('#comapnyCalendar').fullCalendar('render');
        },
        dayClick: function(date, jsEvent, view) {
            $(".fc-widget-content").each(function(){
            	$(this).css('background-color', 'white');
            });
            $(this).css('background-color', '#ffc');

        },
        eventClick: function(event, element) {
            // change the border color just for fun
//            $(this).css('border-color', 'red');
//        	console.log(event);
//        	showing the event information in form
        	showEventInformation(event);
        	$("#delete-event").click(function(){
        		$.SmartMessageBox(
        				{
        					title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
        					content : "<br>This Event will be permanently deleted and cannot be recovered. Only Admin can	recover it.<br>"
        							+ "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
        					buttons : '[No][Yes]'
        				}, function(ButtonPressed) {
        					if (ButtonPressed === "Yes") {
        						 var dataObj = {
        						    		eventId : event.id,
        						    	};
        						    $.ajax({
        								url : "deleteCalendarEvent",
        								type : "GET",
        								data : dataObj,
        								success : function(result) {
        									result = JSON.parse(result);
        									// CLearing Project Table
        									if (result.ajaxResult == "success") {
        										resetHolidayForm();
        										$('#comapnyCalendar').fullCalendar('removeEvents',event.id);
        										gritterForSucessMsgs("Calendar Event has been deleted successfully.");
        									} else {
        										gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
        												+ result.reason);
        									}
        								},
        								error : function() {
        									gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');

        								}
        							});
        					}
        				});
        	});
        	$("#update-event").click(function(){
//        		console.log($("#eventId").val());
        		var calendarData = {};
        		calendarData.eventTitle = $("#title").val();
        		calendarData.eventType = $('input[name="iconselect"]').val().split("-")[1];
        		calendarData.eventDescription = $("#description").val();
        		calendarData.eventId = $("#eventId").val();
        		calendarData.calendarEventType = $("#calendarEventType").val();
        		var color = "";
        		$('input[name="priority"]').parent().each(function(){
        			if($(this).hasClass("active")){
        				color=$(this).children().val().split(" ")[0];
        				}
        		});
        		$('input[name="iconselect"]').parent().each(function(){
        			if($(this).hasClass("active")){
        				calendarData.eventType = $(this).children().val().split("-")[1];
        				}
        		});
        		var date = new Date($("#fromDate").val());
        		var d= date.getDate();
        	    var m=parseInt(date.getMonth())+1;
        	    var y=date.getFullYear();
        	    if(m<10){
        	     m="0"+m;
        	    }else{
        	     m=m;
        	    }
        	    if(d<10){
        	     d="0"+d;
        	    }else{
        	      d=d;
        	    }
        	    date=m+"-"+d+"-"+y;
        	    calendarData.fromDate = moment(date);
        	    if($("#toDate").val() == ""){
        	    	calendarData.toDate = moment(date);
        	    }else{
        	    	var date1 = new Date($("#toDate").val());
        	    	var d1= date1.getDate();
        	        var m1=parseInt(date1.getMonth())+1;
        	        var y1=date1.getFullYear();
        	        if(m1<10){
        	         m1="0"+m1;
        	        }else{
        	         m1=m1;
        	        }
        	        if(d1<10){
        	         d1="0"+d1;
        	        }else{
        	          d1=d1;
        	        }
        	        date1=m1+"-"+d1+"-"+y1;
//        	        console.log(date1);
        	        calendarData.toDate = moment(date1);
        	    }
        		if(color == "bg-color-darken"){
        			calendarData.eventColorCode = "DARK";
        		}
        		if(color == "bg-color-blue"){
        			calendarData.eventColorCode = "BLUE";
        		}
        		if(color == "bg-color-orange"){
        			calendarData.eventColorCode = "ORANGE";
        		}
        		if(color == "bg-color-greenLight"){
        			calendarData.eventColorCode = "GREENLIGHT";
        		}
        		if(color == "bg-color-blueLight"){
        			calendarData.eventColorCode = "BLUELIGHT";
        		}
        		if(color == "bg-color-red"){
        			calendarData.eventColorCode = "RED";
        		}
//        		console.log(calendarData);
        		$
        		.ajax({
        			url : "saveCalendarEvent",
        			type : "POST",
        			contentType : "application/json",
        			data : JSON.stringify(calendarData),
        			success : function(result) {
        				result = JSON.parse(result);
        				event.className[0] = color;
        				event.title = calendarData.eventTitle;
        				event.description = calendarData.eventDescription;
        				event.icon = "fa-"+calendarData.eventType;
        				event.calendarEventType = calendarData.calendarEventType;
//        				console.log(event);
//        	             $('#comapnyCalendar').fullCalendar('removeEvents',event.id);
        				 $('#comapnyCalendar').fullCalendar('updateEvent', event);
        				 if (result.ajaxResult == "success") {
        					 resetHolidayForm();
	       					  gritterForSucessMsgs("Calendar Event has been updated successfully.");
	        			} 
        			},
        			error : function() {
        				gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');
        			}
        		});
        	});
        },
        events: function(start, end, timezone, callback){
        	var calendarData = {};
            var date = start;
        	var d= date.getDate();
            var m=parseInt(date.getMonth())+1;
            var y=date.getFullYear();
            if(m<10){
             m="0"+m;
            }else{
             m=m;
            }
            if(d<10){
             d="0"+d;
            }else{
              d=d;
            }
            date=m+"-"+d+"-"+y;
            var date1 = end;
        	var d1= date1.getDate();
            var m1=parseInt(date1.getMonth())+1;
            var y1=date1.getFullYear();
            if(m1<10){
             m1="0"+m1;
            }else{
             m1=m1;
            }
            if(d1<10){
             d1="0"+d1;
            }else{
              d1=d1;
            }
            date1=m1+"-"+d1+"-"+y1;
            calendarData.fromDate = date;
            calendarData.toDate = date1;
            var tempData={
            "toDate":calendarData.toDate,
            "fromDate":calendarData.fromDate
            };
        	$.ajax({
				url : "getEventListForDateRange",
				type : "GET",
				data : tempData,
//				dataType:"json",
				success : function(result) {
					details = JSON.parse(result);
					for(var i=0;i<details.calendarEventList.length;i++){
						var start = new Date(details.calendarEventList[i].fromDate);
						var end = new Date(details.calendarEventList[i].toDate);
						var color = "";
						if(details.calendarEventList[i].eventColorCode == "DARK"){
							color = "bg-color-darken";
						}
						if(details.calendarEventList[i].eventColorCode ==  "BLUE"){
							color ="bg-color-blue";
						}
						if(details.calendarEventList[i].eventColorCode == "ORANGE"){
							color = "bg-color-orange";
						}
						if(details.calendarEventList[i].eventColorCode == "GREENLIGHT"){
							color = "bg-color-greenLight";
						}
						if(details.calendarEventList[i].eventColorCode == "BLUELIGHT"){
							color = "bg-color-blueLight";
						}
						if(details.calendarEventList[i].eventColorCode == "RED"){
							color = "bg-color-red";
						}
						var className = [];
						className[0] = color;
						className[1] = "txt-color-white";
						 var CalendarArray = {
							 id : details.calendarEventList[i].eventId,
                             title: details.calendarEventList[i].eventTitle,
                             description : details.calendarEventList[i].eventDescription,
                             start:  start,
                             end : end,
                             className:className,
                             icon : "fa-"+details.calendarEventList[i].eventType,
                             calendarEventType :details.calendarEventList[i].calendarEventType 
                         };
						 $('#comapnyCalendar').fullCalendar('renderEvent', CalendarArray);
					}
				}
			});
        }
    });

	$('.fc-header-right').show();
	$('.fc-header-center').show();
	
	
	$(".fc-content").css({"margin-right":"-13px","margin-left":"-13px"});
	
};

// end pagefunction

// loadscript and run pagefunction
loadScript("assets/js/jquery.fullcalendar.min.js", pagefunction);

function showEventInformation(event){
	$("#title").val(event.title);
	$("#description").val(event.description);
	$('input[name="priority"]').parent().removeClass('active');
	$('input[name="priority"]').each(function(){
		if($(this).val().split(" ")[0] == event.className[0]){
			$(this).parent().addClass("active");
		}
	});
	$('input[name="iconselect"]').parent().removeClass('active');
	$('input[name="iconselect"]').each(function(){
		if($(this).val() == event.icon){
			$(this).parent().addClass("active");
		}
	});
	$("#calendarEventType").val(event.calendarEventType);
	$("#eventId").val(event.id);
	$("#fromDate").val(event.start);
	$("#toDate").val(event.end);
	$("#add-event").hide();
	$("#update-event").show();
	$("#delete-event").show();
}
$(document).ready(function(){
	$('#title').bind("change keyup",function(){
		if($('#title').val()!=""){
			$("#title").removeClass("calendarValidation");
			$(".titleValidation").hide();
		}
	});
	$('#description').bind("change keyup",function(){
		if($('#description').val()!=""){
			$("#description").removeClass("calendarValidation");
			$(".descriptionValidation").hide();
		}
	});
	$('#calendarEventType').bind("change keyup",function(){
		if($('#calendarEventType').val()!=""){
			$("#calendarEventType").removeClass("calendarValidation");
			$(".typeValidation").hide();
		}
	});
	$('.fc-header-right').show();
	$('.fc-header-center').show();
	$("#print-event").click(function(){
//		window.print();
		var  w = $('#comapnyCalendar').css('width');
		beforePrint();
//		Popup();
		$(".fc-day-number").css("padding","0px 2px");
		var toPrint = document.getElementById('calendarForm');
		var height = $("#calendarForm").css("height");
//		console.log(toPrint.getElementsByClassName("fc-day-number"));
//		toPrint.style.border="";
	    var mywindow = window.open('', '', 'height=800,width=1400');
	    mywindow.document.write('<html><head><title>Events</title>');
	    /*optional stylesheet*/ 
//	    mywindow.document.write('<style>body{ background-color:white;}</style>');
//	    mywindow.document.write('<style>.bg-color-orange {background-color: #c79121!important; }</style>');
//	    mywindow.document.write('<style>.fc-other-month{ background-color:white !important;  background-image: -webkit-linear-gradient(135deg,rgba(0,0,0,.03)25%,transparent 25%,transparent 50%,rgba(0,0,0,.03)50%,rgba(0,0,0,.03)75%,transparent 75%,transparent) !important;}</style>');
//	    mywindow.document.write('<style>.fc-last fc-other-month{ background-color:white;  background-image: -webkit-linear-gradient(135deg,rgba(0,0,0,.03)25%,transparent 25%,transparent 50%,rgba(0,0,0,.03)50%,rgba(0,0,0,.03)75%,transparent 75%,transparent) !important;}</style>');
	    mywindow.document.write('<link rel="stylesheet" href="assets/css/smartadmin-production.min.css" />');
	    mywindow.document.write('<link rel="stylesheet" href="assets/css/bootstrap.min.css" />');
	    mywindow.document.write('<link rel="stylesheet" href="assets/css/font-awesome.min.css" />');
	    mywindow.document.write('<link rel="stylesheet" href="assets/css/jquery.gritter.css" />');
//	    mywindow.document.write('<link rel="stylesheet" href="assets/css/fullcalendar.min.css" />');
//	    mywindow.document.write('<link rel="stylesheet" href="assets/css/fullcalendar.print.css" />');
	    mywindow.document.write('</head><body >');
	    mywindow.document.write(toPrint.innerHTML);
	    mywindow.document.write('</body></html>');

	    mywindow.print();
	    mywindow.close();
//		afterPrint(w);
		// return calendar to original, delay so the print processes the correct width
	    window.setTimeout(function() {
	        $('#print-event').show();
//	        $('.calendarHeaderIcon').show();
	        $('#comapnyCalendar').css('width', w);
	        $('#comapnyCalendar').fullCalendar('render');
	    }, 10);
	});
});
var beforePrint = function(){
	// prepare calendar for printing
	$("#print-event").hide();
//	$(".calendarHeaderIcon").hide();
	$('#comapnyCalendar').css('width', '710px');
	$('#comapnyCalendar').fullCalendar('render');
}
function Popup()
{
	

    return true;
}

function saveOrUpdateCalendar(copiedEventObject){
	var calendarData = {};
	calendarData.eventTitle = copiedEventObject.title;
	calendarData.eventType = copiedEventObject.icon.split("-")[1];
	calendarData.eventDescription = copiedEventObject.description;
	calendarData.calendarEventType = copiedEventObject.calendarEventType;
	if(copiedEventObject.id == undefined){
		calendarData.eventId = null;
	}else{
		calendarData.eventId = copiedEventObject.id;
	}
	var date = copiedEventObject.start;
	var d= date.getDate();
    var m=parseInt(date.getMonth())+1;
    var y=date.getFullYear();
    if(m<10){
     m="0"+m;
    }else{
     m=m;
    }
    if(d<10){
     d="0"+d;
    }else{
      d=d;
    }
    date=m+"-"+d+"-"+y;
    calendarData.fromDate = moment(date);
    if(copiedEventObject.end == null){
    	calendarData.toDate = moment(date);
    }else{
    	var date1 = copiedEventObject.end;
    	var d1= date1.getDate();
        var m1=parseInt(date1.getMonth())+1;
        var y1=date1.getFullYear();
        if(m1<10){
         m1="0"+m1;
        }else{
         m1=m1;
        }
        if(d1<10){
         d1="0"+d1;
        }else{
          d1=d1;
        }
        date1=m1+"-"+d1+"-"+y1;
        calendarData.toDate = moment(date1);
    }
	if(copiedEventObject.className[0] == "bg-color-darken"){
		calendarData.eventColorCode = "DARK";
	}
	if(copiedEventObject.className[0] == "bg-color-blue"){
		calendarData.eventColorCode = "BLUE";
	}
	if(copiedEventObject.className[0] == "bg-color-orange"){
		calendarData.eventColorCode = "ORANGE";
	}
	if(copiedEventObject.className[0] == "bg-color-greenLight"){
		calendarData.eventColorCode = "GREENLIGHT";
	}
	if(copiedEventObject.className[0] == "bg-color-blueLight"){
		calendarData.eventColorCode = "BLUELIGHT";
	}
	if(copiedEventObject.className[0] == "bg-color-red"){
		calendarData.eventColorCode = "RED";
	}
//	console.log(calendarData);
	$
	.ajax({
		url : "saveCalendarEvent",
		type : "POST",
		contentType : "application/json",
		data : JSON.stringify(calendarData),
		success : function(result) {
			result = JSON.parse(result);
//			console.log(result);
			// CLearing Project Table
			if (result.ajaxResult == "success") {
				if(copiedEventObject.id == undefined){
					gritterForSucessMsgs("Calendar Event has been added successfully.");
				}else{
					gritterForSucessMsgs("Calendar Event has been updated successfully.");
				}
			} 
		},
		error : function() {
			gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');

					}
	});
}

function setCalendarEventToDelete(holidayId){
	  // $("#activityId").val(activityId);
	   
}
function resetHolidayForm(){
	var validator = $( "#add-event-form" ).validate();
	validator.resetForm();
	$('label').removeClass("state-success");
	$('label').removeClass("state-error");
	$("#eventId").val('');
	$("#fromDate").val('');
	$("#toDate").val('');
	$("#title").val('');
	$("#calendarEventType").val("");
	$("#title").removeClass("calendarValidation");
	$(".titleValidation").hide();
	$("#description").val("");
	$("#description").removeClass("calendarValidation");
	$(".descriptionValidation").hide();
	$('input[name="priority"]').parent().removeClass('active');
	$('input[name="iconselect"]').parent().removeClass('active');
	$("#icon-1").parent().addClass('active');
	$("#option1").parent().addClass('active');
	$("#add-event").show();
	$("#update-event").hide();
	$("#delete-event").hide();
}
