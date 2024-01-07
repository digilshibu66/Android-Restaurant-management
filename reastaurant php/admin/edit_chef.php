<?php
include'connection.php';
session_start();

$id=$_GET['edit_id'];

$waiter_query=mysqli_query($conn,"SELECT * FROM employee_tb WHERE emp_id='$id'");
$data=mysqli_fetch_assoc($waiter_query);

if(isset($_POST['sub']))
{
   
    $nm=$_POST['name'];
     $ph=$_POST['phone'];
      $eml=$_POST['email'];
       $add=$_POST['address'];
       


  

   
    mysqli_query($conn,"UPDATE employee_tb SET name='$nm',phone='$ph',email='$eml',address='$add' WHERE emp_id='$id'");
    echo "<script>alert('Details Updated')</script>";
    echo "<script>window.location.href='view_chef.php'</script>";


               

}




?>
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Restaurant Management</title>
    <meta name="description" content="Sufee Admin - HTML5 Admin Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="apple-touch-icon" href="apple-icon.png">
    <link rel="shortcut icon" href="favicon.ico">


    <link rel="stylesheet" href="vendors/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="vendors/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="vendors/themify-icons/css/themify-icons.css">
    <link rel="stylesheet" href="vendors/flag-icon-css/css/flag-icon.min.css">
    <link rel="stylesheet" href="vendors/selectFX/css/cs-skin-elastic.css">

    <link rel="stylesheet" href="assets/css/style.css">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>



</head>

<body>


    <?php include'include/sidebar.php';?> 

    <div id="right-panel" class="right-panel">
       
        <?php include'include/header.php';?>        

        <div class="breadcrumbs">
            <div class="col-sm-4">
                <div class="page-header float-left">
                    <div class="page-title">
                        <h1>WAITER</h1>
                    </div>
                </div>
            </div>
            <div class="col-sm-8">
                <div class="page-header float-right">
                    <div class="page-title">
                        <ol class="breadcrumb text-right">
                            <li><a href="index.php">Dashboard</a></li>
                           
                            <li class="active">update details</li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
        <div class="content mt-3">
            <div class="animated fadeIn">
                <div class="row">                  
                   
                                            <div class="col-lg-8 ml-5">
                                                <div class="card">
                                                    <div class="card-header">
                                                        <strong>Waiter </strong> update
                                                    </div>
                                                    <div class="card-body card-block">
                                                        <form action="" method="post" enctype="multipart/form-data" class="form-horizontal">
                                                           
                                                            <div class="row form-group">
                                                                <div class="col col-md-3"><label for="text-input" class=" form-control-label">Name</label></div>
                                                                <div class="col-12 col-md-9"><input type="text" value="<?php echo $data['name'];?>"  class="form-control"name="name" onkeyup="clearmsg('spname')" id="name_id"><span id="spname" style="color: white;background-color: red;" ></span></div>
                                                            </div>
                                                            <div class="row form-group">
                                                                <div class="col col-md-3"><label for="text-input" class=" form-control-label">Phone</label></div>
                                                                <div class="col-12 col-md-9"><input type="text" value="<?php echo $data['phone'];?>"  class="form-control"maxlength="10" oninput="this.value = this.value.replace(/[^0-9.]/g, ''); this.value = this.value.replace(/(\..*)\./g, '$1');"
                                                                  name="phone" id="phone_id" onkeyup="clearmsg('spphone')"></div>
                                                                  <span id="spphone" style="color: #F00"></span>
                                                            </div>
                                                            <div class="row form-group">
                                                                <div class="col col-md-3"><label for="email-input" class=" form-control-label">Email </label></div>
                                                                <div class="col-12 col-md-9"><input type="email"  value="<?php echo $data['email'];?>" class="form-control" name="email" id="email_id" onkeyup="clearmsg('spemail')"></div><span id="spemail" style="color: #F00"></span>
                                                            </div>
                                                           
                                                            
                                                            <div class="row form-group">
                                                                <div class="col col-md-3"><label for="textarea-input" class=" form-control-label">Address</label></div>
                                                                <div class="col-12 col-md-9"><textarea  rows="9"  class="form-control"  name="address" id="address_id" onkeyup="clearmsg('spaddress')"><?php echo $data['address'];?></textarea><span id="spaddress" style="color: #F00"></span></div>
                                                            </div>
                                                          
                                                           
                                                            <span id="sppass" style="color: #F00"></span>
                                                   <div class="card-footer ">
                                                        <button type="submit" onclick="return validate();" name="sub" class="btn btn-primary btn-sm">
                                                            <i class="fa fa-dot-circle-o"></i> Update
                                                        </button>
                                                        
                                                    </div>
                                                     
                                                                
                                                        </form>
                                                    </div>
                                                   
                                                </div>                                               
                                            </div>  

                                            </div>
                                        </div><!-- .animated -->
                                    </div><!-- .content -->
                                </div><!-- /#right-panel -->
                                <!-- Right Panel -->


                            <script src="vendors/jquery/dist/jquery.min.js"></script>
                            <script src="vendors/popper.js/dist/umd/popper.min.js"></script>

                            <script src="vendors/jquery-validation/dist/jquery.validate.min.js"></script>
                            <script src="vendors/jquery-validation-unobtrusive/dist/jquery.validate.unobtrusive.min.js"></script>

                            <script src="vendors/bootstrap/dist/js/bootstrap.min.js"></script>
                            <script src="assets/js/main.js"></script>
<script>
                                
function validate()
{
    var name=document.getElementById("name_id").value.trim();
    var mobile=document.getElementById("phone_id").value;
    var email=document.getElementById("email_id").value;
    
    var address=document.getElementById("address_id").value;    
   
  
    
    if(name=="")
    {
        
        document.getElementById("spname").innerHTML="* Please enter your name";
            
        return false;
    }

    if(mobile=="")
    {
        
        document.getElementById("spphone").innerHTML="* Please enter your mobile";
        
        return false;
    }
    
    if(email==""||email.indexOf("@")==-1||email.indexOf(".")==-1)
    {
            document.getElementById("spemail").innerHTML="* Please give valid email";
        return false;
    }
    
    
    if(address=="")
    {
        document.getElementById("spaddress").innerHTML="* Please enter your address";
        return false;
    }
    
       
    
   
}

function clearmsg(sp)
{
    document.getElementById(sp).innerHTML="";
}

</script>
                         
</body>
</html>
