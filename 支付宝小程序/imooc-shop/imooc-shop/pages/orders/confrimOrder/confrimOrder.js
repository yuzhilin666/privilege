const app = getApp();

Page({
  data: {
      preOrderItemList: [],
      checkAllTotalAmount: 0,

      addressEmpty: false,
      addressFull: true,
  },
  onLoad() {},

  initAddress() {
      var me = this;

      // 获取全局的用户对象
      var userInfo = app.getGlobalUserInfo();
      // 使用临时id 1001
      var userId = 1001;
      if (userInfo != null && userInfo != undefined) {
          userId = userInfo.id;
      }


      // 首先应该到缓存里去查询是否存在地址缓存信息，减少后端的交互
      var addressChoosed = my.getStorageSync({ key: 'addressChoosed'}).data;
      if (addressChoosed != null && addressChoosed != undefined) {
          me.setData({
              addressEmpty: true,
              addressFull: false,

              addressInfo: addressChoosed,
          });
          return;
      }
          

      // 发送请求到后端
      my.showNavigationBarLoading();
      my.showLoading({
          content: "疯狂加载中..."
      });

      // 请求接口
      my.httpRequest({
          url: app.serverUrl + '/address/default/' + userId,
          method: 'POST',
          header: {
              'content-type': 'application/json'
          },
          dataType: 'json',
          success: function(res) {
              console.log(res);
              // 获取拿到后端的数据
              var myData = res.data;
              console.log("========== 查询地址信息 start ===========");
              console.log(myData);
              console.log("========== 查询地址信息 end ===========");
              if (myData.status == 200) {
                  // 获得地址
                  var address = myData.data;
                  if (address == null || address == undefined) {
                        me.setData({
                            addressEmpty: false,
                            addressFull: true,
                        });
                  } else {
                      me.setData({
                          addressEmpty: true,
                          addressFull: false,

                          addressInfo: address,
                      });
                  }

              }
          },
          complete: function(res) {
              my.hideNavigationBarLoading();
              my.hideLoading();
          }
      });
  },

  onShow() {
      var me = this;

      me.initAddress();

      // 从缓存中获取预处理订单数据列表
      var preOrderItemList = my.getStorageSync({key: 'preOrderItemList'}).data;
      
      var checkAllTotalAmount = 0;
      for (var i = 0; i < preOrderItemList.length ; i ++) {
          var item = preOrderItemList[i].item;
          var itemCounts = preOrderItemList[i].counts;
          checkAllTotalAmount += Number(item.priceDiscountYuan) * itemCounts;
      }

      me.setData({
          preOrderItemList: preOrderItemList,
          checkAllTotalAmount: checkAllTotalAmount,
      });
  },

    // 监听文本框事件，获取文本框中的内容，然后进行数据绑定
    setRemark(e) {
        var remark = e.detail.value;
        this.setData({
            orderRemark: remark,
        });
        // console.log(remark);
    },

    // 清理购物车缓存 和 预处理订单数据列表
    clearItemCache() {
        var cartItemIdArray = my.getStorageSync({ key: 'cartItemIdArray' }).data;
        var preOrderItemList = my.getStorageSync({ key: 'preOrderItemList' }).data;

        if (cartItemIdArray != null && cartItemIdArray != undefined 
            && preOrderItemList != null && preOrderItemList != undefined ) {
                
            for (var i = 0 ; i < preOrderItemList.length ; i ++) {
                var itemId = preOrderItemList[i].item.id;
                for (var j = 0; j < cartItemIdArray.length; j++) {
                    if (itemId == cartItemIdArray[j].itemId) {
                        cartItemIdArray.splice(j, 1);
                        break;
                    }
                }
            }

            if (cartItemIdArray.length > 0) {
                // 更新缓存
                my.setStorageSync({
                    key: 'cartItemIdArray', // 缓存数据的key
                    data: cartItemIdArray // 要缓存的数据
                });
            } else {
                my.removeStorageSync({
                    key: 'cartItemIdArray', // 缓存数据的key
                });
            }
        }

        my.removeStorageSync({
            key: 'preOrderItemList', // 缓存数据的key
        });
    },

    submitOrder() {
        var me = this;

        // 从缓存中获取预处理订单数据列表
        var preOrderItemList = my.getStorageSync({ key: 'preOrderItemList' }).data;

        // 循环这个list，构建商品str
        var itemStr = "";
        for (var i = 0; i < preOrderItemList.length; i++) {
            var itemId = preOrderItemList[i].item.id;
            var itemCounts = preOrderItemList[i].counts;
            var singleItem = itemId + "|" + itemCounts + ",";
            itemStr += singleItem;
        }

        // 拼接完毕后，发送数据到后端，调用接口，生成[待付款]订单
        // TODO: 由于目前没有登录，暂时可以使用临时的用户id
        // var buyerId = "temp-buyerId";
        var remark = me.data.orderRemark;
        // TODO: 地址管理，后续完善
        // var addressId = "";

        // 获取全局的用户对象
        var userInfo = app.getGlobalUserInfo();
        // 使用临时id 1001
        var userId = 1001;
        if (userInfo != null && userInfo != undefined) {
            userId = userInfo.id;
        }
        var addressInfo = me.data.addressInfo;

        // 发送请求到后端
        my.showNavigationBarLoading();
        my.showLoading({
            content: "疯狂加载中..."
        });

        // 请求接口，查询商品详情
        my.httpRequest({
            url: app.serverUrl + '/order/createOrder?itemStr=' + itemStr
                                                                        + "&buyerId=" + userId
                                                                        + "&addressId=" + addressInfo.id,
            data: {
                remark: (remark == undefined ? "" : remark)
            },
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
                    // 获得待付款订单状态的id
                    var orderId = myData.data;
                    console.log(orderId);

                    // 清理缓存
                    me.clearItemCache();

                    // 跳转到支付页面
                    my.redirectTo({
                        url: '/pages/orders/payment/payment?orderId=' + orderId
                                    + '&orderAmount=' + me.data.checkAllTotalAmount
                    });
                }
            },
            complete: function(res) {
                my.hideNavigationBarLoading();
                my.hideLoading();
            }
        });
    },
});
