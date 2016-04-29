<?php
	session_start();

	$memcache = new Memcache;
	$memcache->connect('localhost',11211) or die("Could not connect to memcache");

	if($memcache->get('turn') != $_SESSION['turn'])
	{
		$_SESSION['turn'] = $memcache->get('turn');
		if($_SESSION['turn'] == 1000)
		{
			echo json_encode(array("update"=>true,"section"=>"winners","question"=>"/CXC/winners.html"));
			exit(0);
		}
		else
		{
			echo json_encode(array("update"=>true,"section"=>$memcache->get('section'),"question"=>$memcache->get('question')));
			exit(0);
		}
	}	

	echo json_encode(array("update"=>false,"question"=>NULL,"data"=>$_SESSION['turn']." ".$memcache->get('turn')." ".$memcache->get('answer')));

	exit(0);
?>
