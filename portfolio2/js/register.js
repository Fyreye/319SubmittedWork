$(document).ready(function() {
	$("#register").click(function() {
		getUser();
	});
	$("img").click(function() {
		stuff();
	});
	$("#cleanup").click(function() {
		cleanup();
	});
});

function getUser()
{
	var user = $("#username").val();
	$.post("/php/register.php", {username:user}, function(data,status) {
	if(data.status)
	{
		console.log(data.message);
		window.location.href = data.data;
	}
	else
		$("h4").text(data.message);
	}, "json");
}

function stuff()
{
	window.location.href = 'Projector.html';
}

function cleanup()
{
	$.post("/php/cleanup.php", function(data) {
		console.log(data);
	});
}
