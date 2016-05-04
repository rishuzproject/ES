<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- #MAIN CONTENT -->

<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa  fa-money txt-color-blue"></i>
			&nbsp; Payment
			<span>&gt; Make payment
			</span>
		</h1>
	</div>
	
</div>


	<div class="col-md-12">
		<form id="userFormInPayment" method="post">
		<input type="hidden" name= "finalPrice" id ="finalPrice">
		<input type="hidden" name= "paymentType" id ="paymentType">
			<article class="col-md-12 sortable-grid ui-sortable">

				<div class="jarviswidget jarviswidget-sortable" id="wid-id-1"
					data-widget-editbutton="false" data-widget-custombutton="false"
					role="widget" style="">
					<header role="heading"
						style="height: 50px; background-image: -ms-linear-gradient(top, #404040 0%, #404040 100%); background-image: -moz-linear-gradient(top, #404040 0%, #404040 100%); background-image: -o-linear-gradient(top, #404040 0%, #404040 100%); background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #404040), color-stop(1, #404040)); background-image: -webkit-linear-gradient(top, #404040 0%, #404040 100%); background-image: linear-gradient(to bottom, #404040 0%, #404040 100%); color: white; font-size: 14px;"
						id="userInfoHead">
						<div class="jarviswidget-ctrls" role="menu"></div>
						<span class="widget-icon"
							style="top: 6px; text-shadow: 1px 1px #000000;"> <i
							id="uicon" class="fa fa-user"></i>
						</span>
						<h2 style="top: 6px; text-shadow: 1px 1px #000000;">User
							Information</h2>
						<span class="pull-right hide" id="uedit"
							style="position: relative; top: 16px; right: 15px; cursor: pointer"
							onclick="showUserInfoDiv()"> <i class="fa fa-edit"
							style="color: white; text-shadow: 1px 1px #000000;"></i></span>
					</header>

					<!-- widget div-->
					<div role="content" id="userinfo" class="userClass">

						<!-- widget edit box -->
						<div class="jarviswidget-editbox">
							<!-- This area used as dropdown edit box -->

						</div>
						<!-- end widget edit box -->

						<!-- widget content -->
						<div class="widget-body no-padding">

							<div class="smart-form">

								<fieldset>
										<input type="hidden" id="userId" name="userId">
										<input type="hidden" id="emailId" name="emailId">
										<input type="hidden" id="password" name="password">
									<div class="row">
										<section class="col col-6">
											<label class="input"> <i
												class="icon-append fa fa-user"></i> <input type="text"
												name="firstName" id="firstName" placeholder="First Name..">
												<b class="tooltip tooltip-bottom-right">Needed to enter
													your First Name</b>
											</label>
										</section>

										<section class="col col-6">
											<label class="input"> <i
												class="icon-append fa fa-user"></i> <input type="text"
												name="middleName" id="middleName"
												placeholder="Middle Name.."> <b
												class="tooltip tooltip-bottom-right">Needed to enter
													your Middle Name</b>
											</label>
										</section>
									</div>

									<div class="row">
										<section class="col col-6">
											<label class="input"> <i
												class="icon-append fa fa-user"></i> <input type="text"
												name="lastName" id="lastName" placeholder="Last Name..">
												<b class="tooltip tooltip-bottom-right">Needed to enter
													your Last Name</b>
											</label>
										</section>

										<section class="col col-6">
											<label class="input"> <i
												class="icon-append fa fa-user"></i> <input type="text"
												name="nickName" id="nickName" placeholder="Nickname..">
												<b class="tooltip tooltip-bottom-right">Needed to enter
													your Nick Name</b>
											</label>
										</section>
									</div>
									<div class="row">
										<section class="col col-6">
											<label class="input"><!-- <i
												class="icon-append fa fa-phone"></i> <input type="text"
												name="phoneCarrier" id="phoneCarrier"
												placeholder="Phone Carrier.."> -->
												<select class="form-control selectpicker show-tick" id="phoneCarrier" name="phoneCarrier"> 
													<option value="">--Select PhoneCarrier--</option>
													<option value="at&t">At&t</option>
													<option value="t-mobile">T-Mobile</option>
													<option value="verizon">Verizon</option>
													<option value="sprint">Sprint</option>
													<option value="virgin">Virgin</option>
													<option value="tracfone">Tracfone</option>
													<option value="nextel">Nextel</option>
													<option value="alltel">Alltel</option>
													<option value="ptel">Ptel</option>
													<option value="usCellular">US Cellular</option>
												</select>
												<b class="tooltip tooltip-bottom-right">Needed to enter
													your Phone Carrier</b>
											</label>
										</section>

										<section class="col col-6">
											<label class="input"> <i
												class="icon-append fa fa-mobile"></i> <input type="text"
												name="phoneNumber" id="phoneNumber"
												placeholder="Phone Number.."> <b
												class="tooltip tooltip-bottom-right">Needed to enter
													your Phone Number</b>
											</label>
										</section>
									</div>

									<div class="row">
										<section class="col col-3">
											<label style="font-size: 13px; position: relative; top: 3px;">Preferred
												Contact Mode : </label>
										</section>
										<section class="col col-9">
											<div class="row">
												<div class="col col-4">
													<label class="radio"> <input type="radio"
														name="preferredContactMode" id="preferredContactMode"
														checked="checked" value="phone"> <i></i>&nbsp;&nbsp;&nbsp;Phone
													</label>


												</div>
												<div class="col col-4">
													<label class="radio"> <input type="radio"
														name="preferredContactMode" id="preferredContactMode"
														value="email"> <i></i>&nbsp;&nbsp;&nbsp;Email
													</label>


												</div>
												<div class="col col-4">
													<label class="radio"> <input type="radio"
														name="preferredContactMode" id="preferredContactMode"
														value="both"> <i></i>&nbsp;&nbsp;&nbsp;Both
													</label>


												</div>
											</div>
										</section>
									</div>
								</fieldset>

								<footer>
									<button type="button" id="userValidateId" class="btn btn-default ValidateId"
										onclick="showCompanyInfo()">Next</button>
								</footer>
							</div>

						</div>
						<!-- end widget content -->

					</div>
					<!-- end widget div -->

				</div>

			</article>

			<article class="col-md-12 sortable-grid ui-sortable"
				style="position: relative; top: -30px;">

				<div class="jarviswidget jarviswidget-sortable" id="wid-id-1"
					data-widget-editbutton="false" data-widget-custombutton="false"
					role="widget" style="">
					<header role="heading" style="height: 50px;" id="companyInfoHead">
						<div class="jarviswidget-ctrls" role="menu"></div>
						<span class="widget-icon" style="top: 6px;"> <i id="cicon"
							class="fa fa-building"></i>
						</span>
						<h2 style="top: 6px">Company Information</h2>

						<span class="pull-right hide" id="cedit"
							style="position: relative; top: 16px; right: 15px; cursor: pointer"
							onclick="showCompanyInfoDiv()"> <i class="fa fa-edit"
							style="color: white; text-shadow: 1px 1px #000000;"></i></span>
					</header>

					<!-- widget div-->
					<div role="content" id="companyinfo" class="hide companyClass">

						<!-- widget edit box -->
						<div class="jarviswidget-editbox">
							<!-- This area used as dropdown edit box -->

						</div>
						<!-- end widget edit box -->

						<!-- widget content -->
						<div class="widget-body no-padding">

							<div class="smart-form">

								<fieldset>
										<input type="hidden" id="domainId" name="domainId">
									<div class="row col-md-8">
										<section class="col col-10">
											<label class="input"> <i
												class="icon-append fa fa-building-o"></i> <input type="text"
												name="companyName" id="companyName"
												placeholder="Company Name.."> <b
												class="tooltip tooltip-bottom-right">Needed to enter
													your company name</b>
											</label>
										</section>
										<section class="col col-2"></section>
									</div>

									<div class="row col-md-8">
										<section class="col col-10">
											<label class="textarea"> <i
												class="icon-append fa fa-map-marker"></i> <textarea rows="3"
													class="custom-scroll" name="companyAddress"
													id="companyAddress" placeholder="Company Address.."></textarea>
												<b class="tooltip tooltip-bottom-right">Needed to enter
													your company address</b>
											</label>
										</section>
										<section class="col col-2"></section>
									</div>

									<div class="row col-md-8">
										<!-- <section class="col col-10">
											<label class="input "> <i
												class="icon-append fa fa-ticket"></i> <input type="text"
												name="domainName" id="domainName"
												placeholder="Domain Name.."> <b
												class="tooltip tooltip-bottom-right">Needed to enter
													your domain name</b>
											</label>
										</section> -->
										
										<!-- domain logo code..do not remove -->
										<!-- <section>
											<label for="templateFileLabel" class="input input-file">
												<div class="button">
													<input type="file" name="templateFile" id="templateFile"
														onchange="this.parentNode.nextSibling.value = this.value">
														Browse
												</div>
												<input type="text" name="fileName" id="fileName"
													placeholder="Choose document to Upload" readonly="">
											</label>
										</section> -->
										<!-- <section class="col col-2">
											<span style="vertical-align: middle;"> <a
												title="Check Availability"
												style="position: relative; top: 10px;"><i
													class="fa fa-check-circle-o fa-lg fa-fw"></i></a> <b
												class="tooltip tooltip-bottom-right">Check Availability</b>
											</span>
											<input class="btn btn-default fa-user" id="validate" type="button" value="Check Availability">
										</section> -->
									</div>

									<div class="row col-md-8">
										<section class="col col-10">
											<label class="input"><!--  <i
												class="icon-append fa fa-phone"></i> <input type="text"
												name="companyPhoneCarrier" id="companyPhoneCarrier"
												placeholder="Company Phone Carrier.."> --> 
												<select class="form-control selectpicker show-tick" id="companyPhoneCarrier" name="companyPhoneCarrier">

															<option value="">--Select PhoneCarrier--</option>
															<option value="at&t">At&t</option>
															<option value="t-mobile">T-Mobile</option>
															<option value="verizon">Verizon</option>
															<option value="sprint">Sprint</option>
															<option value="virgin">Virgin</option>
															<option value="tracfone">Tracfone</option>
															<option value="nextel">Nextel</option>
															<option value="alltel">Alltel</option>
															<option value="ptel">Ptel</option>
															<option value="usCellular">US Cellular</option>
													</select>
												<b class="tooltip tooltip-bottom-right">Needed to enter
													your Company Phone Carrier</b>
											</label>
										</section>
										<section class="col col-2"></section>
									</div>

                                    <div class="row col-md-8">
										<section class="col col-10">
										<label for="uploadImageLabel" class="input input-file">
									<div class="button">
										<input type="file" name="uploadImageFile" id="uploadImageFile"
											onchange="this.parentNode.nextSibling.value = this.value">
										Browse
									</div><input type="text" name="uploadImageFileName" id="uploadImageFileName"
									placeholder="Choose document to Upload" style="color:#2E6074"
									readonly="">
								</label>
									</section>
										<section class="col col-2"></section>
									</div>
                                    
									<div class="row col-md-8">
										<section class="col col-10">
											<label class="input"> <i
												class="icon-append fa fa-mobile"></i> <input type="text"
												name="companyPhoneNumber" id="companyPhoneNumber"
												placeholder="Company Phone Number.."> <b
												class="tooltip tooltip-bottom-right">Needed to enter
													your Company Phone Number</b>
											</label>
										</section>
										<section class="col col-2"></section>
									</div>
								</fieldset>

								<footer>
									<button type="button"id="domainValidateId" class="btn btn-default ValidateId"
										onclick="showSummary()">Next</button>
								</footer>
							</div>

						</div>
						<!-- end widget content -->

					</div>
					<!-- end widget div -->

				</div>

			</article>
			<article class="col-md-12 sortable-grid ui-sortable"
				style="position: relative; top: -60px;">

				<div class="jarviswidget jarviswidget-sortable" id="wid-id-1"
					data-widget-editbutton="false" data-widget-custombutton="false"
					role="widget" style="">
					<header role="heading" style="height: 50px;" id="summaryHead">
						<div class="jarviswidget-ctrls" role="menu"></div>
						<span class="widget-icon" style="top: 6px;"> <i id="sicon"
							class="fa fa-shopping-cart"></i>
						</span>
						<h2 style="top: 6px">Summary</h2>

						<span class="pull-right hide" id="sedit"
							style="position: relative; top: 16px; right: 15px; cursor: pointer"
							onclick="showSummaryDiv()"> <i class="fa fa-edit"
							style="color: white; text-shadow: 1px 1px #000000;"></i></span>
					</header>
					<div role="content" id="summary" class="hide summaryClass">
						<div class="jarviswidget-editbox"></div>
						<div class="widget-body no-padding">

							<div class="smart-form">
							      <div class="alert alert-block alert-info" id="cartMessage">
						
						<h4 class="alert-heading">
						The cart is empty!!!!
							
						</h4>
						
                      </div>
                                <input type="hidden" id="summarisedSubscriptionArrayIds" name="summarisedSubscriptionArrayIds">
								<table class="table table-hover"
									id="summarisedSubscriptionTable">
									<thead>
										<tr>
											<th class="text-center">APP</th>
											<th class="text-center">PLAN</th>
											<th class="text-center">DESCRIPTION</th>
											<th class="text-center">PRICE</th>
											<th class="text-center"></th>
										</tr>
									</thead>
									<tbody>


									</tbody>
								</table>

								<footer>
									<button type="button"id="summaryValidateId" class="btn btn-default"
										onclick="showPayment()">Next</button>
								</footer>
							</div>
						</div>
					</div>
				</div>

			</article>

			<article class="col-md-12 sortable-grid ui-sortable"
				style="position: relative; top: -90px;">

				<div class="jarviswidget jarviswidget-sortable" id="wid-id-1"
					data-widget-editbutton="false" data-widget-custombutton="false"
					role="widget" style="">
					<header role="heading" style="height: 50px;" id="paymentHead">
						<div class="jarviswidget-ctrls" role="menu"></div>
						<span class="widget-icon" style="top: 6px;"> <i id="picon"
							class="fa fa-credit-card"></i>
						</span>
						<h2 style="top: 6px">Payment</h2>

						<span class="pull-right hide" id="pedit"
							style="position: relative; top: 16px; right: 15px; cursor: pointer"
							onclick="showPaymentDiv()"> <i class="fa fa-edit"
							style="color: white; text-shadow: 1px 1px #000000;"></i></span>
					</header>
					<div role="content" id="payment" class="hide paymentClass">
						<div class="jarviswidget-editbox"></div>
						<div class="widget-body no-padding">

							<div class="smart-form">

								<div class="tabs-left">
									<ul class="nav nav-tabs tabs-left" id="demo-pill-nav"
										style="padding: 25px;">
										<li class="active"><a href="#tab-r1" data-toggle="tab">Card
										</a></li>
										<li><a href="#tab-r2" data-toggle="tab"> Net Banking</a></li>
										<li><a href="#tab-r3" data-toggle="tab"> Pay Pal</a></li>
										<li><a href="#tab-r4" data-toggle="tab"> Promo Code</a></li>
									</ul>
									<div class="tab-content col-md-7" style="padding-top: 8px;">

										<div class="tab-pane active" id="tab-r1">
											<fieldset>
												<section>
													<div class="inline-group">
														<label class="radio"> <input type="radio"
															name="radio-inline" checked=""> <i></i>&nbsp;&nbsp;Visa
														</label> <label class="radio"> <input type="radio"
															name="radio-inline"> <i></i>&nbsp;&nbsp;MasterCard
														</label> <label class="radio"> <input type="radio"
															name="radio-inline"> <i></i>&nbsp;&nbsp;American
															Express
														</label>
													</div>
												</section>

												<section>
													<label class="input"> <input type="text"
														placeholder="Name on card">
													</label>
												</section>

												<div class="row">
													<section class="col col-10">
														<label class="input"> <input type="text"
															size="20" data-stripe="number" placeholder="Card number"
															data-mask="9999-9999-9999-9999">
														</label>
													</section>
													<section class="col col-2">
														<label class="input"> <input type="password"
															size="4" data-stripe="cvc" placeholder="CVV2" data-mask="999">
														</label>
													</section>
												</div>

												<div class="row">
													<label class=" col col-4">Expiration date</label>
													<section class="col col-5">
														<label class="select"> <select data-stripe="exp-month">
																<option value="0" selected="" disabled="">Month</option>
																<option value="1">January</option>
																<option value="1">February</option>
																<option value="3">March</option>
																<option value="4">April</option>
																<option value="5">May</option>
																<option value="6">June</option>
																<option value="7">July</option>
																<option value="8">August</option>
																<option value="9">September</option>
																<option value="10">October</option>
																<option value="11">November</option>
																<option value="12">December</option>
														</select> <i></i>
														</label>
													</section>
													<section class="col col-3">
														<label class="input"> <input type="text"
															data-stripe="exp-year" placeholder="Year" data-mask="2099">
														</label>
													</section>
												</div>
												<footer>
													<button id="paymentByCard" type="submit" class="btn btn-success" onclick="setPaymentType(this.id)">Pay
														Now</button>
												</footer>
											</fieldset>

										</div>
										<div class="tab-pane" id="tab-r2">
											<fieldset>
												<div class="row">
													<label class="col col-2">Bank Name</label>
													<section class="col col-10">
														<label class="select"> <select name="bank">
																<option value="0" selected="" disabled="">Bank
																	name</option>
																<option value="1">Bank 1</option>
																<option value="2">Bank 2</option>
																<option value="3">Bank 3</option>
																<option value="4">Bank 4</option>
														</select> <i></i>
														</label>
													</section>
												</div>
												<footer>
													<button id="paymentByNet" type="submit" class="btn btn-success" onclick="setPaymentType(this.id)">Pay
														Now</button>
												</footer>
											</fieldset>
										</div>

										<div class="tab-pane" id="tab-r3">
											<fieldset>
												<section>
													<label class="input"> <i
														class="icon-append fa fa-envelope-o"></i> <input
														type="email" name="email" placeholder="Email address..">
														<b class="tooltip tooltip-bottom-right">Needed to
															verify your account</b>
													</label>
												</section>

												<section>
													<label class="input"> <i
														class="icon-append fa fa-lock"></i> <input type="password"
														name="password" placeholder="Password.." id="password">
														<b class="tooltip tooltip-bottom-right">Don't forget
															your password</b>
													</label>
												</section>

												<footer>
													<button id="paymentByPayPal" type="button" class="btn btn-success"
														onclick="showUserInfo();setPaymentType(this.id);">Pay Now</button>
												</footer>
											</fieldset>
										</div>
										<div class="tab-pane" id="tab-r4">
											<fieldset>
												<div class="row">
													<label class="label col col-2">Promo Code</label>
													<section class="col col-10">
														<label class="input"> <input type="text"
															name="name" placeholder="promo code">
														</label>
													</section>
												</div>
												<footer>
													<button id="paymentByPromoCode" type="submit" class="btn btn-success" onclick="setPaymentType(this.id)">Go</button>
												</footer>
											</fieldset>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</article>
		</form>
	</div>


<script type="text/javascript" src="assets/js/loader.min.js"></script>
<script type="text/javascript" src="assets/js/paymentSummary.js"></script>
<script type="text/javascript" src="assets/js/payment.js"></script>
<script type="text/javascript" src="assets/js/checkout.js"></script>
<script type="text/javascript" src="assets/js/stripev2.js"></script>
<script type="text/javascript">
   /* // This identifies your website in the createToken call below --> */
  Stripe.setPublishableKey("pk_test_3nm7TUmUTNe88Azpw9diQWPr");
</script>