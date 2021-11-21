//加载wx-server-sdk
const cloud = require('wx-server-sdk')

// 初始化 cloud
cloud.init({
  env: 'test-1-18466a'
})
// 导出入口
exports.main = async function(event,context){
  // 获取上下文
  const wxContext = cloud.getWXContext();
  // 连接数据库
  const db = cloud.database();
  // 判断用户是否存在
  const result = await db.collection('users').where({
    openid: wxContext.OPENID
  }).limit(1).get();
  //{ "data": [], "errMsg": "collection.get:ok" }
  if (result.data.length == 0){
    let params = {
      ...event.user,
      openid: wxContext.OPENID
    }
    //用户不存在，需要插入新用户
    const userData = await db.collection('users').add({
      data: params
    });
    const result = await db.collection('users').where({
      openid: wxContext.OPENID
    }).limit(1).get();
    return {
      userId: result.data[0]._id
    }
  }else{
    return {
      userId: result.data[0]._id
    }
  }
}