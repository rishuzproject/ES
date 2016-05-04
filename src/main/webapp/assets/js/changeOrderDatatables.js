/**
 * @ Ashutosh
 */

function initializeRfcTakeOffSheetTable(rfcTakeOffSheetsList, rfcSheets) {
	takeOffSheetTable.fnClearTable();
	for (var i = 0; i < rfcTakeOffSheetsList.length; i++) {
		var submittedDate = rfcSheets[i].submittedDate;
		if (rfcSheets[i].submittedDate == null
				|| rfcSheets[i].submittedDate == undefined) {
			submittedDate = "";
		}
		takeOffSheetTable.fnAddData([ i + 1, rfcSheets[i].fileName,
				rfcTakeOffSheetsList[i].rfcLogSno.rfcDesc, submittedDate,
				rfcSheets[i].fileId ], false);
	}
	takeOffSheetTable.fnDraw();
}
function initializeRfcMappingSelectBox(rfcData) {
	$('#extrfcMapping').find('option').remove().end().append(
			'<option value="">--Select--</option>');
	var extrfcMapping = document.getElementById("extrfcMapping");
	for (var i = 0; i < rfcData.rfcLogList.length; i++) {
		if (rfcData.rfcLogList[i].rfcType == "External"
				&& rfcData.rfcLogList[i].deleted != true) {
			var option = document.createElement("option");
			option.text = rfcData.rfcLogList[i].rfcDesc;
			option.value = rfcData.rfcLogList[i].sNo;
			try {
				extrfcMapping.add(option, select.options[null]);
			} catch (e) {
				extrfcMapping.add(option, null);
			}
		}

	}
}
function addDeletedTableData(rfcData) {
	deletedChangeOrderTable.fnClearTable();
	var origDate, rfcStatus, rfcType;
	for (var i = 0; i < rfcData.rfcLogList.length; i++) {
		origDate = rfcStatus = rfcType = "";
		if (rfcData.rfcLogList[i].origDate == null
				|| rfcData.rfcLogList[i].origDate == undefined)
			origDate = "";
		else
			origDate = rfcData.rfcLogList[i].origDate;

		if (rfcData.rfcLogList[i].rfcStatus == null
				|| rfcData.rfcLogList[i].rfcStatus == undefined)
			rfcStatus = "";
		else
			rfcStatus = rfcData.rfcLogList[i].rfcStatus;
		if (rfcData.rfcLogList[i].rfcType == null
				|| rfcData.rfcLogList[i].rfcType == undefined)
			rfcType = "";
		else
			rfcType = rfcData.rfcLogList[i].rfcType;

		if (rfcData.rfcLogList[i].deleted == true) {
			deletedChangeOrderTable.fnAddData([ i + 1,
					rfcData.rfcLogList[i].rfcDesc, rfcType, rfcStatus,
					rfcData.rfcLogList[i].quoted,
					rfcData.rfcLogList[i].approved,
					rfcData.rfcLogList[i].totalCost,
					rfcData.rfcLogList[i].grossProfit,
					rfcData.rfcLogList[i].grossMargin,
					rfcData.rfcLogList[i].sNo ], false);
		}
	}
	deletedChangeOrderTable.fnDraw();
}
pageSetUp();

var allChangeOrderTable = null;
var approvedChangeOrderTable = null;
var priceProceedChangeOrderTable = null;
var pendingChangeOrderTable = null;
var deniedChangeOrderTable = null;
var deletedChangeOrderTable = null;
var takeOffSheetTable = null;

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

	deletedChangeOrderTable = $('#deletedChangeOrderTable')
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
						// "aoColumnDefs" : [ {
						// "aTargets" : [ 3, 4, 5, 6 ],
						// "bSortable" : false,
						// "fnCreatedCell" : function(nTd, sData, oData, iRow,
						// iCol) {
						// var $currencyCell = $(nTd);
						// var commaValue = $currencyCell.text().replace(
						// /(\d)(?=(\d\d\d)+(?!\d))/g, "1,");
						// $currencyCell.text(commaValue);
						// }
						// } ],
						"aoColumns" : [

								{
									"mData" : null,
									"sClass" : "center",
									"sDefaultContent" : '<img src="assets/img/details_open.png">'
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
										try {
											return "$" + (full[4]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										try {
											return "$" + (full[5]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										try {
											return "$" + (full[6]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										try {
											return "$" + (full[7]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										try {
											return (full[8]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										var id = full[9];
										return "<div class='pull-right action-buttons'><a title= 'Restore this RFC.' "
												+ "class='glyphicon glyphicon-share-alt ' href=\"javascript:restoreDeletedRfc('"
												+ id + "')\"></a></div>";
									}
								} ],
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#deletedChangeOrderTable'),
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

	takeOffSheetTable = $('#takeOffSheetTable')
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
						"aoColumns" : [
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return full[0];
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										return "<a  href=\"javascript:downloadTakeOffSheets('"
												+ full[4]
												+ "')\">"
												+ full[1]
												+ "</a>";
									}
								}, {
									sClass : "center",
									mRender : function(data, type, full) {
										return full[2];
									}
								}, {
									sClass : "center",
									mRender : function(data, type, full) {
										return full[3];
									}
								} ],
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#takeOffSheetTable'),
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

	allChangeOrderTable = $('#allChangeOrderTable')
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
						// "aoColumnDefs" : [ {
						// "aTargets" : [ 3, 4, 5, 6 ],
						// "bSortable" : false,
						// "fnCreatedCell" : function(nTd, sData, oData, iRow,
						// iCol) {
						// var $currencyCell = $(nTd);
						// var commaValue = $currencyCell.text().replace(
						// /(\d)(?=(\d\d\d)+(?!\d))/g, "1,");
						// $currencyCell.text(commaValue);
						// }
						// } ],
						"aoColumns" : [

								{
									"mData" : null,
									"sClass" : "center",
									"sDefaultContent" : '<img src="assets/img/details_open.png">'
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
										try {
											return "$" + (full[4]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										try {
											return "$" + (full[5]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										try {
											return "$" + (full[6]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										try {
											return "$" + (full[7]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										try {
											return (full[8]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										var id = full[9];
										return "<div class='pull-right action-buttons'><a class='glyphicon glyphicon-pencil ' href=\"javascript:toggleExternalRfc('"
												+ id
												+ "')\"></a> <a class='deleteConfirmDialog glyphicon glyphicon-trash ' href=\"javascript:setDeleted('"
												+ id + "')\"></a></div>";
									}
								} ],
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#allChangeOrderTable'),
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
	approvedChangeOrderTable = $('#approvedChangeOrderTable')
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
						// "aoColumnDefs" : [ {
						// "aTargets" : [ 3, 4, 5, 6 ],
						// "bSortable" : false,
						// "fnCreatedCell" : function(nTd, sData, oData, iRow,
						// iCol) {
						// var $currencyCell = $(nTd);
						// var commaValue = $currencyCell.text().replace(
						// /(\d)(?=(\d\d\d)+(?!\d))/g, "1,");
						// $currencyCell.text(commaValue);
						// }
						// } ],
						"aoColumns" : [

								{
									"mData" : null,
									"sClass" : "center",
									"sDefaultContent" : '<img src="assets/img/details_open.png">'
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
										try {
											return "$" + (full[4]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										try {
											return "$" + (full[5]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										try {
											return "$" + (full[6]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										try {
											return "$" + (full[7]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										try {
											return (full[8]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										var id = full[9];
										return "<div class='pull-right action-buttons'><a class='glyphicon glyphicon-pencil ' href=\"javascript:toggleExternalRfc('"
												+ id
												+ "')\"></a> <a class='deleteConfirmDialog glyphicon glyphicon-trash ' href=\"javascript:setDeleted('"
												+ id + "')\"></a></div>";
									}
								} ],
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#approvedChangeOrderTable'),
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
	priceProceedChangeOrderTable = $('#priceProceedChangeOrderTable')
			.dataTable(
					{
						"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12	 hidden-xs'l>r>"
								+ "t"
								+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
						"autoWidth" : true,
						"bFilter" : true,
						"bSearchable" : false,
						"bInfo" : true,
						"bDestroy" : true,
						// "aoColumnDefs" : [ {
						// "aTargets" : [ 3, 4, 5, 6 ],
						// "bSortable" : false,
						// "fnCreatedCell" : function(nTd, sData, oData, iRow,
						// iCol) {
						// var $currencyCell = $(nTd);
						// var commaValue = $currencyCell.text().replace(
						// /(\d)(?=(\d\d\d)+(?!\d))/g, "1,");
						// $currencyCell.text(commaValue);
						// }
						// } ],
						"aoColumns" : [

								{
									"mData" : null,
									"sClass" : "center",
									"sDefaultContent" : '<img src="assets/img/details_open.png">'
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
										try {
											return "$" + (full[4]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										try {
											return "$" + (full[5]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										try {
											return "$" + (full[6]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										try {
											return "$" + (full[7]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										try {
											return (full[8]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										var id = full[9];
										return "<div class='pull-right action-buttons'><a class='glyphicon glyphicon-pencil ' href=\"javascript:toggleExternalRfc('"
												+ id
												+ "')\"></a> <a class='deleteConfirmDialog glyphicon glyphicon-trash ' href=\"javascript:setDeleted('"
												+ id + "')\"></a></div>";
									}
								} ],
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#priceProceedChangeOrderTable'),
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
	pendingChangeOrderTable = $('#pendingChangeOrderTable')
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
						// "aoColumnDefs" : [ {
						// "aTargets" : [ 3, 4, 5, 6 ],
						// "bSortable" : false,
						// "fnCreatedCell" : function(nTd, sData, oData, iRow,
						// iCol) {
						// var $currencyCell = $(nTd);
						// var commaValue = $currencyCell.text().replace(
						// /(\d)(?=(\d\d\d)+(?!\d))/g, "1,");
						// $currencyCell.text(commaValue);
						// }
						// } ],
						"aoColumns" : [

								{
									"mData" : null,
									"sClass" : "center",
									"sDefaultContent" : '<img src="assets/img/details_open.png">'
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
										try {
											return "$" + (full[4]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										try {
											return "$" + (full[5]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										try {
											return "$" + (full[6]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										try {
											return "$" + (full[7]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										try {
											return (full[8]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										var id = full[9];
										return "<div class='pull-right action-buttons'><a class='glyphicon glyphicon-pencil ' href=\"javascript:toggleExternalRfc('"
												+ id
												+ "')\"></a> <a class='deleteConfirmDialog glyphicon glyphicon-trash' href=\"javascript:setDeleted('"
												+ id + "')\"></a></div>";
									}
								} ],
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#pendingChangeOrderTable'),
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
	deniedChangeOrderTable = $('#deniedChangeOrderTable')
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
						// "aoColumnDefs" : [ {
						// "aTargets" : [ 3, 4, 5, 6 ],
						// "bSortable" : false,
						// "fnCreatedCell" : function(nTd, sData, oData, iRow,
						// iCol) {
						// var $currencyCell = $(nTd);
						// var commaValue = $currencyCell.text().replace(
						// /(\d)(?=(\d\d\d)+(?!\d))/g, "1,");
						// $currencyCell.text(commaValue);
						// }
						// } ],
						"aoColumns" : [

								{
									"mData" : null,
									"sClass" : "center",
									"sDefaultContent" : '<img src="assets/img/details_open.png">'
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
										try {
											return "$" + (full[4]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										try {
											return "$" + (full[5]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										try {
											return "$" + (full[6]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										try {
											return "$" + (full[7]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										try {
											return (full[8]).toFixed(2);
										} catch (e) {
											return "";
										}
									}
								},
								{
									sClass : "center",
									mRender : function(data, type, full) {
										var id = full[9];
										return "<div class='pull-right action-buttons'><a class='glyphicon glyphicon-pencil ' href=\"javascript:toggleExternalRfc('"
												+ id
												+ "')\"></a> <a class='deleteConfirmDialog glyphicon glyphicon-trash' href=\"javascript:setDeleted('"
												+ id + "')\"></a></div>";
									}
								} ],
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#deniedChangeOrderTable'),
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
	getProjectList();
};
function getProjectList() {
	$.ajax({
		url : "getProjectListForRfcSelectpicker",
		type : "POST",
		success : function(result) {
			initializeSelectProjectForRfc(JSON.parse(result));
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log(xhr);
			console.log(ajaxOptions);
			console.log(thrownError);
		},
		complete : function() {
			// $('#loading').modal('hide');
		}
	});
}
function addExternalTableData(result) {
	allChangeOrderTable.fnClearTable();
	approvedChangeOrderTable.fnClearTable();
	priceProceedChangeOrderTable.fnClearTable();
	pendingChangeOrderTable.fnClearTable();
	deniedChangeOrderTable.fnClearTable();

	var origDate, rfcStatus, rfcType, quoted, approved;
	for (var i = 0; i < result.rfcLogList.length; i++) {
		if (result.rfcLogList[i].deleted != true) {
			origDate = rfcStatus = rfcType = quoted = approved = "";
			if (result.rfcLogList[i].origDate == null
					|| result.rfcLogList[i].origDate == undefined)
				origDate = "";
			else
				origDate = result.rfcLogList[i].origDate;

			if (result.rfcLogList[i].rfcStatus == null
					|| result.rfcLogList[i].rfcStatus == undefined)
				rfcStatus = "";
			else
				rfcStatus = result.rfcLogList[i].rfcStatus;

			// if (result.rfcLogList[i].quoted == null
			// || result.rfcLogList[i].quoted == undefined)
			// quoted = "";
			// else
			// quoted = result.rfcLogList[i].quoted;
			// if (result.rfcLogList[i].approved == null
			// || result.rfcLogList[i].approved == undefined)
			// approved = "";
			// else
			// approved = result.rfcLogList[i].approved;
			// Adding Item to Table
			allChangeOrderTable.fnAddData(
					[ i + 1, result.rfcLogList[i].rfcDesc,
							result.rfcLogList[i].rfcType, rfcStatus,
							result.rfcLogList[i].quoted,
							result.rfcLogList[i].approved,
							result.rfcLogList[i].totalCost,
							result.rfcLogList[i].grossProfit,
							result.rfcLogList[i].grossMargin,
							result.rfcLogList[i].sNo ], false);
			if (result.rfcLogList[i].rfcStatus == "Approved") {
				approvedChangeOrderTable.fnAddData([ i + 1,
						result.rfcLogList[i].rfcDesc,
						result.rfcLogList[i].rfcType, rfcStatus,
						result.rfcLogList[i].quoted,
						result.rfcLogList[i].approved,
						result.rfcLogList[i].totalCost,
						result.rfcLogList[i].grossProfit,
						result.rfcLogList[i].grossMargin,
						result.rfcLogList[i].sNo ], false);
			}
			if (result.rfcLogList[i].rfcStatus == "Price & Proceed") {
				priceProceedChangeOrderTable.fnAddData([ i + 1,
						result.rfcLogList[i].rfcDesc,
						result.rfcLogList[i].rfcType, rfcStatus,
						result.rfcLogList[i].quoted,
						result.rfcLogList[i].approved,
						result.rfcLogList[i].totalCost,
						result.rfcLogList[i].grossProfit,
						result.rfcLogList[i].grossMargin,
						result.rfcLogList[i].sNo ], false);
			}
			if (result.rfcLogList[i].rfcStatus == "Pending") {
				pendingChangeOrderTable.fnAddData([ i + 1,
						result.rfcLogList[i].rfcDesc,
						result.rfcLogList[i].rfcType, rfcStatus,
						result.rfcLogList[i].quoted,
						result.rfcLogList[i].approved,
						result.rfcLogList[i].totalCost,
						result.rfcLogList[i].grossProfit,
						result.rfcLogList[i].grossMargin,
						result.rfcLogList[i].sNo ], false);
			}
			if (result.rfcLogList[i].rfcStatus == "Denied") {
				deniedChangeOrderTable.fnAddData([ i + 1,
						result.rfcLogList[i].rfcDesc,
						result.rfcLogList[i].rfcType, rfcStatus,
						result.rfcLogList[i].quoted,
						result.rfcLogList[i].approved,
						result.rfcLogList[i].totalCost,
						result.rfcLogList[i].grossProfit,
						result.rfcLogList[i].grossMargin,
						result.rfcLogList[i].sNo ], false);
			}
		}
	}
	allChangeOrderTable.fnDraw();
	approvedChangeOrderTable.fnDraw();
	priceProceedChangeOrderTable.fnDraw();
	pendingChangeOrderTable.fnDraw();
	deniedChangeOrderTable.fnDraw();
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
/*
 * ======== History For a particular RFC Goes down below @Ashutosh ========
 */
var anOpen = [];
var rfcTrackingList = "";
$(document).on(
		"click",
		"#allChangeOrderTable td.sorting_1",

		function() {
			var nTr = this.parentNode;
			var i = $.inArray(nTr, anOpen);
			if (i === -1) {
				$('img', this).attr('src', "assets/img/details_close.png");
				oTableExtALLTable = $("#allChangeOrderTable").dataTable();
				var nDetailsRow = oTableExtALLTable.fnOpen(nTr,
						fnFormatDetails(oTableExtALLTable, nTr), 'details');
				$('#innerDetails', nDetailsRow).slideDown();
				anOpen.push(nTr);
			} else {
				$('img', this).attr('src', "assets/img/details_open.png");
				$('#innerDetails', $(nTr).next()[0]).slideUp(10, function() {
					oTableExtALLTable.fnClose(nTr);
					anOpen.splice(i, 1);
				});
			}
		});

$(document).on(
		"click",
		"#approvedChangeOrderTable td.sorting_1",

		function() {
			var nTr = this.parentNode;
			var i = $.inArray(nTr, anOpen);
			if (i === -1) {
				$('img', this).attr('src', "assets/img/details_close.png");
				oTableExtApprovedTable = $("#approvedChangeOrderTable")
						.dataTable();
				var nDetailsRow = oTableExtApprovedTable
						.fnOpen(nTr, fnFormatDetails(oTableExtApprovedTable,
								nTr), 'details');
				$('#innerDetails', nDetailsRow).slideDown();
				anOpen.push(nTr);
			} else {
				$('img', this).attr('src', "assets/img/details_open.png");
				$('#innerDetails', $(nTr).next()[0]).slideUp(10, function() {
					oTableExtApprovedTable.fnClose(nTr);
					anOpen.splice(i, 1);
				});
			}
		});

$(document).on(
		"click",
		"#priceProceedChangeOrderTable td.sorting_1",

		function() {
			var nTr = this.parentNode;
			var i = $.inArray(nTr, anOpen);
			if (i === -1) {
				$('img', this).attr('src', "assets/img/details_close.png");
				oTableExtPriceTable = $("#priceProceedChangeOrderTable")
						.dataTable();
				var nDetailsRow = oTableExtPriceTable.fnOpen(nTr,
						fnFormatDetails(oTableExtPriceTable, nTr), 'details');
				$('#innerDetails', nDetailsRow).slideDown();
				anOpen.push(nTr);
			} else {
				$('img', this).attr('src', "assets/img/details_open.png");
				$('#innerDetails', $(nTr).next()[0]).slideUp(10, function() {
					oTableExtPriceTable.fnClose(nTr);
					anOpen.splice(i, 1);
				});
			}
		});

$(document)
		.on(
				"click",
				"#pendingChangeOrderTable td.sorting_1",
				function() {
					var nTr = this.parentNode;
					var i = $.inArray(nTr, anOpen);
					if (i === -1) {
						$('img', this).attr('src',
								"assets/img/details_close.png");
						oTableExtPendingTable = $("#pendingChangeOrderTable")
								.dataTable();
						var nDetailsRow = oTableExtPendingTable.fnOpen(nTr,
								fnFormatDetails(oTableExtPendingTable, nTr),
								'details');
						$('#innerDetails', nDetailsRow).slideDown();
						anOpen.push(nTr);
					} else {
						$('img', this).attr('src', "./images/details_open.png");
						$('#innerDetails', $(nTr).next()[0]).slideUp(10,
								function() {
									oTableExtPendingTable.fnClose(nTr);
									anOpen.splice(i, 1);
								});
					}
				});

$(document)
		.on(
				"click",
				"#deniedChangeOrderTable td.sorting_1",
				function() {
					var nTr = this.parentNode;
					var i = $.inArray(nTr, anOpen);
					if (i === -1) {
						$('img', this).attr('src',
								"assets/img/details_close.png");
						oTableExtDeniedTable = $("#deniedChangeOrderTable")
								.dataTable();
						var nDetailsRow = oTableExtDeniedTable.fnOpen(nTr,
								fnFormatDetails(oTableExtDeniedTable, nTr),
								'details');
						$('#innerDetails', nDetailsRow).slideDown();
						anOpen.push(nTr);
					} else {
						$('img', this).attr('src', "./images/details_open.png");
						$('#innerDetails', $(nTr).next()[0]).slideUp(10,
								function() {
									oTableExtDeniedTable.fnClose(nTr);
									anOpen.splice(i, 1);
								});
					}
				});
var rfcTrackingArray = null;
function fnFormatDetails(oTable, nTr) {
	oData = oTable.fnGetData(nTr);
	var rfcId = oData[9];
	sOut = '<div class="well innerTable" id="innerDetails"><table class="table table-bordered table-condensed"><tbody><thead style="color:#0081B5"><tr><th style="text-align:center;">Orig. Ref No</th><th style="text-align:center;">Change Date</th><th style="text-align:center;">Status</th><th style="text-align:center;">Quoted $</th><th style="text-align:center;">Appr $</th><th style="text-align:center;">Tot Cost</th><th style="text-align:center;">Profit $</th><th style="text-align:center;">Margin %</th></tr></thead><tbody style="color:#a3a3a3;text-align:center">';
	$
			.ajax({
				url : "GetRfcTrackingInfo.do",
				type : "POST",
				data : "rfcLogId=" + rfcId,
				success : function(result) {
					rfcTrackingArray = JSON.parse(result);
					if (rfcTrackingArray.rfcTrackingArray.length != 0) {
						for (var i = 0; i < rfcTrackingArray.rfcTrackingArray.length; i++) {
							var temporgRefNo = rfcTrackingArray.rfcTrackingArray[i].orgRefNo == undefined ? ""
									: rfcTrackingArray.rfcTrackingArray[i].orgRefNo;
							var changeDate = rfcTrackingArray.rfcTrackingArray[i].submittedDate == undefined ? ""
									: rfcTrackingArray.rfcTrackingArray[i].submittedDate
							sOut += '<tr><td class="tdBorder">'
									+ temporgRefNo
									+ "R"
									+ (i + 1)
									+ '</td><td class="tdBorder">'
									+ changeDate
									+ '</td><td class="tdBorder">'
									+ rfcTrackingArray.rfcTrackingArray[i].rfcStatus
									+ '</td><td class="tdBorder">'
									+ formatCurrencyToUSFormatByValue(rfcTrackingArray.rfcTrackingArray[i].quoted)
									+ '</td><td class="tdBorder">'
									+ formatCurrencyToUSFormatByValue(rfcTrackingArray.rfcTrackingArray[i].approved)
									+ '</td><td class="tdBorder">'
									+ formatCurrencyToUSFormatByValue(rfcTrackingArray.rfcTrackingArray[i].totalCost)
									+ '</td><td class="tdBorder">'
									+ formatCurrencyToUSFormatByValue(rfcTrackingArray.rfcTrackingArray[i].grossProfit)
									+ '</td><td class="tdBorder">'
									+ rfcTrackingArray.rfcTrackingArray[i].grossMargin
											.toFixed(2) + '</td></tr>';
						}
						sOut += '</tbody></table></div>';
					} else {
						sOut = '<div class="well innerTable" id="innerDetails"><table class="table table-bordered table-condensed"><tbody><thead style="color:#0081B5"><tr><th style="text-align:center;">No history available for this Change Order.</th></tr></thead></tbody><table></div>';
					}
				},
				async : false
			});
	return sOut;
}