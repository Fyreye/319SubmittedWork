$(document).ready(function() {
	window.setInterval(function() {
		nextQuestion();
	}, 2000);
	$("button").click(function() {
		checkAnswer();
	});
});


function nextQuestion() {
	$.get("/php/questions.php", function(data) {
		if(data.update)
		{
			if(data.section === "winners")
			{
				window.location.href = data.question;
			}
			else
			{ 
				$("#section").html(data.section);
				$("#question").text(data.question);
			}
		}
	}, "json");
}

function checkAnswer() {
	$answer = $("#answer").val();
	console.log("Checking");
	$.post("/php/checkAnswer.php", {"answer":$answer}, function(data) {
			
	});
}
