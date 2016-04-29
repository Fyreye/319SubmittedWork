<?php
	session_start();
	echo json_encode(array('username' => $_SESSION['username'], 'valid' => $_SESSION['valid'], 'time' => $_SESSION['time']));
?>
