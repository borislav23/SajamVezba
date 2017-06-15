var sajam = angular.module("sajam", [ 'ngRoute' ]);

sajam.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : '/static/app/html/index.html'
	}).when('/standovi', {
		templateUrl : '/static/app/html/partialHtml/standovi.html'
	}).when('/sajmovi', {
		templateUrl : '/static/app/html/partialHtml/sajmovi.html'
	}).when('/standovi/edit/:id', {
		templateUrl : '/static/app/html/partialHtml/edit-stand.html'
	}).otherwise({
		redirectTo : '/'
	});
} ]);

sajam.controller("myStandovi", function($scope, $location, $http) {

	$scope.base_url_sajmovi = "/api/sajmovi";
	$scope.base_url_standovi = "/api/standovi";

	$scope.pageNum = 0;
	$scope.totalPages = 0;

	$scope.sajmovi = [];
	$scope.standovi = [];

	$scope.noviStand = {};
	$scope.noviStand.zakupac = "";
	$scope.noviStand.povrsina = "";
	$scope.noviStand.sajamId = "";

	$scope.trazeniStand = {};
	$scope.trazeniStand.zakupac = "";
	$scope.trazeniStand.minP = "";
	$scope.trazeniStand.maxP = "";

	var getStandovi = function() {

		var config = {params : {}};

		config.params.pageNum = $scope.pageNum;

		if ($scope.trazeniStand.zakupac != "") {
			config.params.zakupac = $scope.trazeniStand.zakupac;
		}

		if ($scope.trazeniStand.minP != "") {
			config.params.minP = $scope.trazeniStand.minP;
		}

		if ($scope.trazeniStand.maxP != "") {
			config.params.maxP = $scope.trazeniStand.maxP;
		}

		$http.get($scope.base_url_standovi, config)
			.then(function success(data) {
					console.log(data.data);
					$scope.standovi = data.data;
					$scope.totalPages = data.headers('totalPages');
					alert("Uspesno dobavljeni standovi");
				});
	};

	var getSajmovi = function() {
		$http.get($scope.base_url_sajmovi).then(function success(data) {
			console.log(data.data);
			$scope.sajmovi = data.data;
			alert("Uspesno dobavljeni sajmovi");
		});
	};

	getStandovi();
	getSajmovi();
	
	 $scope.dodaj = function(){
	        $http.post($scope.base_url_standovi, $scope.noviStand)
	            .then(function success(data){
	                console.log(data.data);
	                alert("Uspesno dodat stand.");
	                getStandovi();
	            });
	    };

	$scope.back = function(){
		if ($scope.pageNum > 0) {
			$scope.pageNum = $scope.pageNum - 1;
			getStandovi();
		}
	};

	$scope.forward = function() {
		if ($scope.pageNum < $scope.totalPages - 1){
			$scope.pageNum = $scope.pageNum + 1;
			getStandovi();
		}
	};

    $scope.trazi = function () {
        $scope.pageNum = 0;
        getStandovi();
    }
	
    var data = "/sajam/{sajamId}";
    	
    $scope.proceedToDelete = function(id){
    	
    		$http.delete($scope.base_url_standovi + "/" + id + data)
			.then(function success(data){
				console.log(data.data);
				alert("Uspesno je obrisana aktivnost");
				getStandovi();
				
			});	
		};
    

});
