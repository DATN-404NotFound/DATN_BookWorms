var password = document.getElementById("password")
  , confirm_password = document.getElementById("confirmPassword");


function validatePassword() {
  if(password.value != confirm_password.value) {
    confirm_password.setCustomValidity("Passwords Don't Match");
    return false;
  } else {
    confirm_password.setCustomValidity('');
    return true;
  }
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;

function enableSubmitButton() {
  document.getElementById('submitButton').disabled = false;
  document.getElementById('loader').style.display = 'none';
}

function disableSubmitButton() {
  document.getElementById('submitButton').disabled = true;
  document.getElementById('loader').style.display = 'unset';
}

function validateSignupForm() {
    if(validatePassword() == true){ 
        console.log("true"+ password.value);
       try {
        $.ajax({  
            url: "http://localhost:8080/rest/account/newpass",
            type: "POST",
            data: password.value,
            success: function (resultData) {
                console.log(resultData);
                console.log("ok");
                if(!resultData || resultData.value === null){
                    $('#mess').show()
                }
                else{ 
                    console.log(JSON.stringify(resultData));
                    $('#myModal').show();
                    $('#iconModel').html('<i  style="font-size: 50px;color: green;" class="bi bi-check-circle"></i> ')
                    $('#descrptionInfor').text("Cập nhật thành công!")
                }
            },
        });
       } catch (error) {
        console.log("lỗi : "+ error)
        $('#mess').show();
        
       }
    }
    else{ 
        console.log("false");
    }
}

$(document).ready(function () {
	$('#mess').hide();
    $('#myModal').hide()
});
