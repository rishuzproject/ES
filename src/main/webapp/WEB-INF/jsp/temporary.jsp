

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" name="viewport"
       content="width=device-width, initial-scale=1">
<title>Spring MVC + Dropzone.js Example</title>

<link rel="stylesheet" type="text/css"
       href='assets/css/bootstrap.min.css'>
<style type="text/css">
.container {
	margin-top: 10px;
}

.dz-filename {
	font-weight:bold;
}

.dz-success-mark, .dz-error-mark {
	display: none;
}

.dz-remove {
	display: block;
	text-align: center;
}

.dz-details {
	border-bottom: 1px solid LightGray;
}

.dz-progress {
	
	margin-top: 15px;
	background-color: #f5f5f5;
	border-radius: 4px;
}

.dz-upload { 
	border-radius: 4px;
    display: block; 
    background-color: #428bca; 
    height: 20px;
    width: 0%;
    text-align: center;
    color: white;
}

.dz-preview {
	display: inline-block;
	margin: 10px;
	background-color: #fff;
	border: 1px solid #ddd;
	border-radius: 4px;
	line-height: 1.42857;
	margin-bottom: 20px;
	padding: 4px;
}

.file-dropzone {
	border: 1px dashed #ddd;
}

.file-dropzone.drag-over {
	outline: LightGreen solid 4px;
}
</style>

</head>
<body>
       <div class="container">
              <div class="panel panel-default">
                     <div class="panel-heading text-center">
                           <h3>Spring MVC + Dropzone.js Example</h3>
                     </div>
                     <div class="panel-body">
                           <div>
                                  <form id="dropzone-form" class="dropzone"
                                         enctype="multipart/form-data">

                                         <div class="dz-default dz-message file-dropzone text-center well col-sm-12">
                                                 <span class="glyphicon glyphicon-paperclip"></span> <span>
                                                       To attach files, drag and drop here</span><br>
                                                <span>OR</span><br>
                                                <span>Just Click</span>
                                         </div>

                                         <!-- this is were the previews should be shown. -->
                                         <div class="dropzone-previews"></div>
                                  </form>
                                  <hr>
                                  <button id="upload-button" class="btn btn-primary">
                                         <span class="glyphicon glyphicon-upload"></span> Upload
                                  </button>
                                  <a class="btn btn-primary pull-right" href="list"> <span
                                         class="glyphicon glyphicon-eye-open"></span> View All Uploads
                                  </a>
                           </div>
                     </div>
              </div>
       </div>

        <script type="text/javascript"
              src='assets/js/jquery.min.js'></script>
       <script type="text/javascript"
              src='assets/js/bootstrap.min.js'></script>
       <script type="text/javascript"
              src='assets/js/dropzone.min.js'></script>
       <script type="text/javascript"
              src='assets/js/app.js'></script>
      
</body>
</html>