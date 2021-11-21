const app = getApp();

Page({

  data: {},

  onLoad() {
    console.log("页面初次加载");

    console.log("=============== start ===============");

    console.log(app.name);
    console.log(app.age);
    console.log(app.isBoy);

    console.log(app.course);

    console.log(app.sayHello());

    console.log("=============== end ===============");
  },

  onReady() {
    console.log("页面就绪，渲染完成");
  },

  onShow() {
    console.log("页面显示");
  },

  onHide() {
    console.log("页面隐藏");
  },

  onUnload() {
    console.log("页面关闭");
  },
  

  close() {
    my.redirectTo({
      url: "/pages/index/index",
    });
  },

  onTitleClick() {
    console.log("标题被点击");
  },

  onReachBottom() {
    console.log("页面触底了");
  },

  onPullDownRefresh() {
    console.log("下拉刷新触发");
  },

  onShareAppMessage() {
    console.log("分享");
    return {
      title: "hello world",
      desc: "这是一个描述内容",
      path: "pages/hello/hello"
    }
  },

});
