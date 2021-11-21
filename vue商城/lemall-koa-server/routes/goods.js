const router = require('koa-router')();
const Goods = require('./../models/goods');
const User = require('./../models/user');
const utils = require('./../utils/utils')

//查询商品列表数据
router.get("/list",async function (ctx) {
  let query = ctx.request.query;
  let page = parseInt(query.page);
  let pageSize = parseInt(query.pageSize);
  let priceLevel = query.priceLevel;
  let sort = query.sort;
  let skip = (page-1)*pageSize;
  var priceGt = '',priceLte = '';
  let params = {};
  if(priceLevel!='all'){
    switch (priceLevel){
      case '0':priceGt = 0;priceLte=100;break;
      case '1':priceGt = 100;priceLte=500;break;
      case '2':priceGt = 500;priceLte=1000;break;
      case '3':priceGt = 1000;priceLte=5000;break;
    }
    params = {
      salePrice:{
          $gt:priceGt,
          $lte:priceLte
      }
    }
  }
  let goodsModel = Goods.find(params).skip(skip).limit(pageSize);
  goodsModel.sort({'salePrice':sort});
  let res = await goodsModel.exec();
  ctx.body = utils.handleSuc({
    count: res.length,
    list: res
  });
});

//加入到购物车
router.post("/addCart",async function (ctx) {
  const userId = ctx.cookies.get('userId'),productId = ctx.request.body.productId;
  if (!userId){
    ctx.body = utils.handleFail('请先登录');
    return;
  }
  const userDoc = await User.findOne({ userId: userId });
  if (userDoc){
    let goodsItem = '';
    userDoc.cartList.forEach(function (item) {
      if (item.productId == productId) {
        goodsItem = item;
        item.productNum++;
      }
    });
    if (goodsItem) {
     let res = await userDoc.save();
     if(res){
       ctx.body = utils.handleSuc();
     }else{
       ctx.body = utils.handleFail('添加购物车失败');
     }
    } else {
      let doc = await Goods.findOne({ productId: productId });
      if (doc) {
        userDoc.cartList.push({
          "productId": doc.productId,
          "productName": doc.productName,
          "salePrice": doc.salePrice,
          "productImage": doc.productImage,
          "productNum": 1,
          "checked": 1
        });
        let res = await userDoc.save();
        if(res){
          ctx.body = utils.handleSuc();
        }else{
          ctx.body = utils.handleFail('添加购物车失败');
        }
      }
    }
  }
});

module.exports = router;
