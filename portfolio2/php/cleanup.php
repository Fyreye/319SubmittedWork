<?php
	session_start();
	session_unset();
	session_destroy();

	$servername = "localhost";
	$username = "webmaster";
	$password = "triviadb";
	$dbname = "CXC";
	
	$conn = new mysqli($servername, $username, $password, $dbname);
	
	if ($conn->connect_error) 
	{
		die("Connection failed: ".$conn->connect_error);
	}

	$sql = "DELETE FROM player WHERE ip='".$_SERVER['REMOTE_ADDR']."';";

	if ($conn->query($sql) === TRUE)
	{
		$msg = "Records deleted";
	}
	else
	{
		$msg = "Failed to delete records";
	}

	$conn->close();

	echo $msg;

?>
