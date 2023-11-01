var app = angular.module('myApp', ['ngRoute']);
app.config(function($locationProvider) {
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
        .otherwise({
            redirectTo: '/seller'
        });
});
app.controller("changeProfileController", function ($scope, $routeParams, $route, $http, $rootScope) {
    let host = "http://localhost:8080/rest/shop/";
    $scope.shop = {};
    // $scope.shop = {
    //     name: s,
    //     phoneNumber: "",
    //     logo: "",
    //     description: "",
    //     shopAddress: ""
    // };
    $scope.getProfileShop = function () {
        $scope.shop = {};
        let url = `${host}detail`;
        $http.get(url).then(resp => {
            $scope.shopDetail = resp.data;
            console.log("shop: ", resp.data)
        }).catch(error => {
            console.log("Error", error)
        });
    }
    $scope.changeProfileShop = function () {
        console.log("shop: ", "test111")
    }
    $scope.getProfileShop();
});
