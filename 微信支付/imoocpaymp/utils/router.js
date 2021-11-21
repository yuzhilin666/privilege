/**
 * @author 河畔一角
 * @description 通用的路由跳转文件
 */
const routerPath = {
  'index':'/pages/index/index',
  'pay':'/pages/pay/index',
  'activity':'/pages/activity/index'
}
module.exports = {
  // 页面跳转
  push(path,option={}){
    if(typeof path == 'string'){
      option.path = path;
    }else{
      option = path;
    }
    let url = routerPath[option.path];
    let { query={}, openType,duration } = option;
    let params = this.parse(query);
    if (params){
      url += '?' + params;
    }
    duration?setTimeout(()=>{
      this.to(openType,url);
    }, duration) : this.to(openType, url);
  },
  to(openType,url){
    let obj = { url };
    if (openType == 'redirect'){
      wx.redirectTo(obj)
    } else if (openType == 'reLaunch') {
      wx.reLaunch(obj)
    } else if (openType == 'back') {
      wx.navigateBack({
        delta:1
      })
    } else{
      wx.navigateTo(obj);
    }
  },
  parse(data){
    let arr = [];
    for(let key in data){
      arr.push(key + '=' + data[key]);
    }
    return arr.join('&');
  }
}