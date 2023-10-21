
function choose(e){ 
    console.log("ad")
    var a = document.getElementById(e);
    var b =  a.parentElement.parentElement;
    var c = b.children[2]
    var d = c.getElementsByClassName('form-check-input')
    if(a.checked){ 
        for(var i=0; i<d.length;i++){ 
            d[i].checked=true;
        }
    }
    else{
        for(var i=0; i<d.length;i++){ 
            d[i].checked=false;
        }
    }

}

function chooseAll(e){ 
    var f = document.getElementsByName('inp')
    for(var i=0; i< f.length;i++){ 
        f[i].checked= true;
        choose( f[i].getAttribute('id'));
    }
    
}


function deleteAll(e){ 
    var f = document.getElementsByName('inp')
    for(var i=0; i< f.length;i++){ 
        f[i].checked= false;
        choose( f[i].getAttribute('id'));
    }
    
}

function changeQuantity(e){ 
    console.log("kdf"+ e.value)
   
}

const app = angular.module("cart_app",[]);
app.controller("cart_ctrl", function($scope,$http){ 
	
})

// var list = [];
// function addetail(){
//     var inp = document.getElementsByName('carttest');
//     alert("jhdjfhs"+ inp[1].nodeName)

//    for(var i =0; i<inp.length;i++){ 
//         if(inp[i].checked){ 
//             alert("yes"+i)
//             list[i] = i; 
//         }
      
//    }
//    alert("sl"+ list.length)
// //    alert("jskfd"+ list_detail.length)
// }

// const app = angular.module("cart_app",[]);

// let host = "https://localhost:8080/api/cart";
// app.controller("cart_ctrl", function($scope,$http){ 
//     $scope.carts= {}
//     $scope.cart= {}
//        $scope.loadAll =  function(){ 
//            var url = `${host}`
//            $http.get(url).then(resp =>{ 
//             $scope.carts = resp.data;
//             console.log("loadAll()")
//            })
//         }

//         $scope.addCart = function(id){ 
//             var a = $scope.carts.find(cart => cart.bookId == id);
//             if(a){ 
//                 alert("Đã có trong giỏ")
//             }
//             else{ 
//                 alert("chưa có trong giỏ")
//             }
//             // var url = `${host}/id`
//             // $http.get(url).then(resp =>{ 
//             //  $scope.data = resp.data;
//             //  console.log("loadAll()")
//             // })
//         }
// });
// 	$scope.cart = { 
// 		items:[],

// 		//thêm sản phẩm vào giỏ hàng
// 		add(id){
// 			var item = this.items.find(item => item.id == id);
// 			if(item){ 
// 				// nếu có trong gỏ hàng items rồi thì tăng số lượng
// 				item.qty ++;
// 				//sẽ viết lưu vào sql

// 			}
// 			else{
// 				$http.get(`/rest/products/${id}`).then(resp =>{ 
// 					resp.data.qty = 1;
// 					this.items.push(resp.data);
// 					//sẽ viết lưu vào sql
// 				})
// 			}
// 		},

// 		//Xoá sản phẩm trong giỏ
// 		remove(id){
// 			var index = this.items.findIndex(item => item.id ==id);
// 			this.items.splice(index,1);
// 			//sẽ viết lưu vào sql
// 		},


// 		//Tính thành tiền của 1 sp
// 		amt_of(item){},

// 		//Tính tổng số lượng của cá mặt hàng
// 		get count(){
// 			return this.items
// 			.map(item => item.qty)
// 			.reduce((total,qty)=> total += qty,0);
// 		},

// 		//Tính tổng tiền của các mặt hàng
// 		get amount(){
// 			return this.items
// 			.map(item => item.qty* item.price)
// 			.reduce((total,qty)=> total += qty,0);
// 		},

// 		//Lưu giỏ hàng vào Local storage
	
// 	}
// 	$scope.cart.loadFromLocalStore();

// 	// $scope.order = {
// 	// 	createDate : new Date(),
// 	// 	address : "",
// 	// 	account:  {username:$("#user1").text()},
		
// 		//Tạo ra orderDtail là hoá đơn chi tiết trước của từng sản phẩm trong order
// 		// Rồi sau đó mới đêm toàn bộ order đi tính

// 		// get orderDetails(){ 
// 		// 		return $scope.cart.items.map(item => {
// 		// 			return {
// 		// 				product : {id : item.id},
// 		// 				price : item.price,
// 		// 				quantity : item.qty
// 		// 			}
// 		// 		})
// 		// },
		
// 		// purchase(){
// 		// 	var order = angular.copy(this);
// 		// 	//thực hiện đặt hàng
// 		// 	$http.post(`/rest/orders`,order).then(resp =>{ 
// 		// 		alert("Đặt hàng thành công");
// 		// 		$scope.cart.clear();
				
// 		// 		location.href = "/order/detail/"+ resp.data.id;
// 		// 		console.log("ksjd"+ resp.data)
// 		// 	}).catch(error =>{
// 		// 		alert("Đặt hàng lỗi !");
// 		// 		console.log(error)})
// 		// }
// 	});

// // });

