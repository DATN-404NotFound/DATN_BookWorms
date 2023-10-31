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
        .when('/shpping/bulkDelivery', {
            templateUrl: 'templates/SellerChannel/BulkDelivery.html',
            controller: 'bulkDeliveryController'
        })
        .when('/profile/shopProfile', {
            templateUrl: 'templates/SellerChannel/shopProfile.html',
            controller: 'bulkDeliveryController'
        })
        .otherwise({
            redirectTo: '/seller'
        });
});