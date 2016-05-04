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
<div style="position: absolute; top: 18px; right: 22px; z-index: 1;">
	<div class="btn-group" id="manageApplicationDiv">
		<a href="#applicationDetail" class="btn btn-sm btn-success"> <i
			class="fa fa-gear"></i> Manage Application
		</a>
	</div>
	<div class="btn-group">
		<a href="#openCart" data-toggle="modal" class="btn btn-sm btn-primary" id="totalRows">
			<!-- <i class="fa fa-shopping-cart"></i> -->
			<img src="./assets/img/hard_hat.png"
						alt="hard_hat" style="width:16px"> Cart &nbsp<b
			class="badge bounceIn animated"></b>
		</a>
	</div>
</div>

<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<!-- <i class="fa-fw fa fa-shopping-cart txt-color-blue"></i> -->
			<img src="./assets/img/hard_hat.png"
						alt="hard_hat" > App Store <span>
				&gt; My Apps </span>
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

	<div class="col-xs-12 col-sm-4 col-md-4">
		<div class="panel panel-teal pricing-big">
			<div class="panel-body no-padding text-align-center">
				<div class="price-features">
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



<div class="row row col-sm-12">
	<div id="section1Detail"
		style="padding-top: 25px; height: 255px; text-align: center">
		<div class="well bg-color-teal">
			<h1 class="semi-bold ">Description of FTS ?</h1>
			<p>
				SmartAdmin comes with a fully customized grid system catered
				specifically for building form layouts. Its not technically "better"
				than the bootstrap 3 built in grid system, but rather more
				simplified for rapid form layout and faster development. Idealy you
				would use either the <strong>bootstrap</strong> grid or the <strong>smart-form</strong>
				grid, when building your form layouts. It is important not to mix
				elements from two seperate classes as it can cause conflict.
			</p>
			<p>
				You can explore some of the <a title="Go to Bootstrap Forms">layouts</a>
				made using Smart Forms grid system or if you are not comfortable
				with this grid system you can simply stick to the default <a>Bootstrap
					forms</a>
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
						$10<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr>
							<td>1 Project</td>
						</tr>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>10 no: of users</td>
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
						$20<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr>
							<td>1 Project</td>
						</tr>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>10 no: of users</td>
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
						$35<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr>
							<td>1 Project</td>
						</tr>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>10 no: of users</td>
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
				<h3 class="panel-title">Plan6</h3>
			</div>
			<div class="panel-body no-padding text-align-center">
				<div class="the-price">
					<h1>
						$99<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr>
							<td>1 Project</td>
						</tr>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>10 no: of users</td>
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
	style="padding-top: 25px; height: 255px; text-align: center">
	<div class="row col-sm-12">
		<div class="well bg-color-teal">
			<h1 class="semi-bold ">Description of PD ?</h1>
			<p>
				SmartAdmin comes with a fully customized grid system catered
				specifically for building form layouts. Its not technically "better"
				than the bootstrap 3 built in grid system, but rather more
				simplified for rapid form layout and faster development. Idealy you
				would use either the <strong>bootstrap</strong> grid or the <strong>smart-form</strong>
				grid, when building your form layouts. It is important not to mix
				elements from two seperate classes as it can cause conflict.
			</p>
			<p>
				You can explore some of the <a title="Go to Bootstrap Forms">layouts</a>
				made using Smart Forms grid system or if you are not comfortable
				with this grid system you can simply stick to the default <a>Bootstrap
					forms</a>
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
						$10<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr>
							<td>1 Project</td>
						</tr>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>10 no: of users</td>
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
						$20<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr>
							<td>1 Project</td>
						</tr>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>10 no: of users</td>
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
						$35<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr>
							<td>1 Project</td>
						</tr>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>10 no: of users</td>
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
						$50<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr>
							<td>1 Project</td>
						</tr>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>15 no: of users</td>
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
						$70<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr>
							<td>1 Project</td>
						</tr>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>15 no: of users</td>
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
	style="padding-top: 25px; height: 255px; text-align: center">
	<div class="row col-sm-12">
		<div class="well bg-color-teal">
			<h1 class="semi-bold ">Description of MPR</h1>
			<p>
				SmartAdmin comes with a fully customized grid system catered
				specifically for building form layouts. Its not technically "better"
				than the bootstrap 3 built in grid system, but rather more
				simplified for rapid form layout and faster development. Idealy you
				would use either the <strong>bootstrap</strong> grid or the <strong>smart-form</strong>
				grid, when building your form layouts. It is important not to mix
				elements from two seperate classes as it can cause conflict.
			</p>
			<p>
				You can explore some of the <a title="Go to Bootstrap Forms">layouts</a>
				made using Smart Forms grid system or if you are not comfortable
				with this grid system you can simply stick to the default <a>Bootstrap
					forms</a>
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
						$10<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr>
							<td>1 Project</td>
						</tr>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>10 no: of users</td>
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
						$20<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr>
							<td>1 Project</td>
						</tr>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>10 no: of users</td>
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
						$35<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr>
							<td>1 Project</td>
						</tr>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>10 no: of users</td>
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
						$50<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr>
							<td>1 Project</td>
						</tr>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>15 no: of users</td>
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
						$70<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr>
							<td>1 Project</td>
						</tr>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>15 no: of users</td>
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
						$99<span class="subscript">/mo</span>
					</h1>
					<small>1 month FREE trial</small>
				</div>
				<table class="table">
					<tbody>
						<tr>
							<td>1 Project</td>
						</tr>
						<tr class="active">
							<td>Data Usage</td>
						</tr>
						<tr>
							<td>10 no: of users</td>
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

<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
	<!-- Button trigger modal -->
	<!-- MODAL PLACE HOLDER -->
	<form id="expressCheckoutForm" method="POST" action="saveCookieData">
	<input type="hidden" id="selectedPlans" name="selectedPlans">
	<input type="hidden" id="promoCode" name="promoCode">
	<div class="modal fade" id="openCart" tabindex="-1" role="dialog"
		aria-labelledby="remoteModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						<span class="badge pull-right inbox-badge customColor">&times;</span>
					</button>
					<h4 class="modal-title"><!-- 
						<i class="fa fa-shopping-cart txt-color-blue"></i> -->
						<img src="./assets/img/hard_hat.png"
						alt="hard_hat" >
						&nbsp;Subscription Cart
					</h4>
				</div>
				<div class="modal-body no-padding">

					<table class="table table-hover" id="subscriptionCartTable">
						<thead>
							<tr>
								<th class="text-center">APP</th>
								<th>PLAN</th>
								<th>DESCRIPTION</th>
								<th>PRICE</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							
							<tr id="totalSumOfPlans">
											<td colspan="3" style="text-align:left; vertical-align: middle;"><strong>Total Amount :</strong></td>
											<td colspan="5" id="Sum"><h4><strong><span></span></strong></h4></td>
										</tr>
										
										<tr  id="discountedAmount">
											<td colspan="2" style="text-align:right; vertical-align: middle;">
											<fieldset>
												<section>
													<label class="input">
														<input type="text" name="promoC" id="promoC" placeholder="PROMO CODE.." style="border-radius: 5px; border: 1px solid #ccc;padding: 5px;">
													</label>
												</section>
											</fieldset>	
											</td>
											<td colspan="6" style="text-align:right; vertical-align: middle;">Your Total Savings <label>$5.50</label></td>
										</tr>
										
										<tr  id="totalAmountRow">
											<td colspan="3" style="text-align:left; vertical-align: middle;" class="text-success"><h5><strong>Total Amount after discount :</strong></h5></td>
											<td colspan="5" id="totalSum" style="vertical-align: middle;"><h4><strong><span class="text-success"></span></strong></h4></td>
										</tr>
							<tr>
								<td colspan="5"><footer
										style="padding: 10px 0;;">

										<button type="button" class="btn btn-default pull-right"
											data-dismiss="modal">
											&nbsp;Cancel</button>
											<!-- <form id="formData" name="formData" action="getCookieData" method="POST">
											<input type="hidden" id="tableData" name="tableData"> -->
											<button class="btn btn-success pull-right" type="button" id="send" style="margin: 0 10px"  onclick="saveAllRecords();"> 
											<!-- <i class="fa fa-shopping-cart"></i> -->
											<img src="./assets/img/hard_hat.png"
						alt="hard_hat" style="width:16px"> &nbsp;Express Checkout
										</button>   <!--  </a></form> -->
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
	</form>
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
</script>
<script>
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
$("#projectType").css({'pointer-events':'none','color':'rgba(168, 168, 168, 0.35)'});
$("#deptDetails").css({'pointer-events':'none','color':'rgba(168, 168, 168, 0.35)'});
$("#maalDetails").css({'pointer-events':'none','color':'rgba(168, 168, 168, 0.35)'});
$("#budgtForm").css({'pointer-events':'none','color':'rgba(168, 168, 168, 0.35)'});
$("#appStore").removeAttr("href").css({'cursor':'pointer','color':'#fff'});
$( '#appStore' ).click( function( e ) {
    e.preventDefault();
    return false;
} );

//$("#accountSettingId").css("pointer-events", "none");
</script>
