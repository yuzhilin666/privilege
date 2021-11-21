//app.js
let router = require('./utils/router.js')
App({
  router,
  onLaunch: function () {
    
    if (!wx.cloud) {
      console.error('请使用 2.2.3 或以上的基础库以使用云能力')
    } else {
      wx.cloud.init({
        env:'test-1-18466a',//设置当前环境
        traceUser: true,//是否再后台线上用户信息
      })
    }

    this.globalData = {}
  }
})
