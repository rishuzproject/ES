pageSetUp();
couponDetailsTable = null;
var laddaButton = 0;
var couponCodeToDelete = 0;

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
	var responsiveHelper_dt_basic = undefined;

	var breakpointDefinition = {
		tablet : 1024,
		phone : 480
	};
	$("#validity").datepicker({
		format : "mm-dd-yyyy"
	}).on('changeDate', function(ev) {
		$('#validity').datepicker('hide');
	});
	couponDetailsTable = $('#couponDetailsTable')
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
												return full[8];
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
										},
										{
											mData : null,
											sClass : "center",
											mRender : function(data, type, full) {
												return "<div class='pull-right action-buttons'><a title='Edit' data-toggle='tooltip' class='blue' href=\"javascript:setCouponToUpdate('"
										           + full[0]
										           + "')\"> <i class='fa fa-pencil bigger-130'></i></a> <a title='Delete' data-toggle='tooltip' data-action='alertBox' href= 'javascript:void(0)' onclick=\"javascript:setCouponCodeId('"
										           + full[0]
										           + "')\"> <i class='fa fa-trash-o bigger-130'></i></a></div>";
											}
										} ],
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#couponDetailsTable'),
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
	getCouponDetails();
	/* END BASIC */
};
// for delete action
function setCouponCodeId(couponId){
	$.SmartMessageBox(
			{
				title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
				content : "<br>This Coupon will be permanently deleted and cannot be recovered. Only Admin can	recover it.<br>"
						+ "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
				buttons : '[No][Yes]'
			}, function(ButtonPressed) {
				if (ButtonPressed === "Yes") {
					setCouponToDelete(couponId);
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

$("#couponFormId").validate({
	
	// Rules for form validation
	rules : {
		couponCode : {
			required : true
		},
		validity:{
			required: true,
		},
		discountAmount:{
			required: true,
			digits : true,
		},
		additionalUser:{
			required: true,
		    digits : true,
		},
		additionalSpace:{
			required: true,
			digits : true,
		}
	},
		
	// Messages for form validation
	messages : {
		couponCode : {
			required : "Please enter the coupon code"
		},
		validity : {
			required : "Please enter the validity"
		},
		discountAmount : {
			required : "Please enter the discount amount",
			digits : "Please enter valid discount amount",
		},
		additionalUser : {
			required : "Please enter the additional users",
			digits : "Please enter valid additional users",
		},
		additionalSpace : {
			required : "Please enter the additional space",
			digits : "Please enter valid additional space",
		}
	},

	// Do not change code below
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	},
	submitHandler: function(form) {
		ajaxCallForSaveOrUpdateCouponForm(form.id);
	}
});

function ajaxCallForSaveOrUpdateCouponForm(formId) {
	var laddaRef;
	if(laddaButton != 0){
		 laddaRef = Ladda.create(laddaButton);
		laddaRef.start();
	}
	$
	.ajax({
		url : "saveOrUpdateCouponForm",
		type : "POST",
		data : $("#" + formId).serialize(),
		success : function(result) {
					if(laddaRef != undefined){
						laddaRef.stop();
					}
					result = JSON.parse(result);
					var actionType = "";
					if($("#couponId").val() != ""){
						actionType = "update";
					}
					if($("#typeOfAction").val() != "saveandcontinue"){
					     $("#remoteModal").modal("hide");
					}
					// CLearing Project Table
					if (result.ajaxResult == "success") {
						getCouponDetails();
						// Resetting Forms
						if(actionType != ""){
							  gritterForSucessMsgs("Coupon has been updated successfully.");
                          } else {
                          	 gritterForSucessMsgs("Coupon has been saved successfully.");
                          }
						resetCouponForm();
					} else {
						$("#remoteModal").modal("hide");
						resetCouponForm();
						if(result.reason == "couponCodeExist")
							{
							gritterForErrorMsgs("Coupon Code is already Exist."
									);
							}else{
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ result.reason);}
					}
				},
				error : function() {
					if(laddaRef != undefined){
						laddaRef.stop();
					}
					
					resetCouponForm();
					$("#remoteModal").modal("hide");
					gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');

				}
			});
}

function getCouponDetails() {
	$
			.ajax({
				url : "getCouponDetail",
				type : "POST",
				success : function(result) {
					couponFormDetails = JSON.parse(result);
					console.log(couponFormDetails);
					couponDetailsTable.fnClearTable();
					for (var t = 0; t < couponFormDetails.allCouponList.length; t++) {
						var validity = moment(new Date(couponFormDetails.allCouponList[t].validity)).format("MM-DD-YYYY");
						var isUsed = "",isOneTimeApplicable="",additionalSpace=0,additionalUser=0,discountAmount=0;
						if(couponFormDetails.allCouponList[t].isUsed != null 
								&& couponFormDetails.allCouponList[t].isUsed != undefined){
							isUsed = couponFormDetails.allCouponList[t].isUsed;
						} else{
							isUsed = false;
						}
						if(couponFormDetails.allCouponList[t].additionalSpace != null 
								&& couponFormDetails.allCouponList[t].additionalSpace != undefined){
							additionalSpace = couponFormDetails.allCouponList[t].additionalSpace;
						}
						if(couponFormDetails.allCouponList[t].additionalUser != null 
								&& couponFormDetails.allCouponList[t].additionalUser != undefined){
							additionalUser = couponFormDetails.allCouponList[t].additionalUser;
						}
						if(couponFormDetails.allCouponList[t].discountAmount != null 
								&& couponFormDetails.allCouponList[t].discountAmount != undefined){
							discountAmount = couponFormDetails.allCouponList[t].discountAmount;
						}
						if(couponFormDetails.allCouponList[t].isOneTimeApplicable != null 
								&& couponFormDetails.allCouponList[t].isOneTimeApplicable != undefined){
							isOneTimeApplicable = couponFormDetails.allCouponList[t].isOneTimeApplicable;
						} else{
							isOneTimeApplicable = false;
						}
						couponDetailsTable
								.fnAddData(
										[
										 		couponFormDetails.allCouponList[t].couponId,
												couponFormDetails.allCouponList[t].couponCode,
												validity,
												discountAmount,
												additionalUser,
												additionalSpace,
												isOneTimeApplicable,
												isUsed,
												t+1],
										false);
					}
					settingCouponCount(couponFormDetails);
					couponDetailsTable.fnDraw();
				}
			});
}

function setCouponToUpdate(couponId){
    $("#modelTitleId").html("Update Coupon");
	$("#remoteModal").modal("show");
	for (var u = 0; u < couponFormDetails.allCouponList.length; u++) {
		if(couponId == couponFormDetails.allCouponList[u].couponId){
			var validity = moment(new Date(couponFormDetails.allCouponList[u].validity)).format("MM-DD-YYYY");
			$("#couponId").val(couponFormDetails.allCouponList[u].couponId);
			$("#couponCode").val(couponFormDetails.allCouponList[u].couponCode);
			$("#validity").val(validity);
			$("#discountAmount").val(couponFormDetails.allCouponList[u].discountAmount);
			$("#additionalUser").val(couponFormDetails.allCouponList[u].additionalUser);
			$("#additionalSpace").val(couponFormDetails.allCouponList[u].additionalSpace);
			$('input:checkbox[name=isOneTimeApplicable]').prop('checked',couponFormDetails.allCouponList[u].isOneTimeApplicable);
			$("#save").html(
			"<i class='fa fa-thumbs-up bigger-110'></i> Update");
			$("#saveandcontinue").hide();
			break;
		}
	}
}

function setCouponToDelete(couponId){
	  // $("#activityId").val(activityId);
	    var dataObj = {
	    		couponIdToDel : couponId,
	    	};
	    $.ajax({
			url : "couponCodeDeleteAction",
			type : "POST",
			data : dataObj,
			success : function(result) {
				result = JSON.parse(result);
				// CLearing Project Table
				if (result.ajaxResult == "success") {
					getCouponDetails();
					gritterForSucessMsgs("Coupon has been deleted successfully.");
				} else {
					gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
							+ result.reason);
				}
			},
			error : function() {
				$("#couponFormModal").modal("hide");
				gritterForErrorMsgs('Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.');

			}
		});
}
function resetCouponForm(){
	var validator = $( "#couponFormId" ).validate();
	validator.resetForm();
			$('label').removeClass("state-success");
			$('label').removeClass("state-error");
			$("#couponId").val('');
			$("#typeOfAction").val('');
			$("#couponCode").val('');
			$("#validity").val("");
			$("#discountAmount").val("");
			$("#additionalUser").val("");
			$("#additionalSpace").val("");
			$('input:checkbox[name=isOneTimeApplicable]').prop('checked',false);
			$("#save").html(
			"<i class='fa fa-floppy-o'></i> &nbsp; Save ");
			$("#saveandcontinue").show();
}
function settingCouponCount(couponFormDetails){
	$("#totalCouponsId").html("&nbsp;"+couponFormDetails.allCouponList.length);
}
function setActionType(buttonId, laddaButtonTemp){
	$("#typeOfAction").val(buttonId);
	laddaButton = laddaButtonTemp;
}