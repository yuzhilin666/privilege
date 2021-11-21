App({

  serverUrl: "https://www.imoocdsp.com",

  // 定义常量
  name: "imooc",
  age: 18,
  isBoy: true,
  // 定义对象
  course: {
    lesson: "小程序",
    teacher: "风间影月"
  },
  // 定义自定义方法(函数)
  sayHello() {
    console.log("hello imooc~");
  },

    // 构建全局购物车商品对象，{商品ID， 购买数}
    cartItem(itemId, counts) {
        var cartItem = new Object();
        cartItem.itemId = itemId;
        cartItem.counts = counts;
        return cartItem;
    },

    // 构建全局不可变商品对象，{商品对象， 购买数， 是否选中}
    finalCartItem(item, counts, isSelect) {
        var finalCartItem = new Object();
        finalCartItem.item = item;
        finalCartItem.counts = counts;
        finalCartItem.isSelect = isSelect;
        return finalCartItem;
    },

    // 根据商品id获取购物车中某个商品的件数
    fetchItemCounts(cartItemIdArray, itemId) {
        // debugger;
        for (var i=0; i < cartItemIdArray.length; i ++) {
            var item = cartItemIdArray[i];
            if (item != null && item != undefined && itemId == item.itemId) {
                return item.counts;
            }
        }
    },

    // 获取购物车中某个商品是否选中的状态
    fetchItemIsSelect(finalCartItemList, itemId) {
        for (var i = 0; i < finalCartItemList.length; i++) {
            var item = finalCartItemList[i].item;
            if (item != null && item != undefined && itemId == item.id) {
                return finalCartItemList[i].isSelect;
            }
        }
    },

    // 获取购物车中的某个商品对象信息
    fetchItemFromFinalList(finalCartItemList, itemId) {
        for (var i = 0; i < finalCartItemList.length; i++) {
            var item = finalCartItemList[i].item;
            if (item != null && item != undefined && itemId == item.id) {
                return item;
            }
        }
    },

    // 从本地缓存中获取全局的用户对象
    getGlobalUserInfo() {
        // return null;
        var userInfo = my.getStorageSync({ key: 'globalUserInfo' }).data;
        return userInfo;
    },

    // 设置用户的全新信息到本地缓存
    setGlobalUserInfo(userInfo) {
        my.setStorageSync({
            key: 'globalUserInfo', 
            data: userInfo,
        });
    },

    // 支付宝支付
    doAlipay(orderId) {
        var me = this;

        var qq = 1001;

        // 发送请求到后端
        my.showNavigationBarLoading();
        my.showLoading({
            content: "疯狂加载中..."
        });

        // 请求接口，获取支付宝交易号tradeNo
        my.httpRequest({
            url: me.serverUrl + '/team/alipay?orderId=' + orderId + "&qq=" + qq,
            method: 'POST',
            header: {
                'content-type': 'application/json'
            },
            dataType: 'json',
            success: function(res) {
                console.log(res);
                // 获取拿到后端的数据
                var myData = res.data;
                if (myData.status == 200) {
                    var tradeNo = myData.data;
                    console.log("获得支付宝交易流水号 - tradeNo: " + tradeNo);

                    // 小程序端唤起收银台进行支付
                    my.tradePay({
                        tradeNO: tradeNo,
                        success: (res) => {

                            if (res.resultCode == "9000") {
                                my.alert({
                                    title: '支付结果',
                                    content: '支付成功！',
                                    buttonText: '确定',
                                    success: (result) => {
                                        my.switchTab({
                                            url: '/pages/mine/info/info'
                                        });
                                    }
                                });
                            }

                        },
                        fail: (res) => {
                            my.alert({
                                title: '支付结果',
                                content: '支付失败，请重试...',
                                buttonText: '确定',
                            });
                        },
                    });

                } else {
                    my.alert({
                        title: '支付结果',
                        content: '支付失败，请重试...',
                        buttonText: '确定',
                    });
                }
            },
            complete: function(res) {
                my.hideNavigationBarLoading();
                my.hideLoading();
            }
        });
    },

  // onLaunch() {
  //   console.log("项目第一次启动...");
  // },

  // onShow() {
  //   console.log("项目显示...");
  // },

  // onHide() {
  //   console.log("项目隐藏...");
  //   throw new Error("此处发生错误...");
  // },

  // onError() {
  //   console.log("项目发生错误...");
  // },

});
