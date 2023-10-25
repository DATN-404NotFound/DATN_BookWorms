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
    $scope.cart = {
        items: [],
        load() {
            var urlload = `${host}/get-all`;
            $http.get(urlload).then(resp => {
                items.push(resp.data)
            })
        },
        add(id) {
            var item = this.items.find(item => item.id == id);
            if (item) {
                item.quantity++;
                var url = `${host}/put-cart`;
                var cartobject = angular.copy(this.item);
                $http.put(url, cartobject).then(resp => {
                }
                )
            }
            else {
                var bookObject = "";
                var url1 = "http://localhost:8080/book/getone/" + id;
                $http.get(url1).then(resp => {
                    bookObject = resp.data;
                })
                var newitem = {
                    user: { username: $("#user1").text() },
                    book: bookObject
                }
                var url = `${host}/post-cart`;
                $http.post(url, newitem).then(resp => {
                    this.items.push(resp.data)
                })
            }
        },
        delete(cartid) {
            var urldete = `${host}/get-cart-id/` + cartid;
            $http.delete(urldete).then(resp => {
                var itemIndex = $scope.items.findIndex(item => item.cartid == cartid);
                this.items.splice(itemIndex, 1);

            })
        }
    }
})

