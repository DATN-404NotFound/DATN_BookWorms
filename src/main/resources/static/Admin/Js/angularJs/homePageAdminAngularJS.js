angular.module("statistical", [])
	.controller("myCtrl", function($scope, $http) {
		$scope.select;
		let host = "http://localhost:8080/rest/admin/";
		
		$scope.changeOrder = function() {
			$scope.top5= [];
			let url;
			if($scope.select != "bestSeller"){
				 url =`${host}bestSeller`;
			}else{
				 url =`${host}inventory`;
			}
			$http.get(url).then(resp => {
				$scope.top5 = resp.data;
				console.log("top5: ", resp.data)
			}).catch(error => {
				console.log("Error", error)
			});
		}
		$scope.changeOrder();
	});