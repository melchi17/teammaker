var teamApp = angular.module('teamApp', ['ui.bootstrap']);

teamApp.controller('Form', function ($scope, $http) {
	$scope.submit = function() {
		$http({ 
			method: 'POST', 
			data: $scope.formData,
			url:'/api/team' 
		}).success(function( data, status, headers, config ){
			$scope.success(data);
		}).error(function( data, status, headers, config ){
			var data = {
				"key": {
					"kind": "Team",
					"appId": "team-maker-ship-it",
					"id": 5629499534213120,
					"name": null,
					"parent": null,
					"namespace": "",
					"complete": true
				},
				"parent": null,
				"kind": "Team",
				"appId": "team-maker-ship-it",
				"namespace": "",
				"properties": {
					"title": "Ben's Team",
					"pf": "Ben Weisel",
					"pg": "Josh Thompson",
					"c": "Kevin Deenanauth",
					"sf": "Brian Cody",
					"hc": "Gregg Popovich",
					"sg": "Paul Burgess"
				}
			}
			$scope.success(data);
		})
	};
	$scope.success = function(data) {
		$scope.$parent.showResults = true;
		$scope.$parent.yourPermalink = location.origin+'/team/'+data.key.id;

		$scope.$parent.teamName = data.properties.title;
		$scope.$parent.pf = data.properties.pf;
		$scope.$parent.pg = data.properties.pg;
		$scope.$parent.c = data.properties.c;
		$scope.$parent.sf = data.properties.sf;
		$scope.$parent.hc = data.properties.hc;
		$scope.$parent.sg = data.properties.sg;
	};
	
	$scope.players = [];
	$http.get('js/players.json', {}).then(function (res) {
		$scope.players = res.data.players;
	});
});

teamApp.controller('init', function ($scope, $http) {
	var teamID = location.search.indexOf('id=') > -1 ? location.search.replace('?id=', '') : false;

	if( teamID ){

		$http({ 
			method: 'GET', 
			url:'/api/team/'+teamID 
		}).success(function( data, status, headers, config ){
			$scope.showResults = true;
			$scope.yourPermalink = location.origin+'/team/'+data.key.id;
			
			$scope.teamName = data.properties.title;
			$scope.pf = data.properties.pf;
			$scope.pg = data.properties.pg;
			$scope.c = data.properties.c;
			$scope.sf = data.properties.sf;
			$scope.hc = data.properties.hc;
			$scope.sg = data.properties.sg;
		}).error(function( data, status, headers, config ){
			var data = {
				"key": {
					"kind": "Team",
					"appId": "team-maker-ship-it",
					"id": 5629499534213120,
					"name": null,
					"parent": null,
					"namespace": "",
					"complete": true
				},
				"parent": null,
				"kind": "Team",
				"appId": "team-maker-ship-it",
				"namespace": "",
				"properties": {
					"title": "Ben's Team",
					"pf": "Ben Weisel",
					"pg": "Josh Thompson",
					"c": "Kevin Deenanauth",
					"sf": "Brian Cody",
					"hc": "Gregg Popovich",
					"sg": "Paul Burgess"
				}
			}
			$scope.showResults = true;
			$scope.yourPermalink = location.origin+'/team/'+data.key.id;

			$scope.teamName = data.properties.title;
			$scope.pf = data.properties.pf;
			$scope.pg = data.properties.pg;
			$scope.c = data.properties.c;
			$scope.sf = data.properties.sf;
			$scope.hc = data.properties.hc;
			$scope.sg = data.properties.sg;
		})
	}
});
