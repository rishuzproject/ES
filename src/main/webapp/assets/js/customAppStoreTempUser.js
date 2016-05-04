function NormalLayoutForHome()
{
	$("#show-shortcut").css("pointer-events", "fill");
	$("#dashboardId").css({'pointer-events':'fill','color':'#a8a8a8'});
	$("#manageAcc").css({'pointer-events':'fill','color':'#a8a8a8'});
	$("#appInvoice").css({'pointer-events':'fill','color':'#a8a8a8'});
	$("#proj").css({'pointer-events':'fill','color':'#a8a8a8'});
	$("#mngUser").css({'pointer-events':'fill','color':'#a8a8a8'});
	$("#accSettings").css({'pointer-events':'fill','color':'#a8a8a8'});
	$("#custDirectory").css({'pointer-events':'fill','color':'#a8a8a8'});
	$("#vendorDir").css({'pointer-events':'fill','color':'#a8a8a8'});
	$("#contractorDir").css({'pointer-events':'fill','color':'#a8a8a8'});
	$("#license").css({'pointer-events':'fill','color':'#a8a8a8'});
	$("#statLicense").css({'pointer-events':'fill','color':'#a8a8a8'});
	$("#localLicense").css({'pointer-events':'fill','color':'#a8a8a8'});
	$("#projectType").css({'pointer-events':'fill','color':'#a8a8a8'});
	$("#deptDetails").css({'pointer-events':'fill','color':'#a8a8a8'});
	$("#maalDetails").css({'pointer-events':'fill','color':'#a8a8a8'});
	$("#budgtForm").css({'pointer-events':'fill','color':'#a8a8a8'});
	$("#jobsIDForLonkId").css({'pointer-events':'fill','color':'#a8a8a8'});
	$("#appStore").attr("href","applicationStore");
	$( '#appStore' ).click( function( e ) {
	    return true;
	} );
}
function customizedLayoutForHome()
{
$("#show-shortcut").css("pointer-events", "none");
$("#dashboardId").css({'pointer-events':'none','color':'rgba(168, 168, 168, 0.35)'});
$("#manageAcc").css({'pointer-events':'none','color':'rgba(168, 168, 168, 0.35)'});
$("#appInvoice").css({'pointer-events':'none','color':'rgba(168, 168, 168, 0.35)'});
$("#proj").css({'pointer-events':'none','color':'rgba(168, 168, 168, 0.35)'});
$("#mngUser").css({'pointer-events':'none','color':'rgba(168, 168, 168, 0.35)'});
$("#accSettings").css({'pointer-events':'none','color':'rgba(168, 168, 168, 0.35)'});
$("#custDirectory").css({'pointer-events':'none','color':'rgba(168, 168, 168, 0.35)'});
$("#vendorDir").css({'pointer-events':'none','color':'rgba(168, 168, 168, 0.35)'});
$("#contractorDir").css({'pointer-events':'none','color':'rgba(168, 168, 168, 0.35)'});
$("#license").css({'pointer-events':'none','color':'rgba(168, 168, 168, 0.35)'});
$("#statLicense").css({'pointer-events':'none','color':'rgba(168, 168, 168, 0.35)'});
$("#localLicense").css({'pointer-events':'none','color':'rgba(168, 168, 168, 0.35)'});
$("#jobsIDForLonkId").css({'pointer-events':'none','color':'rgba(168, 168, 168, 0.35)'});
$("#projectType").css({'pointer-events':'none','color':'rgba(168, 168, 168, 0.35)'});
$("#deptDetails").css({'pointer-events':'none','color':'rgba(168, 168, 168, 0.35)'});
$("#maalDetails").css({'pointer-events':'none','color':'rgba(168, 168, 168, 0.35)'});
$("#budgtForm").css({'pointer-events':'none','color':'rgba(168, 168, 168, 0.35)'});
//$("#appStore").removeAttr("href").css({'cursor':'pointer','color':'#fff'});
//$( '#appStore' ).click( function( e ) {
//    e.preventDefault();
//    return false;
//} );
}