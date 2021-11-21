const app = getApp();

Page({
    data: {
        titleName: "",
        itemList: [],
    },

    onLoad(params) {
        var me = this;

        var searchType = params.searchType;
        var itemName = params.itemName;
        console.log("searchType:" + searchType);
        console.log("itemName:" + itemName);

        var catId = params.catId;
        var catName = params.catName;
        console.log("catId:" + catId);
        console.log("catName:" + catName);



        // 根据类型去查询结果集
        if (searchType == "cat") {
            my.showNavigationBarLoading();
            my.showLoading({
                content: "疯狂加载中..."
            });

            my.httpRequest({
                url: app.serverUrl + '/items/searchByCat?catId=' + catId,
                method: 'POST',
                header: {
                    'content-type': 'application/json'
                },
                // data: { name: 'jack', age:'18' },
                dataType: 'json',
                success: function(res) {
                    console.log(res);
                    // 获取拿到后端的数据
                    var myData = res.data;
                    if (myData.status == 200) {
                        var itemList = myData.data;
                        console.log(itemList);
                        // 把新的数据重新覆盖数据绑定中的原有的值
                        me.setData({
                            itemList: itemList,
                            titleName: catName
                        });
                    }
                },
                complete: function(res) {
                    my.hideNavigationBarLoading();
                    my.hideLoading();
                }
            });
        } else if (searchType == "words") {
            my.showNavigationBarLoading();
            my.showLoading({
                content: "疯狂加载中..."
            });

            my.httpRequest({
                url: app.serverUrl + '/items/search',
                method: 'POST',
                header: {
                    'content-type': 'application/json'
                },
                data: { itemName: itemName},
                dataType: 'json',
                success: function(res) {
                    console.log(res);
                    // 获取拿到后端的数据
                    var myData = res.data;
                    if (myData.status == 200) {
                        var itemList = myData.data;
                        console.log(itemList);
                        // 把新的数据重新覆盖数据绑定中的原有的值
                        me.setData({
                            itemList: itemList,
                            titleName: "搜索结果"
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
});
