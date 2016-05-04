
<!-- #MAIN CONTENT -->

<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-users fa-fw "></i> Plans <span>&gt; Manage plan </span>
		</h1>
	</div>
	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
		<ul id="sparks" class="">
			<li class="sparks-info">
				<h5>
					Total PLANS <span class="txt-color-blue">171</span>
				</h5>
			</li>
			<li class="sparks-info">
				<h5>
					Active PLANS <span class="txt-color-purple"><i
						class="fa fa-thumbs-o-up" data-rel="bootstrap-tooltip"
						title="Increased"></i>&nbsp;100</span>
				</h5>

			</li>
			<li class="sparks-info">
				<h5>
					Inactive PLANS <span class="txt-color-greenDark"><i
						class="fa fa-thumbs-o-down"></i>&nbsp;71</span>
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
						aria-hidden="true" onclick="resetPurchaseForm()">&times;</button>
					<h4 class="modal-title">Add New Plan</h4>
				</div>
				<div class="modal-body no-padding">

					<form action="" id="managePurchaseForm" class="smart-form">

						<fieldset>
							<section>
								<div class="row">
									<div class="col col-lg-12">
									<label class="input" >
										<select class="form-control selectpicker show-tick"
														id="planId" name="planid">
														<option value=""> Select Application Name</option>
														<option value="">FTS</option>
														<option value="">MPR</option>
										</select>
									</label>
									</div>
								</div>
							</section>

							<section>
								<div class="row">
								<div class="col col-lg-12">
									<label class="input"> <i
											class="icon-append fa fa-comment"></i> <input type="text"
											name="plandesc" id="planDesc" placeholder="Plan Description">
											<b class="tooltip tooltip-bottom-right">Needed to enter
											the plan Description</b>
									</label>
								</div>
								</div>
							</section>
							<section>
								<div class="row">
									<div class="col col-lg-12">
										<label class="textarea"><i
											class="icon-append fa fa-comment"></i> 
										 <textarea name="planadditionaldesc" id="planAdditionalDesc" placeholder="Additional Description" row ="5"></textarea>
											<b class="tooltip tooltip-bottom-right">Needed to enter
											the plan additional description</b>
											</label>
										</div>
									</div>
							</section>
							<div class="row">
									<section class="col col-6">
										<label class="input"><i
												class="icon-append fa fa-usd"></i> <input type="text"
											name="price" id="price" placeholder="Price">
											<b class="tooltip tooltip-bottom-right">Needed to enter
											the price</b>
										</label>
									</section>
									<section class="col col-6">
										<label class="input"><i
												class="icon-append fa fa-database"></i> <input type="text"
											name="dataUsage" id="dataUsage" placeholder="Data usage">
											<b class="tooltip tooltip-bottom-right">Needed to enter data usage</b>
										</label>
									</section>
								</div>
								<div class="row">
									<section class="col col-6">
										<label class="input"> <i
												class="icon-append fa fa-users"></i><input type="text"
											name="maxusers" id="maxUsers" placeholder="Max. No of Users">
											<b class="tooltip tooltip-bottom-right">Needed to enter
											the maximum number of users</b>
										</label>
									</section>
									<section class="col col-6">
										<label class="input"><i
												class="icon-append fa fa-calendar"></i> <input type="text"
											name="validitymonth" id="validityMonth" placeholder="Validity in Month">
											<b class="tooltip tooltip-bottom-right">Needed to enter
											the validity months</b>
										</label>
									</section>
									
								</div>
														
						</fieldset>

						<footer>
						<button type="button" class="btn btn-default" data-dismiss="modal" onclick="resetPurchaseForm()">Cancel</button>
						<button type="button" class="btn btn-primary" id="reset" onclick="resetPurchaseForm()"><i class="fa fa-undo"></i> &nbsp;Reset</button>
						<button type="submit" class="btn btn-primary ladda-button" data-style="expand-right" id="saveandcontinue" onclick="submitTypeAppPurch(this.id,this);"> <i class="fa fa-forward"></i> &nbsp;Save & Continue</button>
						<button type="submit" class="btn btn-primary ladda-button" data-style="expand-right" id="save" onclick="submitTypeAppPurch(this.id,this);"><i class="fa fa-floppy-o"></i> &nbsp; Save</button>
							
							
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
					&nbsp;
					<a href="" data-toggle="modal" data-target="#remoteModal"> <span
						class="widget-icon" style="color: #E4E4E4;"> <i class="fa fa-plus-square"></i>
					</span>
						<h2 style="color: #E4E4E4;">Add New Plan</h2>
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
							<table id="purchaseTable"
								class="table table-striped table-bordered table-hover dataTable no-footer"
								width="100%" role="grid" aria-describedby="dt_basic_info"
								style="width: 100%;">
								<thead>
									<tr role="row">
									
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 10px;">
											Sl.No.</th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 110px;">
											Plan Name</th>
											<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 110px;"><i
											class="fa fa-fw fa-comment text-muted hidden-md hidden-sm hidden-xs"></i>
											Plan Description</th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Date: activate to sort column ascending"
											style="width: 97px;"><i
											class="fa fa-fw fa-usd txt-color-blue hidden-md hidden-sm hidden-xs"></i>
											Price</th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Date: activate to sort column ascending"
											style="width: 97px;"><i
											class="fa fa-fw fa-calendar txt-color-blue hidden-md hidden-sm hidden-xs"></i>
											Validity in Months</th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Date: activate to sort column ascending"
											style="width: 97px;"><i
											class="fa fa-fw fa-users txt-color-blue hidden-md hidden-sm hidden-xs"></i>
											Max. No of Users</th>
														
										
								</thead>
								<tbody>
								<tr align="center">
								<td> Windows 7 </td>
								<td> windows 7 OS Software</td>
								<td> 100</td>
								<td> 36</td>
								<td>10</td>
								</tr>
								<tr align="center">
								<td> Windows 8 </td>
								<td> windows 8 OS Software</td>
								<td> 150</td>
								<td> 36</td>
								<td>20</td>
								</tr>
								<tr align="center">
								<td> AVG ANTIWARE </td>
								<td> AVG Anti Virus Software</td>
								<td> 50</td>
								<td> 36</td>
								<td>5</td>
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
<script type="text/javascript" src="assets/js/applicationPurchasePlan.js"></script>
<!-- <script type="text/javascript">
	/* DO NOT REMOVE : GLOBAL FUNCTIONS!
	 *
	 * pageSetUp(); WILL CALL THE FOLLOWING FUNCTIONS
	 *
	 * // activate tooltips
	 * $("[rel=tooltip]").tooltip();
	 *
	 * // activate popovers
	 * $("[rel=popover]").popover();
	 *
	 * // activate popovers with hover states
	 * $("[rel=popover-hover]").popover({ trigger: "hover" });
	 *
	 * // activate inline charts
	 * runAllCharts();
	 *
	 * // setup widgets
	 * setup_widgets_desktop();
	 *
	 * // run form elements
	 * runAllForms();
	 *
	 ********************************
	 *
	 * pageSetUp() is needed whenever you load a page.
	 * It initializes and checks for all basic elements of the page
	 * and makes rendering easier.
	 *
	 */

	pageSetUp();

	/*
	 * ALL PAGE RELATED SCRIPTS CAN GO BELOW HERE
	 * eg alert("my home function");
	 * 
	 * var pagefunction = function() {
	 *   ...
	 * }
	 * loadScript("js/plugin/_PLUGIN_NAME_.js", pagefunction);
	 * 
	 */

	// PAGE RELATED SCRIPTS
	// pagefunction	
	var pagefunction = function() {
		//console.log("cleared");

		/* // DOM Position key index //
		
			l - Length changing (dropdown)
			f - Filtering input (search)
			t - The Table! (datatable)
			i - Information (records)
			p - Pagination (paging)
			r - pRocessing 
			< and > - div elements
			<"#id" and > - div with an id
			<"class" and > - div with a class
			<"#id.class" and > - div with an id and class
			
			Also see: http://legacy.datatables.net/usage/features
		 */

		/* BASIC ;*/
		var responsiveHelper_dt_basic = undefined;

		var breakpointDefinition = {
			tablet : 1024,
			phone : 480
		};

		$('#dt_basic')
				.dataTable(
						{
							"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"
									+ "t"
									+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
							"autoWidth" : true,
							"preDrawCallback" : function() {
								// Initialize the responsive datatables helper once.
								if (!responsiveHelper_dt_basic) {
									responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
											$('#dt_basic'),
											breakpointDefinition);
								}
							},
							"rowCallback" : function(nRow) {
								responsiveHelper_dt_basic
										.createExpandIcon(nRow);
							},
							"drawCallback" : function(oSettings) {
								responsiveHelper_dt_basic.respond();
							}
						});

		/* END BASIC */
	};

	// load related plugins

	loadScript(
			"assets/js/datatable/jquery.dataTables.min.js",
			function() {
				loadScript(
						"assets/js/datatable/dataTables.tableTools.min.js",
						function() {
							loadScript(
									"assets/js/datatable/dataTables.bootstrap.min.js",
									function() {
										loadScript(
												"assets/js/datatable/datatables.responsive.min.js",
												pagefunction);
									});
						});
			});
	function resetPurchaseForm(){
		 var validator = $( "#managePurchaseForm" ).validate();
		 validator.resetForm();
		   $('label').removeClass("state-success");
		   $('label').removeClass("state-error");
		   $("#planId").val("");
		   $("#planDesc").val("");
		   $("#planAdditionalDesc").val("");
		   $("#price").val("");
		   $("#validityMonth").val("");
		   $("#maxUsers").val("");
		  // $("#applicationName").selectpicker("val", "");
		   //$("#planName").selectpicker("val", "");
		   $('.selectpicker').selectpicker('refresh');
		  }
	
	// Form Validations
	$("#managePurchaseForm").validate({
		// Rules for form validation
		rules : {
			planid : {
				required :true,
			},
			
			plandesc : {
				required : true,
				maxlength : 2000
			},
			
			price : {
				required : true,
				
			},
			validitymonth:{
				required : true,
			},
			maxusers :{
				required : true,
			}
		},
			
		// Messages for form validation
		messages : {
			planid : {
				required :" Please select the application name",
			},
			
			plandesc : {
				required :"Enter the description about the application",
				
			},
			price : {
				required : "Enter the price of the application"
				
			},
			validitymonth:{
				required : "Enter the validity in months",
			},
			maxusers :{
				required : "Enter the maximum number of users",
			}
		},

		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		},
		submitHandler: function(form) {
            form.submit();
        }
	});
</script> -->

<!-- END #MAIN CONTENT -->