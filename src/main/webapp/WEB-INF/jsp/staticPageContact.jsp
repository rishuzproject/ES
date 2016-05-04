<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>Ecosystem</title>
    <link rel="shortcut icon" href="assets/img/favicon.ico" type="image/icon">
    <link rel="shortcut icon" href="assets/img/favicon.png">
    <meta name="description" content="">

  

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
    </style>
</head>

<!-- Preloader -->


<body style="overflow: visible;">
<div id="preloader" style="display: none;">
    <div id="status" style="display: none;">&nbsp;</div>
</div>

<div id="sb-site">
<div class="boxed" style="padding-bottom:10px">

<%@ include file="./staticHeaderFooter.jsp" %>

	

<header class="main-header no-margin-bottom">
    <div class="container">
        <h1 class="page-title">Contact</h1>
    </div>
</header>
<div>
    	<iframe width="100%" height="400" frameborder="0" style="border:0" src="https://www.google.com/maps/embed/v1/place?q=Bangalore%2C%20Karnataka%2C%20India&amp;key=AIzaSyAiDDm4EVLxnz6ArrHuDc8JsHR6H0eJVYE"></iframe>  
	</div>
	<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h2 class="section-title">Send Message</h2>
        </div>
        <div class="col-md-8">
            <section>
                <form role="form" id="staticContactForm">
                    <div class="form-group">
                        <label for="InputName">Name</label>
                        <input type="text" class="form-control" id="inputName" name="inputName">
                    </div>
                    <div class="form-group">
                        <label for="InputEmail1">Email address</label>
                        <input type="email" class="form-control" id="inputEmail1" name="inputEmail1">
                    </div>
                    <div class="form-group">
                        <label for="InputMessage">Message</label>
                        <textarea class="form-control" id="inputMessage" rows="8" name="inputMessage"></textarea>
                    </div>
                    <button type="submit" class="btn btn-ar btn-primary ladda-button" data-style="expand-right"
							 id="submitId" onclick="setActionType(this)">Submit</button>
                    <div class="clearfix"></div>
                </form>
            </section>
        </div>

        <div class="col-md-4">
            <section>
                <div class="panel panel-primary">
                    <div class="panel-heading"><i class="fa fa-envelope-o"></i> Additional Information</div>
                    <div class="panel-body">
                        <h4 class="section-title no-margin-top">Contacts</h4>
                        <address>
                            <strong>Cerrid Solutions Pvt. Ltd.</strong><br>
                            2nd Floor, C K Plaza, # L-152<br>
                             14th Cross , 5th Main <br>
                             Sector VI, H S R Layout.<br>
                             Bangalore 02, INDIA<br>
                            <abbr title="Phone">P:</abbr> +91 (806) 534 4202<br>
                            Mail: <a href="#">info@cerridsolutions.com</a>
                        </address>
                       

                        <!-- Business Hours -->
                        <!-- <h4 class="section-title no-margin-top">Business Hours</h4>
                        <ul class="list-unstyled">
                            <li><strong>Monday-Friday:</strong> 9am to 7pm</li>
                            <li><strong>Sunday:</strong> Closed</li>
                        </ul> -->
                    </div>
                </div>
            </section>
        </div>
    </div>
</div>


</div>



            </div>
    



 <!-- sb-slidebar sb-right -->
<div id="back-top" style="display: none;">
    <a href="#header"><i class="fa fa-chevron-up"></i></a>
</div>

<script src="./assets/js/staticWEBjs/staticContact.js"></script>

</body>

</html>
