<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<head>
<style>

.itemDbErrorBlock
{
  box-shadow: 3px 5px 11px #836463;border-right: 5px solid #953b39;background:#fff;color:#836463;height: 160px; overflow-y: auto
}
.itemDbErrorHeader
{
 border-bottom: 1px solid #953b39;border-right: 5px solid #953b39;margin-bottom:0px !important
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
</head>

<!-- #MAIN CONTENT -->
<script type="text/javascript" src="assets/js/itemDetail.js"></script>
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-sitemap fa-fw txt-color-blue "></i>
			<spring:message code="ManageItemDB.PageHead.Heading" />
			<span> &gt; <spring:message
					code="ManageItemDB.PageHead.SubHeading" />
			</span>
		</h1>
	</div>
	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
		<ul id="sparks" class="">
			<li class="sparks-info">
				<h5>
					<spring:message code="ManageItemDB.PageHead.Total" />
					<span class="txt-color-blue" id="totalItems"></span>
				</h5>
			</li>
		</ul>
	</div>
</div>
<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">

	<div class="modal fade" id="itemRemoteModal" tabindex="-1"
		role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true"
		data-dismiss="modal"   data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						onclick="resetItemForm()" aria-hidden="true"
						onclick="resetItemForm()">
						<span class="badge pull-right inbox-badge customColor">×</span>
					</button>
					<h4 class="modal-title" id="itemModalTitle">
						<i class="fa fa-sitemap fa-fw txt-color-blue "></i>&nbsp;
						<spring:message code="ManageItemDB.Form.AddNewItem" />
					</h4>
				</div>
				<div class="modal-body no-padding">
					<div class="fuelux">
						<div class="wizard-example" style="display: none;">
							<div class="wizard" id="MyWizard" style="border-radius: 0;">
								<ul class="steps">
									<li class="active" data-target="#step1"><span
										class="badge badge-info">1</span>Form Page 1<span
										class="chevron"></span></li>
									<li data-target="#step2" class=""><span class="badge">2</span>Form
										Page 2 Info.<span class="chevron"></span></li>
									<li data-target="#step3" class=""><span class="badge">3</span>Form
										Page 3<span class="chevron"></span></li>
								</ul>
							</div>
						</div>
						<form id="manageItemForm" class="smart-form" method="post">
							<div class="step-content" style="min-height: 370px;">
								<div class="step-pane active" id="step1">
									<fieldset>
										<input type="hidden" name="itemTypeAction" id="itemTypeAction" value="save">
										<input type="hidden" name="itemDbNumber" id="itemDbNumber">
										<input type="hidden" name="submitId" id="submitId">
										<section>
											<div class="row">
												<div class="col col-lg-12">
													<label class="input"><i
														class="icon-append fa fa-sort-alpha-asc mandatoryStyle"></i> <input
														type="text" name="itemDescription" id="itemDescription"
														placeholder="Item Description"> <b
														class="tooltip tooltip-bottom-right">Item Description</b>
													</label>
												</div>
											</div>
										</section>
										<div class="row">
											<section class="col col-6">
												<label class="input"> <i
													class="icon-append fa fa-sort-numeric-desc mandatoryStyle"></i> <input
													type="text" name="itemCode" id="itemCode"
													placeholder="Item Code"> <b
													class="tooltip tooltip-bottom-right">Item Code</b>
												</label>
											</section>
											<section class="col col-6">
												<label class="select"> <select
													class="form-control selectpicker show-tick"
													id="categoryLevel1" name="categoryLevel1">
														<option value="">Category 1</option>
														
												</select>
												<i class="mandatoryIconStyle"></i>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="select"> <select
													class="form-control selectpicker show-tick"
													id="categoryLevel2" name="categoryLevel2">
														<option value="">Category 2</option>
												</select>
												<i></i>
												</label>
											</section>
											<section class="col col-6">
												<label class="select"> <select
													class="form-control selectpicker show-tick"
													id="categoryLevel3" name="categoryLevel3">
														<option value="">Category 3</option>
												</select>
												<i></i>
												</label>
											</section>

										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="priceCode" id="priceCode"
													placeholder="Price Code"> <b
													class="tooltip tooltip-bottom-right">Price Code</b> </label>
											</section>
											<section class="col col-6">
												<label class="input"> <i
													class="icon-append fa fa-sort-alpha-asc"></i> <input
													type="text" name="materialUnit" id="materialUnit"
													placeholder="Material Unit"> <b
													class="tooltip tooltip-bottom-right">Material Unit</b>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input"> <i
													class="icon-append fa fa-usd"></i> <input type="text"
													name="materialPrice" id="materialPrice"
													placeholder="Material Price"> <b
													class="tooltip tooltip-bottom-right">Material Price</b>
												</label>
											</section>
											<section class="col col-6">
												<label class="input"> <i class="icon-append fa">%</i>
													<input type="text" name="materialDiscount"
													id="materialDiscount" placeholder="Material Discount">
													<b class="tooltip tooltip-bottom-right">Material
														Discount</b>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input"> <i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="materialNetCost" id="materialNetCost"
													placeholder="Material Net Cost"> <b
													class="tooltip tooltip-bottom-right">Material Net Cost</b>

												</label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-alpha-asc"></i> <input
													type="text" name="netCondition" id="netCondition"
													placeholder="Net Condition"> <b
													class="tooltip tooltip-bottom-right">Net Condition</b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-alpha-asc"></i> <input
													type="text" name="materialCondition" id="materialCondition"
													placeholder="Material Condition"> <b
													class="tooltip tooltip-bottom-right">Material Condition</b>
												</label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="priceFactor" id="priceFactor"
													placeholder="Price Factor"> <b
													class="tooltip tooltip-bottom-right">Price Factor</b> </label>
											</section>
										</div>
									</fieldset>
								</div>

								<div class="step-pane" id="step2">
									<fieldset>
										<div class="row">
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-alpha-asc"></i> <input
													type="text" name="laborUnit" id="laborUnit"
													placeholder="Labor Unit"> <b
													class="tooltip tooltip-bottom-right">Labor Unit</b> </label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="estimatingLevel" id="estimatingLevel"
													placeholder="Estimating Level"> <b
													class="tooltip tooltip-bottom-right">Estimating Level</b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="col2Labor" id="col2Labor"
													placeholder="Col 2 Labor"> <b
													class="tooltip tooltip-bottom-right">Col 2 Labor</b> </label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="col3Labor" id="col3Labor"
													placeholder="Col 3 Labor"> <b
													class="tooltip tooltip-bottom-right">Col 3 Labor</b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="neca1" id="neca1" placeholder="NECA 1">
													<b class="tooltip tooltip-bottom-right">NECA 1</b> </label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="neca2" id="neca2" placeholder="NECA 2">
													<b class="tooltip tooltip-bottom-right">NECA 2</b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="neca3" id="neca3" placeholder="NECA 3">
													<b class="tooltip tooltip-bottom-right">NECA 3</b> </label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-alpha-asc"></i> <input
													type="text" name="laborCondition" id="laborCondition"
													placeholder="Labor Condition"> <b
													class="tooltip tooltip-bottom-right">Labor Condition</b> </label>
											</section>

										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-alpha-asc"></i> <input
													type="text" name="basedOn" id="basedOn"
													placeholder="Based On"> <b
													class="tooltip tooltip-bottom-right">Based On</b> </label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="laborCode" id="laborCode"
													placeholder="Labor Code"> <b
													class="tooltip tooltip-bottom-right">Labor Code</b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="actualLabor" id="actualLabor"
													placeholder="Actual Labor"> <b
													class="tooltip tooltip-bottom-left">Actual Labor</b> </label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="belcoLabor" id="belcoLabor"
													placeholder="Belco Labor"> <b
													class="tooltip tooltip-bottom-right">Belco Labor</b> </label>
											</section>
										</div>
									</fieldset>
								</div>

								<div class="step-pane" id="step3">
									<fieldset>
										<div class="row">
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-comment"></i> <input type="text"
													name="belcoLaborDescription" id="belcoLaborDescription"
													placeholder="Belco Labor Description"> <b
													class="tooltip tooltip-bottom-right">Belco Labor
														Description</b> </label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="belcoMaterial" id="belcoMaterial"
													placeholder="Belco Material"> <b
													class="tooltip tooltip-bottom-right">Belco Material</b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-comment"></i> <input type="text"
													name="belcoMaterialDescription"
													id="belcoMaterialDescription"
													placeholder="Belco Material Description"> <b
													class="tooltip tooltip-bottom-right">Belco Material
														Description</b> </label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-alpha-asc"></i> <input
													type="text" name="weightUnit" id="weightUnit"
													placeholder="Weight Unit"> <b
													class="tooltip tooltip-bottom-right">Weight Unit</b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="weight" id="weight" placeholder="Weight">
													<b class="tooltip tooltip-bottom-right">Weight</b> </label>
											</section>
											<section class="col col-6">
												<label class="input"> <i
													class="icon-append fa fa-suitcase"></i> <input type="text"
													name="manufacturerName" id="manufacturerName"
													placeholder="Manufacturer Name"> <b
													class="tooltip tooltip-bottom-right">Manufacturer Name</b>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-alpha-asc"></i> <input
													type="text" name="catalogNumber" id="catalogNumber"
													placeholder="Catalog Number"> <b
													class="tooltip tooltip-bottom-right">Catalog Number</b> </label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-alpha-asc"></i> <input
													type="text" name="supplierName" id="supplierName"
													placeholder="Supplier Name"> <b
													class="tooltip tooltip-bottom-right">Supplier Name</b> </label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="supplierCode" id="supplierCode"
													placeholder="Supplier Code"> <b
													class="tooltip tooltip-bottom-right">Supplier Code</b> </label>
											</section>
											<section class="col col-6">
												<label class="input"> <i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="reference" id="reference"
													placeholder="Reference"> <b
													class="tooltip tooltip-bottom-right">Reference</b>
												</label>
											</section>
										</div>
										<div class="row">
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="universalItemCode" id="universalItemCode"
													placeholder="Universal Item Code"> <b
													class="tooltip tooltip-bottom-right">Universal Item
														Code</b> </label>
											</section>
											<section class="col col-6">
												<label class="input"><i
													class="icon-append fa fa-sort-numeric-desc"></i> <input
													type="text" name="quickTakeoffCode" id="quickTakeoffCode"
													placeholder="Quick Takeoff Code"> <b
													class="tooltip tooltip-bottom-right">Quick Takeoff Code</b>
												</label>
											</section>
										</div>
									</fieldset>
								</div>
							</div>
							<footer>
								<input class="btn btn-default pull-left hide" id="btnWizardPrev"
									type="button" value="Back"> <input
									class="btn btn-primary" id="btnWizardNext" type="button"
									value="Next"> <span class="hide" id="submitbuttons">
									<button type="button" id = "cancel" class="btn btn-default"
										data-dismiss="modal" onclick="resetItemForm()">
										<spring:message code="Button.Cancel" />
									</button>
									<button type="button" class="btn btn-primary" id="reset"
										onclick="resetItemForm()">
										<i class="fa fa-undo"></i>
										<spring:message code="Button.Reset" />
									</button>
									<button type="submit" class="btn btn-primary ladda-button"
										id="saveandcontinue" onclick="submitType(this.id,this)">
										<i class="fa fa-forward"></i>
										<spring:message code="Button.SaveContinue" />
									</button>
									<button type="submit" class="btn btn-primary ladda-button"
										data-style="expand-right" id="save"
										onclick="submitType(this.id,this)">
										<i class="fa fa-floppy-o"></i>
										<spring:message code="Button.Save" />
									</button>
								</span>
							</footer>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- MODAL PLACE HOLDER -->
	<div class="modal fade" id="itemDbUploadModal" tabindex="-1"
		role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true"  data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true" onclick="cancelItemDbUploadForm()"><span class="badge pull-right inbox-badge customColor">&times;</span></button>
						<h4 class="modal-title" id="modalTitleId"><i class="fa fa-users fa-fw txt-color-blue"></i> Add New Item</h4>
				</div>
				<div class="modal-body no-padding">
					<form id="itemDbUploadForm" method="POST" class="smart-form">
						<input type="hidden" id="confirmItemDbUploadId" name="confirmItemDbUploadId">
						<fieldset>
						<section>
								<label for="itemDbTemplateFileLabel" class="input input-file">
									<div class="button">
										<input type="file" name="itemDbTemplateFile" id="itemDbTemplateFile"
											onchange="this.parentNode.nextSibling.value = this.value">
										Browse
									</div><input type="text" name="itemDbFileName" id="itemDbFileName"
									placeholder="Choose document to Upload"
									readonly="">
								</label>
							</section>
							
						</fieldset>
						<footer>
							<button type="submit" class="btn btn-primary button" id="saveItemDbUpload">
									<i class="fa fa-floppy-o"></i> &nbsp; Save
							</button>
							<button type="button" class="btn btn-default"
									data-dismiss="modal" onclick="cancelItemDbUploadForm()">Cancel
							</button>
						</footer>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- MODAL FOR CONFIRMATION -->						
					
	<div class="modal fade" id="itemDbUploadConfirmation" tabindex="-1"
  role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true"  data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog">
   <div class="modal-content">
	<div class="modal-header">
      <button type="button" class="close" data-dismiss="modal"
       aria-hidden="true" onclick="resetItemDbConfirmation()"><span class="badge pull-right inbox-badge customColor">&times;</span></button>
      <h4 class="modal-title" id="itemDbConfirmModalTitleId"><i class="fa fa-users fa-fw txt-color-blue"></i> Error in the template File</h4>
    </div>
    <div class="modal-body no-padding">
    <fieldset>
    	<section style="text-align: center;" id = "itemDbConfirmationHeader">
			<div class="space"></div>
				<h4 class="smaller">
					<b>Error Occured while uploading</b>
				</h4>
				<ul class="list-unstyled spaced inline bigger-110 margin-15">
					<li><i class="ace-icon fa fa-hand-o-right blue"></i> Select Continue to insert the error values as it is</li>
					<li><i class="ace-icon fa fa-hand-o-right blue"></i> Select Cancel to cancel and upload again</li>
				</ul>
		</section>
    	<section id="itemDbErrorSection" style="display:none;">
			<div class="alert alert-danger itemDbErrorHeader">
				<strong>Error Occured !</strong>
			</div>
			<div class="alert alert-danger fade in itemDbErrorBlock" id="errorBlock"> </div>
		</section>
    </fieldset>
    <footer>
    	<button type="submit" class="btn btn-primary button" id="saveItemDbConfirmUpload" onclick="setItemDbUploadConfirmation(this.id)">
				<i class="fa fa-forward"></i> &nbsp;Continue
	 	</button>
	 	<button type="button" class="btn btn-default" id = "cancelItemDbUpload"
	 	 	data-dismiss="modal" onclick="setItemDbUploadConfirmation(this.id);resetItemDbConfirmation();cancelItemDbUploadForm()">Cancel
	 	</button>
   	</footer>
    </div>
   </div>
  </div>
 </div>				

<section id="widget-grid" class="">
	<div class="row">
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
					&nbsp; <a href="#" data-toggle="modal" data-target="#itemRemoteModal" id="itemRemoteAnchorTag" > <span
						class="widget-icon" style="color: #E4E4E4;"> <i
							class="fa fa-plus-square"></i>
					</span>
						<h2 style="color: #E4E4E4">
							&nbsp;
							<spring:message code="ManageItemDB.Form.AddNewItem" />
						</h2>
					</a><%-- &nbsp;|&nbsp;&nbsp;
					
					<a href="" data-toggle="modal" data-target="#uploadModal" onclick="setModuleName('<spring:message code="Upload.ItemDb"/>')">>
							 <span class="widget-icon" style="color: #E4E4E4;"> <i class="fa fa-plus-square"></i>
							 </span>
								<h2 style="color: #E4E4E4;">Upload File</h2>
						  </a> --%>
					
					 <span class="jarviswidget-loader"><i
						class="fa fa-refresh fa-spin"></i></span>
				</header>
				<div role="content">
					<div class="jarviswidget-editbox"></div>
					<div class="widget-body no-padding">
						<div id="dt_basic_wrapper"
							class="dataTables_wrapper form-inline no-footer">
							<table id="itemDetailTable"
								class="table table-striped table-bordered table-hover dataTable no-footer"
								role="grid" aria-describedby="dt_basic_info"
								style="width: 100%;">
								<thead>
									<tr role="row">
										<th data-hide="phone" class="sorting_asc" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-sort="ascending"
											aria-label="ID: activate to sort column ascending"
											style="width: 41px;"><spring:message
												code="ManageItemDB.DataTable.TableHead.Column1" /></th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 110px;"><i
											class="icon-append fa fa-sort-alpha-asc"></i> &nbsp; <spring:message
												code="ManageItemDB.DataTable.TableHead.Column2" /></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Date: activate to sort column ascending"
											style="width: 97px;"><spring:message
												code="ManageItemDB.DataTable.TableHead.Column3" /></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Date: activate to sort column ascending"
											style="width: 97px;"><spring:message
												code="ManageItemDB.DataTable.TableHead.Column4" /></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Date: activate to sort column ascending"
											style="width: 97px;"><spring:message
												code="ManageItemDB.DataTable.TableHead.Column5" /></th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 110px;"><i class="icon-append fa">%</i>
											&nbsp; <spring:message
												code="ManageItemDB.DataTable.TableHead.Column6" /></th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Name: activate to sort column ascending"
											style="width: 110px;"><i
											class="fa fa-fw fa-usd text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message
												code="ManageItemDB.DataTable.TableHead.Column7" /></th>
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
				</div>
			</div>
		</article>
	</div>
</section>
<!--  <script type="text/javascript" src="assets/js/loader.min.js"></script> -->
