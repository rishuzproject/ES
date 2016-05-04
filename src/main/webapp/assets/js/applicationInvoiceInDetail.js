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

		// PAGE RELATED SCRIPTS

		// pagefunction

		var pagefunction = function() {
			getUserDetailsForInvoiceInDetail();
		};

		// end pagefunction

		// run pagefunction on load

		pagefunction();
		$(document).ready(function() {
	    	gettingValuesFromProperties();
	    });


function getUserDetailsForInvoiceInDetail(){
	$.ajax({
		url : "getUserDetailsForInvoiceInDetail",
		type : "POST",
		success : function(result){
			result = JSON.parse(result);
			console.log(result);
			$("#applyInterest").click(function (){
				applyInterest(result);
			});
			var firstName ,lastName,middleName;
			middleName = lastName = "";
			if (result.applicationInvoicePlanMapping[0].submittedBy.middleName != undefined
					&& result.applicationInvoicePlanMapping[0].submittedBy.middleName != null) {
				middleName = result.applicationInvoicePlanMapping[0].submittedBy.middleName;
			}
			if (result.applicationInvoicePlanMapping[0].submittedBy.lastName != undefined
					&& result.applicationInvoicePlanMapping[0].submittedBy.lastName != null) {
				lastName = result.applicationInvoicePlanMapping[0].submittedBy.lastName;
			}
			firstName=result.applicationInvoicePlanMapping[0].submittedBy.firstName;
			var name=firstName+" "+middleName+" "+lastName;
			var compAddress = result.applicationInvoicePlanMapping[0].domainDetail.companyAddress;
			var a = compAddress.split(',');
			var addressSplit="";
			for(var i=0;i< a.length-1;i++){
				addressSplit += a[i]+",<br>";
			}
			addressSplit += a[i]+"<br>";
			var comPhone = result.applicationInvoicePlanMapping[0].domainDetail.companyPhoneCarrier;
			var buyerId = "<font style='text-transform: uppercase;'><strong>"+name+"</strong></font><br>"+addressSplit+"<abbr title='Phone'>Phone:</abbr>"+comPhone;
			submittedDate = moment(new Date(result.applicationInvoicePlanMapping[0].submittedDate)).format("MM-DD-YYYY");
			$("#submittedDateId").html("<i class='fa fa-calendar'></i>&nbsp;"+submittedDate);
			$("#invoiceId").html("#"+result.applicationInvoicePlanMapping[0].applicationInvoice.invoiceId);
			$("#domainNameId").html(result.applicationInvoicePlanMapping[0].domainDetail.companyName+".");
			$("#buyerId").html(buyerId);
			var rows="";
			var total=0;
			var hstgst = 13;
			var interest = 0;
			var lateFee = 0;
			var totalLateFee = 0;
			var totalInterest = 0;
			var additonalCharges = 0;
			if(result.applicationInvoicePlanMapping[0].applicationInvoice.additionalCharges != null 
					&& result.applicationInvoicePlanMapping[0].applicationInvoice.additionalCharges != undefined){
				additonalCharges = result.applicationInvoicePlanMapping[0].applicationInvoice.additionalCharges;
			}
			if(result.applicationInvoicePlanMapping[0].applicationInvoice.isInterestApplicable){
				interest = propertiesResult.isInterest;
			}
			if(result.applicationInvoicePlanMapping[0].applicationInvoice.isLateFeesApplicable){
				lateFee = propertiesResult.isLateFee;
			}
			for(var t=0;t<result.applicationInvoicePlanMapping.length;t++){
				var app=result.applicationInvoicePlanMapping[t].domainApplicationPlanMapping.applicationPlanDirectory.applicationDirectory.applicationName;
				var plan=result.applicationInvoicePlanMapping[t].domainApplicationPlanMapping.applicationPlanDirectory.planName;
				var price=result.applicationInvoicePlanMapping[t].domainApplicationPlanMapping.applicationPlanDirectory.price; 
				var subTotal = price;
				totalLateFee += lateFee;
				totalInterest += ((interest/100) * price);
				if(result.applicationInvoicePlanMapping[0].applicationInvoice.isInterestApplicable){
					total += ((interest/100) * price);
				}
				if(result.applicationInvoicePlanMapping[0].applicationInvoice.isLateFeesApplicable){
					total += lateFee;
				}
				total += subTotal;
				rows += "<tr><td>" +app + "</td><td>" + plan + "</td><td>" + price + "</td><td>" + interest + "</td><td>"+subTotal+"</td></tr>";
			}
			total += additonalCharges;
			rows +="<tr><td colspan='4'>HST/GST</td><td><strong>"+hstgst+"%</strong></td></tr>";
			if(result.applicationInvoicePlanMapping[0].applicationInvoice.isInterestApplicable){
				rows +="<tr><td colspan='4'>Total Interest</td><td><strong>$"+totalInterest+"</strong></td></tr>";
			}
			if(result.applicationInvoicePlanMapping[0].applicationInvoice.isLateFeesApplicable){
				rows +="<tr><td colspan='4'>Total Late Fees</td><td><strong>$"+totalLateFee+"</strong></td></tr>";
			}
			if(additonalCharges != 0){
				rows +="<tr><td colspan='4'>Additional Charges</td><td><strong>$"+additonalCharges+"</strong></td></tr>";
			}
			rows +="<tr><td colspan='4'>Total</td><td><strong>$"+total+"</strong></td></tr>";
			$( rows ).appendTo( "#applicationInvoiceInDetailsTable tbody" );
			var totalAmount=(total *(hstgst/100))+total;
			$("#totalAmount").html(totalAmount.toFixed(2)+"&nbsp;USD");
			$("#totalAmountId").html(totalAmount.toFixed(2)+"&nbsp;USD");
//			$("#").html();
//			$("#").html();
		},
		error : function(result){
			/*  console.log(result.message);*/
//			console.log("error");
		}
	});
}
function printDiv(divName) {
    var printContents = document.getElementById(divName).innerHTML;
    var originalContents = document.body.innerHTML;

    document.body.innerHTML = printContents;

    window.print();

    document.body.innerHTML = originalContents;
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