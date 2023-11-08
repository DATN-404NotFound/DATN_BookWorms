const app = angular.module("cart_app", []);

app.controller("cart_ctrl", function ($scope, $http) {
    $scope.books = [];
    $scope.loadAll = function (){
        console.log("aaaa") 
        $http.get("/rest/books/cate").then(resp =>{ 
            $scope.books = resp.data;
        })
    }
    $scope.loadAll();
});