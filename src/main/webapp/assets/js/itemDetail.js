/**
 * 
 */

pageSetUp();
ItemDetailTable = null;
var laddaButton = 0;
var itemIdToDelete = 0;
var limitStart = 0;
var limitEnd = 100;
/*
 * ALL PAGE RELATED SCRIPTS CAN GO BELOW HERE eg alert("my home function");
 * 
 * var pagefunction = function() { ... }
 * loadScript("js/plugin/_PLUGIN_NAME_.js", pagefunction);
 * 
 */

// PAGE RELATED SCRIPTS
// pagefunction
var pagefunction = function() {
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
	ItemDetailTable = $('#itemDetailTable')
			.dataTable(
					{
						"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"
								+ "t"
								+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
						"autoWidth" : true,
						"bFilter" : false,
						"bSearchable" : false,
						"bInfo" : true,
						"bDestroy" : true,
						"scrollY": "500px",
						  "scrollCollapse": true,
						  "paging": false,

						aoColumns : [
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[7];
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
									mData : null,
									sClass : "center",
									mRender : function(data, type, full) {
										return "<div class='pull-right action-buttons'><a title='edit' data-toggle='tooltip' class='blue'  href=\"javascript:itemUpdate('"
												+ full[0]
												+ "')\"> <i class='fa fa-pencil bigger-130'></i></a> <a class='deleteConfirmDialog' title='delete' data-toggle='modal' data-action='alertBox' href= 'javascript:void(0)' onclick=\"javascript:setItemId('"
												+ full[0]
												+ "')\"> <i class='fa fa-trash-o bigger-130'></i></a></div>";
									}
								} ],

						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#itemDetailTable'),
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
	getItemDetail();
	getCategory1List();
		/* END BASIC */
};

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

$(document).ready(function() {
	$('#MyWizard').on('change', function(e, data) {
	});

	$('#MyWizard').on('changed', function(e, data) {
	});

	$('#MyWizard').on('finished', function(e, data) {
	});

	$('#btnWizardPrev').on('click', function() {
		//$('#MyWizard').wizard('previous');
		
		if (($("#step2").hasClass("active"))) {
			$("#step2").removeClass("active");
			$("#step1").addClass("active");
			$("#btnWizardPrev").addClass("hide");
			return false;
		}
		
		if (($("#step3").hasClass("active"))) {
			$("#step3").removeClass("active");
			$("#step2").addClass("active");
			$("#save").hide();
			$("#reset").hide();
			$("#saveandcontinue").hide();
			return false;
		}

		
	});

	$('#btnWizardNext').on('click', function() {
		
		var form = $("#manageItemForm");
		form.validate({
			// Rules for form validation
			rules : {

				itemDescription : {
					required : true
				},
				categoryLevel1 : {
					required : true
				},
				itemCode : {
					required : true,
					digits : true,
				},
				quickTakeoffCode : {
					digits : true,
				},
				reference : {
					digits : true,
				},
				materialPrice : {
					number : true,
				},
				materialDiscount : {
					number : true,
				},
				materialNetCost : {
					number : true,
				},
				priceFactor : {
					number : true,
				},
				estimatingLevel : {
					number : true,
				},
				col2Labor : {
					number : true,
				},
				col3Labor : {
					number : true,
				},
				neca1 : {
					number : true,
				},
				neca2 : {
					number : true,
				},
				neca3 : {
					number : true,
				},
				weight : {
					number : true,
				},
				supplierCode : {
					number : true,
				},
				universalItemCode : {
					number : true,
				},
				actualLabor : {
					number : true,
				}
			},

			// Messages for form validations
			messages : {

				itemDescription : {
					required : 'Please enter the item description'
				},
				categoryLevel1 : {
					required : 'Please enter the category level 1'
				},
				itemCode : {
					required : 'Please enter the item code',
					digits : 'Please enter the item code in digits'
				},
				quickTakeoffCode : {
					digits : 'Please enter the Quick TakeOff Code in numbers',
				},
				reference : {
					digits : 'Please enter the reference in numbers',
				},
				materialPrice : {
					number : 'Please enter the material price in numbers',
				},
				materialDiscount : {
					number : 'Please enter the material discount in numbers',
				},
				materialNetCost : {
					number : 'Please enter the material net cost in numbers',
				},
				priceFactor : {
					number : 'Please enter the price factor in numbers',
				},
				estimatingLevel : {
					number : 'Please enter the estimating level in numbers',
				},
				col2Labor : {
					number : 'Please enter the col 2 labor in numbers',
				},
				col3Labor : {
					number : 'Please enter the col 3 labor in numbers',
				},
				neca1 : {
					number : 'Please enter the neca1 in numbers',
				},
				neca2 : {
					number : 'Please enter the neca1 in numbers',
				},
				neca3 : {
					number : 'Please enter the neca1 in numbers',
				},
				weight : {
					number : 'Please enter the weight in numbers',
				},
				supplierCode : {
					number : 'Please enter the supplier code in numbers',
				},
				universalItemCode : {
					number : 'Please enter the universal item code in numbers',
				},
				actualLabor : {
					number : 'Please enter the actual labor in numbers',
				}

			},
			// Do not change code below
			errorPlacement : function(error, element) {
				error.insertAfter(element.parent());
			},
			submitHandler : function(form) {
				ajaxCallForSaveOrUpdateItem(form.id);

			}
		});
		if (!form.valid()) {
			/* event.preventDefault(); */
		} else {
			
			if (($("#step1").hasClass("active"))) {
				$("#step1").removeClass("active");
				$("#step2").addClass("active");
				$("#btnWizardPrev").removeClass("hide");
				return false;
			}

			if (($("#step2").hasClass("active"))) {
				$("#step2").removeClass("active");
				$("#step3").addClass("active");
				$("#btnWizardPrev").removeClass("hide");
				$("#btnWizardNext").addClass("hide");
				$("#submitbuttons").removeClass("hide");
				$("#reset").show();
			}

			if (($("#step3").hasClass("active"))) {
				if($("#itemTypeAction").val()=="update"){
					$("#save").show();
					$("#saveandcontinue").hide();
				}
				else{
				$("#save").show();
				$("#saveandcontinue").show();
				}
				
				
			}
		}
	});

	$('#btnWizardStep').on('click', function() {
		var item = $('#MyWizard').wizard('selectedItem');
	});

	$('#MyWizard').on('stepclick', function(e, data) {
		if (data.step === 1) {
		}
	});

	$('#btnStep2').on('click', function(e, data) {
		$('[data-target=#step2]').trigger("click");
	});
});

$(document)
		.ready(
				function() {

					var navListItems = $('ul.setup-panel li a'), allWells = $('.setup-content');

					allWells.hide();

					navListItems.click(function(e) {
						e.preventDefault();
						var $target = $($(this).attr('href')), $item = $(
								this).closest('li');

						if (!$item.hasClass('disabled')) {
							navListItems.closest('li')
									.removeClass('active');
							$item.addClass('active');
							allWells.hide();
							$target.show();
						}
					});

					$('ul.setup-panel li.active a').trigger('click');

					// DEMO ONLY //
					$('#next1').on(
							'click',
							function(e) {

								$('ul.setup-panel li:eq(1)').removeClass(
										'disabled');
								$('ul.setup-panel li a[href="#step-2"]')
										.trigger('click');
								$(this).remove();

							})
					$('#next2').on(
							'click',
							function(e) {

								$('ul.setup-panel li:eq(2)').removeClass(
										'disabled');
								$('ul.setup-panel li a[href="#step-3"]')
										.trigger('click');
								$(this).remove();

							})
				});

function resetItemForm() {
	var validator = $("#manageItemForm").validate();
	validator.resetForm();
	$('label').removeClass("state-success");
	$('label').removeClass("state-error");
	$('input').val('');
	$("#categoryLevel1").val("");
	$("#categoryLevel2").val("");
	$("#categoryLevel3").val("");
	$("#save").html("Save");
	
	$("#btnWizardNext").val("Next");
	$("#btnWizardPrev").val("Back");
	
	$("#step1").addClass("active");
	$("#step2").removeClass("active");
	$("#step3").removeClass("active");
	
	$("#btnWizardPrev").addClass("hide");
	$("#itemModalTitle").html("Add New Item");
	$("#save").hide();
	$("#saveandcontinue").hide();
	$("#reset").hide();
	
	$("#btnWizardPrev").addClass("hide");
	$("#cancel").hide();
	
	$("#btnWizardNext").removeClass("hide");
	
	// $('.selectpicker').selectpicker('refresh');
}

function setItemId(itemId) {
	itemIdToDelete = itemId;
	$.SmartMessageBox(
			{
				title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
				content : "<br>This Item will be "
						+ "permanently deleted . Only Admin can	recover it.<br>"
						+ "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
				buttons : '[No][Yes]'
			}, function(ButtonPressed) {
				if (ButtonPressed === "Yes") {
					itemDelete();
				}
			});
};

//$("#btnWizardNext").click(function(){
//	console.log("in validate");
//	var form = $("#manageItemForm");
//	form.validate({
//	// Rules for form validation
//	rules : {
//	
//	  itemDescription : { required : true }, 
//	  laborUnit : { required : true },
//	  belcoMaterial : { required : true }
//	 
//	},
//
//	// Messages for form validations
//	messages : {
//	
//	 itemDescription : { required : 'Please enter the item description' },
//	 laborUnit : { required : 'plkeasdadsa' },
//	 belcoMaterial : { required : 'enter material' }
//	 
//	},
//	// Do not change code below
//	errorPlacement : function(error, element) {
//		error.insertAfter(element.parent());
//	},
//	submitHandler : function(form) {
//		ajaxCallForSaveOrUpdateItem(form.id);
//
//	}
//});
//	if(!form.valid()){
//		console.log("not valid");
//		event.preventDefault();
//	}
//	
//});

function ajaxCallForSaveOrUpdateItem(formId) {
	var laddaRef = Ladda.create(laddaButton);
	laddaRef.start();
	$
			.ajax({
				url : "addUpdateItemFormData",
				type : "POST",
				data : $("#" + formId).serialize(),
				success : function(result) {
					laddaRef.stop();
					var result1 = JSON.parse(result);
					if($("#submitId").val()=="save"){
						$("#itemRemoteModal").modal('hide');
//						$(".modal-backdrop").hide();
					}
					if (result1.ajaxResult == "success"){
						if($("#itemTypeAction").val()=="update"){
							$("#itemRemoteModal").modal('hide');
							gritterForSucessMsgs("A record of item type has been successfully updated.");
						}
						else{
							 gritterForSucessMsgs("A record of item type has been successfully saved.");
							 }
						limitStart = 0;
						isScroll = false;
						getItemDetail(isScroll);
						resetItemForm();
					}
					else{
						$("#itemRemoteModal").modal('hide');
//						$("#itemRemoteModal").hide();
//						$(".modal-backdrop").hide();
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ resultant.reason);
					}
				},
				error : function() {
					laddaRef.stop();
					resetItemForm();
					$("#itemRemoteModal").modal('hide');
					gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");
				}
			});
}

function bindScrollToTable(){
	 $('#itemDetailTable_wrapper > div.dataTables_scroll > div.dataTables_scrollBody').bind('scroll',function() {
	      if ($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight) {
	    	  var isScroll = true;
	    	  limitStart += 100;
	    	  getItemDetail(isScroll);
	      }
	     });
}

function getItemDetail(isScroll) {
var data={
		"limitStart" : limitStart,
		"limitEnd" : limitEnd
};
	itemDetail = "itemDetail";
	console.log("sdfs");
	$.ajax({
		url : "getItemDetails",
		type : "POST",
		data : data,
		success : function(result) {
			itemDetail = JSON.parse(result);
			if(itemDetail.statusReturned == "200"){
				var itemCount = 0;
				if(isScroll != true){
					ItemDetailTable.fnClearTable();
					$("#totalItems").html(itemDetail.allItemList.length);
				}else{
					itemCount = parseInt($("#totalItems").html());
					$("#totalItems").html(itemCount+itemDetail.allItemList.length);
				}
				var categoryLevel2="",categoryLevel3="",materialDiscount="",materialNetCost="";
				for (var t = 0; t < itemDetail.allItemList.length; t++) {
					itemCount++;
					if(itemDetail.allItemList[t].categoryLevel2==null)
						itemDetail.allItemList[t].categoryLevel2=categoryLevel2;
				
					if(itemDetail.allItemList[t].categoryLevel3==null)
						itemDetail.allItemList[t].categoryLevel3=categoryLevel3;
				
					if(itemDetail.allItemList[t].materialDiscount==null)
						itemDetail.allItemList[t].materialDiscount=materialDiscount;
				
					if(itemDetail.allItemList[t].materialNetCost==null)
						itemDetail.allItemList[t].materialNetCost=materialNetCost;
					ItemDetailTable.fnAddData([
					                           itemDetail.allItemList[t].itemId,
					                           itemDetail.allItemList[t].itemDescription,
					                           itemDetail.allItemList[t].categoryLevel1,
					                           itemDetail.allItemList[t].categoryLevel2,
					                           itemDetail.allItemList[t].categoryLevel3,
					                           itemDetail.allItemList[t].materialDiscount,
					                           itemDetail.allItemList[t].materialNetCost, (itemCount) ],
					                           false);
				}
				ItemDetailTable.fnDraw();
				if(isScroll != true){
					bindScrollToTable();
				}
			}
			else{
				gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
						+ itemDetail.reason);
			}
		}
	});
	
}
function getCategory1List(){
	var data={
			"itemDetail" : "itemDetail"
	};
	$.ajax({
		url : "getCategory1List",
		type : "POST",
		data : data,
		success : function(result) {
			getCategory1List = result;
			var elementId = document.getElementById("categoryLevel1");
			for(var i=0;i<getCategory1List.length;i++){
				var optn = document.createElement("OPTION");
				optn.text = getCategory1List[i];
				optn.value = getCategory1List[i];
				elementId.options.add(optn);
			}
		}
	});
}
$("#categoryLevel1").on(
		"change",
		function(e) {
			var value = $("#categoryLevel1").val();
			if (value != "") {
				getcategoryLevel2(value);
			} else {
				$('#categoryLevel2').find('option').remove().end().append(
						'<option value="">Category 2</option>');
			}
		});
function generateJsonStringForItemSearch(categorySelected) {

	var categorySelectedTemp = categorySelected;
	var categorySelectedString = "";

	if (categorySelectedTemp != undefined && categorySelectedTemp != null) {
		for (var len = 0; len < categorySelectedTemp.length; len++) {
			categorySelectedTemp[len] = categorySelectedTemp[len].trim();
			if (categorySelectedTemp[len].indexOf('"') != -1) {
				categorySelectedTemp[len] = categorySelectedTemp[len].replace(
						/"/g, '\\"');
			} else if (categorySelectedTemp[len].indexOf("'") != -1) {
				categorySelectedTemp[len] = categorySelectedTemp[len].replace(
						/'/g, "\\'");
			}
			if (len == categorySelectedTemp.length - 1) {
				categorySelectedString += categorySelectedTemp[len];
			} else {
				categorySelectedString += categorySelectedTemp[len] + "','";
			}
		}
	}

	return categorySelectedString;
}
function getcategoryLevel2(value){
	var category1Selected = "category1LabelInJson";
	var category1SelectedJSON = generateJsonStringForItemSearch(value);
	$.ajax({
		url : "getCategory2List",
		type : "POST",
		dataType : "json",
		contentType : "application/json",
		data : '{"' + category1Selected + '": "'
				+ category1SelectedJSON + '"}',
		success : function(result) {
			categoryLevel2 = result;
				var elementId = document.getElementById("categoryLevel2");
				$('#categoryLevel2').find('option').remove().end().append(
				'<option value="">Category 2</option>');
				for (var i = 0; i < categoryLevel2.length; i++) {
					var optn = document.createElement("OPTION");
					optn.text = categoryLevel2[i];
					optn.value = categoryLevel2[i];
					elementId.options.add(optn);
				}
		},
		error : function() {
			console.log("error");
		}
	});
}
$("#categoryLevel2").on(
		"change",
		function(e) {
			var value = $("#categoryLevel2").val();
			if (value != "") {
				getcategoryLevel3(value);
			} else {
				$('#categoryLevel3').find('option').remove().end().append(
						'<option value="">Category 3</option>');
			}
		});
function getcategoryLevel3(value){
	var category1Selected = "category1LabelInJson";
	var category2Selected = "category2LabelInJson";
	var category1SelectedJSON = generateJsonStringForItemSearch($("#categoryLevel1").val());
	var category2SelectedJSON = generateJsonStringForItemSearch(value);
	$.ajax({
		url : "getCategory3List",
		type : "POST",
		dataType : "json",
		contentType : "application/json",
		data : '{"' + category1Selected + '": "' + category1SelectedJSON
				+ '","' + category2Selected + '":"' + category2SelectedJSON
				+ '"}',
		success : function(result) {
			categoryLevel3 = result;
				var elementId = document.getElementById("categoryLevel3");
				$('#categoryLevel3').find('option').remove().end().append(
				'<option value="">Category 3</option>');
				for (var i = 0; i < categoryLevel3.length; i++) {
					var optn = document.createElement("OPTION");
					optn.text = categoryLevel3[i];
					optn.value = categoryLevel3[i];
					elementId.options.add(optn);
				}
		},
		error : function() {
			console.log("error");
		}
	});
}
function itemUpdate(id) {
	var data={
			"itemId" : id,
	};
	$.ajax({
		url : "getItemDetailsByItemId",
		type : "POST",
		data : data,
		success : function(result) {
			itemDetail = JSON.parse(result);
			if(itemDetail.statusReturned == "200"){
				showItemUpdateData(id);
			}
		}
	});
	
}
function showItemUpdateData(id){
	for (var i = 0; i < itemDetail.allItemList.length; i++) {
		if (id == itemDetail.allItemList[i].itemId) {
			$("#itemDbNumber").val(id);
			$("#basedOn").val(itemDetail.allItemList[i].basedOn);
			$("#belcoLabor").val(itemDetail.allItemList[i].belcoLabor);
			$("#belcoLaborDescription").val(itemDetail.allItemList[i].belcoLaborDescription);
			$("#belcoMaterial").val(itemDetail.allItemList[i].belcoMaterial);
			$("#belcoMaterialDescription").val(itemDetail.allItemList[i].belcoMaterialDescription);
			$("#catalogNumber").val(itemDetail.allItemList[i].catalogNumber);
			$("#categoryLevel1").val(itemDetail.allItemList[i].categoryLevel1);
			$("#categoryLevel2").val(itemDetail.allItemList[i].categoryLevel2);
			$("#categoryLevel3").val(itemDetail.allItemList[i].categoryLevel3);
			$("#col2Labor").val(itemDetail.allItemList[i].col2Labor);
			$("#col3Labor").val(itemDetail.allItemList[i].col3Labor);
			$("#estimatingLevel").val(itemDetail.allItemList[i].estimatingLevel);
			$("#itemDescription").val(itemDetail.allItemList[i].itemDescription);
			$("#itemCode").val(itemDetail.allItemList[i].itemCode);
			$("#laborCode").val(itemDetail.allItemList[i].laborCode);
			$("#laborCondition").val(itemDetail.allItemList[i].laborCondition);
			$("#laborUnit").val(itemDetail.allItemList[i].laborUnit);
			$("#manufacturerName").val(itemDetail.allItemList[i].manufacturerName);
			$("#materialCondition").val(itemDetail.allItemList[i].materialCondition);
			$("#materialDiscount").val(itemDetail.allItemList[i].materialDiscount);
			$("#materialNetCost").val(itemDetail.allItemList[i].materialNetCost);
			$("#materialPrice").val(itemDetail.allItemList[i].materialPrice);
			$("#materialUnit").val(itemDetail.allItemList[i].materialUnit);
			$("#neca1").val(itemDetail.allItemList[i].neca1);
			$("#neca2").val(itemDetail.allItemList[i].neca2);
			$("#neca3").val(itemDetail.allItemList[i].neca3);
			$("#netCondition").val(itemDetail.allItemList[i].netCondition);
			$("#priceCode").val(itemDetail.allItemList[i].priceCode);
			$("#priceFactor").val(itemDetail.allItemList[i].priceFactor);
			$("#quickTakeoffCode").val(itemDetail.allItemList[i].quickTakeoffCode);
			$("#reference").val(itemDetail.allItemList[i].item_reference);
			$("#supplierCode").val(itemDetail.allItemList[i].supplierCode);
			$("#supplierName").val(itemDetail.allItemList[i].supplierName);
			$("#universalItemCode").val(itemDetail.allItemList[i].universalItemCode);
			$("#weight").val(itemDetail.allItemList[i].weight);
			$("#weightUnit").val(itemDetail.allItemList[i].weightUnit);
			$("#actualLabor").val(itemDetail.allItemList[i].actualLabor);
			$("#save").html("<i class='fa fa-thumbs-up bigger-110'></i> Update");
			//break;
		}
		$("#btnWizardNext").removeClass("hide");
		$("#saveandcontinue").hide();
	}
	$("#itemModalTitle").html("Update Item");
	$("#itemTypeAction").val("update");
	$("#itemRemoteModal").modal('show');
}
function itemDelete() {

	$("#itemTypeAction").val("delete");
	var dataObj = {
		itemIdToDel : itemIdToDelete,
	};
	$
			.ajax({
				url : "deleteItem",
				type : "POST",
				data : dataObj,
				success : function(result) {
					result = JSON.parse(result);
					resetItemForm();

					// CLearing Department Table
					if (result.ajaxResult == "success") {
						$("#itemRemoteModal").modal("hide");
						gritterForSucessMsgs("A record of item type has been successfully deleted");
						limitStart = 0;
						isScroll = false;
						getItemDetail(isScroll);
						resetItemForm();
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

function submitType(buttonId, laddaButtonTemp) {
	$("#submitId").val(buttonId);
	laddaButton = laddaButtonTemp;
}

function resetItemDbUploadForm(){
	$("#confirmItemDbUploadId").val(null);
	$("#itemDbFileName").val(null);
}

$.validator.addMethod("checkSize",function(value, element){
	
	var size = element.files[0].size;
    if(size > 15728640){
    	return false;
    }
    else{
    	return true;
    }
},"Please upload a file with size less than 15 MB");

$("#itemDbUploadForm").validate({
	rules : {
		itemDbTemplateFile : {
			required : true,
			checkSize : true
		}
	},
	messages : {
		itemDbTemplateFile : {
			required : 'Please select a file'
		},
	},
	submitHandler : function(form) {
		// form.submit();
		ajaxCallForAddItemTemplate();
	}
});

function ajaxCallForAddItemTemplate(){
	var msg="";
	$
	.ajax({
		url : 'itemTemplateController',
		data : new FormData($("#itemDbUploadForm")[0]),
		cache : false,
		contentType : false,
		processData : false,
		type : 'POST',
		success : function(data) {
			$("#itemDbUploadModal").modal('hide');
			result = JSON.parse(data);
			if(result.ajaxResult =="failure"){
				resetItemDbUploadForm();
				var out = document.getElementById("errorBlock");
				out.innerHTML = "";
				if(result.reason[0].colNumber == -1){
					for(i=0;i<result.reason.length;i++){
						out.appendChild(document.createTextNode("Row : "+result.reason[i].rowNumber+" Error :"+result.reason[i].errorMessage));
						out.appendChild(document.createElement("br"));
					}
					$("#itemDbConfirmationHeader").hide();
					$("#saveItemDbConfirmUpload").hide();
					$("#cancelItemDbUpload").html("Close");
					getItemDetail();
				}
				else if(result.reason[0].excelCell == "A0"){
					for(i=0;i<result.reason.length;i++){
						out.appendChild(document.createTextNode(" Error :"+result.reason[i].errorMessage));
						out.appendChild(document.createElement("br"));
					}
					$("#saveItemDbConfirmUpload").hide();
				}
				else{
					for(i=0;i<result.reason.length;i++){
						if(result.reason[i].excelCell == undefined){
							out.appendChild(document.createTextNode(" Row Number :"+result.reason[i].rowNumber+" Error :"+result.reason[i].errorMessage));
						}
						else{
							out.appendChild(document.createTextNode(" Cell Address :"+result.reason[i].excelCell+" Error :"+result.reason[i].errorMessage));
						}
						out.appendChild(document.createElement("br"));
					}
				}
				$("#itemDbUploadConfirmation").modal("show");
				$("#itemDbErrorSection").css('display','block');
			}
			else if(result.ajaxResult == "success"){
				gritterForSucessMsgs("A file of item type has been successfully added");
				resetItemDbUploadForm();
				getItemDetail();
				cancelItemDbUploadForm();
			}
			else if(result.ajaxResult == "failure"){
				gritterForErrorMsgs("An error occurred : "+ result.reason);
			}
			else{
				gritterForErrorMsgs("Could not be saved.Contact dev");
			}
		},
		error : function() {
			alert("Some problem occured");
		}
	});
}
function setItemDbUploadConfirmation(buttonId){
	 
	$("#itemDbUploadConfirmation").modal("hide"); 
	if(buttonId == "saveItemDbConfirmUpload"){
	  $("#confirmItemDbUploadId").val(1);
	  ajaxCallForAddItemTemplate();
	}
	else
	  $("#confirmItemDbUploadId").val(-1);
}

function resetItemDbConfirmation(){
	$("#saveItemDbConfirmUpload").show();
	$("#itemDbConfirmationHeader").show();
	$("#cancelItemDbUpload").html("Cancel");
	$("#itemDbUploadConfirmation").modal("hide");
}

function cancelItemDbUploadForm(){
	var validator = $("#itemDbUploadForm").validate();
	validator.resetForm();
	$('input').val('');
}