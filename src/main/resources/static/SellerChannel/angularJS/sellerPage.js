    var app = angular.module('myApp', ['ngRoute']);
    app.config(function ($locationProvider) {
    $locationProvider.hashPrefix('!');
});
app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: '/Ibook/seller/homePageSeller',
            controller: 'indexController'
        })
        .when('/orderManagement/sales', {
            templateUrl: '/Ibook/seller/orderManagement/sales',
            controller: 'salesController'
        })
        .when('/orderManagement/transport', {
            templateUrl: '/Ibook/seller/orderManagement/transport',
            controller: 'transportController'
        })
        .when('/orderManagement/createProduct', {
            templateUrl: 'templates/SellerChannel/CreateProduct.html',
            controller: 'createProductController'
        })
        .when('/orderManagement/voucher', {
            templateUrl: 'templates/SellerChannel/Voucher.html',
            controller: 'voucherController'
        })
        .when('/orderManagement/createVoucher', {
            templateUrl: 'templates/SellerChannel/CreateVoucher.html',
            controller: 'createVoucherController'
        })
        .when('/orderManagement/reviews', {
            templateUrl: 'templates/SellerChannel/ShopReview.html',
            controller: 'reviewController'
        })
        .when('/shipping/bulkDelivery', {
            templateUrl: 'templates/SellerChannel/BulkDelivery.html',
            controller: 'bulkDeliveryController'
        })
        .when('/shop/shopProfile', {
            templateUrl: '/Ibook/seller/shop/shopProfile',
            controller: 'shopProfileController'
        })
        .when('/shop/shopProfile/change', {
            templateUrl: '/Ibook/seller/shop/shopProfile/change',
            controller: 'changeProfileController'
        })
        .when('/shop/setting/address',{
            templateUrl: '/Ibook/seller/shop/setting/address',
            controller: 'addressSettingController'
        })
        .otherwise({
            redirectTo: '/seller'
        });
});

//Change Profile Setting
app.controller("changeProfileController", function ($scope, $routeParams, $route, $http, $rootScope) {
    let host = "http://localhost:8080/rest/shop/";
    $scope.logoChange = null;
    $scope.getProfileShop = function () {
        $rootScope.shop = [];
        let url = `${host}detail`;
        $http.get(url).then(resp => {
            $rootScope.shop = resp.data;
            // localStorage.setItem("shop",resp.data);
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
            document.getElementById("logo").src = e.target.result;
            $scope.$apply();
        };
        reader.readAsDataURL(e.target.files[0]);
        $scope.logoChange =  e.target.files[0];
    }
    $scope.saveProfileChange = function (file, shopId) {
        let formData;
        formData = new FormData();
        if (file != null){
            formData.append('fileImage',file);
        }
        formData.append('shopId', shopId)
        formData.append("reportProgress", true);
        const headers = {
            'Content-Type': undefined ,
            transformRequest: angular.identity
        };
        let url = `${host}save/profile`;
            $http.post(url, formData,{headers:headers}).then(resp => {
            console.log("Save profile success!!!")
        }).catch(error => {
            console.log("Upload fail", error)
        });

    }
    $scope.changeProfileShop = function () {
        $scope.saveProfileChange($scope.logoChange, $rootScope.shop.shopid);

    }
    $scope.getProfileShop();

});


//Setting Address Shop
app.controller("addressSettingController", function ($scope, $routeParams, $route, $http, $rootScope) {
    let host = "http://localhost:8080/rest/shop/";
    $scope.AddressForm={};
    $scope.Address = [];

    $scope.getAddressShop = function () {
        $scope.Address = [];
        let url = `${host}address`;
        $http.get(url).then(resp => {
            $scope.Address = resp.data;
            // console.log("address: ", $scope.Address);

        }).catch(error => {
            console.log("Error", error)
        });
    }

    $scope.saveAddressShop = function () {
        let url = `${host}address/createOrUpdate`;
        const headers = {
            'Content-Type': "application/json" ,
            transformRequest: angular.identity
        };
        $http.post(url,JSON.stringify($scope.AddressForm),{headers:headers} ).then(resp => {
            console.log("Save address success!!!")
            $scope.getAddressShop();
        }).catch(error => {
            console.log("Save address false")
        });
    }
    $scope.changeActive = function (id) {
        let url = `${host}address/updateActive`;
        const headers = {
            'Content-Type': undefined ,
            transformRequest: angular.identity
        };
        formData = new FormData();
        formData.append('addressShopId', id)
        $http.post(url,formData,{headers:headers} ).then(resp => {
            console.log("Save change active success!!!")
        }).catch(error => {
            console.log("Save change active false!!!!")
        });
    }
    $scope.getAddressShop();
});


