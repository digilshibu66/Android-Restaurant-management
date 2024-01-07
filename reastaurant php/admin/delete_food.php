<?php 
include('connection.php');

$delete_f=$_GET['delete_id'];


mysqli_query($conn,"DELETE FROM `menu_tb` WHERE item_id='$delete_f'");

header('location:view_menu.php');



?>