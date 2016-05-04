/**
 * Anantha Meghana jagruthi
 * 
 */


var responsiveHelper_dt_basic = undefined;

	var breakpointDefinition = {
		tablet : 1024,
		phone : 480
	};
	var otherCompaniesInfoTable=null;
	
	
function setCompanyName(domainId)
{
	
	$("#selectedCompanyId").val(domainId);
	$.SmartMessageBox(
			{
				title : "<i class='fa fa fa-bell txt-color-orangeDark'></i> Please Confirm.",
				content : "<br>Once the company is switched cannot revert back within a session "
						+ "Are you sure you want to switch?<br>"
						+ "<br/> <i class='ace-icon fa fa-hand-o-right txt-color-orangeDark bigger-150'></i>&nbsp;&nbsp;<b>Are you sure you want to delete it ?</b>",
				buttons : '[No][Yes]'
			}, function(ButtonPressed) {
				if (ButtonPressed === "Yes") {
					$("#modalForPswdVerification").modal("show");
					
				   
				}
			});

	
}
function  initOtherCompaniesInfoTable(){
	
		otherCompaniesInfoTable=$('#otherCompaniesInfoTable')
				.dataTable(
						{
							"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"
									+ "t"
									+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
							"autoWidth" : true,
							aoColumns : [{
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
								mData : null,
								sClass : "center",
								mRender : function(data, type, full) {
									return "<div class='pull-right action-buttons'><button  onclick=\"javascript:setCompanyName('"
										+ full[5]
										+ "')\">Switch Company</button></div>";
								}
							} ],
							"preDrawCallback" : function() {
								// Initialize the responsive datatables helper once.
								if (!responsiveHelper_dt_basic) {
									responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
											$('#otherCompaniesInfoTable'),
											breakpointDefinition);
								}
							},
							"rowCallback" : function(nRow) {
								responsiveHelper_dt_basic
										.createExpandIcon(nRow);
							},
							"drawCallback" : function(oSettings) {
								responsiveHelper_dt_basic.respond();
							}
						});
		getOtherCompaniesInfoTable();
}

function setCompanyId(id){
	$("#selectedCompanyId").val(id);
}
function switchCompany()
{
	
		$.ajax({
		url:"switchingCompany",
		type:"POST",
		data:$("#passwordVerificationForm").serialize(),
		success:function(){
			location.href="staticHome";
		}
	});
}

function getOtherCompaniesInfoTable()
{
	
	$.ajax({
		url:"getOtherCompanyInfo",
		type:"GET",
		success:function(result){
			var companyDetails=JSON.parse(result);
			if(companyDetails.ajaxResult == "success")
			{
				otherCompaniesInfoTable.fnClearTable();
				console.log(companyDetails.otherCompanyInfoDetails.length);
			for(var i=0;i<companyDetails.otherCompanyInfoDetails.length;i++)
			{
				if(companyDetails.otherCompanyInfoDetails[i].domainId!=2)
				otherCompaniesInfoTable.fnAddData([i+1,companyDetails.otherCompanyInfoDetails[i].companyName,companyDetails.otherCompanyInfoDetails[i].companyAddress,companyDetails.otherCompanyInfoDetails[i].companyPhoneCarrier,companyDetails.otherCompanyInfoDetails[i].companyPhoneNumber,companyDetails.otherCompanyInfoDetails[i].domainId]);
				
			}
		 }
		}
	});
}