<?php 
include('connection.php');

$delete_c=$_GET['delete_id'];


mysqli_query($conn,"DELETE FROM `employee_tb` WHERE login_id='$delete_c'");
mysqli_query($conn,"DELETE FROM `login_tb` WHERE login_id='$delete_c'");

header('location:view_chef.php');



?>