/**
 * Anantha MeghanaJagruthi
 */
var arrayId = new Array();
var promoCode;
function settingValuesInCookies() {
	var cookies = getJsonStringForsSTableData(); // obtaining the json string for
	// setting the value of cookie
	var arrayIds = JSON.stringify(arrayId);
	document.cookie = "subscriptionCookie" + "=" + cookies;
	document.cookie = "arrayIds" + "=" + arrayIds;
}
function getJsonStringForsSTableData() {
	
	var contentsSCTable = new Array();
	var rows = $('#summarisedSubscriptionTable tr').length - 3;
	console.log(rows);
	for (var i = 1; i <= rows; i++) {

		var columns = document.getElementById("summarisedSubscriptionTable").rows[i].cells.length;
		contentsSCTable[i] = new Array(columns - 1);

		for (var j = 0; j < columns - 1; j++) {

			if (j == columns - 2) {
				contentsSCTable[i][j] = parseFloat(document
						.getElementById("summarisedSubscriptionTable").rows[i].cells[j].innerHTML);
			} else {
				contentsSCTable[i][j] = document
						.getElementById("summarisedSubscriptionTable").rows[i].cells[j].innerHTML;
			}

		}
		
	}
	cookie = JSON.stringify(contentsSCTable);
	return cookie;
}

function calculateSum() {

	var sum = 0;
	var count = 0;
	var sumToBeDetected = 0;
	var numberColumns = document.getElementById("summarisedSubscriptionTable").rows[0].cells.length;
	
	for (var i = 1; i <= $("#summarisedSubscriptionTable tr").length - 3; i++) {
		sum = sum
				+ parseFloat(document
						.getElementById("summarisedSubscriptionTable").rows[i].cells[numberColumns - 2].innerHTML);
		
	}
   
	
	sum=Math.round(sum*100)/100;
	//if (promoCode == "1")
		sumToBeDetected = 100;
	$("#PSum").html(sum);
	$("#promocode").val(promoCode);
	$("#totalSaved").show();
	if (sum >= sumToBeDetected) {
		sum = sum - sumToBeDetected;
		$("#PTSum").html(sum);
		$("#finalPrice").val(sum);
	} else {
		$("#amountAfterDiscount").hide();
		$("#totalSum").html("");
		$("#discountedAmount").hide();
		$("#PTSum").html(0);
		$("#finalPrice").val(0);
	}
}
function deleteRow(id) {

	var current = window.event.srcElement;
	var index = arrayId.indexOf(id);
	arrayId.splice(index, 1);
	while ((current = current.parentElement) && current.tagName != "TR")
		;
	current.parentElement.removeChild(current);
	checkCount();
	calculateSum();
	settingValuesInCookies();
}
function fillSummarisedSubscriptionTable() {
	
	var $table = $('#summarisedSubscriptionTable');

	for (var i = 0; i < tableData.selectedPlansList.length; i++) {
		
		arrayId.push(tableData.selectedPlansList[i].planId);
		$row = $('<tr/>');
		$col = $('<td/>');
		$col
				.append(tableData.selectedPlansList[i].applicationDirectory.applicationName);
		$row.append($col);
		$col = $('<td/>');
		$col.append(tableData.selectedPlansList[i].planName);
		$row.append($col);
		$col = $('<td/>');
		$col.append(tableData.selectedPlansList[i].planDescription);
		$row.append($col);
		$col = $('<td/>');
		$col.append(tableData.selectedPlansList[i].price);
		$row.append($col);
		$col = $('<td/>');
		$col
				.append("<button class='btn btn-xs btn-default'data-original-title='Cancel' onclick='deleteRow("
						+ tableData.selectedPlansList[i].planId
						+ ");'><i class='fa fa-times'></i></button>");
		$row.append($col);
		$table.append($row);

	}
	$row = $('<tr id="totalSum"  />');
	$col = $('<td colspan="3" style="text-align:right; vertical-align: middle;"/>');
	$col.append("<strong>Total Amount :</strong>");
	$row.append($col);
	$col = $('<td colspan="1" id="PSum"/>');
	$col.append("<h4><strong><span></span></strong></h4>");
	$row.append($col);
	$table.append($row);
	/*$row = $('<tr  id="discountedAmount"  />');
	$col = $('<td colspan="2" style="text-align:right; vertical-align: middle;"/>');
	$col.append('<fieldset><section><label class="input"><input type="text" id="promocode" name="promocode" placeholder="PROMO CODE.." style="border-radius: 5px; border: 1px solid #ccc;padding: 5px;"></label></section></fieldset>');
	$row.append($col);
	$col = $('<td colspan="3" style="text-align:right; vertical-align: middle; id="totalSaved" />');
	$col.append("Your Total Savings : <label>$5.50</label>");
	$row.append($col);
	$table.append($row);*/
	$row = $('<tr id="amountAfterDiscount"  />');
	$col = $('<td colspan="3" style="text-align:right; vertical-align: middle;"/>');
	$col.append("<h5><strong>Total Amount after discount :</strong></h5>");
	$row.append($col);
	$col = $('<td colspan="1" id="PTSum"/>');
	$col.append('<h4><strong><span class="text-success"></span></strong></h4>');
	$row.append($col);
	$table.append($row);
	calculateSum();

}

var tableData;
function getTableContents() {
	$.ajax({
		url : "getCookieData",
		method : "POST",
		success : function(data) {
			tableData = JSON.parse(data);
			promoCode = tableData.promoCode;
			fillSummarisedSubscriptionTable();
		}
	});
}
function checkCount()
{
	if(($("#summarisedSubscriptionTable tr").length-4)==0)
		{
		$("#cartMessage").show();
		$("#summarisedSubscriptionTable").hide();
		//$("#totalSumOfPlans").html("<p> <span style='color:red'>The cart is empty....!!!!</span>");
		}
	else
		{
		$("#cartMessage").hide();
		$("#summarisedSubscriptionTable").show();
		}
}
$(document).ready(function() {
	console.log("ready");
	
	getTableContents();
	checkCount();

});

// Payment page related scripts starts here.
function showCompanyInfo() {
	//$('#userinfo').addClass('hide');
	//$('#companyinfo').removeClass('hide');
	$('#uicon').removeClass('fa-user');
	$('#uicon').addClass('fa-check');
	$('#companyInfoHead').addClass('headerGradient');
	$('#uedit').removeClass('hide');
}
function showSummary() {
	//$('#summary').removeClass('hide');
	//$('#companyinfo').addClass('hide');
	$('#cicon').removeClass('fa-building');
	$('#cicon').addClass('fa-check');
	$('#cedit').removeClass('hide');
	$('#companyInfoHead')
			.css(
					{
						'background-image' : '-ms-linear-gradient(top, #404040 0%, #404040 100%)',
						'background-image' : '-moz-linear-gradient(top, #404040 0%, #404040 100%)',
						'background-image' : '-o-linear-gradient(top, #404040 0%, #404040 100%)',
						'background-image' : '-webkit-gradient(linear, left top, left bottom, color-stop(0, #404040), color-stop(1, #404040))',
						'background-image' : '-webkit-linear-gradient(top, #404040 0%, #404040 100%)',
						'background-image' : 'linear-gradient(to bottom, #404040 0%, #404040 100%)',
						"color" : "white",
						"text-shadow" : "1px 1px #000000"
					});
}
function showPayment() {
	$('#payment').removeClass('hide');
	$('#summary').addClass('hide');
	$('#sicon').removeClass('fa-shopping-cart');
	$('#sicon').addClass('fa-check');
	$('#sedit').removeClass('hide');
	$('#summaryHead')
			.css(
					{
						'background-image' : '-ms-linear-gradient(top, #404040 0%, #404040 100%)',
						'background-image' : '-moz-linear-gradient(top, #404040 0%, #404040 100%)',
						'background-image' : '-o-linear-gradient(top, #404040 0%, #404040 100%)',
						'background-image' : '-webkit-gradient(linear, left top, left bottom, color-stop(0, #404040), color-stop(1, #404040))',
						'background-image' : '-webkit-linear-gradient(top, #404040 0%, #404040 100%)',
						'background-image' : 'linear-gradient(to bottom, #404040 0%, #404040 100%)',
						"color" : "white",
						"text-shadow" : "1px 1px #000000"
					});

}
function showUserInfo() {
	$('#userinfo').removeClass('hide');
	$('#payment').addClass('hide');
	$('#picon').removeClass('fa-credit-card');
	$('#picon').addClass('fa-check');
	$('#pedit').removeClass('hide');
	$('#paymentHead')
			.css(
					{
						'background-image' : '-ms-linear-gradient(top, #404040 0%, #404040 100%)',
						'background-image' : '-moz-linear-gradient(top, #404040 0%, #404040 100%)',
						'background-image' : '-o-linear-gradient(top, #404040 0%, #404040 100%)',
						'background-image' : '-webkit-gradient(linear, left top, left bottom, color-stop(0, #404040), color-stop(1, #404040))',
						'background-image' : '-webkit-linear-gradient(top, #404040 0%, #404040 100%)',
						'background-image' : 'linear-gradient(to bottom, #404040 0%, #404040 100%)',
						"color" : "white",
						"text-shadow" : "1px 1px #000000"
					});
}
function showUserInfoDiv() {
	$('#userinfo').removeClass('hide');
	$('#companyinfo').addClass('hide');
	$('#summary').addClass('hide');
	$('#payment').addClass('hide');
}
function showCompanyInfoDiv() {
	$('#userinfo').addClass('hide');
	$('#companyinfo').removeClass('hide');
	$('#summary').addClass('hide');
	$('#payment').addClass('hide');
}
function showSummaryDiv() {
	$('#userinfo').addClass('hide');
	$('#companyinfo').addClass('hide');
	$('#summary').removeClass('hide');
	$('#payment').addClass('hide');
}
function showPaymentDiv() {
	$('#userinfo').addClass('hide');
	$('#companyinfo').addClass('hide');
	$('#summary').addClass('hide');
	$('#payment').removeClass('hide');
}
// Payment page related scripts ends here.
