/** 
 * Anantha MeghanaJagruthi
 * 
 */



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

	roleDetailsTable=null;
	var roleToDelete=0;
//	$.root_ = $("body");
//	var   initApp = function(a) {
//	        return a.SmartActions = function() {
//	            var a = {
//	                alertBox: function(a) {
//	                    $.SmartMessageBox({
//	                        title: "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
//	                        content: a.data("logout-msg") || "<br>This role will be "+
//							"permanently deleted . Only Admin can	recover it.<br>"+"" +
//							"<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
//	                        buttons: "[No][Yes]"
//	                    }, function(a) {
//	                    	if(a=="Yes"){roleDetailSubmitController();}
//	                    });
//	                }
//	            };
//	            $.root_.on("click", '[data-action="alertBox"]', function(b) {
//	                var c = $(this);
//	                a.alertBox(c), b.preventDefault(), c = null;
//	            });
//	        },  a
//	    }({});
//	    jQuery(document).ready(function() {
//	        initApp.SmartActions(); 
//	       
//	    });
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
		
		roleDetailsTable=$("#roleDetailsTable")
				.dataTable(
						{
							"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"
									+ "t"
									+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
							"autoWidth" : true,
							aoColumns : [{
								sClass : "center",
								mRender : function(data, type, full) {
									return full[0];
								}
							},
							{
								sClass : "center",
								mRender : function(data, type, full) {
									return full[1];
								}
							},
							{
								sClass : "center",
								mRender : function(data, type, full) {
									return full[2];
								}
							},
							{
								mData : null,
								sClass : "center",
								mRender : function(data, type, full) {
									return "<div class='pull-right action-buttons'><a title='edit' data-toggle='tooltip' class='blue'  href=\"javascript:setRoleToUpdate('"
										+ full[3]
										+ "')\"> <i class='fa fa-pencil bigger-130'></i></a> <a title='Delete' data-toggle='tooltip' data-action='alertBox' href= 'javascript:void(0)' onclick=\"javascript:setRoleId('"
											+ full[3]
											+ "')\"> <i class='fa fa-trash-o bigger-130'></i></a></div>";
								}
							} ],
							"preDrawCallback" : function() {
								// Initialize the responsive datatables helper once.
								if (!responsiveHelper_dt_basic) {
									responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
											$("#roleDetailsTable"),
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
		getRoleDetails();

		/* END BASIC */
	};
	$("#roleDescriptionForm").validate({
		// Rules for form validation
		rules : {
			roleName : {
				required :true,
			},
			roleDescription : {
				required :true,
			}
		},
			
		// Messages for form validation
		messages : {
			roleName : {
				required :"Enter the role name"
			},
			roleDescription : {
				required :"Enter the role description"
			}
		},

		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		},
		submitHandler: function(form) {
			
			roleDetailSubmitController();
			
        }
	});
	function getRoleDetails()
	{
		
		console.log("Entered");
		$.ajax({
			url:"getRoleDetails",
			type:"POST",
			success:function(resultJSON)
			{
				
				roleList=JSON.parse(resultJSON);
				roleDetailsTable.fnClearTable();
				for(var i=0;i<roleList.roleDetails.length;i++)
				{
					
					
					roleDetailsTable
					.fnAddData([i+1,roleList.roleDetails[i].roleName,roleList.roleDetails[i].description,roleList.roleDetails[i].roleId],false);
					
					
				}
				roleDetailsTable.fnDraw();
			}
		});
	}
	function setAction(action)
	{
		
		if($("#roleAction").val()=="")
			{
			$("#roleAction").val(action);
			}
		
	}
	
	function setRoleId(id)
	{
		$("#roleId").val(id);
		$("#roleAction").val("delete");
		$.SmartMessageBox(
				{
					title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
					content : "<br>This Role will be "
							+ "permanently deleted . Only Admin can	recover it.<br>"
							+ "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
					buttons : '[No][Yes]'
				}, function(ButtonPressed) {
					if (ButtonPressed === "Yes") {
						roleDetailSubmitController();
					}
				});
	}
	function resetRoleForm()
	{
		
		 var validator = $("#roleDescriptionForm").validate();
		 validator.resetForm();
		 $('label').removeClass("state-success");
		 $('label').removeClass("state-error");
		console.log( $("#roleName").val(''));
		 console.log($("#roleDescription").val(''));
		 $("#saveandcontinue").show();
		 $("#saveButtonId").html("Save");
	}
	function setRoleToUpdate(id)
	{
		$("#roleAction").val("update");
		 $("#addRoleModal").modal("show");
		 $("#saveButtonId").html("update");
		 $("#saveandcontinue").hide();
		var description;
		for(var i=0;i<roleList.roleDetails.length;i++)
			{
			   if(id==roleList.roleDetails[i].roleId)
				   {
				        $("#roleName").val(roleList.roleDetails[i].roleName);
				        $("#roleId").val(roleList.roleDetails[i].roleId);
				       if(roleList.roleDetails[i].description==null||roleList.roleDetails[i].description==undefined)
				       {
				        description='';	
				       }
				       else
				    	   {
				    	   description = roleList.roleDetails[i].description;
				    	   }
				        $("#roleDescription").val(description);
				        break;
				   }
			}
	}
	function roleDetailSubmitController(){
		alert("controller entered"+$("#roleAction").val());
		$.ajax({
			url:"rolesFormController",
			type:"POST",
			data:$("#roleDescriptionForm").serialize(),
			success:function(output, textStatus, jqXHR)
			{
				
				resetRoleForm();
				getRoleDetails();
				alert(output);
				if($("#roleAction").val()=="save"||$("#roleAction").val()=="update"||$("#roleAction").val()=="delete")
					{
					
					   $("#addRoleModal").modal("hide");
					  
					}
				
			
				if (output == "success") {
					
					 gritterForSucessMsgs("Role has been "+$("#roleAction").val()+"d successfully.");
				} else {
				
					gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"+result
							);
				}
			},
			error : function() {
				laddaRef.stop();
				gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
			}
		});
	}