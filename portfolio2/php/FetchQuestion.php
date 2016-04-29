<?php
	
	error_reporting(E_ALL | E_WARNING | E_NOTICE);
	ini_set('display_errors', TRUE);	

	$servername = "localhost";
	$username = "webmaster";
	$password = "triviadb";
	$dbname = "CXC";
	$output = new stdClass;
	$output->question = "";
	$output->answer = "";
	$output->section = "";
	
	$conn = new mysqli($servername, $username, $password, $dbname);

	$memcache = new Memcache;
	$memcache->connect('localhost', 11211) or die ("Could not connect");
	
	if ($conn->connect_error) 
	{
		die("Connection failed: ".$conn->connect_error);
	}	
	
	$turnNum = $memcache->get("turn");  //grab the turn variable from the db
	
//	$request = "SELECT question,answer FROM questions WHERE questionNumber =".$turnNum;
//	$dbResult = $conn->query($request);

	if($turnNum == 0){
		
		$output->question = "Ready?";
		$output->answer = "Ready?";
		$output->section = "Waiting for Players";
	}else{

		
		$request = "SELECT question FROM questions WHERE questionNumber =".$turnNum;
		$dbResult = $conn->query($request);	
		
		$question = $dbResult->fetch_assoc()['question'];//fetch the question from the db
		$output->question = $question;


		$request = "SELECT answer FROM questions WHERE questionNumber =".$turnNum;
		$dbResult = $conn->query($request);

		$answer = $dbResult->fetch_assoc()['answer'];
		$output->answer = $answer;


		$request = "SELECT section FROM questions WHERE questionNumber =".$turnNum;
		$dbResult = $conn->query($request);

		$section = $dbResult->fetch_assoc()['section'];
		$output->section = $section;

		if($question == '') $output->question = "done";
	}
	
	$memcache->set('question', $output->question, false, 0) or die ("Failed to save question at the server");
	$memcache->set('answer', $output->answer, false, 0) or die ("Failed to save answer at the server");
	$memcache->set('section', $output->section, false, 0) or die ("Failed to save section at the server");
	//echo $output->question;
	echo $memcache->get('question');
	exit(0);
?>
