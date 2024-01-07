<?php 
include('connection.php');

$delete_mn=$_GET['delete_id'];


mysqli_query($conn,"DELETE FROM `today_menu_tb` WHERE today_menu_id='$delete_mn'");

header('location:view_todays_menu.php');



?>