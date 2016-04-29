<?php
	session_start();

	if (empty($_POST['answer']))
	{	
		exit(0);
	}

	$memcache = new Memcache;
	$memcache->connect('localhost',11211) or die("Could not connect to memcache");

	// Check user answer against correct answer ($memcache->get('answer'))
	// Create new database connection if correct and update user score

	similar_text($memcache->get('answer'), $_POST['answer'], $compare);

//	echo $memcache->get('answer')."  ".$_POST['answer']."  ".$compare;

	if ($compare >= 80.0)
	{
//		echo "correct";
		$servername = "localhost";
		$username = "webmaster";
		$password = "triviadb";
		$dbname = "CXC";
	
		$conn = new mysqli($servername, $username, $password, $dbname);

		if ($conn->connect_error) 
		{
			die("Connection failed: ".$conn->connect_error);
		}

		$_SESSION['score'] = $_SESSION['score'] + 1;
		$sql = "UPDATE player SET score=".$_SESSION['score']." WHERE ip='".$_SESSION['ip']."';";
	
		if ($conn->query($sql) === TRUE)
		{
			echo "Score Updated";
		}
		else
		{
			echo "Failed updating  ".$sql;
		}
	
		$conn->close();
		exit(0);
	}
	
	echo $memcache->get('answer')."  ".$_POST['answer']."  ".$compare;

	exit(0);
?>
