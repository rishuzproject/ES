<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<style>
.notificationStyle{
	  margin: 3px;
	  font-size: 14px;
	  font-weight: bold !important;
}
.notificationContent{
  height: 100%;
  border-color: #BDBDBD;
  border-width: 1px;
  border-style: solid;
}
.alert.alert-danger::-webkit-scrollbar {
	height: 9px;
	width: 9px;
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
</style>
<link href="assets/css/jquery.gritter.css" rel="stylesheet">
<script type="text/javascript" src="assets/js/jquery.gritter.min.js"></script>
<script type="text/javascript" src="assets/js/notificationCenter.js"></script>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-5">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa  fa-bullhorn txt-color-blue"></i>
			<spring:message code="NotificationCenter.Pagehead.Heading" />
		</h1>
	</div>
	
</div>

<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">

	<!-- Button trigger modal -->


	<!-- MODAL PLACE HOLDER -->
	<div class="modal fade" id="notificationCenterRemoteModal" tabindex="-1"
		role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true"  data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">


				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						<span class="badge pull-right inbox-badge customColor" onclick="resetNotificationForm()">&times;</span>
					</button>
					<h4 class="modal-title" id="emailSubject">
					</h4>
				</div>
				<div class="modal-body no-padding">

					<form id="addNotificationForm" method="POST" class="smart-form">
						<input type="hidden" name="emailNotificationId" id="emailNotificationId">
						<input type="hidden" name="notificationActionType" id="notificationActionType">
						<input type="hidden" name="notificationsubmittedDate" id="notificationsubmittedDate">
						<fieldset>
							<div class="row">
								<section class="col col-2">
									<label class="input notificationStyle">
										<spring:message code="NotificationCenter.Modal.Placeholder.From"/>
									</label>
								</section>
								<section class="col col-10">
									<label class="input"> <i
										class="icon-append fa fa-envelope"></i> <input
										type="text" name="emailFrom" id="emailFrom" readonly
										placeholder="<spring:message code="NotificationCenter.Modal.Placeholder.From"/>">
										<b class="tooltip tooltip-bottom-right"><spring:message
												code="NotificationCenter.Modal.Tooltips.From" /></b>
									</label>
								</section>
							</div>
							<div class="row">
								<section class="col col-2">
									<label class="input notificationStyle">
										<spring:message code="NotificationCenter.Modal.Placeholder.To"/>
									</label>
								</section>
								<section class="col col-10">
								<label class="input"> <i
									class="icon-append fa fa-envelope"></i> <input type="text"
									name="emailTo" id="emailTo" readonly
									placeholder="<spring:message code="NotificationCenter.Modal.Placeholder.To"/>">
									<b class="tooltip tooltip-bottom-right"><spring:message
											code="NotificationCenter.Modal.Tooltips.To" /></b>
								</label>
							</section>
							</div>
							<div class="row">
								<section class="col col-2 ">
									<label class="input notificationStyle">
										<spring:message code="NotificationCenter.Modal.Placeholder.Cc"/>
									</label>
								</section>
								<section class="col col-10">
								<label class="input"> <i
									class="icon-append fa fa-envelope"></i> <input
									type="text" name="emailCc" id="emailCc" readonly
									placeholder="<spring:message code="NotificationCenter.Modal.Placeholder.Cc"/>">
									<b class="tooltip tooltip-bottom-right"><spring:message
											code="NotificationCenter.Modal.Tooltips.Cc" /></b>
								</label>
							</section>
							</div>
							<div class="row">
								<section class="col col-2">
									<label class="input notificationStyle">
										<spring:message code="NotificationCenter.Modal.Placeholder.Bcc"/>
									</label>
								</section>
								<section class="col col-10">
								<label class="input"> <i
									class="icon-append fa fa-envelope"></i> <input
									type="text" name="emailBcc" id="emailBcc" readonly
									placeholder="<spring:message code="NotificationCenter.Modal.Placeholder.Bcc"/>">
									<b class="tooltip tooltip-bottom-right"><spring:message
											code="NotificationCenter.Modal.Tooltips.Bcc" /></b>
								</label>
							</section>
							</div>
							<%-- <div class="row">
								<section class="col col-2">
									<label class="input notificationStyle">
										<spring:message code="NotificationCenter.Modal.Placeholder.Subject"/>
									</label>
								</section>
								<section class="col col-10">
								<label class="input"> <i
									class="icon-append fa fa-envelope"></i> <input
									type="text" name="emailSubject" id="emailSubject"
									placeholder="<spring:message code="NotificationCenter.Modal.Placeholder.Subject"/>">
									<b class="tooltip tooltip-bottom-right"><spring:message
											code="NotificationCenter.Modal.Tooltips.Subject" /></b>
								</label>
							</section>
							</div> --%>
							<div class="row">
								<section class="col col-2">
									<label class="input notificationStyle">
										<spring:message code="NotificationCenter.Modal.Placeholder.Content"/>
									</label>
								</section>
								<section class="col col-10">
								<%-- <label class="textarea"> <textarea
										name="emailContent" id="emailContent"
										data-width="100%" rows="10"
										placeholder="<spring:message code="NotificationCenter.Modal.Placeholder.Content"/>"></textarea>
									<b class="tooltip tooltip-bottom-right"><spring:message
											code="NotificationCenter.Modal.Tooltips.Content" /></b>  </label> --%>
								<div class="notificationContent">
									<div id="emailContent" class="note-editable custom-scroll" contenteditable="true" style="display:none;min-height:80px;"></div>
									<div id="forwardContent" readonly style="display:none;"></div>
									<div id="normalContent"></div>
								</div>			
								
							</section>
							</div>
						</fieldset>

						<footer>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" onclick="resetNotificationForm()">
								<spring:message code="Button.Cancel"/>
							</button>
							<button type="submit" class="btn btn-primary ladda-button"
								id="sendBtn" style="display:none;" data-style="expand-right"
								onclick="setNotificationType(this)">
								<spring:message code="Button.Send" />&nbsp;
								<i class="fa fa-arrow-circle-right fa-lg"></i> 
							</button>
							<button type="button" class="btn btn-primary" id="forwardBtn"
							          >
								<i class="fa fa-share"></i> &nbsp;
								<spring:message code="Button.Forward" />
							</button>

						</footer>

					</form>
				</div>


			</div>
		</div>
	</div>
	<!-- END MODAL -->

</div>
<!-- Datatable code starts -->

<!-- widget grid -->
<section id="widget-grid" class="">

	<!-- row -->
	<div class="row">

		<!-- NEW WIDGET START -->
		<article
			class="col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
			<div
				class="jarviswidget jarviswidget-color-darken jarviswidget-sortable"
				id="wid-id-0" data-widget-editbutton="false" role="widget" style="">
				<header role="heading">
					<div class="jarviswidget-ctrls" role="menu">
						<a href="javascript:void(0);"
							class="button-icon jarviswidget-toggle-btn" rel="tooltip"
							title="" data-placement="bottom" data-original-title="Collapse"><i
							class="fa fa-minus "></i></a> <a href="javascript:void(0);"
							class="button-icon jarviswidget-fullscreen-btn" rel="tooltip"
							title="" data-placement="bottom" data-original-title="Fullscreen"><i
							class="fa fa-expand "></i></a> 
					</div>
					
				</header>

				<!-- widget div-->
				<div role="content">

					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->

					</div>
					<!-- end widget edit box -->

					<!-- widget content -->
					<div class="widget-body no-padding">
                      
						<div id="dt_basic_wrapper"
							class="dataTables_wrapper form-inline no-footer">
							
								<div class="notificationContent custom-scroll" id="notificationCenterLists" style="height:400px; overflow-y: scroll;"></div>
						</div>

					</div>
					<!-- end widget content -->

				</div>
				
				
				<!-- end widget div -->

			</div>
			<!-- Till Here -->


		</article>
		<!-- WIDGET END -->

	</div>

	<!-- end row -->

	<!-- end row -->

</section>
<!-- end widget grid -->
