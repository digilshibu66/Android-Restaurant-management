<?php 
include('connection.php');

$delete_tb=$_GET['delete_id'];


mysqli_query($conn,"DELETE FROM `table_tb` WHERE tbl_id='$delete_tb'");

header('location:view_table.php');



?>