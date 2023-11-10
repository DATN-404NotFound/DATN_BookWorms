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
			} else {
				d[i].checked = true;
				purchase.push(d[i].getAttribute('id'));
			}
		}
	} else {
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



function selectOne(e) {
	var one = document.getElementById(e);
	if (one.checked) {
		purchase.push(one.getAttribute('id'));
	} else {
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
		console.log("ship = " + shop)
		switch (action) {
			case 'PUT': {
				console("post")
				updateCart(cartid, json);
				break;
			}
			case 'DELETE': {
				deleteCart(cartid, shop);
				break;
			}
		}
	});
}


function updateCart(id, json) {
	console.log("out in ")
	$.ajax({
		url: "http://localhost:8080/rest/cart",
		type: "POST",
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

function deleteCart(id, shop) {
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
	var chi = $("#cart" + shop).children();
	if (chi.length == 0) {
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
	for (var i = 0; i < chil.length; i++) {
		console.log(chil.length);
		if (chil[i].children[0].children[0].checked) {
			a += 1;
			var pa = parent.parentElement.parentElement;
			var shops = pa.children[0].children[0];
			console.log("i " + a + " - leng - " + chil.length)
			if (chil.length == a) {
				shops.checked = true;
			}
		} else {
			var pa = parent.parentElement.parentElement;
			var shops = pa.children[0].children[0];
			shops.checked = false;
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
			} else {
				b.disabled = a;
				c.disabled = !a;
			}
		}
	}
}

// function filterChoose(stt,name){
// 	console.log("chose name : "+ name)

// }
const app = angular.module("cart_app", []);



let host = "http://localhost:8080/rest/cart"
app.controller("cart_ctrl", function ($scope, $http) {

	app.filter('searchField', ['$parse', function ($parse) {
		return function (collection) {
	
		  var get, field;
	
		  collection = isObject(collection) ? toArray(collection) : collection;
	
		  var args = Array.prototype.slice.call(arguments, 1);
	
		  if(!isArray(collection) || !args.length) {
			return collection;
		  }
	
		  return collection.map(function(member) {
	
			field = args.map(function(field) {
			  get = $parse(field);
			  return get(member);
			}).join(' ');
	
			return extend(member, { searchField: field });
		  });
		}
	  }]);

$scope.sp = [];
$scope.loadProduct = function(){ 
		$http.get("http://localhost:8080/rest/books").then(resp =>{ 
			$scope.sp = resp.data;
			console.log("nn "+JSON.stringify($scope.sp[0]))
		})
}

$scope.loadProduct();

	$scope.filterCate = [];
	$scope.filterWrite = [];
	$scope.filterPC = [];
	$scope.filterPrice = [];
	$scope.filterRatting = [];

	$scope.orderBy = function () {
		location.href = "/product/list?priceSort=" + $scope.or;
	}
	$scope.newyear = function () {
		location.href = "/product/list?newYear= yes";
	}
	$scope.bestseller = function () {
		location.href = "/product/list?sold= yes";
	}

	$scope.filterChoose = function (stt, name) {
		switch (stt) {
			case 1: {
				$scope.process($scope.filterCate, name);
				break;
			}
			case 2: {
				$scope.process($scope.filterWrite, name);
				break;
			}
			case 3: {
				$scope.process($scope.filterPC, name);
				break;
			}
			case 4: {
				$scope.process($scope.filterPrice, name);
				break;
			} 
			case 5: {
				$scope.process($scope.filterRatting, name);
				break;
			}
		}
		$scope.loadProduct();
	}

$scope.process = function(array, name){ 
	if (name.checked) {
		console.log("chose name : " + name.getAttribute('id'));
		array.push(name.getAttribute('id'));
	}
	else {
		console.log("fal name : " + name);
		var del = array.findIndex(i => i === name.getAttribute('id'));
		array.splice(del, 1)
	}
	console.log("filter : "  + array)
}


	$scope.booksCate = [];
	$scope.loadAll = function () {
		console.log("aaaa")
		$http.get("/rest/books/cate/4").then(resp => {
			$scope.a = 4;
			$scope.b = 0;
			$scope.booksCate = resp.data;
		})
	}




	$scope.edu = function (id, a, b) {
		$http.get("/rest/books/cate/" + id).then(resp => {
			$scope.a = a;
			$scope.b = b;
			$scope.booksCate = resp.data;
			$scope.booksCate.forEach(i => {
				$scope.setImage(i.bookid, id)
			})
		})
	}
	$scope.setImage = function (bookId, cate) {
		let url = `http://localhost:8080/rest/imagebook/` + bookId;
		$http.get(url).then(resp => {
			var a = [];
			a = (resp.data);
			console.log("đmmmm" + a[0].name)
			switch (cate) {
				case 2: {
					document.getElementById('imgs2' + bookId).src = "/Client/images/" + a[0].name
					break;
				}
				case 4: {
					document.getElementById('imgs4' + bookId).src = "/Client/images/" + a[0].name
					break;
				}
				case 7: {
					document.getElementById('imgs7' + bookId).src = "/Client/images/" + a[0].name
					break;
				}
			}

		}).catch(error => {
			console.log("Error", error)
		});;
	}
	$scope.loadAll();

	$scope.next = function (id, cate) {
		console.log("next" + id)
		$scope.b = id + 1;
		$scope.edu(cate, 4, $scope.b)

	}

	$scope.prev = function (id, cate) {
		console.log("next" + id)
		$scope.b = id - 1;
		$scope.edu(cate, 4, $scope.b)
	}

	$scope.cart = {
		items: [],
		add(id) {
			console.log("ennn" + id)
			var item = this.items.find(item => item.books.bookid == id);

			if (item) {
				item.quantity++;
				var updatecart = `${host}`;
				var cartupdate = angular.copy(item);
				$http.put(updatecart, cartupdate).then(resp => {

				})

			} else {
				$http.get(`/rest/books/` + id).then(resp => {
					var s = resp.data;
					$scope.cartss = {
						cartid: "",
						userid: "",
						bookid: s.bookid,
						quantity: 1
					}
					var addc = angular.copy($scope.cartss)
					$http.post(`/rest/cart`, addc).then(resp => {
						this.items.push(resp.data)
					})
				})
			}
		},
		load() {
			console.log("loo")
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
			} else {
				$http.get(urlsale).then(resp => {
					voucherAll.push(resp.data);
				})
			}
		},
	}
})


var shoponline = [];
var deal = [];
var books = [];
var salevoucher = [];
var paymentaccount = [];
var vouchero = {};


$(document).ready(function () {
	$('#voucherOther').change(function () {

		var a = $('#voucherOther').children("option:selected").val();
		$.get("http://localhost:8080/rest/discount/" + a, function (data, status) {
			console.log(data)
			var b = document.getElementById('totalPriceAll').innerText;
			var reply = b.replace(',', '');
			var c = Number(reply) * data.sales.discountpercentage;
			document.getElementById('totalSales').innerText = formatNumber(c, ".", ",");
			vouchero = data;
			calculatorPrice();
		});

	})

});


$(document).ready(function () {
	$('#pay').change(function () {
		var a = $('#pay').children("option:selected").val();
		if (a == 1) {
			$('#pa').show()
		}
		else {
			$('#pa').hide()
		}
	})
});



function voucherSelected(shopid) {
	var vou = $('#voucher' + shopid).children("option:selected").val();
	$.get("http://localhost:8080/rest/discount/" + vou, function (data, status) {
		console.log(data)
		if (!data || data.value === null) {
		}
		else {
			var v = salevoucher.find(i => i.saleid == vou);
			if (v) {
			}
			else {
				salevoucher.push(data);
				localStorage.setItem('sales', JSON.stringify(salevoucher));
			}
		}
	});
}

function selectShop(item) {
	$.get("http://localhost:8080/rest/shoponline/" + item, function (data, status) {

		var y = shoponline.find(i => i.shopid == data.shopid);
		if (y) {
		}
		else {
			shoponline.push(data);
			console.log("datashop " + shoponline.length)
			localStorage.setItem('shoponline', JSON.stringify(shoponline));
			voucherSelected(data.shopid);
		}
	});
}

function findBook(item) {
	$.get("http://localhost:8080/rest/books/" + item.bookid, function (data, status) {
		books.push(data);
		localStorage.setItem('books', JSON.stringify(books));
		selectShop(data.shoponlines.shopid);
	})
}

function findCart(item) {
	$.get("http://localhost:8080/rest/cart/" + item, function (data, status) {
		deal.push(data);
		localStorage.setItem('deal', JSON.stringify(deal));
		findBook(data);
	})
}

function findPaymentAccount() {
	$.get("http://localhost:8080/rest/paymentaccount/user", function (data, status) {
		deal.push(data);
		localStorage.setItem('deal', JSON.stringify(deal));
		findBook(data);
	})
}


function deals() {
	shoponline = [];
	salevoucher = [];
	books = [];
	deal = [];
	console.log(purchase);
	purchase.forEach(item => {
		findCart(item);
		localStorage.setItem('deal', JSON.stringify(deal));
		location.href = "/order";

	})
}


$(document).ready(function () {
	loadWin();
	$('#pa').hide()
});


function loadWin() {
	console.log("KKKKKK")
	var a = JSON.parse(localStorage.getItem('books'));
	var b = JSON.parse(localStorage.getItem('deal'));
	var c = JSON.parse(localStorage.getItem('shoponline'));
	var d = JSON.parse(localStorage.getItem('sales'));
	var totalPriceAll = 0;
	c.forEach(m => {
		var priceItem = 0;

		b.forEach(i => {
			if (i.books.shopid == m.shopid) {
				priceItem += (i.books.price * i.quantity);
			}
		})
		if (d.length > 0) {
			d.forEach(j => {
				if (j.sales.shopid == m.shopid) {
					priceItem -= (priceItem * j.sales.discountpercentage);
				}
			})
		}
		document.getElementById('priceItem' + m.shopid).innerText = formatNumber(priceItem, ".", ",");;
		totalPriceAll += priceItem;
		console.log("priceAll " + totalPriceAll)

	})
	document.getElementById('totalPriceAll').innerText = formatNumber(totalPriceAll, ".", ",");
	calculatorPrice();
}

function calculatorPrice() {
	var a = document.getElementById('totalPriceAll').innerText.replace(',', '');
	var b = document.getElementById('shippingPrice').innerText.replace(',', '');
	var c = document.getElementById('totalSales').innerText.replace(',', '');
	var d = document.getElementById('totalFreeShip').innerText.replace(',', '');
	var e = document.getElementById('totalFinal');
	var f = Number(a) + Number(b) - Number(c) - Number(d);
	e.innerText = formatNumber(f, ".", ",");
}



const app1 = angular.module("order_app", []);
app1.controller("order_ctrl", function ($scope, $http) {
	$scope.bookItem = [];
	$scope.dealItem = [];
	$scope.shopItem = [];
	$scope.salesItem = [];
	$scope.totalQuantity = 0;

	$scope.loadDeal = function () {
		$scope.bookItem = JSON.parse(localStorage.getItem('books'));
		$scope.dealItem = JSON.parse(localStorage.getItem('deal'));
		$scope.shopItem = JSON.parse(localStorage.getItem('shoponline'));
		$scope.salesItem = JSON.parse(localStorage.getItem('sales'));
		$scope.totalQuantity = $scope.bookItem.length;
	}

	$scope.loadDeal();
	$scope.setImage = function (bookId) {
		let url = `http://localhost:8080/rest/imagebook/` + bookId;
		$http.get(url).then(resp => {
			var a = [];
			a = (resp.data);
			document.getElementById('img' + bookId).src = "Client/images/" + a[0].name
		}).catch(error => {
			console.log("Error", error)
		});;
	}

	$scope.deleteDeal = function () {
		$scope.dealItem.forEach(i => {
			$http.delete("http://localhost:8080/rest/cart/" + i.cartid).then(resp => {
			})
		})
	}
	$scope.paymentCart = function () {
		$scope.shopItem.forEach(i => {
			var a = document.getElementById('priceItem' + i.shopid).innerText;
			$scope.bookings = {
				bookingid: "",
				createat: new Date(),
				cost: Number(a.replace(',', '')),
				userid: "",
				orderstatusid: 1,
				shippingunitid: Number($('#shippunit').children("option:selected").val()),
				note: $('#noteBooking').val(),
				get listOfPayments() {
					return {
						paymentid: "",
						createat: new Date(),
						status: "Chưa thanh toán",
						paid: $('#pac').children("option:selected").val(),
						type: Number($('#pay').children("option:selected").val()),
						addressuserid: $('#addressship').children("option:selected").val(),
						addressusers: { addressuserid: $('#addressship').children("option:selected").val() },
						paymentaccounts: { paid: $('#pac').children("option:selected").val() },
					}
				},
				orderstatuses: { orderstatusid: 1 },
				account: { userid: "" },
				get listOfDetailbookings() {
					return $scope.dealItem.map(item => {
						if (item.books.shopid == i.shopid) {
							return {
								dbid: "",
								bookid: item.books.bookid,
								quantity: item.quantity,
								books: { bookid: item.books.bookid }
							}
						}

					})
				}
			}
			var booking = angular.copy($scope.bookings);
			$http.post(`/rest/bookings`, booking).then(resp => {
				$scope.deleteDeal();
				$http.delete("http://localhost:8080/rest/discount/" + vouchero.discountcodeid).then(resp => {
				})
				location.href = "/cart"
			}).catch(error => {
				alert("Đặt hàng lỗi !");
				console.log(error)
			})
		});
	}
});