/**
 * 
 */
pageSetUp();

sovDetailsDataTable = null;
var laddaButton = 0;
var sovIdToDelete = 0;
var projectId=0;
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
	sovDetailsDataTable = $('#sovDetailsTable')
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
										return "<a  title='Open SOV' href=\"javascript:viewSOV('"
												+ full[0]
												+ "')\">"
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
									mData : null,
									sClass : "center",
									mRender : function(data, type, full) {
										return "<div class='pull-right action-buttons'><a title='edit' data-toggle='tooltip' class='blue'  href=\"javascript:sovUpdate('"
												+ full[0]
												+ "')\"> <i class='fa fa-pencil bigger-130'></i></a> <a class='deleteConfirmDialog' title='delete' data-toggle='modal' data-action='alertBox' href= 'javascript:void(0)' onclick=\"javascript:setSovIdToDelete('"
												+ full[0]
												+ "')\"> <i class='fa fa-trash-o bigger-130'></i></a></div>";
									}
								} ],
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
										$('#sovDetailsTable'),
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
};
function viewSOV(sid) {
	console.log("on click:"+sid);
	$("#sessionSovId").val(sid);
	$("#sovIdForSession").submit();
}

function setSovIdToDelete(sovId) {
	sovIdToDelete = sovId;
	$
			.SmartMessageBox(
					{
						title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
						content : "<br>This SOV will be "
								+ "permanently deleted . Only Admin can	recover it.<br>"
								+ "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
						buttons : '[No][Yes]'
					}, function(ButtonPressed) {
						if (ButtonPressed === "Yes") {
							sovDelete(sovId);
						}
					});

};

function sovUpdate(id) {
	$("#modalTitleId").html("Update SOV");
	$("#sovRemoteModal").modal("show");
	//alert(id);
	for (var i = 0; i < sovDetails.allSOVList.length; i++) {
		if (id == sovDetails.allSOVList[i].sovId) {
			$("#sovName").val(sovDetails.allSOVList[i].sovName);
			$("#sovId").val(id);
			$("#sovType").val(sovDetails.allSOVList[i].sovType);
			// $("#save")
			// .html("<i class='fa fa-thumbs-up bigger-110'></i> Update");
			// $("#saveandcontinue").hide();
			break;
		}
	}
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

$("#sovIdForSession").validate({

	// Do not change code below
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	},
	submitHandler : function(form) {
		// form.submit();
		setSovId(form.id);
	}
});

function setSovId(formId) {
	console.log($("#" + formId).serialize());
	$.ajax({
		url : "redirectToItemView",
		type : "POST",
		data : $("#" + formId).serialize(),
		success : function(result) {
			window.location.href = "home#sovTable";
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

// Validation Remaining

// Reset Form

// get SOV Details
function getSOVDetails() {
	//alert($("#projectId").val());
	var data = {
		"projectIdTemp" : projectId
	};
	$
			.ajax({
				url : "getAllSOVList",
				type : "POST",
				data : data,
				success : function(result) {
					sovDetails = JSON.parse(result);
					console.log(sovDetails)
					if (sovDetails.ajaxResult == "success") {
						sovDetailsDataTable.fnClearTable();
						var count = 0;
						for (var t = 0; t < sovDetails.allSOVList.length; t++) {
							if (sovDetails.allSOVList[t].status == "ACTIVE")
								sovDetailsDataTable
										.fnAddData(
												[
														sovDetails.allSOVList[t].sovId,
														sovDetails.allSOVList[t].sovName,
														sovDetails.allSOVList[t].sovType,
														sovDetails.allSOVList[t].jobDetail.jobName,

														(++count) ], false);

						}
						sovDetailsDataTable.fnDraw();
					} else {
						gritterForErrorMsgs("Some Problem Occured. Primary Reason Being :"
								+ sovDetails.reason);
					}
				}
			});
}

var stringVar = "abcde";
function displaySOVDetails() {
	$.ajax({
		url : "redirectToItemView",
		data : $("#sovIdForSession").serialize(),
		success : function(result) {
			//alert("here:" + result);
			window.location.href = "home#sovTable"
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log(xhr);
			console.log(ajaxOptions);
			console.log(thrownError);
		},
	});
}

$(document).ready(function() {
	$("#wid-id-0").hide();
	getProjectList();
	$("#reset").hide();
	barchartsForAllProjects();

});

function resetSelectedProject() {
	$("#selectProject").val("");
	$("#wid-id-0").hide();
	$("#reset").hide();
	$('#backBtnId').addClass('hide');
	$('#selectDivId').removeClass('col-md-11');
	$('#selectDivId').addClass('col-md-12');
	$("#barChartForAllProjects").html("");
	$("#summaryProject").show();
	barchartsForAllProjects();
}
function getProjectList() {
	$.ajax({
		url : "getProjectListForRfcSelectpicker",
		type : "POST",
		success : function(result) {
			initializeSelectProject(JSON.parse(result));
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

$("#selectProject").change(function() {
	projectId=$("#selectProject").val();
	//alert(projectId+"  -------");
	if($("#selectProject").val()==""){
		$("#wid-id-0").hide();
		$("#reset").hide();
		$("#barChartForAllProjects").html("");
		$("#summaryProject").show();
		barchartsForAllProjects();
	}else{
		showSelectedProject();
		getSOVDetails();
	}
	
});
function showSelectedProject(){
	$("#wid-id-0").show();
	$("#barChartForSelectedProject").html("");
	$("#reset").show();
	$("#projectId").val($("#selectProject").val());
	$("#summaryProject").hide();
	barChartsForSelectedProject();
}
function addUpdateSOV(status) {
	$("#jobIdHidden").val($("#projectId").val());
	//alert($("#jobIdHidden").val());
	console.log($("#sovDetailsForm").serialize());
	$.ajax({
		url : "addUpdateSOV",
		type : "POST",
		data : $("#sovDetailsForm").serialize(),
		success : function(result) {

			if (status == "save") {
				$("#sovRemoteModal").modal('hide');
			}
			getSOVDetails();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log(xhr);
			console.log(ajaxOptions);
			console.log(thrownError);
		},
		complete : function() {
			getSOVDetails();
			// $('#loading').modal('hide');
		}
	});
}
function barchartsForAllProjects() {
	$.ajax({
		url : "totalsForCharts",
		type : "POST",
		success : function(result) {
			rfcDataForBar = JSON.parse(result);
			for (var i = 0; i < rfcDataForBar.length; i++) {
				if (rfcDataForBar[i].price <= 0) {
					rfcDataForBar.splice(i, 1);
					i--;
				}
			}
			createBarChart(rfcDataForBar, "barChartForAllProjects");
		}
	});
}
function barChartsForSelectedProject() {
	$.ajax({
		url : "totalForChartsForSelectedProject",
		type : "POST",
		data : {selectedProjectId : $("#projectId").val()},
		success : function(result) {
			rfcDataForSelectedProject = JSON.parse(result);
			for (var i = 0; i < rfcDataForSelectedProject.length; i++) {
				if (rfcDataForSelectedProject[i].price <= 0) {
					rfcDataForSelectedProject.splice(i, 1);
					i--;
				}
			}
			createBarChart(rfcDataForSelectedProject, "barChartForSelectedProject");
		}
	});
}
function createBarChart(data1, idForBar) {
	var margin = {
		top : 40,
		right : 20,
		bottom : 30,
		left : 120
	}, width = 515 - margin.left - margin.right, height = 475 - margin.top
			- margin.bottom;

	var y = d3.scale.linear().domain([ 0, d3.max(data1, function(d) {
		return d.price;
	}) ]).range([ height, 0 ]);
	console.log(data1.map(function(d) {
		return d.planName;
	}));
	var x = d3.scale.ordinal().domain(data1.map(function(d) {
		return d.planName;
	})).rangeRoundBands([ 0, width ], .4);

	var xAxis = d3.svg.axis().scale(x).orient("bottom");

	var yAxis = d3.svg.axis().scale(y).orient("left");

	var tip = d3.tip().attr('class', 'd3-tip').offset([ -10, 0 ]).html(
			function(d) {
				return "<strong>Name:</strong> <span style='color:red'>"
						+ d.planName + "</span><br> Price: $" + d.price;
			});

	var svg = d3.select("#" + idForBar).append("svg").attr("width",
			width + margin.left + margin.right).attr("height",
			height + margin.top + margin.bottom).style("background-color",
			"#ededed").append("g").attr("transform",
			"translate(" + margin.left + "," + margin.top + ")");

	// filters go in defs element
	var defs = svg.append("defs");

	// create filter with id #drop-shadow
	// height=130% so that the shadow is not clipped
	var filter = defs.append("filter").attr("id", "drop-shadow").attr("height",
			"130%");

	// SourceAlpha refers to opacity of graphic that this filter will be applied
	// to
	// convolve that with a Gaussian with standard deviation 3 and store result
	// in blur
	filter.append("feGaussianBlur").attr("in", "SourceAlpha").attr(
			"stdDeviation", 5).attr("result", "blur");

	// translate output of Gaussian blur to the right and downwards with 2px
	// store result in offsetBlur
	filter.append("feOffset").attr("in", "blur").attr("dx", 5).attr("dy", 5)
			.attr("result", "offsetBlur");

	// overlay original SourceGraphic over translated blurred opacity by using
	// feMerge filter. Order of specifying inputs is important!
	var feMerge = filter.append("feMerge");

	feMerge.append("feMergeNode").attr("in", "offsetBlur");
	feMerge.append("feMergeNode").attr("in", "SourceGraphic");// filters go in
	// defs element
	var defs = svg.append("defs");

	// create filter with id #drop-shadow
	// height=130% so that the shadow is not clipped
	var filter = defs.append("filter").attr("id", "drop-shadow").attr("height",
			"130%");

	// SourceAlpha refers to opacity of graphic that this filter will be applied
	// to
	// convolve that with a Gaussian with standard deviation 3 and store result
	// in blur
	filter.append("feGaussianBlur").attr("in", "SourceAlpha").attr(
			"stdDeviation", 5).attr("result", "blur");

	// translate output of Gaussian blur to the right and downwards with 2px
	// store result in offsetBlur
	filter.append("feOffset").attr("in", "blur").attr("dx", 5).attr("dy", 5)
			.attr("result", "offsetBlur");

	// overlay original SourceGraphic over translated blurred opacity by using
	// feMerge filter. Order of specifying inputs is important!
	var feMerge = filter.append("feMerge");

	feMerge.append("feMergeNode").attr("in", "offsetBlur");
	feMerge.append("feMergeNode").attr("in", "SourceGraphic");

	svg.call(tip);

	svg.append("g").attr("class", "x axis").attr("transform",
			"translate(0," + height + ")").style("fill", "red").style(
			"stroke-width", "2px").call(xAxis).append("text");

	svg.append("g").attr("class", "y axis").style("fill", "red").call(yAxis)
			.append("text").attr("transform", "rotate(-90)").attr("y", 6).attr(
					"dy", ".71em").style("text-anchor", "end").text("price");

	svg.selectAll(".bar").data(data1).enter().append("rect").attr("class",
			"bar").attr("y", height).attr("fill", "steelblue").style("filter",
			"url(#drop-shadow)").transition().duration(500).delay(1000).ease(
			"quad").attr("x", function(d) {
		return x(d.planName);
	}).attr("width", x.rangeBand()).attr("y", function(d) {
		return y(d.price);
	}).attr("height", function(d) {
		return height - y(d.price);
	});

	svg.selectAll("rect").on('click', function(d) {
		alert("success");
	});
	svg.selectAll("rect").on(
			'mouseover',
			function(d) {
				tip.show(d);
				d3.select(this).transition().duration(100).ease("elastic")
						.attr("y", function(d) {
							return y(d.price) - 5;
						}).attr("height", function(d) {
							return height - y(d.price) + 5 + 'px';
						}).attr("width", x.rangeBand() + 3 + 'px');
			});

	svg.selectAll("rect").on('mouseout', function(d) {
		tip.hide(d);
		d3.select(this).transition().duration(50).attr("y", function(d) {
			return y(d.price);
		}).attr("height", function(d) {
			return height - y(d.price);
		}).attr("width", x.rangeBand());
	});

}

function sovDelete(id) {
	var data = {
		"sovId" : id
	};
	$.ajax({
		url : "deleteSOV",
		type : "POST",
		data : data,
		success : function() {
			getSOVDetails();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log(xhr);
			console.log(ajaxOptions);
			console.log(thrownError);
		}
	});
}

function initializeSelectProject(result) {
	$('#selectProject')
			.find('option')
			.remove()
			.end()
			.append(
					'<option value="">-- Default representation of projects and SOVs --</option>');
	var projectInfo = document.getElementById("selectProject");
	for (var i = 0; i < result.activeProjectList.length; i++) {
		if (result.activeProjectList[i].status == "ACTIVE") {
			var option = document.createElement("option");
			option.text = result.activeProjectList[i].jobNumber + "-"
					+ result.activeProjectList[i].jobName;
			option.value = result.activeProjectList[i].jobId;
			try {
				projectInfo.add(option, select.options[null]);
			} catch (e) {
				projectInfo.add(option, null);
			}
		}

	}
}
$("#sovTab1").click(function(e) {
	$(this).tab('show');
	showSelectedProject();
	getSOVDetails();
});
$("#sovTab2").click(function(e) {
	$(this).tab('show');
	getSOVDetails();
});

function resetSOVDetailsForm(){
	var validator = $("#sovDetailsForm").validate();
	validator.resetForm();
			$('label').removeClass("state-success");
			$('label').removeClass("state-error");
			$('input').val('');
			$("#sovType").val("");
}