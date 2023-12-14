var app = angular.module('Admin', ['ngRoute']);
app.config(function($locationProvider) {
	$locationProvider.hashPrefix('!');
});

app.config(function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl: '/admin/findOrderUser',
			controller: 'indexController'
		})
		.when('/admin/findtop5', {
			templateUrl: '/admin/findtop5',
			controller: 'findtop5'
		})
		.when('/api/payment/callpayment', {
			templateUrl: '/api/payment/callpayment',
			controller: 'transportController'
		})
		.when('/admin/confirm', {
			templateUrl: '/admin/confirm',
			controller: ''
		})
		.when('/admin/delivering', {
			templateUrl: '/admin/delivering',
			controller: ''
		})
		.when('/admin/unpaid', {
			templateUrl: '/admin/unpaid',
			controller: ''
		})
		.when('/admin/paid', {
			templateUrl: '/admin/paid',
			controller: ''
		})
		.when('/admin/processed', {
			templateUrl: '/admin/processed',
			controller: ''
		})
		.when('/admin/refund', {
			templateUrl: '/admin/refund',
			controller: ''
		})
		.when('/admin/cancel', {
			templateUrl: '/admin/cancel',
			controller: ''
		})
		.when('/createvoucher', {
			templateUrl: '/createvoucher',
			controller: ''
		})
		.when('/admin/listvoucher', {
			templateUrl: '/admin/listvoucher',
			controller: ''
		})
		.when('/admin/statisticalbook', {
			templateUrl: '/admin/statisticalbook',
			controller: ''
		})
		.when('/admin/statisticalshop', {
			templateUrl: '/admin/statisticalshop',
			controller: ''
		})
})

app.controller("findtop5", function($scope, $routeParams, $route, $http, $rootScope, $timeout, $window) {
	let host = "http://localhost:8080/rest/admin/";
	$scope.select = "bestSeller";
	$scope.first = function(){
		let url = `${host}bestSeller`;
		$http.get(url).then(resp =>{
			$scope.sp = resp.data;
		})
	}
	
	$scope.first();
	$scope.changeOrder = function() {
		if ($scope.select != "bestSeller") {
			let url = `${host}inventory`;
			 $http.get(url).then(resp =>{
			$scope.sp = resp.data;
		})
		} else {
			let url = `${host}bestSeller`;
			$http.get(url).then(resp =>{
			$scope.sp = resp.data;
		})
		}
	}

	$scope.changeOrder();
})