<?php 
include('connection.php');

$avlbl=$_GET['available_id'];


mysqli_query($conn,"UPDATE `table_tb` SET status='available' WHERE tbl_id='$avlbl'");



$reser=$_GET['reserve_id'];

mysqli_query($conn,"UPDATE `table_tb` SET status='reserved' WHERE tbl_id='$reser'");



$oc=$_GET['occupi_id'];

mysqli_query($conn,"UPDATE `table_tb` SET status='occupied' WHERE tbl_id='$oc'");

// $oc=$_GET['occupi_id'];

// mysqli_query($conn,"UPDATE `table_tb` SET status='occupied' WHERE tbl_id='$oc'");


header('location:view_table.php');



?>