const app = getApp();

Page({
  data: {
      addressList: [],
      defaultChoose: 0,     // 默认选中的index定义
      defaultRdoChoose: 0,     // 默认radio选中的下标
  },
  onLoad(params) {

      var addressChooseId = params.addressChooseId;
      this.setData({
          addressChooseId: addressChooseId
      });

      this.init();
  },

  init() {
      var me = this;

      // 获取全局的用户对象
      var userInfo = app.getGlobalUserInfo();
      // 使用临时id 1001
      var userId = 1001;
      if (userInfo != null && userInfo != undefined) {
          userId = userInfo.id;
      }

      // 发送请求到后端
      my.showNavigationBarLoading();
      my.showLoading({
          content: "疯狂加载中..."
      });

      // 请求接口
      my.httpRequest({
          url: app.serverUrl + '/address/addressList/' + userId,
          method: 'POST',
          header: {
              'content-type': 'application/json'
          },
          dataType: 'json',
          success: function(res) {
              console.log(res);
              // 获取拿到后端的数据
              var myData = res.data;
              console.log("========== 查询地址list start ===========");
              console.log(myData);
              console.log("========== 查询地址list end ===========");
              if (myData.status == 200) {
                  // 获得地址
                  var addressList = myData.data;

                  me.setData({
                    addressList: addressList,
                  });

                    // 匹配传过来的addressChooseId是否和addressList中的某个地址匹配，匹配则选中
                  for (var i = 0; i < addressList.length; i ++) {
                      var address = addressList[i];
                     
                      // radio 选中
                      if (address.isDefault == 1) {
                          me.setData({
                              defaultRdoChoose: i,
                          });
                      }

                      // 选中信封效果
                      var addressChooseId = me.data.addressChooseId;
                      if (addressChooseId != null && addressChooseId != undefined && addressChooseId != '') {
                          if (addressChooseId == address.id) {
                            me.setData({
                                defaultChoose : i,
                            });
                          }
                      }
                      
                  }

              }
          },
          complete: function(res) {
              my.hideNavigationBarLoading();
              my.hideLoading();
          }
      });


  },

    setDefault(e) {
        var defaultAddressId = e.detail.value;

        // 获取全局的用户对象
        var userInfo = app.getGlobalUserInfo();
        // 使用临时id 1001
        var userId = 1001;
        if (userInfo != null && userInfo != undefined) {
            userId = userInfo.id;
        }

        // 发送请求到后端
        my.showNavigationBarLoading();
        my.showLoading({
            content: "疯狂加载中..."
        });

        // 请求接口
        my.httpRequest({
            url: app.serverUrl + '/address/setDefault?userId=' + userId
                                                + '&addressId=' + defaultAddressId,
            method: 'POST',
            header: {
                'content-type': 'application/json'
            },
            dataType: 'json',
            success: function(res) {},
            complete: function(res) {
                my.hideNavigationBarLoading();
                my.hideLoading();
            }
        });
    },

    confirmChoose() {
        // 获得地址下标
        var addressIndex = this.data.defaultChoose;
        // 获得地址列表
        var addressList = this.data.addressList;
        // 获得具体的地址对象
        var address  = addressList[addressIndex];
        // 更新地址缓存
        my.setStorageSync({
            key: 'addressChoosed', // 缓存数据的key
            data: address, // 要缓存的数据
        });

        my.navigateBack({
            delta: 1
        });
    },

    chooseMe(e) {
        var clickIndex = e.target.dataset.clickIndex;
        this.setData({
            defaultChoose : clickIndex,
        });
    },

    modifyMe(e) {
        var addressId = e.target.dataset.addressId;
        my.redirectTo({
            url: '/pages/orders/addressInfo/addressInfo?addressId=' + addressId,
        });
    },

    deleteMe(e) {
        var me = this;
        var addressId = e.target.dataset.addressId;

        my.confirm({
            title: "友情提示",
            content: "确认删除改地址吗？",
            confirmButtonText: "确认",
            cancelButtonText: "取消",
            success: (res) => {
                if (res.confirm) {
                    // 发送请求到后端
                    my.showNavigationBarLoading();
                    my.showLoading({
                        content: "疯狂加载中..."
                    });

                    // 请求接口
                    my.httpRequest({
                        url: app.serverUrl + '/address/delete/' + addressId,
                        method: 'POST',
                        header: {
                            'content-type': 'application/json'
                        },
                        dataType: 'json',
                        success: function(res) {
                            // 获取拿到后端的数据
                            var myData = res.data;
                            
                            if (myData.status == 200) {
                                // 重新刷新当前页面的地址列表数据
                                me.init();

                                // 判断删除的address是否和缓存中的一致，如果一致，则清楚对应的缓存数据
                                var addressChoosed = my.getStorageSync({ key: 'addressChoosed' }).data;
                                if (addressChoosed != null && addressChoosed != undefined && addressChoosed.id == addressId) {
                                    my.removeStorageSync({
                                        key: 'addressChoosed', // 缓存数据的key
                                    });
                                }
                            }
                        },
                        complete: function(res) {
                            my.hideNavigationBarLoading();
                            my.hideLoading();
                        }
                    });
                }
            },
        });
    },

  addNewAddress() {
      my.redirectTo({
          url: '/pages/orders/addressInfo/addressInfo',
      });
  }
});
