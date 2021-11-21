//index.js
//获取应用实例
const app = getApp()
let store = require('./../../utils/store.js')
let Api = app.Api;
let router = app.router;
Page({
  data: {
    userId:store.getItem('userId')
  },
  onLoad: function () {
    this.getOpenId();
  },
  getOpenId(){
    wx.cloud.callFunction({
      name:'getOpenId',
      data:{
        name:'jack'
      },
      success:res=>{
        console.log('云函数[getOpenId]调用成功'+JSON.stringify(res))
      },
      fail:res=>{
        console.log('云函数[getOpenId]调用失败' + JSON.stringify(res))
      }
    });
  },
  getUserInfo(e){
    // 第一步获取用户信息
    let user = e.detail.userInfo;
    // 第二部调用小程序云函数
    wx.cloud.callFunction({
      name:'login',
      data:{
        user
      },
      success:res => {
        store.setItem('userId',res.result.userId);
        this.setData({
          userId: res.result.userId
        })
      },
      fail:res=>{
        console.log('云函数[登录授权]报错，错误信息为：'+JSON.stringify(res));
      }
    })
  },
  recharge(){
    router.push('pay');
  },
  activity(){
    router.push('activity');
  },
  onShareAppMessage(){
    return {
      title:'欢迎体验慕课支付',
      path:'/pages/index/index',
      imageUrl: '/assets/images/share_mp_logo.png'
    }
  }
})
