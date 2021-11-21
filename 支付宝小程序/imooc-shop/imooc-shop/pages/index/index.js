const app = getApp();

Page({

  data: {
    carousels: [],
    featuresList: [],
    newItemList: [],
  },

  onLoad(query) {

    // 页面加载
    console.info(`Page onLoad with query: ${JSON.stringify(query)}`);
  },
  onPullDownRefresh() {
    // 页面被下拉
    this.initData();
  },
  onReady() {
    this.initData();
  },
  initData() {
    var me = this;

    // 请求轮播图
    my.httpRequest({
        url: app.serverUrl + '/index/carousels',
        method: 'POST',
        header:{ 
          'content-type': 'application/json' 
        },
        // data: { name: 'jack', age:'18' },
        dataType: 'json',
        success: function(res) {
          console.log(res);
          // 获取拿到后端的数据
          var myData = res.data;
          if (myData.status == 200) {
            var carousels = myData.data;
            // 把新的数据重新覆盖数据绑定中的原有的值
            me.setData({
              carousels: carousels
            });
          }
        },
        fail: function(res) {
          console.log("发生错误：" + res);
        },
        complete: function(res) {
          console.log("最终执行的complete：" + res);
        }
    });


    // 请求推荐商品列表
    my.httpRequest({
        url: app.serverUrl + '/index/items/rec',
        method: 'POST',
        header:{ 
          'content-type': 'application/json' 
        },
        dataType: 'json',
        success: function(res) {
          console.log(res);
          // 获取拿到后端的数据
          var myData = res.data;
          if (myData.status == 200) {
            var featuresList = myData.data;
            // 把新的数据重新覆盖数据绑定中的原有的值
            me.setData({
              featuresList: featuresList
            });
          } 
        }
    });

    // 请求新品列表
    my.httpRequest({
        url: app.serverUrl + '/index/items/new',
        method: 'POST',
        header:{ 
          'content-type': 'application/json' 
        },
        dataType: 'json',
        success: function(res) {
          console.log(res);
          // 获取拿到后端的数据
          var myData = res.data;
          if (myData.status == 200) {
            var newItemList = myData.data;
            // 把新的数据重新覆盖数据绑定中的原有的值
            me.setData({
              newItemList: newItemList
            });
          } 
        }
    });
  },

    showItem(e) {
        var itemId = e.target.dataset.itemId;
        my.navigateTo({
            url: "/pages/query/item/item?itemId=" + itemId
        });
    },

    showItemList(e) {
        var itemId = e.target.dataset.itemId;
        var catId = e.target.dataset.catId;
        var searchType = e.target.dataset.searchType; 

        if (searchType == 1) {
            my.navigateTo({
                url: "/pages/query/item/item?itemId=" + itemId
            });
        } else if (searchType == 2) {
            my.navigateTo({
                url: "/pages/query/list/list?searchType=cat&catId=" + catId + "&catName=搜索结果"
            });
        }
    },

  onShow() {
    // 页面显示
    // my.clearStorage();
    // 测试获取购物车缓存信息并且打印
      var cartItemIdArray = my.getStorageSync({
          key: 'cartItemIdArray', // 缓存数据的key
      }).data;
      console.log("====== 首页测试购物车数据 start ======");
      console.log(cartItemIdArray);
      console.log("====== 首页测试购物车数据 end ======");
  },
  onHide() {
    // 页面隐藏
  },
  onUnload() {
    // 页面被关闭
  },
  onTitleClick() {
    // 标题被点击
  },

  onReachBottom() {
    // 页面被拉到底部
  },
  onShareAppMessage() {
    // 返回自定义分享信息
    return {
      title: 'My App',
      desc: 'My App description',
      path: 'pages/index/index',
    };
  },
});
