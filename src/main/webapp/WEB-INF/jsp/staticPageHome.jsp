<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

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
/*.carousel-razon {
background-color: #7093A1;
background-color: #7093A1;
}*/
.carousel-razon {
background-color: #0099da;
background-color: rgba(122, 147, 158, 0.9);
}
.carousel-control .fa-chevron-left  
{
	left:22%;
}
.carousel-control .fa-chevron-right
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
	.modal-dialog {
margin: 73px auto;
}
.allThreeButton {
z-index: 1;
}
.desktopAlign {
display: inline-block;
font-family: FontAwesome;
font-style: normal;
font-weight: normal;
line-height: 2 !important; 
-webkit-font-smoothing: antialiased;
-moz-osx-font-smoothing: grayscale;
}
    </style>
</head>



<body style="overflow: visible;">


<div id="sb-site">
<div class="boxed">

<%@ include file="./staticHeaderFooter.jsp" %>

<div class="row">
<section class="carousel-section" style="height:413px">


<div class="col-md-6" style="padding-right:0px">
    <div id="" class="carousel carousel-razon slide" data-ride="carousel" data-interval="5000">
        <div class="carousel-inner" style="height:413px">
            <div class="item active">
                <div class="container">
                    <div class="row">
                    <div class="col-md-6 col-sm-12" style="padding-left:50px">
                    
                    <div class="carousel-caption animated bounceInDown animation-delay-3">
                                <div class="carousel-text">
                                   <h1 class="animated fadeInDownBig  carousel-title">What is Ecosystem</h1>
                                   <h2 class="animated fadeInDownBig   crousel-subtitle">Smart way of handling your Business & Manage application at a single page and yours</h2>
                                   <ul class="list-unstyled carousel-list">
                                      
                                      <li class="animated fadeInLeftBig bounceInLeft animation-delay-7" ><i class="fa fa-check"></i>Projects Management</li>
                                       <li class="animated fadeInLeftBig bounceInLeft animation-delay-9" ><i class="fa fa-check"></i>Users Management</li>
                                      <li class="animated fadeInLeftBig bounceInLeft animation-delay-11"><i class="fa fa-check"></i>Statistics</li>
                                       <li class="animated fadeInLeftBig bounceInLeft animation-delay-13"><i class="fa fa-check"></i>Logs Usage</li>
                                   </ul>
                                   <p class="animated fadeInDowntBig bounceInDown animation-delay-30">Just sign in to feel the next generation business tool. 
                                   <span class="dropdown" style="cursor:pointer">Get Started</span></p>
                               </div>
                            </div>                            
                        </div>                                               
                    </div>
                </div>
            </div>      
        </div>
   </div>
   </div>

<div class="col-md-6" style="padding-left:0px">
  <div id="carousel-example-generic" class="carousel carousel-razon slide" data-ride="carousel" data-interval="5000">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1" class=""></li>
            <li data-target="#carousel-example-generic" data-slide-to="2" class=""></li>
            <li data-target="#carousel-example-generic" data-slide-to="4" class=""></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" style="height:413px;padding-left:13%">
        
         <div class="item active">
                <div class="container">
                   <div class="row">
                        
                        <div class="col-md-5 col-sm-7 hidden-xs carousel-img-wrap">
                            <div class="carousel-img">
                                <img src="./assets/img/staticWEBimg/chart.png" class="img-responsive animated bounceInUp animation-delay-2" alt="Image">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        
            <div class="item">
                <div class="container">
                    <div class="row">
                    
                        <div class="col-md-5 col-sm-7 ">
                            <div class="carousel-caption">
                                <div class="carousel-text">
                                   <h1 class="animated fadeInDownBig animation-delay-20 carousel-title" style="color: brown;">Notification : MPR</h1>
                                   <h2 class="animated fadeInDownBig animation-delay-18  crousel-subtitle" >Japan's inspirational footballer</h2>
                                  
                                   
                                   <p class="animated fadeInUpBig animation-delay-30">CNN's Kyung Lah sits down with Japan's World Cup-winning captain to reflect on their win amid tragedy.</p>
                               </div>
                            </div>
                        </div>
                        
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="container">
                    <div class="row">
                        
                        <div class="col-md-5 col-sm-4 hidden-xs carousel-img-wrap">
                           <div class="carousel-caption">
                                <div class="carousel-text">
                                   <h1 class="animated fadeInDownBig animation-delay-7 carousel-title" style="color: brown;">Notification : PD</h1>
                                   <h2 class="animated fadeInDownBig animation-delay-5  crousel-subtitle" >Japan's inspirational footballer</h2>
                                  
                                   
                                   <p class="animated fadeInUpBig animation-delay-17">CNN's Kyung Lah sits down with Japan's World Cup-winning captain to reflect on their win amid tragedy.</p>
                               </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="container">
                    <div class="row">
                        
                        <div class="col-lg-5 col-md-5 col-sm-3 hidden-xs carousel-img-wrap">
                           <div class="carousel-caption">
                                <div class="carousel-text">
                                   <h1 class="animated fadeInDownBig animation-delay-7 carousel-title" style="color: brown;">Notification : FTS</h1>
                                   <h2 class="animated fadeInDownBig animation-delay-5  crousel-subtitle" >From propaganda to pop artist</h2>
                                  
                                   
                                   <p class="animated fadeInUpBig animation-delay-17">Song Byeok had every reason to be pleased with his success. A gift for drawing led to a prestigious career as a propaganda artist and full membership .</p>
                               </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left fa fa-chevron-left"></span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right fa fa-chevron-right"></span>
        </a>
    </div>
</div>  
</section>
</div>


<section class="margin-bottom">
<p class="lead lead-lg text-center primary-color margin-bottom">Know about <strong>Our Products</strong> and get started</p>
    <div class="container">
        <div class="row">  
        <div class="col-md-4">
        	<div class="well text-center prdct-lst">
            	<span class="icon-ar icon-ar-lg" style="background: rgb(172, 202, 202);">
            	<!-- <i class="fa fa-desktop desktopAlign"></i> -->
            	<img src="./assets/img/mprLogo.png" alt="MPR Logo" width="70px">
            	</span>
            	<h4 class="no-margin">Monthly Projection Report</h4>
            	<p></p>
                <p>
                An application used by the construction companies for Project Management, Resource Management and Cashflow Monitoring.
                </p>
                <span><a href="#">Learn more</a></span>
                <div class="clearfix"></div>
                <a class="btn btn-default prdct-btn dropdown"  >Get started</a>
            </div>
        </div>
        <div class="col-md-4">
        	<div class="well text-center prdct-lst">
            	<span class="icon-ar icon-ar-lg" style="background: rgb(172, 202, 202);">
            	<!-- <i class="fa fa-desktop desktopAlign"></i> -->
            	<img src="./assets/img/fmsLogo.png" alt="FMS Logo" width="70px">
            	</span>
            	<h4 class="no-margin">Fixture Management System</h4>
                <p>
                <p>
                An application used by the construction companies to track and monitor the Orders of the Fixtures Required for Construction.
                </p>
                <span><a href="#">Learn more</a></span>
                <div class="clearfix"></div>
                <a class="btn btn-default prdct-btn dropdown">Get started</a>
            </div>
        </div>
        <div class="col-md-4">
        	<div class="well text-center prdct-lst">
            	<span class="icon-ar icon-ar-lg" style="background: rgb(172, 202, 202);">
            	<!-- <i class="fa fa-desktop desktopAlign"></i> -->
            	<img src="./assets/img/pdLogo.png" alt="PD Logo" width="70px">
            	</span>
            	<h4 class="no-margin">Purchase Directive</h4>
                <p>
                <p>
                An application used by the construction companies for Order Management and Billing Management for the relative projects.
                </p>
                <span><a href="#">Learn more</a></span>
                <div class="clearfix"></div>
                <a class="btn btn-default prdct-btn dropdown" >Get started</a>
            </div>
        </div>
      </div>
        
        
        <section>
        <h2 class="section-title">Our Clients</h2>
        <div class="col-md-6">
                 <div class="bx-wrapper" style="max-width: 100%; margin: 0px auto;"><div class="bx-viewport" style="width: 100%; overflow: hidden; position: relative; height: 166px;">
                 <ul class="bxslider" id="home-block" style="width: 615%; ">
                    <li style="float: left; list-style: none; position: relative; width: 555px;">
                        <blockquote class="blockquote-color">
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante ultricies nisi vel augue quam semper libero.</p>
                            <footer>Brian Krzanich, Intel CEO</footer>
                        </blockquote>
                    </li>
                </ul>
            </div>
        </div>
        </div>
        <div class="col-md-6">
                 <div class="bx-wrapper" style="max-width: 100%; margin: 0px auto;"><div class="bx-viewport" style="width: 100%; overflow: hidden; position: relative; height: 166px;">
                 <ul class="bxslider" id="home-block" style="width: 615%; ">
                    <li style="float: left; list-style: none; position: relative; width: 555px;">
                        <blockquote class="blockquote-color">
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante ultricies nisi vel augue quam semper libero.</p>
                            <footer>Brian Krzanich, Intel CEO</footer>
                        </blockquote>
                    </li>
                </ul>
            </div>
        </div>
        </div>
   </section>
    </div>
</section>
</div>


<!--<aside id="footer-widgets">
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <h3 class="footer-widget-title">Sitemap</h3>
                
                
                <p>Lorem ipsum Amet fugiat elit nisi anim mollit minim labore ut esse Duis ullamco ad dolor veniam velit.</p>
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Email Adress">
                    <span class="input-group-btn">
                        <button class="btn btn-ar btn-primary" type="button">Subscribe</button>
                    </span>
                </div><!-- /input-group -->
            </div>
            
            


<!--start of modal -->
	<div class="modal fade" id="getSatartedModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"  data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Modal title</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-3 control-label">Email</label>
							<div class="col-sm-9">
								<input type="email" class="form-control" id="inputEmail3"
									placeholder="Email">
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-3 control-label">Domain</label>
							<div class="col-sm-9">
								<input type="email" class="form-control" id="inputEmail3"
									placeholder="Email">
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-3 control-label">Company
								Name</label>
							<div class="col-sm-9">
								<input type="email" class="form-control" id="inputEmail3"
									placeholder="Email">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-3 control-label">Password</label>
							<div class="col-sm-9">
								<input type="password" class="form-control" id="inputPassword3"
									placeholder="Password">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-ar btn-primary"
						data-dismiss="modal">Register</button>
					<button type="button" data-dismiss="modal" class="btn btn-ar btn-default">Cancel</button>
				</div>
			</div>
		</div>
	</div>
	<!-- end of modal -->


 <!-- sb-slidebar sb-right -->
<div id="back-top" style="display: none;">
    <a href="#header"><i class="fa fa-chevron-up"></i></a>
</div>

<script src="./assets/js/staticWEBjs/staticHome.js"></script>

</body>

</html>
