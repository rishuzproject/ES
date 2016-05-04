<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 <link rel="stylesheet" type="text/css" media="screen" href="assets/css/custom_manage_accounts.css"> 

<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-lg fa-fw fa-gear txt-color-blue"></i><spring:message code="ManageAccounts.Pagehead.Heading"/> <span>&gt;
				<spring:message code="ManageAccounts.Pagehead.SubHeading"/></span>
		</h1>
	</div>
	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
		<ul id="sparks" class="">
			<li class="sparks-info">
				<h5>
					<spring:message code="ManageAccounts.Pagehead.TotalAccounts"/> <span class="txt-color-blue" id="totalAccounts"></span>
				</h5>
			</li>
		</ul>
	</div>
</div>
<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">

	<!-- MODAL PLACE HOLDER -->
	
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
					
				</header>

				<div role="content">
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
					</div>
					<div class="widget-body no-padding">

						<div id="dt_basic_wrapper"
							class="dataTables_wrapper form-inline no-footer">
							<table id="manageAccountsTable"
								class="table table-striped table-bordered table-hover dataTable no-footer"
								width="100%" role="grid" aria-describedby="dt_basic_info"
								style="width: 100%;">
								<thead>
									<tr role="row">
										<th data-hide="phone" class="sorting_asc" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-sort="ascending"
											aria-label=": activate to sort column ascending"
											style="width: 10px;" class="center"></th>
										<th data-hide="phone" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-sort="ascending"
											aria-label="Customer Name: activate to sort column ascending"
											style="width: 150px;" class="center"><spring:message code="ManageAccounts.Datatable.TableHead.Column1"/></th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label="Domain Name: activate to sort column ascending"
											style="width: 100px;"><spring:message code="ManageAccounts.Datatable.TableHead.Column2"/></th>
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="Status: activate to sort column ascending"
											style="width: 50px;"><spring:message code="ManageAccounts.Datatable.TableHead.Column3"/></th>
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="Email: activate to sort column ascending"
											style="width: 20px;"><spring:message code="ManageAccounts.Datatable.TableHead.Column4"/></th>
										<th data-hide="phone" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label="Activate Button: activate to sort column ascending"
											style="width: 30px;"><spring:message code="ManageAccounts.Datatable.TableHead.Column5"/></th>
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="Deactivate Button: activate to sort column ascending"
											style="width: 30px;"><spring:message code="ManageAccounts.Datatable.TableHead.Column6"/></th>
									</tr>
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

<script type="text/javascript" src="assets/js/manageAccount.js"></script>
