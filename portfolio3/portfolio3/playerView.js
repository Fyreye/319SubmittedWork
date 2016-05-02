var socket = io.connect();
var app = angular.module('myApp', ['ngRoute']);

app.config(function ($routeProvider) {
    $routeProvider.when("/game", {
    	controller: "buzz",
    	templateUrl: "Game.html"
    })
    .when("/login", {
    	controller: "login",
    	templateUrl: "login.html"
    })
    .otherwise( {
    	redirectTo: '/login'
    });
});

app.controller('login', function ($scope, $rootScope)
{
	$scope.username = '';

	$scope.login = function()
	{
		$rootScope.username = $scope.username;
		socket.emit('register', {"username": $scope.username});
	};

	socket.on('registered', function(data)
	{
		window.location.href = '#/game';
	});
});

app.controller('buzz', function ($scope, $rootScope)
{
	$scope.buzz = function()
	{
		socket.emit('buzz', {"username": $rootScope.username});
	};
});

app.controller('playerController', function($scope)
{
	if($scope.players === undefined)
		socket.emit('getPlayers', 'get');
	socket.on('players', function(data)
	{
		$scope.players = data;
		$scope.$apply();
	});
});
