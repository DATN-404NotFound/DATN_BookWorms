angular.module("statistical", [])
	.controller("myCtrl", function($scope, $http) {
		$scope.select = 'hi';
		
		let host = "http://localhost:8080/rest/admin/";
		
		$scope.changeOrder = function() {
			console.log('test' + $scope.select)
			$scope.top5= [];
			let url;
			if($scope.select != "bestSeller"){
				 url =`${host}bestSeller`;
			}else{
				 url =`${host}inventory`;
			}
			$http.get(`http://localhost:8080/rest/admin/bestSeller`).then(resp => {
				$scope.top5 = resp.data;
				console.log("top5: ", resp.data)
			}).catch(error => {
				console.log("Error", error)
			});
		}
		$scope.changeOrder();
	});