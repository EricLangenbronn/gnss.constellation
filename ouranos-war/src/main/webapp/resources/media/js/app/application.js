angular.module('ouranosApp', [ 'displayService' ]).config(
		[ '$routeProvider', function($routeProvider) {
			$routeProvider.when('/display', {
				templateUrl : 'views/display.jsp',
				controller : DisplayController
			}).otherwise({
				redirectTo : '/display'
			});
		} ]);