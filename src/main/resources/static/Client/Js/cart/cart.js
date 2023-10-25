var purchase = [];
function choose(e) {
    check()
    var a = document.getElementById(e);
    var b = a.parentElement.parentElement;
    var c = b.children[2]
    var d = c.getElementsByClassName('form-check-input')
    if (a.checked) {
        for (var i = 0; i < d.length; i++) {
            if (d[i].checked) {
                continue;
            }
            else {
                d[i].checked = true;
                purchase.push(d[i].getAttribute('id'));
                console.log(purchase)
            }
        }
    }
    else {
        for (var i = 0; i < d.length; i++) {
            d[i].checked = false;
            var index = purchase.findIndex(item => item == d[i].getAttribute('id'));
            purchase.splice(index, 1);
            console.log(purchase)
        }
    }
}



$(document).ready(function () {
    $('#selectAll').click(function () {
        var $name = 'name="carttest"';
        $(':checkbox').prop('checked', true)
        $(this).prop('disabled', true)
        $('#deleteAll').prop('disabled', false)
        purchase = []
        $('input[' + $name + ']').each(function (i) {
            purchase.push($(this).attr('id'))
        })
    })
})


$(document).ready(function () {
    $('#deleteAll').click(function () {
        var $name = 'name="carttest"';
        $(':checkbox').prop('checked', false)
        $(this).prop('disabled', true)
        $('#selectAll').prop('disabled', false)
        $('input[' + $name + ']').each(function (i) {
            purchase = [];
        })
    })
})



function totalAllChoose() {
    if (purchase.length > 0) {
        purchase.forEach((n) => {

        })
    }
}

function selectOne(e) {
    var one = document.getElementById(e);
    if (one.checked) {
        purchase.push(one.getAttribute('id'))
    }
    else {
        var item = purchase.findIndex(item => item == e);
        purchase.splice(item, 1);
    }
    check2(e);
    check()
    console.log(purchase)
}


function check2(e) {
    var one = document.getElementById(e);
    var parent = one.parentElement.parentElement.parentElement;
    var chil = parent.children;
    var a = 0;
    for (var i = 0; i < chil.length; i++) {
        if (chil[i].children[0].children[0].checked) {
            a++;
            var pa = parent.parentElement.parentElement;
            var shops = pa.children[0].children[0];
            if (chil.length == a) {
                shops.checked = true;
            }
            else {
                shops.checked = false;
            }
        }
    }
}


function check() {
    var f = document.getElementsByName('inp');
    var index = 0;
    for (var i = 0; i < f.length; i++) {
        if (f[i].checked) {
            index++;
            var a = true;
            var b = document.getElementById('deleteAll');
            var c = document.getElementById('selectAll');

            if (f.length == index) {
                b.disabled = !a;
                c.disabled = a;
            }
            else {
                b.disabled = a;
                c.disabled = !a;
            }
        }
    }
}


const app = angular.module("cart_app", []);
let host = "http://localhost:8080/rest/cart"
app.controller("cart_ctrl", function ($scope, $http) {
    $scope.totalAll = {
        cartsAll: [],
        get getall() {
            var amout = 0;
            if (purchase.length > 0) {
                purchase.forEach((n) => {
                    var urlcart = `${host}/` + n
                    $http.get(urlcart).then(resp => {
                        cartsAll.push(resp.data)
                    })
                })
            }
            cartsAll.forEach((n) => {
                amout = n.books.price * n.quantity;
            })
            return amout;
        }
    }

    $scope.changequantity = function(quantity,cartid){ 
        var urlcart = `${host}/` + cartid;
        var cartchange ={};
        $http.get(urlcart).then(resp =>{ 
            cartchange = resp.data;
        })
        cartchange.quantity = quantity;
        $http.put(urlcart,cartchange).then(resp =>{
        })
    }

    $scope.cartsvoucher={
        voucherAll :[],
        changevoucher(couoponcode){ 
            var urlsale ="http://localhost:8080/rest/sale/"+couoponcode
            var con = this.voucherAll.find(item =>  item.couoponcode == couoponcode)
            if(con){ 
                con.couoponcode = couoponcode;
            }
            else{
                $http.get(urlsale) .then(resp =>{ 
                    voucherAll.push(resp.data); 
                })
            }
        },
    }
  
})

