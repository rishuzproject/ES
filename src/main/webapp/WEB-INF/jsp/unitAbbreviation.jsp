<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<head>

</head>

<!-- #MAIN CONTENT -->
<script type="text/javascript" src="assets/js/jquery.gritter.min.js"></script>
<link href="assets/css/jquery.gritter.css" rel="stylesheet">

<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-6">
	<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-lg fa-fw fa-weibo txt-color-blue"></i> <spring:message code="UnitAbbreviation.Pagehead.Heading"/> <span>&gt; <spring:message code="UnitAbbreviation.Pagehead.Subheading"/>  </span>
		</h1>
	</div>
	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-6">
		<ul id="sparks" class="">
			<li class="sparks-info">
				<h5>
					 <spring:message code="UnitAbbreviation.Pagehead.TotalUnits"/><span class="txt-color-blue" id="totalUnitsCount"></span>
				</h5>
			</li>
		</ul>
	</div>
</div>

<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">

	<!-- Button trigger modal -->


	<!-- MODAL PLACE HOLDER -->
	<div class="modal fade" id="unitAbbreviationModal" tabindex="-1"
		role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true"  data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick=""><span class="badge pull-right inbox-badge customColor">×</span></button>
					<h4 class="modal-title" id="modalTitleId"><i class="fa fa-lg fa-fw fa-weibo txt-color-blue"></i><spring:message code="UnitAbbreviation.Modal.Label.AddNewUnit"/></h4>
				</div>
				<div class="modal-body no-padding">

					<form id="unitAbbreviationForm" method="POST" class="">
						<fieldset>
						<input type="hidden" name="unitAbbreviationAction" id ="unitAbbreviationAction" value="save">
						<input type="hidden" name="abbreviationId" id ="abbreviationId">
						<input type="hidden" name="submitId" id="submitId">
						<div class="smart-form">
								<section>
								<div class="col col-lg-12">
										<label class="input"><i
											class="icon-append fa fa-sort-alpha-asc mandatoryStyle"></i> <input type="text"
											class="form-control" name="abbreviationName"
											id="abbreviationName" placeholder="<spring:message code="UnitAbbreviation.Modal.Placeholder.AbbreviationName"/>"> <b
											class="tooltip tooltip-bottom-right"><spring:message code="UnitAbbreviation.Modal.Tooltips.AbbreviationName"/></b>
										</label><br>
									</div>
								</section>
								<section>
									<div class="col col-lg-12">
										<label class="input"><i
											class="icon-append fa fa-sort-alpha-asc mandatoryStyle"></i> <input type="text"
											class="form-control" name="abbreviationMeaning"
											id="abbreviationMeaning" placeholder="<spring:message code="UnitAbbreviation.Modal.Placeholder.AbbreviationMeaning"/>"> <b
											class="tooltip tooltip-bottom-right"><spring:message code="UnitAbbreviation.Modal.Tooltips.AbbreviationMeaning"/></b>
										</label><br>
									</div>
								</section>
						</div>
								
							
						</fieldset>

                 <div class="smart-form">
						<footer>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" onclick="resetUnitAbbreviationForm()"><spring:message code="Button.Cancel"/></button>
							<button type="button" class="btn btn-primary" id="reset"
								onclick="resetUnitAbbreviationForm()">
								<i class="fa fa-undo"></i> &nbsp;<spring:message code="Button.Reset"/>
							</button>
							<button type="submit" class="btn btn-primary ladda-button" data-style="expand-right"
								id="saveandcontinue" onclick="submitType(this.id,this)">
								<i class="fa fa-forward"></i> &nbsp;<spring:message code="Button.SaveContinue"/>
							</button>
							<button type="submit" class="btn btn-primary ladda-button" data-style="expand-right" id="save" onclick="submitType(this.id,this)">
								<i class="fa fa-floppy-o"></i> &nbsp;<spring:message code="Button.Save"/>
							</button>
						</footer>
                   </div>
                   
					</form>
				</div>


			</div>
		</div>
	</div>
	<!-- END MODAL -->

</div>



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
					
					&nbsp; <a href="" data-toggle="modal"
						data-target="#unitAbbreviationModal" onclick=" resetUnitAbbreviationForm();"> <span
						class="widget-icon" style="color: #E4E4E4;"> <i
							class="fa fa-plus-square"></i>
					</span>
						<h2 style="color: #E4E4E4;"> <spring:message code="UnitAbbreviation.Modal.Label.AddNewUnit"/></h2>
					</a> 
					
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

						<div id="dt_basic_wrapper"
							class="dataTables_wrapper form-inline no-footer">
							<table id="unitAbbreviationTable"
								class="table table-striped table-bordered table-hover dataTable no-footer"
								width="100%" role="grid" aria-describedby="dt_basic_info"
								style="width: 100%;">
								<thead>
									<tr role="row">
									   <th data-hide="phone" class="sorting_asc" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-sort="ascending"
											aria-label="ID: activate to sort column ascending"
											style="width: 20px;"><spring:message code="UnitAbbreviation.Table.Column.Index"/></th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Comp Name: activate to sort column ascending"
											style="width: 150px;"><spring:message code="UnitAbbreviation.Table.Column.AbbreviationName"/></th>	
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Comp Name: activate to sort column ascending"
											style="width: 150px;"><spring:message code="UnitAbbreviation.Table.Column.AbbreviationMeaning"/></th>
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
<!-- end widget grid -->

<!-- END #MAIN CONTENT -->
<script type="text/javascript" src="assets/js/unitAbbreviation.js"></script>