<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<head>
<style>
.widget-body.no-padding {
margin: -13px -6px 0 !important;
}
</style>
</head>

<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title">
			<i class="fa fa-user-md fa-fw txt-color-blue"></i> User <span>&gt; 
			Mapping </span>
		</h1>
	</div>
	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
		<ul id="sparks" class="">
			<li class="sparks-info">
				<h5>
					Total Users <span class="txt-color-blue">171</span>
				</h5>
			</li>
			<li class="sparks-info">
				<h5>
					Active Users <span class="txt-color-purple"><i
						class="fa fa-thumbs-o-up" data-rel="bootstrap-tooltip"
						title="Increased"></i>&nbsp;100</span>
				</h5>
			</li>
			<li class="sparks-info">
				<h5>
					Inactive Users <span class="txt-color-greenDark"><i
						class="fa fa-thumbs-o-down"></i>&nbsp;71</span>
				</h5>
			</li>
		</ul>
	</div>
</div>


<!-- Datatable code starts -->

<!-- widget grid -->
<section id="widget-grid" class="">
	<!-- row -->
	<div class="row">
		<!-- NEW WIDGET START -->
		<article
			class="col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
			<div
				class="jarviswidget jarviswidget-color-darken jarviswidget-sortable"
				id="wid-id-0" data-widget-editbutton="false" role="widget" style="">
				<header role="heading">
					<div class="jarviswidget-ctrls" role="menu">
						<a href="javascript:void(0);"
							class="button-icon jarviswidget-toggle-btn" rel="tooltip"
							title="" data-placement="bottom" data-original-title="Collapse"><i
							class="fa fa-minus "></i></a> <a href="javascript:void(0);"
							class="button-icon jarviswidget-fullscreen-btn" rel="tooltip"
							title="" data-placement="bottom" data-original-title="Fullscreen"><i
							class="fa fa-expand "></i></a> 
					</div>
					<div class="widget-toolbar" role="menu">
						<a data-toggle="dropdown"
							class="dropdown-toggle color-box selector"
							href="javascript:void(0);"></a>
						<ul
							class="dropdown-menu arrow-box-up-right color-select pull-right">
							<li><span class="bg-color-green"
								data-widget-setstyle="jarviswidget-color-green" rel="tooltip"
								data-placement="left" data-original-title="Green Grass"></span></li>
							<li><span class="bg-color-greenDark"
								data-widget-setstyle="jarviswidget-color-greenDark"
								rel="tooltip" data-placement="top"
								data-original-title="Dark Green"></span></li>
							<li><span class="bg-color-greenLight"
								data-widget-setstyle="jarviswidget-color-greenLight"
								rel="tooltip" data-placement="top"
								data-original-title="Light Green"></span></li>
							<li><span class="bg-color-purple"
								data-widget-setstyle="jarviswidget-color-purple" rel="tooltip"
								data-placement="top" data-original-title="Purple"></span></li>
							<li><span class="bg-color-magenta"
								data-widget-setstyle="jarviswidget-color-magenta" rel="tooltip"
								data-placement="top" data-original-title="Magenta"></span></li>
							<li><span class="bg-color-pink"
								data-widget-setstyle="jarviswidget-color-pink" rel="tooltip"
								data-placement="right" data-original-title="Pink"></span></li>
							<li><span class="bg-color-pinkDark"
								data-widget-setstyle="jarviswidget-color-pinkDark" rel="tooltip"
								data-placement="left" data-original-title="Fade Pink"></span></li>
							<li><span class="bg-color-blueLight"
								data-widget-setstyle="jarviswidget-color-blueLight"
								rel="tooltip" data-placement="top"
								data-original-title="Light Blue"></span></li>
							<li><span class="bg-color-teal"
								data-widget-setstyle="jarviswidget-color-teal" rel="tooltip"
								data-placement="top" data-original-title="Teal"></span></li>
							<li><span class="bg-color-blue"
								data-widget-setstyle="jarviswidget-color-blue" rel="tooltip"
								data-placement="top" data-original-title="Ocean Blue"></span></li>
							<li><span class="bg-color-blueDark"
								data-widget-setstyle="jarviswidget-color-blueDark" rel="tooltip"
								data-placement="top" data-original-title="Night Sky"></span></li>
							<li><span class="bg-color-darken"
								data-widget-setstyle="jarviswidget-color-darken" rel="tooltip"
								data-placement="right" data-original-title="Night"></span></li>
							<li><span class="bg-color-yellow"
								data-widget-setstyle="jarviswidget-color-yellow" rel="tooltip"
								data-placement="left" data-original-title="Day Light"></span></li>
							<li><span class="bg-color-orange"
								data-widget-setstyle="jarviswidget-color-orange" rel="tooltip"
								data-placement="bottom" data-original-title="Orange"></span></li>
							<li><span class="bg-color-orangeDark"
								data-widget-setstyle="jarviswidget-color-orangeDark"
								rel="tooltip" data-placement="bottom"
								data-original-title="Dark Orange"></span></li>
							<li><span class="bg-color-red"
								data-widget-setstyle="jarviswidget-color-red" rel="tooltip"
								data-placement="bottom" data-original-title="Red Rose"></span></li>
							<li><span class="bg-color-redLight"
								data-widget-setstyle="jarviswidget-color-redLight" rel="tooltip"
								data-placement="bottom" data-original-title="Light Red"></span></li>
							<li><span class="bg-color-white"
								data-widget-setstyle="jarviswidget-color-white" rel="tooltip"
								data-placement="right" data-original-title="Purity"></span></li>
							<li><a href="javascript:void(0);"
								class="jarviswidget-remove-colors" data-widget-setstyle=""
								rel="tooltip" data-placement="bottom"
								data-original-title="Reset widget color to default">Remove</a></li>
						</ul>
					</div>
					&nbsp;
				</header>

				<!-- widget div-->
				<div role="content">
					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
					</div>
					<!-- end widget edit box -->
					<!-- widget content -->
					<div class="widget-body ">
					
				
			

			

			<hr class="simple">

			<p>
				<button id="add_tab" class="btn btn-primary"  onclick="newTabCreation()">
					Add Tab
				</button>
			</p>

						<div
							class="hide ui-tabs ui-widget ui-widget-content ui-corner-all "
							id="mainTabDiv">
							<ul class="nav nav-tabs bordered" id="itemDetailUL">
								<!-- <li class="active">
			<a href="#s1" data-toggle="tab">Application <i class="fa fa-fw fa-lg fa-font"></i>
			</a>
			</li>
			<li class="">
			<a href="#s2" data-toggle="tab"> Plan  <i class="fa fa-fw fa-lg fa-edit"></i>
			</a>
			</li> -->
							</ul>
							<div class="tab-content padding-10" id="itemTabContent">
							
								<!-- <div class="tab-pane active" id="s1">
				<h2>ashraf</h2>
			</div>-->
			<!-- <div class="tab-pane " id="s2">
				<h2>mosharrf</h2>
			</div> --> 
							</div>
						</div>

	
					
						<!-- <div id="dt_basic_wrapper"
							class="dataTables_wrapper form-inline no-footer">
							<table id="userDetailsTable"
								class="table table-striped table-bordered table-hover dataTable no-footer"
								width="100%" role="grid" aria-describedby="dt_basic_info"
								style="width: 100%;">
								<thead>
									<tr role="row">
										<th data-hide="phone" class="sorting_asc" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-sort="ascending"
											aria-label="ID: activate to sort column ascending"
											style="width: 41px;"><i
											class="fa fa-fw fa-key txt-color-blue text-muted hidden-md hidden-sm hidden-xs"></i>
											 Id</th>
										<th data-class="expand" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" First Name: activate to sort column ascending"
											style="width: 110px;"><i
											class="fa fa-fw fa-user txt-color-blue text-muted hidden-md hidden-sm hidden-xs"></i>
											First Name </th>
										<th data-hide="phone,tablet" class="sorting" tabindex="0"
											aria-controls="dt_basic" rowspan="1" colspan="1"
											aria-label=" Nick Name: activate to sort column ascending"
											style="width: 110px;"><i
											class="fa fa-fw fa-user txt-color-blue text-muted hidden-md hidden-sm hidden-xs"></i>
											Last Name </th>
										<th class="sorting" tabindex="0" aria-controls="dt_basic"
											rowspan="1" colspan="1"
											aria-label="Email: activate to sort column ascending"
											style="width: 110px;"><i
											class="fa fa-fw fa-user txt-color-blue text-muted hidden-md hidden-sm hidden-xs"></i>
											Nick Name </th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div> -->
						
						
					</div>
					<!-- end widget content -->
				</div>
				<!-- end widget div -->
			</div>
			<!-- Till Here -->
		</article>
		<!-- WIDGET END -->
	</div>
	<!-- end row -->
	<!-- end row -->
</section>
<!-- end widget grid -->

<!-- END #MAIN CONTENT -->


<!-- start of new tab create -->
<script type="text/javascript">

var clicked = true;

function newTabCreation()
{   
   if(clicked)
   {
	  console.log('1st tab');
     clicked = false;
     return new1stTabDefaultCreation();
   }
  else
  {
     console.log('2nd tab onward'); 
     clicked = false;
     return new2ndTabOnwardCreation();
  }
}


function new1stTabDefaultCreation() {
	 $("#mainTabDiv").removeClass('hide'); 
	
	//its for li creation
	var itemDetailUL = document.getElementById("itemDetailUL");
	
	var item1stLI = $("<li>")
	.attr(
			{
				'class' : "active"
			});
	var anchor1stA = $("<a>1st tab</a>")
	.attr(
			{
				'href' : "#s1",
				'data-toggle': "tab"
			});	
	var icon1stI = $("<i class ='fa fa-fw fa-lg fa-font'></i>");
	
		
	icon1stI.appendTo(anchor1stA);   
	anchor1stA.appendTo(item1stLI);
	item1stLI.appendTo(itemDetailUL);

	//its for li tab creation
	var itemTabContent = document.getElementById("itemTabContent");
	
	var item1stTabDIV = $("<Div>")
	.attr(
			{
				'class' : "tab-pane active",
				'id' : "s1"
			});
	var heading1stH2 = $("<h2>1st tab content</h2>");
	
	
	heading1stH2.appendTo(item1stTabDIV);
	item1stTabDIV.appendTo(itemTabContent);
};


function new2ndTabOnwardCreation() {
	
	//its for li creation
	var itemDetailUL = document.getElementById("itemDetailUL");
	
	var item2onwardsLI = $("<li>")
	.attr(
			{
				'class' : ""
			});
	var anchor2onwardsA = $("<a>2nd tab</a>")
	.attr(
			{
				'href' : "#s2",
				'data-toggle': "tab"
			});	
	var icon2onwardsI = $("<i class ='fa fa-fw fa-lg fa-book'></i>");
	
		
	icon2onwardsI.appendTo(anchor2onwardsA);   
	anchor2onwardsA.appendTo(item2onwardsLI);
	item2onwardsLI.appendTo(itemDetailUL);

	//its for li tab creation
	var itemTabContent = document.getElementById("itemTabContent");
	
	var item2onwardsTabDIV = $("<Div>")
	.attr(
			{
				'class' : "tab-pane",
				'id' : "s2"
			});
	var heading2onwardsH2 = $("<h2>2nd tab content</h2>");
	
	
	heading2onwardsH2.appendTo(item2onwardsTabDIV);
	item2onwardsTabDIV.appendTo(itemTabContent);
}
</script>

<!-- end of new tab create -->	
