<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- #MAIN CONTENT -->
<style>
.nav-tabs>li.active>a{background: #F0F1F1 !important}
</style>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-6">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-gear fa-fw txt-color-blue"></i>
			<spring:message code="ApplicationAndPlan.Pagehead.Heading" />
			<span> &gt; <spring:message
					code="ApplicationAndPlan.Pagehead.SubHeading" />
			</span>
		</h1>
	</div>

	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-6">
		<ul id="sparks" class="">
			<li class="sparks-info">
				<h5>
					<spring:message code="ApplicationAndPlan.Pagehead.TotalApplication" />
					<span class="txt-color-blue" id="totalApplication"></span>
				</h5>
			</li>
			<li class="sparks-info">
				<h5>
					<spring:message code="ApplicationAndPlan.Pagehead.TotalPlan" />
					<span class="txt-color-blue" id="totalPlan"></span>
				</h5>
			</li>
		</ul>
	</div>
</div>

<div role="content">

	<!-- widget edit box -->
	<div class="jarviswidget-editbox">
		<!-- This area used as dropdown edit box -->

	</div>
	<!-- end widget edit box -->

	<!-- widget content -->
	<div class="widget-body">
<!-- <hr class="simple"> -->
		<ul id="myTab1" class="nav nav-tabs bordered" style="background: #E7E7E7">
			<li class="active"><a href="#s1" data-toggle="tab"><spring:message
						code="ApplicationAndPlan.Tabs.Tab1" /> <i
					class="fa fa-fw fa-lg fa-font"></i> </a></li>
			<li class=""><a href="#s2" data-toggle="tab"><spring:message
						code="ApplicationAndPlan.Tabs.Tab2" /> <i
					class="fa fa-fw fa-lg fa-edit"></i></a></li>
		</ul>

		<div id="myTabContent1" class="tab-content no-padding" style="padding-top: 10px !important">
			<div class="tab-pane active" id="s1">


				<!-- widget grid -->
				<section id="widget-grid" class="">

					<!-- row -->
					<div class="row">

						<!-- NEW WIDGET START -->
						<article
							class="col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
							<div
								class="jarviswidget jarviswidget-color-darken jarviswidget-sortable"
								id="wid-id-0" data-widget-editbutton="false" role="widget"
								style="">
								<header role="heading">
									<div class="jarviswidget-ctrls" role="menu">
										<a href="javascript:void(0);"
											class="button-icon jarviswidget-toggle-btn" rel="tooltip"
											title="" data-placement="bottom"
											data-original-title="Collapse"><i class="fa fa-minus "></i></a>
										<a href="javascript:void(0);"
											class="button-icon jarviswidget-fullscreen-btn" rel="tooltip"
											title="" data-placement="bottom"
											data-original-title="Fullscreen"><i class="fa fa-expand "></i></a>
										<!-- <a href="javascript:void(0);"
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
												data-widget-setstyle="jarviswidget-color-green"
												rel="tooltip" data-placement="left"
												data-original-title="Green Grass"></span></li>
											<li><span class="bg-color-greenDark"
												data-widget-setstyle="jarviswidget-color-greenDark"
												rel="tooltip" data-placement="top"
												data-original-title="Dark Green"></span></li>
											<li><span class="bg-color-greenLight"
												data-widget-setstyle="jarviswidget-color-greenLight"
												rel="tooltip" data-placement="top"
												data-original-title="Light Green"></span></li>
											<li><span class="bg-color-purple"
												data-widget-setstyle="jarviswidget-color-purple"
												rel="tooltip" data-placement="top"
												data-original-title="Purple"></span></li>
											<li><span class="bg-color-magenta"
												data-widget-setstyle="jarviswidget-color-magenta"
												rel="tooltip" data-placement="top"
												data-original-title="Magenta"></span></li>
											<li><span class="bg-color-pink"
												data-widget-setstyle="jarviswidget-color-pink" rel="tooltip"
												data-placement="right" data-original-title="Pink"></span></li>
											<li><span class="bg-color-pinkDark"
												data-widget-setstyle="jarviswidget-color-pinkDark"
												rel="tooltip" data-placement="left"
												data-original-title="Fade Pink"></span></li>
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
												data-widget-setstyle="jarviswidget-color-blueDark"
												rel="tooltip" data-placement="top"
												data-original-title="Night Sky"></span></li>
											<li><span class="bg-color-darken"
												data-widget-setstyle="jarviswidget-color-darken"
												rel="tooltip" data-placement="right"
												data-original-title="Night"></span></li>
											<li><span class="bg-color-yellow"
												data-widget-setstyle="jarviswidget-color-yellow"
												rel="tooltip" data-placement="left"
												data-original-title="Day Light"></span></li>
											<li><span class="bg-color-orange"
												data-widget-setstyle="jarviswidget-color-orange"
												rel="tooltip" data-placement="bottom"
												data-original-title="Orange"></span></li>
											<li><span class="bg-color-orangeDark"
												data-widget-setstyle="jarviswidget-color-orangeDark"
												rel="tooltip" data-placement="bottom"
												data-original-title="Dark Orange"></span></li>
											<li><span class="bg-color-red"
												data-widget-setstyle="jarviswidget-color-red" rel="tooltip"
												data-placement="bottom" data-original-title="Red Rose"></span></li>
											<li><span class="bg-color-redLight"
												data-widget-setstyle="jarviswidget-color-redLight"
												rel="tooltip" data-placement="bottom"
												data-original-title="Light Red"></span></li>
											<li><span class="bg-color-white"
												data-widget-setstyle="jarviswidget-color-white"
												rel="tooltip" data-placement="right"
												data-original-title="Purity"></span></li>
											<li><a href="javascript:void(0);"
												class="jarviswidget-remove-colors" data-widget-setstyle=""
												rel="tooltip" data-placement="bottom"
												data-original-title="Reset widget color to default">Remove</a></li>
										</ul>
									</div> -->
									&nbsp; <a href="" data-toggle="modal"
										data-target="#applicationModal"> <span class="widget-icon"
										style="color: #E4E4E4;"> <i class="fa fa-plus-square"></i>
									</span>
										<h2 style="color: #E4E4E4;">
											<spring:message
												code="ApplicationAndPlan.Form.AddNewApplication" />
										</h2>
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
											<table id="applicationDetailTable"
												class="table table-striped table-bordered table-hover dataTable no-footer"
												width="100%" role="grid" aria-describedby="dt_basic_info"
												style="width: 100%;">
												<thead>
													<tr role="row">
														<th data-class="expand" class="sorting" tabindex="0"
															aria-controls="dt_basic" rowspan="1" colspan="1"
															aria-label=" Name: activate to sort column ascending"
															style="width: 110px;"><spring:message
																code="ApplicationAndPlan.ApplicationDatatable.TableHead.Column1" /></th>
														<th data-class="expand" class="sorting" tabindex="0"
															aria-controls="dt_basic" rowspan="1" colspan="1"
															aria-label=" Name: activate to sort column ascending"
															style="width: 110px;"><i
															class="fa fa-fw fa-user text-muted hidden-md hidden-sm hidden-xs"></i>
															<spring:message
																code="ApplicationAndPlan.ApplicationDatatable.TableHead.Column2" /></th>
														<th data-class="expand" class="sorting" tabindex="0"
															aria-controls="dt_basic" rowspan="1" colspan="1"
															aria-label=" Name: activate to sort column ascending"
															style="width: 110px;"><i
															class="fa fa-fw fa-link text-muted hidden-md hidden-sm hidden-xs"></i>
															<spring:message
																code="ApplicationAndPlan.ApplicationDatatable.TableHead.Column3" /></th>
														<th data-hide="phone,tablet" class="sorting" tabindex="0"
															aria-controls="dt_basic" rowspan="1" colspan="1"
															aria-label=" Date: activate to sort column ascending"
															style="width: 97px;"><i
															class="fa fa-fw fa-calendar txt-color-blue hidden-md hidden-sm hidden-xs"></i>
															<spring:message
																code="ApplicationAndPlan.ApplicationDatatable.TableHead.Column4" /></th>
														<th data-hide="phone,tablet" class="sorting" tabindex="0"
															aria-controls="dt_basic" rowspan="1" colspan="1"
															aria-label=" Date: activate to sort column ascending"
															style="width: 10px;"></i></th>
												</thead>
												<tbody>


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
			</div>
			<div class="tab-pane" id="s2">
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
								id="wid-id-0" data-widget-editbutton="false" role="widget"
								style="">
								<header role="heading">
									<div class="jarviswidget-ctrls" role="menu">
										<a href="javascript:void(0);"
											class="button-icon jarviswidget-toggle-btn" rel="tooltip"
											title="" data-placement="bottom"
											data-original-title="Collapse"><i class="fa fa-minus "></i></a>
										<a href="javascript:void(0);"
											class="button-icon jarviswidget-fullscreen-btn" rel="tooltip"
											title="" data-placement="bottom"
											data-original-title="Fullscreen"><i class="fa fa-expand "></i></a>
										<!-- <a href="javascript:void(0);"
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
												data-widget-setstyle="jarviswidget-color-green"
												rel="tooltip" data-placement="left"
												data-original-title="Green Grass"></span></li>
											<li><span class="bg-color-greenDark"
												data-widget-setstyle="jarviswidget-color-greenDark"
												rel="tooltip" data-placement="top"
												data-original-title="Dark Green"></span></li>
											<li><span class="bg-color-greenLight"
												data-widget-setstyle="jarviswidget-color-greenLight"
												rel="tooltip" data-placement="top"
												data-original-title="Light Green"></span></li>
											<li><span class="bg-color-purple"
												data-widget-setstyle="jarviswidget-color-purple"
												rel="tooltip" data-placement="top"
												data-original-title="Purple"></span></li>
											<li><span class="bg-color-magenta"
												data-widget-setstyle="jarviswidget-color-magenta"
												rel="tooltip" data-placement="top"
												data-original-title="Magenta"></span></li>
											<li><span class="bg-color-pink"
												data-widget-setstyle="jarviswidget-color-pink" rel="tooltip"
												data-placement="right" data-original-title="Pink"></span></li>
											<li><span class="bg-color-pinkDark"
												data-widget-setstyle="jarviswidget-color-pinkDark"
												rel="tooltip" data-placement="left"
												data-original-title="Fade Pink"></span></li>
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
												data-widget-setstyle="jarviswidget-color-blueDark"
												rel="tooltip" data-placement="top"
												data-original-title="Night Sky"></span></li>
											<li><span class="bg-color-darken"
												data-widget-setstyle="jarviswidget-color-darken"
												rel="tooltip" data-placement="right"
												data-original-title="Night"></span></li>
											<li><span class="bg-color-yellow"
												data-widget-setstyle="jarviswidget-color-yellow"
												rel="tooltip" data-placement="left"
												data-original-title="Day Light"></span></li>
											<li><span class="bg-color-orange"
												data-widget-setstyle="jarviswidget-color-orange"
												rel="tooltip" data-placement="bottom"
												data-original-title="Orange"></span></li>
											<li><span class="bg-color-orangeDark"
												data-widget-setstyle="jarviswidget-color-orangeDark"
												rel="tooltip" data-placement="bottom"
												data-original-title="Dark Orange"></span></li>
											<li><span class="bg-color-red"
												data-widget-setstyle="jarviswidget-color-red" rel="tooltip"
												data-placement="bottom" data-original-title="Red Rose"></span></li>
											<li><span class="bg-color-redLight"
												data-widget-setstyle="jarviswidget-color-redLight"
												rel="tooltip" data-placement="bottom"
												data-original-title="Light Red"></span></li>
											<li><span class="bg-color-white"
												data-widget-setstyle="jarviswidget-color-white"
												rel="tooltip" data-placement="right"
												data-original-title="Purity"></span></li>
											<li><a href="javascript:void(0);"
												class="jarviswidget-remove-colors" data-widget-setstyle=""
												rel="tooltip" data-placement="bottom"
												data-original-title="Reset widget color to default">Remove</a></li>
										</ul>
									</div> -->
									&nbsp; <a href="" data-toggle="modal" data-target="#planModal">
										<span class="widget-icon" style="color: #E4E4E4;"> <i
											class="fa fa-plus-square"></i>
									</span>
										<h2 style="color: #E4E4E4;">
											<spring:message code="ApplicationAndPlan.Form.AddNewPlan" />
										</h2>
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
											<table id="applicationPurchasePlanTable"
												class="table table-striped table-bordered table-hover dataTable no-footer"
												width="100%" role="grid" aria-describedby="dt_basic_info"
												style="width: 100%;">
												<thead>
													<tr role="row">

														<th data-class="expand" class="sorting" tabindex="0"
															aria-controls="dt_basic" rowspan="1" colspan="1"
															aria-label=" Name: activate to sort column ascending"
															style="width: 10px;"><spring:message
																code="ApplicationAndPlan.PlanDatatable.TableHead.Column1" /></th>
														<th data-class="expand" class="sorting" tabindex="0"
															aria-controls="dt_basic" rowspan="1" colspan="1"
															aria-label=" Name: activate to sort column ascending"
															style="width: 110px;"><spring:message
																code="ApplicationAndPlan.PlanDatatable.TableHead.Column2" /></th>
														<th data-class="expand" class="sorting" tabindex="0"
															aria-controls="dt_basic" rowspan="1" colspan="1"
															aria-label=" Name: activate to sort column ascending"
															style="width: 110px;"><i
															class="fa fa-fw fa-comment text-muted hidden-md hidden-sm hidden-xs"></i>
															<spring:message
																code="ApplicationAndPlan.PlanDatatable.TableHead.Column3" /></th>
														<th data-hide="phone,tablet" class="sorting" tabindex="0"
															aria-controls="dt_basic" rowspan="1" colspan="1"
															aria-label=" Date: activate to sort column ascending"
															style="width: 97px;"><i
															class="fa fa-fw fa-usd txt-color-blue hidden-md hidden-sm hidden-xs"></i>
															<spring:message
																code="ApplicationAndPlan.PlanDatatable.TableHead.Column4" /></th>
														<th data-hide="phone,tablet" class="sorting" tabindex="0"
															aria-controls="dt_basic" rowspan="1" colspan="1"
															aria-label=" Date: activate to sort column ascending"
															style="width: 97px;"><i
															class="fa fa-fw fa-calendar txt-color-blue hidden-md hidden-sm hidden-xs"></i>
															<spring:message
																code="ApplicationAndPlan.PlanDatatable.TableHead.Column5" /></th>
														<th data-hide="phone,tablet" class="sorting" tabindex="0"
															aria-controls="dt_basic" rowspan="1" colspan="1"
															aria-label=" Date: activate to sort column ascending"
															style="width: 97px;"><i
															class="fa fa-fw fa-users txt-color-blue hidden-md hidden-sm hidden-xs"></i>
															<spring:message
																code="ApplicationAndPlan.PlanDatatable.TableHead.Column6" /></th>
														<th data-class="expand" class="sorting" tabindex="0"
															aria-controls="dt_basic" rowspan="1" colspan="1"
															aria-label=" Comp Name: activate to sort column ascending"
															style="width: 10px;"></th>
												</thead>
												<tbody>

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


			</div>


		</div>

	</div>
	<!-- end widget content -->

</div>


<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">

     <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">

					<!-- Button trigger modal -->


					<!-- MODAL PLACE HOLDER -->
					<div class="modal fade" id="planModal" tabindex="-1" role="dialog"
						aria-labelledby="remoteModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
						<div class="modal-dialog">
							<div class="modal-content">


								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true" onclick="resetPurchaseForm()">
										<span class="badge pull-right inbox-badge customColor">&times;</span>
									</button>

									<h4 class="modal-title" id="modalTitleId">
										<i class="fa fa-fw fa-lg fa-edit txt-color-blue"></i>
										<spring:message code="ApplicationAndPlan.Form.AddNewPlan" />
									</h4>
								</div>
								<div class="modal-body no-padding">

									<form id="managePurchaseForm" class="smart-form">

										<fieldset>
											<div class="row">
												<section class="col col-lg-12">
													<label class="input"> <select
														class="form-control selectpicker show-tick"
														id="applicationName" name="applicationName">
															<option value=""><spring:message
																	code="ApplicationAndPlan.AddNewPlan.selectPicker.ApplicationName" /></option>
<!-- 															<option value="2"></option> -->
<!-- 															<option value="1"></option> -->
													</select>
													</label>
												</section>
											</div>

											<section>
												<div class="row">
													<div class="col col-lg-12">
														<label class="input"> <i
															class="icon-append fa fa-user"></i> <input type="text"
															name="planName" id="planName"
															placeholder="<spring:message code="ApplicationAndPlan.AddNewPlan.label.PlanName"/>">
															<b class="tooltip tooltip-bottom-right"><spring:message
																	code="ApplicationAndPlan.AddNewPlan.tooltip.PlanName" /></b>
														</label>
													</div>
												</div>
											</section>

											<section>
												<div class="row">
													<div class="col col-lg-12">
														<label class="textarea"> <i
															class="icon-append fa fa-comment"></i> <textarea
																name="planDescription" id="planDescription"
																placeholder="<spring:message code="ApplicationAndPlan.AddNewPlan.label.PlanDescription"/>"></textarea>
															<b class="tooltip tooltip-bottom-right"><spring:message
																	code="ApplicationAndPlan.AddNewPlan.tooltip.PlanDescription" /></b>
														</label>
													</div>
												</div>
											</section>
											<section>
												<div class="row">
													<div class="col col-lg-12">
														<label class="textarea"><i
															class="icon-append fa fa-comment"></i> <textarea
																name="planAdditionalDescription"
																id="planAdditionalDescription"
																placeholder="<spring:message code="ApplicationAndPlan.AddNewPlan.label.PlanAdditionalDescription"/>"
																row="5"></textarea> <b
															class="tooltip tooltip-bottom-right"><spring:message
																	code="ApplicationAndPlan.AddNewPlan.tooltip.PlanAdditionalDescription" /></b>
														</label>
													</div>
												</div>
											</section>
											<div class="row">
												<section class="col col-6">
													<label class="input"><i
														class="icon-append fa fa-usd"></i> <input type="text"
														name="price" id="price"
														placeholder="<spring:message code="ApplicationAndPlan.AddNewPlan.label.Price"/>">
														<b class="tooltip tooltip-bottom-right"><spring:message
																code="ApplicationAndPlan.AddNewPlan.tooltip.Price" /></b> </label>
												</section>
												<section class="col col-6">
													<label class="input"><i
														class="icon-append fa fa-database"></i> <input type="text"
														name="dataUsage" id="dataUsage"
														placeholder="<spring:message code="ApplicationAndPlan.AddNewPlan.label.DataUsage"/>">
														<b class="tooltip tooltip-bottom-right"><spring:message
																code="ApplicationAndPlan.AddNewPlan.tooltip.DataUsage" /></b>
													</label>
												</section>
											</div>
											<div class="row">
												<section class="col col-6">
													<label class="input"> <i
														class="icon-append fa fa-users"></i><input type="text"
														name="maxNumberOfUser" id="maxNumberOfUser"
														placeholder="<spring:message code="ApplicationAndPlan.AddNewPlan.label.MaxNoOfUsers"/>">
														<b class="tooltip tooltip-bottom-right"><spring:message
																code="ApplicationAndPlan.AddNewPlan.tooltip.MaxNoOfUsers" /></b>
													</label>
												</section>
												<section class="col col-6">
													<label class="input"><i
														class="icon-append fa fa-calendar"></i> <input type="text"
														name="planValidityInMonths" id="planValidityInMonths"
														placeholder="<spring:message code="ApplicationAndPlan.AddNewPlan.label.PlanValidityInMonths"/>">
														<b class="tooltip tooltip-bottom-right"><spring:message
																code="ApplicationAndPlan.AddNewPlan.tooltip.PlanValidityInMonths" /></b>
														<input type="hidden" name="planTypeAction"
														id="planTypeAction" value="save"> <input
														type="hidden" name="planId" id="planId"> <input
														type="hidden" name="submitId" id="submitId"> </label>

												</section>

											</div>

										</fieldset>

										<footer>
											<button type="button" class="btn btn-default"
												data-dismiss="modal" onclick="resetPurchaseForm()">
												<spring:message code="Button.Cancel" />
											</button>
											<button type="button" class="btn btn-primary" id="reset"
												onclick="resetPurchaseForm()">
												<i class="fa fa-undo"></i> &nbsp;
												<spring:message code="Button.Reset" />
											</button>
											<button type="submit" class="btn btn-primary ladda-button"
												data-style="expand-right" id="saveandcontinue"
												onclick="submitTypeAppPurch(this.id,this)">
												<i class="fa fa-forward"></i> &nbsp;
												<spring:message code="Button.SaveContinue" />
											</button>
											<button type="submit" class="btn btn-primary ladda-button"
												data-style="expand-right" id="save"
												onclick="submitTypeAppPurch(this.id,this)">
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
     

	<!-- MODAL PLACE HOLDER -->
	<div class="modal fade" id="applicationModal" tabindex="-1"
		role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">


				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						<span class="badge pull-right inbox-badge customColor">&times;</span>
					</button>
					<h4 class="modal-title" id="modalTitleIdForApplication">
						<i class="fa fa-fw fa-lg fa-font txt-color-blue "></i>
						<spring:message code="ApplicationAndPlan.Form.AddNewApplication" />
					</h4>
				</div>
				<div class="modal-body no-padding">

					<form id="appDetailForm" action="" class="smart-form" method="POST">
						<input type="hidden" name="action" id="action"> <input
							type="hidden" name="applicationId" id="applicationId"> <input
							type="hidden" name="submittedDate" id="submittedDate">
						<fieldset>
							<section class="11">
								<label class="input"><i class="icon-append fa fa-user"></i>
									<input type="text" name="applicationName" id="appName"
									placeholder="<spring:message code="ApplicationAndPlan.AddNewApplication.label.ApplicationName"/>">
									<b class="tooltip tooltip-bottom-right"><spring:message
											code="ApplicationAndPlan.AddNewApplication.tooltip.ApplicationName" /></b>
								</label>
							</section>
							<hr>
							<br>
							<section class="11">
								<label class="input"> <i class="icon-append fa fa-link"></i>

									<input type="text" name="applicationLink" id="applicationLink"
									placeholder="<spring:message code="ApplicationAndPlan.AddNewApplication.label.ApplicationLink"/>">
									<b class="tooltip tooltip-bottom-right"><spring:message
											code="ApplicationAndPlan.AddNewApplication.tooltip.ApplicationLink" /></b>
								</label>
							</section>
							<hr>
							<br>
							<section class="11">
								<i class="icon-append fa fa-envelope"></i> <label
									class="textarea"> <i class="icon-append fa fa-comment"></i>
									<textarea data-width="100%" name="description" id="description"
										maxlength="2000"
										placeholder="<spring:message code="ApplicationAndPlan.AddNewApplication.label.Description"/>"></textarea>
									<b class="tooltip tooltip-bottom-right"><spring:message
											code="ApplicationAndPlan.AddNewApplication.tooltip.Description" /></b>
								</label>
							</section>
							<hr>
							<br>
							<section class="6">
								<label class="checkbox"> <input type="checkbox"
									id="listUnderAppStore" name="listUnderAppStore"> <i></i>
								<spring:message
										code="ApplicationAndPlan.AddNewApplication.label.checkbox" />
								</label>
							</section>
						</fieldset>
						<footer>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" onclick="resetApplicationForm();">
								<spring:message code="Button.Cancel" />
							</button>
							<button type="button" class="btn btn-primary" id="reset"
								onclick="resetApplicationForm();">
								<i class="fa fa-undo"></i> &nbsp;
								<spring:message code="Button.Reset" />
							</button>
							<button type="submit" class="btn btn-primary"
								id="saveAndContinue"
								onclick="setApplicationAction('saveandcontinue')">
								<i class="fa fa-forward"></i> &nbsp;
								<spring:message code="Button.SaveContinue" />
							</button>
							<button type="submit" class="btn btn-primary" id="Save"
								onclick="setApplicationAction('save')">
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
<script type="text/javascript"
	src="assets/js/applicationPurchasePlan.js"></script>

<script type="text/javascript" src="assets/js/applicationDetails.js"></script>

<script type="text/javascript" src="assets/js/moment.min.js"></script>


