
angular.module('ui.bootstrap.demo', ['ui.bootstrap']);
angular.module('ui.bootstrap.demo').controller('TypeaheadCtrl', function($scope, $http) {
	$scope.selected = "";
    $http.get("JSON/PROCESSOR.json").then(function(response){
 		$scope.componentsCPU = response.data;
    });
// Any function returning a promise object can be used to load values asynchronously
    $scope.selectedGPU = "";
    $http.get("JSON/GRAPHICSCARD.json").then(function(response){
        $scope.componentsGPU = response.data;
    });

    $scope.selectedMemory = "";
    $http.get("JSON/MEMORY.json").then(function(response){
        $scope.componentsMemory = response.data;
    });

    $scope.selectedMotherboard = "";
    $http.get("JSON/MOTHERBOARD.json").then(function(response){
        $scope.componentsMotherboard = response.data;
    });

    $scope.selectedMemory = "";
    $http.get("JSON/OPTICALDRIVE.json").then(function(response){
        $scope.componentsOD = response.data;
    });

    $scope.selectedPowersupply = "";
    $http.get("JSON/PSU.json").then(function(response){
        $scope.componentsPSU = response.data;
    });

    $scope.selectedPowersupply = "";
    $http.get("JSON/CASE.json").then(function(response){
        $scope.componentsCASE = response.data;
    });

    $scope.selectedSSD = "";
    $http.get("JSON/SSD.json").then(function(response){
        $scope.componentsSSD = response.data;
    });
		
    $scope.selectedAll = "";
    $http.get("JSON/All.json").then(function(response){
        $scope.componentsALL = response.data;
    });

    $scope.computer = {};
    $scope.update = function(selected) {
        $scope.computer = angular.copy(selected);
    };

});


