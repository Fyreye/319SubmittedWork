$(document).ready(function() {
	$("button").click(function() {
		login();
	});
});

function login()
{
	var user = $("#username").val();
	var pass = $("#password").val();
	$.post("checkLogin.php", {username : user,  password : pass}, function(data) {
		if(data.success)
			window.location.href = 'board.html';
		else
			$("#message").text("Incorrect username or password");
		}, "json");
}
