<?php 
include('connection.php');

$delete_d=$_GET['delete_id'];


mysqli_query($conn,"DELETE FROM `employee_tb` WHERE login_id='$delete_d'");
mysqli_query($conn,"DELETE FROM `login_tb` WHERE login_id='$delete_d'");

header('location:view_security.php');



?>