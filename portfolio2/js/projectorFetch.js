$(document).ready(function() {
	$("#nextPage").click(function() {
		 nextQuestion();
	});	
	$("#cry").click(function() {
		reset();
	});
	$("img").click(function() {
		window.location.href = './';
	});
	$('#question').ready(function(){
		refreshQuestion();
	});
	$('#review').click(function() {
		//reset();
		window.location.href = 'ProjectorReview.html';
	});	
});

function nextQuestion() {

//	console.log('update');
	
	$.post('/php/updateTurn.php', function(data) {
//		console.log(data);
		refreshQuestion();
	});
}

function reset() {

	$.post('/php/ResetTurn.php', function() {
//		console.log("turn reset");
		refreshQuestion();
	});
}
function refreshQuestion() {

	$.post('/php/FetchQuestion.php', function(data) {
//		console.log('fetch');
		if(data == "done"){
			$('#review').css('display',"block");
			$('#nextPage').css('display','none');
			$('#question').html('');
		} else
			$('#question').html(data);
		headingUpdate();
//		console.log(data);
	});
}
function headingUpdate() {

	$.post('/php/loadHeading.php', function(data) {
		console.log(data);
		$('#sectionHeading').html(data);
	});
}

