<?php
	
	error_reporting(E_ALL | E_WARNING | E_NOTICE);
	ini_set('display_errors', TRUE);	

	$memcache = new Memcache;
	$memcache->connect('localhost', 11211) or die ("Could not connect");
	
	echo $memcache->get('answer');
	exit(0);
?>
