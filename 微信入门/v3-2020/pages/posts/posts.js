
import {postList} from '../../data/data.js'


Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   * 钩子函数 hook function
   * 顺序
   * 条件渲染 列表渲染
   */
  async onLoad(options) {
    // setData
    // 更新
    // 创建+更新
    // JSON
    // ES6

    wx.setStorageSync('flag', 2)

    const flag =await wx.getStorage({
      key: 'flag',
      // success(value){
      //   console.log(value.data)
      // }
    })

    // flag.then((value)=>{
    //   console.log(value)
    // })

    

    console.log(flag)

    this.setData({
      postList
    })
  },


  onGoToDetail(event){
      const pid = event.currentTarget.dataset.postId | event.detail.pid
      wx.navigateTo({
        url:'/pages/post-detail/post-detail?pid=' + pid
      })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {
    // console.log("onready")
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    // console.log("onshow")
  },

  /**
   * 生命周期函数--监听页面隐藏
   * 条件触发
   */
  onHide: function () {
    // console.log("onhide")
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    // console.log("onunload")
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    console.log("onreach")
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})