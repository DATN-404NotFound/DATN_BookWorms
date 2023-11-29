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
}


function Active(cartid, action) {
	var e = document.getElementById("quantity" + cartid).value;
	$.get("http://localhost:8080/rest/cart/" + cartid, function (data, status) {
		json = data;
		json.quantity = e;
		var shop = json.books.shopid;
		switch (action) {
			case 'PUT': {
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
	$.ajax({
		url: "http://localhost:8080/rest/cart",
		type: "PUT",
		data: JSON.stringify(json),
		contentType: "application/json; charset=utf-8",
		success: function (resultData) {
			$("#cart" + id).children().eq(5).text(formatNumber(resultData.books.price * resultData.quantity, ".", ","));
			checkAll();
		},
	});

}



function deleteCart(id, shop) {
	$.ajax({
		url: "http://localhost:8080/rest/cart/" + id,
		type: "DELETE",
		success: function (resultData) {
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
		if (chil[i].children[0].children[0].checked) {
			a += 1;
			var pa = parent.parentElement.parentElement;
			var shops = pa.children[0].children[0];
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



const app = angular.module("my_app", []);

let host = "http://localhost:8080/rest/cart"
app.controller("cart_ctrl", function ($scope, $http, $rootScope) {


	$scope.loadContent = function (bookid) {
		$http.get("/Client/Js/content.json").then(resp => {
			$scope.bookcontent = resp.data;
			$scope.itembookcontent = $scope.bookcontent.find(item => item.bookid == bookid);
			$('#content' + bookid).text($scope.itembookcontent.content)
		})
	}



	/////////////////// Product list
	$scope.sp = [];
	$scope.loadProduct = function () {
		$http.get("http://localhost:8080/rest/books").then(resp => {
			var am = localStorage.getItem('filter1');
			$scope.sp = resp.data;
			if (am !== null && am != "") {
				$scope.filterCate.push(am);
				$scope.getList(1, "type", "listtype", $scope.filterCate)
				localStorage.removeItem("filter1");
			}
		})
	}

	$scope.setImageBook = function (bookId) {
		let url = `http://localhost:8080/rest/imagebook/` + bookId;
		$http.get(url).then(resp => {
			var a = [];
			a = (resp.data);
			document.getElementById('product' + bookId).src = "/Client/images/" + a[0].name
		}).catch(error => {
			console.log("Error", error)
		});;
	}

	$scope.setShop = function (shopId) {
		let url = `http://localhost:8080/rest/files/` + shopId;
		$http.get(url).then(resp => {
			var a = [];
			a = (resp.data);
			document.getElementById('shopimage' + shopId).src = "/Client/images/" + a[0].filename
		}).catch(error => {
			console.log("Error", error)
		});;
	}

	$scope.loadProduct();
	$scope.filterCate = [];
	$scope.filterWrite = [];
	$scope.filterPC = [];
	$scope.filterPrice = [];
	$scope.filterValueCate = [];
	$scope.filterValueWrite = [];
	$scope.filterRatting = [];
	$scope.filterValueRatting = [];

	$scope.newyear = function () {
		$scope.or = "-publishingyear"
	}
	$scope.bestseller = function () {
		$scope.or = "-quantitysold"
	}

	$scope.filterChoose = function (stt, name) {
		switch (stt) {
			case 1: {
				$scope.process($scope.filterCate, name);
				$scope.getList(1, "type", "listtype", $scope.filterCate);
				break;
			}
			case 2: {
				$scope.process($scope.filterWrite, name);
				$scope.getList(2, "writer", "listwriter", $scope.filterWrite);
				break;
			}
			case 3: {
				$scope.process($scope.filterPC, name);
				break;
			}
			case 4: {
				var a = name.getAttribute('id');
				if (name.checked) {

					switch (a) {
						case '0': {
							$scope.filterPrice.push("0-100000");
							console.log($scope.filterPrice)
							break;
						}
						case '1': {
							$scope.filterPrice.push("100000-200000");
							break;
						}
						case '2': {
							$scope.filterPrice.push("200000-400000");
							break;
						}
						case '4': {
							$scope.filterPrice.push("400000-600000");
							break;
						}
						case '6': {
							$scope.filterPrice.push("600000-10000000");
							break;

						}
					}
				}
				else {
					switch (a) {
						case '0': {
							var del = $scope.filterPrice.findIndex(i => i === "0-100000");
							$scope.filterPrice.splice(del, 1);
							break;
						}
						case '1': {
							var del = $scope.filterPrice.findIndex(i => i === "100000-200000");
							$scope.filterPrice.splice(del, 1);
							break;
						}
						case '2': {
							var del = $scope.filterPrice.findIndex(i => i === "200000-400000");
							$scope.filterPrice.splice(del, 1);
							break;
						}
						case '4': {
							var del = $scope.filterPrice.findIndex(i => i === "400000-600000");
							$scope.filterPrice.splice(del, 1);
							break;
						}
						case '6': {
							var del = $scope.filterPrice.findIndex(i => i === "600000-10000000");
							$scope.filterPrice.splice(del, 1);
							break;
						}

					}
				}
				break;
			}
			case 5: {
				$scope.process($scope.filterRatting, name);
				$scope.getList(3, "Eva", "listeva", $scope.filterRatting);
				break;
			}
		}
		$scope.loadProduct();
	}

	$scope.filterByGenres = function (b) {
		if ($scope.filterPC.length == 0) {
			return ($scope.filterPC.indexOf(b.publishingcompanies.namepc) == -1);
		}
		else {
			return ($scope.filterPC.indexOf(b.publishingcompanies.namepc) !== -1);
		}
	};


	$(document).ready(function () {
		$('#showtab').click(function () {
			$('.nav-pills a[href="#products_us"]').tab('show');
		})
	});


	//header
	$scope.mms = function () {
		localStorage.setItem("searchfilter", $scope.searchfilter);

	}


	$scope.filterAll = function (b) {
		$scope.searchfilter = localStorage.getItem("searchfilter")
		if ($scope.searchfilter == null) {
			return (JSON.stringify(angular.lowercase(b.bookname)).indexOf($scope.searchfilter) == -1 || JSON.stringify(b.price).indexOf($scope.searchfilter) == -1);
		}
		else {
			return (JSON.stringify(angular.lowercase(b.bookname)).indexOf($scope.searchfilter) != -1 || JSON.stringify(b.price).indexOf($scope.searchfilter) != -1);
		}
	}

	$scope.filtershop = function (s) {
		$scope.searchfilter = localStorage.getItem("searchfilter")
		if ($scope.searchfilter == null) {
			return (JSON.stringify(angular.lowercase(s.shopname)).indexOf($scope.searchfilter) == -1);
		}
		else {
			return (JSON.stringify(angular.lowercase(s.shopname)).indexOf($scope.searchfilter) != -1);
		}
	}

	$scope.filterByCate = function (b) {
		if ($scope.filterCate.length == 0) {
			return ($scope.filterValueCate.indexOf(b.bookid) == -1);
		}
		else {
			return ($scope.filterValueCate.indexOf(b.bookid) !== -1);
		}
	};


	$scope.filterByWriter = function (b) {
		if ($scope.filterWrite.length == 0) {
			return ($scope.filterValueWrite.indexOf(b.bookid) == -1);
		}
		else {
			return ($scope.filterValueWrite.indexOf(b.bookid) !== -1);
		}
	};

	$scope.filterByRatting = function (b) {
		if ($scope.filterRatting.length == 0) {
			return ($scope.filterValueRatting.indexOf(b.bookid) == -1);
		}
		else {
			return ($scope.filterValueRatting.indexOf(b.bookid) !== -1);
		}
	};



	$scope.filterByPrice = function (b) {
		var object;
		if ($scope.filterPrice.length == 0) {
			return (b.price >= 0 && b.price <= 10000000)
		}
		else {
			$scope.filterPrice.forEach(p => {
				if ((b.price >= Number(p.substring(0, p.indexOf("-"))) && b.price <= Number(p.substring(p.indexOf("-") + 1, p.length)))) {
					object = b
				}
			})
			return object;
		}
	};

	$scope.filterChoose1 = function (stt, name) {
		var la = [];
		la.push(name.getAttribute('id'));
		localStorage.setItem('filter1', la);
	}


	$scope.process = function (array, name) {
		if (name.checked) {
			array.push(name.getAttribute('id'));
		}
		else {
			var del = array.findIndex(i => i === name.getAttribute('id'));
			array.splice(del, 1)
		}
	}


	$scope.getList = function (mang, name, key, value) {
		$http.get("http://localhost:8080/rest/books/" + name + "?" + key + "=" + value + "").then(resp => {
			switch (mang) {
				case 1: {
					$scope.filterValueCate = resp.data;
					break;
				}
				case 2: {
					$scope.filterValueWrite = resp.data;
					break;
				}
				case 3: {
					$scope.filterValueRatting = resp.data;
					break;
				}
			}
		})
	}

	$scope.booksCate = [];
	$scope.loadAll = function () {
		$http.get("/rest/books/cate/4").then(resp => {
			$scope.a = 4;
			$scope.b = 0;
			$scope.booksCate = resp.data;
		})
	}
	$scope.quantityPro = 1;

	// HomePage
	$scope.buyNow = function (id) {
		$http.get("/rest/books/" + id).then(resp => {
			var book = [];
			book.push(resp.data);
			localStorage.setItem('books', JSON.stringify(book));
			var shop = [];
			shop.push(book[0].shoponlines);
			localStorage.setItem('shoponline', JSON.stringify(shop));
			var deal = [
				{
					books: book[0],
					quantity: $scope.quantityPro
				}
			]
			var sale = [];
			localStorage.setItem('deal', JSON.stringify(deal));
			localStorage.setItem('sales', JSON.stringify(sale));
			location.href = "/order";
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
		$scope.b = id + 1;
		$scope.edu(cate, 4, $scope.b)
	}

	$scope.prev = function (id, cate) {
		$scope.b = id - 1;
		$scope.edu(cate, 4, $scope.b)
	}

	$scope.setQuantityPro = function (id) {
		$scope.quantityPro = 1;
		$scope.cart.add(id);
	}

	$scope.cart = {
		items: [],
		add(id) {
			var item = this.items.find(item => item.books.bookid == id);
			if (item) {
				item.quantity += $scope.quantityPro;
				var updatecart = `${host}`;
				var cartupdate = angular.copy(item);
				$http.put(updatecart, cartupdate).then(resp => {
					$scope.cart.load();
				})

			} else {
				$http.get(`/rest/books/` + id).then(resp => {
					var s = resp.data;
					$scope.cartss = {
						cartid: "",
						userid: "",
						bookid: s.bookid,
						quantity: $scope.quantityPro
					}
					var addc = angular.copy($scope.cartss)
					$http.post(`/rest/cart`, addc).then(resp => {
						this.items.push(resp.data);
						$scope.cart.load();
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
	$scope.detailBook = function (bookid) {
		location.href = "/product/detail/" + bookid;
	}


	///////////////////////// Shop Page 
	$scope.ListBookOfShop = []
	$scope.toShopDetail = function (shopid) {
		$http.get("http://localhost:8080/rest/books/shop?shopid=" + shopid).then(resp => {
			localStorage.setItem('productShop', JSON.stringify(resp.data));
			//location.href = "/shop/" + shopid
		})
	}

	$scope.shopAll = [];
	$scope.getAllshop = function () {
		$http.get("/rest/shoponline").then(resp => {
			$scope.shopAll = resp.data;

		})
	}

	$scope.addDiscount = function (couoponcode) {
		try {
			$scope.discountAdd = {
				discountcodeid: "",
				saleid: couoponcode,
				userid: "",
				startdiscount: new Date(),
				enddiscount: "2023-12-31",
				isdelete: false,
				minprice: "",
				status: "",

			}
			var dis = angular.copy($scope.discountAdd)
			$http.post(`/rest/discount`, dis).then(resp => {
			})
			document.getElementById('accep' + couoponcode).innerText = "Đã nhận";
			document.getElementById('accep' + couoponcode).disabled
		} catch (error) {
			console.log("error1 : " + error)

		}
	}


	$(document).ready(function () {
		$scope.ListBookOfShop = JSON.parse(localStorage.getItem('productShop'));
	});

	$scope.getAllshop();

	///////////////////////// Cart Page
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
			var b = document.getElementById('totalPriceAll').innerText;
			var reply = b.replaceAll(',', '');
			var c = Number(reply) * Number(data.sales.discountpercentage);
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
		if (!data || data.value === null || vou == undefined) {
			localStorage.setItem('sales', JSON.stringify(salevoucher));
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
	if (c != null) {
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


		})
	}
	document.getElementById('totalPriceAll').innerText = formatNumber(totalPriceAll, ".", ",");
	calculatorPrice();
}



function calculatorPrice() {
	var a = document.getElementById('totalPriceAll').innerText.replaceAll(',', '');
	var b = document.getElementById('shippingPrice').innerText.replaceAll(',', '');
	var c = document.getElementById('totalSales').innerText.replaceAll(',', '');
	var d = document.getElementById('totalFreeShip').innerText.replaceAll(',', '').replaceAll('-', '');
	var e = document.getElementById('totalFinal');
	var f = Number(a) + Number(b) - Number(c) - Number(d);
	e.innerText = formatNumber(f, ".", ",");
}



app.controller("order_ctrl", function ($scope, $http, $timeout) {

/////////////// Deal Page
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
		var timeoutTimer = 0;
		$scope.shopItem.forEach(i => {
			var a = document.getElementById('priceItem' + i.shopid).innerText;
			$scope.bookings = {
				bookingid: i.shopid,
				createat: new Date(),
				cost: Number(a.replace(',', '')),
				userid: "",
				orderstatusid: 1,
				shippingunitid: Number($('#shippunit' + i.shopid).children("option:selected").val()),
				note: $('#noteBooking' + i.shopid).val(),
				get listOfPayments() {
					return {
						paymentid: "",
						createat: new Date(),
						status: "Chưa thanh toán",
						paid: $('#pac').children("option:selected").val(),
						type: Number($('#pay').children("option:selected").val()),
						addressuserid: $('#addressship').children("option:selected").val(),
						addressusers: { addressuserid: $('#addressship').children("option:selected").val() },

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
						else {
							console.log("khác shop" + item.books.shopid)
							return;
						}

					})
				},
			}
			var booking = angular.copy($scope.bookings);
			console.log("ind " + JSON.stringify($scope.bookings))
			$http.post(`/rest/bookings`, booking).then(resp => {
				$scope.deleteDeal();
				$http.delete("http://localhost:8080/rest/discount/" + vouchero.discountcodeid).then(resp => {
				})
				$('#myModal').show();
				$('#iconModel').html('<i  style="font-size: 50px;color: green;" class="bi bi-check-circle"></i> ')
				$('#descrptionInfor').text("Đặt hàng thành công!")
			}).catch(error => {
				$('#myModal').show();
				$('#iconModel').html('<i  style="font-size: 50px;color: red;" class="bi bi-x-circle"></i> ')
				$('#descrptionInfor').text("Đặt hàng thất bại! Vui lòng thử lại")
				console.log(error)
			})
		});
	}
});



