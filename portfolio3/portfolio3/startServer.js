var util = require('util');
var path = require('path');
var http = require('http');
var url = require('url');
var fs = require('fs');
var io = require('socket.io');

//----------------------------------------
// What follows is to set up the http
// server to handle requests
//----------------------------------------

var server = http.createServer();

var questions;
var players = [];
var active = null;
var ready = false;

(function()
{
		questions= JSON.parse(fs.readFileSync('questions.json', 'utf-8'));
})();

// attach handler
server.on('request', function (req,res)
{
	var file = path.normalize('.' + req.url);
	fs.exists(file, function(exists)
	{
		if (exists)
		{
			var rs = fs.createReadStream(file);

			rs.on('error', function()
			{
				res.writeHead(500); // error status
				res.end('Internal Server Error');
			});

			res.writeHead(200); // ok status

			// PIPE the read stream with the RESPONSE stream
			rs.pipe(res);
		} 
		else
		{
			res.writeHead(404); // error status
			res.end('NOT FOUND');
		}
	});
}); // end server on handler

server.listen(1337, function() {
	console.log("Server listening on: http://localhost:1337");
});

//----------------------------------------
// What follows is to set up the websockets
// to handle communication between the server
// and its clients
//----------------------------------------

var listener = io.listen(server);

listener.sockets.on('connection', function(socket)
{
	socket.emit('questions', questions);
	socket.emit('players', players);

	socket.on('getPlayers', function(data)
	{
		socket.emit('players', players);
	});

	socket.on('register', function(data)
	{
		players.push({"name": data.username, "score": 0});
		socket.emit('registered', 'good');
		socket.broadcast.emit('players', players);
	});

	socket.on('updateScore', function(data)
	{
		var back = false;
		for(var i = 0; i < players.length; i++)
		{
			if(players[i]["name"] === data.username)
			{
				if(data.action === "add")
				{
					players[i]["score"] = parseInt(players[i]["score"]) + parseInt(data.score);
					back = true;
				}
				else
				{
					players[i]["score"] = parseInt(players[i]["score"]) - parseInt(data.score);
				}
				break;
			}
		}
		socket.emit('players', players);
		socket.broadcast.emit('players', players);
		active = null;
		var player = "N/A";
		socket.broadcast.emit('playerBuzz', player);
		if(back)
		{
			ready = false;
			var pos = (data.score/200)-1;
			questions[data.category]["Questions"][pos]["status"] = "hidden";

			socket.emit('displayTable', questions);
			socket.broadcast.emit('displayTable', questions);
		}
	});

	socket.on('timeOut', function(data)
	{
		active = null;
		ready = false;
		var pos = (data.score/200)-1;
		questions[data.category]["Questions"][pos]["status"] = "hidden";

		socket.emit('displayTable', questions);
		socket.broadcast.emit('displayTable', questions);
	});

	socket.on('goToQ', function(data)
	{
		ready = true;
		active = null;
		socket.broadcast.emit('displayQuestion', {"value": data.value, "category": data.category});
	});

	socket.on('buzz', function(data)
	{
		if(ready)
		{
			if(active === null)
			{
				active = data.username;
				socket.broadcast.emit('playerBuzz', data.username);
			}
		}
	});
});
