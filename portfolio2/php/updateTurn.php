<?php
	
	error_reporting(E_ALL | E_WARNING | E_NOTICE);
	ini_set('display_errors', TRUE);		
	
	$memcashe = new Memcache;
	$memcashe->connect('localhost', 11211) or die ('could not connect');

	$turn = $memcashe->get('turn');

	$turnNum = $turn + 1;
	
	$memcashe->set('turn', $turnNum, false, 0) or die ("Failed to update turn variable");

	echo $turnNum;
	exit(0);
?>
