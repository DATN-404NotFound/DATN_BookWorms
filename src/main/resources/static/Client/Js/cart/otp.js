const inputs = document.getElementById("inputs"); 
  var otp = '';
inputs.addEventListener("input", function (e) { 
    const target = e.target; 
    const val = target.value; 
  
    if (isNaN(val)) { 
        target.value = ""; 
        return; 
    } 
  
    if (val != "") { 
        otp = otp+val;
        const next = target.nextElementSibling; 
        if (next) { 
            next.focus(); 
        } 
        else{ 
           confirmOtp(otp); 
        }
    } 
}); 


function confirmOtp(otp) {
	console.log("otp : "+ otp);
	$.ajax({
		url: "http://localhost:8080/rest/otp",
		type: "POST",
		data: otp,
		success: function (resultData) {
			console.log(resultData);
			console.log("ok");
            if(resultData =="OK"){
               location.href = "http://localhost:8080/account/newpass"
            }
            else{ 
                location.href = "http://localhost:8080/account/ConfirmCode"
            }
		},
	});
}
  
inputs.addEventListener("keyup", function (e) { 
    const target = e.target; 
    const key = e.key.toLowerCase(); 
  
    if (key == "backspace" || key == "delete") { 
        otp = otp.length-1;
        
        target.value = ""; 
        if (isNaN(a)) { 
            otp='';
        } 
        const prev = target.previousElementSibling; 
        if (prev) { 
            prev.focus(); 
        } 
        return; 
    } 
});