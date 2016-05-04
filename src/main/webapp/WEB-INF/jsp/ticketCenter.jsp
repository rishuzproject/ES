
<!-- #MAIN CONTENT -->
<script type="text/javascript" src="assets/js/ticketCenter.js"></script>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa  fa-ticket txt-color-blue"></i> Ticket Center <span>&gt;
				Manage Issues </span>
		</h1>
	</div>
	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
		<ul id="sparks" class="">
			<li class="sparks-info">
				<h5>
					Total Issues <span class="txt-color-blue">171</span>
				</h5>
			</li>
			<li class="sparks-info">
				<h5>
					Total Resolved <span class="txt-color-blue">100</span>
				</h5>
			</li>
			<li class="sparks-info">
				<h5>
					Avg. Resolvation Time <span class="txt-color-blue">100</span>
				</h5>
			</li>
			<li class="sparks-info">
				<h5>
					Waiting Cust Res <span class="txt-color-blue">50</span>
				</h5>
			</li>
		</ul>
	</div>
</div>
<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">

	<!-- Button trigger modal -->


	<!-- MODAL PLACE HOLDER -->
	<div class="modal fade" id="reportIssueRemoteModal" tabindex="-1"
		role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true"  data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">


				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick="resetReportIssueForm()"><span class="badge pull-right inbox-badge customColor">&times;</span></button>
					<h4 class="modal-title"><i class="fa  fa-ticket txt-color-blue"></i> Raise A Ticket</h4>
				</div>
				<div class="modal-body no-padding">

					<form id="report_issue_form" action="reportIssueAction"
						method="POST" class="smart-form">
						<fieldset>

							<div class="row">
								<section class="col col-6">
									<label class="input"> <select
										class="form-control selectpicker show-tick" id="productName"
										name="productName">

											<option value="">--Select Product--</option>
											<option value="ecosystem">Ecosystem</option>
											<option value="fts">FTS</option>
											<option value="mpr">MPR</option>
											<option value="pd">PD</option>
									</select>
									</label>

								</section>
								<section class="col col-6">
									<label class="input"> <select
										class="form-control selectpicker show-tick" id="moduleName"
										name="moduleName">

											<option value="">--Select Module--</option>
									</select>
									</label>
								</section>
							</div>
							<div class="row">

								<section class="col col-6">
									<label class="input"> <select
										class="form-control selectpicker show-tick" id="projectName"
										name="projectName">

											<option value="">--Select Project--</option>
											<option value="Project1">Project1</option>
											<option value="Project2">Project2</option>
											<option value="Project3">Project3</option>
											<option value="Project4">Project4</option>
											<option value="Project5">Project5</option>
									</select>
									</label>

								</section>
								<section class="col col-6">
									<label class="input"> <select
										class="form-control selectpicker show-tick" id="severity"
										name="severity">

											<option value="">--Select Severity--</option>
											<option value="high">High</option>
											<option value="moderate">Moderate</option>
											<option value="low">Low</option>
									</select>
									</label>
								</section>
							</div>
							<div class="row">
								<section>
									<div class="col col-lg-12">
										<label class="textarea"> <i
											class="icon-append fa fa-comment"></i> <textarea cols="4"
												maxlength="2000" name="description" id="description"
												placeholder="Description..."></textarea> <b
											class="tooltip tooltip-bottom-right">Enter the
												description</b> <font color="red" size="1px"><p
													align="right">(max : 2000 characters)</p></font><br>
										</label>
									</div>
								</section>
							</div>
							<div class="row">
								<section>
									<div class="col col-lg-12">
										<label class="textarea"> <i
											class="icon-append fa fa-comment"></i> <textarea cols="4"
												maxlength="2000" name="additionalComments"
												id="additionalComments" placeholder="Additional Comments..."></textarea>
											<b class="tooltip tooltip-bottom-right">Enter additional
												comments</b> <font color="red" size="1px"><p
													align="right">(max : 2000 characters)</p></font><br>
										</label>
									</div>
								</section>
							</div>
							<section>
         						<label for="file" class="input input-file">
          							<div class="button"><input type="file" name="file" id="file" onchange="this.parentNode.nextSibling.value = this.value">Browse</div><input type="text" name="fileName" id="fileName" placeholder="Choose the Document to Upload" readonly="">
         						</label>
       					 	</section>

						</fieldset>

						<footer>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" onclick="resetReportIssueForm()">Cancel</button>
							<button type="button" class="btn btn-primary" id="reset"
								onclick="resetReportIssueForm()">
								<i class="fa fa-undo"></i> &nbsp;Reset
							</button>
							<button type="submit" class="btn btn-primary" id="raiseTicket">
								<i class="fa fa-list-alt fa-2"></i>&nbsp; Raise Ticket
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
							class="fa fa-expand "></i></a> <a href="javascript:void(0);"
							class="button-icon jarviswidget-delete-btn" rel="tooltip"
							title="" data-placement="bottom" data-original-title="Delete"><i
							class="fa fa-times"></i></a>
					</div>
					<div class="widget-toolbar" role="menu">
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
					</div>
					&nbsp; <a href="" data-toggle="modal"
						data-target="#reportIssueRemoteModal"> <span
						class="widget-icon" style="color: #E4E4E4;"> <i
							class="fa fa-plus-square"></i>
					</span>
						<h2 style="color: #E4E4E4;">Raise A Ticket</h2>
					</a> <span class="jarviswidget-loader"><i
						class="fa fa-refresh fa-spin"></i></span>
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
							<table id="reportedIssuesTable"
								class="table table-striped table-bordered table-hover dataTable no-footer"
								width="100%" role="grid" aria-describedby="dt_basic_info"
								style="width: 100%;">
								<thead>
									<tr role="row">
										<th data-hide="phone" class="sorting_asc" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-sort="ascending"
											aria-label="ID: activate to sort column ascending"
											style="width: 41px;" class="center">ID</th>
										<th data-hide="phone" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-sort="ascending"
											aria-label="Product: activate to sort column ascending"
											style="width: 90px;" class="center"><i
											class="icon-append fa fa-sort-alpha-asc"></i> &nbsp; Product</th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Module: activate to sort column ascending"
											style="width: 150px;"><i
											class="icon-append fa fa-sort-alpha-asc"></i> &nbsp; Module</th>
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="Project: activate to sort column ascending"
											style="width: 150px;"><i
											class="icon-append fa fa-file-text"></i> &nbsp;Project</th>
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="Severity: activate to sort column ascending"
											style="width: 90px;"><i
											class="icon-append fa fa-asterisk "></i> &nbsp; Severity</th>
										<th data-hide="phone" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Description: activate to sort column ascending"
											style="width: 200px;"><i
											class="icon-append fa fa-comment"></i>&nbsp; Description</th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Module Name: activate to sort column ascending"
											style="width: 150px;"><i class="icon-append fa fa-user"></i>
											&nbsp; Reported By</th>
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="Reported Date: activate to sort column ascending"
											style="width: 150px;"><i
											class="fa fa-fw fa-calendar txt-color-blue hidden-md hidden-sm hidden-xs"></i>&nbsp;
											Reported Date</th>
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="Reported Date: activate to sort column ascending"
											style="width: 150px;"><i
											class="fa fa-fw fa-calendar txt-color-blue hidden-md hidden-sm hidden-xs"></i>&nbsp;
											Resolved Date</th>
									</tr>
								</thead>

								<tbody>
									<tr>
										<td class="center">1</td>
										<td class="center">MPR</td>
										<td class="center">Projects</td>
										<td class="center">McKinley School</td>
										<td class="center">High</td>
										<td class="center">description......</td>
										<td class="center">Raju</td>
										<td class="center">26-May-2014</td>
										<td class="center">27-May-2014</td>
									</tr>
									<tr>
										<td class="center">2</td>
										<td class="center">FTS</td>
										<td class="center">Purchase Directive</td>
										<td class="center">Demo_PD</td>
										<td class="center">Moderate</td>
										<td class="center">description.....</td>
										<td class="center">Rahul</td>
										<td class="center">5-July-2014</td>
										<td class="center">7-July-2014</td>
									</tr>
									<tr>
										<td class="center">3</td>
										<td class="center">PD</td>
										<td class="center">Ficture</td>
										<td class="center">Demo Ficture</td>
										<td class="center">Low</td>
										<td class="center">description.....</td>
										<td class="center">Sanju</td>
										<td class="center">10-Aug-2014</td>
										<td class="center">15-Aug-2014</td>
									</tr>
									<tr>
										<td class="center">4</td>
										<td class="center">Ecosystem</td>
										<td class="center">Budget Form</td>
										<td class="center">mat code1</td>
										<td class="center">Moderate</td>
										<td class="center">description.....</td>
										<td class="center">Prakash</td>
										<td class="center">22-Aug-2014</td>
										<td class="center">25-Aug-2014</td>
									</tr>
								</tbody>
							</table>

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

<!-- END #MAIN CONTENT -->