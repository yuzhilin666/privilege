const app = getApp();

Page({
  data: {
      orderId: "",
      orderAmount: 0,
  },

  onLoad(params) {
      var orderId = params.orderId;
      var orderAmount = params.orderAmount;

      this.setData({
          orderId: orderId,
          orderAmount: orderAmount,
      });
  },

    // 支付宝支付
    doAlipay() {
        var me = this;

        var orderId = me.data.orderId;

        var qq = 1001;

        // 发送请求到后端
        my.showNavigationBarLoading();
        my.showLoading({
            content: "疯狂加载中..."
        });

        // 请求接口，获取支付宝交易号tradeNo
        my.httpRequest({
            url: app.serverUrl + '/team/alipay?orderId=' + orderId + "&qq=" + qq,
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
                      title: '支付结果' ,
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
});
