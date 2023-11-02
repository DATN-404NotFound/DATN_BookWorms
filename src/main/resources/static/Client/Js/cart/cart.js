var purchase = [];
var tong = 0;
var json = [];

function choose(e) {
    check()
    var a = document.getElementById(e);
    var b = a.parentElement.parentElement;
    var c = b.children[2];
    var d = c.getElementsByClassName('form-check-input');
    var e = c.getElementsByClassName('total_pro');
    if (a.checked) {
        for (var i = 0; i < d.length; i++) {
            if (d[i].checked) {
                continue;
            }
            else {
                d[i].checked = true;
                purchase.push(d[i].getAttribute('id'));
            }
        }
    }
    else {
        console.log("klkklkk")
        for (var i = 0; i < d.length; i++) {
            d[i].checked = false;
            var index = purchase.findIndex(item => item == d[i].getAttribute('id'));
            purchase.splice(index, 1);
        }
    }
    checkAll();
}


$(document).ready(function () {
    $('#selectAll').click(function () {
        var $name = 'name="carttest"';
        $(':checkbox').prop('checked', true)
        $(this).prop('disabled', true)
        $('#deleteAll').prop('disabled', false)
        purchase = []
        $('input[' + $name + ']').each(function (i) {
            purchase.push($(this).attr('id'));
            checkAll();
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
            checkAll();
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
        purchase.push(one.getAttribute('id'));
    }
    else {
        var item = purchase.findIndex(item => item == e);
        purchase.splice(item, 1);
    }
    check2(e);
    check();
    checkAll();
    console.log(purchase)
}


function Active(cartid, action) {
    var e = document.getElementById("quantity" + cartid).value;
    $.get("http://localhost:8080/rest/cart/" + cartid, function (data, status) {
        json = data;
        json.quantity = e;
        var shop = json.books.shopid;
        console.log("ship = "+ shop)
        switch (action) {
            case 'PUT': {
                updateCart(cartid, json);
                break;
            }
            case 'DELETE': {
                deleteCart(cartid,shop);
                break;
            }
        }
    });
}


function updateCart(id, json) {
    $.ajax({
        url: "http://localhost:8080/rest/cart",
        type: "PUT",
        data: JSON.stringify(json),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (resultData) {
            console.log(resultData);
            $("#cart" + id).children().eq(5).text(formatNumber(resultData.books.price * resultData.quantity, ".", ","));
            console.log("ok");
            checkAll();
        },
    });
   
}

function deleteCart(id,shop) {
    $.ajax({
        url: "http://localhost:8080/rest/cart/" + id,
        type: "DELETE",
        success: function (resultData) {
            console.log(resultData)
        },
    });
    $("#cart" + id).remove();
    var index = purchase.findIndex(item => item == id);
    purchase.splice(index, 1);
   var chi=  $("#cart" + shop).children();
   if(chi.length ==0){ 
    $("#shop" + shop).remove();
   }
    checkAll();
}


function formatNumber(nStr, decSeperate, groupSeperate) {
    nStr += '';
    x = nStr.split(decSeperate);
    x1 = x[0];
    x2 = x.length > 1 ? '.' + x[1] : '';
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {
        x1 = x1.replace(rgx, '$1' + groupSeperate + '$2');
    }
    return x1 + x2 + ".00";
}

function checkAll() {
    this.tong = 0;
    console.log("kdsjfkgldsjg" + purchase)
    for (var i = 0; i < purchase.length; i++) {
        var strprice = document.getElementById('cartid' + purchase[i]).innerText;
        var reply = strprice.replace(',', '');
        this.tong += Number(reply);

    }
    document.getElementById("allPrice").innerText = formatNumber(this.tong, ".", ",");
}


function check2(e) {
    var one = document.getElementById(e);
    var parent = one.parentElement.parentElement.parentElement;
    var chil = parent.children;
    var a = 0;
    for (var i = 0; i < chil.length;i++) {
        console.log(chil.length);
        if (chil[i].children[0].children[0].checked) {
     	a += 1;
            var pa = parent.parentElement.parentElement;
            var shops = pa.children[0].children[0];
            console.log("i "+ a +" - leng - "+ chil.length)
            if (chil.length == a) {
                shops.checked = true;
            }
        }
        else{ 
            var pa = parent.parentElement.parentElement;
            var shops = pa.children[0].children[0];
            shops.checked = false;
        }
    }
}


function check() {
    var f = document.getElementsByName('inp');
    console.log("len" + f.length)
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

    $scope.cart = {
        items: [],
        add(id) {
            console.log("ennn" + id)
            var item = this.items.find(item => item.books.bookid == id);

            if (item) {
                // nếu có trong gỏ hàng items rồi thì tăng số lượng
                item.quantity++;
                console.log("ok có " + item.books.bookid)
                var updatecart = `${host}`;
                console.log("url " + updatecart);
                var cartupdate = angular.copy(item);
                $http.put(updatecart, cartupdate).then(resp => {

                })

            }
            else {
                $http.get(`/rest/books/` + id).then(resp => {
                    var s = resp.data;
                    console.log("kkk" + JSON.stringify(s))

                    $scope.cartss = {
                        cartid: "",
                        userid: "2361ba0a",
                        bookid: s.bookid,
                        quantity: 1
                    }
                    var addc = angular.copy($scope.cartss)
                    $http.post(`/rest/cart`, addc).then(resp => {
                        this.items.push(resp.data)
                        console.log("in " + resp.data)
                    })
                })
            }
        },
        load() {
            var url = `${host}/user`
            $http.get(url).then(resp => {
                this.items = resp.data;
            });
        },

    }


    $scope.cart.load();

    $scope.cartsvoucher = {
        voucherAll: [],
        changevoucher(couoponcode) {
            var urlsale = "http://localhost:8080/rest/sale/" + couoponcode
            var con = this.voucherAll.find(item => item.couoponcode == couoponcode)
            if (con) {
                con.couoponcode = couoponcode;
            }
            else {
                $http.get(urlsale).then(resp => {
                    voucherAll.push(resp.data);
                })
            }
        },
    }



    $scope.booking = {
        bookingid: "new",
        createat: new Date(),
        userid: { userid: $("#user1").text() },
        get cost() {
            return $scope.totalAll.cartsAll
                .map(item => item.quantity * item.books.price)
                .reduce((total, quantity) => total += quantity, 0);
        },
        get detailbookings() {
            return $scope.totalAll.cartsAll.map(item => {
                return {
                    dbid: "detailbooking",
                    books: { bookid: item.books.id },
                    quantity: item.quantity
                }
            })
        },
        orderstatuses: 1,
        shippingunits: 1,
        listOfPayments: 1,

        purch() {
            var order = angular.copy(this);
            var url = "http://localhost:8080/rest/booking/post-detail";

            $http.post(url, order).then(resp => {
                $scope.totalAll.cartsAll = [];
                location.href = "/booking/detail" + resp.data.id
            })
        }

    }
})

