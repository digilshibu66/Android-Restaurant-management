<?php 
include('connection.php');

$delete_f=$_GET['delete_id'];

$date=date('d-m-Y');
mysqli_query($conn,"DELETE FROM `today_menu_tb` WHERE menu_date='$date'");

header('location:add_menu.php');



?>