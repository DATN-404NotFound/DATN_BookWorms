$(document).ready(function () {
    // Bắt sự kiện khi tab được chọn
    $('a[data-bs-toggle="tab"]').on('shown.bs.tab', function (e) {
        var target = $(e.target).attr("href"); 
        // Lấy href của tab đã được chọn
        // Thực hiện các thay đổi cần thiết
    });
});

// function changeTab(tabName) {
//     // Thay đổi URL của tab dựa trên tên tab và chuyển điều hướng đến controller
//     window.location.href = "/orderMyAccount/tabs?tab=" + tabName;
// }
