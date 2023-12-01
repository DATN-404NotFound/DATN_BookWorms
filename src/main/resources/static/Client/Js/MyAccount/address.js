const app = angular.module("address_app", []);



app.controller("address_ctrl", function ($scope, $http) {

    $scope.callModel = function(addressid){
        console.log("kjsdkj")
        $http.get("/rest/address/"+ addressid).then(resp=>{ 
            console.log("resp = "+ JSON.stringify(resp.data));
            $scope.add = resp.data;

        })
    }

    $scope.updateAdd = function(){ 
        var ad = angular.copy($scope.add);
        $http.post("/rest/address", ad).then(resp =>{ 
            location.href="/myAccount/address";
        })
    }
    $scope.deleteAdd = function(addressid){ 
        console.log(addressid)
        $http.delete("/rest/address/"+ addressid).then(resp =>{ 
            console.log("lsklf")           
            location.href="/myAccount/address";
        })
        
        
    }
});