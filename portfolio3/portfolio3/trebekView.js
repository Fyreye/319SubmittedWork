var socket = io.connect();
var app = angular.module('myApp', ['ngRoute']);

app.config(function ($routeProvider)
{
    $routeProvider.when("/table", {
        controller: "myController",
        templateUrl: "table.html"
    })
    .when("/question/:value/:category", {
    	controller: "cell",
    	templateUrl: "trebekCell.html"
    })
   .otherwise( {
    	redirectTo: '/table'
    });
});

app.controller('myController', function ($scope, $rootScope)
{
	$scope.categories = $rootScope.questions;

	socket.on('questions', function(data)
	{
		$rootScope.questions = data;
		$scope.categories = $rootScope.questions;
		if($scope.categories !== null)
		{
	 		$scope.$apply();
	 	}
	});

	socket.on('displayTable', function(data)
	{
		$rootScope.questions = data;
		window.location.href = "#/table";
	});
});

app.controller('playersController', function($scope)
{
	if($scope.players === undefined)
		socket.emit('getPlayers', 'get');
	socket.on('players', function(data)
	{
		$scope.players = data;
		$scope.$apply();
	});
});

app.controller('cell', function ($scope, $routeParams, $rootScope)
{
	socket.emit('goToQ', {"category": $routeParams.category, "value": $routeParams.value});
	var pos = ($routeParams.value/200)-1;
	$scope.answer = $rootScope.questions[$routeParams.category]["Questions"][pos]["answer"];
	$scope.question = $rootScope.questions[$routeParams.category]["Questions"][pos]["question"];
	
	socket.on('playerBuzz', function(data)
	{
		$scope.username = data;
		$scope.$apply();
	});

	$scope.yes = function()
	{
		socket.emit('updateScore', {"username": $scope.username, "action": "add", "score":$routeParams.value, "category": $routeParams.category});
	};
	$scope.no = function()
	{
		socket.emit('updateScore', {"username": $scope.username, "action": "awh hell naw", "score":$routeParams.value, "category": $routeParams.category});
	};
	$scope.timeOut = function()
	{
		socket.emit('timeOut', {"score":$routeParams.value, "category": $routeParams.category});
	};	
});