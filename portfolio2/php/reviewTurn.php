<?php
	
	session_start();

	error_reporting(E_ALL | E_WARNING | E_NOTICE);
	ini_set('display_errors', TRUE);		
	
	$turnR = $_SESSION['turnR'];

	$turnRNum = $turnR + 1;

	$_SESSION['turnR'] = $turnRNum;
	
	echo $turnRNum;
	exit(0);
?>
