/*pageSetUp();*/
// Model i agree button
/*
 * $("#i-agree").click(function() { $this = $("#terms"); if ($this.checked) {
 * $('#myModal').modal('toggle'); } else { $this.prop('checked', true);
 * $('#myModal').modal('toggle'); } });
 */
invoicePlanTableData = null; 
invoiceCartTable = null;
invoiceDetailsTable = null;
var invoiceIdToDel = 0;
var cartCount=0;
var totalAmount = 0;
//var invoiceIdToCancelled = 0;
var laddaButton = 0;

//$.root_ = $("body");
//var   initApp = function(a) {
//        return a.SmartActions = function() {
//            var a = {
//                alertBox: function(a) {
//                    $.SmartMessageBox({
//                        title: "<i class='fa fa fa-bell txt-color-orangeDark'></i> Alert Box ?",
//                        content: a.data("logout-msg") || "This invoice will be permanently deleted and cannot be recovered. Only Admin can recover it.<br>"+
//                        "<i class='ace-icon fa fa-hand-o-right blue bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete ?</b>",
//                        buttons: "[No][Yes]"
//                    }, function(a) {
//                     if(a=="Yes"){setInvoiceToDelete(invoiceIdToDel);}
//                    });
//                }
//            };
//            $.root_.on("click", '[data-action="alertBox"]', function(b) {
//                var c = $(this);
//                a.alertBox(c), b.preventDefault(), c = null;
//            });
//        },  a
//    }({});
    $(document).ready(function() {
//    	invoiceCartTable.fnClearTable(); 
    	$("#cartId").hide();
    	$("#lateFeesId").hide();
    	$("#interestId").hide();
    	gettingValuesFromProperties();
    });
////for cancelling invoice 
//    $.root1_ = $("body");
//    var   initApp1 = function(a) {
//            return a.SmartActions = function() {
//                var a = {
//                    alertBox1: function(a) {
//                        $.SmartMessageBox({
//                            title: "<i class='fa fa fa-bell txt-color-orangeDark'></i> Alert Box ?",
//                            content: a.data("logout-msg") || "This invoice will be Cancelled.<br>"+
//                            "<i class='ace-icon fa fa-hand-o-right blue bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete ?</b>",
//                            buttons: "[No][Yes]"
//                        }, function(a) {
//                         if(a=="Yes"){setInvoiceToCancel(invoiceIdToDel);}
//                        });
//                    }
//                };
//                $.root1_.on("click", '[data-action="alertBox1"]', function(b) {
//                    var c = $(this);
//                    a.alertBox1(c), b.preventDefault(), c = null;
//                });
//            },  a
//        }({});
//        jQuery(document).ready(function() {
//            initApp.SmartActions(); 
//            initApp1.SmartActions(); 
//           
//        });
        
        function applicationChangeSelectBox() {
        	
        	var x = document.getElementById("appCustFilter").value;
        	
        	 $('.dt-toolbar').find('input[type=search]').filter(':visible:first').val(x).focus();
        	/* 	alert(value); */
        		/*if(document.getElementById("appCustFilter").value == "mpr")
        			 {
        	    console.log('mpr');
        			 }
        		if(document.getElementById("appCustFilter").value == "fts")
        		 {
        			console.log('fts');
        		 }
        		if(document.getElementById("appCustFilter").value == "pd")
        		 {
        			console.log('pd');
        		 }*/
        	}
      
var pagefunction = function() {
	// console.log("cleared");

	/*
	 * // DOM Position key index //
	 * 
	 * l - Length changing (dropdown) f - Filtering input (search) t - The
	 * Table! (datatable) i - Information (records) p - Pagination (paging) r -
	 * pRocessing < and > - div elements <"#id" and > - div with an id <"class"
	 * and > - div with a class <"#id.class" and > - div with an id and class
	 * 
	 * Also see: http://legacy.datatables.net/usage/features
	 */

	/* BASIC ; */
	
	// Invoice Plan Goes here
	invoicePlanTableData = $('#invoicePlanTable')
	.dataTable(
			{
				"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"
						+ "t"
						+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
				"autoWidth" : true,
				"bFilter" : true,
				"bSearchable" : false,
				"bInfo" : true,
				"bDestroy" : true,
				aoColumns : [
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[4];
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
									sClass : "center",
									mRender : function(data, type, full) {
										return full[3]+"$/month";
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return "<div class='pull-right action-buttons'><a title='Add' data-toggle='tooltip' class='blue' href=\"javascript:addToCart('"
								           + full[0]
								           + "')\"> <i class='fa fa-arrow-right bigger-130'></i></a>";
									}
								}],
								"preDrawCallback" : function() {
									// Initialize the responsive datatables helper once.
									if (!responsiveHelper_dt_basic) {
										responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
												$('#invoicePlanTable'),
												breakpointDefinition);
									}
								},
								"rowCallback" : function(nRow) {
									responsiveHelper_dt_basic.createExpandIcon(nRow);
								},
								"drawCallback" : function(oSettings) {
									responsiveHelper_dt_basic.respond();
								}
						/*End Basic */		
			});

	
	$("#invoiceGeneratedDate").datepicker({
		format : "mm-dd-yyyy"
	}).on('changeDate', function(ev) {
		$('#invoiceGeneratedDate').datepicker('hide');
	});

	$("#invoiceDueDate").datepicker({
		format : "mm-dd-yyyy"
	}).on('changeDate', function(ev) {
		$('#invoiceDueDate').datepicker('hide');
	});

	var responsiveHelper_dt_basic = undefined;

	var breakpointDefinition = {
		tablet : 1024,
		phone : 480
	};
	
	//Invoice Cart Goes here
	
	invoiceCartTable = $('#applicationPurchasePlanTable')
	.dataTable(
			{
				"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"
						+ "t"
						+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
				"autoWidth" : true,
				"bFilter" : false,
				"bSearchable" : false,
				"bInfo" : false,
				"bDestroy" : true,
				"bPaginate": false,
				aoColumns : [
								{
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
									sClass : "center",
									mRender : function(data, type, full) {
										return full[3];
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[4];
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[5];
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[6];
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[7];
									}
								}],
								"preDrawCallback" : function() {
									// Initialize the responsive datatables helper once.
									if (!responsiveHelper_dt_basic) {
										responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
												$('#applicationPurchasePlanTable'),
												breakpointDefinition);
									}
								},
								"rowCallback" : function(nRow) {
									responsiveHelper_dt_basic.createExpandIcon(nRow);
								},
								"drawCallback" : function(oSettings) {
									responsiveHelper_dt_basic.respond();
								}
						/*End Basic */		
					});
	$("#invoiceGeneratedDate").datepicker({
		format : "mm-dd-yyyy"
	}).on('changeDate', function(ev) {
		$('#invoiceGeneratedDate').datepicker('hide');
	});

	$("#invoiceDueDate").datepicker({
		format : "mm-dd-yyyy"
	}).on('changeDate', function(ev) {
		$('#invoiceDueDate').datepicker('hide');
	});

	var responsiveHelper_dt_basic = undefined;

	var breakpointDefinition = {
		tablet : 1024,
		phone : 480
	};
	
	invoiceDetailsTable = $('#applicationInvoiceTable')
			.dataTable(
					{
						"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"
								+ "t"
								+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
						"autoWidth" : true,
						"bFilter" : true,
						"bSearchable" : false,
						"bInfo" : true,
						"bDestroy" : true,
						aoColumns : [
										{
											sClass : "center",
											mRender : function(data, type, full) {
												return full[0];
											}
										},
										{
											sClass : "center",
											mRender : function(data, type, full) {
												return "<a  href='home#applicationInvoiceInDetail?invoiceId="+full[1]+"'>"
												+ full[1]
												+ "</a>";
											}
										},
										{
											sClass : "center",
											mRender : function(data, type, full) {
												return full[2];
											}
										},
										{
											sClass : "center",
											mRender : function(data, type, full) {
												return full[3];
											}
										},
										{
											sClass : "center",
											mRender : function(data, type, full) {
												return full[4];
											}
										},
										{
											sClass : "center",
											mRender : function(data, type, full) {
												return full[5];
											}
										},
										{
											sClass : "center",
											mRender : function(data, type, full) {
												return full[6];
											}
										},
										{
											mData : null,
											sClass : "center",
											mRender : function(data, type, full) {
												return "<div class='pull-right action-buttons'><a title='Edit' data-toggle='tooltip' class='blue' href=\"javascript:setInvoiceToUpdate('"
										           + full[1]
										           + "')\"> <i class='fa fa-pencil bigger-130'></i></a> <a title='Cancel' data-toggle='tooltip' data-action='alertBox1' href= 'javascript:void(0)' onclick=\"javascript:setInvoiceIdToCancel('"
										           + full[1]
										           + "')\"> <i class='fa fa-times bigger-130'></i></a> <a title='Delete' data-toggle='tooltip' data-action='alertBox' href= 'javascript:void(0)' onclick=\"javascript:setInvoiceId('"
										           + full[1]
										           + "')\"> <i class='fa fa-trash-o bigger-130'></i></a></div>";
											}
										} ],
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#applicationInvoiceTable'),
										breakpointDefinition);
							}
						},
						"rowCallback" : function(nRow) {
							responsiveHelper_dt_basic.createExpandIcon(nRow);
						},
						"drawCallback" : function(oSettings) {
							responsiveHelper_dt_basic.respond();
						}
					});

	/* END BASIC */
	getApplicationInvoiceDetails();
};

// For Delete Action
function setInvoiceId(inoiveId){
	$.SmartMessageBox(
			{
				title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
				content : "<br>This Invoice will be "
						+ "permanently deleted . Only Admin can	recover it.<br>"
						+ "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
				buttons : '[No][Yes]'
			}, function(ButtonPressed) {
				if (ButtonPressed === "Yes") {
					setInvoiceToDelete(inoiveId);
				}
			});
	}
function setInvoiceIdToCancel(inoiveId){
	$.SmartMessageBox(
			{
				title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
				content : "<br>This Invoice will be "
						+ "Cancelled .<br>"
						+ "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to cancel it ?</b>",
				buttons : '[No][Yes]'
			}, function(ButtonPressed) {
				if (ButtonPressed === "Yes") {
					setInvoiceToCancel(inoiveId);
				}
			});
	}

// load related plugins

loadScript("assets/js/datatable/jquery.dataTables.min.js", function() {
	loadScript("assets/js/datatable/dataTables.tableTools.min.js", function() {
		loadScript("assets/js/datatable/dataTables.bootstrap.min.js",
				function() {
					loadScript(
							"assets/js/datatable/datatables.responsive.min.js",
							pagefunction);
				});
	});
});

$("#application_invoice_form").validate({
	// Rules for form validation
	rules : {
		domainDetailUI : {
			required : true,

		},
		invoiceGeneratedDate : {
			required : true,
		},
		invoiceDueDate : {
			required : true,
		}
	},

	// Messages for form validation
	messages : {
		domainDetailUI : {
			required : "Please select the  domain name"

		},
		invoiceGeneratedDate : {
			required : "Please enter start date",
		},
		invoiceDueDate : {
			required : "Please enter due date",
		}
	},

	// Do not change code below
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	},
	submitHandler : function(form) {
		ajaxCallForSaveOrUpdateApplicationInvoiceForm(form.id);
	}
});

function ajaxCallForSaveOrUpdateApplicationInvoiceForm(formId) {
	var laddaRef = Ladda.create(laddaButton);
	laddaRef.start();
	var ivoiceCartTableData = null;
	var arrayItemTable = new Array();
	var rows=$("#applicationPurchasePlanTable").dataTable().fnGetNodes();
	for (var i = 0; i < rows.length; i++) {
		ivoiceCartTableData = {
			"sno" : $(rows[i]).find("td:eq(0)").html(),
			"applicationName" : $(rows[i]).find("td:eq(1)").html(),
			"planName" : $(rows[i]).find("td:eq(2)").html(),
			"startDate" : $(rows[i]).find("td:eq(3)").html(),
			"endDate" : $(rows[i]).find("td:eq(4)").html(),
			"price" : $(rows[i]).find("td:eq(5)").html(),
			"extraCharges" : $(rows[i]).find("td:eq(6)").html(),
			"total" : $(rows[i]).find("td:eq(7)").html()
		};
		arrayItemTable.push(ivoiceCartTableData);
	}
	var formData = {
			"invoiceId" :$("#invoiceId").val(),
			"invoiceGeneratedDate":$('#invoiceGeneratedDate').val(),
			"invoiceDueDate":$('#invoiceDueDate').val(),
			"additionalCharges":$('#additionalCharges').val(),
			"invoiceType":$("#invoiceType").val(),
			"domainDetail":$('#domainDetail').val(),
			"isLateFeesApplicable":$('input:checkbox[name=isLateFeesApplicable]').is(':checked'),
			"isInterestApplicable":$('input:checkbox[name=isInterestApplicable]').is(':checked')
	};
	arrayItemTable.push(formData);
	$
			.ajax({
				url : "saveOrUpdateApplicationInvoiceAction",
				type : "POST",
				contentType : "application/json",
				data : JSON.stringify(arrayItemTable),
				success : function(result) {
					laddaRef.stop();
					result = JSON.parse(result);
					var actionType = "";
					if($("#invoiceId").val() != ""){
						actionType = "update";
					}
					if($("#typeOfAction").val() != "saveandcontinue"){
					     $("#invoiceRemoteModal").modal("hide");
					}
					// CLearing Project Table 
					if (result.ajaxResult == "success") {
						getApplicationInvoiceDetails();
						// Resetting Forms
						if(actionType != ""){
							  gritterForSucessMsgs("Invoice has been updated successfully.");
                          } else {
                          	 gritterForSucessMsgs("Invoice has been saved successfully.");
                          }
						resetInvoiceForm();
						
					} else {
						$("#invoiceRemoteModal").modal("hide");
						resetInvoiceForm();
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ result.reason);
					}
					invoiceCartTable.fnClearTable();
					$("#cartId").hide();
					cartCount = 0;
					totalAmount =0;
					$("#totalAmount").html("$0");
				},
				error : function() {
					laddaRef.stop();
					resetInvoiceForm();
					invoiceCartTable.fnClearTable();
					$("#cartId").hide();
					cartCount = 0;
					totalAmount =0;
					$("#totalAmount").html("$0");
					$("#invoiceRemoteModal").modal("hide");
					gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');

				}
			});
}
function resetInvoiceForm() {
	var validator = $("#application_invoice_form").validate();
//	validator.resetForm();
	$('label').removeClass("state-success");
	$('label').removeClass("state-error");
	$("#modalTitleId").html("<i class='fa fa-book txt-color-blue'></i>Generate Invoice");
	$("#invoiceId").val("");
	$('#invoiceGeneratedDate').val("");
	$('#invoiceDueDate').val("");
	$('#additionalCharges').val("");
	$("#invoiceType").val("Paid");
	$('#domainDetail').val("");
	$("#save").html(
	"<i class='fa fa-floppy-o'></i> &nbsp; Save ");
	$("#saveandcontinue").show();
	$('input:checkbox[name=isLateFeesApplicable]').prop('checked',false);
	$('input:checkbox[name=isInterestApplicable]').prop('checked',false);
	invoiceCartTable.fnClearTable();
	$("#cartId").hide();
	cartCount = 0;
	totalAmount =0;
	$("#totalAmount").html("$0");
	$("#interestId").hide();
	$("#interestAmount").html();
	$("#lateFeesId").hide();
	$("#lateFeesAmount").html();
}
function showCurrentDate() {
	var currentDate = new Date();
	var dd = currentDate.getDate();
	var mm = currentDate.getMonth() + 1;
	var yyyy = currentDate.getFullYear();
	if (dd < 10) {
		dd = '0' + dd
	}
	if (mm < 10) {
		mm = '0' + mm
	}
	var currentDateFormated = mm + '-' + dd + '-' + yyyy;
	$("#invoiceGeneratedDate").val(currentDateFormated);
}
function getApplicationInvoiceDetails() {
	var invoiceFormData = $("#").serialize();
	invoiceFormDetails = "applicationInvoice";
	$.ajax({
		url : "getApplicationInvoiceDetails",
		type : "POST",
		contentType : 'text/plain',
		data : invoiceFormData,
		success : function(result) {
			invoiceFormDetails = JSON.parse(result);
			initSelectBox();
			invoiceDetailsTable.fnClearTable();
			for (var t = 0; t < invoiceFormDetails.allInvoiceDetailsList.length; t++) {
				var additonalCharges = 0;
				if(invoiceFormDetails.allInvoiceDetailsList[t].additionalCharges != null && invoiceFormDetails.allInvoiceDetailsList[t].additionalCharges != undefined){
					additonalCharges = invoiceFormDetails.allInvoiceDetailsList[t].additionalCharges;
				}
				invoiceDetailsTable
						.fnAddData(
							[
							 		t+1,
									invoiceFormDetails.allInvoiceDetailsList[t].invoiceId,
									invoiceFormDetails.allInvoiceDetailsList[t].domainDetail.domainName,
									invoiceFormDetails.allInvoiceDetailsList[t].invoiceGeneratedDate,
									invoiceFormDetails.allInvoiceDetailsList[t].invoiceDueDate,
									invoiceFormDetails.allInvoiceDetailsList[t].invoiceType,
									additonalCharges],
							false);
				}
			settingInvoicesCount(invoiceFormDetails);
			invoiceDetailsTable.fnDraw();
			addPlanTable();
			addOptionsToDomainSelBox(invoiceFormDetails.allDomainDetails);
		},
		error : function(error) {
//			console.log(error);
		}
	});
}

function addOptionsToDomainSelBox(domainDetails){
	var domainDetailList = $("#domainDetail");
	var elementId = document.getElementById("domainDetail");
	 elementId.options.length = 1;
	if(domainDetails != undefined && domainDetails != null){
		for(var i=0; i<domainDetails.length; i++){
			domainDetailList.append("<option value="
					+ domainDetails[i].domainId + ">"
					+ domainDetails[i].domainName
					+ "</option>");
		}
	}
}
function setInvoiceToUpdate(invoiceId){
    $("#modalTitleId").html("<i class='fa fa-book txt-color-blue'></i>Update Invoice");
	$("#invoiceRemoteModal").modal("show");
	for (var u = 0; u < invoiceFormDetails.allInvoiceDetailsList.length; u++) {
		var invoiceGeneratedDate=invoiceDueDate="";
		if(invoiceId == invoiceFormDetails.allInvoiceDetailsList[u].invoiceId){
			invoiceGeneratedDate = moment(new Date(invoiceFormDetails.allInvoiceDetailsList[u].invoiceGeneratedDate)).format("MM-DD-YYYY");
			invoiceDueDate = moment(new Date(invoiceFormDetails.allInvoiceDetailsList[u].invoiceDueDate)).format("MM-DD-YYYY");
			$("#invoiceId").val(invoiceFormDetails.allInvoiceDetailsList[u].invoiceId);
			$("#domainDetail").val(invoiceFormDetails.allInvoiceDetailsList[u].domainDetail.domainId);
			$("#invoiceGeneratedDate").val(invoiceGeneratedDate);
			$("#invoiceDueDate").val(invoiceDueDate);
			$("#invoiceType").val(invoiceFormDetails.allInvoiceDetailsList[u].invoiceType);
			$("#additionalCharges").val(invoiceFormDetails.allInvoiceDetailsList[u].additionalCharges);
			$('input:checkbox[name=isLateFeesApplicable]').prop('checked',invoiceFormDetails.allInvoiceDetailsList[u].isLateFeesApplicable);
			$('input:checkbox[name=isInterestApplicable]').prop('checked',invoiceFormDetails.allInvoiceDetailsList[u].isInterestApplicable);
			$("#save").html(
			"<i class='fa fa-thumbs-up bigger-110'></i> Update");
			$("#saveandcontinue").hide();
		}
	}
	var count = 0;
	for (var t = 0; t < invoiceFormDetails.applicationInvoicePlanMapping.length; t++) {
		if(invoiceId == invoiceFormDetails.applicationInvoicePlanMapping[t].applicationInvoice.invoiceId){
		var extraPrice,total,startDate,endDate;
		startDate=endDate ='';extraPrice=0;
		startDate = moment(new Date(invoiceFormDetails.applicationInvoicePlanMapping[t].domainApplicationPlanMapping.planStartDate)).format("MM-DD-YYYY");
		endDate = moment(new Date(invoiceFormDetails.applicationInvoicePlanMapping[t].domainApplicationPlanMapping.planExpiryDate)).format("MM-DD-YYYY");
		total = invoiceFormDetails.applicationInvoicePlanMapping[t].domainApplicationPlanMapping.applicationPlanDirectory.price+extraPrice;
		totalAmount += total;
		invoiceCartTable
				.fnAddData(
					[
					 		++count,
					 		invoiceFormDetails.applicationInvoicePlanMapping[t].domainApplicationPlanMapping.applicationPlanDirectory.applicationDirectory.applicationName,
					 		invoiceFormDetails.applicationInvoicePlanMapping[t].domainApplicationPlanMapping.applicationPlanDirectory.planName,
					 		startDate,
					 		endDate,
					 		invoiceFormDetails.applicationInvoicePlanMapping[t].domainApplicationPlanMapping.applicationPlanDirectory.price,
							extraPrice,
							total]);
	}
}
	invoiceCartTable.fnDraw();
	if($('#lateFeeCheckboxId').is(':checked')){
	addLateFees();
	}
	if($('#interestCheckbox1Id').is(':checked')){
	addInterest();
	}
	$("#cartId").html(count--);
	$("#cartId").show();
//	$("#cart").addClass('active');
//	$("#plans").removeClass('active');
	$("#totalAmount").html("$"+totalAmount.toFixed(2));
}

function setInvoiceToDelete(invoiceId){
	   
	    var dataObj = {
	    		invoiceIdToDel : invoiceId,
	    	};
	    $.ajax({
			url : "invoiceDeleteAction",
			type : "POST",
			data : dataObj,
			success : function(result) {
				result = JSON.parse(result);
				// CLearing Project Table
				if (result.ajaxResult == "success") {
					getApplicationInvoiceDetails();
					gritterForSucessMsgs("Invoice has been deleted successfully.");
				} else {
					gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
							+ result.reason);
				}
			},
			error : function() {
				gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');

			}
		});
}
function setActionType(buttonId, laddaButtonTemp){
	$("#typeOfAction").val(buttonId);
	laddaButton = laddaButtonTemp;
}
function settingInvoicesCount(invoiceFormDetails){
	var cancelledInvoicesId=paidInvoicesId=pendingInvoicesId=totalInvoicesId=0;
	for (var u = 0; u < invoiceFormDetails.allInvoiceDetailsList.length; u++) {
		if(invoiceFormDetails.allInvoiceDetailsList[u].invoiceType == "Pending"){
			pendingInvoicesId++;
		}
		if(invoiceFormDetails.allInvoiceDetailsList[u].invoiceType == "Paid"){
			paidInvoicesId++;
		}
		if(invoiceFormDetails.allInvoiceDetailsList[u].invoiceType == "Cancelled"){
			cancelledInvoicesId++;
		}
	}
	totalInvoicesId = cancelledInvoicesId+paidInvoicesId+pendingInvoicesId;
	$("#totalInvoicesId").html(totalInvoicesId);
	$("#cancelledInvoicesId").html("<i class='fa fa-thumbs-o-down' data-rel='bootstrap-tooltip' title='Increased'>&nbsp;"+cancelledInvoicesId);
	$("#paidInvoicesId").html("<i class='fa fa-thumbs-o-up'></i>&nbsp;"+paidInvoicesId);
	$("#pendingInvoicesId").html("<i class='fa fa-thumbs-o-down'></i>&nbsp;"+pendingInvoicesId);
}
function setInvoiceToCancel(invoiceId){
	   
    var dataObj = {
    		invoiceIdToDel : invoiceId,
    	};
    $.ajax({
		url : "invoiceCancelAction",
		type : "POST",
		data : dataObj,
		success : function(result) {
			result = JSON.parse(result);
			// CLearing Project Table
			if (result.ajaxResult == "success") {
				getApplicationInvoiceDetails();
				gritterForSucessMsgs("Invoice has been cancelled successfully.");
			} else {
				gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
						+ result.reason);
			}
		},
		error : function() {
			gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');

		}
	});
}
function initSelectBox(){
	/*var a = document.getElementById("applicationName");
	var d = document.getElementById("domainDetail");
	var p = document.getElementById("planName");
	for(var t=0;t< invoiceFormDetails.applicationDirectoryDetails.length ; t++){
		var r = document.createElement("option");
		var dr = document.createElement("option");
		r.text=invoiceFormDetails.applicationDirectoryDetails[t].applicationName;
		r.value=invoiceFormDetails.applicationDirectoryDetails[t].applicationId;
		console.log(r.text);
		a.add(r,null);
	}*/
//	$(".selectpicker").selectpicker("refresh");
}
function checkDuplicatePlanExists(planId){
	var invoiceCartTableDataToCheck = invoiceCartTable.fnGetData();
	var tempcount =0;
	var checkcount = 1;
	if(invoiceCartTableDataToCheck.length != 0){
	for (var x = 0; x < invoiceCartTableDataToCheck.length;x++) {
			var appName = planResult.allPlanList[planId-1].applicationDirectory.applicationName;
			var planName = planResult.allPlanList[planId-1].planName;
			var pId = planResult.allPlanList[planId-1].planId;
			if(invoiceCartTableDataToCheck[x][1] == appName
					&& invoiceCartTableDataToCheck[x][2] ==  planName){
				tempcount=checkcount;
			} else if(invoiceCartTableDataToCheck[x][1] == appName){
				tempcount=4;
			}
	}
	}
	return tempcount;
}
function addToCart(planId){
	if(checkDuplicatePlanExists(planId) == 1){
		gritterForErrorMsgs("The Plan is alredy available in the cart");
	} else if(checkDuplicatePlanExists(planId) == 4){
		gritterForErrorMsgs("Only one plan from an application must be  present in the table");
	} else {
		cartCount++;
	for (var t = 0; t < planResult.allPlanList.length; t++) {
		if(planId == planResult.allPlanList[t].planId){
		var startDate,endDate,extraPrice,total,d;
		d=new Date();
		startDate=new Date();endDate =new Date(d.setMonth(d.getMonth()+planResult.allPlanList[t].planValidityInMonths));extraPrice=0;
		total = planResult.allPlanList[t].price+extraPrice;
		totalAmount += total;
		invoiceCartTable
				.fnAddData(
					[
					 		cartCount,
					 		planResult.allPlanList[t].applicationDirectory.applicationName,
					 		planResult.allPlanList[t].planName,
							moment(startDate).format("MM-DD-YYYY"),
							moment(endDate).format("MM-DD-YYYY"),
							planResult.allPlanList[t].price,
							extraPrice,
							total]);
	}
}
	invoiceCartTable.fnDraw();
	$("#cartId").html(cartCount);
	$("#cartId").show();
	$("#totalAmount").html("$"+totalAmount.toFixed(2));
}
}
//deleting from cart
function deleteFromCart(cartId){
	var count;
	 var invoiceCartTableData = invoiceCartTable.fnGetData();
	  for (var x = 0, len1 = invoiceCartTableData.length; x < len1; x++) {
	   if (invoiceCartTableData[x][0] == cartId) {
		   invoiceCartTable.fnDeleteRow(x);
		   totalAmount -= invoiceCartTableData[x][7];
		   cartCount--;
		   updateCartTable(invoiceCartTable.fnGetData());
	   } 	   
	  }
	  invoiceCartTable.fnDraw();
	  $("#cartId").html(cartCount);
	  $("#totalAmount").html("$"+totalAmount.toFixed(2));
}
function updateCartTable(invoiceCartTableData){
	invoiceCartTable.fnClearTable();
	for (var x = 0; x < invoiceCartTableData.length; x++) {
		invoiceCartTable
				.fnAddData(
					[
							x+1,
							invoiceCartTableData[x][1],
							invoiceCartTableData[x][2],
							invoiceCartTableData[x][3],
							invoiceCartTableData[x][4],
							invoiceCartTableData[x][5],
							invoiceCartTableData[x][6],
							invoiceCartTableData[x][7]]);
		}
	invoiceCartTable.fnDraw();
}
function addPlanTable(){
	$.ajax({
		url : "getPurchasePlanAction",
		type : "POST",
		success : function(result) {
			planResult = JSON.parse(result);
			invoicePlanTableData.fnClearTable();
			for (var t = 0; t < planResult.allPlanList.length; t++) {
				invoicePlanTableData
						.fnAddData(
							[
									planResult.allPlanList[t].planId,
									planResult.allPlanList[t].applicationDirectory.applicationName,
									planResult.allPlanList[t].planName,
									planResult.allPlanList[t].price,
									t+1,
									planResult.allPlanList[t].applicationDirectory.applicationId],
							false);
				}
			invoicePlanTableData.fnDraw();
		},
		error : function(result) {
//			console.log("error");
//			console.log(result.message);
		}
	});
}

function gettingValuesFromProperties(){
	$.ajax({
		url : "gettingValuesFromProperties",
		type : "POST",
		success : function(result) {
			propertiesResult = JSON.parse(result);
		},
		error : function(result) {
//			console.log("error");
//			console.log(result.message);
		}
	});
}
function addLateFees(){
//	gettingValuesFromProperties();
	if($('#lateFeeCheckboxId').is(':checked')){
	$("#lateFeesId").show();
	var lateFee = propertiesResult.isLateFee;
	var invoiceCartTableData = invoiceCartTable.fnGetData();
	invoiceCartTable.fnClearTable();
	var amount = 0;
	for (var x = 0; x < invoiceCartTableData.length; x++) {
		amount += propertiesResult.isLateFee;
		var subTotal = invoiceCartTableData[x][7]+ lateFee;
		var additionalCharges = invoiceCartTableData[x][6]+lateFee;
		invoiceCartTable
				.fnAddData(
					[
							x+1,
							invoiceCartTableData[x][1],
							invoiceCartTableData[x][2],
							invoiceCartTableData[x][3],
							invoiceCartTableData[x][4],
							invoiceCartTableData[x][5],
							additionalCharges,
							subTotal]);
		totalAmount += lateFee;
		}
	invoiceCartTable.fnDraw();
	$("#totalAmount").html("$"+totalAmount.toFixed(2));
	$("#lateFeesAmount").html("$ "+amount);
	} else {
		$("#lateFeesId").hide();
		$("#lateFeesAmount").html();
		var lateFee = propertiesResult.isLateFee;
		var invoiceCartTableData = invoiceCartTable.fnGetData();
		invoiceCartTable.fnClearTable();
		var amount = 0;
		for (var x = 0; x < invoiceCartTableData.length; x++) {
			amount += propertiesResult.isLateFee;
			var subTotal = invoiceCartTableData[x][7] - lateFee;
			var additionalCharges = invoiceCartTableData[x][6] - lateFee;
			invoiceCartTable
					.fnAddData(
						[
								x+1,
								invoiceCartTableData[x][1],
								invoiceCartTableData[x][2],
								invoiceCartTableData[x][3],
								invoiceCartTableData[x][4],
								invoiceCartTableData[x][5],
								additionalCharges,
								subTotal]);
			totalAmount -= lateFee;
			}
		invoiceCartTable.fnDraw();
		$("#totalAmount").html("$"+totalAmount.toFixed(2));
		$("#lateFeesAmount").html("$ "+amount);
	}
}
function addInterest(){
//	gettingValuesFromProperties();
	if($('#interestCheckbox1Id').is(':checked')){
	$("#interestId").show();
	$("#interestAmount").html(propertiesResult.isInterest+" %");
	var isInterest = propertiesResult.isInterest;
	var invoiceCartTableData = invoiceCartTable.fnGetData();
	invoiceCartTable.fnClearTable();
	for (var x = 0; x < invoiceCartTableData.length; x++) {
		var subTotal = invoiceCartTableData[x][7]+ (isInterest/100)*invoiceCartTableData[x][5];
		var additionalCharges = invoiceCartTableData[x][6]+(isInterest/100)*invoiceCartTableData[x][5];
		invoiceCartTable
				.fnAddData(
					[
							x+1,
							invoiceCartTableData[x][1],
							invoiceCartTableData[x][2],
							invoiceCartTableData[x][3],
							invoiceCartTableData[x][4],
							invoiceCartTableData[x][5],
							additionalCharges,
							subTotal]);
		totalAmount += (isInterest/100)*invoiceCartTableData[x][5];
		}
	invoiceCartTable.fnDraw();
	$("#totalAmount").html("$"+totalAmount.toFixed(2));
	} else {
		$("#interestId").hide();
		$("#interestAmount").html();
		var isInterest = propertiesResult.isInterest;
		var invoiceCartTableData = invoiceCartTable.fnGetData();
		invoiceCartTable.fnClearTable();
		for (var x = 0; x < invoiceCartTableData.length; x++) {
			var subTotal = invoiceCartTableData[x][7] - (isInterest/100)*invoiceCartTableData[x][5];
			var additionalCharges = invoiceCartTableData[x][6] - (isInterest/100)*invoiceCartTableData[x][5];
			invoiceCartTable
					.fnAddData(
						[
								x+1,
								invoiceCartTableData[x][1],
								invoiceCartTableData[x][2],
								invoiceCartTableData[x][3],
								invoiceCartTableData[x][4],
								invoiceCartTableData[x][5],
								additionalCharges,
								subTotal]);
			totalAmount -= (isInterest/100)*invoiceCartTableData[x][5];
			}
		invoiceCartTable.fnDraw();
		$("#totalAmount").html("$"+totalAmount.toFixed(2));
	}
}