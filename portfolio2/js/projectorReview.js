$(document).ready(function() {
	$("#reveal").click(function() {
		 revealButton();
	});
	$("#nextRPage").click(function() {
		 nextRQuestion();
	});	
	$("#cry").click(function() {
		resetR();
	});
	$("img").click(function() {
		window.location.href = './';
	});
	$('#question').ready(function(){
		resetR();
	});
	
});

function revealButton() {
	
	$('#answer').css('visibility', 'visible');
	$('#reveal').css('visibility', 'hidden');
	
	
}

function nextRQuestion() {

//	console.log('update');
	
	$.post('/php/reviewTurn.php', function(data) {
		refreshRQuestion();
	});
	$('#answer').css('visibility', 'hidden');
	$('#reveal').css('visibility', 'visible');
}

function resetR() {

	$.post('/php/reviewReset.php', function(data) {
		
		refreshRQuestion();
	});
}
function refreshRQuestion() {

	$.post('/php/reviewFetchQuestion.php', function(data) {
//		console.log('fetch');
		if(data.que === "done"){
			window.location.href = "winners.html";
		} else{

			$('#question').html(data.que);
			$('#answer').html(data.ans);
			$('sectionHeading').html(data.head);
		}		
		

	}, "json");
}

