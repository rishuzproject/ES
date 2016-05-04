<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- #MAIN CONTENT -->

	<!-- /.modal -->
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title">
			<i class="fa fa-barcode fa-fw txt-color-blue"></i> Coupons
		</h1>
	</div>
	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
		<ul id="sparks" class="">
			<li class="sparks-info">
				<h5>TOTAL COUPONS
					 <span class="txt-color-blue" id="totalCouponsId">0</span>
				</h5>
			</li>
		</ul>
	</div>
</div>
<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">

	<!-- Button trigger modal -->


	<!-- MODAL PLACE HOLDER -->
	<div class="modal fade" id="remoteModal" tabindex="-1" role="dialog"
		aria-labelledby="remoteModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">


				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick="resetCouponForm()"><span class="badge pull-right inbox-badge customColor">&times;</span></button>
					<h4 class="modal-title" id="modelTitleId"><i class="fa fa-barcode  fa-fw txt-color-blue"></i> Add New Coupon</h4>
				</div>
				<div class="modal-body no-padding">

					<form  method="POST" id="couponFormId"
						class="smart-form" commandName = "couponForm">
							<input type="hidden" name = "couponId" id="couponId">
							<input type="hidden" id="typeOfAction" name="typeOfAction">
						<fieldset>
							<section>
								<label class="input"> <i
									class="icon-append fa fa-sort-numeric-asc"></i> <input type="text"
									name="couponCode" id="couponCode" placeholder="Coupon code"> <b
									class="tooltip tooltip-bottom-right">Enter the Coupon code</b>
								</label>
							</section>
							
							<div class="row">
								<section class="col col-6">
									<label class="input" > <i class="icon-append fa fa-calendar"></i>
									<input type="text" name="validity" id="validity"
									placeholder="Validity"> <b
									class="tooltip tooltip-bottom-right">Enter the Validity</b>
								</label>
								</section>
								<section class="col col-6">
									<label class="input" > <i class="icon-append fa fa-money"></i>
									<input type="text" name="discountAmount"
									id="discountAmount" placeholder="Discount Amount"> <b
									class="tooltip tooltip-bottom-right">Enter the Discount Amount</b>
								</label>
								</section>
							</div>
							<div class="row">
								<section class="col col-6">
									<label class="input"> <i class="icon-append fa fa-user"></i>
										<input type="text" name="additionalUser" id="additionalUser"
										placeholder="Additional User" > <b
										class="tooltip tooltip-bottom-right">Enter the Additional User</b>
									</label>
								</section>
								<section class="col col-6">
									<label class="input"> <i class="icon-append fa fa-money"></i>
										<input type="text" name="additionalSpace" id="additionalSpace"
										placeholder="Additional Space"> <b
										class="tooltip tooltip-bottom-right">Enter the Additional Space</b>
									</label>
								</section>
							</div>
							<div class="row">
								<section class="col col-lg-6 pull right">
												<label class="checkbox"> <input type="checkbox"
													name="isOneTimeApplicable" id="isOneTimeApplicable"> <i></i>Is
													One Time Applicable?
												</label>
								</section>
							</div>

						</fieldset>

						<footer>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" onclick="resetCouponForm()" >Cancel</button>
							<button type="button" class="btn btn-primary" id="reset"
								onclick="resetCouponForm()">
								<i class="fa fa-undo"></i> &nbsp;Reset
							</button>
							<button type="submit" class="btn btn-primary ladda-button" data-style="expand-right"
								id="saveandcontinue" onclick="setActionType(this.id,this) ">
								<i class="fa fa-forward"></i> &nbsp;Save & Continue
							</button>
							<button type="submit" class="btn btn-primary ladda-button" data-style="expand-right" 
							        id="save" onclick="setActionType(this.id,this)" >
								<i class="fa fa-floppy-o"></i> &nbsp; Save
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
					
					&nbsp; <a href="" data-toggle="modal" data-target="#remoteModal" onclick="resetCouponForm();">
						<span class="widget-icon" style="color: #E4E4E4;"> <i
							class="fa fa-plus-square"></i>
					</span>
						<h2 style="color: #E4E4E4;">Add New Coupon</h2>
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
							<table id="couponDetailsTable"
								class="table table-striped table-bordered table-hover dataTable no-footer"
								width="100%" role="grid" aria-describedby="dt_basic_info"
								style="width: 100%;">
								<thead>
									<tr role="row">
										<th data-hide="phone" class="sorting_asc" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-sort="ascending"
											aria-label="ID: activate to sort column ascending"
											style="width: 41px;">#</th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" First Name: activate to sort column ascending"
											style="width: 130px;"><i
											class="fa fa-fw fa-sort-numeric-asc text-muted hidden-md hidden-sm hidden-xs"></i>
											Coupon Code</th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Nick Name: activate to sort column ascending"
											style="width: 78px;"><i
											class="fa fa-fw fa-calendar text-muted hidden-md hidden-sm hidden-xs"></i>
											Validity</th>
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="Email: activate to sort column ascending"
											style="width: 168px;"><i
											class="fa fa-fw fa-money text-muted hidden-md hidden-sm hidden-xs"></i>
											Discount Amount</th>
										<th data-hide="phone" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Phone: activate to sort column ascending"
											style="width: 145px;"><i
											class="fa fa-fw fa-user text-muted hidden-md hidden-sm hidden-xs"></i>
											Additional User</th>
										<th data-hide="phone" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Phone: activate to sort column ascending"
											style="width: 160px;"><i
											class="fa fa-fw fa-money text-muted hidden-md hidden-sm hidden-xs"></i>
											Additional Space</th>	
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label="Prefered Contact Mode: activate to sort column ascending"
											style="width: 168px;"><i
											class="fa fa-fw  text-muted hidden-md hidden-sm hidden-xs"></i>
											Is-One Time App</th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Submitted Date: activate to sort column ascending"
											style="width: 90px;"><i
											class="fa fa-fw  txt-color-blue hidden-md hidden-sm hidden-xs"></i>
											Is-Used</th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" : activate to sort column ascending"
											style="width: 30px;"></th>
									</tr>
								</thead>
								<tbody>
									<!-- <tr role="row" class="odd">
										<td class="sorting_1">1</td>
										<td><span class="responsiveExpander"></span>Jennifer</td>
										<td>1-342-463-8341</td>
										<td>Et Rutrum Non Associates</td>
										<td>35728</td>
										<td>Fogo</td>
										<td>03/04/14</td>
									</tr>
									<tr role="row" class="even">
										<td class="sorting_1">2</td>
										<td><span class="responsiveExpander"></span>Clark</td>
										<td>1-516-859-1120</td>
										<td>Nam Ac Inc.</td>
										<td>7162</td>
										<td>Machelen</td>
										<td>03/23/13</td>
									</tr>
									<tr role="row" class="odd">
										<td class="sorting_1">3</td>
										<td><span class="responsiveExpander"></span>Brendan</td>
										<td>1-724-406-2487</td>
										<td>Enim Commodo Limited</td>
										<td>98611</td>
										<td>Norman</td>
										<td>02/13/14</td>
									</tr>
									<tr role="row" class="even">
										<td class="sorting_1">4</td>
										<td><span class="responsiveExpander"></span>Warren</td>
										<td>1-412-485-9725</td>
										<td>Odio Etiam Institute</td>
										<td>10312</td>
										<td>Sautin</td>
										<td>01/01/13</td>
									</tr>
									<tr role="row" class="odd">
										<td class="sorting_1">5</td>
										<td><span class="responsiveExpander"></span>Rajah</td>
										<td>1-849-642-8777</td>
										<td>Neque Ltd</td>
										<td>29131</td>
										<td>Glovertown</td>
										<td>02/16/13</td>
									</tr>
									<tr role="row" class="even">
										<td class="sorting_1">6</td>
										<td><span class="responsiveExpander"></span>Demetrius</td>
										<td>1-470-329-9627</td>
										<td>Euismod In Ltd</td>
										<td>1883</td>
										<td>Kapolei</td>
										<td>03/15/13</td>
									</tr> -->

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

<script>
/* start of initializing datepicker */
</script>
<script type="text/javascript" src="assets/js/couponDetails.js"></script>
<!-- END #MAIN CONTENT -->