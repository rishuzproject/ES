<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- #MAIN CONTENT -->
<style>
th.ui-th-column div {
    white-space: normal !important;
    height: auto !important;
    padding: 2px;
}
.ui-jqgrid .ui-jqgrid-sdiv .footrow {
	height: 30px
}

.ui-corner-all {
	border-radius: 0px !important;
}

.ui-jqgrid .ui-jqgrid-htable th div {
	height: 50px !important;
}
/* css start for comment div   */
.profile-user-info-striped {
	border: 1px solid #DCEBF7;
}

.profile-user-info {
	display: table;
	width: 98%;
	width: calc(100% - 24px);
	margin: 0 auto;
}

.profile-info-row {
	display: table-row;
}

.profile-info-row:first-child .profile-info-name,.profile-info-row:first-child .profile-info-value
	{
	border-top: none;
}

.profile-user-info-striped .profile-info-value {
	border-top: 1px dotted #DCEBF7;
	padding-left: 12px;
}

.profile-info-value {
	display: table-cell;
	padding: 6px 4px 6px 6px;
	border-top: 1px dotted #D5E4F1;
}

a {
	color: #428bca;
	text-decoration: none;
}
/* css end for comment div   */

/* css start for scroller */
.ui-jqgrid-bdiv::-webkit-scrollbar,.custScroller::-webkit-scrollbar
	{
	height: 9px;
	width: 9px;
	background-color: white;
	border: 1px solid lightgrey;
}
.ui-jqgrid-bdiv::-webkit-scrollbar-thumb,.custScroller::-webkit-scrollbar-thumb
	{
	background-color: #428bca;
}
.ui-jqgrid-bdiv::-webkit-scrollbar-thumb:hover,.custScroller::-webkit-scrollbar-thumb:hover
	{
	background-color: #428bca;
}
.ui-jqgrid-bdiv::-webkit-scrollbar-thumb:active,.custScroller::-webkit-scrollbar-thumb:active
	{
	background-color: #428bca;
}
/* css end for scroller */
.badgeTimes {
padding: 3px 5px !important;
}
.badgeCheck {
padding: 3px 3px !important;
}

</style>
<link href="assets/css/ui.jqgrid.css" rel="stylesheet">
<link href="assets/css/jquery-ui-redmond.css" rel="stylesheet">

<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-gear fa-fw txt-color-blue"></i> <spring:message code="Sov.PageHead.Heading" /> <span>
				&gt; <spring:message code="Sov.PageHead.SubHeading" /> </span>
		</h1>
	</div>
	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
		<ul id="sparks" class="">
			<li class="sparks-info">
				<h5>
					<spring:message code="Sov.PageHead.TotalActiveItemsSov" /> <span class="txt-color-blue" id="totalItemsInSOV"></span>
				</h5>
			</li>
		</ul>
	</div>
</div>

<div class="modal fade" id="sovItemRejectionModal" tabindex="-1"
	role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true"  data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 496px !important;">
		<div class="modal-content">
			<div class="modal-header" style="padding: 10px !important;">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" onclick="resetSovItemRejectionForm()">
					<span class="badge pull-right inbox-badge customColor"><spring:message code="Sov.Button.CancelText" /></span>
				</button>
				<h4 class="modal-title" id="rejectionModalTitleId"><i class="fa fa-times fa-fw txt-color-red"></i> <spring:message code="Sov.Modal.RejectTitle" /></h4>
			</div>
			<div class="modal-body no-padding">

				<form id="sovItemRejectionForm" class="smart-form" method="POST">
					<fieldset>
						<input type="hidden" name="itemNo" id="itemNo">
						<section class="col col-md-12">
							<label class="textarea"> <i
								class="icon-append fa fa-comment"></i> <textarea
									name="approvalComments" id="approvalComments"
									class="form-control" rows="4" placeholder="Comments with respect to rejection"></textarea>
								<b class="tooltip tooltip-bottom-right"><spring:message code="Sov.formLabelTooltip.EnterComment" /></b>

							</label>
						</section>

					</fieldset>

					<footer style="padding: 0px 14px 10px !important;">
						<button type="button" class="btn btn-default" data-dismiss="modal"
							onclick="resetSovItemRejectionForm()">
							<spring:message code="Button.Cancel" />
						</button>
						<button type="submit" class="btn btn-primary ladda-button"
							data-style="expand-right" id="reject">
							<i class="fa fa-floppy-o"></i> &nbsp;
							<spring:message code="Button.Reject" />
						</button>
					</footer>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- END MODAL -->

<div class="modal fade" id="sovItemApprovalModal" tabindex="-1"
	role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true"  data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 496px !important;">
		<div class="modal-content">
			<div class="modal-header" style="padding: 10px !important;">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" onclick="resetSovItemApprovalForm()">
					<span class="badge pull-right inbox-badge customColor"><spring:message code="Sov.Button.CancelText" /></span>
				</button>
				<h4 class="modal-title" id="approvalModalTitleId"><i class="fa fa-check fa-fw txt-color-blue"></i> <spring:message code="Sov.Modal.ApproveTitle" /></h4>
			</div>
			<div class="modal-body no-padding">

				<form id="sovItemApprovalForm" class="smart-form" method="POST">
					<fieldset>
						<input type="hidden" name="approvedItemId" id="approvedItemId">
						<section class="col col-md-12">
							<label class="textarea"> <i
								class="icon-append fa fa-comment"></i> <textarea
									name="approvalComment" id="approvalComment"
									class="form-control" rows="4" placeholder="Approval comment"></textarea>
								<b class="tooltip tooltip-bottom-right"><spring:message code="Sov.formLabelTooltip.EnterComment" /></b>

							</label>
						</section>

					</fieldset>

					<footer style="padding: 0px 14px 10px !important;">
						<button type="button" class="btn btn-default" data-dismiss="modal"
							onclick="resetSovItemApprovalForm()">
							<spring:message code="Button.Cancel" />
						</button>
						<button type="submit" class="btn btn-primary ladda-button"
							data-style="expand-right" id="reject">
							<i class="fa fa-floppy-o"></i> &nbsp;
							<spring:message code="Button.Approve" />
						</button>
					</footer>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- END MODAL -->

<!-- Customer Approval modal  -->
<div class="modal fade" id="customerApprovalModal" tabindex="-1"
	role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true"  data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" onclick="">
					<span class="badge pull-right inbox-badge customColor"><spring:message code="Sov.Button.CancelText" /></span>
				</button>
				<h4 class="modal-title" id="customerApprovalModalTitleId"><spring:message code="Sov.Modal.ApproveTitle" /></h4>
			</div>
			<div class="modal-body no-padding">

				<form id="customerApprovalForm" class="smart-form" method="POST">
					<fieldset>
						<section>
							<div class="col col-lg-12">
								<label class="input"> <label><spring:message code="Sov.EmailFormLabels.To" /></label><input type="text"
									class="form-control" name="cutomerApprovalEmailTo"
									id="cutomerApprovalEmailTo"/> <b
									class="tooltip tooltip-bottom-right"><spring:message code="Sov.EmailFormLabelsTooltip.To" /></b>
								</label><br>
							</div>
						</section>
						<section>
							<div class="col col-lg-12">
								<label class="input"> <label><spring:message code="Sov.EmailFormLabels.Cc" /></label> <input type="text"
									class="form-control" name="cutomerApprovalEmailCc"
									id="cutomerApprovalEmailCc" /> <b
									class="tooltip tooltip-bottom-right"><spring:message code="Sov.EmailFormLabelsTooltip.Cc" /></b>
								</label><br>
							</div>
						</section>
						<section>
							<div class="col col-lg-12">
								<label class="input"> <label><spring:message code="Sov.EmailFormLabels.Bcc" /></label><input type="text"
									class="form-control" name="cutomerApprovalEmailBcc"
									id="cutomerApprovalEmailBcc"  /> <b
									class="tooltip tooltip-bottom-right"><spring:message code="Sov.EmailFormLabelsTooltip.Bcc" /></b>
								</label><br>
							</div>
						</section>
						<section>
							<div class="col col-lg-12">
								<label class="input"> <label><spring:message code="Sov.EmailFormLabels.LinkActivationDuration" /></label><input type="text"
									class="form-control" name="emailLinkActivationDuration"
									id="emailLinkActivationDuration" /> <b
									class="tooltip tooltip-bottom-right"><spring:message code="Sov.EmailFormLabelsTooltip.LinkActivationDuration" /></b>
								</label><br>
							</div>
						</section>
						<section class="col col-md-12">
							<label class="textarea"> <label><spring:message code="Sov.EmailFormLabels.EmailBody" /></label><i
								class="icon-append fa fa-comment"></i> <textarea
									name="customerApprovalEmailBody" id="customerApprovalEmailBody"
									class="form-control" rows="10" ></textarea> <b
								class="tooltip tooltip-bottom-right"><spring:message code="Sov.EmailFormLabelsTooltip.EmailBody" /></b>
							</label>
						</section>

					</fieldset>

					<footer>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							onclick="">
							<spring:message code="Button.Cancel" />
						</button>
						<button type="submit" class="btn btn-primary ladda-button"
							data-style="expand-right" id="submitCustomerApproval">
							<i class="fa fa-floppy-o"></i> &nbsp;
							<spring:message code="Button.Approve" />
						</button>
					</footer>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- END MODAL -->

<div>
	<button type="submit" class="btn btn-primary ladda-button"
		data-style="expand-right" id="requestForApproval"
		onclick="sendRequestForApproval()">
		<i class="fa fa-floppy-o"></i> &nbsp; <spring:message code="Sov.Action.ApprovalRequest" />
	</button>

	<button type="submit" class="btn btn-primary ladda-button"
		data-style="expand-right" id="sendForCustomerApproval"
		onclick="requestCustApproval()" style="display: none">
		<i class="fa fa-floppy-o"></i> &nbsp; <spring:message code="Sov.Action.CustomerApprovalRequest" />
	</button>
</div>

<section id="widget-grid" class="">
	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
			
		<div class="jarviswidget jarviswidget-color-darken jarviswidget-sortable" id="wid-id-0" data-widget-editbutton="false" role="widget" style="">
				<header role="heading">
				   <h2 style="color: #E4E4E4;">
							<spring:message code="Sov.TableListingHeading.SovItemDetails" />
						</h2>
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

				<div role="content" >
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
					</div>
					<div class="widget-body no-padding">
							<div id="jqg">
								<table id="jQGridDemo"></table>
								<div id="pager"></div>
							</div>
							<br>
						
						
						<div class="col-md-12" style="padding-bottom: 10px;">
						<div style="overflow: scroll;max-height: 268px;overflow-x: hidden;" class="custScroller">
						<div class="profile-user-info profile-user-info-striped custFind" id="commentsDivId">
											</div>
								</div>			
							<div class="profile-user-info commentField hide" >
							<textarea class="form-control" style="resize: none" rows="4"
								id="commentTextArea" name="" placeholder="Add Comment"></textarea>
								<br><br>
							<button type="button" class="btn btn-white btn-sm btn-primary"
								onclick="setCommentToAddForSOV();">
								<i class="ace-icon fa fa-plus"></i> <spring:message code="Sov.TableButtons.Add" />
							</button>
							<button type="button"
								class="btn btn-white btn-sm btn-primary hideCommentFieldBtn">
								<i class="ace-icon fa fa-times"></i> <spring:message code="Sov.TableButtons.Cancel" />
							</button>
						</div>
										
						<div class="profile-user-info" >					
						  <button type="button" class="btn btn-white btn-sm btn-primary commentBtn">
							<i class="ace-icon fa fa-comment bigger-110"></i> <spring:message code="Sov.TableButtons.Comment" />
						  </button>
						</div>
					</div>
				</div>	
			</div>
		</div>
	</article>
</div>
</section>				
<script type="text/javascript" src="assets/js/grid.locale-en.js"></script>
<script type="text/javascript" src="assets/js/jquery.jqGrid.js"></script>
<script type="text/javascript" src="assets/js/sovTable.js"></script>
<script type="text/javascript" src="assets/js/jquery.jqGrid.min.js"></script>
<!-- END #MAIN CONTENT -->