/**
 * @author 河畔一角
 * @description 公共请求方法
 */
let store = require('../utils/store.js')
let system = store.getSystemInfo();
const clientInfo = {
  'clientType':'mp',
  'appnm':'imoocpay',
  'model': system.model,
  'os': system.system,
  'screen': system.screenWidth + '*' + system.screenHeight,
  'version': App.version,
  'channel':'miniprogram'
}
const errMsg = '服务异常，请稍后重试';
module.exports = {
  fetch:(url,data={},option={})=>{
    let { loading = true, toast = true, isMock = false, method = 'get' } = option;
    return new Promise((resolve,reject)=>{
      if(loading){
        wx.showLoading({
          title: '加载中...',
          mask:true
        })
      }
      let env = isMock ? App.config.mockApi : App.config.baseApi
      wx.request({
        url: env + url,
        data,
        method,
        header: {
          'clientInfo': JSON.stringify(clientInfo)
        },
        success: function (result) {
          let res = result.data;// {code:0,data:'',message:''}
          if(res.code == 0){
            if (loading){
              wx.hideLoading();
            }
            resolve(res.data);
          }else{
            if(toast){
              wx.showToast({
                mask: true,
                title: res.messsage,
                icon: 'none'
              })
            }else{
              wx.hideLoading();
            }
            reject(res);
          }
        },
        fail: function (e = { code: -1, msg: errMsg, errMsg}) {
          let msg = e.errMsg;
          if(msg == 'request:fail timeout'){
            msg = '服务请求超时，请稍后处理';
          }
          wx.showToast({
            title: msg,
            icon:'none'
          })
          reject(e);
        }
      })
    })
  }
}