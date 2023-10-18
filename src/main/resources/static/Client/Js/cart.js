// document.getElementById("1").onclick = function(e){ 
//     var checkbox = document.getElementsByName('carttest');
//     if(this.checked){ 
//         for (var i = 0; i < checkbox.length; i++){
//         checkbox[i].checked = true;
//     }
//     }
//     else{ 
//         for (var i = 0; i < checkbox.length; i++){
//         checkbox[i].checked = false;
//     }
// }
// };

function choose(e){ 
    var a = document.getElementById(e).parentElement.parentElement;
   var child = a.children.getElementsTagName;
   if(this.checked){ 
            for (var i = 0; i < checkbox.length; i++){
            checkbox[i].checked = true;
        }
        }
        else{ 
            for (var i = 0; i < checkbox.length; i++){
            checkbox[i].checked = false;
        }

}
}
document.getElementById('btn').onclick = function()
{
    var checkbox = document.getElementsByName('carttest');
    var result = "";
     
    for (var i = 0; i < checkbox.length; i++){
        if (checkbox[i].checked === true){
            result += ' [' + checkbox[i].value + ']';
        }
    }  
    alert("Sở thích là: " + result);
};