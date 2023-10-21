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
    var f = document.getElementById
}
function selectOne(e) {
    var one = document.getElementById(e);
    if (one.checked) {
        purchase.push(one.getAttribute('id'))
        console.log("lkdkf" + purchase)
        check2(e);
    }
    else {
        var item = purchase.findIndex(item => item == e);
        purchase.splice(item, 1);
        console.log("lkdkf" + purchase)
    }
}

function check2(e){ 
    var one = document.getElementById(e);
    var parent = one.parentElement.parentElement.parentElement;
    var chil =  parent.children;
    for(var i=0; i<chil.length;i++){ 
  var a=0;
     if(chil[i].children[0].children[0].checked) { 
        a++;
        if(chil.length ==a){ 
            
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
            if (f.length == index) {
                console.log("khdesfjkh")
                document.getElementById('deleteAll').disabled = false;
                document.getElementById('selectAll').disabled = true;
            }
            else {
                document.getElementById('deleteAll').disabled = true;
                document.getElementById('selectAll').disabled = false;
            }
        }
    }
}


function chooseAll(e) {
    var f = document.getElementsByName('inp')
    for (var i = 0; i < f.length; i++) {
        f[i].checked = true;
        choose(f[i].getAttribute('id'));
    }
    check();
}


function deleteAll(e) {
    var f = document.getElementsByName('inp')
    for (var i = 0; i < f.length; i++) {
        f[i].checked = false;
        choose(f[i].getAttribute('id'));
    }
    check();

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
        },
        // get amout(){ 
        //     return this.items.map(item=>item.quantity*item.price)
        // }

    }
})

