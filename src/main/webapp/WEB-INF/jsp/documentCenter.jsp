<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script type="text/javascript" src="assets/js/documentCenter.js"></script>

<!-- #MAIN CONTENT -->

<!-- Download Document Form -->
<form action="downloadDocumentById" id="documentCenterDownload" method="GET"
	name="documentCenterDownload" style="display: none">
	<input type="hidden" name="fileId" id="fileId" />
</form>

<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa  fa-file-text txt-color-blue"></i>
			<spring:message code="DocumentCenter.PageHead.Heading" />
			<span>&gt; <spring:message
					code="DocumentCenter.PageHead.SubHeading" />
			</span>
		</h1>
	</div>
	<div class="col-xs-10 col-sm-5 col-md-5 col-lg-8">
		<ul id="sparks" class="">
			<li class="sparks-info">
				<h5>
					<spring:message code="DocumentCenter.PageHead.TotalDocuments" />
					<span class="txt-color-blue" id="totalDocumentsId"> </span>
				</h5>
			</li>
		</ul>
	</div>
</div>
<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">

	<!-- Button trigger modal -->


	<!-- MODAL PLACE HOLDER -->
	<div class="modal fade" id="documentCenterRemoteModal" tabindex="-1"
		role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true"  data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">


				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						<span class="badge pull-right inbox-badge customColor">&times;</span>
					</button>
					<h4 class="modal-title">
						<i class="fa  fa-file-text txt-color-blue"></i> &nbsp;
						<spring:message code="DocumentCenter.Form.AddNewDocument" />
					</h4>
				</div>
				<div class="modal-body no-padding">

					<form id="addDocumentForm" method="POST" class="smart-form">
						<input type="hidden" name="documentCenterId" id="documentCenterId">
						<input type="hidden" name="docActionType" id="docActionType">

						<fieldset>
							<section class="11">
								<label class="input"> <i
									class="icon-append fa fa-database mandatoryStyle"></i> <input type="text"
									name="documentName" id="documentName"
									placeholder="<spring:message code="DocumentCenter.label.DocumentName"/>">
									<b class="tooltip tooltip-bottom-right"><spring:message
											code="DocumentCenter.tooltip.DocumentName" /></b>
								</label>
							</section>
							<section class="11">
								<label class="textarea"><i
									class="icon-append fa fa-comment mandatoryStyle"></i> <textarea
										name="documentDescription" id="documentDescription"
										data-width="100%" maxlength="2000"
										placeholder="<spring:message code="DocumentCenter.label.DocumentDescription"/>"></textarea>
									<b class="tooltip tooltip-bottom-right"><spring:message
											code="DocumentCenter.tooltip.DocumentDescription" /></b> <font
									color="red" size="1px"><p align="right">
											<spring:message
												code="DocumentCenter.label.maxlength.DocumentDescription" />
										</p></font> </label>
							</section>
							<section>
								<label for="file" class="input input-file">
									<div class="button">
										<input type="file" name="fileUploaded" id="fileUploaded"
											onchange="checkFileSize(this);">
										<spring:message code="DocumentCenter.label.button.Browse" />
									</div><input type="text" name="fileName" id="fileName"
									placeholder="<spring:message code="DocumentCenter.label.Browse"/>"
									readonly="">
								</label>
							</section>
						</fieldset>

						<footer>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" onclick="resetApplicationForm()">
								<spring:message code="Button.Cancel" />
							</button>
							<button type="button" class="btn btn-primary" id="reset"
								onClick="resetApplicationForm()">
								<i class="fa fa-undo"></i> &nbsp;
								<spring:message code="Button.Reset" />
							</button>
							<button type="submit" class="btn btn-primary ladda-button" data-style="expand-right"
								id="saveandcontinue" onclick="setDocActionType(this.id,this);">
								<i class="fa fa-forward"></i> &nbsp;
								<spring:message code="Button.SaveContinue" />
							</button>
							<button type="submit" class="btn btn-primary ladda-button" id="save"
							          data-style="expand-right" onclick="setDocActionType(this.id,this);">
								<i class="fa fa-floppy-o"></i> &nbsp;
								<spring:message code="Button.Save" />
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
							class="fa fa-expand "></i></a> <!-- <a href="javascript:void(0);"
							class="button-icon jarviswidget-delete-btn" rel="tooltip"
							title="" data-placement="bottom" data-original-title="Delete"><i
							class="fa fa-times"></i></a> -->
					</div>
					<!-- <div class="widget-toolbar" role="menu">
						<a data-toggle="dropdown"
							class="dropdown-toggle color-box selector"
							href="javascript:void(0);"></a>
						<ul
							class="dropdown-menu arrow-box-up-right color-select pull-right">
							<li><span class="bg-color-green"
								data-widget-setstyle="jarviswidget-color-green" rel="tooltip"
								data-placement="left" data-original-title="Green Grass"></span></li>
							<li><span class="bg-color-greenDark"
								data-widget-setstyle="jarviswidget-color-greenDark"
								rel="tooltip" data-placement="top"
								data-original-title="Dark Green"></span></li>
							<li><span class="bg-color-greenLight"
								data-widget-setstyle="jarviswidget-color-greenLight"
								rel="tooltip" data-placement="top"
								data-original-title="Light Green"></span></li>
							<li><span class="bg-color-purple"
								data-widget-setstyle="jarviswidget-color-purple" rel="tooltip"
								data-placement="top" data-original-title="Purple"></span></li>
							<li><span class="bg-color-magenta"
								data-widget-setstyle="jarviswidget-color-magenta" rel="tooltip"
								data-placement="top" data-original-title="Magenta"></span></li>
							<li><span class="bg-color-pink"
								data-widget-setstyle="jarviswidget-color-pink" rel="tooltip"
								data-placement="right" data-original-title="Pink"></span></li>
							<li><span class="bg-color-pinkDark"
								data-widget-setstyle="jarviswidget-color-pinkDark" rel="tooltip"
								data-placement="left" data-original-title="Fade Pink"></span></li>
							<li><span class="bg-color-blueLight"
								data-widget-setstyle="jarviswidget-color-blueLight"
								rel="tooltip" data-placement="top"
								data-original-title="Light Blue"></span></li>
							<li><span class="bg-color-teal"
								data-widget-setstyle="jarviswidget-color-teal" rel="tooltip"
								data-placement="top" data-original-title="Teal"></span></li>
							<li><span class="bg-color-blue"
								data-widget-setstyle="jarviswidget-color-blue" rel="tooltip"
								data-placement="top" data-original-title="Ocean Blue"></span></li>
							<li><span class="bg-color-blueDark"
								data-widget-setstyle="jarviswidget-color-blueDark" rel="tooltip"
								data-placement="top" data-original-title="Night Sky"></span></li>
							<li><span class="bg-color-darken"
								data-widget-setstyle="jarviswidget-color-darken" rel="tooltip"
								data-placement="right" data-original-title="Night"></span></li>
							<li><span class="bg-color-yellow"
								data-widget-setstyle="jarviswidget-color-yellow" rel="tooltip"
								data-placement="left" data-original-title="Day Light"></span></li>
							<li><span class="bg-color-orange"
								data-widget-setstyle="jarviswidget-color-orange" rel="tooltip"
								data-placement="bottom" data-original-title="Orange"></span></li>
							<li><span class="bg-color-orangeDark"
								data-widget-setstyle="jarviswidget-color-orangeDark"
								rel="tooltip" data-placement="bottom"
								data-original-title="Dark Orange"></span></li>
							<li><span class="bg-color-red"
								data-widget-setstyle="jarviswidget-color-red" rel="tooltip"
								data-placement="bottom" data-original-title="Red Rose"></span></li>
							<li><span class="bg-color-redLight"
								data-widget-setstyle="jarviswidget-color-redLight" rel="tooltip"
								data-placement="bottom" data-original-title="Light Red"></span></li>
							<li><span class="bg-color-white"
								data-widget-setstyle="jarviswidget-color-white" rel="tooltip"
								data-placement="right" data-original-title="Purity"></span></li>
							<li><a href="javascript:void(0);"
								class="jarviswidget-remove-colors" data-widget-setstyle=""
								rel="tooltip" data-placement="bottom"
								data-original-title="Reset widget color to default">Remove</a></li>
						</ul>
					</div> -->
					&nbsp; <a href="" data-toggle="modal"
						data-target="#documentCenterRemoteModal"> <span
						class="widget-icon" style="color: #E4E4E4;"> <i
							class="fa fa-plus-square"></i>
					</span>
						<h2 style="color: #E4E4E4;">
							<spring:message code="DocumentCenter.Form.AddNewDocument" />
						</h2>
					</a> <span class="jarviswidget-loader"><i
						class="fa fa-refresh fa-spin"></i></span>
				</header>

				<!-- widget div-->
				<div role="content" class="no-padding">

					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->

					</div>
					<!-- end widget edit box -->

					<!-- widget content -->
					<div class="alert alert-block alert-success" style="  margin-bottom: 0px;">
						<h4 class="alert-heading">
							<i class="fa fa-check-square-o"></i>
							<spring:message code="DocumentCenter.Data.line1" />
						</h4>
						<p>
							<spring:message code="DocumentCenter.Data.line2" />
							</br>
							<spring:message code="DocumentCenter.Data.line3" />
						</p>
					</div>

					<div id="dt_basic_wrapper"
						class="dataTables_wrapper form-inline no-footer">
						<table id="documentCenterTable"
							class="table table-striped table-bordered table-hover dataTable no-footer"
							width="100%" role="grid" aria-describedby="dt_basic_info"
							style="width: 100%;">
							<thead>
								<tr role="row">

									<th data-class="expand" class="sorting" tabindex="0"
										aria-controls="dt_basic" rowspan="1" colspan="1"
										aria-label=" Name: activate to sort column ascending"
										style="width: 30px;">
										#</th>
									<th data-class="expand" class="sorting" tabindex="0"
										aria-controls="dt_basic" rowspan="1" colspan="1"
										aria-label=" Name: activate to sort column ascending"
										style="width: 60px;"><i
										class="fa fa-fw fa-database text-muted hidden-md hidden-sm hidden-xs"></i>
										<spring:message
											code="DocumentCenter.DataTable.TableHead.Column2" /></th>
									<th data-hide="phone,tablet" class="sorting" tabindex="0"
										aria-controls="dt_basic" rowspan="1" colspan="1"
										aria-label=" Date: activate to sort column ascending"
										style="width: 140px;"><i
										class="fa fa-fw fa-comment hidden-md hidden-sm hidden-xs"></i>
										<spring:message
											code="DocumentCenter.DataTable.TableHead.Column3" /></th>
									<th data-hide="phone,tablet" class="sorting" tabindex="0"
										aria-controls="dt_basic" rowspan="1" colspan="1"
										aria-label=" : activate to sort column ascending"
										style="width: 10px;"></th>

								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>

					</div>

				</div>
				<!-- end widget content -->

			</div>
			<!-- end widget div -->
		</article>
		<!-- WIDGET END -->
	</div>
	<!-- Till Here -->

	<!-- WIDGET END -->


	<!-- end row -->

	<!-- end row -->

</section>
<!-- end widget grid -->

<script type="text/javascript">
	
</script>


<!-- END #MAIN CONTENT -->