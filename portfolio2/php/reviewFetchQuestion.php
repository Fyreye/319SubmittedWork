<?php

	session_start();
	
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
	
	if ($conn->connect_error) 
	{
		die("Connection failed: ".$conn->connect_error);
	}	
	
	$turnNum = $_SESSION['turnR'];  //grab the turn variable 


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
	
	echo json_encode(array("que"=>$output->question,"ans"=>$output->answer,"head"=>$output->section));
	exit(0);
?>
