$(document).ready(function() {
	getWinners();
});

function getWinners() {
	$.get("/php/winners.php", function(data) {
		$("#content").html(data);
	});
}
