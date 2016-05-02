var socket = io.connect();
var app = angular.module('myApp', ['ngRoute']);

app.config(function ($routeProvider) {
    $routeProvider.when("/cell/:value/:category", {
        controller: "cell",
        templateUrl: "Cell.html"
    })
	.when("/table", {
        controller: "myController",
        templateUrl: "table.html"
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

app.controller('cell', function ($scope, $routeParams, $rootScope)
{
	var pos = ($routeParams.value/200)-1;
	$scope.question = $rootScope.questions[$routeParams.category]["Questions"][pos]["question"];
});

socket.on('displayQuestion', function(data)
{
	window.location.href = "#/cell/" + data.value + "/" + data.category;
});