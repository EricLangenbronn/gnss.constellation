function DisplayController($scope, $routeParams, $location) {

	$scope.gotolistDateTime = function() {
		$location.path("/")
	};
}