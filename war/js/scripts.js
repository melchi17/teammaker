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

		$scope.$parent.teamName = data.propertyMap.name;
		$scope.$parent.pf = data.propertyMap.pf;
		$scope.$parent.pg = data.propertyMap.pg;
		$scope.$parent.c = data.propertyMap.c;
		$scope.$parent.sf = data.propertyMap.sf;
		$scope.$parent.hc = data.propertyMap.hc;
		$scope.$parent.sg = data.propertyMap.sg;
	};
	
	$scope.players = [];
	$http.get('js/players.json', {}).then(function (res) {
		$scope.players = res.data.players;
	});
	
	$scope.coaches = [];
	$http.get('js/coaches.json', {}).then(function (res) {
		$scope.coaches = res.data.coaches;
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
			
			$scope.teamName = data.propertyMap.name;
			$scope.pf = data.propertyMap.pf;
			$scope.pg = data.propertyMap.pg;
			$scope.c = data.propertyMap.c;
			$scope.sf = data.propertyMap.sf;
			$scope.hc = data.propertyMap.hc;
			$scope.sg = data.propertyMap.sg;
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

			$scope.teamName = data.propertyMap.name;
			$scope.pf = data.propertyMap.pf;
			$scope.pg = data.propertyMap.pg;
			$scope.c = data.propertyMap.c;
			$scope.sf = data.propertyMap.sf;
			$scope.hc = data.propertyMap.hc;
			$scope.sg = data.propertyMap.sg;
		})
	}
});
