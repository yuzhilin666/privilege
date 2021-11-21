Page({
  data: {},
  onLoad() {},

  tap() {
    console.log("点击事件");
  },

  longTap() {
    console.log("长按点击事件");
  },

  touchStart() {
    console.log("开始触摸");
  },

  touchMove() {
    console.log("触摸后移动");
  },

  touchEnd() {
    console.log("触摸结束");
  },

  touchCancel() {
    console.log("触摸中断");
  },

  tapData(e) {
    console.log(e);
    var name = e.target.dataset.name;
    var age = e.target.dataset.age;
    console.log("姓名：" + name);
    console.log("年龄：" + age);

    var userNickName = e.target.dataset.userNickName;
    console.log("userNickName：" + userNickName);

    var userage = e.target.dataset.userage;
    console.log("userage" + userage);
  },

});
