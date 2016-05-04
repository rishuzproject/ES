<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<head>
<style>

.departmentErrorBlock
{
  box-shadow: 3px 5px 11px #836463;border-right: 5px solid #953b39;background:#fff;color:#836463;height: 160px; overflow-y: auto
}
.deptErrorHeader
{
 border-bottom: 1px solid #953b39;border-right: 5px solid #953b39;margin-bottom:0px !important
}
.alert.alert-danger::-webkit-scrollbar {
	height: 9px;
	width: 9px;
}
.mandatoryStyle {
	color : #ed1c24 !important;
}

.alert.alert-danger::-webkit-scrollbar-thumb {
	background-color: rgb(216, 168, 168);
}

.alert.alert-danger::-webkit-scrollbar-thumb:hover {
	background-color: rgb(216, 168, 168);
}

.alert.alert-danger::-webkit-scrollbar-thumb:active {
	background-color: rgb(216, 168, 168);
}
.calendarValidation{
background: #fff0f0;
border-color: #A90329;
}
.validationMessage{
  margin-top: 6px;
  padding: 0 1px;
  font-style: normal;
  font-size: 11px;
  line-height: 15px;
  color: #D56161;
}
/* #calendarForm{
     page-break-after: auto;
}*/
 @media print
{
	* {-webkit-print-color-adjust:exact;}
	body * { visibility: hidden; }
	.calendarForm * { visibility: visible; 
	}
	.bg-color-orange {
		  background-color: #c79121 !important;
		  
		}
	.bg-color-darken {
	  background-color: #404040 !important;
	}	
	/* .div2 { position: absolute; top: 40px; left: 30px; } */
}  
</style>
</head>

<!-- #MAIN CONTENT -->
<link href="assets/css/jquery.gritter.css" rel="stylesheet">
<!-- <link href="assets/css/fullcalendar.min.css" rel="stylesheet"> -->
<!-- <link href="assets/css/fullcalendar.print.css" rel="stylesheet" media="print"> -->
<script type="text/javascript" src="assets/js/fullcalendar.min.js"></script>
<script type="text/javascript" src="assets/js/moment.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="assets/js/companyCalendar.js"></script>

<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-calendar fa-fw txt-color-blue"></i><spring:message code="Holiday.PageHead.Title" />
		</h1>
	</div>
</div>
<div class="col-sm-12 col-md-12 col-lg-3">
		<!-- new widget -->
		<div class="jarviswidget jarviswidget-color-blueDark" >
			<header>
				<h2> <spring:message code="Holiday.Form.AddEvents" /> </h2>
			</header>

			<!-- widget div-->
			<div>

				<div class="widget-body">
					<!-- content goes here -->

					<form id="add-event-form">
						<fieldset>
							<input type="hidden" id="eventId">
							<input type="hidden" id="fromDate">
							<input type="hidden" id="toDate">
							<div class="form-group">
								<label><spring:message code="Holiday.Form.SelectEventIcon" />
								&nbsp;<span class="mandatoryStyle">*</span>
								</label>
								<div class="btn-group btn-group-sm btn-group-justified" data-toggle="buttons">
									<label class="btn btn-default active">
										<input type="radio" name="iconselect" id="icon-1" value="fa-info" checked="">
										<i class="fa fa-info text-muted"></i> </label>
									<label class="btn btn-default">
										<input type="radio" name="iconselect" id="icon-2" value="fa-warning">
										<i class="fa fa-warning text-muted"></i> </label>
									<label class="btn btn-default">
										<input type="radio" name="iconselect" id="icon-3" value="fa-check">
										<i class="fa fa-check text-muted"></i> </label>
									<label class="btn btn-default">
										<input type="radio" name="iconselect" id="icon-4" value="fa-user">
										<i class="fa fa-user text-muted"></i> </label>
									<label class="btn btn-default">
										<input type="radio" name="iconselect" id="icon-5" value="fa-lock">
										<i class="fa fa-lock text-muted"></i> </label>
									<label class="btn btn-default">
										<input type="radio" name="iconselect" id="icon-6" value="fa-clock-o">
										<i class="fa fa-clock-o text-muted"></i> </label>
								</div>
							</div>
							<div class="form-group">
								<label><spring:message code="Holiday.Form.SelectEventType" />
								&nbsp;<span class="mandatoryStyle">*</span>
								</label>
								<select class="form-control show-tick" id= "calendarEventType" name="calendarEventType">
									<option value="">Select Event Type</option>
									<option value="holiday">Holiday</option>
									<option value="event">Event</option>
								</select>
								<div class="typeValidation validationMessage" style="display:none">Please select the event type</div>
							</div>
							<div class="form-group">
								<label><spring:message code="Holiday.Form.EventTitle.PlaceHolder" />
								&nbsp;<span class="mandatoryStyle">*</span>
								</label>
								<input id="title" class="form-control" name="title" maxlength="40" type="text" placeholder="<spring:message code="Holiday.Form.EventTitle.PlaceHolder" />">
								<div class="titleValidation validationMessage" style="display:none">Please enter the event title</div>
							</div>
							<div class="form-group">
											<label><spring:message code="Holiday.Form.description.PlaceHolder"/>
											&nbsp;<span class="mandatoryStyle">*</span>
											</label> <textarea
													class="form-control" rows="3" name="description"
													id="description" maxlength="40"
													placeholder="<spring:message code="Holiday.Form.description.PlaceHolder"/>"></textarea>
												<font
												color="red" size="1px"><p align="right">
														<spring:message
															code="Holiday.Form.description.Limt" />
													</p></font>
									<div class="descriptionValidation validationMessage" style="display:none">Please enter the event description</div>				
											
								</div>
							<div class="form-group">
								<label><spring:message code="Holiday.Form.SelectEventColor" />
								&nbsp;<span class="mandatoryStyle">*</span>
								</label>
								<div class="btn-group btn-group-justified btn-select-tick" data-toggle="buttons">
									<label class="btn bg-color-darken active">
										<input type="radio" name="priority" id="option1" value="bg-color-darken txt-color-white" checked="">
										<i class="fa fa-check txt-color-white"></i> </label>
									<label class="btn bg-color-blue">
										<input type="radio" name="priority" id="option2" value="bg-color-blue txt-color-white">
										<i class="fa fa-check txt-color-white"></i> </label>
									<label class="btn bg-color-orange">
										<input type="radio" name="priority" id="option3" value="bg-color-orange txt-color-white">
										<i class="fa fa-check txt-color-white"></i> </label>
									<label class="btn bg-color-greenLight">
										<input type="radio" name="priority" id="option4" value="bg-color-greenLight txt-color-white">
										<i class="fa fa-check txt-color-white"></i> </label>
									<label class="btn bg-color-blueLight">
										<input type="radio" name="priority" id="option5" value="bg-color-blueLight txt-color-white">
										<i class="fa fa-check txt-color-white"></i> </label>
									<label class="btn bg-color-red">
										<input type="radio" name="priority" id="option6" value="bg-color-red txt-color-white">
										<i class="fa fa-check txt-color-white"></i> </label>
								</div>
							</div>

						</fieldset>
						<div class="form-actions">
							<div class="row">
								<div class="col-md-12">
									<button class="btn btn-primary" type="button" id="add-event">
										<i class="fa fa-floppy-o"></i>
										<spring:message code="Button.AddEvent" />
									</button>
									<button class="btn btn-primary" type="button" id="delete-event" style="display:none">
										<i class="fa fa-trash-o bigger-130"></i>
										Delete
									</button>
									<button class="btn btn-primary" type="button" id="update-event" style="display:none">
										<i class="fa fa-thumbs-up bigger-110"></i>
										<spring:message code="Button.UpdateEvent" />
									</button>
									 <button class="btn btn-primary" type="button" id="reset-event" onclick="resetHolidayForm()">
										<i class="fa fa-undo"></i>
									</button>
								</div>
							</div>
						</div>
					</form>

					<!-- end content -->
				</div>

			</div>
			<!-- end widget div -->
		</div>
		<!-- end widget -->

		<div class="well well-sm" id="event-container">
			<form>
				<legend>
					<spring:message code="Holiday.Form.DraggableEvents" />
				</legend>
				<ul id="external-events" class="list-unstyled">
					
				</ul>
				<div class="checkbox">
					<label>
						<input type="checkbox" id="drop-remove" class="checkbox style-0" checked="checked">
						<span><spring:message code="Holiday.Form.removeafterdrop" /></span> </label>

				</div>
			</form>

		</div>
	</div>
<div class="col-sm-12 col-md-12 col-lg-9">
	<div class="jarviswidget jarviswidget-color-blueDark calendarForm" id="calendarForm">
	<header>
				<span class="widget-icon calendarHeaderIcon"> <i class="fa fa-calendar"></i> </span>
				<h2> <spring:message code="Holiday.Form.MyEvents" /> </h2>
				<button class="btn btn-primary" type="button" id="print-event" style="background-color:#4C4F53;float: right;border-color: #4C4F53;">
					<i class="fa fa-print"></i>
				</button>
			</header>
	<div id="comapnyCalendar"></div>
	</div>
</div>	

<!-- END #MAIN CONTENT -->
<script>
setInterval(function(){$('#year1').next().find('.dropdown-menu').css('background','black')},3);

</script>