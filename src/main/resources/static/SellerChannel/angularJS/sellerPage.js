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
        .when('/shop/TranportChannel/Transport', {
            templateUrl: '/Ibook/seller/shop/TranportChannel/Transport',
            controller: 'transportController'
        })
        .when('/shop/TranportChannel/CreateProduct', {
            templateUrl: '/Ibook/seller/shop/TranportChannel/CreateProduct',
            controller: 'createProductController'
        })
        .when('/shop/TranportChannel/Voucher', {
            templateUrl: '/Ibook/seller/shop/TranportChannel/Voucher',
            controller: 'voucherController'
        })
        .when('/shop/TranportChannel/CreateVoucher', {
            templateUrl: '/Ibook/seller/shop/TranportChannel/CreateVoucher',
            controller: 'createVoucherController'
        })
        .when('/shop/TranportChannel/ShopRewiew', {
            templateUrl: '/Ibook/seller/shop/TranportChannel/ShopRewiew',
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
        .when('/finance/accountBalance',{
            templateUrl: '/Ibook/seller/shop/finance/bankAccountBalance',
            controller: 'bankAccountController'
        })
        .when('/finance/createBankAccount',{
            templateUrl: '/Ibook/seller/shop/finance/createBankAccount',
            controller: 'createBankAccountController'
        })
        .otherwise({
            redirectTo: '/seller'
        });
});
app.controller("createBankAccountController", function ($scope, $routeParams, $route, $http, $rootScope,$timeout,$window) {
    let host = "http://localhost:8080/rest/revenueFinance/";
    $scope.accountBalance={};
    $scope.getAccountShop = function () {
        $scope.account = [];
        $http.get(`http://localhost:8080/rest/shop/account`).then(resp => {
            $scope.account = resp.data;
        }).catch(error => {
            console.log("Error", error)
        });
    }

    $scope.saveAccountBalance = function () {
        $scope.accountBalance.userid = $scope.account.userid;
        let url = `${host}saveAccountBalance`;
        const headers = {
            'Content-Type': "application/json",
            transformRequest: angular.identity
        };
        $http.post(url, JSON.stringify($scope.accountBalance), {headers: headers}).then(resp => {
            console.log("Save Account  success!!!")
            $scope.getBankAccountBalance();
            $window.alert("Save Account  success!!!");
        }).catch(error => {
            console.log("Save Account false!!!")
            $window.alert("Save Account false!!!");
        });
    }
    $scope.getAccountShop();
})
//Bank account balance
app.controller("bankAccountController", function ($scope, $routeParams, $route, $http, $rootScope,$timeout) {
    let host = "http://localhost:8080/rest/revenueFinance/";
    $scope.getBankAccountBalance = function () {
        $scope.accountBalance=[];
        let url = `${host}getAccountBalance`;
        $http.get(url).then(resp => {
            $scope.accountBalance = resp.data;
            console.log("accountBalance:", $scope.accountBalance)
        }).catch(error => {
            console.log("Error", error)
        });
    }


    $scope.getBankAccountBalance();
})
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
    $scope.getAnalysisFinance = function () {
        $scope.analysisFinance=[];
        let url = `${host}getAnalysisFinance`;
        $http.get(url).then(resp => {
            $scope.analysisFinance = resp.data;
            console.log("analysisFinance:", $scope.analysisFinance)
        }).catch(error => {
            console.log("Error", error)
        });
    }
    $scope.getListFinance = function () {
        $scope.listPayment=[];
        let url = `${host}getListFinance`;
        $http.get(url).then(resp => {
            $scope.listPayment = resp.data;
            console.log("listPayment:", $scope.listPayment)
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
            $scope.getRevenueFinance();
            $scope.getAnalysisFinance();
            $scope.getListFinance();
            console.log("Result: ", "success!!!!!!")
            $window.alert("Send request payment success!!!");

        }).catch(error => {
            console.log("Error", error)
            $window.alert("Send request payment false!!!");
        });
    }
    $scope.getRevenueFinance();
    $scope.getAnalysisFinance();
    $scope.getListFinance();
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
//************************************************************************Tung dev seller (Dep trai vai lone)

    //Create Product
app.controller('createProductController', function($scope, BookService, $http) {
    // Lấy tên các danh mục
    BookService.getCategories().then(function(response) {
        $scope.categories = response.data;
    }, function(error) {
        console.error('Lỗi khi lấy tên danh mục:', error);
    });

    // Lấy tên các nhà xuất bản
    BookService.getPublishingCompanies().then(function(response) {
        $scope.publishingCompanies = response.data;
    }, function(error) {
        console.error('Lỗi khi lấy tên nhà xuất bản:', error);
    });

    $scope.createBook = function () {
        var formData = new FormData();
        formData.append('bookname', $scope.bookname);
        formData.append('language', $scope.language);
        formData.append('size', $scope.size);
        formData.append('weight', $scope.weight);
        formData.append('totalpage', $scope.totalpage);
        formData.append('publishingyear', $scope.publishingyear);
        formData.append('price', $scope.price);
        formData.append('quantity', $scope.quantity);
        formData.append('statues', $scope.statues);
        formData.append('publishingcompanyid', $scope.publishingcompanyid);
        formData.append('isactive', $scope.isactive);
        formData.append('category', $scope.category);

        angular.forEach($scope.images, function (image) {
            formData.append('images', image);
            console.log($scope.images)
        });

        $http.post('/rest/books/create', formData, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(function (response) {
            console.log(response.data);
            alert('Book created successfully!');
            // Redirect or perform any other actions after successful creation
        }, function (error) {
            console.error(error);
            alert('Error creating book. Please try again.');
        });
    };
});
app.service('BookService', function($http) {
    // Dịch vụ để lấy tên các danh mục
    this.getCategories = function() {
        return $http.get('/rest/books/names');
    };

    // Dịch vụ để lấy tên các nhà xuất bản
    this.getPublishingCompanies = function() {
        return $http.get('/rest/books/publishingcompany');
    };
});
                    //Add
//Tranport
app.controller('transportController', function($scope, $routeParams, $route, $http, $rootScope) {
    $scope.pageSize = 5; // Number of items per page
    $scope.currentPage = 1; // Current page
    $scope.totalPages = 1
    $scope.findByOrderStatusId = function(orderstatusid) {
        $scope.bookings = [];
        if (orderstatusid === 0) {
            $http.get('/rest/tranportChannel/all')
                .then(function(response) {
                    $scope.bookings = response.data;
                    $scope.totalPages = Math.ceil($scope.bookings.length / $scope.pageSize);
                    $scope.setPage(1); // Set initial page
                });
        } else {
            $http.get('/rest/tranportChannel/' + orderstatusid)
                .then(function(response) {
                    $scope.bookings = response.data;
                    $scope.totalPages = Math.ceil($scope.bookings.length / $scope.pageSize);
                    $scope.setPage(1); // Set initial page
                });
        }
    };
    $scope.findByOrderStatusId();
    $scope.setPage = function (page) {
        console.log('Current Page:', $scope.currentPage);
        console.log('Total Pages:', $scope.totalPages);
        if (page < 1 || page > $scope.totalPages) {
            return;
        }
        $scope.currentPage = page;
        var startIndex = (page - 1) * $scope.pageSize;
        var endIndex = startIndex + $scope.pageSize;
        $scope.paginatedBooks = $scope.bookings.slice(startIndex, endIndex);
        console.log('setPage called with page:', page);
        // ... (rest of the code)

        console.log('currentPage:', $scope.currentPage);
        console.log('paginatedBooks:', $scope.paginatedBooks);
    };
    $scope.getPages = function () {
        return new Array($scope.totalPages).fill().map((_, index) => index + 1);
    };

});


    //Voucher
    //Create Voucher
app.controller('createVoucherController', function($scope, $http) {
    $scope.pageSize = 5; // Number of items per page
    $scope.currentPage = 1; // Current page
    $scope.totalPages = 1
    $scope.initInfoProduct = function () {
        $scope.books = [];
        $http.get('/rest/books')
            .then(function(response) {
                $scope.books = response.data;
                $scope.totalPages = Math.ceil($scope.books.length / $scope.pageSize);
                $scope.setPage(1); // Set initial page
                console.log('Evaluates:', $scope.books);
            })
            .catch(function(error) {
                console.error('Error fetching data:', error);
            });

    }
    $scope.initInfoProduct();
    $scope.setPage = function (page) {
        console.log('Current Page:', $scope.currentPage);
        console.log('Total Pages:', $scope.totalPages);
        if (page < 1 || page > $scope.totalPages) {
            return;
        }
        $scope.currentPage = page;
        var startIndex = (page - 1) * $scope.pageSize;
        var endIndex = startIndex + $scope.pageSize;
        $scope.paginatedBooks = $scope.books.slice(startIndex, endIndex);
        console.log('setPage called with page:', page);
        // ... (rest of the code)

        console.log('currentPage:', $scope.currentPage);
        console.log('paginatedBooks:', $scope.paginatedBooks);
    };

    $scope.getPages = function () {
        return new Array($scope.totalPages).fill().map((_, index) => index + 1);
    };
    // Disable button
    $scope.selectedOption = '';//Default value
    $scope.isButtonDisabled = true;

    $scope.updateButtonStatus = function () {

        $scope.isButtonDisabled = $scope.selectedOption === 'Books';
    };
    $scope.createSale = function() {
        // Gửi dữ liệu form đến API
        $http.post('http://localhost:8080/rest/sale/create', $scope.sale)
            .then(function(response) {

                alert(response.data.message);
            })
            .catch(function(error) {

                console.error('Error:', error);
                alert('An error occurred while processing the request.');
            });
    };

});


    //Review
app.controller('reviewController', function($scope, $routeParams, $route, $http, $rootScope) {
    $scope.evaluates = [];
    $scope.filteredReviews = [];

    $scope.filterReviews = function(selectedStar) {
        if (selectedStar === 'All') {
            // Nếu chọn "All", hiển thị tất cả đánh giá
            $scope.filteredReviews = $scope.evaluates;
        } else {
            // Nếu chọn một số sao cụ thể, lọc đánh giá theo rating
            $scope.filteredReviews = $scope.evaluates.filter(function(review) {
                return review.rating == selectedStar;
            });
        }
    };
    $scope.initInfoProduct = function () {
        console.log("alo alo");
        $http.get('/api/evaluates')
            .then(function(response) {
                $scope.evaluates = response.data;
                $scope.filterReviews('All'); // Hiển thị tất cả đánh giá khi trang được load
                console.log('Evaluates:', $scope.evaluates);
            })
            .catch(function(error) {
                console.error('Error fetching data:', error);
            });
    };

    $scope.initInfoProduct();
});

//////////////sales
app.controller('salesOrderManagementController', function($scope, $http) {
    $scope.pageSize = 5; // Number of items per page
    $scope.currentPage = 1; // Current page
    $scope.totalPages = 1
    $scope.initInfoProduct = function () {
        $scope.books = [];
        $http.get('/rest/books/ab')
            .then(function(response) {
                $scope.books = response.data;
                $scope.totalPages = Math.ceil($scope.books.length / $scope.pageSize);
                $scope.setPage(1); // Set initial page
                console.log('Evaluates:', $scope.books);
            })
            .catch(function(error) {
                console.error('Error fetching data:', error);
            });

    }
    $scope.initInfoProduct();
    $scope.setPage = function (page) {
        console.log('Current Page:', $scope.currentPage);
        console.log('Total Pages:', $scope.totalPages);
        if (page < 1 || page > $scope.totalPages) {
            return;
        }
        $scope.currentPage = page;
        var startIndex = (page - 1) * $scope.pageSize;
        var endIndex = startIndex + $scope.pageSize;
        $scope.paginatedBooks = $scope.books.slice(startIndex, endIndex);
        console.log('setPage called with page:', page);
        // ... (rest of the code)

        console.log('currentPage:', $scope.currentPage);
        console.log('paginatedBooks:', $scope.paginatedBooks);
    };

    $scope.getPages = function () {
        return new Array($scope.totalPages).fill().map((_, index) => index + 1);
    };
    $scope.setImage = function (bookId) {
        let url = `http://localhost:8080/rest/imagebook/` + bookId;
        $http.get(url).then(resp => {
            var a = [];
            a = (resp.data);
            console.log("fff"+JSON.stringify(resp.data));
            document.getElementById('img' + bookId).src = "/Client/images/" + a[0].name
        }).catch(error => {
            console.log("Error", error)
        });
    }
    $scope.books = [
        { isactive: true },
        { isactive: false },
        // Add more book objects as needed
    ];
    $scope.updateIsActive = function(book) {

        $http.put('/rest/books/updateIsActive/' + book.bookid, { isactive: book.isactive })
            .then(function(response) {
                console.log('Cập nhật thành công.');
            })
            .catch(function(error) {
                console.error('Lỗi khi cập nhật: ', error);
                // Nếu có lỗi, bạn có thể khám phá các cách xử lý lỗi phù hợp với ứng dụng của bạn
            });
    };
    $scope.removeBook = function(book) {
        var bookId = book.bookid;

        $http.delete('/rest/books/delete/' + bookId)
            .then(function(response) {
                var index = $scope.paginatedBooks.indexOf(book);
                if (index !== -1) {
                    $scope.paginatedBooks.splice(index, 1);
                }
            })
            .catch(function(error) {
                console.error('Error deleting book:', error);
            });
    };
});


