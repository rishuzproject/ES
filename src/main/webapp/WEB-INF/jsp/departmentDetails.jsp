<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<head>
<style>
.departmentErrorBlock
{
  box-shadow: 3px 5px 11px #836463;border-right: 5px solid #953b39;background:#fff;color:#836463;height: 160px; overflow-y: auto
}
.deptErrorHeader
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

/* css of the modal */
.toggleDualListBox , .toggleDualListBox1
{
float: right;cursor:pointer
}
.dl-horizontal
{
margin-bottom: 10px !important; padding: 6px !important; border: 1px solid #ccc;
}
.custMarginCss{
margin-bottom:0px !important
}
.custMargin
{
margin-top:10px !important
}
</style>
</head>

<!-- #MAIN CONTENT -->
<script type="text/javascript" src="assets/js/departmentUserMapping.js"></script> 
<script type="text/javascript" src="assets/js/departmentDetails.js"></script>
<script type="text/javascript" src="assets/js/jquery.gritter.min.js"></script>
<link href="assets/css/jquery.gritter.css" rel="stylesheet">
<!-- duallistBox css  -->
<link href="assets/css/plugins.min.css" rel="stylesheet">


<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-gear fa-fw txt-color-blue"></i><spring:message code="ManageDepartment.Pagehead.Heading.Department"/> <span> &gt;<spring:message code="ManageDepartment.Pagehead.SubHeading.ManageDepartments"/> </span>
		</h1>
	</div>
	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
		<ul id="sparks" class="">
			<li class="sparks-info">
				<h5>
					<spring:message code="ManageDepartment.Pagehead.TotalDepartments"/> <span class="txt-color-blue" id="totalDepartments"></span>
				</h5>
			</li>
		</ul>
	</div>
</div>

<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">

	<!-- Button trigger modal -->


	<!-- MODAL PLACE HOLDER -->
<%-- 	<div class="modal fade" id="departmentRemoteModal" tabindex="-1"
		role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true"  data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick=" resetDepartmentDetailsForm()"><span class="badge pull-right inbox-badge customColor">×</span></button>
					<h4 class="modal-title" id="modalTitleId"><spring:message code="ManageDepartment.Form.AddNewDepartment"/></h4>
				</div>
				<div class="modal-body no-padding">

					<form id="departmentDetailsForm" method="POST" class="">
						<fieldset>
						<div class="smart-form">
								<section>
									<div class="col col-lg-12">
										<label class="input"> <input type="text"
											class="form-control" name="departmentName"
											id="departmentName" placeholder="<spring:message code="ManageDepartment.AddNewDepartment.label.DepartmentName"/>"> <b
											class="tooltip tooltip-bottom-right"><spring:message code="ManageDepartment.AddNewDepartment.tooltip.DepartmentName"/></b>
											 <input type="hidden" name="departmentTypeAction" id ="departmentTypeAction" value="save">
											 <input type="hidden" name="departmentId" id ="departmentId">
											 <input type="hidden" name="selectedUsersId" id="selectedUsersId">
											 <input type="hidden" name="submitId" id="submitId">
										</label>
										<br>
									</div>
								</section>
								<section>
									<div class="col col-lg-12" >
										<label class="textarea textarea-resizable"> <i
											class="icon-append fa fa-comment"></i> <textarea
												class="form-control" rows="3" name="departDescription"
												id="departDescription" placeholder="Description"></textarea>
											<b class="tooltip tooltip-bottom-right">Enter the
												department description</b> <font color="red" size="1px">
												<p align="right">(max : 1000 characters)</p></font><br>
										</label>
									</div>
								</section>
						
								<section>
									<div class="col col-lg-12" style="width:100%;padding-bottom: 25px;">
										<dl class="dl-horizontal">
											<dt style="text-align: center;">Department Head :</dt>
											<dd id="departmentHead"> 
											 <i class="fa fa-edit txt-color-blue toggleDualListBox" >&nbsp; </i>
											</dd>
										</dl>
										<dl class="dl-horizontal">
											<dt style="text-align: center;">Department Staff :</dt>
											<dd  id="departmentStaff"> 
											 <i class="fa fa-edit txt-color-blue toggleDualListBox" >&nbsp; </i>
											</dd>
										</dl>
									</div>
								</section>
							</div>
							
							<hr>
									<div class="widget-body hide dualListBoxDiv" style="padding: 20px;">
										<select multiple="multiple"  name="initializeRole" 
											id="initializeRole" style="display: none;">
											
										</select>
									</div>
										
										<div class="widget-body hide dualListBoxDiv1" style="padding: 6px;border: 1px solid #CCC;border-top: none;">
										<select multiple="multiple" size="10" name="``"
											id="initializeStaffList" style="display: none;">
											
										</select>
									</div>
									</fieldset>
									
								
								
						

                 <div class="smart-form">
						<footer>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" onclick="resetDepartmentDetailsForm()"><spring:message code="Button.Cancel"/></button>
							<button type="button" class="btn btn-primary" id="reset"
								onclick="resetDepartmentDetailsForm()">
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
	</div> --%>
	<!-- END MODAL -->

</div>

<!-- MODAL PLACE HOLDER -->
	<div class="modal fade" id="departmentUploadModal" tabindex="-1"
		role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true"  data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true" onclick="cancelDepartmentUploadForm()"><span class="badge pull-right inbox-badge customColor">&times;</span></button>
						<h4 class="modal-title" id="modalUploadTitleId"><i class="fa fa-users fa-fw txt-color-blue"></i> Add New Department</h4>
				</div>
				<div class="modal-body no-padding">
					<form id="departmentUploadForm" method="POST" class="smart-form">
					<input type="hidden" id="className" name="className">
						<input type="hidden" id="confirmProjectUploadId" name="confirmDepartmentUploadId">
						<fieldset>
							<section>
								<label for="departmentTemplateFileLabel" class="input input-file">
									<div class="button">
										<input type="file" name="projectTemplateFile" id="projectTemplateFile"
											onchange="setDeptUploadFileName(this.value)">
										Browse
									</div><input type="text" name="departmentFileName"
								id="departmentFileName" placeholder="Choose document to Upload"
								readonly="">
							</label>
							</section>
							
						</fieldset>
						<footer>
							<button type="submit" class="btn btn-primary button" id="saveDepartmentUpload">
									<i class="fa fa-floppy-o"></i> &nbsp; Save
							</button>
							<button type="button" class="btn btn-default"
									data-dismiss="modal" onclick="cancelDepartmentUploadForm()">Cancel
							</button>
						</footer>
					</form>
				</div>
			</div>
		</div>
	</div>



<div class="modal fade" id="departmentRemoteModal" tabindex="-1"
		role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true"  data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick=" resetDepartmentDetailsForm()"><span class="badge pull-right inbox-badge customColor">×</span></button>
					<h4 class="modal-title" id="modalTitleId"><i class="fa fa-gear fa-fw txt-color-blue"></i> <spring:message code="ManageDepartment.Form.AddNewDepartment"/></h4>
				</div>
				<div class="modal-body no-padding">
					<form id="departmentDetailsForm" method="POST" class="">
						<fieldset>
						<div class="smart-form">
								<section>
									<div class="col col-lg-12">
										<label class="input"> <input type="text"
											class="form-control" name="departmentName"
											id="departmentName" placeholder="<spring:message code="ManageDepartment.AddNewDepartment.label.DepartmentName"/>"> <b
											class="tooltip tooltip-bottom-right"><spring:message code="ManageDepartment.AddNewDepartment.tooltip.DepartmentName"/></b>
											 <input type="hidden" name="departmentTypeAction" id ="departmentTypeAction" value="save">
											 <input type="hidden" name="departmentId" id ="departmentId">
											 <input type="hidden" name="submitId" id="submitId">
											 <input type="hidden" name="selectedUsersId" id="selectedUsersId">
											 <input type="hidden" name="selectedStaffId" id="selectedStaffId">
											 
										</label><br>
									</div>
								</section>
								<section>
									<div class="col col-lg-12" >
										<label class="textarea textarea-resizable"> <i
											class="icon-append fa fa-comment"></i> <textarea
												class="form-control" rows="3" name="departDescription"
												id="departDescription" placeholder="Description"></textarea>
											<b class="tooltip tooltip-bottom-right">Enter the
												department description</b> <font color="red" size="1px"><p align="right">(max : 1000 characters)</p></font><br>
										</label>
									</div>
								</section>
						</div>
								<section>
									<div class="col col-lg-12" style="width:100%;padding-bottom: 25px;">
										<dl class="dl-horizontal" id="departmentHeadID" style="">
											<dt style="text-align: center;">Department Head :</dt>
											<dd id="departmentHead"> <p id="depHead">
											 <i class="fa fa-edit txt-color-blue toggleDualListBox" >&nbsp; </i></p>
											</dd>
										</dl>
										<div class="widget-body hide dualListBoxDiv" style="padding: 6px;border: 1px solid #CCC;border-top: none;">
										<select multiple="multiple" size="10" name="roleName"
											id="initializeRole" style="display: none;">
										
										</select>
									</div>
										<dl class="dl-horizontal" id="departmentStaffID">
											<dt style="text-align: center;">Department Staff :</dt>
											<dd id="departmentStaff"> <p id="depStaff">
											 <i class="fa fa-edit txt-color-blue toggleDualListBox1" >&nbsp; </i></p>
											</dd>
										</dl>
										<div class="widget-body hide dualListBoxDiv1" style="padding: 6px;border: 1px solid #CCC;border-top: none;">
										<select multiple="multiple" size="10" name="staffName"
											id="initializeStaffList" style="display: none;">
											
										</select>
									</div>
									</div>
								</section>
							
						</fieldset>

                 <div class="smart-form">
						<footer>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" onclick="resetDepartmentDetailsForm()"><spring:message code="Button.Cancel"/></button>
							<button type="button" class="btn btn-primary" id="reset"
								onclick="resetDepartmentDetailsForm()">
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
	
	<!-- test code for payment module -->
<!-- 					<input type='image' name='submit' onclick="callPaym()" src='https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif' alt='Check out with PayPal'/> -->
<!-- MODAL FOR CONFIRMATION -->						
					
<div class="modal fade" id="departmentUploadConfirmation" tabindex="-1"
  role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true"  data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog">
   <div class="modal-content">
	<div class="modal-header">
      <button type="button" class="close" data-dismiss="modal"
       aria-hidden="true" onclick="resetDepartmentConfirmation()"><span class="badge pull-right inbox-badge customColor">&times;</span></button>
      <h4 class="modal-title" id="deptConfirmModalTitleId"><i class="fa fa-users fa-fw txt-color-blue"></i> Error in the template File</h4>
    </div>
    <div class="modal-body no-padding">
    <fieldset>
    	<section style="text-align: center;" id = "departmentConfirmationHeader">
			<div class="space"></div>
				<h4 class="smaller">
					<b>Error Occured while uploading</b>
				</h4>
				<ul class="list-unstyled spaced inline bigger-110 margin-15">
					<li><i class="ace-icon fa fa-hand-o-right blue"></i> Select Continue to insert the error values as it is</li>
					<li><i class="ace-icon fa fa-hand-o-right blue"></i> Select Cancel to cancel and upload again</li>
				</ul>
		</section>
    	<section id="deptErrorSection" style="display:none;">
			<div class="alert alert-danger deptErrorHeader">
				<strong>Error Occured !</strong>
			</div>
			<div class="alert alert-danger fade in departmentErrorBlock" id="deptErrorBlock"> </div>
		</section>
    </fieldset>
    <footer>
    	<button type="submit" class="btn btn-primary button" id="saveDeptConfirmUpload" onclick="setDeptUploadConfirmation(this.id)">
				<i class="fa fa-forward"></i> &nbsp;Continue
	 	</button>
	 	<button type="button" class="btn btn-default" id="cancelDepartmentUpload"
	 	 	data-dismiss="modal" onclick="setDeptUploadConfirmation(this.id);resetDepartmentConfirmation();cancelDepartmentUploadForm()">Cancel
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
			
			<div class="well" style="text-align: center;">
			  Under Construction :  will be updated soon
			</div>
			<%-- <div
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
						data-target="#departmentRemoteModal" onclick=" resetDepartmentDetailsForm();"> <span
						class="widget-icon" style="color: #E4E4E4;"> <i
							class="fa fa-plus-square"></i>
					</span>
						<h2 style="color: #E4E4E4;"><spring:message code="ManageDepartment.Form.AddNewDepartment"/></h2>
					</a> &nbsp;|&nbsp;&nbsp;
					<a href="" data-toggle="modal" data-target="#uploadModal" onclick="setModuleName('<spring:message code="Upload.DepartmentType"/>')">
							 <span class="widget-icon" style="color: #E4E4E4;"> <i class="fa fa-plus-square"></i>
							 </span>
								<h2 style="color: #E4E4E4;">Upload File</h2>
						  </a>
					
					<span class="jarviswidget-loader"><i
						class="fa fa-refresh fa-spin"></i></span>
				</header>

				<div role="content">

					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->

					</div>
					<div class="widget-body no-padding">

						<div id="dt_basic_wrapper"
							class="dataTables_wrapper form-inline no-footer">
							<table id="departmentDetailsTable"
								class="table table-striped table-bordered table-hover dataTable no-footer"
								width="100%" role="grid" aria-describedby="dt_basic_info"
								style="width: 100%;">
								<thead>
									<tr role="row">
										<th data-hide="phone" class="sorting_asc" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-sort="ascending"
											aria-label="ID: activate to sort column ascending"
											style="width: 20px;"><spring:message code="ManageDepartment.Datatable.TableHead.Column1"/></th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Comp Name: activate to sort column ascending"
											style="width: 150px;"><spring:message code="ManageDepartment.Datatable.TableHead.Column2"/></th>
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
 --%>

		</article>

	</div>


</section>

<script>
	/*  duallistBox js*/
	loadScript("assets/js/jquery.bootstrap-duallistbox.min.js", initializeDuallistbox);
	
	function initializeDuallistbox(){
		  $('#initializeRole').bootstrapDualListbox({
          nonSelectedListLabel: 'Non-selected',
          selectedListLabel: 'Selected',
          preserveSelectionOnMove: 'moved',
          moveOnSelect: false,
          showFilterInputs: false,   
          nonSelectedFilter: 'ion ([7-9]|[1][0-2])'
        });
		  $('#initializeStaffList').bootstrapDualListbox({
	          nonSelectedListLabel: 'Non-selected',
	          selectedListLabel: 'Selected',
	          preserveSelectionOnMove: 'moved',
	          moveOnSelect: false,
	          showFilterInputs: false,   
	          nonSelectedFilter: 'ion ([7-9]|[1][0-2])'
        });
		    
	}
	
	$('.toggleDualListBox').on('click',function(){
		$('.dualListBoxDiv').toggleClass('hide');
		$('.dualListBoxDiv1').addClass('hide');
		$('#departmentStaffID').addClass('custMargin');
	});
	
	$('.toggleDualListBox1').on('click',function(){
		$('.dualListBoxDiv1').toggleClass('hide');
		$('.dualListBoxDiv').addClass('hide');
	});
	
	
   $(".toggleDualListBox").one("click", function(){
	   $('#departmentHeadID').toggleClass('custMarginCss');
		addCustLabelDept();
	});
   $(".toggleDualListBox1").one("click", function(){
	   $('#departmentStaffID').toggleClass('custMarginCss');
	   addCustLabelStaff();
	});
	
	function addCustLabelDept(){
	var parentElement = document.getElementsByClassName("box1");
	console.log("This is the department dual list box");
	console.log(parentElement);
	var theFirstChild = parentElement[0].firstChild;
	var thefourthChild=parentElement[0].firstChild.length;
	console.log(thefourthChild);
	 var newElement = document.createElement("div");
	 newElement.style.background = "#F7F7F7";
	 newElement.innerHTML ='<h4 style="text-align:center">List of Avallibility</h4>';
	 /*var node = document.createTextNode("List of Avallibility"); 
	newElement .appendChild(node); */
	parentElement[0].insertBefore(newElement, theFirstChild);
	
	var parentElement = document.getElementsByClassName("box2");
	var theFirstChild = parentElement[0].firstChild;
	var newElement = document.createElement("div");
	newElement.style.background = "#F7F7F7";
	newElement.innerHTML ='<h4 style="text-align:center">Department List</h4>';
	parentElement[0].insertBefore(newElement, theFirstChild);
	}
	
	function addCustLabelStaff(){
		var parentElement = $("#departmentStaffID").next().find('.box1'); 
			//document.getElementsByClassName("box1");
		var theFirstChild = parentElement[0].firstChild;
		 var newElement = document.createElement("div");
		 newElement.style.background = "#F7F7F7";
		newElement.innerHTML ='<h4 style="text-align:center">List of Avallibility</h4>';
		parentElement[0].insertBefore(newElement, theFirstChild);
		
		var parentElement = $("#departmentStaffID").next().find('.box2');
		var theFirstChild = parentElement[0].firstChild;
		var newElement = document.createElement("div");
		newElement.style.background = "#F7F7F7";
		newElement.innerHTML ='<h4 style="text-align:center">Staff List</h4>';
		parentElement[0].insertBefore(newElement, theFirstChild);
		}
	
	$('#reset').on("click",function(e){
	    $(".btn removeall btn-default").trigger(e.type);
	    resetDualListBox();
	  
	    
//   
	    //dualListbox.elements.select1.empty();
	    //dualListbox.elements.select2.empty();
	});
	
</script>