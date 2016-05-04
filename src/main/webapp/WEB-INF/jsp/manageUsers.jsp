<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="assets/js/manageUsers.js"></script>
<!-- #MAIN CONTENT -->


	<!-- /.modal -->
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title">
			<i class="fa fa-user fa-fw txt-color-blue"></i> <spring:message code="ManageUser.PageHead.Heading"/> <span>&gt; 
			<spring:message code="ManageUser.PageHead.SubHeading"/> </span>
		</h1>
	</div>
	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
		<ul id="sparks" class="">
			<li class="sparks-info">
				<h5>
					<spring:message code="ManageUser.PageHead.TotalUserInfo"/> <span class="txt-color-blue" id="totalUsersId">0</span>
				</h5>
			</li>
			<li class="sparks-info">
				<h5>
					<spring:message code="ManageUser.PageHead.ActiveUserInfo"/> <span class="txt-color-purple" id="activeUsersId"><i
						class="fa fa-thumbs-o-up" data-rel="bootstrap-tooltip"
						title="Increased"></i>0</span>
				</h5>

			</li>
			<li class="sparks-info">
				<h5>
					<spring:message code="ManageUser.PageHead.InactiveUserInfo"/> <span class="txt-color-greenDark" id="inactiveUsersId"><i
						class="fa fa-thumbs-o-down"></i>0</span>
				</h5>

			</li>
		</ul>
	</div>
</div>
<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">

	<!-- Button trigger modal -->


	<!-- MODAL PLACE HOLDER -->
	<div class="modal fade" id="remoteModal" tabindex="-1" role="dialog"
		aria-labelledby="remoteModalLabel" aria-hidden="true"  data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">


				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick="resetUserForm()"><span class="badge pull-right inbox-badge customColor">&times;</span></button>
					<h4 class="modal-title" id="modelTitleId"><i class="fa fa-user fa-fw txt-color-blue"></i> <spring:message code="ManageUser.Form.AddNewUser"/></h4>
				</div>
				<div class="modal-body no-padding">

					<form  method="POST" id="manage_user_form"
						class="smart-form">

						<input type="hidden" id="userId" name="userId"> 
						<input type="hidden" id="userActionType" name="userActionType"> 
						<fieldset>
							<section>
								<label class="input"> <i
									class="icon-append fa fa-envelope mandatoryStyle"></i> <input type="email"
									name="emailId" id="emailId" placeholder="<spring:message code="ManageUser.label.EmailId"/>"> <b
									class="tooltip tooltip-bottom-right"><spring:message code="ManageUser.tooltip.EmailID"/></b>
								</label>
							</section>
							<section>
								<label class="input" id="pwdLabel"> <i class="icon-append fa fa-lock mandatoryStyle"></i>
									<input type="password" name="password" id="password"
									placeholder="<spring:message code="ManageUser.label.Password"/>"> <b
									class="tooltip tooltip-bottom-right"><spring:message code="ManageUser.tooltip.Password"/></b>
								</label>
							</section>
							<section>
								<label class="input" id="confPwdLabel"> <i class="icon-append fa fa-lock mandatoryStyle"></i>
									<input type="password" name="confirmPassword"
									id="confirmPassword" placeholder="<spring:message code="ManageUser.label.ConfirmPassword"/>"> <b
									class="tooltip tooltip-bottom-right"><spring:message code="ManageUser.tooltip.ConfirmPassword"/></b>
								</label>
							</section>
							<hr>
							<br>
							<div class="row">
								<section class="col col-lg-6">
									<label class="select"> <select
														class="form-control  show-tick" id="organisationName" name="organisationName">
															<option value=""><spring:message
																	code="ManageUser.label.OrganisationName" /></option>
	
													</select> <i></i>
													</label>
								</section>
								<section class="col col-lg-6">
									<label class="select"> <select
														class="form-control  show-tick" id="departmentName" name="departmentName">
															<option value=""><spring:message
																	code="ManageUser.label.DepartmentName" /></option>
	
													</select> <i></i>
													</label>
								</section>
							</div>
							<div class="row">
								<section class="col col-lg-6">
									<label class="select"> <select
														class="form-control  show-tick" id="designationName" name="designationName">
															<option value=""><spring:message
																	code="ManageUser.label.DesignationName" /></option>
	
													</select> <i></i>
													</label>
								</section>
								<section class="col col-lg-6">
									<label class="select"> <select
														class="form-control  show-tick" id="supervisorName" name="supervisorName">
															<option value=""><spring:message
																	code="Jobs.Information.label.ChooseSupervisor" /></option>
	
													</select> <i></i>
													</label>
								</section>
							</div>
							<div class="row">
								<section class="col col-lg-12">
									<label class="select" style="margin-bottom:10px"> 
										<select class="form-control selectpicker required show-tick" id="userRoles" name="userRoles" multiple>
											<option value="" disabled>Select Application Roles</option>
										</select>
										<div id="errorMessageForRoles"></div>
									</label>
								</section>
							</div>
							<hr>
							<br>
							<div class="row">
								<section class="col col-6">
									<label class="input"> <i class="icon-append fa fa-user mandatoryStyle"></i>
										<input type="text" name="firstName" id="firstName"
										placeholder="<spring:message code="ManageUser.label.FirstName"/>" onKeyUp="settingDefaultNickName()"> <b
										class="tooltip tooltip-bottom-right"><spring:message code="ManageUser.tooltip.FirstName"/></b>
									</label>
								</section>
								<section class="col col-6">
									<label class="input"> <i class="icon-append fa fa-user"></i>
										<input type="text" name="middleName" id="middleName"
										placeholder="<spring:message code="ManageUser.label.MiddleName"/>"> <b
										class="tooltip tooltip-bottom-right"><spring:message code="ManageUser.tooltip.MiddleName"/></b>
									</label>
								</section>
							</div>
							<div class="row">
								<section class="col col-6">
									<label class="input"><i class="icon-append fa fa-user"></i>
										<input type="text" name="lastName" id="lastName"
										placeholder="<spring:message code="ManageUser.label.LastName"/>"> <b
										class="tooltip tooltip-bottom-right"><spring:message code="ManageUser.tooltip.LastName"/></b>
									</label>
								</section>
								<section class="col col-6">
									<label class="input"><i class="icon-append fa fa-user"></i>
										<input type="text" name="nickName" id="nickName"
										placeholder="<spring:message code="ManageUser.label.NickName"/>"> <b
										class="tooltip tooltip-bottom-right"><spring:message code="ManageUser.tooltip.NickName"/></b>
									</label>
								</section>
							</div>
							<hr>
							<br>
							<div class="row">
								<section class="col col-6">
									<label class="select"> <%-- <input type="text"
										name="phoneCarrier" id="phoneCarrier"
										placeholder="<spring:message code="ManageUser.label.PhoneCarrier"/>">  --%>
										<select class="form-control show-tick" id="phoneCarrier" name="phoneCarrier">

															<option value="" >--Select PhoneCarrier--</option>
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
													</select><i class=""></i><b
										class="tooltip tooltip-bottom-right"><spring:message code="ManageUser.tooltip.PhoneCarrier"/></b>
									</label>
								</section>
								<section class="col col-6">
									<label class="input"> <i
										class="icon-append fa fa-phone-square"></i> <input type="text" 
										name="phoneNumber" id="phoneNumber" placeholder="<spring:message code="ManageUser.label.PhoneNumber"/>">
										<b class="tooltip tooltip-bottom-right"><spring:message code="ManageUser.tooltip.PhoneNumber"/></b>
									</label>
								</section>
							</div>
							<hr>
							<br>
							<div class="row">
								<section class="col col-6">
									<label style="margin-top: 3px ;font-size: 14px;font-weight: 500;">
										<spring:message code="ManageUser.label.PreferredContactMode"/> : 
										<b class="tooltip tooltip-bottom-right"><spring:message code="ManageUser.tooltip.PreferredContactMode"/></b>
									</label>
								</section>
								<section class="col col-2">
									<label class="radio">
										<input type="radio" name="preferredContactMode" id="preferredContactMode" value="phone"> 
										<i></i> <spring:message code="ManageUser.radiobutton.option1.PreferredContactMode"/>
									</label>
								</section>
								<section class="col col-2">
									<label class="radio">
										<input type="radio" name="preferredContactMode" id="preferredContactMode" value="email">
										<i></i> <spring:message code="ManageUser.radiobutton.option2.PreferredContactMode"/>
									</label>
								</section>
								<section class="col col-2">
									<label class="radio">
										<input type="radio" name="preferredContactMode" id="preferredContactMode" value="both"> 
										<i></i> <spring:message code="ManageUser.radiobutton.option3.PreferredContactMode"/>
									</label>
								</section>
							</div>
							<hr>
							<br>
							<div class="row">
								<section class="col col-lg-12">
									<label class="checkbox">
										<input type="checkbox" name="isDeptHead" id="isDeptHead"> 
										<i></i> <spring:message code="ManageUser.label.IsDeptHead"/>
									</label>
								</section>
							</div>

						</fieldset>

						<footer>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" onclick="resetUserForm()"><spring:message code="Button.Cancel"/></button>
							<button type="button" class="btn btn-primary" id="reset"
								onclick="resetUserForm()">
								<i class="fa fa-undo"></i> &nbsp;<spring:message code="Button.Reset"/>
							</button>
							<button type="submit" class="btn btn-primary ladda-button" data-style="expand-right"
								id="saveandcontinue" onclick="setUserActionType(this.id,this);">
								<i class="fa fa-forward"></i> &nbsp;<spring:message code="Button.SaveContinue"/>
							</button>
							<button type="submit" class="btn btn-primary ladda-button" data-style="expand-right" 
							        id="saveButtonId" onclick="setUserActionType(this.id,this);">
								<i class="fa fa-floppy-o"></i> &nbsp; <spring:message code="Button.Save"/>
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
							class="fa fa-expand "></i></a> 
					</div>
					&nbsp; <a href="" data-toggle="modal" data-target="#remoteModal" onclick="resetUserForm();">
						<span class="widget-icon" style="color: #E4E4E4;"> <i
							class="fa fa-plus-square"></i>
					</span>
						<h2 style="color: #E4E4E4;"><spring:message code="ManageUser.Form.AddNewUser"/></h2>
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
                      <div class="alert alert-block alert-info">
						<h4><b style="font-size: 15px;"><spring:message code="ManageUser.ListingLabelText.ActiveUsersList"/></b></h4>
					</div>
						<div id="dt_basic_wrapper"
							class="dataTables_wrapper form-inline no-footer">
							<table id="userDetailsTable"
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
											style="width: 110px;"><i
											class="fa fa-fw fa-user text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="ManageUser.DataTable.TableHead.Column2"/></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Nick Name: activate to sort column ascending"
											style="width: 107px;"><i
											class="fa fa-fw fa-user text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="ManageUser.DataTable.TableHead.Column3"/></th>
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="Email: activate to sort column ascending"
											style="width: 100px;"><i
											class="fa fa-fw fa-user text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="ManageUser.DataTable.TableHead.Column4"/></th>
										<th data-hide="phone" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Phone: activate to sort column ascending"
											style="width: 154px;"><i
											class="fa fa-fw fa-phone text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="ManageUser.DataTable.TableHead.Column5"/></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label="Prefered Contact Mode: activate to sort column ascending"
											style="width: 175px;"><i
											class="fa fa-fw fa-phone text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="ManageUser.DataTable.TableHead.Column6"/></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Submitted Date: activate to sort column ascending"
											style="width: 200px;"><i
											class="fa fa-fw fa-calendar txt-color-blue hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="ManageUser.DataTable.TableHead.Column7"/></th>
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
				
				<div role="content">
                  	<br><br>
					<!-- widget content -->
					<div class="widget-body no-padding">
					<div class="alert alert-block alert-info">
						<h4><b style="font-size: 15px;"><spring:message code="ManageUser.ListingLabelText.PendingActivationUsersList"/></b></h4>
					</div>

						<div id="dt_basic_wrapper"
							class="dataTables_wrapper form-inline no-footer">
							<table id="pendingUserDetailsTable"
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
											style="width: 110px;"><i
											class="fa fa-fw fa-user text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="ManageUser.DataTable.TableHead.Column2"/></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Nick Name: activate to sort column ascending"
											style="width: 107px;"><i
											class="fa fa-fw fa-user text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="ManageUser.DataTable.TableHead.Column3"/></th>
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="Email: activate to sort column ascending"
											style="width: 100px;"><i
											class="fa fa-fw fa-user text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="ManageUser.DataTable.TableHead.Column4"/></th>
										<th data-hide="phone" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Phone: activate to sort column ascending"
											style="width: 154px;"><i
											class="fa fa-fw fa-phone text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="ManageUser.DataTable.TableHead.Column5"/></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label="Prefered Contact Mode: activate to sort column ascending"
											style="width: 175px;"><i
											class="fa fa-fw fa-phone text-muted hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="ManageUser.DataTable.TableHead.Column6"/></th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Submitted Date: activate to sort column ascending"
											style="width: 200px;"><i
											class="fa fa-fw fa-calendar txt-color-blue hidden-md hidden-sm hidden-xs"></i>
											<spring:message code="ManageUser.DataTable.TableHead.Column7"/></th>
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
<script>
 $(document).ready(function() {
	$('.selectpicker').selectpicker();
}); 
</script>

<!-- END #MAIN CONTENT -->