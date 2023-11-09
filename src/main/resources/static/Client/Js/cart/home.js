const app2 = angular.module("home_app", []);

app2.controller("home_ctrl", function ($scope, $http) {
$scope.booksCate = [];
    $scope.loadAll = function (){
        console.log("aaaa") 
        $http.get("/rest/books/cate/4").then(resp =>{ 	
			$scope.a = 4;
			$scope.b = 0;
            $scope.booksCate = resp.data;	
        })
    }

	$scope.edu = function(id,a,b){
		$http.get("/rest/books/cate/"+id).then(resp =>{ 	
			$scope.a = a;
			$scope.b = b;
            $scope.booksCate = resp.data;	
			$scope.booksCate.forEach(i=>{ 
			$scope.setImage(i.bookid,id)
			})
        })
	}
	$scope.setImage = function (bookId,cate) {
		let url = `http://localhost:8080/rest/imagebook/` + bookId;
		$http.get(url).then(resp => {
			var a = [];
			a = (resp.data);
			console.log("đmmmm"+ a[0].name)
			switch (cate){ 
				case 2:{ 
					document.getElementById('imgs2' + bookId).src = "/Client/images/" + a[0].name
					break;
				}
				case 4:{ 
					document.getElementById('imgs4' + bookId).src = "/Client/images/" + a[0].name
					break;
				}
				case 7:{ 
					document.getElementById('imgs7' + bookId).src = "/Client/images/" + a[0].name
					break;
				}
			}
			
		}).catch(error => {
			console.log("Error", error)
		});;
	}
    $scope.loadAll();

	$scope.next = function(id,cate){ 
		console.log("next"+id)
		$scope.b = id+1;
		$scope.edu(cate,4,$scope.b)
	
	}

	$scope.prev = function(id,cate){ 
		console.log("next"+id)
		$scope.b = id-1;
				$scope.edu(cate,4,$scope.b)
	}

});