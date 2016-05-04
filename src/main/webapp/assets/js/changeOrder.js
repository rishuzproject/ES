/**
 * @ Ashutosh
 */
function checkFileSize(thisObj) {
	if (thisObj.files[0].size > 15 * Math.pow(2, 20)) {
		thisObj.parentNode.nextSibling.value = "";
		$("#fileName").val("");
		gritterForErrorMsgs("The attachement size cannot exceed 15MB.");
	} else {
		thisObj.parentNode.nextSibling.value = thisObj.value;
	}
}
function downloadTakeOffSheets(id) {
	window.location.href = 'downloadTakeOffSheets?rfcTakeOffSheetNumber=' + id; // downloading
	// the
	// file
	gritterForSucessMsgs("Document has been downloaded successfully.");
}
// reset the view
function reset() {
	$("#selectProject").val("");
	$("#wid-id-0").hide();
	$("#barChartDiv").show();
	$("#pieChartDiv").show();
	planPieChart();
	$("#backBtnId").removeClass('hide');
	$("#reset").hide();
	$('#selectDivId').removeClass('col-md-11');
	$('#selectDivId').addClass('col-md-12');
}
// start of dashboard chart
$(document).ready(function() {
	// bar chart for total quoted vs total approved
	planBarChart();
	planPieChart();
});
$.validator.addMethod("validateDescription", function(value, element) {
	var myURL = value;
	var expression = /^(?=.*[a-z])/i;
	var regex = new RegExp(expression);
	return (myURL.match(regex));
}, "Please provide a description for this RFC");
$.validator.addMethod("validateReferenceNo", function(value, element) {
	var myURL = value;
	var expression = /^(?=.*[a-z])/i;
	var regex = new RegExp(expression);
	return (myURL.match(regex));
}, "Please provide a Reference Number for this RFC");

$.validator.addMethod("checkExpectedApprovalAmt", function(value, element) {
	if (parseFloat(value.replace(/,/g, "")) > parseFloat($("#extquoted").val()
			.replace(/,/g, ""))) {
		return false;
	} else {
		return true;
	}
}, "Please enter an amount less than or equal to Quoted Amount");

$.validator.addMethod("checkGreaterThanZero", function(value, element) {
	if (parseFloat(value.replace(/,/g, "")) < 0) {
		return false;
	} else {
		return true;
	}
}, "Please enter an amount greater than 0");

$("#manageChangeOrderForm")
		.validate(
				{
					// Rules for form validation
					rules : {
						rfcDesc : {
							required : true,
						},
						rfcDesc : {
							validateDescription : true,
						},
						rfcReferenceNo : {
							required : true,
						},
						rfcReferenceNo : {
							validateReferenceNo : true,
						},
						rfcNotes : {
							maxlength : 500,
						},
						quoted : {
							checkGreaterThanZero : true,
						},
						expApproval : {
							checkExpectedApprovalAmt : true,
							checkGreaterThanZero : true
						},
						factor : {
							checkGreaterThanZero : true
						},
						material : {
							checkGreaterThanZero : true
						},
						materialFactor : {
							checkGreaterThanZero : true
						},
						actualMaterialCost : {
							checkGreaterThanZero : true
						},
						subContract : {
							checkGreaterThanZero : true
						},
						subcontractorFactor : {
							checkGreaterThanZero : true
						},
						labrHr : {
							checkGreaterThanZero : true
						},
						labr : {
							checkGreaterThanZero : true
						},
						laborFactor : {
							checkGreaterThanZero : true
						},
						actualLaborCost : {
							checkGreaterThanZero : true
						},
						equip : {
							checkGreaterThanZero : true
						},
						ownedEquipment : {
							checkGreaterThanZero : true
						},
						dirJobCost : {
							checkGreaterThanZero : true
						},
						projAdmin : {
							checkGreaterThanZero : true
						},
						indirCost : {
							checkGreaterThanZero : true
						},
						contingency : {
							checkGreaterThanZero : true
						},
						exttotalCost : {
							checkGreaterThanZero : true
						},
						extgrossProfit : {
							checkGreaterThanZero : true
						},
						extgrossMargin : {
							checkGreaterThanZero : true
						},
						approved : {
							checkGreaterThanZero : true
						}
					/*
					 * , approvedDate : { checkNotBeforeCreationDate : true }
					 */
					},

					// Messages for form validation
					messages : {
						rfcDesc : {
							required : "Please enter RFC description"
						},
						rfcDesc : {
							validateDescription : "Please provide a description for this RFC",
						},
						rfcReferenceNo : {
							required : "Please enter Reference Number",
						},
						rfcReferenceNo : {
							validateReferenceNo : "Please provide a Reference Number for this RFC",
						},
						rfcNotes : {
							maxlength : "Enter RFC description in maximum of 500 characters",
						},
						quoted : {
							checkGreaterThanZero : "Please enter an amount greater than 0",
						},
						expApproval : {
							checkExpectedApprovalAmt : "Please enter an amount less than or equal to Quoted Amount",
							checkGreaterThanZero : "Please enter an amount greater than 0"
						},
						factor : {
							checkGreaterThanZero : "Please enter an amount greater than 0"
						},
						material : {
							checkGreaterThanZero : "Please enter an amount greater than 0"
						},
						materialFactor : {
							checkGreaterThanZero : "Please enter an amount greater than 0"
						},
						actualMaterialCost : {
							checkGreaterThanZero : "Please enter an amount greater than 0"
						},
						subContract : {
							checkGreaterThanZero : "Please enter an amount greater than 0"
						},
						subcontractorFactor : {
							checkGreaterThanZero : "Please enter an amount greater than 0"
						},
						labrHr : {
							checkGreaterThanZero : "Please enter an amount greater than 0"
						},
						labr : {
							checkGreaterThanZero : "Please enter an amount greater than 0"
						},
						laborFactor : {
							checkGreaterThanZero : "Please enter an amount greater than 0"
						},
						actualLaborCost : {
							checkGreaterThanZero : "Please enter an amount greater than 0"
						},
						equip : {
							checkGreaterThanZero : "Please enter an amount greater than 0"
						},
						ownedEquipment : {
							checkGreaterThanZero : "Please enter an amount greater than 0"
						},
						dirJobCost : {
							checkGreaterThanZero : "Please enter an amount greater than 0"
						},
						projAdmin : {
							checkGreaterThanZero : "Please enter an amount greater than 0"
						},
						indirCost : {
							checkGreaterThanZero : "Please enter an amount greater than 0"
						},
						contingency : {
							checkGreaterThanZero : "Please enter an amount greater than 0"
						},
						exttotalCost : {
							checkGreaterThanZero : "Please enter an amount greater than 0"
						},
						extgrossProfit : {
							checkGreaterThanZero : "Please enter an amount greater than 0"
						},
						extgrossMargin : {
							checkGreaterThanZero : "Please enter an amount greater than 0"
						},
						approved : {
							checkGreaterThanZero : "Please enter an amount greater than 0"
						}
					/*
					 * , approvedDate : { checkNotBeforeCreationDate : "Please
					 * enter an approval date on or after the RFC Creation Date" }
					 */
					},

					// Do not change code below
					errorPlacement : function(error, element) {
						error.insertAfter(element.parent());
					},
					submitHandler : function(form) {
						// form.submit();
						if ($("#submitType").val() == "saveBtn") {
							saveUpdateRfc('manageChangeOrderForm', 'nextBtn');
						} else {
							saveUpdateRfc('manageChangeOrderForm', 'updateBtn');
						}
					}
				});

$("#selectProject").change(function() {
	$("#barChartDiv").hide();
	$("#pieChartDiv").hide();
	$("#wid-id-0").show();
	$("#reset").show();
	// $("#summaryProject").show();
});
function createCharts() {
	$("#barChartForProject").empty();
	$("#pieChartForProject").empty();
	createPieChartForProject($("#selectProject").val());
	createBarChartForProject($("#selectProject").val());
}
function loadRfcTakeOffSheetsData() {
	var data={"jobId":$("#jobIdHidden").val()};
	$.ajax({
		url : "getAllTakeOffSheets",
		type : "POST",
		dataType : 'text',
		data:data,
		success : function(temp) { // to be changed
			var temp0 = JSON.parse(temp);
			console.log(temp0);
			initializeRfcTakeOffSheetTable(temp0.rfcTakeOffSheetsList,
					temp0.rfcSheets);
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log("Error in the function");
		}
	});
}
function createPieChartForProject(jobId) {
	var type = "total";
	rfcDataForPie = "rfcDataForPie";
	$.ajax({
		url : "totalCalculationsForProject",
		type : "POST",
		data : {
			rfcDataForPie : rfcDataForPie,
			chartTypeForProject : type,
			jobId : jobId
		},
		success : function(result) {
			rfcDataForPie = JSON.parse(result);
			for (var i = 0; i < rfcDataForPie.length; i++) {
				if (rfcDataForPie[i].price <= 0) {
					rfcDataForPie.splice(i, 1);
					i--;
				}
			}
			createPieChart(rfcDataForPie, "pieChartForProject");
		},
	});
}
function createBarChartForProject(jobId) {
	rfcDataForBar = "rfcDataForBar";
	var type = "QA";
	$.ajax({
		url : "totalCalculationsForProject",
		type : "POST",
		data : {
			rfcDataForBar : rfcDataForBar,
			chartTypeForProject : type,
			jobId : jobId
		},
		success : function(result) {
			rfcDataForBar = JSON.parse(result);
			for (var i = 0; i < rfcDataForBar.length; i++) {
				if (rfcDataForBar[i].price <= 0) {
					rfcDataForBar.splice(i, 1);
					i--;
				}
			}
			createBarChart(rfcDataForBar, "barChartForProject");
		}
	});
}
function planPieChart() {
	rfcDataForPie = "rfcDataForPie";
	var type = "total";
	$
			.ajax({
				url : "totalCalculations",
				type : "POST",
				data : {
					rfcDataForPie : rfcDataForPie,
					chartType : type,
				},
				success : function(result) {
					rfcDataForPie = JSON.parse(result);
					for (var i = 0; i < rfcDataForPie.length; i++) {
						if (rfcDataForPie[i].price <= 0) {
							rfcDataForPie.splice(i, 1);
							i--;
						}
					}
					createPieChart(rfcDataForPie, "pieChart");
				},
				error : function() {
					console
							.log("Some error occured while creating pie chart based on all the change order data");
				}
			});
}
function planBarChart() {
	rfcDataForBar = "rfcDataForBar";
	var type = "QA";
	$
			.ajax({
				url : "totalCalculations",
				type : "POST",
				data : {
					rfcDataForBar : rfcDataForBar,
					chartType : type,
				},
				success : function(result) {
					rfcDataForBar = JSON.parse(result);
					for (var i = 0; i < rfcDataForBar.length; i++) {
						if (rfcDataForBar[i].price <= 0) {
							rfcDataForBar.splice(i, 1);
							i--;
						}
					}
					createBarChart(rfcDataForBar, "barChart");
				},
				error : function() {
					console
							.log("Some error occured while creating bar chart based on all the change order data");
				}
			});
}
function createPieChart(data, idForPie) {
	$("#pieChart").empty();
	var w = 493, // width
	h = 475, // height
	r = 120, // radius
	color = d3.scale.category10(); // builtin range of colors

	var vis = d3.select("#" + idForPie).append("svg:svg").data([ data ]).attr(
			"width", w).attr("height", h).append("svg:g").attr("transform",
			"translate(" + (r + 150) + "," + (r + 110) + ")")
	var outerArc = d3.svg.arc().innerRadius(0).outerRadius(r * 2.5);
	var arc = d3.svg.arc().innerRadius(0).outerRadius(r);

	var pie = d3.layout.pie().value(function(d) {
		return d.price;
	});

	var pos = d3.svg.arc().innerRadius(r + 2).outerRadius(r + 2);

	var getAngle = function(d) {
		return (180 / Math.PI * (d.startAngle + d.endAngle) / 2 - 90);
	};
	var tip = d3.tip().attr('class', 'd3-tip').offset([ -30, 0 ]).html(
			function(d) {
				return "<strong>Name:</strong> <span style='color:red'>"
						+ d.data.planName + "</span><br> Price: $"
						+ d.data.price;
			})

	vis.call(tip);

	var arcs = vis
			.selectAll("g.slice")
			.data(pie)
			.enter()
			.append("svg:g")
			.attr("class", "slice")
			.on(
					'mouseover',
					function(d, i) {
						d3
								.select(this)
								.transition()
								.duration(500)
								.ease('elastic')
								.attr(
										'transform',
										function(d) {
											var dist = 10;
											d.midAngle = ((d.endAngle - d.startAngle) / 2)
													+ d.startAngle;
											var x = Math.sin(d.midAngle) * dist;
											var y = -Math.cos(d.midAngle)
													* dist;
											return 'translate(' + x + ',' + y
													+ ')';
										});
						tip.show(d);
					}).on(
					'mouseout',
					function(d) {
						tip.hide();
						d3.select(this).transition().duration(500).ease(
								'bounce').attr('transform', 'translate(0,0)');
						d3.select("#tooltip").style("opacity", 0);
					});

	arcs.append("svg:path").attr("fill", function(d, i) {
		return color(i);
	}).transition().delay(function(d, i) {
		return i * 500;
	}).duration(2000).attrTween('d', function(d) {
		var i = d3.interpolate(d.startAngle + 0.1, d.endAngle);
		return function(call) {
			d.endAngle = i(call);
			return arc(d);
		}
	});
	arcs.append("svg:text").attr("transform", function(d) {
		var varbl1 = outerArc.centroid(d);
		// varbl1[0] = r * 0.95 * (midAngle(d) < Math.PI ? 1 : -1);
		return "translate(" + varbl1 + ") ";
	}).attr("dy", 5).style("text-anchor", function(d) {
		return midAngle(d) < Math.PI ? "start" : "end";
	}).style("fill", "red").style("font-weight", "bold").text(function(d) {
		return d.data.planName;
	});

	function midAngle(d) {
		return d.startAngle + (d.endAngle - d.startAngle) / 2;
	}

	arcs.append("svg:polyline").attr("points", function(d) {
		var varbl = arc.centroid(d);
		var varbl1 = outerArc.centroid(d);
		// varbl1[0] = r * 0.95 * (midAngle(d) < Math.PI ? 1 : -1);
		var arr = [ arc.centroid(d), outerArc.centroid(d), varbl1 ];
		return arr;
	})
}
function createBarChart(data1, idForBar) {
	var margin = {
		top : 40,
		right : 20,
		bottom : 30,
		left : 120
	}, width = 493 - margin.left - margin.right, height = 475 - margin.top
			- margin.bottom;

	var y = d3.scale.linear().domain([ 0, d3.max(data1, function(d) {
		return d.price;
	}) ]).range([ height, 0 ]);
	// console.log(data1.map(function(d) {
	// return d.planName;
	// }));
	var x = d3.scale.ordinal().domain(data1.map(function(d) {
		return d.planName;
	})).rangeRoundBands([ 0, width ], .4);

	var xAxis = d3.svg.axis().scale(x).orient("bottom");

	var yAxis = d3.svg.axis().scale(y).orient("left");
	var tip = d3.tip().attr('class', 'd3-tip').offset([ -10, 0 ]).html(
			function(d) {
				return "<strong>Name:</strong> <span style='color:red'>"
						+ d.planName + "</span><br> Price: $" + d.price;
			})

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

	feMerge.append("feMergeNode").attr("in", "offsetBlur")
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

	feMerge.append("feMergeNode").attr("in", "offsetBlur")
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

	svg.selectAll("rect").on(
			'mouseover',
			function(d) {
				tip.show(d);
				d3.select(this).transition().duration(100).ease("elastic")
						.attr("y", function(d) {
							return y(d.price) - 5;
						}).attr("height", function(d) {
							return height - y(d.price) + 5 + 'px';
						}).attr("width", x.rangeBand() + 3 + 'px')
			});

	svg.selectAll("rect").on('mouseout', function(d) {
		tip.hide(d);
		d3.select(this).transition().duration(50).attr("y", function(d) {
			return y(d.price);
		}).attr("height", function(d) {
			return height - y(d.price);
		}).attr("width", x.rangeBand())
	});
}
// end of dashboard chart

function setRfcOrigDate() {
	var date = new Date();
	var month = [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
			"Sep", "Oct", "Nov", "Dec" ];
	document.getElementById("extorigDate").value = date.getDate() + '-'
			+ month[(date.getMonth())] + '-' + date.getFullYear();
}
function addOptionsToStatus(edited) {
	setRfcOrigDate();
	var newOptions = "";
	if (edited != null) {
		newOptions = {
			'Pending' : 'Pending'
		};
	} else {
		newOptions = {
			'Pending' : 'Pending',
			'Denied' : 'Denied',
			'Price & Proceed' : 'Price & Proceed',
			'Approved' : 'Approved'
		};
	}
	$("#extstatus").empty();
	$.each(newOptions, function(text, key) {
		var option = new Option(key, text);
		$("#extstatus").append($(option));
	});
}
function initializeSelectProjectForRfc(result) {
	console.log(result)
	$('#selectProject')
			.find('option')
			.remove()
			.end()
			.append(
					'<option value="">-- Select a Project to view its related Change Orders List --</option>');
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
var rfcData = "";
function getRfcListByprojectId(jobId) {
	$("#jobIdHidden").val(jobId);
	if (jobId != "") {
		$
				.ajax({
					url : "getRfcForSelectedJob",
					type : "POST",
					data : "jobId=" + jobId,
					async : false,
					success : function(result) {
						rfcData = JSON.parse(result);
						if (rfcData.rfcLogList.length != 0) {
							$("#headingAddUpdate")
									.html(
											"<i class='fa fa-stack-exchange txt-color-blue'>&nbsp;</i> Add New Change Order for "
													+ rfcData.rfcLogList[0].jobDetail.jobName
													+ "-"
													+ rfcData.rfcLogList[0].jobDetail.jobNumber);
						} else {
							$("#headingAddUpdate").html("Add New Change Order");
						}
						addExternalTableData(rfcData);
						initializeRfcMappingSelectBox(rfcData);
						addDeletedTableData(rfcData);
						createCharts();
					},
					error : function(xhr, ajaxOptions, thrownError) {
						console
								.log("inside error method getRfcListByprojectId(jobId) ")
					},
					complete : function() {
						console
								.log("after completion getRfcListByprojectId(jobId)");
					}
				});
	}
}

/* start of DATE validation code */
var nowTemp = new Date();
var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(),
		nowTemp.getDate(), 0, 0, 0, 0);

var checkin = $('#extorigDate').datepicker({
	format : "dd-mm-yyyy",
	onRender : function(date) {
		return date.valueOf() < now.valueOf() ? 'disabled' : '';
	}
}).on('changeDate', function(ev) {
	if (ev.date.valueOf() > checkout.date.valueOf()) {
		var newDate = new Date(ev.date)
		newDate.setDate(newDate.getDate() + 1);
		checkout.setValue(newDate);
	}
	checkin.hide();
}).data('datepicker');
var checkout = $('#extapprovedDate').datepicker({
	format : "dd-mm-yyyy",
	onRender : function(date) {
		return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
	}
}).on('changeDate', function(ev) {
	checkout.hide();
}).data('datepicker');
/* ends of DATE validation code */

var originationDate = new Date();
// $("#extapprovedDate").datepicker({
// format : "mm-dd-yyyy"
// }).on('changeDate', function(ev) {
// $('#extapprovedDate').datepicker('hide');
// });
// $("#extapprovedDate").datepicker("option","minDate",12-30-2014);
$("#selectProject").change(function() {
	$("#justMessage").addClass("hide");
	$("#wid-id-0").removeClass("hide");
});
$('.contBack').click(function() {
	var nextId = $(this).parents('.tab-pane').prev().attr("id");
	$('[href=#' + nextId + ']').tab('show');
});

function valueSelect(type) {

	// debugger;
	if ($("#extRfcType").val() == "Internal"
			&& document.getElementById("extstatus").value == 'Approved') {
		return true;
	}
	if (type == "External") {
		if (document.getElementById("extstatus").value == 'Approved'
				&& (document.getElementById("extapproved").value == "" || document
						.getElementById("extapproved").value == 0.00)) {
			gritterForAlertMsgs("Status is Approved, Please enter value for Approved Amount.")
			removeComma();
			// $("#approvalLi").removeClass("hide");
			// $("#approvalLi").addClass("active");
			// $("#step3").addClass("active");
			// $("#revenueLi").removeClass("active");
			// // $("#revenueLi").addClass("hide");
			// $("#step2").removeClass("active");
			// $("#step1").removeClass("active");
			// $("#rfcInfoLi").removeClass("active");
			document.getElementById('extapproved').focus();
			return false;
		} else if (document.getElementById("extstatus").value == 'Approved'
				&& document.getElementById("extcustRefNo").value == "") {
			gritterForAlertMsgs(
					"Status is Approved, Please enter Customer Reference Number.",
					function() {
						removeComma();
						// $("#approvalLi").removeClass("hide");
						// $("#approvalLi").addClass("active");
						// $("#step3").addClass("active");
						// $("#revenueLi").removeClass("active");
						// $("#revenueLi").addClass("hide");
						// $("#step2").removeClass("active");
						document.getElementById('extcustRefNo').focus();
					});
			return false;
		} else {
			removeComma();
			ajaxCallToSaveUpdateRfc('manageChangeOrderForm');
		}
		// Putting a condition to check if Approved amount is entered and status
		// is not approved. @Ankur

		if ((document.getElementById("extapproved").value != ""
				&& document.getElementById("extapproved").value != 0 && document
				.getElementById("extapproved").value != 0.00)
				&& document.getElementById("extstatus").value != 'Approved') {
			var msg = "You have entered value for Approved Amount, however the status is not Approved. Do you still want to proceed ?";
			$
					.SmartMessageBox(
							{
								title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
								content : msg,
								buttons : '[No][Yes]'
							},
							function(ButtonPressed) {
								if (ButtonPressed === "Yes") {
									ajaxCallToSaveUpdateRfc('manageChangeOrderForm');
								}
							});
			return false;
		}
	}
	return true;
}
function saveUpdateRfc(formId, buttonId) {
	if (document.getElementById("extsNo").value == "" && buttonId == "nextBtn") {
		ajaxCallToSaveUpdateRfc(formId);
	} else if (document.getElementById("extsNo").value != ""
			&& buttonId == "updateBtn") {
		 
		// ajaxCallToSaveUpdateRfc(formId);
		valueSelect('External');
		loadRfcTakeOffSheetsData();
	}
}
function removeCommasForForm() {
	$("input[type='text']").each(function() {
		$(this).val($(this).val().replace(/[,]/g, ""));
	});
	return true;
}
function removeComma() {
	document.getElementById("extquoted").value = document
			.getElementById("extquoted").value.replace(/,/g, "");
	document.getElementById("extapproved").value = document
			.getElementById("extapproved").value.replace(/,/g, "");
	document.getElementById("extexpApproval").value = document
			.getElementById("extexpApproval").value.replace(/,/g, "");
	document.getElementById("extmaterial").value = document
			.getElementById("extmaterial").value.replace(/,/g, "");
	document.getElementById("extsubContract").value = document
			.getElementById("extsubContract").value.replace(/,/g, "");
	document.getElementById("extdirJobCost").value = document
			.getElementById("extdirJobCost").value.replace(/,/g, "");
	document.getElementById("extprojAdmin").value = document
			.getElementById("extprojAdmin").value.replace(/,/g, "");
	document.getElementById("extequip").value = document
			.getElementById("extequip").value.replace(/,/g, "");
	document.getElementById("extOwnEquip").value = document
			.getElementById("extOwnEquip").value.replace(/,/g, "");
	document.getElementById("extlabrHr").value = document
			.getElementById("extlabrHr").value.replace(/,/g, "");
	document.getElementById("extlabr").value = document
			.getElementById("extlabr").value.replace(/,/g, "");
	document.getElementById("extindirCost").value = document
			.getElementById("extindirCost").value.replace(/,/g, "");
	document.getElementById("extcontingency").value = document
			.getElementById("extcontingency").value.replace(/,/g, "");
}
var dateInUIFormat = "";
var separatedDates = "";
function ajaxCallToSaveUpdateRfc(formId) {
	// //format for origination date changed due to different formats of date
	// $("#extapprovedDate").val($("#originationDate").val());
	// alert("hehrerere:"+$("#extapprovedDate").val());
	// format for origination date changed due to different formats of date
	dateInUIFormat = $("#extorigDate").val();
	// if ($("#submitType").val() == "saveBtn") { // if action is save, change
	// // format like this
	// separatedDates = dateInUIFormat.split("-");
	// $("#extorigDate").val(
	// separatedDates[0] + "-" + separatedDates[1] + "-"
	// + separatedDates[2]);
	// } else {
	// separatedDates = dateInUIFormat.split(" "); // if action is update,
	// // change format like this
	// separatedDates[1] = separatedDates[1].slice(0, -1);
	// $("#extorigDate").val(
	// separatedDates[1] + "-" + separatedDates[0] + "-"
	// + separatedDates[2]);
	// }
	$('#loadingDiv').addClass('active');
	removeCommasForForm();
	var result = "";
	$
			.ajax({
				url : 'addRfcUsingAjax',
				data : new FormData($('#manageChangeOrderForm')[0]),
				cache : false,
				contentType : false,
				processData : false,
				type : 'POST',
				success : function(data) {
					result = JSON.parse(data);
					getRfcListByprojectId(result.savedRfcId.jobDetail.jobId);
					$('#loadingDiv').removeClass('active');
					toggleExternalRfc(result.savedRfcId.sNo);
					document.getElementById("myFile").value = "";
					gritterForSucessMsgs("Operation Successful.");
				},
				error : function(xhr, ajaxOptions, thrownError) {
					console.log("inside error addRfcUsingAjax() ");
					$('#loadingDiv').removeClass('active');
					gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");

				},
				complete : function() {
					// $('#loading').modal('hide');
					console.log("after completion addRfcUsingAjax()");
				}
			});
}

// Global Rfc Selected Value, based on edit button clicked
var rfcSelectedValue = null;
function toggleExternalRfc(clickedId) {
	rfcSelectedValue = clickedId;
	var origDate = "";
	var lastUpDate = "";
	var totalCost = "";
	var grossProfit = "";
	var grossMargin = "";
	var calBasedOn = "";
	var projectStatus = "";
	for (var i = 0; i < rfcData.rfcLogList.length; i++) {
		if (rfcData.rfcLogList[i].sNo == clickedId) {
			// Used to render handsontable
			// getRfcSubCostValues(clickedId);
			origDate = rfcData.rfcLogList[i].origDate == undefined ? ""
					: rfcData.rfcLogList[i].origDate;
			// lastUpDate = rfcData.rfcLogList[i].lastStatUpDate;
			totalCost = rfcData.rfcLogList[i].totalCost;
			grossProfit = rfcData.rfcLogList[i].grossProfit;
			grossMargin = rfcData.rfcLogList[i].grossMargin;
			calBasedOn = rfcData.rfcLogList[i].calBasedOn;
			projectStatus = rfcData.rfcLogList[i].rfcStatus;
			// showHideStatusBasedButton(projectStatus);
			showHideRequestForApprovalButton(
					rfcData.rfcLogList[i].rfcUserResponseStatus, projectStatus);
			$('#headingAddUpdate').html(
					'Update Change Order for '
							+ rfcData.rfcLogList[i].jobDetail.jobName + "-"
							+ rfcData.rfcLogList[i].jobDetail.jobNumber);
			$("#rfcStatusHidden").val(rfcData.rfcLogList[i].rfcStatus)
			$("#rfcUserResponseStatus").val(
					rfcData.rfcLogList[i].rfcUserResponseStatus);
			// $("#extsNo").val(rfcData.rfcLogList[i].sNo);
		}
	}
	addOptionsToStatus();
	$("#externalRfcModal").modal("show");
	doGetRfcInfo(clickedId, origDate, lastUpDate, totalCost, grossProfit,
			grossMargin, calBasedOn, projectStatus);
}
function doGetRfcInfo(sNo, origDate, lastUpDate, totalCost, grossProfit,
		grossMargin, calBasedOn, projectStatus) {
	for (var i = 0; i < rfcData.rfcLogList.length; i++) {
		if (rfcData.rfcLogList[i].sNo == sNo) {
			if (rfcData.rfcLogList[i].rfcType.toUpperCase() == "External"
					.toUpperCase()) {
				document.getElementById("extsNo").value = convertor(rfcData.rfcLogList[i].sNo);
				try {
					if (rfcData.rfcLogList[i].rfcMappingDB != undefined
							&& rfcData.rfcLogList[i].rfcMappingDB.replace("[",
									"").replace("]", "").length != 0) {
						document.getElementById("rfcMappingTxtDiv").style.display = "block";
						document.getElementById("rfcMappingTxt").value = rfcData.rfcLogList[i].rfcMappingDB
								.replace("[", "").replace("]", "");
					}
				} catch (e) {
					console.log(e);
				}
				document.getElementById("extprojAdmin").value = formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].projAdmin));
				document.getElementById("extrfcDesc").value = rfcData.rfcLogList[i].rfcDesc;
				document.getElementById("extequip").value = formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].equip));
				document.getElementById("extOwnEquip").value = formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].ownedEquipment));
				document.getElementById("extcustRefNo").value = rfcData.rfcLogList[i].custRefNo;
				document.getElementById("extapprovedDate").value = rfcData.rfcLogList[i].approvedDate == undefined ? ""
						: rfcData.rfcLogList[i].approvedDate;
				document.getElementById("extcustRefNoDesc").value = rfcData.rfcLogList[i].custRefNoDesc == undefined ? ""
						: rfcData.rfcLogList[i].custRefNoDesc;
				document.getElementById("extOrgRefNo").value = rfcData.rfcLogList[i].originationReferenceNumber == undefined ? ""
						: rfcData.rfcLogList[i].originationReferenceNumber;

				document.getElementById("extorigDate").value = origDate;
				document.getElementById("extlabrHr").value = formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].labrHr));
				document.getElementById("extexpApproval").value = formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].expApproval));
				document.getElementById("extquoted").value = formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].quoted));
				document.getElementById("extmaterial").value = formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].material));
				document.getElementById("extsubContract").value = formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].subContract));
				document.getElementById("extSubcontractCostFactor").value = formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].subcontractorFactor));
				if (rfcData.rfcLogList[i].approved != 0.00) {
					document.getElementById("extapproved").value = formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].approved));
				} else {
					document.getElementById("extapproved").value = "";
				}
				document.getElementById("extlabr").value = formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].labr));
				document.getElementById("extindirCost").value = formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].indirCost));
				document.getElementById("extcontingency").value = formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].contingency));
				document.getElementById("extfactor").value = rfcData.rfcLogList[i].factor;
				// document.getElementById("extlastStatUpDate").value =
				// lastUpDate == undefined ? ""
				// : lastUpDate;
				document.getElementById("extdirJobCost").value = formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].dirJobCost));
				// Notes For An rfc
				document.getElementById("extrfcNotes").value = rfcData.rfcLogList[i].rfcNotes == undefined ? ""
						: rfcData.rfcLogList[i].rfcNotes;

				/*
				 * alert(rfcData.rfcLogList[i].status);
				 * setSelectedValue(document.getElementById("extstatus"),
				 * rfcData.rfcLogList[i].status);
				 */
				// @Ankur for Rfc Mapping
				/*
				 * var rfcMapping=""; try { rfcMapping =
				 * rfcData.rfcLogList[i].rfcMappingDB.split("[")[1]
				 * .split("]")[0].split(",");
				 * console.log(rfcData.rfcLogList[i].rfcMappingDB);
				 * console.log(rfcMapping); } catch (e) { try { rfcMapping =
				 * rfcData.rfcLogList[i].rfcMappingDB; console.log("in catch
				 * try"+rfcMapping); } catch (e) { } }
				 */
				// $('#extrfcMapping').selectpicker('val', rfcMapping);
				if (rfcData.rfcLogList[i].status == "") {
					$("#extstatus").val("Pending");
				} else {
					$('#extstatus').val(rfcData.rfcLogList[i].rfcStatus);
				}
				$('#extRfcType').val(rfcData.rfcLogList[i].rfcType);
				document.getElementById("exttotalCost").value = formatCurrencyToUSFormatByValue(totalCost);
				document.getElementById("extgrossMargin").value = convertor2Int(
						grossMargin).toFixed(2);
				document.getElementById("extgrossProfit").value = formatCurrencyToUSFormatByValue(grossProfit);
				document.getElementById("extApprovalfactor").value = convertor(rfcData.rfcLogList[i].materialFactor);
				document.getElementById("extLaborfactor").value = convertor(rfcData.rfcLogList[i].laborFactor);
				calculateMaster("external");
			}
			// else if (rfcData.rfcLogList[i].rfcType.toUpperCase() ==
			// "Internal"
			// .toUpperCase()) {
			// document.getElementById("intsNo").value =
			// convertor(rfcData.rfcLogList[i].sNo);
			// document.getElementById("intprojAdmin").value =
			// formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].projAdmin));
			// document.getElementById("intrfcDesc").value =
			// rfcData.rfcLogList[i].rfcDesc;
			// document.getElementById("intequip").value =
			// formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].equip));
			// document.getElementById("intOwnEquip").value =
			// formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].ownedEquipment));
			// document.getElementById("intcustRefNo").value =
			// rfcData.rfcLogList[i].custRefNo;
			// // to be changed later
			// document.getElementById("extOrgRefNo").value =
			// rfcData.rfcLogList[i].orgRefNo == undefined ? ""
			// : rfcData.rfcLogList[i].orgRefNo;
			// document.getElementById("intorigDate").value = origDate;
			// document.getElementById("intlabrHr").value =
			// formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].labrHr));
			// document.getElementById("intexpApproval").value =
			// formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].expApproval));
			// document.getElementById("intquoted").value =
			// formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].quoted));
			// document.getElementById("intcontingency").value =
			// formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].contingency));
			// document.getElementById("intmaterial").value =
			// formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].material));
			// document.getElementById("intsubContract").value =
			// formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].subContract));
			// document.getElementById("intSubcontractorCostFactor").value =
			// formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].subcontractCostFactor));
			// document.getElementById("intapproved").value =
			// formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].approved));
			// document.getElementById("intlabr").value =
			// formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].labr));
			// document.getElementById("intindirCost").value =
			// formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].indirCost));
			// // document.getElementById("intlastStatUpDate").value =
			// // lastUpDate == undefined ? ""
			// // : lastUpDate;
			// document.getElementById("intdirJobCost").value =
			// formatCurrencyToUSFormatByValue(convertor(rfcData.rfcLogList[i].dirJobCost));
			// // Notes For An rfc
			// document.getElementById("intrfcNotes").value =
			// rfcData.rfcLogList[i].rfcNotes;
			//
			// document.getElementById("intSubmitButton").innerHTML = "Update";
			// if (rfcData.rfcLogList[i].status == "Denied"
			// || projectStatus == "CLOSED") {
			// document.getElementById("intSubmitButton").disabled = true;
			// } else {
			// document.getElementById("intSubmitButton").disabled = false;
			// }
			// /*
			// * setSelectedValue(document.getElementById("intstatus"),
			// * rfcData.rfcLogList[i].status);
			// */
			// document.getElementById("intfactor").value =
			// rfcData.rfcLogList[i].factor;
			// document.getElementById("inttotalCost").value =
			// formatCurrencyToUSFormatByValue(convertor2IntWithoutCommas(
			// totalCost).toFixed(2));
			// document.getElementById("intgrossMargin").value =
			// convertor2IntWithoutCommas(
			// grossMargin).toFixed(2);
			// document.getElementById("intgrossProfit").value =
			// formatCurrencyToUSFormatByValue(convertor2IntWithoutCommas(
			// grossProfit).toFixed(2));
			// document.getElementById("intMaterialfactor").value =
			// convertor(rfcData.rfcLogList[i].materialFactor);
			// document.getElementById("intLaborfactor").value =
			// convertor(rfcData.rfcLogList[i].laborFactor);
			// $('#intstatus').selectpicker('val',
			// rfcData.rfcLogList[i].status);
			// $('#intstatus').selectpicker('refresh');
			// $('#intRfcType').selectpicker('val',
			// rfcData.rfcLogList[i].rfcType);
			// $('#intRfcType').selectpicker('refresh');
			// calculateMaster("internal");
			// }
			break;
		}
	}
}
function rfcMappingCal(value, type) {
	var extMap = document.getElementById("extrfcMapping");
	document.getElementById("extlabrHr").value = "";
	document.getElementById("extprojAdmin").value = "";
	document.getElementById("extexpApproval").value = "";
	document.getElementById("extquoted").value = "";
	document.getElementById("extmaterial").value = "";
	document.getElementById("extsubContract").value = "";
	document.getElementById("extapproved").value = "";
	document.getElementById("extlabr").value = "";
	document.getElementById("extindirCost").value = "";
	document.getElementById("extcontingency").value = "";
	document.getElementById("extdirJobCost").value = "";
	document.getElementById("extequip").value = "";
	document.getElementById("extOwnEquip").value = "";
	if (document.getElementById("extsNo").value != "") {
		rfcMappingCalHelper(document.getElementById("extsNo").value, type);
	}
	for (var x = 0; x < extMap.length; x++) {
		if (extMap[x].selected) {
			rfcMappingCalHelper(extMap[x].value, type);
		}
	}
}
function rfcMappingCalHelper(valueToCompare, type) {
	for (var i = 0; i < rfcData.rfcLogList.length; i++) {
		if (rfcData.rfcLogList[i].sNo == valueToCompare) {
			document.getElementById("extlabrHr").value = convertor(convertor2Int(document
					.getElementById("extlabrHr").value)
					+ convertor2Int(rfcData.rfcLogList[i].labrHr));
			document.getElementById("extexpApproval").value = convertor(convertor2Int(document
					.getElementById("extexpApproval").value)
					+ convertor2Int(rfcData.rfcLogList[i].expApproval));
			document.getElementById("extquoted").value = convertor(convertor2Int(document
					.getElementById("extquoted").value)
					+ convertor2Int(rfcData.rfcLogList[i].quoted));
			document.getElementById("extmaterial").value = convertor(convertor2Int(document
					.getElementById("extmaterial").value)
					+ convertor2Int(rfcData.rfcLogList[i].material));
			document.getElementById("extsubContract").value = convertor(convertor2Int(document
					.getElementById("extsubContract").value)
					+ convertor2Int(rfcData.rfcLogList[i].subContract));
			document.getElementById("extapproved").value = convertor(convertor2Int(document
					.getElementById("extapproved").value)
					+ convertor2Int(rfcData.rfcLogList[i].approved));
			document.getElementById("extlabr").value = convertor(convertor2Int(document
					.getElementById("extlabr").value)
					+ convertor2Int(rfcData.rfcLogList[i].labr));
			document.getElementById("extindirCost").value = convertor(convertor2Int(document
					.getElementById("extindirCost").value)
					+ convertor2Int(rfcData.rfcLogList[i].indirCost));
			document.getElementById("extcontingency").value = convertor(convertor2Int(document
					.getElementById("extcontingency").value)
					+ convertor2Int(rfcData.rfcLogList[i].contingency));
			document.getElementById("extprojAdmin").value = convertor(convertor2Int(document
					.getElementById("extprojAdmin").value)
					+ convertor2Int(rfcData.rfcLogList[i].projAdmin));
			document.getElementById("extdirJobCost").value = convertor(convertor2Int(document
					.getElementById("extdirJobCost").value)
					+ convertor2Int(rfcData.rfcLogList[i].dirJobCost));
		}
	}
}
// New function added by Vaibhav for number formatting on keyup.
function formatCurrencyToUSFormat(id, value) {
	$("#" + id).val(
			value.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g,
					","));
}
function formatCurrencyToUSFormatByValue(value) {
	value = convertor2Int(value).toFixed(2);
	return value.toString().replace(/,/g, "").replace(/\B(?=(\d{3})+(?!\d))/g,
			",");
}
function convertor2IntWithoutCommas(value) {
	var v;
	try {
		value = value.replace(/,/g, "");
		v = value == "" ? 0 : value;
	} catch (e) {
		console.log(e);
	}
	return +v;
}
function setRevenueValuesForInternal() {
	if ($("#extRfcType").val() == "Internal") {
		document.getElementById("extexpApproval").value = 0.00;
		document.getElementById("extquoted").value = 0.00;
		document.getElementById("extapproved").value = 0.00;
	}
}
function convertor2Int(value) {
	var v = value == "" ? 0 : value;
	return +v;
}
function convertor(value) {
	return (value == 0 || value == null) ? "" : value;
}
function calculateMaster(type) {
	if (document.getElementById("extstatus").value == 'Approved') {
		$("#extapproved").removeAttr('readonly');
		$("#approvalLi").removeClass("hide");
		$("#step3").removeClass("hide");
	} else {
		// console.log("else approved");
		$("#extapproved").attr('readonly', true);
		// $("#approvalLi").addClass("hide");
		// $("#step3").addClass("hide");
	}
	calculateLabourMaterialSubcontractorCost(type);
	var totCost = calculateTotalCost(type);
	var calOn = calculateCalculationBasedOn(type);
	var grossProfit = calculateGrossProfit(type, calOn, totCost);
	calculateGrossMargin(type, calOn, grossProfit);
}
function calculateGrossMargin(type, calBasedOn, grossProfit) {
	if (convertor2Int(calBasedOn) != 0) {
		return (convertor2Int(grossProfit) / convertor2Int(calBasedOn) * 100)
				.toFixed(2);
	} else {
		return "";
	}
}
function calculateCalculationBasedOn(type) {
	var calBased;
	if (type == "external") {
		var factor = document.getElementById("extfactor").value;
		if (factor == "" || factor == undefined) {
			factor = 100;
		}
		calBased = convertor2IntWithoutCommas(document
				.getElementById("extquoted").value)
				* factor / 100;
	}
	// else if (type == "internal") {
	// var factor = document.getElementById("intfactor").value;
	// if (factor == "" || factor == undefined) {
	// factor = 100;
	// }
	// calBased = convertor2IntWithoutCommas(document
	// .getElementById("intquoted").value)
	// * factor / 100;
	// }
	return calBased;
}
function calculateGrossProfit(type, calOn, totCost) {
	var grossProfit;

	if (type == "external") {
		grossProfit = (convertor2Int(calOn) - convertor2Int(totCost))
				.toFixed(2);
		formatCurrencyToUSFormat('extgrossProfit', grossProfit);
	}
	// else if (type == "internal") {
	// grossProfit = (convertor2Int(calOn) - convertor2Int(totCost))
	// .toFixed(2);
	// formatCurrencyToUSFormat('intgrossProfit', grossProfit);
	// }
	return grossProfit;
}
function calculateTotalCost(type) {
	var totalCost = 0;
	if (type == "external") {
		totalCost = (convertor2IntWithoutCommas(document
				.getElementById("extActualMaterialCost").value)
				+ convertor2IntWithoutCommas(document
						.getElementById("extActualSubcontractCost").value)
				+ convertor2IntWithoutCommas(document
						.getElementById("extdirJobCost").value)
				+ convertor2IntWithoutCommas(document
						.getElementById("extActualLaborCost").value)
				+ convertor2IntWithoutCommas(document
						.getElementById("extindirCost").value)
				+ convertor2IntWithoutCommas(document
						.getElementById("extcontingency").value)
				+ convertor2IntWithoutCommas(document
						.getElementById("extprojAdmin").value)
				+ convertor2IntWithoutCommas(document
						.getElementById("extequip").value) + convertor2IntWithoutCommas(document
				.getElementById("extOwnEquip").value));
		formatCurrencyToUSFormat('exttotalCost', totalCost.toFixed(2));
	}
	// Calculation for internal costs commented out. Uncomment if needed in
	// future.
	// else if (type == "internal") {
	// totalCost = (convertor2IntWithoutCommas(document
	// .getElementById("intActualMaterialCost").value)
	// + convertor2IntWithoutCommas(document
	// .getElementById("intActualSubcontractCost").value)
	// + convertor2IntWithoutCommas(document
	// .getElementById("intdirJobCost").value)
	// + convertor2IntWithoutCommas(document
	// .getElementById("intActualLaborCost").value)
	// + convertor2IntWithoutCommas(document
	// .getElementById("intindirCost").value)
	// + convertor2IntWithoutCommas(document
	// .getElementById("intcontingency").value)
	// + convertor2IntWithoutCommas(document
	// .getElementById("intprojAdmin").value)
	// + convertor2IntWithoutCommas(document
	// .getElementById("intequip").value) + convertor2IntWithoutCommas(document
	// .getElementById("intOwnEquip").value));
	// formatCurrencyToUSFormat('inttotalCost', totalCost.toFixed(2));
	// }
	return totalCost;
}
function calculateLabourMaterialSubcontractorCost(type) {
	var tmpVal;
	if (type == "external") {
		// Calculation For Material Starts
		var factor = document.getElementById("extApprovalfactor").value;
		if (factor == "" || factor == undefined) {
			factor = 100;
		}
		tmpVal = (convertor2IntWithoutCommas(document
				.getElementById("extmaterial").value)
				* factor / 100);
		formatCurrencyToUSFormat('extActualMaterialCost', tmpVal);
		// Calculation For Material Ends
		// Calculation For Labor Starts
		factor = document.getElementById("extLaborfactor").value;
		if (factor == "" || factor == undefined) {
			factor = 100;
		}
		tmpVal = (convertor2IntWithoutCommas(document.getElementById("extlabr").value)
				* factor / 100);
		formatCurrencyToUSFormat('extActualLaborCost', tmpVal);
		// Calculation For Labor Ends
		// Calculation For Subcontractor Starts
		factor = document.getElementById("extSubcontractCostFactor").value;
		if (factor == "" || factor == undefined) {
			factor = 100;
		}
		tmpVal = (convertor2IntWithoutCommas(document
				.getElementById("extsubContract").value)
				* factor / 100);
		formatCurrencyToUSFormat('extActualSubcontractCost', tmpVal);
		// Calculation For Subcontractor Ends
	}
	// Calculation for internal costs commented out. Uncomment if needed in
	// future.
	// else if (type == "internal") {
	// var factor = document.getElementById("intMaterialfactor").value;
	// if (factor == "" || factor == undefined) {
	// factor = 100;
	// }
	// tmpVal = (convertor2IntWithoutCommas(document
	// .getElementById("intmaterial").value)
	// * factor / 100);
	// document.getElementById("intActualMaterialCost").value = tmpVal;
	//
	// factor = document.getElementById("intLaborfactor").value;
	// if (factor == "" || factor == undefined) {
	// factor = 100;
	// }
	// tmpVal =
	// (convertor2IntWithoutCommas(document.getElementById("intlabr").value)
	// * factor / 100);
	// document.getElementById("intActualLaborCost").value = tmpVal;
	// // Calculation For Subcontractor Starts
	// factor = document.getElementById("intSubcontractorCostFactor").value;
	// if (factor == "" || factor == undefined) {
	// factor = 100;
	// }
	// tmpVal = (convertor2IntWithoutCommas(document
	// .getElementById("intsubContract").value)
	// * factor / 100);
	// formatCurrencyToUSFormat('intActualSubcontractCost', tmpVal);
	// // Calculation For Subcontractor Ends
	// }
}

function setDeleted(sNo) {
	// Getting jobId
	var jobIdHidden = $("#jobIdHidden").val();
	$
			.SmartMessageBox(
					{
						title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
						content : "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete this Change Order ?</b>",
						buttons : '[No][Yes]'
					},
					function(ButtonPressed) {
						if (ButtonPressed === "Yes") {
							$
									.ajax({
										url : "DeleteRfcBasedOnRfcId",
										type : "POST",
										data : "sNo=" + sNo,
										success : function(result) {
											if (JSON.parse(result).result == "success") {
												getRfcListByprojectId(jobIdHidden);
												gritterForSucessMsgs("Change Order has been deleted successfully.You can view deleted change order under <b>Deleted</b> tab");
											} else {
												gritterForErrorMsgs("Some Error Occured While Deleting this Change Order. Please refresh the page and try again.If this Problem Persists, please report it to dev team.");
											}
										},
										error : function(xhr, ajaxOptions,
												thrownError) {
											gritterForErrorMsgs("Some Error Occured While Deleting this Change Order. Please refresh the page and try again.If this Problem Persists, please report it to dev team.");
										},
										complete : function() {
										}
									});
						}
					});
}
function restoreDeletedRfc(sNo) {
	var jobIdHidden = $("#jobIdHidden").val();
	$
			.SmartMessageBox(
					{
						title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
						content : "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to restore this Change Order ?</b>",
						buttons : '[No][Yes]'
					},
					function(ButtonPressed) {
						if (ButtonPressed === "Yes") {
							$
									.ajax({
										url : "RestoreDeletedRfc",
										type : "POST",
										data : "sNo=" + sNo,
										success : function(result) {
											getRfcListByprojectId(jobIdHidden);
											if (JSON.parse(result).result == "success") {
												getRfcListByprojectId(jobIdHidden);
												gritterForSucessMsgs("Change Order has been restored successfully.");
											} else {
												gritterForErrorMsgs("Some Error Occured While Restoring this Change Order. Please refresh the page and try again.If this Problem Persists, please report it to dev team.");
											}
										},
										error : function(xhr, ajaxOptions,
												thrownError) {
											gritterForErrorMsgs("Some Error Occured While Restoring this Change Order. Please refresh the page and try again.If this Problem Persists, please report it to dev team.");
										},
										complete : function() {
										}
									});
						}
					});
	// bootbox.confirm("Are you sure you want to restore this RFC ?", function(
	// result) {
	// if (result == true) {
	//			
	// }
	// });
}
function setRfcUserResponseStatusValue(status) {
	var sendForCustomerApprovalMsg = "Are you sure you want to Send For Customer Approval? "
			+ "</br> Change Order history will be maintained for every update in this Change Order.";
	var approvedMsg = "Are you sure you want to Approve this Change Order?  </br> Once Approved no changes can be made, unless Re-Opened by admin.";
	var rejectMsg = "Are you sure you want to Reject this Change Order? </br> Once Rejected , it will again seek for Customer Approval.";
	var resendForCustomerApprovalMsg = "Are you sure you want to Resend this Change Order For Customer Approval? "
			+ "</br> Change Order history will be maintained.";

	//bootboxConfirmMsg = "";
	if (status == "Send For Customer Approval") {
		// valueSelect('External');
		bootboxConfirmMsg = sendForCustomerApprovalMsg;
	} else if (status == "Approved") {
		if($('#extapproved').val() == 0 && $('#extcustRefNo').val() == 0){
			gritterForErrorMsgs("c# and Approved amount fields are mandatory.");
			$('#revenueLi , #step2').removeClass('active');
			$('#approvalLi , #step3').addClass('active');
			return;
		}
		else{
			bootboxConfirmMsg = approvedMsg;			
		}
	} else if (status == "Rejected") {
		bootboxConfirmMsg = rejectMsg;
	} else if (status == "Resend For Customer Approval") {
		bootboxConfirmMsg = resendForCustomerApprovalMsg;
	}

	$
			.SmartMessageBox(
					{
						title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
						content : bootboxConfirmMsg,
						buttons : '[No][Yes]'
					},
					function(ButtonPressed) {
						if (ButtonPressed === "Yes") {
							$("#rfcUserResponseStatus").val(status);
							// In Case of Send For Customer Approval status
							// setting status as
							// pending & Approved in case of Approved Button Is
							// clicked.
							if (status == "Send For Customer Approval"
									|| status == "Resend For Customer Approval") {
								$("#extstatus").val("Pending");
							} else if (status == "Approved") {
								$("#extstatus").val("Approved");
							}
							ajaxCallToSaveUpdateRfc('manageChangeOrderForm');
						}
					});

	// bootbox.confirm(bootboxConfirmMsg, function(result) {
	// if (result == true) {
	// $("#rfcUserResponseStatus").val(status);
	// // In Case of Send For Customer Approval status setting status as
	// // pending & Approved in case of Approved Button Is clicked.
	// if (status == "Send For Customer Approval") {
	// $("#extstatus").val("Pending");
	// } else if (status == "Approved") {
	// $("#extstatus").val("Approved");
	// }
	//
	// $("#extRfcFormId").submit();
	// }
	// });
}
function setRfcReOpen() {
	var reOpenMsg = "Are you sure you want to Re-Open this Change Order? </br>"
			+ "Re-Opening the Change Order Will change the status from Approved to Pending </br> Change Order Customer Approval Status will be set to Sent for Customer Approval.";
	$
			.SmartMessageBox(
					{
						title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
						content : reOpenMsg,
						buttons : '[No][Yes]'
					},
					function(ButtonPressed) {
						if (ButtonPressed === "Yes") {
							$
									.ajax({
										url : "ReOpenApprovedRfc.do",
										type : "POST",
										data : "rfcSNO=" + rfcSelectedValue,
										success : function(result) {
											if (result.result == "success") {
												showHideRequestForApprovalButton(
														"Send For Customer Approval",
														"Pending");
												// showHideStatusBasedButton("Pending");
												$("#extstatus").val('Pending');
												gritterForSucessMsgs("Change Order is Reopened");

											} else {
												gritterForErrorMsgs("Some Error Occured While Opening this Change Order. Please refresh the page and try again.If this Problem Persists, please report it to dev team.");
											}
										},
										error : function(xhr, ajaxOptions,
												thrownError) {
											console
													.log("inside error setRfcReOpen() method");
											gritterForErrorMsgs("Some Error Occured While Opening this Change Order. Please refresh the page and try again.If this Problem Persists, please report it to dev team.");
										},
										complete : function() {
											console
													.log("after completion setRfcReOpen() method");
										}
									});
						}
					});
}
function btnNextTab() {
	/*
	 * if($(".addingId").is("#nextBtn")){
	 * $tabs.filter('.active').next('li').find('a').tab('show'); }else{ };
	 * 
	 * if ($(".addingId").not(".class4js") && $(".addingId").not(".class4js")) {
	 * $("#nextBtn").prop('disabled', true); $("#prevBtn").prop('disabled',
	 * false); }
	 */
}
function btnPrevTab() {
	/*
	 * if ($(".addingId").is(".class4js") && $(".addingId").not(".class4js")) {
	 * $("#prevBtn").prop('disabled', true); $("#nextBtn").prop('disabled',
	 * false); }
	 */

}
function showHideRequestForApprovalButton(rfcUserResponseStatus, rfcStatus) {
	try {
		// Hiding/ Showing requestCustomerApproval Based buttons,If
		// status is null
		// then Showing requestCustomerApproval Button and hiding
		// Approve/Reject
		// Button. If Status is requestCustomerApproval then showing
		// Approve/Reject
		// Button.
		$("#saveBtn").hide();
		// var $tabs = $('.tabbable ul li');
		// $(".addingId").attr('id', 'nextBtn');
		// $tabs.filter('.active').next('li').find('a').tab('show');
		console.log("show hide button tab");
		console.log(rfcUserResponseStatus);
		console.log(rfcStatus);
		if ((rfcUserResponseStatus == "Send For Customer Approval" || rfcUserResponseStatus == "Resend For Customer Approval")
				&& !(rfcStatus == "Approved" || rfcStatus == "Denied")) {
			$("#customerApproval").addClass('hide');
			$("#approveBtn").removeClass('hide');
			$("#rejectBtn").removeClass('hide');
			$("#resendCustomerApproval").removeClass('hide');
			$("#reopenBtn").addClass('hide');
			$("#updateBtn").removeClass('hide');
			// Tab visibility
			$("#revenueLi").addClass("active");
			$("#revenueLi").removeClass("hide");
			$("#step2").addClass("active");
			$("#approvalLi").removeClass("hide");
			$("#step3").removeClass("active");
			$("#rfcInfoLi").removeClass("active");
			$("#step1").removeClass("active");
		} else if (rfcUserResponseStatus == "Approved"
				&& (rfcStatus == "Approved")) {
			$("#customerApproval").addClass('hide');
			$("#approveBtn").addClass('hide');
			$("#rejectBtn").addClass('hide');
			$("#reopenBtn").removeClass('hide');
			$("#resendCustomerApproval").addClass('hide');
			$("#updateBtn").addClass('hide');
			// Tab visibility
			$("#revenueLi").removeClass("hide");
			$("#revenueLi").addClass("active");
			$("#step2").addClass("active");
			$("#approvalLi").removeClass("hide");
			$("#step3").removeClass("hide");
			$("#rfcInfoLi").removeClass("active");
			$("#step1").removeClass("active");
		} else if ((rfcUserResponseStatus == undefined || rfcUserResponseStatus == "")
				&& rfcStatus == "Pending") {
			$("#customerApproval").removeClass('hide');
			$("#approveBtn").addClass('hide');
			$("#rejectBtn").addClass('hide');
			$("#reopenBtn").addClass('hide');
			$("#resendCustomerApproval").addClass('hide');
			$("#updateBtn").removeClass('hide');
			// $("#saveBtn").hide();
			// Tab visibility
			$("#revenueLi").removeClass("hide");
			$("#step1").removeClass("active");
			$("#rfcInfoLi").removeClass("active");
			$("#revenueLi").addClass("active");
			$("#step2").addClass("active");
		} else if ((rfcUserResponseStatus == undefined || rfcUserResponseStatus == "")
				&& rfcStatus == "Approved") {
			$("#customerApproval").addClass('hide');
			$("#approveBtn").addClass('hide');
			$("#rejectBtn").addClass('hide');
			$("#reopenBtn").removeClass('hide');
			$("#resendCustomerApproval").addClass('hide');
			$("#updateBtn").addClass('hide');
			$("#nextBtn").addClass("btn4hide");
			$("#rfcInfoLi").removeClass("active");
			$("#revenueLi").removeClass("hide");
			$("#step1").removeClass("active");
			$("#approvalLi").removeClass("hide");
			$("#approvalLi").addClass("active");
			$("#step3").addClass("active");
		} else if (rfcUserResponseStatus == "Rejected"
				&& rfcStatus == "Pending") {
			$("#customerApproval").removeClass('hide');
			$("#approveBtn").addClass('hide');
			$("#rejectBtn").addClass('hide');
			$("#reopenBtn").addClass('hide');
			$("#resendCustomerApproval").addClass('hide');
			$("#updateBtn").removeClass('hide');
			$("#nextBtn").addClass("class4js");
			$("#nextBtn").prop('disabled', true);
			// Tab visibility
			$("#approvalLi").addClass('hide');
			$("#revenueLi").addClass("active");
			$("#revenueLi").removeClass("hide");
			$("#step2").addClass("active");
			$("#step1").removeClass("active");
			$("#rfcInfoLi").removeClass("active");
			// $("#step1").addClass("active");
		}
	} catch (e) {
		console.log(e);
	}

}

function gritterForAlertMsgs(msg) {
	$.smallBox({
		title : "<b>Ecosystem: Alert !!</b>",
		content : msg,
		color : "#5384AF",
		icon : "fa fa-bell bounce animated custSize",
		timeout : 5000,
		iconSmall : "fa fa-times fadeInRight animated custPosit"
	});
}

/**
 * Harsh Verma
 */

/**
 * Rfc Material Budget Calculation starts here
 */
function fetchMatBudgetFormData() {
	console.log("inside fetchMatBudgetFormData()");
	var sNo = $("#extsNo").val();
	console.log(sNo);
	$
			.ajax({
				url : "GetMatBudgetInfo",
				type : "POST",
				data : "rfcLogId=" + sNo,
				success : function(result) {
					var response = JSON.parse(result);
					initializeMaterialDataTable(response);
				},
				error : function(xhr, ajaxOptions, thrownError) {
					console.log("inside error fetchMatBudgetFormData() ajax");
					$('#loadingDiv').removeClass('active');
					gritterForErrorMsgs("Some Problem Occured. Please refresh the page and try again. If this problem persists, please contact Dev Team.");

				}
			});
}

function initializeMaterialDataTable(response) {
	projMatHeaderTable = $('#matHeaderTable').dataTable({
		"bFilter" : false,
		"bInfo" : false,
		"bPaginate" : false,
		"bDestroy" : true,
		"aoColumns" : [ {
			sClass : "center editable editableCellStyle",
			mRender : function(data, type, full) {
				return full[0];
			}
		}, {
			sClass : "center editable editableCellStyle",
			mRender : function(data, type, full) {
				return full[1];
			}
		} ],
		"fnDrawCallback" : function() {
			makeMatBudgetTableEditable();
		}
	});
	projMatListTable = $('#materialCostTable').dataTable({
		"bDestroy" : true,
		"aoColumns" : [ {
			sClass : "center ",
			mRender : function(data, type, full) {
				return full[0];
			}
		}, {
			sClass : "center",
			mRender : function(data, type, full) {
				return full[1];
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
		}, {
			sClass : "center",
			mRender : function(data, type, full) {
				return full[4];
			}
		}, {
			sClass : "center editable editableCellStyle",
			mRender : function(data, type, full) {
				return full[5];
			}
		}, {
			sClass : "center editable editableCellStyle",
			mRender : function(data, type, full) {
				return full[5];
			}
		} ],
		"fnDrawCallback" : function() {
			makeMatBudgetTableEditable();
		}
	});
	projMatListTable.fnClearTable();
	projMatHeaderTable.fnClearTable();
	var salesTax = response.MaterialBudget[0].salesTax;
	var matTotal = 0;
	for (var i = 0; i < response.MaterialBudget.length; i++) {
		var sumMatQuot = convertor2Int(response.MaterialBudget[i].material)
				+ convertor2Int(response.MaterialBudget[i].quotedItems);
		var calculatedVal = sumMatQuot + ((sumMatQuot * salesTax) / 100);
		projMatListTable.fnAddData([ response.MaterialBudget[i].rowId, "MAT",
				response.MaterialBudget[i].activityDescription,
				response.MaterialBudget[i].activityNumber, calculatedVal,
				response.MaterialBudget[i].material,
				response.MaterialBudget[i].quotedItems ], false);
		matTotal += convertor2Int(calculatedVal);
	}

	$("#matTotal").text(convertor2Int(matTotal));
	$("#extmaterial").val(convertor2Int(matTotal));

	projMatHeaderTable.fnAddData(
			[ response.MaterialBudget[0].miscMat, salesTax ], false);
	projMatHeaderTable.fnDraw();
	projMatListTable.fnDraw();
}

function makeMatBudgetTableEditable() {
	$('.editable')
			.editable(
					function(sVal) {
						return sVal;
					},
					{
						type : 'text',
						placeholder : '',
						"callback" : function(sValue, y) {
							var nodes = projMatHeaderTable.fnGetNodes();
							var matData = projMatListTable.fnGetNodes();

							var miscTax = $(nodes[0]).find("td:eq(0)").html();
							var salesTax = $(nodes[0]).find("td:eq(1)").html();
							var matTotal = 0;
							var finalValInclsTax = 0;
							var material = 0;
							var quoted = 0;
							var sum = 0;
							for (var i = 0; i < matData.length; i++) {
								material = $(matData[i]).find("td:eq(5)")
										.html();
								quoted = $(matData[i]).find("td:eq(6)").html();

								if (!isNaN(miscTax) && !isNaN(salesTax)
										&& !isNaN(quoted) && !isNaN(material)) {
									sum = convertor2Int(material)
											+ convertor2Int(quoted);
									finalValInclsTax = convertor2Int(sum)
											+ ((convertor2Int(sum) * convertor2Int(salesTax)) / 100);
									$(matData[i]).find("td:eq(5)").html(
											material);
									$(matData[i]).find("td:eq(6)").html(quoted);
									$(matData[i]).find("td:eq(4)").html(
											finalValInclsTax);
									matTotal += convertor2Int(finalValInclsTax);
								}
							}
							$("#matTotal").text(convertor2Int(matTotal));
							$("#extmaterial").val(convertor2Int(matTotal));
						},
						"tooltip" : 'Click to enter / change.',
						onblur : "submit",
						onsubmit : function(settings, td) {
							var input = $(td).find('input');
							var original = input.val();
						}
					});
}

function saveMaterialBudgetForm() {
	var matHeaderTable = $("#matHeaderTable").dataTable().fnGetNodes();
	var matDataTableList = $("#materialCostTable").dataTable().fnGetNodes();
	var materialRfcBudgetTableData = null;
	var arrayItemTable = new Array();

	for (var i = 0; i < matDataTableList.length; i++) {
		materialRfcBudgetTableData = {
			"sno" : $(matDataTableList[i]).find("td:eq(0)").html(),
			"CostType" : $(matDataTableList[i]).find("td:eq(1)").html(),
			"Description" : $(matDataTableList[i]).find("td:eq(2)").html(),
			"Activity" : $(matDataTableList[i]).find("td:eq(3)").html(),
			"Material" : $(matDataTableList[i]).find("td:eq(5)").html(),
			"Quoted" : $(matDataTableList[i]).find("td:eq(6)").html(),
			"MiscTax" : $(matHeaderTable[0]).find("td:eq(0)").html(),
			"SalesTax" : $(matHeaderTable[0]).find("td:eq(1)").html(),
			"RfcLogID" : $("#extsNo").val()
		};
		arrayItemTable.push(materialRfcBudgetTableData);
	}

	$.ajax({
		url : "AddUpdateMaterialBudgetForm",
		type : "POST",
		contentType : 'application/json',
		data : JSON.stringify(arrayItemTable),
		success : function(result) {

		}
	});
}
/**
 * Rfc Material Budget Calculation End here
 */

/**
 * Rfc Sub-Contractor Budget Calculation End here
 */
function fetchSubContractBudgetFormData() {
	var sNo = $("#extsNo").val();
	$.ajax({
		url : "GetSubContractorBudgetInfo",
		type : "POST",
		data : "rfcLogId=" + sNo,
		success : function(result) {
			var response = JSON.parse(result);
			initializeSubContrDataTable(response);
		}
	});
}

function initializeSubContrDataTable(response) {

	subcontractTable = $('#subcontractTable').dataTable({
		"bDestroy" : true,
		"aoColumns" : [ {
			sClass : "center ",
			mRender : function(data, type, full) {
				return full[0];
			}
		}, {
			sClass : "center",
			mRender : function(data, type, full) {
				return full[1];
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
		}, {
			sClass : "center",
			mRender : function(data, type, full) {
				return full[4];
			}
		}, {
			sClass : "center editable editableCellStyle",
			mRender : function(data, type, full) {
				return full[5];
			}
		} ],
		"fnDrawCallback" : function() {
			makeSubContBudgetTableEditable();
		}
	});

	subcontractTable.fnClearTable();
	var subValInclsTotal = 0;
	for (var i = 0; i < response.SubContractorBudget.length; i++) {
		subcontractTable.fnAddData([ response.SubContractorBudget[i].rowId,
				"SUB", response.SubContractorBudget[i].activityDescription,
				response.SubContractorBudget[i].activityNumber,
				response.SubContractorBudget[i].quotedItems,
				response.SubContractorBudget[i].quotedItems ], false);
		subValInclsTotal += convertor2Int(response.SubContractorBudget[i].quotedItems);
	}
	$("#subTotal").text(convertor2Int(subValInclsTotal));
	$("#extsubContract").val(convertor2Int(subValInclsTotal));
	subcontractTable.fnDraw();

}

function makeSubContBudgetTableEditable() {
	$('.editable').editable(function(sVal) {
		return sVal;
	}, {
		type : 'text',
		placeholder : '',
		"callback" : function(sValue, y) {
			var nodes = subcontractTable.fnGetNodes();
			var subValInclsTotal = 0;
			var valIncls = 0;
			var quoted = 0;
			for (var i = 0; i < nodes.length; i++) {
				quoted = $(nodes[i]).find("td:eq(5)").html();
				if (!isNaN(quoted)) {
					$(nodes[i]).find("td:eq(5)").html(quoted);
					$(nodes[i]).find("td:eq(4)").html(quoted);
					subValInclsTotal += convertor2Int(quoted);
				}
			}
			$("#subTotal").text(convertor2Int(subValInclsTotal));
			$("#extsubContract").val(convertor2Int(subValInclsTotal));
		},
		"tooltip" : 'Click to enter / change.',
		onblur : "submit",
		onsubmit : function(settings, td) {
			var input = $(td).find('input');
			var original = input.val();
		}
	});
}

function saveSubContrctBudgetForm() {
	var subcontractTable = $("#subcontractTable").dataTable().fnGetNodes();
	var subcontractRfcBudgetTableData = null;
	var arrayItemTable = new Array();

	for (var i = 0; i < subcontractTable.length; i++) {

		subcontractRfcBudgetTableData = {
			"sno" : $(subcontractTable[i]).find("td:eq(0)").html(),
			"CostType" : $(subcontractTable[i]).find("td:eq(1)").html(),
			"Description" : $(subcontractTable[i]).find("td:eq(2)").html(),
			"Activity" : $(subcontractTable[i]).find("td:eq(3)").html(),
			"Quoted" : $(subcontractTable[i]).find("td:eq(5)").html(),
			"RfcLogID" : $("#extsNo").val()
		};
		arrayItemTable.push(subcontractRfcBudgetTableData);
	}

	$.ajax({
		url : "AddUpdateSubContrctBudgetForm",
		type : "POST",
		contentType : 'application/json',
		data : JSON.stringify(arrayItemTable),
		success : function(result) {

		}
	});
}
/**
 * Rfc Sub-Contractor Budget Calculation End here
 */

/**
 * Rfc Labor Budget Calculation Starts here
 */
function fetchLaborBudgetFormData() {
	var sNo = $("#extsNo").val();
	$.ajax({
		url : "GetLaborBudgetInfo",
		type : "POST",
		data : "rfcLogId=" + sNo,
		success : function(result) {
			var response = JSON.parse(result);
			initializeLaborDataTable(response);
		}
	});
}

function initializeLaborDataTable(response) {
	laborHeaderCostTable = $('#laborHeaderCostTable').dataTable({
		"bFilter" : false,
		"bInfo" : false,
		"bPaginate" : false,
		"bDestroy" : true,
		"aoColumns" : [ {
			sClass : "center editable editableCellStyle",
			mRender : function(data, type, full) {
				return full[0];
			}
		}, {
			sClass : "center editable editableCellStyle",
			mRender : function(data, type, full) {
				return full[1];
			}
		}, {
			sClass : "center editable editableCellStyle",
			mRender : function(data, type, full) {
				return full[2];
			}
		}, {
			sClass : "center editable editableCellStyle",
			mRender : function(data, type, full) {
				return full[3];
			}
		}, {
			sClass : "center editable editableCellStyle",
			mRender : function(data, type, full) {
				return full[4];
			}
		} ],
		"fnDrawCallback" : function() {
			makeLaborBudgetTableEditable();
		}
	});

	laborCostTableList = $('#laborCostTableList').dataTable({
		"bDestroy" : true,
		"aoColumns" : [ {
			sClass : "center ",
			mRender : function(data, type, full) {
				return full[0];
			}
		}, {
			sClass : "center ",
			mRender : function(data, type, full) {
				return full[1];
			}
		}, {
			sClass : "center ",
			mRender : function(data, type, full) {
				return full[2];
			}
		}, {
			sClass : "center ",
			mRender : function(data, type, full) {
				return full[3];
			}
		}, {
			sClass : "center ",
			mRender : function(data, type, full) {
				return full[4];
			}
		}, {
			sClass : "center editable editableCellStyle",
			mRender : function(data, type, full) {
				return full[5];
			}
		}, {
			sClass : "center ",
			mRender : function(data, type, full) {
				return full[6];
			}
		}, {
			sClass : "center ",
			mRender : function(data, type, full) {
				return full[7];
			}
		} ],
		"fnDrawCallback" : function() {
			makeLaborBudgetTableEditable();
		}
	});

	laborHeaderCostTable.fnClearTable();
	laborCostTableList.fnClearTable();

	var laborTotal = 0;
	var burden = response.LaborBudget[0].burden;
	var fringes = response.LaborBudget[0].fringes;
	var foremanRate
	if (response.LaborBudget[0].foremanRate != undefined) {
		foremanRate = response.LaborBudget[0].foremanRate;
	} else {
		foremanRate = 0;
	}

	laborHeaderCostTable.fnAddData([ response.LaborBudget[0].labWBdn,
			response.LaborBudget[0].labWoBdn, burden, fringes, foremanRate ],
			false);

	for (var i = 0; i < response.LaborBudget.length; i++) {
		var hrs = response.LaborBudget[i].hours;
		var totalLabCost = hrs * foremanRate;
		var burdenCost = hrs * burden;
		var fringesCost = hrs * fringes;
		laborCostTableList.fnAddData([ response.LaborBudget[i].rowId, "LABOR",
				response.LaborBudget[i].activityDescription,
				response.LaborBudget[i].activityNumber, totalLabCost, hrs,
				burdenCost, fringesCost ], false);
		laborTotal += convertor2Int(totalLabCost);
	}

	$("#laborTotal").text(convertor2Int(laborTotal));
	$("#extlabrHr").val(convertor2Int(laborTotal));
	laborHeaderCostTable.fnDraw();
	laborCostTableList.fnDraw();

}

function makeLaborBudgetTableEditable() {
	$('.editable')
			.editable(
					function(sVal) {
						return sVal;
					},
					{
						type : 'text',
						placeholder : '',
						"callback" : function(sValue, y) {
							var headerNodes = laborHeaderCostTable.fnGetNodes();
							var nodes = laborCostTableList.fnGetNodes();

							var labWBdn = $(headerNodes[0]).find("td:eq(0)")
									.html();
							var labWoBdn = $(headerNodes[0]).find("td:eq(1)")
									.html();
							var burden = $(headerNodes[0]).find("td:eq(2)")
									.html();
							var fringes = $(headerNodes[0]).find("td:eq(3)")
									.html();
							var foremanRate = $(headerNodes[0])
									.find("td:eq(4)").html();

							var hrs = 0;
							var burdenCosts = 0;
							var fringesCosts = 0;
							var totalLaborCosts = 0;

							var totalCost = 0;
							for (var i = 0; i < nodes.length; i++) {
								hrs = $(nodes[i]).find("td:eq(5)").html();

								if (!isNaN(hrs)) {
									$(nodes[i]).find("td:eq(5)").html(hrs);
									burdenCosts = $(nodes[i]).find("td:eq(5)")
											.html()
											* burden;
									fringesCosts = $(nodes[i]).find("td:eq(5)")
											.html()
											* fringes;
									totalLaborCosts = $(nodes[i]).find(
											"td:eq(5)").html()
											* foremanRate;

									$(nodes[i]).find("td:eq(4)").html(
											totalLaborCosts);
									$(nodes[i]).find("td:eq(6)").html(
											burdenCosts);
									$(nodes[i]).find("td:eq(7)").html(
											fringesCosts);

									totalCost += convertor2Int(totalLaborCosts);
								}
							}
							$("#laborTotal").text(convertor2Int(totalCost));
							$("#extlabrHr").val(convertor2Int(totalCost));
						},
						"tooltip" : 'Click to enter / change.',
						onblur : "submit",
						onsubmit : function(settings, td) {
							var input = $(td).find('input');
							var original = input.val();
						}
					});
}

function saveLaborBudgetForm() {
	var laborHeaderTable = $("#laborHeaderCostTable").dataTable().fnGetNodes();
	var laborDataTableList = $("#laborCostTableList").dataTable().fnGetNodes();
	var laborRfcBudgetTableData = null;
	var arrayItemTable = new Array();

	for (var i = 0; i < laborDataTableList.length; i++) {

		laborRfcBudgetTableData = {
			"sno" : $(laborDataTableList[i]).find("td:eq(0)").html(),
			"CostType" : $(laborDataTableList[i]).find("td:eq(1)").html(),
			"Description" : $(laborDataTableList[i]).find("td:eq(2)").html(),
			"Activity" : $(laborDataTableList[i]).find("td:eq(3)").html(),
			"HOURS" : $(laborDataTableList[i]).find("td:eq(5)").html(),
			"RfcLogID" : $("#extsNo").val(),
			"LAB_W_BDN" : $(laborHeaderTable[0]).find("td:eq(0)").html(),
			"LAB_WO_BDN" : $(laborHeaderTable[0]).find("td:eq(1)").html(),
			"BURDEN" : $(laborHeaderTable[0]).find("td:eq(2)").html(),
			"FRINGES" : $(laborHeaderTable[0]).find("td:eq(3)").html(),
			"FOREMAN_RATE" : $(laborHeaderTable[0]).find("td:eq(4)").html(),
		};
		arrayItemTable.push(laborRfcBudgetTableData);
	}

	$.ajax({
		url : "AddUpdateLaborBudgetForm",
		type : "POST",
		contentType : 'application/json',
		data : JSON.stringify(arrayItemTable),
		success : function(result) {

		}
	});
}

/**
 * Rfc Labor Budget Calculation End here
 */

/**
 * Rfc Equipment Budget Calculation Starts here
 */
function fetchEquipBudgetFormData() {
	var sNo = $("#extsNo").val();
	$.ajax({
		url : "GetEquipBudgetInfo",
		type : "POST",
		data : "rfcLogId=" + sNo,
		success : function(result) {
			var response = JSON.parse(result);
			initializeEquipDataTable(response);
		}
	});
}

function initializeEquipDataTable(response) {

	equipBudgetTable = $('#equipmentTable').dataTable({
		"bDestroy" : true,
		"aoColumns" : [ {
			sClass : "center ",
			mRender : function(data, type, full) {
				return full[0];
			}
		}, {
			sClass : "center",
			mRender : function(data, type, full) {
				return full[1];
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
		}, {
			sClass : "center",
			mRender : function(data, type, full) {
				return full[4];
			}
		}, {
			sClass : "center editable editableCellStyle",
			mRender : function(data, type, full) {
				return full[5];
			}
		} ],
		"fnDrawCallback" : function() {
			makeEquipBudgetTableEditable();
		}
	});

	equipBudgetTable.fnClearTable();
	var equipValInclsTotal = 0;

	for (var i = 0; i < response.EquipBudget.length; i++) {
		equipBudgetTable.fnAddData([ response.EquipBudget[i].rowId,
				"RENT EQUIP", response.EquipBudget[i].activityDescription,
				response.EquipBudget[i].activityNumber,
				response.EquipBudget[i].cost, response.EquipBudget[i].cost ],
				false);
		equipValInclsTotal += convertor2Int(response.EquipBudget[i].cost);
	}
	$("#equipTotal").text(convertor2Int(equipValInclsTotal));
	$("#extequip").val(convertor2Int(equipValInclsTotal));
	equipBudgetTable.fnDraw();

}

function makeEquipBudgetTableEditable() {
	$('.editable').editable(function(sVal) {
		return sVal;
	}, {
		type : 'text',
		placeholder : '',
		"callback" : function(sValue, y) {
			var nodes = equipBudgetTable.fnGetNodes();
			var equipValInclsTotal = 0;
			var valIncls = 0;
			var cost = 0;
			for (var i = 0; i < nodes.length; i++) {
				cost = $(nodes[i]).find("td:eq(5)").html();
				if (!isNaN(cost)) {
					$(nodes[i]).find("td:eq(5)").html(cost);
					$(nodes[i]).find("td:eq(4)").html(cost);
					equipValInclsTotal += convertor2Int(cost);
				}
			}
			$("#equipTotal").text(convertor2Int(equipValInclsTotal));
			$("#extequip").val(convertor2Int(equipValInclsTotal));
		},
		"tooltip" : 'Click to enter / change.',
		onblur : "submit",
		onsubmit : function(settings, td) {
			var input = $(td).find('input');
			var original = input.val();
		}
	});
}

function saveEquipBudgetForm() {
	var equipTable = $("#equipmentTable").dataTable().fnGetNodes();
	var equipRfcBudgetTableData = null;
	var arrayItemTable = new Array();

	for (var i = 0; i < equipTable.length; i++) {

		equipRfcBudgetTableData = {
			"sno" : $(equipTable[i]).find("td:eq(0)").html(),
			"CostType" : $(equipTable[i]).find("td:eq(1)").html(),
			"Description" : $(equipTable[i]).find("td:eq(2)").html(),
			"Activity" : $(equipTable[i]).find("td:eq(3)").html(),
			"Cost" : $(equipTable[i]).find("td:eq(5)").html(),
			"RfcLogID" : $("#extsNo").val()
		};
		arrayItemTable.push(equipRfcBudgetTableData);
	}

	$.ajax({
		url : "AddUpdateEquipBudgetForm",
		type : "POST",
		contentType : 'application/json',
		data : JSON.stringify(arrayItemTable),
		success : function(result) {

		}
	});
}
/**
 * Rfc Labor Budget Calculation End here
 */

/**
 * Rfc Direct Job Budget Calculation Starts here
 */
function fetchDirectBudgetFormData() {
	var sNo = $("#extsNo").val();
	$.ajax({
		url : "GetDirectJobBudgetInfo",
		type : "POST",
		data : "rfcLogId=" + sNo,
		success : function(result) {
			var response = JSON.parse(result);
			initializeDirJobDataTable(response);
		}
	});
}

function initializeDirJobDataTable(response) {
	directJobTableList = $("#directJobTableList").dataTable({
		"bDestroy" : true,
		"aoColumns" : [ {
			sClass : "center ",
			mRender : function(data, type, full) {
				return full[0];
			}
		}, {
			sClass : "center",
			mRender : function(data, type, full) {
				return full[1];
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
		}, {
			sClass : "center",
			mRender : function(data, type, full) {
				return full[4];
			}
		}, {
			sClass : "center editable editableCellStyle",
			mRender : function(data, type, full) {
				return full[5];
			}
		} ],
		"fnDrawCallback" : function() {
			makeDirJobTableEditable();
		}
	});

	directJobTableList.fnClearTable();
	var dirJobValInclsTotal = 0;

	for (var i = 0; i < response.DirectJobBudget.length; i++) {
		directJobTableList.fnAddData([ response.DirectJobBudget[i].rowId,
				"DJE", response.DirectJobBudget[i].activityDescription,
				response.DirectJobBudget[i].activityNumber,
				response.DirectJobBudget[i].cost,
				response.DirectJobBudget[i].cost ], false);
		dirJobValInclsTotal += convertor2Int(response.DirectJobBudget[i].cost);
	}
	$("#dirJobTotal").text(convertor2Int(dirJobValInclsTotal));
	$("#extdirJobCost").val(convertor2Int(dirJobValInclsTotal));
	directJobTableList.fnDraw();
}

function makeDirJobTableEditable() {
	$('.editable').editable(function(sVal) {
		return sVal;
	}, {
		type : 'text',
		placeholder : '',
		"callback" : function(sValue, y) {
			var nodes = directJobTableList.fnGetNodes();
			var dirJobValInclsTotal = 0;
			var cost = 0;
			for (var i = 0; i < nodes.length; i++) {
				cost = $(nodes[i]).find("td:eq(5)").html();
				if (!isNaN(cost)) {
					$(nodes[i]).find("td:eq(5)").html(cost);
					$(nodes[i]).find("td:eq(4)").html(cost);
					dirJobValInclsTotal += convertor2Int(cost);
				}
			}
			$("#dirJobTotal").text(convertor2Int(dirJobValInclsTotal));
			$("#extdirJobCost").val(convertor2Int(dirJobValInclsTotal));
		},
		"tooltip" : 'Click to enter / change.',
		onblur : "submit",
		onsubmit : function(settings, td) {
			var input = $(td).find('input');
			var original = input.val();
		}
	});
}

function saveDirJobBudgetForm() {
	var dirJobTable = $("#directJobTableList").dataTable().fnGetNodes();
	var dirJobRfcBudgetTableData = null;
	var arrayItemTable = new Array();

	for (var i = 0; i < dirJobTable.length; i++) {
		dirJobRfcBudgetTableData = {
			"sno" : $(dirJobTable[i]).find("td:eq(0)").html(),
			"CostType" : $(dirJobTable[i]).find("td:eq(1)").html(),
			"Description" : $(dirJobTable[i]).find("td:eq(2)").html(),
			"Activity" : $(dirJobTable[i]).find("td:eq(3)").html(),
			"Cost" : $(dirJobTable[i]).find("td:eq(5)").html(),
			"RfcLogID" : $("#extsNo").val()
		};
		arrayItemTable.push(dirJobRfcBudgetTableData);
	}

	$.ajax({
		url : "AddUpdateDirJobBudgetForm",
		type : "POST",
		contentType : 'application/json',
		data : JSON.stringify(arrayItemTable),
		success : function(result) {

		}
	});
}

/**
 * Rfc Direct Job Budget Calculation End here
 */

/**
 * Rfc InDirect Budget Calculation Starts here
 */
function fetchInDirectBudgetFormData() {
	var sNo = $("#extsNo").val();
	$.ajax({
		url : "GetIndirectBudgetInfo",
		type : "POST",
		data : "rfcLogId=" + sNo,
		success : function(result) {
			var response = JSON.parse(result);
			initializeIndirDataTable(response);
		}
	});
}

function initializeIndirDataTable(response) {
	indirTableList = $("#indirTableList").dataTable({
		"bDestroy" : true,
		"aoColumns" : [ {
			sClass : "center ",
			mRender : function(data, type, full) {
				return full[0];
			}
		}, {
			sClass : "center",
			mRender : function(data, type, full) {
				return full[1];
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
		}, {
			sClass : "center",
			mRender : function(data, type, full) {
				return full[4];
			}
		}, {
			sClass : "center editable editableCellStyle",
			mRender : function(data, type, full) {
				return full[5];
			}
		} ],
		"fnDrawCallback" : function() {
			makeInDirTableEditable();
		}
	});

	indirTableList.fnClearTable();
	var inDirValInclsTotal = 0;

	for (var i = 0; i < response.IndirectBudget.length; i++) {
		indirTableList.fnAddData([ response.IndirectBudget[i].rowId,
				"INDIRECT", response.IndirectBudget[i].activityDescription,
				response.IndirectBudget[i].activityNumber,
				response.IndirectBudget[i].material,
				response.IndirectBudget[i].material ], false);
		inDirValInclsTotal += convertor2Int(response.IndirectBudget[i].material);
	}
	$("#indirTotal").text(convertor2Int(inDirValInclsTotal));
	$("#extindirCost").val(convertor2Int(inDirValInclsTotal));
	indirTableList.fnDraw();
}

function makeInDirTableEditable() {
	$('.editable').editable(function(sVal) {
		return sVal;
	}, {
		type : 'text',
		placeholder : '',
		"callback" : function(sValue, y) {
			var nodes = indirTableList.fnGetNodes();
			var inDirValInclsTotal = 0;
			var cost = 0;
			for (var i = 0; i < nodes.length; i++) {
				cost = $(nodes[i]).find("td:eq(5)").html();
				if (!isNaN(cost)) {
					$(nodes[i]).find("td:eq(5)").html(cost);
					$(nodes[i]).find("td:eq(4)").html(cost);
					inDirValInclsTotal += convertor2Int(cost);
				}
			}
			$("#indirTotal").text(convertor2Int(inDirValInclsTotal));
			$("#extindirCost").val(convertor2Int(inDirValInclsTotal));
		},
		"tooltip" : 'Click to enter / change.',
		onblur : "submit",
		onsubmit : function(settings, td) {
			var input = $(td).find('input');
			var original = input.val();
		}
	});
}

function saveIndirBudgetForm() {
	var inDirTable = $("#indirTableList").dataTable().fnGetNodes();
	var inDirRfcBudgetTableData = null;
	var arrayItemTable = new Array();

	for (var i = 0; i < inDirTable.length; i++) {
		inDirRfcBudgetTableData = {
			"sno" : $(inDirTable[i]).find("td:eq(0)").html(),
			"CostType" : $(inDirTable[i]).find("td:eq(1)").html(),
			"Description" : $(inDirTable[i]).find("td:eq(2)").html(),
			"Activity" : $(inDirTable[i]).find("td:eq(3)").html(),
			"Material" : $(inDirTable[i]).find("td:eq(5)").html(),
			"RfcLogID" : $("#extsNo").val()
		};
		arrayItemTable.push(inDirRfcBudgetTableData);
	}

	$.ajax({
		url : "AddUpdateIndirectBudgetForm",
		type : "POST",
		contentType : 'application/json',
		data : JSON.stringify(arrayItemTable),
		success : function(result) {

		}
	});
}

/**
 * Rfc InDirect Budget Calculation End here
 */

/**
 * Rfc Project Admin Budget Calculation Starts here
 */
function fetchProjAdminBudgetFormData() {
	var sNo = $("#extsNo").val();
	$.ajax({
		url : "GetProjAdminBudgetInfo",
		type : "POST",
		data : "rfcLogId=" + sNo,
		success : function(result) {
			var response = JSON.parse(result);
			initializeProjAdminDataTable(response);
		}
	});
}

function initializeProjAdminDataTable(response) {
	projAdminTableList = $("#projAdminTableList").dataTable({
		"bDestroy" : true,
		"aoColumns" : [ {
			sClass : "center ",
			mRender : function(data, type, full) {
				return full[0];
			}
		}, {
			sClass : "center",
			mRender : function(data, type, full) {
				return full[1];
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
		}, {
			sClass : "center editable editableCellStyle",
			mRender : function(data, type, full) {
				return full[4];
			}
		} ],
		"fnDrawCallback" : function() {
			makeProAdminTableEditable();
		}
	});

	projAdminTableList.fnClearTable();
	var proAdminValInclsTotal = 0;
	for (var i = 0; i < response.ProjAdminBudget.length; i++) {
		var valueIncTax = response.ProjAdminBudget[i].valueIncTax;
		if (valueIncTax != undefined) {
			valueIncTax = response.ProjAdminBudget[i].valueIncTax;
		} else {
			valueIncTax = 0;
		}
		projAdminTableList.fnAddData([ response.ProjAdminBudget[i].rowId,
				"PROJECT ADMIN",
				response.ProjAdminBudget[i].activityDescription,
				response.ProjAdminBudget[i].activityNumber, valueIncTax ],
				false);
		proAdminValInclsTotal += convertor2Int(valueIncTax);
	}
	$("#projAdminTotal").text(convertor2Int(proAdminValInclsTotal));
	$("#extprojAdmin").val(convertor2Int(proAdminValInclsTotal));
	projAdminTableList.fnDraw();
}

function makeProAdminTableEditable() {
	$('.editable').editable(function(sVal) {
		return sVal;
	}, {
		type : 'text',
		placeholder : '',
		"callback" : function(sValue, y) {
			var nodes = projAdminTableList.fnGetNodes();
			var proAdminValInclsTotal = 0;
			var cost = 0;
			for (var i = 0; i < nodes.length; i++) {
				cost = $(nodes[i]).find("td:eq(4)").html();
				if (!isNaN(cost)) {
					$(nodes[i]).find("td:eq(4)").html(cost);
					proAdminValInclsTotal += convertor2Int(cost);
				}
			}
			$("#projAdminTotal").text(convertor2Int(proAdminValInclsTotal));
			$("#extprojAdmin").val(convertor2Int(proAdminValInclsTotal));
		},
		"tooltip" : 'Click to enter / change.',
		onblur : "submit",
		onsubmit : function(settings, td) {
			var input = $(td).find('input');
			var original = input.val();
		}
	});
}

function saveProjAdminBudgetForm() {
	var proAdminTable = $("#projAdminTableList").dataTable().fnGetNodes();
	var proAdminRfcBudgetTableData = null;
	var arrayItemTable = new Array();

	for (var i = 0; i < proAdminTable.length; i++) {
		proAdminRfcBudgetTableData = {
			"sno" : $(proAdminTable[i]).find("td:eq(0)").html(),
			"CostType" : $(proAdminTable[i]).find("td:eq(1)").html(),
			"Description" : $(proAdminTable[i]).find("td:eq(2)").html(),
			"Activity" : $(proAdminTable[i]).find("td:eq(3)").html(),
			"VALUE_INC_TAX" : $(proAdminTable[i]).find("td:eq(4)").html(),
			"HOURS" : "0.0",
			"RATE" : "0.0",
			"RfcLogID" : $("#extsNo").val()
		};
		arrayItemTable.push(proAdminRfcBudgetTableData);
	}

	$.ajax({
		url : "AddUpdateProjAdminBudgetForm",
		type : "POST",
		contentType : 'application/json',
		data : JSON.stringify(arrayItemTable),
		success : function(result) {

		}
	});
}
/**
 * Rfc Project Admin Budget Calculation End here
 */
function closeBudgetModel() {
	$('.panel-body').removeClass('in')
	//$('.closePanel').slideUp();
}
/**
 * Rfc Project Admin Budget Calculation End here
 */
$('.modal').on('hidden.bs.modal', function() {
	$("#rfcInfoLi").addClass("active");
	$("#step1").addClass("active");
	$("#customerApproval").addClass('hide');
	$("#step3").removeClass("active");
	$("#approvalLi").addClass('hide');
	$("#step2").removeClass("active");
	$("#approvalLi").addClass('hide');
	$("#revenueLi").addClass('hide');
	$("#rejectBtn").addClass('hide');
	$("#resendCustomerApproval").addClass('hide');
	$("#reopenBtn").addClass('hide');
	$("#updateBtn").addClass('hide');
	// $("#headingAddUpdate").html("Add New Change Order");
	$("#saveBtn").show();
	$("#extsNo").val('');
	$("#jobAction").val("");
	$(this).find('form')[0].reset();
	// $('#hrefClick').on('click', function() {
	// if ($("#extsNo").val('')) {
	// $("#revenueLi").addClass('hide');
	// $("#saveBtn").show()
	// }
	// });
});
// function resetForm() {
// var validator = $("#manageChangeOrderForm").validate();
// validator.resetForm();
// $('#manageChangeOrderForm')[0].reset();
// // $("#revenueLi").removeClass("hide");
// // $("#approvalLi").removeClass("hide")
// // $("#revenueLi").addClass("hide");
// // $("#approvalLi").addClass("hide");
// // $("#step2").addClass("hide");
// // $("#step3").addClass("hide");
// // $("#step1").addClass("active");
// $("#rfcInfoLi").addClass("active");
// $("#customerApproval").addClass('hide');
// $("#approveBtn").addClass('hide');
// $("#rejectBtn").addClass('hide');
// $("#resendCustomerApproval").addClass('hide');
// $("#reopenBtn").addClass('hide');
// $("#updateBtn").addClass('hide');
// $("#headingAddUpdate").html("Add New Change Order");
// }

function setSubmitType(buttonId) {

	$("#submitType").val(buttonId);
}

$('input').on('click focusin', function() {
	if (this.value == '0.00' && this.id != 'myFile') {
		this.value = '';
	}
});
$('input').on('focusout', function() {
	if (this.value == '' && this.id != 'myFile') {
		this.value = 0.00;
	}
});
