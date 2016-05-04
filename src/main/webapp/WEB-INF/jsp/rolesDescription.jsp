<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script type="text/javascript"
	src="assets/js/roleDescription.js"></script>
<head>
<style>
.colorBlue
{color:#57889c}
.widget-body.no-padding {
margin: -13px -6px 0 !important;
}
</style>
</head>

<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title">
			<i class="glyphicon glyphicon-user colorBlue"></i> Roles <span>&gt; 
			Description </span>
		</h1>
	</div>
	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
		<ul id="sparks" class="">
			<li class="sparks-info">
				<h5>
					Total Users <span class="txt-color-blue">171</span>
				</h5>
			</li>
			<li class="sparks-info">
				<h5>
					Active Users <span class="txt-color-purple"><i
						class="fa fa-thumbs-o-up" data-rel="bootstrap-tooltip"
						title="Increased"></i>&nbsp;100</span>
				</h5>
			</li>
			<li class="sparks-info">
				<h5>
					Inactive Users <span class="txt-color-greenDark"><i
						class="fa fa-thumbs-o-down"></i>&nbsp;71</span>
				</h5>
			</li>
		</ul>
	</div>
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
							class="fa fa-expand "></i></a> 
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
					<div class="btn-group modalBtnGroup" >
						<button class=" btn customBtnName" data-toggle="modal" data-target="#addRoleModal">
							<span class="fa fa-plus" style="color:#fff"></span>
						</button>
						<label class="customLabel">Generate new 
							role</label>
					</div>
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
							<table id="roleDetailsTable"
								class="table table-striped table-bordered table-hover dataTable no-footer"
								width="100%" role="grid" aria-describedby="dt_basic_info"
								style="width: 100%;">
								<thead>
									<tr role="row">
										<th data-hide="phone" class="sorting_asc" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-sort="ascending"
											aria-label="ID: activate to sort column ascending"
											style="width: 41px;"><i
											class="fa fa-fw fa-key txt-color-blue text-muted hidden-md hidden-sm hidden-xs"></i>
											 Id</th>
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="Email: activate to sort column ascending"
											style="width: 110px;"><i
											class="fa fa-fw fa-user txt-color-blue text-muted hidden-md hidden-sm hidden-xs"></i>
											Role </th>
										<th data-hide="phone" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Phone: activate to sort column ascending"
											style="width: 95px;">
											Role Description </th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" : activate to sort column ascending"
											style="width: 30px;"></th>
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
			</div>
			<!-- Till Here -->
		</article>
		<!-- WIDGET END -->
	</div>
	<!-- end row -->
	<!-- end row -->
</section>
<!-- end widget grid -->

<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
	<!-- Button trigger modal -->
	
	<!-- MODAL PLACE HOLDER -->
	<div class="modal fade" id="addRoleModal" tabindex="-1" role="dialog"
		aria-labelledby="remoteModalLabel" aria-hidden="true"  data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">


				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick="resetUserForm()"><span class="badge pull-right inbox-badge customColor">&times;</span></button>
					<h4 class="modal-title" id="modelTitleId"><i class="glyphicon glyphicon-user colorBlue"></i> Add New Role</h4>
				</div>
				<div class="modal-body no-padding">

					<form  method="POST" id="roleDescriptionForm"
						class="smart-form">
                         
						<fieldset>
							<section>
								<label class="input"> <i
									class="icon-append fa fa-user"></i> <input type="text"
									name="roleName" id="roleName" placeholder="Role Name"> <b
									class="tooltip tooltip-bottom-right">Role Name</b>
									<input type="hidden" id="roleAction" name="roleAction"> 
						<input type="hidden" id="roleId" name="roleId"> 
								</label>
							</section>
							<section>
										<label class="textarea"> 
										<i class="icon-append fa fa-comment"></i>
										<textarea cols="4" maxlength="1000" name="description" id="roleDescription" placeholder="Role Description"></textarea>
										<b class="tooltip tooltip-bottom-right">Enter the role description</b>
										<font color="red" size="1px"><p align="right">(max : 1000 characters)</p></font><br>
									</label>
								</section>
						</fieldset>
						<footer>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" >Cancel</button>
							<button type="button" class="btn btn-primary" id="reset" >
								<i class="fa fa-undo"></i> &nbsp;Reset
							</button>
							<button type="submit" class="btn btn-primary"
								id="saveandcontinue" onclick="setAction('saveandcontinue');">
								<i class="fa fa-forward"></i> &nbsp;Save & Continue
							</button>
							<button type="submit" class="btn btn-primary" id="saveButtonId" onclick="setAction('save');">
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


<!-- END #MAIN CONTENT -->