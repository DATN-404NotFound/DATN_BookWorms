const app = angular.module("myPersonal", []);
app.controller("myPersonalCtrl", function ($scope, $http) {

    let host = "http://localhost:8080/rest/shop/";
    $scope.logoChange = null;
    $scope.getProfileShop = function () {
        $scope.shop = [];
        let url = `${host}detail`;
        $http.get(url).then(resp => {
            $scope.shop = resp.data;
            localStorage.setItem("shop", resp.data);
            console.log("shop: ", resp.data);
            $scope.initLogo(resp.data.logo);
        }).catch(error => {
            console.log("Error", error)
        });
    }

    $scope.initLogo = function (logo) {
        if (logo == null) {
            console.log("Error", logo);
            document.getElementById("logo").src = "http://bootdey.com/img/Content/avatar/avatar1.png";
        } else {
            document.getElementById("logo").src = "/SellerChannel/images/" + logo;
        }
    }

    $scope.changeImage = function (e) {
        var reader = new FileReader();
        reader.onload = function (e) {
            document.getElementById("logoMypersonal").src = e.target.result;
            $scope.$apply();
        };
        reader.readAsDataURL(e.target.files[0]);
        $scope.logoChange = e.target.files[0];
    }
});
