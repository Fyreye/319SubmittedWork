<?php
  session_start();
	$encodedPosts = file_get_contents("posts.txt");     // load in json encoded posts
	$postArr = json_decode($encodedPosts,true);             //decode post array
	$_SESSION["postJSON"] = $encodedPosts;
	//$table = $document.getElementByID("postBoard");   //get the table from dom  ____THROWS 500 ERROR_____
//	$postArr = ["p", "q", "k"];
	$output = "<tr>  <th>User</th> <th>Post</th> </tr>";	
	$i;
	foreach($postArr as $value) {			//for each post in the post array  $i=0;i<$postArr.length;$i++
		if($_SESSION["username"] == $value[user])
			$output.="<tr> <td>".($value[user])."</td><td><input name='place' type='text' placeholder='".($value[data])."'></td><td> <button id='edit' name='updateButton' onclick='updateLogs(".($value[time]).", \"".($value[data])."\")' >update</button> </td> </tr>";
		else
			$output.="<tr> <td>".($value[user])."</td><td>".($value[data])."</td><td>  </td> </tr>";
	}

	$_SESSION['update']=false;

	echo $output;
?>

