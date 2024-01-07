<?php
session_start();
include 'connection.php';
if(isset($_POST['sub']))
{


    $t_menu[]=$_POST['items'];
     $count=count($t_menu['0']);
    // var_dump($count);exit();
    $i=0;
    for ($i=0; $i <$count ; $i++) { 
      
    $menu=$t_menu['0'][$i];

    $current_date=date('d-m-Y');

    mysqli_query($conn,"INSERT INTO today_menu_tb (item_id,menu_date) VALUES ('$menu','$current_date')");


}
    echo"<script>alert('Todays Menu Updated');</script>";
    echo "<script>window.location.href='add_menu.php'</script>";
                  

    
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


    <link rel="stylesheet" href="vendors/chosen/chosen.min.css">
    <link rel="stylesheet" href="assets/css/style.css">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>



</head>

<body>
    <!-- Left Panel -->

    <?php include'include/sidebar.php';?><!-- /#left-panel -->

    <!-- Left Panel -->

    <!-- Right Panel -->

    <div id="right-panel" class="right-panel">

        <!-- Header-->
        <?php include'include/header.php';?><!-- /header -->
        <!-- Header-->

        <div class="breadcrumbs">
            <div class="col-sm-4">
                <div class="page-header float-left">
                    <div class="page-title">
                        <h1>Todays Menu</h1>
                    </div>
                </div>
            </div>
            <div class="col-sm-8">
                <div class="page-header float-right">
                    <div class="page-title">
                        <ol class="breadcrumb text-right">
                            <li><a href="index.php">Dashboard</a></li>
                           
                            <li class="active">Todays menu</li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>

        <div class="content mt-3">
            <div class="animated fadeIn">

               

                    

                    
                <form method="post">
                        <div class="card">
                            <div class="card-header">
                                <strong class="card-title"> Select</strong>
                            </div>
                            <div class="card-body">

                                <select data-placeholder="Choose Food Item..." name="items[]" multiple class="standardSelect">
                                    <option value=""></option>

                                    <?php 

                                    $query=mysqli_query($conn,"SELECT * FROM  menu_tb ");
                                    while ($row=mysqli_fetch_assoc($query)) { ?>
                                   
                                    <option value="<?php echo $row['item_id'];?>"><?php echo $row['item_name'];?></option>
                                    <?php } ?>
                                </select>

                            </div>
                        </div>
                        <button type="submit" name="sub" style="color: white" class="btn btn-primary btn-sm">Submit</button>

                  
                 </form>
                </div>
            </div><!-- .animated -->
        </div><!-- .content -->

    </div><!-- /#right-panel -->

    <!-- Right Panel -->


<script src="vendors/jquery/dist/jquery.min.js"></script>
<script src="vendors/popper.js/dist/umd/popper.min.js"></script>
<script src="vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="assets/js/main.js"></script>
<script src="vendors/chosen/chosen.jquery.min.js"></script>

<script>
    jQuery(document).ready(function() {
        jQuery(".standardSelect").chosen({
            disable_search_threshold: 10,
            no_results_text: "Oops, nothing found!",
            width: "100%"
        });
    });
</script>

</body>

</html>
