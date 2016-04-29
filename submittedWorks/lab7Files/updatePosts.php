<?php
	session_start();
	
	$postJSON = $_SESSION["postJSON"];
	$pTime = $_POST['postNum'];	
	$postUp = $_POST['postUpdate'];
	$postArrs = json_decode($postJSON,true);
	$flag = true;

	foreach($postArrs as $pValue){
		if($pValue[time] == $pTime){
			$pValue[data] = $postUp;
			$flag = false;
		}
	}
	
	if($flag){

		$newPost = "{user:'".$_SESSION['username']."', data:'".$postUp."', time:'".$num."'}";
		
		$postJSON = $newPost.$postJSON;
		$writ = file_put_contents('posts.txt', json_encode($postJSON));
	}else
		$writ = file_put_contents('posts.txt', json_encode($postArrs));

	$_SESSION['postJSON'] = $postJSON;

	
	
	$_SESSION['update']=true;

	echo $writ === false;
?>


