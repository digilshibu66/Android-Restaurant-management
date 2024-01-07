<?php 
include('connection.php');

$delete_w=$_GET['delete_id'];


mysqli_query($conn,"DELETE FROM `employee_tb` WHERE login_id='$delete_w'");
mysqli_query($conn,"DELETE FROM `login_tb` WHERE login_id='$delete_w'");

header('location:view_waiter.php');



?>