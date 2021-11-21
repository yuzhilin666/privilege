/**
 * 用户服务模块路由管理
 */
var router = require('koa-router')();
var User = require('./../models/user');
var utils = require('./../utils/utils');

/* GET users listing. */
router.get('/', function(ctx) {
  ctx.body = 'this is users routes';
});

router.post("/login",async function (ctx) {
  var param = {
    userName:ctx.request.body.userName,
    userPwd: ctx.request.body.userPwd
  }
  let doc = await User.findOne(param);
  if(doc){
    ctx.cookies.set('userId', doc.userId, {
      path: '/',
      maxAge: 1000 * 60 * 60
    })
    ctx.cookies.set('userName', doc.userName, {
      path: '/',
      maxAge: 1000 * 60 * 60
    })
    ctx.body = utils.handleSuc({
      userName: doc.userName
    })
  }else{
    ctx.body = utils.handleFail('帐号密码错误');
  }
});


//登出接口
router.post("/logout", function (ctx) {
  ctx.cookies.set('userId', '', {
    path: '/',
    maxAge: -1
  })
  ctx.cookies.set('userName', '', {
    path: '/',
    maxAge: -1
  })
  ctx.body = utils.handleSuc();
});

router.get("/checkLogin", function (ctx) {
  let cookies = ctx.cookies;
  if(cookies.get('userId')){
    ctx.body = utils.handleSuc(cookies.get('userName'));
  }else{
    ctx.body = utils.handleFail('未登录',10008)
  }
});

// 购物车商品列表
router.get("/getCartCount",async function (ctx) {
  let userId = ctx.cookies.get('userId');
  if(userId){
    let doc = await User.findOne({ userId: userId });
    if(doc){
      let cartList = doc.cartList;
      let cartCount = 0;
      cartList.map(function (item) {
        cartCount += parseInt(item.productNum);
      })
      ctx.body = utils.handleSuc(cartCount);
    }else{
      ctx.body = utils.handleFail('当前用户不存在');
    }
  }else{
    ctx.body = utils.handleFail('当前用户不存在');
  }
});

//查询当前用户的购物车数据
router.get("/cartList",async function (ctx) {
  let userId = ctx.cookies.get('userId');
  if(userId){
    let doc = await User.findOne({ userId: userId });
    if(doc){
      ctx.body = utils.handleSuc(doc.cartList);
    }else{
      ctx.body = utils.handleFail('当前用户不存在');
    }
  }else{
    ctx.body = utils.handleFail('当前用户不存在');
  }
});

//购物车删除
router.post("/cartDel",async function (ctx) {
  let userId = ctx.cookies.get('userId'),
      productId = ctx.request.body.productId;
  let res =await User.update({
    userId: userId
    }, {
    $pull: {
      'cartList': {
        'productId': productId
      }
    }
  });
  // {'ok':1,'nModified':1}
  if(res.nModified>0){
    ctx.body = utils.handleSuc();
  }else{
    ctx.body = utils.handleFail('购物车删除失败');
  }
});

//修改商品数量
router.post("/cartEdit",async function (ctx) {
  let userId = ctx.cookies.get('userId'),
    productId = ctx.request.body.productId,
    productNum = ctx.request.body.productNum,
    checked = ctx.request.body.checked;
  let res = await User.update({ "userId": userId, "cartList.productId": productId }, {
    "cartList.$.productNum": productNum,
    "cartList.$.checked": checked,
  });
  if(res.nModified>0){
    ctx.body = utils.handleSuc();
  }else{
    ctx.body = utils.handleFail('购物车更新失败');
  }
});
router.post("/editCheckAll",async function (ctx) {
  let userId = ctx.cookies.get('userId'),
    checkAll = ctx.request.body.checkAll?'1':'0';
  let user = await User.findOne({ userId: userId });
  if(user){
    user.cartList.forEach((item) => {
      item.checked = checkAll;
    })
    let res = await user.save();
    if(res){
      ctx.body = utils.handleSuc();
    }else {
      ctx.body = utils.handleFail('设置失败');
    }
  }else{
    ctx.body = utils.handleFail('用户不存在');
  }
});
//查询用户地址接口
router.get("/addressList",async function (ctx) {
  let userId = ctx.cookies.get('userId');
  let doc = await User.findOne({ userId: userId });
  if(doc){
    ctx.body = utils.handleSuc(doc.addressList);
  }else{
    ctx.body = utils.handleFail('用户不存在');
  }
});
//设置默认地址接口
router.post("/setDefault",async function (ctx) {
  let userId = ctx.cookies.get('userId'),
      addressId = ctx.request.body.addressId;
  if(!addressId){
    ctx.body = utils.handleFail('地址不能为空', 10003);
  }else{
    let doc = await User.findOne({ userId: userId });
    if (doc) {
      var addressList = doc.addressList;
      addressList.forEach((item) => {
        if (item.addressId == addressId) {
          item.isDefault = true;
        } else {
          item.isDefault = false;
        }
      });
      await doc.save();
      ctx.body = utils.handleSuc();
    } else {
      ctx.body = utils.handleFail('用户不存在');
    }
  }
});

//删除地址接口
router.post("/delAddress",async function (ctx) {
  let userId = ctx.cookies.get('userId'),
    addressId = ctx.request.body.addressId;
  let res =await User.update({
    userId: userId
    }, {
    $pull: {
      'addressList': {
        'addressId': addressId
      }
    }
  });
  if(res.nModified>0){
    ctx.body = utils.handleSuc()
  }else{
    ctx.body = utils.handleFail('地址删除失败');
  }
});

router.post("/payMent",async function (ctx) {
  let userId = ctx.cookies.get('userId'),
    addressId = ctx.request.body.addressId,
    orderTotal = ctx.request.body.orderTotal;
  let doc = await User.findOne({ userId: userId });
  if(doc){
    var address = '', goodsList = [];
    //获取当前用户的地址信息
    doc.addressList.forEach((item) => {
      if (addressId == item.addressId) {
        address = item;
      }
    })
    //获取用户购物车的购买商品
    doc.cartList.filter((item) => {
      if (item.checked == '1') {
        goodsList.push(item);
      }
    });

    var platform = '622';
    var r1 = Math.floor(Math.random() * 10);
    var r2 = Math.floor(Math.random() * 10);

    var sysDate = new Date().Format('yyyyMMddhhmmss');
    var createDate = new Date().Format('yyyy-MM-dd hh:mm:ss');
    var orderId = platform + r1 + sysDate + r2;
    var order = {
      orderId: orderId,
      orderTotal: orderTotal,
      addressInfo: address,
      goodsList: goodsList,
      orderStatus: '1',
      createDate: createDate
    };

    doc.orderList.push(order);
    await doc.save();
    ctx.body = utils.handleSuc({
      orderId: order.orderId,
      orderTotal: order.orderTotal
    });
  } else {
    ctx.body = utils.handleFail('用户不存在');
  }
});
//根据订单Id查询订单信息
router.get("/orderDetail",async function (ctx) {
  let userId = ctx.cookies.get('userId'),
      orderId = ctx.request.query.orderId;
  let userInfo = await User.findOne({ userId: userId });
  if (userInfo){
    var orderList = userInfo.orderList;
    if (orderList.length > 0) {
      var orderTotal = 0;
      orderList.forEach((item) => {
        if (item.orderId == orderId) {
          orderTotal = item.orderTotal;
        }
      });
      if (orderTotal > 0) {
        ctx.body = utils.handleSuc({
          orderId: orderId,
          orderTotal: orderTotal
        })
      } else {
        ctx.body = utils.handleFail('无此订单', 120002);
      }
    } else {
      ctx.body = utils.handleFail('当前用户未创建订单', 120001);
    }
  }else{
    ctx.body = utils.handleFail('用户不存在');
  }
});
module.exports = router;
