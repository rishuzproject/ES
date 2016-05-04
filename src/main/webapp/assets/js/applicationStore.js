/**
 * Anantha MeghanaJagruthi
 */

var arrayId=new Array();
var count = 0;
var result = null;
var cookie;
function getPurchasePlanDetails() {
	$.ajax({
		url : "getPurchasePlanAction",
		method : "POST",
		success : function(resultJSON) {
			result = JSON.parse(resultJSON);
			console.log(result);

		}
	});
}

function checkCount()
{


	if(($("#subscriptionCartTable tr").length-4)==0)
		{
		$("#cartMessage").show();
		$("#subscriptionCartTable").hide();
		}
	else
		{
		$("#cartMessage").hide();
		$("#subscriptionCartTable").show();
		}
}
function cookieHelper(string, symbol) {
	return string.split(symbol);
}
function searchValue(cookieArray, string) {

	var length = string.length - 1;
	var resultantCookie = null;

	for (var i = 0; i < cookieArray.length; i++) {

		cookieArray[i] = cookieArray[i].trim();

		if (cookieArray[i].charAt(string.length) === '=') {
			for (var j = 0; j < string.length; j++) {

				if ((cookieArray[i].charAt(j) === string.charAt(j))
						&& length > 0) {
					length--;
				} else {
					break;
				}
			}

		}
		if (length == 0) {

			resultantCookie = cookieArray[i];
			break;
		}
	}

	return resultantCookie;
}
function fillSubscriptionCartTable() {
	
	var cookieArray = cookieHelper(document.cookie, ';');
	var contentsCookie = searchValue(cookieArray, emailId+":subscriptionCookie");
	var tableContents = cookieHelper(contentsCookie.trim(), '=');
	actualTableContents = JSON.parse(tableContents[1]);
	var table = document.getElementById("subscriptionCartTable");
	if (actualTableContents.length > 0)
		count = actualTableContents.length - 1;
	
	for (var i = 1; i < actualTableContents.length; i++) {
		
		var row = table.insertRow(i);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		var cell3 = row.insertCell(2);
		var cell4 = row.insertCell(3);
		var cell5 = row.insertCell(4);
		cell1.innerHTML = actualTableContents[i][0];
		cell2.innerHTML = actualTableContents[i][1];
		cell3.innerHTML = actualTableContents[i][2];
		cell4.innerHTML = actualTableContents[i][3];
		cell5.innerHTML = "<button class='btn btn-xs btn-default' data-original-title='Cancel' id="
				+ actualTableContents[i][0]
				+ " onclick='javascript:deleteRow();'><i class='fa fa-times'></i></button>";
     
	}
	$("#totalRows").html("<img src='./assets/img/hard_hat.png' alt='hard_hat' style='width:16px'> Cart &nbsp <span class='label bg-color-blue txt-color-white'style='display: inline-block;'>"+count+"</span><b class='badge bounceIn animated'></b>");
	calculateSum();

}

$(document).ready(function() {

	getLoggedInUserDetails();
	getPurchasePlanDetails();
	if (getCookieValues(emailId+":subscriptionCookie", " "+emailId+":subscriptionCookie") != null) {
		
		fillSubscriptionCartTable();
	}
	if(getCookieValues(emailId+":arrayIds"," "+emailId+":arrayIds"))
		{
		
		var arrayIds=JSON.parse(getCookieValues(emailId+":arrayIds"," "+emailId+":arrayIds"));
		arrayId=arrayId.concat(arrayIds);
		}
	$(window).scroll(function() {
		if ($(this).scrollTop() > 100) {
			$('.scrollup').fadeIn();
		} else {
			$('.scrollup').fadeOut();
		}
	});
	checkCount();
});

$(function() {
	$('.scroll a').bind('click', function(event) {
		var $anchor = $(this);

		$('html, body').stop().animate({
			scrollTop : $($anchor.attr('href')).offset().top
		}, 1800, 'easeInOutExpo');
		event.preventDefault();
	});
});
function calculateSum() {

	var sum = 0;
	var numberColumns = document.getElementById("subscriptionCartTable").rows[0].cells.length;
	for (var i = 1; i < $("#subscriptionCartTable tr").length - 3; i++) {
		sum = sum + parseFloat(document.getElementById("subscriptionCartTable").rows[i].cells[numberColumns - 2].innerHTML);
	}
	sum=Math.round(sum*100)/100;
	var promocode="1";
	$("#promoC").val(promocode);
	
	if(sum>=0)
	{
		$("#totalSumOfPlans").html('<td colspan="3" style="text-align:right;"><strong>Total Amount :</strong></td><td colspan="2" id="Sum" style="text-align: left; padding-left: 35px;"><h4><strong><span></span></strong></h4>'+sum+'</td>');
		$("#discountedAmount").show();
		$("#totalAmountRow").show();
	 sum=sum-100;
	 $("#totalSum").html(sum);
	}
	else
	{
		
		$("#discountedAmount").hide();
		$("#totalAmountRow").hide();
	}
	var cSum = JSON.stringify(sum);
	document.cookie = emailId+":sum" + "=" + cSum;

}
function deleteRow(id) {

	//
	var index=arrayId.indexOf(id);
	arrayId.splice(index,1);	
	var current = window.event.srcElement;
	while ((current = current.parentElement) && current.tagName != "TR");
	current.parentElement.removeChild(current);
	settingValuesInCookies();
	count--;
	calculateSum();
	$("#totalRows").html("<img src='assets/img/hard_hat.png' style='width: 16px'> Cart &nbsp <span class='label bg-color-blue txt-color-white'style='display: inline-block;'>"+count+"</span><b class='badge bounceIn animated'></b>");
	checkCount();
}

function getCookieValues(name, name1) {
	
	var regexp = new RegExp("(?:^" + name + "|;\s*" + name + ")=(.*?)(?:;|$)","g");
	var test1 = regexp.exec(document.cookie);
	var regexp1 = new RegExp(
			"(?:^" + name + "|;\s*" + name1 + ")=(.*?)(?:;|$)", "g");
	var test2 = regexp1.exec(document.cookie);
	if (test1 == null) {
		if (!(test2 === null))
			return test2[1];
		else
			return null;
	}
	return test1[1];
}
var emailId="";
function  getLoggedInUserDetails()
{
	$.ajax({
		url:"getSessionValues",
		type:"POST",
		async:false,
		success:function(data){
			var result=JSON.parse(data);
			emailId=result.selectedUserDetails.emailId;
		}
	});
}
function settingValuesInCookies() {
   
	
	
	var cookies = getJsonStringForTableData(); // obtaining the json string for
					   			// setting the value of cookie
	getLoggedInUserDetails();
	var arrayIds=JSON.stringify(arrayId);
	document.cookie = emailId+":subscriptionCookie" + "=" + cookies;
    document.cookie= emailId+":arrayIds"+"="+arrayIds;
    }
function getJsonStringForTableData() {
	var contentsSCTable = new Array();
	var rows = $('#subscriptionCartTable tr').length - 3;
	for (var i = 1; i < rows; i++) {

		var columns = document.getElementById("subscriptionCartTable").rows[i].cells.length;
		contentsSCTable[i] = new Array(columns - 1);

		for (var j = 0; j < columns - 1; j++) {

			if (j == columns - 2) {
				contentsSCTable[i][j] = parseFloat(document
						.getElementById("subscriptionCartTable").rows[i].cells[j].innerHTML);
			} else {
				contentsSCTable[i][j] = document
						.getElementById("subscriptionCartTable").rows[i].cells[j].innerHTML;
			}

		}
		
	}
	cookie = JSON.stringify(contentsSCTable);
	return cookie;
}

function checkForDuplicateRows(id)
{
 var temp=getJsonStringForTableData();
	var arr=JSON.parse(temp);
	var app=new Array();
	app[0]= result.allPlanList[id-1].applicationDirectory.applicationName;
	 app[1]= result.allPlanList[id-1].planName;
	 app[2]= result.allPlanList[id-1].planDescription;
	 app[3] = result.allPlanList[id-1].price;
	var tempCount=0;
	var checkDup=1;
	for(var i=1;i<arr.length;i++)
		{
		  if((arr[i][0]==app[0])&&(arr[i][1]==app[1])&&(arr[i][2]==app[2])&&(arr[i][3]==app[3]))
			  {
			  tempCount=4;
			  break;
			  }
		  else if(arr[i][0]==app[0])
			  {
			    tempCount=checkDup;
			  }
		}
	return tempCount;
}


 
function addNewRow(id,action) {
	
	if(checkForDuplicateRows(id)==4)
    {
		gritterForErrorMsgs("Already plan is present in the table");
    }
	else if(checkForDuplicateRows(id)==1)
		 {
		    gritterForErrorMsgs("Only one plan from an application must be  present in the table");
		}
	else{
	
	var table = document.getElementById("subscriptionCartTable");// A check
																	// must be
																	// kept here
																	// and id
																	// must be
														            // passed
	
	 ++count;
	var row = table.insertRow(count);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);
	var cell5 = row.insertCell(4);
	cell1.innerHTML = result.allPlanList[id-1].applicationDirectory.applicationName;
	cell2.innerHTML = result.allPlanList[id-1].planName;
	cell3.innerHTML = result.allPlanList[id-1].planDescription;
	cell4.innerHTML = result.allPlanList[id-1].price;
	cell5.innerHTML = "<button class='btn btn-xs btn-default' data-original-title='Cancel' onclick='deleteRow("+result.allPlanList[id-1].planId+");'><i class='fa fa-times'></i></button>";
	
	checkCount();
	arrayId.push(result.allPlanList[id-1].planId);
	$("#totalRows").html("<img src='assets/img/hard_hat.png' style='width: 16px'> Cart &nbsp <span class='label bg-color-blue txt-color-white'style='display: inline-block;'>"+count+"</span><b class='badge bounceIn animated'></b>");
	calculateSum();
	gritterForSucessMsgs("Plan is successfully added to the cart");
	settingValuesInCookies();
	if(action=="buyNow")
		{
		  $("#openCart").modal("show");
		}
  }
}
function saveAllRecords() {
	console.log("the selected pplans include");
	console.log(arrayId.length>0);
	if(arrayId.length>0)
	{
		var res=JSON.stringify(arrayId);
		$("#selectedPlans").val(res);
		$("#promoCode").val(1);
		console.log($("#selectedPlans").val());
		var obj={
				selectedPlans:$("#selectedPlans").val(),
				promoCode: $("#promoCode").val()
		};
		$.ajax({
			url:"saveCookieData",
			method:"POST",
			data:obj,
			success:function(data)
			{
				
				if(data == "success")
					{
					window.location.href = 'home#payment';
					}
				
			}
		});

		}
	else
		{
		gritterForInfoMsgs(" Please add atleast a plan in the cart before you proceed for payement");
		}
	}
	

	


