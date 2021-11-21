const app = getApp();

Page({
    data: {
        headerImagesArr: [],
        item: {},
        itemContentArr: [],

        animationInfo: {},
        animationOpacity: 0,

        cartIco: "cart-empty", 

        unlikeHidden: false,
        likeHidden: true,
    },
    onShow() {
        // 创建动画
        var animation = my.createAnimation();
        this.setData({
            // 导出动画效果到页面
            animationInfo: animation.export()
        });
    },

    // 添加商品到购物车
    addToCart() {
        var me = this;

        me.setData({
            animationOpacity: 1
        });

        me.showAddToCartAnimation();


        // 商品id存入缓存购物车
        var itemId = me.data.item.id;
        me.cartItemIncrease(itemId);
    },

    // 跳转到购物车页面
    goToCart() {
        my.switchTab({
            url: '/pages/shoppingCart/cart/cart'
        });
    },

    // 商品放入购物车
    cartItemIncrease(itemId) {
        var me = this;

        // 从缓存中拿到购物车数组对象
        var cartItemIdArray = my.getStorageSync({
            key: 'cartItemIdArray', // 缓存数据的key
        }).data;
        // 判断cartItemIdArray是否为空
        if (cartItemIdArray == null || cartItemIdArray == undefined) {
            // 构建空的购物车数组对象
            cartItemIdArray = [];
        }

        // 定义标识用于判断购物车缓存中是否含有当前页的商品
        var isItemAdded = false;
        for (var i = 0; i < cartItemIdArray.length ; i++) {
            var item = cartItemIdArray[i];
            if (item != null && item != undefined && item.itemId == itemId) {
                // 删除原来的item
                cartItemIdArray.splice(i, 1);
                // 商品counts累加1
                var counts = item.counts + 1;
                // 重新构建商品对象
                var cartItemNew = app.cartItem(itemId, counts);
                cartItemIdArray.push(cartItemNew);
                isItemAdded = true;
                break;
            }
        }

        // 在没有添加过当前商品的时候，新创建一个对象放入数组
        if (!isItemAdded) {
            // 构建新的商品对象
            var cartItem = app.cartItem(itemId, 1);
            // 把这个商品对象放入购物车
            cartItemIdArray.push(cartItem);
        }
        
        // 把cartItemIdArray存入缓存
        my.setStorageSync({
            key: 'cartItemIdArray', // 缓存数据的key
            data: cartItemIdArray, // 要缓存的数据
        });
    },

    // 实现动画效果
    showAddToCartAnimation() {
        // 创建动画
        var animation = my.createAnimation({
            duration: 500
        });
        this.animation = animation;

        // rotate: 旋转
        // this.animation.rotate(-180).step();
        // translateX 在水平轴上移动
        // this.animation.translateX("296rpx").step();
        // 旋转的同时，又在水平轴上移动
        this.animation.rotate(-180).translateX("296rpx").step();

        this.setData({
            // 导出动画效果到页面
            animationInfo: this.animation.export()
        });

        // 复原动画
        setTimeout(function() {
            this.setData({
                animationOpacity: 0,
                cartIco: "cart-full",
            });

            setTimeout(function() {
                this.animation.rotate(0).translateX(0).step({
                    duration: 0
                });
                this.setData({
                    animationInfo: this.animation.export()
                });
            }.bind(this), 550);
        }.bind(this), 600);
 
    },

    onLoad(params) {
        var me = this;

        // 获取上一个页面传过来的商品id主键
        var itemId = params.itemId;

        my.showNavigationBarLoading();
        my.showLoading({
            content: "疯狂加载中..."
        });

        // 请求接口，查询商品详情
        my.httpRequest({
            url: app.serverUrl + '/items/searchById?itemId=' + itemId,
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
                    var item = myData.data;
                    console.log(item);
                    // 获取封面图的数组字符串，并且转换为json array
                    var headerImagesStr = item.headerImages;
                    var headerImagesArr = JSON.parse(headerImagesStr);

                    // 获取详情内容
                    var itemContentArr = JSON.parse(item.content);

                    // 把新的数据重新覆盖数据绑定中的原有的值
                    me.setData({
                        headerImagesArr: headerImagesArr,
                        item: item,
                        itemContentArr: itemContentArr,
                    //     titleName: "搜索结果"
                    });
                }
            },
            complete: function(res) {
                my.hideNavigationBarLoading();
                my.hideLoading();
            }
        });

        var userInfo = app.getGlobalUserInfo();
        if (userInfo != null && userInfo != undefined) {
            // 查询用户是否收藏商品
            my.httpRequest({
                url: app.serverUrl + '/item/userIsLikeItem?itemId=' + itemId
                                                                              + '&userId=' + userInfo.id,
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
                        var isLike = myData.data;
                        
                        if (isLike == 1) {
                            me.setData({
                                unlikeHidden: true,
                                likeHidden: false,
                            });
                        } else {
                            me.setData({
                                unlikeHidden: false,
                                likeHidden: true,
                            });
                        }
                    }
                }
            });
        }
        
    },

    likeItem() {
        var me = this;
        var userInfo = app.getGlobalUserInfo();
        if (userInfo == null || userInfo == undefined) {
            my.confirm({
                title: "温馨提示",
                content: "收藏商品请前往登录",
                confirmButtonText: "登录",
                cancelButtonText: "取消",
                success: (res) => {
                    if (res.confirm) {
                        my.switchTab({
                            url: "/pages/mine/info/info",
                        });
                    }
                },
            });
        } else {
            my.httpRequest({
                url: app.serverUrl + '/item/like?itemId=' + me.data.item.id
                                                           + '&userId=' + userInfo.id,
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
                        me.setData({
                            unlikeHidden: true,
                            likeHidden: false,
                        });
                    }
                }
            });
        }
    },

    unlikeItem() {
        var me = this;
        var userInfo = app.getGlobalUserInfo();
        my.httpRequest({
            url: app.serverUrl + '/item/unlike?itemId=' + me.data.item.id
                + '&userId=' + userInfo.id,
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
                    me.setData({
                        unlikeHidden: false,
                        likeHidden: true,
                    });
                }
            }
        });
    },

    // 用户直接购买，跳过购物车
    buyMe() {
        var me = this;
        var userInfo = app.getGlobalUserInfo();
        if (userInfo == null || userInfo == undefined) {
            my.confirm({
                title: "温馨提示",
                content: "购买商品先登录",
                confirmButtonText: "登录",
                cancelButtonText: "取消",
                success: (res) => {
                    if (res.confirm) {
                        my.switchTab({
                            url: "/pages/mine/info/info",
                        });
                    }
                },
            });
        } else {
            // 构建预处理订单商品对象
            var preOrderItem = app.finalCartItem(me.data.item, 1, null);
            var preOrderItemList = [];
            preOrderItemList.push(preOrderItem);
            my.setStorageSync({
                key: "preOrderItemList",
                data: preOrderItemList,
            });

            my.navigateTo({
                url: "/pages/orders/confrimOrder/confrimOrder"
            });
        }
       
    },
});
