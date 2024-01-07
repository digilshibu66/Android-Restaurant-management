 <?php
include('connection.php');
session_start();
if(isset($_SESSION['login_id']))
{
  header("location:index.php");  
}

if(isset($_POST['login']))
{
$username=$_POST['username'];
$password=$_POST['password'];
$query=mysqli_query($conn,"SELECT * from login_tb where username='$username' and password='$password'");
$result=mysqli_fetch_assoc($query);
if(mysqli_num_rows($query)>0)
{

    
    $role=$result['type'];


        if($role=='0')
          {
              $_SESSION['login_id']=$result['login_id'];
              $_SESSION['type']=$result['type'];
              header("location:index.php");
          } 

         if($role=='1')
          {
              $_SESSION['login_id']=$result['login_id'];
              $_SESSION['type']=$result['type'];
              header("location:index.php");
          }   

         // if($role=='hospital')
         //      {
         //           $status=mysqli_query($conn,"SELECT * FROM tbl_hospital WHERE role_id='$result[role_id]'");
         //           $checking_status=mysqli_fetch_assoc($status);

         //           if($checking_status['status']==1)
         //                {
         //                     $_SESSION['login_id']=$result['role_id'];
         //                     $_SESSION['role']=$result['role'];
         //                     header("location:index.php");
         //                }
                  
               
         //           else
         //                   {
         //                      echo "<script>alert('Waiting for approval')</script>";
         //                   }
         //       }



          // if($role=='2')
          // {
          //     $_SESSION['login_id']=$result['login_id'];
          //     $_SESSION['role']=$result['role'];
          //     header("location:../index.php");
          // }   

}
 else
          {
             echo "<script>alert('invalid username or password')</script>";
          }  
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

<body  style="background-color: #d8e4f0">


    <div class="sufee-login d-flex align-content-center flex-wrap">
        <div class="container">
            <div class="login-content">
                <div class="login-logo">
                    <a href="index.html">
                        <img class="align-content" src="images/logo.png" alt="">
                    </a>
                </div>
                <div class="login-form">
                    <form method="post">
                        <div class="form-group">
                            <label>Username</label><span style="color: white; background-color: red;" id="spname"></span>
                            <input type="text" class="form-control" name="username" id="uname_id" onkeyup="clrmsg('spname')" placeholder="Username">
                        </div>
                            <div class="form-group">
                                <label>Password</label><span style="color: white; background-color: red;" id="sppass"></span>
                                <input type="password" class="form-control" name="password" id="pass_id" onkeyup="clrmsg('sppass')" placeholder="Password">
                        </div>
                               
                                <button type="submit" onclick="return validate()" name="login" class="btn btn-success btn-flat m-b-30 m-t-30">LOGIN</button>
                               
                               
                    </form>
                </div>
            </div>
        </div>
    </div>


    <script src="vendors/jquery/dist/jquery.min.js"></script>
    <script src="vendors/popper.js/dist/umd/popper.min.js"></script>
    <script src="vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="assets/js/main.js"></script>
     <script>
        function validate()
        {
            var name=document.getElementById("uname_id").value;
            var pass=document.getElementById("pass_id").value;

            if(name=="")
            {
                document.getElementById('spname').innerHTML="* empty field";
                return false;
            }
              if(pass=="")
            {
                document.getElementById('sppass').innerHTML="* empty field";
                return false;
            }

        }
        function clrmsg(p)
        {
            document.getElementById(p).innerHTML="";
        }
    </script>


</body>

</html>
