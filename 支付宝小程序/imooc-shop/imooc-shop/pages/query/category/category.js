Page({
  data: {},
  onLoad() {},

    searchItems(e) {
        // console.log(e);
        // 获取搜索框中的值
        var itemName = e.detail.value;
        // debugger;
        // 由于后端对itemName为空进行了推荐列表的处理，所以这里的判断可以省略
        // if (itemName != null && itemName != '' && itemName != undefined) {
            my.navigateTo({
                url: "/pages/query/list/list?searchType=words&itemName=" + itemName
            });
        // }
        //  esle {
        //     return;
        // }
    },
});
