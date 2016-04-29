<?php
	session_start();

	if (!empty($_POST['username']) && !empty($_POST['password']))
	{
		if (strpos(file_get_contents("./users.txt"),$_POST['username'].":".$_POST['password']) !== false)
		{	
			$_SESSION['valid'] = true;
			$_SESSION['username'] = $_POST['username'];
			$_SESSION['time'] = time();
			header("Refresh:2; URL = success.html"); // This line will not execute for some reason
			//exit;
			echo json_encode(array('success' => true));
		}
		else 
		{
			echo json_encode(array('success' => false));
		}
	}
?>
