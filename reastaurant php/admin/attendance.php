<?php 
session_start();

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
    <link rel="stylesheet" href="vendors/datatables.net-bs4/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="vendors/datatables.net-buttons-bs4/css/buttons.bootstrap4.min.css">

    <link rel="stylesheet" href="assets/css/style.css">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>
</head>

<body>
    <!-- Left Panel -->
     <?php include'include/sidebar.php';?> 
    <!-- /#left-panel -->

    <!-- Left Panel -->

    <!-- Right Panel -->

    <div id="right-panel" class="right-panel">

        <!-- Header-->
         <?php include'include/header.php';?>    

        <!-- /header -->
        <!-- Header-->

        <div class="breadcrumbs">
            <div class="col-sm-4">
                <div class="page-header float-left">
                    <div class="page-title">
                        <h1>Chef Details</h1>
                    </div>
                </div>
            </div>
            <div class="col-sm-8">
                <div class="page-header float-right">
                    <div class="page-title">
                        <ol class="breadcrumb text-right">
                            <li><a href="index.php">Dashboard</a></li>
                           
                            <li class="active">chef details</li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>

        <div class="content mt-3">
            <div class="animated fadeIn">
                <div class="row">

                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <strong class="card-title">Data</strong>
                            </div>
                            <div class="card-body">
                                <table id="bootstrap-data-table-export" class="table table-striped table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Type</th>
                                           

                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <?php include'connection.php';
                                        $query=mysqli_query($conn,"SELECT * FROM employee_tb JOIN login_tb ON employee_tb.login_id=login_tb.login_id");
                                        while ($row=mysqli_fetch_assoc($query)) { ?>
                                           
                                        <tr>
                                            <td><?php echo $row['name'];?></td>
                                            <td><?php if($row['type']==1) { ?>
                                                Waiter
                                           <?php } if($row['type']==2)  { ?>
                                                Chef
                                           <?php } if($row['type']==3) { ?>
                                                Delivery Boy
                                           <?php } if($row['type']==4) { ?>
                                                Security
                                           <?php } ?></td>                                           
                                          
                                            <td>
                                                <?php 
                                                $em=$row['emp_id'];
                                                $current_date=date("d-m-Y");
                                                $today=mysqli_query($conn,"SELECT * FROM attendance_tb WHERE day='$current_date' AND emp_id='$em' ");
                                                $data_at=mysqli_fetch_assoc($today);
                                                // $r=$data_at['attendance'];
                                                // print_r($r);
                                                if($data_at['attendance']=='present'){ ?>
                                                <a  style=" width: 74px;" class="btn btn-success btn-sm" >Active</a>
                                            <?php } elseif($data_at['attendance']=='absent') {?>
                                               <a  style=" width: 74px;" class="btn btn-danger btn-sm" >Inactive</a>
                                            <?php } else{ ?>
                                                <a href="attendance_present.php?id=<?php echo $row['emp_id'];?>" style="color: white; width: 74px;" class="btn btn-primary btn-sm">Present</a>
                                                <a href="attendance_ab.php?id=<?php echo $row['emp_id'];?>"  style="color: white" class="btn btn-danger btn-sm">Absent</a>
                                            <?php } ?>
                                            </td>
                                        </tr>
                                       <?php } ?> 
                                    </tbody>
                                </table>
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
    <script src="vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="assets/js/main.js"></script>


    <script src="vendors/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="vendors/datatables.net-bs4/js/dataTables.bootstrap4.min.js"></script>
    <script src="vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
    <script src="vendors/datatables.net-buttons-bs4/js/buttons.bootstrap4.min.js"></script>
    <script src="vendors/jszip/dist/jszip.min.js"></script>
    <script src="vendors/pdfmake/build/pdfmake.min.js"></script>
    <script src="vendors/pdfmake/build/vfs_fonts.js"></script>
    <script src="vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
    <script src="vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
    <script src="vendors/datatables.net-buttons/js/buttons.colVis.min.js"></script>
    <script src="assets/js/init-scripts/data-table/datatables-init.js"></script>


</body>

</html>
