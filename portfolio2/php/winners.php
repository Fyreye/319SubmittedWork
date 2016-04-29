<?php
	$servername = "localhost";
	$username = "webmaster";
	$password = "triviadb";
	$dbname = "CXC";

	$conn = new mysqli($servername, $username, $password, $dbname);

	if ($conn->connect_error) 
	{
		die("Connection failed: ".$conn->connect_error);
	}

	$sql = "SELECT username, score FROM player ORDER BY score DESC LIMIT 3";

	$result = $conn->query($sql);	
	$conn->close();	

	$i = 2;
	$output = "";
	while ($row = $result->fetch_assoc()) {
		$output = $output."<h".$i.">".$row['username']." score: ".$row['score']."</h".$i.">";
		$i = $i + 1;
	}
	
	echo $output;
	exit(0);
	
?>
