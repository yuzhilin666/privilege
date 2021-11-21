const app = getApp();

Page({
  data: {
      addressId: "",
      address: {},
  },
  onLoad(params) {
      var me = this;
      var addressId = params.addressId
      me.setData({
          addressId: addressId
      });

      if (addressId != null && addressId != undefined && addressId != '') {
          // 获取全局的用户对象
          var userInfo = app.getGlobalUserInfo();
          // 使用临时id 1001
          var userId = 1001;
          if (userInfo != null && userInfo != undefined) {
              userId = userInfo.id;
          }

          // 请求接口
          my.httpRequest({
              url: app.serverUrl + '/address/fetch?addressId=' + addressId
                                                                      + '&userId=' + userId,
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

                      me.setData({
                          address: address,
                      });

                  }
              },
              complete: function(res) {
                  my.hideNavigationBarLoading();
                  my.hideLoading();
              }
          });
      }
  },

    submitAddress(e) {
        var me = this;

        var receiver = e.detail.value.receiver;
        var mobile = e.detail.value.mobile;
        var descAddress = e.detail.value.descAddress;
        var txtCity = e.detail.value.txtCity;

        if (receiver == null || receiver == undefined || receiver == '') {
            my.alert({
              title: '友情提示' ,
              content: "请输入收货人",
              buttonText: "OK",
            });
            return;
        }
        if (mobile == null || mobile == undefined || mobile == '') {
            my.alert({
                title: '友情提示',
                content: "请输入手机号",
                buttonText: "OK",
            });
            return;
        } 
        if (descAddress == null || descAddress == undefined || descAddress == '') {
            my.alert({
                title: '友情提示',
                content: "请输入详细地址",
                buttonText: "OK",
            });
            return;
        } 
        if (txtCity == null || txtCity == undefined || txtCity == '') {
            my.alert({
                title: '友情提示',
                content: "请选择一个城市",
                buttonText: "OK",
            });
            return;
        }    

        // 获取全局的用户对象
        var userInfo = app.getGlobalUserInfo();

        // 使用临时id 1001
        var userId = 1001;
        if (userInfo != null && userInfo != undefined) {
            userId = userInfo.id;
        }

        // var addressId ="";
        var addressId = me.data.addressId;
        if (addressId == undefined) {
            addressId = "";
        }

        // 发送请求到后端
        my.showNavigationBarLoading();
        my.showLoading({
            content: "疯狂加载中..."
        });

        // 请求接口
        my.httpRequest({
            url: app.serverUrl + '/address/createOrUpdate?addressId=' + addressId,
            data: {
                userId: userId,
                receiver: receiver,
                mobile: mobile,
                city: txtCity,
                descAddress: descAddress
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
                console.log("========== 提交保存地址信息 start ===========");
                console.log(myData);
                console.log("========== 提交保存地址信息 end ===========");
                if (myData.status == 200) {
                    // 获得地址
                    var address = myData.data;
                    my.setStorageSync({
                        key: 'addressChoosed', // 缓存数据的key
                        data: address, // 要缓存的数据
                    });

                    // 往前退回1级页面
                   my.navigateBack({
                     delta: 1
                   });
                }
            },
            complete: function(res) {
                my.hideNavigationBarLoading();
                my.hideLoading();
            }
        });

    },

    tempChooseCity() {
        my.chooseCity({
            showLocatedCity: false,
            showHotCities: false,
            cities: [
                { city: "北京", adCode: "1001", spell: "beijing"},
                { city: "上海", adCode: "1002", spell: "shanghai" },
                { city: "广州", adCode: "1003", spell: "guangzhou" },
            ],
            hotCities: [
                { city: "杭州", adCode: "2001", spell: "hangzhou" },
                { city: "苏州", adCode: "2002", spell: "suzhou" }
            ],
            success: (res) => {
                console.log(res.city);
                console.log(res);

            },
        });
    },

});
