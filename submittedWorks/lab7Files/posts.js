$(document).ready(function() {
	$("#logout").click(function() {
		logout();
	});
	$("#postBoard").ready(function(){
		updateTable();
	});
	$("#submit").click(function(){
		updateLogs(Date(), $("#input").val());
	});
});

function updateTable(){
	console.log("build Table");
	$.get("viewPosts.php", function (data,status) {
	console.log(status);
	//console.log(data);
		$("#postBoard").html(data);
		
	}).fail(function(){console.log("fail");});
}

function updateLogs(time, pData){
	console.log(time);
	$.post("updatePosts.php", {postNum:time, postUpdate:pData}, function(data) {

		console.log("updating");
		
	}).success(function(data){
		console.log(data);

		updateTable();
	});
	//updateTable();
}

function logout()
{
	
	$.get("logout.php", function() {
		window.location.href = 'login.html';  
	});
}
