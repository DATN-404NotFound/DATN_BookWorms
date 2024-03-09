
function inputFullname() {
    var inputName = document.getElementById('textName');
    inputName.removeAttribute("disabled");
    inputName.classList.add('inputMypersonal');
}
function inputEmail() {
    var inputEmails = document.getElementById('textEmail');
    inputEmails.removeAttribute("disabled");
    inputEmails.classList.add('inputMypersonal');
}

function previewImage(event) {
    var input = event.target;
    var logoMypersonal = document.getElementById('logoMypersonal');

    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            logoMypersonal.src = e.target.result;
        };

        reader.readAsDataURL(input.files[0]);
    }
}
var currentDate = new Date();

// Giới hạn ngày đến năm 2023
var maxDate = new Date(2023, 11, 31); // Tháng 11 (0-11), ngày 31

// Chuyển định dạng ngày thành chuỗi YYYY-MM-DD
var maxDateString = maxDate.toISOString().split('T')[0];

// Thiết lập thuộc tính max cho trường ngày
document.getElementById('inpDate').setAttribute('max', maxDateString);