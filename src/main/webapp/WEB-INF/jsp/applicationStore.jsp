<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<head>
<script type="text/javascript" src="assets/js/applicationStore.js"></script>
<style>
.pricing-big .panel-title {
	font-size: 22px
}
* {
	margin: 0;
	padding: 0;
}
.section {
	text-align: center;
	padding-bottom: 20px;
	/*  min-height:100%; */
	width: 100%;
	/*  height:725px; */
}
#payementGatewayWidget {
	width: 100%;
	height: 580px;
}
.bg-color-teal {
	background-color: #ACCACA !important;
}
.pricing-big .panel-footer {
	background-color: white !important;
	border: 1px solid #ccc;
}
.price-features {
	min-height: 200px;
	border: 1px solid #EEE; 
	background-color: #ACCACA;
	border-top-right-radius: 6px;
    border-top-left-radius: 6px;
}
.panel-body,.panel-teal{
background: rgb(241, 241, 241);
}
</style>
</head>
<!-- <div style="position: absolute; top: 18px; right: 22px; z-index: 1;">
	
</div> -->

<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<!-- <i class="fa-fw fa fa-shopping-cart txt-color-blue"></i> -->
			<img src="./assets/img/hard_hat.png"
						alt="hard_hat" > <spring:message code="ApplicationStore.PageHead.Heading"/> <span>
				&gt; <spring:message code="ApplicationStore.PageHead.SubHeading"/> </span>
		</h1>
	</div>
	<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
		<h1 style="text-align: right; padding-right: 25px">
			<div class="btn-group" id="manageApplicationDiv">
				<a href="#applicationDetail" class="btn btn-sm btn-success"> <i
					class="fa fa-gear"></i> <spring:message
						code="ApplicationStore.PageHead.Link1" />
				</a>
			</div>
			<div class="btn-group">
				<a href="#openCart" data-toggle="modal"
					class="btn btn-sm btn-primary" id="totalRows"> <!-- <i class="fa fa-shopping-cart"></i> -->
					<img src="./assets/img/hard_hat.png" alt="hard_hat"
					style="width: 16px"> <spring:message
						code="ApplicationStore.PageHead.Link2" /> &nbsp<b
					class="badge bounceIn animated"></b>
				</a>
			</div>
		</h1>
	</div>
</div>

<div class="row row col-sm-12">
	<div class="well"
		style="text-align: center; padding-top: 5px; background-color: #ACCACA; border: 1px solid black">
		<h1 class="semi-bold">Our Products</h1>
		<p>It is an organizational lifecycle function within a company
			dealing with the planning, forecasting, and production, or marketing
			of a product or products at all stages of the product lifecycle. It
			integrates people, data, processes and business systems. It provides
			product information for companies and their extended supply chain
			enterprise.</p>
	</div>
</div>

<div class="row scroll col-md-12">
<div class="row">
	<div class="col-xs-12 col-sm-4 col-md-4">
		<div class="panel panel-teal pricing-big">
			<div class="panel-body no-padding text-align-center">
				<div class="price-features" >
					<img src="./assets/img/fmsLogo.png" alt="FMS Logo">
					<h4>Fixture Management System</h4>
				</div>
			</div>
			<div class="panel-footer text-align-center"
				style="background-color: #accaca !important;">
				<a class="btn-link btn-block btn1" href="#section1Detail">Learn
					<span> more</span>
				</a> <a class="btn btn-primary btn-block btn1" href="#section1Detail">View
					plans</a>
			</div>
		</div>
	</div>

	<div class="col-xs-12 col-sm-4 col-md-4">
		<div class="panel panel-teal  pricing-big">
			<div class="panel-body no-padding text-align-center">
				<div class="price-features">
					<img src="./assets/img/pdLogo.png" alt="PD Logo">
					<h4>Purchase Directive </h4>
				</div>
			</div>
			<div class="panel-footer text-align-center"
				style="background-color: #accaca !important;">
				<a class="btn-link btn-block btn1" href="#section2Detail">Learn
					<span> more</span>
				</a> <a class="btn btn-primary btn-block" href="#section2Detail"
					role="button">View plans </a>
			</div>
		</div>
	</div>

	<div class="col-xs-12 col-sm-4 col-md-4">
		<div class="panel panel-teal pricing-big">
			<div class="panel-body no-padding text-align-center">
				<div class="price-features">
					<img src="./assets/img/mprLogo.png" alt="MPR Logo">
					<h4>Monthly Projection Report</h4>
				</div>
			</div>
			<div class="panel-footer text-align-center"
				style="background-color: #accaca !important;">
				<a class="btn-link btn-block btn1" href="#section3Detail">Learn
					<span> more</span>
				</a> <a class="btn btn-primary btn-block" href="#section3Detail"
					role="button">View plans </a>
			</div>
		</div>
	</div>
</div>
</div>



<div class="row row col-sm-12">
	<div id="section1Detail"
		style="padding-top: 25px; <td>Unlimited users</td> text-align: center">
		<div class="well bg-color-teal">
			<h1 class="semi-bold ">Description of FTS ?</h1>
			<p>
				      A  solution to clients of Electricity infrastructure department by which they can easily manage their Project Details, Users Hierarchy, Fixture / Lamp requirement, tracking of order and warehouse management, Issue tracking, Change order / Request Management. 
				A supervisor/management can view  reports , summary in document as well as pictorially in form of charts, or download it directly in form of Excel or PDF
			</p>
			
			</p>
		</div>
	</div>
</div>
<div class="row section scroll" id="section1">
	<div class="col-md-12">
		<div class="alert alert-info alert-block">
			<h4 class="alert-heading">Plans for FTS!</h4>
		</div>
	</div>
	<div class="col-xs-12 col-sm-4 col-md-2">
		<div class="panel panel-blue">

			<div class="panel-heading">
				<h3 class="panel-title">Plan1</h3>
			</div>
			<div class="panel-body no-padding text-align-center">
				<div class="the-price">
					<h1>
						$100<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>1-5 no. of users</td>
						</tr>
						<tr class="active">
							<td>150MB Storage</td>
						</tr>
						<tr>
							<td>Weekly Reports</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="panel-footer no-padding">
				<a href="javascript:void(0);" class="btn bg-color-blue txt-color-white btn-block" onclick="addNewRow(1,'addToCart');">Add
					to Cart</a> <a href="javascript:void(0);"
					class="btn bg-color-blue txt-color-white btn-block" role="button" onclick="addNewRow(1,'buyNow');">Buy
					Now</a>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-4 col-md-2">
		<div class="panel panel-blue">
			<div class="panel-heading">
				<h3 class="panel-title">Plan2</h3>
			</div>
			<div class="panel-body no-padding text-align-center">
				<div class="the-price">
					<h1>
						$200<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>6-10 no. of users</td>
						</tr>
						<tr class="active">
							<td>200MB Storage</td>
						</tr>
						<tr>
							<td>Weekly Reports</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="panel-footer no-padding">
				<a href="javascript:void(0);" class="btn bg-color-blue txt-color-white btn-block" onclick="addNewRow(2,'addToCart');">Add
					to Cart</a> <a href="javascript:void(0);"
					class="btn bg-color-blue txt-color-white btn-block" role="button"  onclick="addNewRow(2,'buyNow');">Buy
					Now</a>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-4 col-md-2">
		<div class="panel panel-blue">
			<div class="panel-heading">
				<h3 class="panel-title">Plan3</h3>
			</div>
			<div class="panel-body no-padding text-align-center">
				<div class="the-price">
					<h1>
						$350<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>11-15 no. of users</td>
						</tr>
						<tr class="active">
							<td>500MB Storage</td>
						</tr>
						<tr>
							<td>Weekly Reports</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="panel-footer no-padding">
				<a href="javascript:void(0);" class="btn bg-color-blue txt-color-white btn-block" onclick="addNewRow(3,'addToCart');">Add
					to Cart</a> <a href="javascript:void(0);"
					class="btn bg-color-blue txt-color-white btn-block" role="button"  onclick="addNewRow(3,'buyNow');">Buy
					Now</a>
			</div>
		</div>
	</div>

	<div class="col-xs-12 col-sm-4 col-md-2">
		<div class="panel panel-blue">
			<div class="panel-heading">
				<h3 class="panel-title">Plan4</h3>
			</div>
			<div class="panel-body no-padding text-align-center">
				<div class="the-price">
					<h1>
						$1000<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>Unlimited users</td>
						</tr>
						<tr class="active">
							<td>Unlimited Storage</td>
						</tr>
						<tr>
							<td>Weekly Reports</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="panel-footer no-padding">
				<a href="javascript:void(0);" class="btn bg-color-blue txt-color-white btn-block" onclick="addNewRow(4,'addToCart');">Add
					to Cart</a> <a href="javascript:void(0);"
					class="btn bg-color-blue txt-color-white btn-block" role="button"  onclick="addNewRow(4,'buyNow');">Buy
					Now</a>
			</div>
		</div>
	</div>
</div>



<div id="section2Detail"
	style="padding-top: 25px; <td>Unlimited users</td> text-align: center">
	<div class="row col-sm-12">
		<div class="well bg-color-teal">
			<h1 class="semi-bold ">Description of PD ?</h1>
			<p>
				This application allows its users to create PDs/FDs,an estimated bill of the items in a order placed by a construction company that undergoes through an approval process among the hierarchy in the organization before actually placing the order.
				It stores and tracks these PDs/FDs throughout the process and a reciept is generated once the order is placed.
			</p>
			
		</div>
	</div>
</div>

<div class="row section scroll" id="section2">
	<div class="col-md-12">
		<div class="alert alert-info alert-block">
			<h4 class="alert-heading">Plans for PD!</h4>
		</div>
	</div>
	<div class="col-xs-12 col-sm-4 col-md-2">
		<div class="panel panel-blue">

			<div class="panel-heading">
				<h3 class="panel-title">Plan1</h3>
			</div>
			<div class="panel-body no-padding text-align-center">
				<div class="the-price">
					<h1>
						$100<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>1-5 no. of users</td>
						</tr>
						<tr class="active">
							<td>150MB Storage</td>
						</tr>
						<tr>
							<td>Weekly Reports</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="panel-footer no-padding">
				<a href="javascript:void(0);" class="btn bg-color-blue txt-color-white btn-block" onclick="addNewRow(5,'addToCart');">Add
					to Cart</a> <a href="javascript:void(0);"
					class="btn bg-color-blue txt-color-white btn-block" role="button"  onclick="addNewRow(5,'buyNow');">Buy
					Now</a>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-4 col-md-2">
		<div class="panel panel-blue">
			<div class="panel-heading">
				<h3 class="panel-title">Plan2</h3>
			</div>
			<div class="panel-body no-padding text-align-center">
				<div class="the-price">
					<h1>
						$200<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>6-10 no. of users</td>
						</tr>
						<tr class="active">
							<td>200MB Storage</td>
						</tr>
						<tr>
							<td>Weekly Reports</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="panel-footer no-padding">
				<a href="javascript:void(0);" class="btn bg-color-blue txt-color-white btn-block" onclick="addNewRow(6,'addToCart');">Add
					to Cart</a> <a href="javascript:void(0);"
					class="btn bg-color-blue txt-color-white btn-block" role="button"  onclick="addNewRow(6,'buyNow');">Buy
					Now</a>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-4 col-md-2">
		<div class="panel panel-blue">
			<div class="panel-heading">
				<h3 class="panel-title">Plan3</h3>
			</div>
			<div class="panel-body no-padding text-align-center">
				<div class="the-price">
					<h1>
						$350<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>11-15 no. of users</td>
						</tr>
						<tr class="active">
							<td>500MB Storage</td>
						</tr>
						<tr>
							<td>Weekly Reports</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="panel-footer no-padding">
				<a href="javascript:void(0);" class="btn bg-color-blue txt-color-white btn-block" onclick="addNewRow(7,'addToCart');">Add
					to Cart</a> <a href="javascript:void(0);"
					class="btn bg-color-blue txt-color-white btn-block" role="button"  onclick="addNewRow(7,'buyNow');">Buy
					Now</a>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-4 col-md-2">
		<div class="panel panel-blue">
			<div class="panel-heading">
				<h3 class="panel-title">Plan4</h3>
			</div>
			<div class="panel-body no-padding text-align-center">
				<div class="the-price">
					<h1>
						$500<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>16-20 no. of users</td>
						</tr>
						<tr class="active">
							<td>500MB Storage</td>
						</tr>
						<tr>
							<td>Weekly Reports</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="panel-footer no-padding">
				<a href="javascript:void(0);" class="btn bg-color-blue txt-color-white btn-block" onclick="addNewRow(8,'addToCart');">Add
					to Cart</a> <a href="javascript:void(0);" 
					class="btn bg-color-blue txt-color-white btn-block" role="button"  onclick="addNewRow(8,'buyNow');">Buy
					Now</a>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-4 col-md-2">
		<div class="panel panel-blue">
			<div class="panel-heading">
				<h3 class="panel-title">Plan5</h3>
			</div>
			<div class="panel-body no-padding text-align-center">
				<div class="the-price">
					<h1>
						$1000<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>Unlimited users</td>
						</tr>
						<tr class="active">
							<td>1000MB Storage</td>
						</tr>
						<tr>
							<td>Weekly Reports</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="panel-footer no-padding">
				<a href="javascript:void(0);" class="btn bg-color-blue txt-color-white btn-block" onclick="addNewRow(9,'addToCart');">Add
					to Cart</a> <a href="javascript:void(0);"
					class="btn bg-color-blue txt-color-white btn-block" role="button"  onclick="addNewRow(9,'buyNow');">Buy
					Now</a>
			</div>
		</div>
	</div>
</div>



<div id="section3Detail"
	style="padding-top: 25px; <td>Unlimited users</td> text-align: center">
	<div class="row col-sm-12">
		<div class="well bg-color-teal">
			<h1 class="semi-bold ">Description of MPR ?</h1>
			<p>
				This application help its clients
to manage their Projects , Manpower and overall Cash Flow  details with ease
Once the details are managed the users of the application can view  Monthly Projection Report, Report Summary in document as well as pictorially in form of charts.
			</p>
		</div>
	</div>
</div>
<div class="row section scroll" id="section3">
	<div class="col-md-12">
		<div class="alert alert-info alert-block">
			<h4 class="alert-heading">Plans for MPR!</h4>
		</div>
	</div>
	<div class="col-xs-12 col-sm-4 col-md-2">
		<div class="panel panel-blue">

			<div class="panel-heading">
				<h3 class="panel-title">Plan1</h3>
			</div>
			<div class="panel-body no-padding text-align-center">
				<div class="the-price">
					<h1>
						$100<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>1-5 no. of users</td>
						</tr>
						<tr class="active">
							<td>150MB Storage</td>
						</tr>
						<tr>
							<td>Weekly Reports</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="panel-footer no-padding">
				<a href="javascript:void(0);" class="btn bg-color-blue txt-color-white btn-block" onclick="addNewRow(10,'addToCart');">Add
					to Cart</a> <a href="javascript:void(0);"
					class="btn bg-color-blue txt-color-white btn-block" role="button" onclick="addNewRow(10,'buyNow');">Buy
					Now</a>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-4 col-md-2">
		<div class="panel panel-blue">
			<div class="panel-heading">
				<h3 class="panel-title">Plan2</h3>
			</div>
			<div class="panel-body no-padding text-align-center">
				<div class="the-price">
					<h1>
						$200<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>6-10 no. of users</td>
						</tr>
						<tr class="active">
							<td>200MB Storage</td>
						</tr>
						<tr>
							<td>Weekly Reports</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="panel-footer no-padding">
				<a href="javascript:void(0);" class="btn bg-color-blue txt-color-white btn-block" onclick="addNewRow(11,'addToCart');">Add
					to Cart</a> <a href="javascript:void(0);"
					class="btn bg-color-blue txt-color-white btn-block" role="button" onclick="addNewRow(11,'buyNow');">Buy
					Now</a>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-4 col-md-2">
		<div class="panel panel-blue">
			<div class="panel-heading">
				<h3 class="panel-title">Plan3</h3>
			</div>
			<div class="panel-body no-padding text-align-center">
				<div class="the-price">
					<h1>
						$350<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>11-15 no. of users</td>
						</tr>
						<tr class="active">
							<td>500MB Storage</td>
						</tr>
						<tr>
							<td>Weekly Reports</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="panel-footer no-padding">
				<a href="javascript:void(0);" class="btn bg-color-blue txt-color-white btn-block" onclick="addNewRow(12,'addToCart');">Add
					to Cart</a> <a href="javascript:void(0);"
					class="btn bg-color-blue txt-color-white btn-block" role="button" onclick="addNewRow(12,'buyNow');">Buy
					Now</a>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-4 col-md-2">
		<div class="panel panel-blue">
			<div class="panel-heading">
				<h3 class="panel-title">Plan4</h3>
			</div>
			<div class="panel-body no-padding text-align-center">
				<div class="the-price">
					<h1>
						$500<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>16-20 no. of users</td>
						</tr>
						<tr class="active">
							<td>500MB Storage</td>
						</tr>
						<tr>
							<td>Weekly Reports</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="panel-footer no-padding">
				<a href="javascript:void(0);" class="btn bg-color-blue txt-color-white btn-block" onclick="addNewRow(13,'addToCart');">Add
					to Cart</a> <a href="javascript:void(0);"
					class="btn bg-color-blue txt-color-white btn-block" role="button" onclick="addNewRow(13,'buyNow');">Buy
					Now</a>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-4 col-md-2">
		<div class="panel panel-blue">
			<div class="panel-heading">
				<h3 class="panel-title">Plan5</h3>
			</div>
			<div class="panel-body no-padding text-align-center">
				<div class="the-price">
					<h1>
						$700<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>21-25 no. of users</td>
						</tr>
						<tr class="active">
							<td>1000MB Storage</td>
						</tr>
						<tr>
							<td>Weekly Reports</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="panel-footer no-padding">
				<a href="javascript:void(0);" class="btn bg-color-blue txt-color-white btn-block" onclick="addNewRow(14,'addToCart');">Add
					to Cart</a> <a href="javascript:void(0);"
					class="btn bg-color-blue txt-color-white btn-block" role="button" onclick="addNewRow(14,'buyNow');">Buy
					Now</a>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-4 col-md-2">
		<div class="panel panel-blue">
			<div class="panel-heading">
				<h3 class="panel-title">Plan6</h3>
			</div>
			<div class="panel-body no-padding text-align-center">
				<div class="the-price">
					<h1>
						$1000<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>Unlimited users</td>
						</tr>
						<tr class="active">
							<td>Unlimited Storage</td>
						</tr>
						<tr>
							<td>Weekly Reports</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="panel-footer no-padding">
				<a href="javascript:void(0);" class="btn bg-color-blue txt-color-white btn-block" onclick="addNewRow(15,'addToCart');">Add
					to Cart</a> <a href="javascript:void(0);"
					class="btn bg-color-blue txt-color-white btn-block" role="button" onclick="addNewRow(15,'buyNow');">Buy
					Now</a>
			</div>
		</div>
	</div>
</div>



<!-- <div class="alert alert-info alert-block" id="payementGateway">
	<h4 class="alert-heading">Payement Gateway</h4>
</div> -->
<!-- <div role="content">
	widget edit box
	<div class="jarviswidget-editbox">
		This area used as dropdown edit box
	</div>
	widget content
	<div class="widget-body" id="payementGatewayWidget">

		<div class="tabs-left">
			<ul class="nav nav-tabs tabs-left" id="demo-pill-nav"
				style="padding: 30px;">
				<li class="active"><a href="#tab-r1" data-toggle="tab">
						Card </a></li>
				<li><a href="#tab-r2" data-toggle="tab"> Net Banking</a></li>
				<li><a href="#tab-r3" data-toggle="tab"> Pay Pal</a></li>
				<li><a href="#tab-r4" data-toggle="tab"> Promo Code</a></li>
			</ul>
			<div class="tab-content"
				style="padding-right: 90px; padding-left: 90px">
				<div class="tab-pane active" id="tab-r1">
					<form action="" id="checkout-form" class="smart-form"
						novalidate="novalidate">
						<fieldset>
							<section>
								<div class="inline-group">
									<label class="radio"> <input type="radio"
										name="radio-inline" checked=""> <i></i>Visa
									</label> <label class="radio"> <input type="radio"
										name="radio-inline"> <i></i>MasterCard
									</label> <label class="radio"> <input type="radio"
										name="radio-inline"> <i></i>American Express
									</label>
								</div>
							</section>

							<section>
								<label class="input"> <input type="text" name="name"
									placeholder="Name on card">
								</label>
							</section>

							<div class="row">
								<section class="col col-10">
									<label class="input"> <input type="text" name="card"
										placeholder="Card number" data-mask="9999-9999-9999-9999">
									</label>
								</section>
								<section class="col col-2">
									<label class="input"> <input type="text" name="cvv"
										placeholder="CVV2" data-mask="999">
									</label>
								</section>
							</div>

							<div class="row">
								<label class="label col col-4">Expiration date</label>
								<section class="col col-5">
									<label class="select"> <select name="month">
											<option value="0" selected="" disabled="">Month</option>
											<option value="1">January</option>
											<option value="1">February</option>
											<option value="3">March</option>
											<option value="4">April</option>
											<option value="5">May</option>
											<option value="6">June</option>
											<option value="7">July</option>
											<option value="8">August</option>
											<option value="9">September</option>
											<option value="10">October</option>
											<option value="11">November</option>
											<option value="12">December</option>
									</select> <i></i>
									</label>
								</section>
								<section class="col col-3">
									<label class="input"> <input type="text" name="year"
										placeholder="Year" data-mask="2099">
									</label>
								</section>
							</div>
							<footer>
								<button type="submit" class="btn btn-primary">Pay Now</button>
							</footer>
						</fieldset>


					</form>
				</div>
				<div class="tab-pane" id="tab-r2">
					<form action="" id="checkout-form" class="smart-form"
						novalidate="novalidate">
						<fieldset>
							<div class="row">
								<label class="label col col-2">Bank Name</label>
								<section class="col col-10">
									<label class="select"> <select name="bank">
											<option value="0" selected="" disabled="">Bank name</option>
											<option value="1">Bank 1</option>
											<option value="1">Bank 2</option>
											<option value="3">Bank 3</option>
											<option value="4">Bank 4</option>
									</select> <i></i>
									</label>
								</section>
							</div>
							<footer>
								<button type="submit" class="btn btn-primary">Pay Now</button>
							</footer>
						</fieldset>
					</form>
				</div>

				<div class="tab-pane" id="tab-r3">
					<form action="" id="checkout-form" class="smart-form"
						novalidate="novalidate">
						<fieldset>
							<section>
								<label class="input"> <i
									class="icon-append fa fa-envelope-o"></i> <input type="email"
									name="email" placeholder="Email address.."> <b
									class="tooltip tooltip-bottom-right">Needed to verify your
										account</b>
								</label>
							</section>

							<section>
								<label class="input"> <i class="icon-append fa fa-lock"></i>
									<input type="password" name="password" placeholder="Password.."
									id="password"> <b class="tooltip tooltip-bottom-right">Don't
										forget your password</b>
								</label>
							</section>

							<footer>
								<button type="submit" class="btn btn-primary">Pay Now</button>
							</footer>
						</fieldset>
					</form>
				</div>
				<div class="tab-pane" id="tab-r4">
					<form action="" id="checkout-form" class="smart-form"
						novalidate="novalidate">
						<fieldset>
							<div class="row">
								<label class="label col col-2">Promo Code</label>
								<section class="col col-10">
									<label class="input"> <input type="text" name="name"
										placeholder="promo code">
									</label>
								</section>
							</div>
							<footer>
								<button type="submit" class="btn btn-primary">Go</button>
							</footer>
						</fieldset>
					</form>
				</div>
			</div>
		</div>

	</div>
	end widget content

</div> -->

<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
	<!-- Button trigger modal -->
	<!-- MODAL PLACE HOLDER -->
	
	<input type="hidden" id="selectedPlans" name="selectedPlans">
	<input type="hidden" id="promoCode" name="promoCode">
	<div class="modal fade" id="openCart" tabindex="-1" role="dialog"
		aria-labelledby="remoteModalLabel" aria-hidden="true"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						<span class="badge pull-right inbox-badge customColor">&times;</span>
					</button>
					<h4 class="modal-title">
						<!-- <i class="fa fa-shopping-cart txt-color-blue"></i> -->
						<img src="./assets/img/hard_hat.png"
						alt="hard_hat" >
						&nbsp;Subscription Cart
					</h4>
				</div>
				<div class="modal-body no-padding" style="padding-top: 4px !important">
                     <div class="alert alert-block alert-info" id="cartMessage">
						<h4 class="alert-heading">
						The cart is empty!!!!
							
						</h4>
						
                      </div>
					<table class="table table-hover" id="subscriptionCartTable">
						<thead>
							<tr>
								<th class="text-center">APP</th>
								<th class="text-center">PLAN</th>
								<th class="text-center">DESCRIPTION</th>
								<th class="text-center">PRICE</th>
								<th class="text-center"></th>
							</tr>
						</thead>
						<tbody>
							<tr id="totalSumOfPlans">
								
							</tr>

							
							<tr id="totalAmountRow">
								<td colspan="3" style="text-align: right;" class="text-success"><h6>
										<strong>Total Amount after discount :</strong>
									</h6></td>
								<td colspan="2" id="totalSum"
									style="vertical-align: middle; text-align: left; padding-left: 35px; font-weight: bold;"></td>
							</tr>
							<tr>
								<td colspan="5"><footer style="padding: 10px 0;">

										<button type="button" class="btn btn-default pull-right"
											data-dismiss="modal">Cancel</button>
										<!-- <form id="formData" name="formData" action="getCookieData" method="POST">
											&nbsp;Cancel</button>
											<input type="hidden" id="tableData" name="tableData"> -->
										<button class="btn btn-success pull-right" type="button"
											id="sendPage" style="margin: 0 10px"
											onclick="saveAllRecords();">
											<!-- <i class="fa fa-shopping-cart"></i> -->
											<img src="./assets/img/hard_hat.png" alt="hard_hat"
												style="width: 16px">&nbsp;Express Checkout
										</button>
										<!--  </a></form> -->
										<!-- <button class="btn btn-success pull-right" type="button"
											id="expresscheckout">
											<i class="fa fa-paper-plane"></i>&nbsp;Express Checkout
										</button> -->
									</footer></td>
							</tr>
						</tbody>
					</table>

				</div>
			</div>
		</div>
		
	</div>
	<!-- END MODAL -->
	
</div>


<div class="btn-header transparent scroll scrollup"
	style="position: fixed; top: 640px; right: 6.5px;">
	<span> <a href="#mainDiv" title="top" id="btn-scroll-up"
		class="btn-scroll-up btn btn-sm btn-inverse display"
		style="background: black; color: white"> <i
			class="fa fa-angle-double-up" style="cursor: pointer;"></i></a>
	</span>
</div>

<script>

</script>

<script>
	$(document).ready(function() {
		$(window).scroll(function() {
			if ($(this).scrollTop() > 100) {
				$('.scrollup').fadeIn();
			} else {
				$('.scrollup').fadeOut();
			}
		});
		//pageAuthorization();
		 
	});
	/* adding by hari later to be changed */
	//function pageAuthorization(){
		//$.ajax({
			//url : "pageAuthorization",
			//type : "POST",
			//success : function(result){
				//result = JSON.parse(result);
				//console.log(result);
				//if(result.istempuser == "true"){
					//$("#manageApplicationDiv").click(function(e){e.preventDefault()});
					//$("#dashboardId").click(function(e){return false});
					//$("#manageAccountId").click(function(e){return false});
					//$("#applicationInvoiceId").click(function(e){return false});
					//$("#projectId").click(function(e){return false});
					//$("#manageUserId").click(function(e){return false});
					//$("#customerDirectoryId").click(function(e){return false});
					//$("#vendorDirectoryId").click(function(e){return false});
					//$("#contractDirectoryId").click(function(e){return false});
					//$("#typesOfProjectId").click(function(e){return false});
					//$("#departmentDetailsId").click(function(e){return false});
					//$("#itemDetailId").click(function(e){return false});
					//$("#budgetFormId").click(function(e){return false});
					//$("#show-shortcut").click(function(e){return false});
					//document.getElementById("nameId").innerHTML = result.userDetails.userName;
					//console.log(result.userDetails.userName);
				//}else{
					//$("#manageApplicationDiv").unbind('click');
					//$("#dashboardId").unbind('click');
					//$("#manageAccountId").unbind('click');
					//$("#applicationInvoiceId").unbind('click');
					//$("#projectId").unbind('click');
					//$("#manageUserId").unbind('click');
					//$("#customerDirectoryId").unbind('click');
					//$("#vendorDirectoryId").unbind('click');
					//$("#contractDirectoryId").unbind('click');
					//$("#typesOfProjectId").unbind('click');
					//$("#departmentDetailsId").unbind('click');
					//$("#itemDetailId").unbind('click');
					//$("#budgetFormId").unbind('click');
					//$("#show-shortcut").unbind('click');
					//document.getElementById("nameId").innerHTML = result.userDetails.firstName;
					//console.log(result.userDetails[0].firstName);
				//}
			//},
			//error : function(result){
				/*  console.log(result.message);*/
				//console.log("error");
			//}
		//});
	//}
</script>
<script>
$("#sendPage").click(function(){
	$('#openCart').modal('hide');
});
</script>
