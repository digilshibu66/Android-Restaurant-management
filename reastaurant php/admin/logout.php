<?php
session_start();

$_SESSION["login_id"]="";

$_SESSION['type']="";

session_destroy();

header("location: login.php");
?>
