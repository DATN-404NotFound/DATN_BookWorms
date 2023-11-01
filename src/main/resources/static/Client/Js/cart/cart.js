var purchase = [];
var tong = 0;
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
    // var strprice = document.getElementById('cartid'+e).innerText;
    // var reply = strprice.replace(',', '');
    if (one.checked) {
        purchase.push(one.getAttribute('id'));
        // this.tong +=  Number(reply);
        console.log("num")
    }
    else {
        var item = purchase.findIndex(item => item == e);
        purchase.splice(item, 1);
        // this.tong -=  Number(reply);
    }
    check2(e);
    check();
    checkAll();
    console.log(purchase)
}

function checkAll() {
    this.tong = 0;
    console.log("kdsjfkgldsjg" + purchase)
    for (var i = 0; i < purchase.length; i++) {
        var strprice = document.getElementById('cartid' + purchase[i]).innerText;
        var reply = strprice.replace(',', '');
        this.tong += Number(reply);

    }
    document.getElementById("allPrice").innerText = this.tong;
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
//  function changeone(id){ 
// 		var e = document.getElementById(id).value
//         console.log("cchangeone"+ e)
//     }

function mm(id) {
    console.log("kksksk" + id)
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
            console.log("kjskljfdksjdfkjdklsfjs")
            var url = `${host}/user`
            $http.get(url).then(resp => {
                this.items = resp.data;
                console.log("an" + this.items[0].books.bookid);

            });
        },
        update() {
            $http.put(`${host}`).then(resp => {
                items = resp.data;
                console.log("an" + items.length);

            });
        }
        , delete(id) {
            console.log("xxXoá tành công")
            $http.delete(`${host}/` + id).then(resp => {
                console.log("Xoá tành công")

            });
        }

    }

    $scope.cart.load();
    $scope.changeone = function () {
		 console.log("12 - " );
        // var e = document.getElementById("quantity" + cartid).value
        // var urlcart = `${host}/` + cartid;
        // var a = e;
        // console.log("12 - " + a);
        // var cartchange = {};
        // $http.get(urlcart).then(resp => {
        //     cartchange = resp.data;
        //     console.log("cart- " + cartchange.quantity)
        //     cartchange.quantity = Number(a);

        //     $http.put(`${host}`, cartchange).then(resp => {
        //         console.log("Thành công " + resp.data.cartid + "- " + resp.data.quantity)
        //         document.getElementById("cartid" + cartid).innerHTML =  resp.data.quantity * resp.data.books.price;
                
                //"${#numbers.formatDecimal("+resp.data.quantity * resp.data.books.price +",0,'COMMA',2,'POINT')}"
                //resp.data.quantity * resp.data.books.price;
           // })


        //})
    }

    $scope.totalAll = {
        cartsAll: [],

        loadc(id) {
            console.log("a" + id)
            purchase.forEach((n) => {
                var urlcart = "http://localhost:8080/rest/cart/" + n;
                console.log(purchase)
                $http.get(urlcart).then(resp => {

                    //         console.log("b3")
                    this.cartsAll.push(resp.data);
                    //  var tol = 0;
                    //  for(var i=0; i<this.cartsAll.length;i++){ 

                    //      tol += (this.cartsAll[i].quantity * this.cartsAll[i].books.price);
                    //        console.log("chay "+ tol);
                    //  }

                    //  this.tong = tol;
                    //  console.log("tong"+ this.tong)

                });
                //     var totl =  this.cartsAll
                // .map(item => item.quantity* item.books.price)
                // .reduce((total,quantity)=> total += quantity,0);

                // console.log("total "+ totl)
            });

        },



        // cartsAll: [],
        // getonecart(){ 
        //     console.log("Á")

        // },

        //console.log("b1")
        // var amout = 0;
        // if (purchase.length > 0) {
        //     console.log("b2")

        // }
        // cartsAll.forEach((n) => {
        //     amout = n.books.price * n.quantity;
        // })
        // return amout;


    }




    // $scope.changequantity = function (quantity, cartid) {
    //     var urlcart = `${host}/` + cartid;
    //     var a = (quantity + 1)
    //     console.log("12 - " + a);
    //     var cartchange = {};
    //     $http.get(urlcart).then(resp => {
    //         cartchange = resp.data;
    //         console.log("cart- " + cartchange.quantity)
    //         cartchange.quantity = Number(quantity + 1);

    //         $http.put(`${host}`, cartchange).then(resp => {
    //             console.log("Thành công " + resp.data.cartid + "- " + resp.data.quantity)
    //         })


    //     })
    // }

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

    $scope.deletecart = function (id) {
        console.log("deletecart")
        var ho = "http://localhost:8080/rest/cart/" + id;
        $http.delete(ho).then(resp => {
            console.log(resp.data);
        });
        location.href = "/cart";
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

