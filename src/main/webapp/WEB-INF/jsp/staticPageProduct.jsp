<!DOCTYPE html>
<html lang="en">

<head>
<title>Ecosystem</title>
<link rel="shortcut icon" href="assets/img/favicon.ico" type="image/icon">    

<style>
.input-group-addon {
border-radius: 0;
background-color: #7093A1;
border-color: #7093A1;
color: #fff;
}

.carousel-razon {
background-color: #0099da;
background-color: rgba(122, 147, 158, 0.9);
}
.carousel-control .glyphicon-chevron-left  
{
	left:22%;
}
.carousel-control .glyphicon-chevron-right
{
	right:22%
	}
.carousel-list li {
font-size: 1.2em;
}
.customColor
{
	color:red;
}
.hoverRemove:hover{
	 background:#fff !important;
	 }
	
/* this fixed css is not working here ,i put in staticHeadetFooter.jsp ,THEN ITS WORKING*/ 
/* ITS FOR heder fixed at certain height */	
/* 	.fixed{
	position: fixed; 
	top: 0; 
	z-index: 100;
	width:100%;
} */
.backgroundColor
{
 background:#fff;
 color:black:
}
    </style>
</head>

<!-- Preloader -->


<body style="overflow: visible;">
<div id="preloader" style="display: none;">
    <div id="status" style="display: none;">&nbsp;</div>
</div>

	<div id="sb-site">
		<div class="boxed">



			<%@ include file="./staticHeaderFooter.jsp"%>


			<header class="main-header">
				<div class="container">
					<h1 class="page-title">Products</h1>
				</div>
			</header>

			<div class="row">
				<!-- <div class="col-xs-12" style="padding-bottom: 30px;padding-left:77px;padding-right:77px;">
					<nav class="navbar navbar-default" role="navigation" id="menu" style="background: #f7f7f7; border-color: #f7f7f7; border-radius: 0px;">

					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li  style="padding-left: 20PX;padding-right: 20PX; cursor:pointer;">
								<a class="hoverRemove" style="font-size: 20px;font-weight: 800;color: #5074B4;">FTS</a>
							</li>
							<li style="padding-left: 20PX;  padding-right: 20PX; cursor:pointer;">
								<a class="hoverRemove" style="font-size: 20px;  font-weight: 800;  color: #5074B4;">MPR</a>
							</li>
							<li style="padding-left: 20PX;  padding-right: 20PX; cursor:pointer;">
								<a class="hoverRemove" style="font-size: 20px;  font-weight: 800;  color: #5074B4;">PD</a>
							</li>
						</ul>
					</div>

				</nav>
				</div> -->
				<div class="col-xs-12">
            <p style="margin-bottom: 0px;" class="slogan text-center animated bounceInLeft animation-delay-5">Customized Application Development Cloud. SaaS. Mobile <span>creation</span>. <br> Our principles are <span>creativity</span>, <span>design</span>, <span>experience</span> and <span>knowledge</span>.</p>
        </div>
			</div>

			<div class="container" style="padding-top: 23px;">
				<nav
					class="navbar navbar-static-top navbar-default navbar-header-full navbar-dark navFixed"
					role="navigation" id="header"
					style="border-top: solid 1px #fff !important;">

					<div class="collapse navbar-collapse backgroundColor"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li
								style="margin-bottom: 0px !important;padding-left: 13px; padding-right: 25px; border-right: 2px solid #ccc; margin-bottom: 6px;"><a
								class="hoverRemove"
								style="padding: 0px;color: rgba(112, 86, 86, 0.64); font-weight: bold; letter-spacing: 5px; font-size: 25px; cursor:pointer;">
								<!-- MPR -->
								<img src="./assets/img/mprLogo.png" alt="MPR Logo" width="70px" style=""></a></li>
							<li
								style="margin-bottom: 0px !important;padding-left: 25px; padding-right: 25px; border-right: 2px solid #ccc;"><a class="hoverRemove"
								style="padding: 0px;color: rgba(112, 86, 86, 0.64); font-weight: bold; letter-spacing: 5px; font-size: 25px; cursor:pointer;">
								<!-- FTS -->
								<img src="./assets/img/fmsLogo.png" alt="FMS Logo" width="70px" style=""></a></li>
							<li
								style="margin-bottom: 0px !important;padding-left: 25px; padding-right: 25px; border-right: 2px solid #ccc;"><a class="hoverRemove"
								style="padding: 0px;color: rgba(112, 86, 86, 0.64); font-weight: bold; letter-spacing: 5px; font-size: 25px; cursor:pointer;">
								<!-- PD -->
								<img src="./assets/img/pdLogo.png" alt="PD Logo" width="70px" style="">
								</a></li>
						</ul>
					</div>

					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav" id="overviewContent">
							<li class="active"><a href="#" data-scroll="overview">Overview</a></li>
							<li class=""><a href="#" data-scroll="features" >Features</a></li>
							<li class=""><a href="#" data-scroll="pricing">Pricing</a></li>
						</ul>
						<ul class="nav navbar-nav pull-right">
							<li id="arrowUp" style="cursor:pointer;"><a ><i class="fa fa-arrow-up"
									style="font-size: 18px"></i></a></li>
						</ul>
					</div>

				</nav>
			</div>
			
			
			<div class="container wrapper">
			
				<section class="row" id="overview" data-anchor="overview">
					<div class="col-md-12">
						<h2 class="right-line">Our Services</h2>
					</div>
					<div class="col-md-7">
						<p>Iusto harum est illum consequatur sint accusamus illo error
							beatae sit officiis quidem eius aliquid hic quisquam minima
							ducimus fugiat, qui nihil quis amet. Nemo unde accusantium ipsam
							inventore repudiandae excepturi molestias sapiente amet officia
							laboriosam facilis consectetur blanditiis, aut, cumque
							temporibus, quam beatae. Alias qui sint, numquam accusantium
							necessitatibus!</p>
					</div>
					<div class="col-md-5">
						<h3>any image</h3>
					</div>
				</section>

				
				<section class="row" id="features" data-anchor="features">
				<div class="right-line"></div>
				<div class="right-line"></div>
					<div class="col-md-12">
						<h2 class="right-line">Our Features</h2>
					</div>
					<div class="col-md-4 col-sm-6">
						<div class="text-icon wow fadeInUp animated"
							style="visibility: visible; -webkit-animation: fadeInUp;">
							<span class="icon-ar icon-ar-lg"><i class="fa fa-cloud"></i></span>
							<div class="text-icon-content">
								<h3 class="no-margin">Track project</h3>
								<p>Praesentium cumque voluptate harum quae doloribus, atque
									error debitis, amet velit in similique, necessitatibus odit
									vero sunt.</p>
							</div>
						</div>
					</div>
					<div class="col-md-4 col-sm-6">
						<div class="text-icon wow fadeInUp animated"
							style="visibility: visible; -webkit-animation: fadeInUp;">
							<span class="icon-ar icon-ar-lg"><i class="fa fa-desktop"></i></span>
							<div class="text-icon-content">
								<h3 class="no-margin">Fast delivery</h3>
								<p>Praesentium cumque voluptate harum quae doloribus, atque
									error debitis, amet velit in similique, necessitatibus odit
									vero sunt.</p>
							</div>
						</div>
					</div>
					<div class="col-md-4 col-sm-6">
						<div class="text-icon wow fadeInUp animated"
							style="visibility: visible; -webkit-animation: fadeInUp;">
							<span class="icon-ar icon-ar-lg"><i class="fa fa-tablet"></i></span>
							<div class="text-icon-content">
								<h3 class="no-margin">Visualized data</h3>
								<p>Praesentium cumque voluptate harum quae doloribus, atque
									error debitis, amet velit in similique, necessitatibus odit
									vero sunt.</p>
							</div>
						</div>
					</div>
					<div class="col-md-4 col-sm-6">
						<div class="text-icon wow fadeInUp animated"
							style="visibility: visible; -webkit-animation: fadeInUp;">
							<span class="icon-ar icon-ar-lg"><i
								class="fa fa-wordpress"></i></span>
							<div class="text-icon-content">
								<h3 class="no-margin">Monitor Cashflow</h3>
								<p>Praesentium cumque voluptate harum quae doloribus, atque
									error debitis, amet velit in similique, necessitatibus odit
									vero sunt.</p>
							</div>
						</div>
					</div>
					<div class="col-md-4 col-sm-6">
						<div class="text-icon wow fadeInUp animated"
							style="visibility: visible; -webkit-animation: fadeInUp;">
							<span class="icon-ar icon-ar-lg"><i
								class="fa fa-graduation-cap"></i></span>
							<div class="text-icon-content">
								<h3 class="no-margin">Fast Order</h3>
								<p>Praesentium cumque voluptate harum quae doloribus, atque
									error debitis, amet velit in similique, necessitatibus odit
									vero sunt.</p>
							</div>
						</div>
					</div>
					<div class="col-md-4 col-sm-6">
						<div class="text-icon wow fadeInUp animated"
							style="visibility: visible; -webkit-animation: fadeInUp;">
							<span class="icon-ar icon-ar-lg"><i
								class="fa fa-paper-plane-o"></i></span>
							<div class="text-icon-content">
								<h3 class="no-margin">Customer service</h3>
								<p>Praesentium cumque voluptate harum quae doloribus, atque
									error debitis, amet velit in similique, necessitatibus odit
									vero sunt.</p>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<h3 class="section-title">Our Clients</h3>
					</div>
					<div class="col-md-6">
						<div class="bx-wrapper" style="max-width: 100%; margin: 0px auto;">
							<div class="bx-viewport"
								style="width: 100%; overflow: hidden; position: relative; height: 166px;">
								<ul class="bxslider" id="home-block" style="width: 615%;">
									<li
										style="float: left; list-style: none; position: relative; width: 555px;">
										<blockquote class="blockquote-color">
											<p>Lorem ipsum dolor sit amet, consectetur adipiscing
												elit. Integer posuere erat a ante ultricies nisi vel augue
												quam semper libero.</p>
											<footer>Brian Krzanich, Intel CEO</footer>
										</blockquote>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="bx-wrapper" style="max-width: 100%; margin: 0px auto;">
							<div class="bx-viewport"
								style="width: 100%; overflow: hidden; position: relative; height: 166px;">
								<ul class="bxslider" id="home-block" style="width: 615%;">
									<li
										style="float: left; list-style: none; position: relative; width: 555px;">
										<blockquote class="blockquote-color">
											<p>Lorem ipsum dolor sit amet, consectetur adipiscing
												elit. Integer posuere erat a ante ultricies nisi vel augue
												quam semper libero.</p>
											<footer>Brian Krzanich, Intel CEO</footer>
										</blockquote>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</section>

				
				<section class="row" style="height: 555px;" id="pricing" data-anchor="pricing">
					<div class="col-md-12">
						<h2 class="right-line">Our Pricing</h2>
					</div>
					<div class="col-md-4 col-sm-6">
						<div class="pricign-box animated fadeInDown animation-delay-7">
							<div class="pricing-box-header">
								<h2>MONTHLY</h2>
								<p>nisi anim mollit in labore ut esse</p>
							</div>
							<div class="pricing-box-price">
								<h3>
									$ 20 <sub>/ month</sub>
								</h3>
							</div>
							<div class="pricing-box-content">
								<ul>
									<li><i class="fa fa-inbox"></i> Unlimited experiments.</li>
									<li><i class="fa fa-cloud-download"></i> Web and Mobile
										Web.</li>
									<li><i class="fa fa-dashboard"></i> Dashboards.</li>
									<li><i class="fa fa-sitemap"></i> Essential features*.</li>
									<li><i class="fa fa-shopping-cart"></i>Customer service.</li>
								</ul>
							</div>
							<div class="pricing-box-footer">
								<a class="btn btn-ar btn-default dropdown">Get started</a>
							</div>
						</div>
					</div>
					<div class="col-md-4 col-sm-6">
						<div
							class="pricign-box pricign-box-pro animated fadeInDown animation-delay-9">
							<div class="pricing-box-header">
								<h2>QUARTELY</h2>
								<p>nisi anim mollit in labore ut esse</p>
							</div>
							<div class="pricing-box-price">
								<h3>
									$ 55 <sub>/ month</sub>
								</h3>
							</div>
							<div class="pricing-box-content">
								<ul>
									<li><i class="fa fa-inbox"></i> Unlimited experiments.</li>
									<li><i class="fa fa-cloud-download"></i> Web and Mobile
										Web.</li>
									<li><i class="fa fa-dashboard"></i> Dashboards.</li>
									<li><i class="fa fa-sitemap"></i> Essential features*.</li>
									<li><i class="fa fa-shopping-cart"></i>Customer service.</li>
								</ul>
							</div>
							<div class="pricing-box-footer">
								<a class="btn btn-ar btn-primary dropdown">Get started</a>
							</div>
						</div>
					</div>
					<div class="col-md-4 col-sm-6">
						<div class="pricign-box animated fadeInDown animation-delay-8">
							<div class="pricing-box-header">
								<h2>YEARLY</h2>
								<p>nisi anim mollit in labore ut esse</p>
							</div>
							<div class="pricing-box-price">
								<h3>
									$ 100 <sub>/ month</sub>
								</h3>
							</div>
							<div class="pricing-box-content">
								<ul>
									<li><i class="fa fa-inbox"></i> Unlimited experiments.</li>
									<li><i class="fa fa-cloud-download"></i> Web and Mobile
										Web.</li>
									<li><i class="fa fa-dashboard"></i> Dashboards.</li>
									<li><i class="fa fa-sitemap"></i> Essential features*.</li>
									<li><i class="fa fa-shopping-cart"></i>Customer service.</li>
								</ul>
							</div>
							<div class="pricing-box-footer">
								<a class="btn btn-ar btn-default dropdown">Get started</a>
							</div>
						</div>
					</div>
				</section>
			</div>
		</div>
	</div>




	<!-- sb-slidebar sb-right -->
<div id="back-top" style="display: none;">
    <a href="#header"><i class="fa fa-chevron-up"></i></a>
</div>


<script src="./assets/js/staticWEBjs/staticProduct.js"></script>
<!-- <script src="./assets/js/jquery1.8.3.js"></script> -->
<!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script> -->

<!-- <script>
   $(document).ready(function(){
	   $(window).bind('scroll', function() {
	   var navHeight = $( window ).height() - 347;
			 if ($(window).scrollTop() > navHeight) {
				 $('.navFixed').addClass('fixed');
			 }
			 else {
				 $('.navFixed').removeClass('fixed');
			 }
		});
	});
</script> -->
<!-- <script>
$(document).ready(function() {
	 $('.scrollTop').click(function() {
	      $('html, body').animate({scrollTop : 0});
	  });
	 });	
$(function() {
	  $('.scroll[href*=#]:not([href=#])').click(function() {
	    if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') && location.hostname == this.hostname) {

	      var target = $(this.hash);
	      target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
	      if (target.length) {
	        $('html,body').animate({
	          scrollTop: target.offset().top
	        }, 1000);
	        return false;
	      }
	    }
	  });
	});
</script> -->
<script>
 $('#overviewContent li a').on('click', function() {
    var scrollAnchor = $(this).attr('data-scroll'),
        scrollPoint = $('section[data-anchor="' + scrollAnchor + '"]').offset().top - 107;
    
    $('body,html').animate({
        scrollTop: scrollPoint
    }, 500);

    return false;

}) ;

$(window).scroll(function() {
    var windscroll = $(window).scrollTop();
    if (windscroll >= 364) {
        $('.navFixed').addClass('fixed');
        $('.wrapper section').each(function(i) {
            if ($(this).position().top <= windscroll + 115) {
                $('#overviewContent li.active').removeClass('active');
                $('#overviewContent li').eq(i).addClass('active');
            }
        });

    } else {

        $('.navFixed').removeClass('fixed');
        $('#overviewContent a.active').removeClass('active');
        $('#overviewContent a:first').addClass('active');
    }

}).scroll();
</script>

<script>
/* $(function() {
    $('#nav li a').click(function() {
       $('#nav li').removeClass();
       $($(this).attr('href')).addClass('active');
    });
 }); */
</script>	

<script>
/* $('li > a').click(function() {
    $('li').removeClass();
    $(this).parent().addClass('active');
}); */
$('#arrowUp').click(function() {
    $('#overviewContent').addClass('active');
    $('#productLink').addClass('active');
    $('#arrowUp').removeClass('active');
    $('html,body').animate({
        scrollTop: $("#sb-site").offset().top},
        'slow');
    
});
</script>


</body>

</html>
