var teamApp = angular.module('teamApp', ['ui.bootstrap']);

teamApp.controller('Form', function ($scope, $http) {
	$scope.submit = function() {
		$http({ 
			method: 'POST', 
			data: $scope.formData,
			url:'/api/team' 
		}).success(function( data, status, headers, config ){
			$scope.success(data);
		})
	};
	$scope.success = function(data) {
		$scope.$parent.showResults = true;
		$scope.$parent.yourPermalink = location.origin+'/team/'+data.id;

		$scope.$parent.teamName = data.propertyMap.name;
		$scope.$parent.pf = data.propertyMap.pf;
		$scope.$parent.pfImage = 'images/player-basketball.jpg';
		$scope.$parent.pg = data.propertyMap.pg;
		$scope.$parent.pgImage = data.propertyMap.pg.img ? data.propertyMap.pg.img : 'images/player-basketball.jpg';
		$scope.$parent.c = data.propertyMap.c;
		$scope.$parent.cImage = data.propertyMap.c.img ? data.propertyMap.c.img : 'images/player-basketball.jpg';
		$scope.$parent.sf = data.propertyMap.sf;
		$scope.$parent.sfImage = data.propertyMap.sf.img ? data.propertyMap.sf.img : 'images/player-basketball.jpg';
		$scope.$parent.hc = data.propertyMap.hc;
		$scope.$parent.hcImage = data.propertyMap.hc.img ? data.propertyMap.hc.img : 'images/player-basketball.jpg';
		$scope.$parent.sg = data.propertyMap.sg;
		$scope.$parent.sgImage = data.propertyMap.sg.img ? data.propertyMap.sg.img : 'images/player-basketball.jpg';
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
	$scope.login = function() {
		$http({
			method: 'GET',
			url: 'api/user'
		}).success(function (data, status, headers, config) {
			$scope.userName = data.email;
		})
	};
	
	$scope.my_teams = function() {
		$http({
			method: 'GET',
			url: 'api/teams'
		}).success(function (data, status, headers, config) {
			$scope.showMyTeams = true;
			$scope.showResults = false;
			$scope.teams = data;
		}) 
	};
	
	$scope.teamID = location.search.indexOf('id=') > -1 ? location.search.replace('?id=', '') : false;

	if( $scope.teamID ){

		$http({ 
			method: 'GET', 
			url:'/api/team/'+$scope.teamID 
		}).success(function( data, status, headers, config ){
			$scope.showResults = true;
			$scope.yourPermalink = location.origin+'/team/'+data.id;
			
			$scope.teamName = data.propertyMap.name;
			$scope.pf = data.propertyMap.pf;
			$scope.pfImage = 'images/player-basketball.jpg';
			$scope.pg = data.propertyMap.pg;
			$scope.pgImage = data.propertyMap.pg.img ? data.propertyMap.pg.img : 'images/player-basketball.jpg';
			$scope.c = data.propertyMap.c;
			$scope.cImage = data.propertyMap.c.img ? data.propertyMap.c.img : 'images/player-basketball.jpg';
			$scope.sf = data.propertyMap.sf;
			$scope.sfImage = data.propertyMap.sf.img ? data.propertyMap.sf.img : 'images/player-basketball.jpg';
			$scope.hc = data.propertyMap.hc;
			$scope.hcImage = data.propertyMap.hc.img ? data.propertyMap.hc.img : 'images/player-basketball.jpg';
			$scope.sg = data.propertyMap.sg;
			$scope.sgImage = data.propertyMap.sg.img ? data.propertyMap.sg.img : 'images/player-basketball.jpg';
		})
	}
});
