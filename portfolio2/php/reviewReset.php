<?php
	
	session_start();

	error_reporting(E_ALL | E_WARNING | E_NOTICE);
	ini_set('display_errors', TRUE);	

	$memcashe = new Memcache;
	$memcashe->connect('localhost', 11211) or die ('could not connect');

	$turnNum = 1000;
	$_SESSION['turnR'] = 0;
	$memcashe->set('turn', $turnNum, false, 0) or die ("Failed to update turn variable");

	echo $_SESSION['turnR'];
	exit(0);
?>
