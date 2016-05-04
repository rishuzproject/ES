<div id="content" style="opacity: 1;">

	<!-- widget grid -->
	<section id="widget-grid" class="">

		<!-- row -->
		<div class="row">

			<!-- NEW WIDGET START -->
			<article
				class="col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">

				<!-- Widget ID (each widget will need unique ID)-->

				<!-- end widget -->

				<div class="jarviswidget well jarviswidget-color-darken"
					id="wid-id-0" data-widget-sortable="false"
					data-widget-deletebutton="false" data-widget-editbutton="false"
					data-widget-colorbutton="false" role="widget" style="">
					<!-- widget options:
				usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">

				data-widget-colorbutton="false"
				data-widget-editbutton="false"
				data-widget-togglebutton="false"
				data-widget-deletebutton="false"
				data-widget-fullscreenbutton="false"
				data-widget-custombutton="false"
				data-widget-collapsed="true"
				data-widget-sortable="false"

				-->
					<header role="heading">
						<div class="jarviswidget-ctrls" role="menu">
							<a href="javascript:void(0);"
								class="button-icon jarviswidget-toggle-btn" rel="tooltip"
								title="" data-placement="bottom" data-original-title="Collapse"><i
								class="fa fa-minus "></i></a> <a href="javascript:void(0);"
								class="button-icon jarviswidget-fullscreen-btn" rel="tooltip"
								title="" data-placement="bottom"
								data-original-title="Fullscreen"><i class="fa fa-expand "></i></a>
						</div>
						<span class="widget-icon"> <i class="fa fa-barcode"></i>
						</span>
						<h2>Item #44761</h2>

						<span class="jarviswidget-loader"><i
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

							<div class="widget-body-toolbar">

								<div class="row">

									<div class="col-sm-4"></div>

									<div class="col-sm-8 text-align-right">
										
										<!-- <div class="btn-group">
											<input type="button" onclick="printDiv('invoiceMainBody')" value="Print" />
										</div> -->
									
										<div class="btn-group">
											<a class="btn btn-sm btn-primary" data-target="#emailComposeModalOpen" data-toggle='modal'>
												<i class="icon-append fa fa-envelope"></i> Send Email
											</a>
										</div>

										<div class="btn-group">
											<a href="javascript:void(0)" class="btn btn-sm btn-success">
												<i class="fa fa-plus"></i> Create New
											</a>
										</div>

									</div>

								</div>

							</div>

							<div class="padding-10" id="invoiceMainBody">
								<br>
								<div class="pull-left">
									<img src="./assets/img/logo.png" width="150" height="32"
										alt="invoice icon">

									<address id="companyId">
										<br> <strong>Belco Elecnor, Inc.</strong> <br> 4331
										Schaefer Ave Chino, <br> CA 91710 <br> <abbr
											title="Phone">P:</abbr> (909) 993-5470 <abbr title="Phone">F:</abbr>
										(909) 993-5476
									</address>
								</div>
								<div class="pull-right">
									<h1 class="font-400">Invoice</h1>
								</div>
								<div class="clearfix"></div>
								<br> <br>
								<div class="row">
									<div class="col-sm-9">
										<h4 class="semi-bold" id="domainNameId"></h4>
										<address id="buyerId">
											<!-- <strong>Mr. Simon Hedger</strong> <br> 342 Mirlington
											Road, <br> Calfornia, CA 431464 <br> <abbr
												title="Phone">P:</abbr> (467) 143-4317 -->
										</address>
									</div>
									<div class="col-sm-3">
										<div>
											<div>
												<strong>INVOICE NO :</strong> <span class="pull-right" id="invoiceId">
													 </span>
											</div>

										</div>
										<div>
											<div class="font-md">
												<strong>INVOICE DATE :</strong> <span class="pull-right" id="submittedDateId">
												</span>
											</div>

										</div>
										<br>
										<div
											class="well well-sm  bg-color-darken txt-color-white no-border">
											<div class="fa-lg">
												Total Due : <span class="pull-right" id="totalAmount"></span>
											</div>

										</div>
										<br> <br>
									</div>
								</div>
								<br> <br>
								<table class="table table-hover" id="applicationInvoiceInDetailsTable">
									<thead>
										<tr>
											<th class="text-center"><i class="icon-append fa fa-sort-alpha-asc"></i> &nbsp; APPLICATION</th>
											<th class="text-center"><i class="icon-append fa fa-sort-alpha-asc"></i> &nbsp; PLAN</th>
											<th class="text-center"><i class="icon-append fa fa-usd"></i>&nbsp; PRICE</th>
											<th class="text-center">% INTEREST</th>
											<th class="text-center"><i class="icon-append fa fa-usd"></i>&nbsp; SUBTOTAL</th>
										</tr>
									</thead>
									<tbody>
										<!-- <tr>
											<td class="text-center"><strong>1</strong></td>
											<td><a href="javascript:void(0);">Print and Web Logo
													Design</a></td>
											<td>Perspiciatis unde omnis iste natus error sit
												voluptatem accusantium doloremque laudantium totam rem
												aperiam xplicabo.</td>
											<td>$1,300.00</td>

											<td>$1,300.00</td>
										</tr>
										<tr>
											<td class="text-center"><strong>1</strong></td>
											<td><a href="javascript:void(0);">SEO Management</a></td>
											<td>Sit voluptatem accusantium doloremque laudantium
												inventore veritatis et quasi architecto beatae vitae dicta
												sunt explicabo.</td>
											<td>$1,400.00</td>
											<td>$1,400.00</td>
										</tr>
										<tr>
											<td class="text-center"><strong>1</strong></td>
											<td><a href="javascript:void(0);">Backend Support
													and Upgrade</a></td>
											<td>Inventore veritatis et quasi architecto beatae vitae
												dicta sunt explicabo.</td>
											<td>$1,700.00</td>
											<td>$1,700.00</td>
										</tr>
										<tr>
											<td colspan="4">Total</td>
											<td><strong>$4,400.00</strong></td>
										</tr>
										<tr>
											<td colspan="4">HST/GST</td>
											<td><strong>13%</strong></td>
										</tr> -->
									</tbody>
								</table>

								<div class="invoice-footer">

									<div class="row">

										<div class="col-sm-7">
											<div class="payment-methods">
												<h5>Payment Methods</h5>
												<img src="assets/img/paymentImages/paypal.png" width="64"
													height="64" alt="paypal"> <img
													src="assets/img/paymentImages/americanexpress.png"
													width="64" height="64" alt="american express"> <img
													src="assets/img/paymentImages/mastercard.png" width="64"
													height="64" alt="mastercard"> <img
													src="assets/img/paymentImages/visa.png" width="64"
													height="64" alt="visa">
											</div>
										</div>
										<div class="col-sm-5">
											<div class="invoice-sum-total pull-right">
												<h3>
													<strong>Total: <span class="text-success" id="totalAmountId"></span></strong>
												</h3>
											</div>
										</div>

									</div>

									<div class="row">
										<div class="col-sm-12">
											<p class="note">**To avoid any excess penalty charges,
												please make payments within 30 days of the due date. There
												will be a 13% interest charge per month on all late invoices.</p>
										</div>
									</div>

								</div>
							</div>

						</div>
						<!-- end widget content -->

					</div>
					<!-- end widget div -->

				</div>
			</article>
			<!-- WIDGET END -->

		</div>

		<!-- end row -->

	</section>
	<!-- end widget grid -->

	<script type="text/javascript">

	</script>
	<script type="text/javascript" src="assets/js/applicationInvoiceInDetail.js"></script>
</div>