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
            controller: 'salesOrderManagementController'
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
        .when('/shop/setting/address', {
            templateUrl: '/Ibook/seller/shop/setting/address',
            controller: 'addressSettingController'
        })
        .when('/shop/setting/shipping', {
            templateUrl: '/Ibook/seller/shop/setting/shipping',
            controller: 'shippingSettingController'
        })
        .when('/shop/setting/account', {
            templateUrl: '/Ibook/seller/shop/setting/account',
            controller: 'accountSettingController'
        })
        .when('/sales', {
            templateUrl: '/Ibook/seller/shop/sales',
            controller: 'salesController'
        })
        .when('/finance/revenue', {
            templateUrl: '/Ibook/seller/shop/finance/revenue',
            controller: 'revenueFinanceController'
        })
        .otherwise({
            redirectTo: '/seller'
        });
});

//revenue
app.controller("revenueFinanceController", function ($scope, $routeParams, $route, $http, $rootScope,$timeout) {
    let host = "http://localhost:8080/rest/revenueFinance/";
    $scope.paymentTotal=[];
    $scope.getRevenueFinance = function () {
        $scope.shopPayment=[];
        let url = `${host}getRevenue`;
        $http.get(url).then(resp => {
            $scope.shopPayment = resp.data;
            console.log("shopPayment:", $scope.shopPayment)
        }).catch(error => {
            console.log("Error", error)
        });
    }

    $scope.sendRequestPayment = function () {
        let url = `${host}sendRequestPayment`;
        var currentDate = new Date();
        let formData;
        formData = new FormData();

        formData.append('paymentTotal', $scope.paymentTotal);
        formData.append("reportProgress", true);
        const headers = {
            'Content-Type': undefined,
            transformRequest: angular.identity
        };
        $http.post(url, formData, {headers: headers}).then(resp => {

            console.log("Result: ", "success!!!!!!")
        }).catch(error => {
            console.log("Error", error)
        });
    }
    $scope.getRevenueFinance();

});
//sales Analysis
app.controller("salesController", function ($scope, $routeParams, $route, $http, $rootScope,$timeout) {


    let host = "http://localhost:8080/rest/salesAnalysis/";
    var currentDate = new Date();
    $scope.year = currentDate.getFullYear();
    $scope.salesAnalysis = [];
    $scope.salesAnalysisNow = [];
    $scope.getSalesAnalysis = function () {
        $scope.salesAnalysis = [];
        $scope.salesAnalysisNow = [];
        let url = `${host}getSalesAnalysis`;
        var currentMonth = currentDate.getMonth() + 1;
        let formData;
        formData = new FormData();
        formData.append('year', $scope.year);
        formData.append("reportProgress", true);
        const headers = {
            'Content-Type': undefined,
            transformRequest: angular.identity
        };
        $http.post(url, formData, {headers: headers}).then(resp => {
            $scope.salesAnalysis = resp.data;
            $scope.salesAnalysisNow.push(resp.data[currentMonth]);
            $scope.salesAnalysisNow.push(resp.data[currentMonth - 1]);
            $timeout(function () {
                $scope.initDataChart(resp.data);
            }, 500);
            console.log("data", $scope.salesAnalysis)
            console.log("data", $scope.salesAnalysisNow)
        }).catch(error => {
            console.log("Error", error)
        });


    }
    $scope.growthIncreases = function (firtsMonth, secondMonth) {
        var growth = 0;

        if (Number(secondMonth) != 0) {
            growth = (Number(firtsMonth) / Number(secondMonth)) * 100;
        }
        return growth;
    }
    $scope.initDataChart = function (salesAnalysis) {
        $scope.mSales = [];
        $scope.order = [];
        $scope.cRate = [];
        $scope.pViews = [];
        $scope.sOrder = [];
        for (let i = 1; i < salesAnalysis.length; i++) {
            $scope.mSales.push(salesAnalysis[i].monthlySales)
            $scope.order.push($scope.salesAnalysis[i].orders)
            $scope.cRate.push($scope.salesAnalysis[i].conversionRate)
            $scope.pViews.push($scope.salesAnalysis[i].pagesViews)
            $scope.sOrder.push($scope.salesAnalysis[i].salesPerOrder)
        }
        $timeout(function (){
            var options = {
                series: [{
                    name: "Monthly Sales",
                    data: $scope.mSales
                },
                    {
                        name: "Orders",
                        data: $scope.order
                    },
                    {
                        name: 'ConversionRate',
                        data:$scope.cRate
                    }
                    ,
                    {
                        name: 'Page Views',
                        data:$scope.pViews
                    }
                    ,
                    {
                        name: 'Sales Per Order',
                        data:$scope.sOrder
                    }
                ],
                chart: {
                    height: 350,
                    type: 'line',
                    zoom: {
                        enabled: false
                    },
                },
                dataLabels: {
                    enabled: false
                },
                stroke: {
                    width: [5, 7, 5],
                    curve: 'straight',
                    dashArray: [0, 8, 5]
                },
                title: {
                    text: 'Page Statistics',
                    align: 'left'
                },
                legend: {
                    tooltipHoverFormatter: function (val, opts) {
                        return val + ' - ' + opts.w.globals.series[opts.seriesIndex][opts.dataPointIndex] + ''
                    }
                },
                markers: {
                    size: 0,
                    hover: {
                        sizeOffset: 6
                    }
                },
                xaxis: {
                    categories: ['01 Jan', '02 Feb', '03 Mar', '04 Apr', '05 May', '06 Jun', '07 July', '08 Aug', '09 Sep',
                        '10 Oct', '11 Nov', '12 Dec'
                    ],
                },
                tooltip: {
                    y: [
                        {
                            title: {
                                formatter: function (val) {
                                    return val + " (mins)"
                                }
                            }
                        },
                        {
                            title: {
                                formatter: function (val) {
                                    return val + " per session"
                                }
                            }
                        },
                        {
                            title: {
                                formatter: function (val) {
                                    return val;
                                }
                            }
                        }
                    ]
                },
                grid: {
                    borderColor: '#f1f1f1',
                }
            };

            var chart = new ApexCharts(document.querySelector("#myChart"), options);
            chart.render();
        },500);
        console.log("data",$scope.mSales );
    }
    $scope.getCategoryRanking = function () {
            $scope.categoryRanking = [];
            let url = `${host}categoryRanking`;
            $http.get(url).then(resp => {
                $scope.categoryRanking = resp.data;
                console.log("categoryRanking:", $scope.categoryRanking)
            }).catch(error => {
                console.log("Error", error)
            });

    }

    $scope.getBookRankingToSales = function (){
        $scope.bookRankingToSales = [];
        let url = `${host}accordingToSales`;
        $http.get(url).then(resp => {
            $scope.bookRankingToSales = resp.data;
            console.log("bookRankingToSales:", $scope.bookRankingToSales)
        }).catch(error => {
            console.log("Error", error)
        });
    }

    $scope.getBookRankingToNumber = function (){
        $scope.bookRankingToNumber = [];
        let url = `${host}productNumber`;
        $http.get(url).then(resp => {
            $scope.bookRankingToNumber = resp.data;
            console.log("bookRankingToNumber:", $scope.bookRankingToNumber)
        }).catch(error => {
            console.log("Error", error)
        });
    }

    $scope.getBookRankingToView = function (){
        $scope.bookRankingToView = [];
        let url = `${host}accordingToView`;
        $http.get(url).then(resp => {
            $scope.bookRankingToView = resp.data;
            console.log("bookRankingToView:", $scope.bookRankingToView)
        }).catch(error => {
            console.log("Error", error)
        });
    }

    $scope.getSalesAnalysis();
    $scope.getCategoryRanking();
    $scope.getBookRankingToSales();
    $scope.getBookRankingToNumber();
    $scope.getBookRankingToView();
});
//Display account shop
app.controller("accountSettingController", function ($scope, $routeParams, $route, $http, $rootScope) {
    let host = "http://localhost:8080/rest/shop/";
    $scope.getAccountShop = function () {
        $scope.account = [];
        let url = `${host}account`;
        $http.get(url).then(resp => {
            $scope.account = resp.data;
            console.log("account", $scope.account)
        }).catch(error => {
            console.log("Error", error)
        });
    }
    $scope.getAccountShop();
});

//Display profile
app.controller("shopProfileController", function ($scope, $routeParams, $route, $http, $rootScope) {
    let host = "http://localhost:8080/rest/shop/";
    $scope.getProfileShop = function () {
        $scope.shop = [];
        let url = `${host}detail`;
        $http.get(url).then(resp => {
            $scope.shop = resp.data;
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
    $scope.getProfileShop();
});
//Change Profile Setting
app.controller("changeProfileController", function ($scope, $routeParams, $route, $http, $rootScope, $window) {
    let host = "http://localhost:8080/rest/shop/";
    $scope.logoChange = null;
    $scope.getProfileShop = function () {
        $scope.shop = [];
        let url = `${host}detail`;
        $http.get(url).then(resp => {
            $scope.shop = resp.data;
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
        $scope.logoChange = e.target.files[0];
    }
    $scope.saveProfileChange = function (file, shopId) {
        let formData;
        formData = new FormData();
        if (file != null) {
            formData.append('fileImage', file);
        }
        formData.append('shopId', shopId)
        formData.append("reportProgress", true);
        const headers = {
            'Content-Type': undefined,
            transformRequest: angular.identity
        };
        let url = `${host}save/profile`;
        $http.post(url, formData, {headers: headers}).then(resp => {

            console.log("Save profile success!!!")
            $window.alert("Save profile success!!!");
        }).catch(error => {
            console.log("Save profile", error)
            $window.alert("Save profile false!!!");
        });

    }
    $scope.saveInfoProfile = function (shop) {
        let url = `${host}saveInfoShop`;
        const headers = {
            'Content-Type': "application/json",
            transformRequest: angular.identity
        };
        $http.post(url, JSON.stringify(shop), {headers: headers}).then(resp => {
            console.log("Save Info success!!!")
            $scope.getAddressShop();
        }).catch(error => {
            console.log("Save Info false!!!")
        });
    }
    $scope.changeProfileShop = function () {
        $scope.saveProfileChange($scope.logoChange, $scope.shop.shopid);
        $scope.saveInfoProfile($scope.shop);
    }
    $scope.getProfileShop();

});


//Setting Address Shop
app.controller("addressSettingController", function ($scope, $routeParams, $route, $http, $rootScope,) {
    let host = "http://localhost:8080/rest/shop/";
    $scope.AddressForm = {};
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
            'Content-Type': "application/json",
            transformRequest: angular.identity
        };
        $http.post(url, JSON.stringify($scope.AddressForm), {headers: headers}).then(resp => {
            console.log("Save address success!!!")
            $scope.getAddressShop();
        }).catch(error => {
            console.log("Save address false")
        });
    }
    $scope.changeActive = function (id) {
        let url = `${host}address/updateActive`;
        const headers = {
            'Content-Type': undefined,
            transformRequest: angular.identity
        };
        formData = new FormData();
        formData.append('addressShopId', id)
        $http.post(url, formData, {headers: headers}).then(resp => {
            console.log("Save change active success!!!")
        }).catch(error => {
            console.log("Save change active false!!!!")
        });
    }
    $scope.getAddressShop();
});


