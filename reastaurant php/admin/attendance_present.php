<?php 
include('connection.php');

$emp=$_GET['id'];
$current_date=date("d-m-Y");


mysqli_query($conn,"INSERT INTO attendance_tb (emp_id,day,attendance) VALUES ('$emp','$current_date','present')");


header('location:attendance.php');



?>