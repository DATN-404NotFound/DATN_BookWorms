$(document).ready(function () {
    // Bắt sự kiện khi tab được chọn
    $('a[data-bs-toggle="tab"]').on('shown.bs.tab', function (e) {
        var target = $(e.target).attr("href"); 
        // Lấy href của tab đã được chọn
        // Thực hiện các thay đổi cần thiết
    });
});

const app = angular.module("ordedr_app", []);

app.controller("order_ctrl", function ($scope, $http) {

    $scope.callModel = function(bookingId){
        console.log(bookingId)
        $http.get("/rest/bookings/"+ bookingId).then(resp=>{ 
            console.log(bookingId)
            console.log("resp = "+ JSON.stringify(resp.data));
            $scope.add = resp.data;

        })
    }

    $scope.updateBooking = function(){ 
        var ad = angular.copy($scope.add);
        $http.post("/rest/bookings/update", ad).then(resp =>{ 
            location.href="/myAccount/orderMyAccount";
        })
    }
});
