/**
 * Koa2重写乐视商城后台
 * 河畔一角
 * 2020.3.5
 */
const Koa = require('koa');
const Router = require('koa-router');
const logger = require('koa-logger');
const json = require('koa-json');
const static = require('koa-static');
const views = require('koa-views');
const bodyparser = require('koa-bodyparser');
const db = require('./config/db')
const users = require('./routes/users')
const goods = require('./routes/goods')
const utils = require('./utils/utils')

// 创建Koa实例
const app = new Koa();

// 创建路由实例，并添加路由前缀
const router = new Router({
  prefix:'/api'
})


// 应用日志中间件
app.use(logger());

// 应用日志中间件
app.use(json());

// 应用静态服务中间件
app.use(static(__dirname+'/public'))
// 设置页面模板引擎中间件
app.use(views(__dirname + '/views'),{
  extension:'ejs'
})

// 应用于post请求
app.use(bodyparser())

// demo:test接口验证
router.get('/test',(ctx,next)=>{
  ctx.body = {
    name:'Jack',
    age:30
  };
})

router.get('/html',async (ctx)=>{
  await ctx.render('index.ejs',{
    name:'Jack'
  });
})

// 初始化数据库配置
db.init();

// 前端登录拦截
app.use(async(ctx,next)=>{
  if(ctx.cookies.get('userId')){
    await next();
  }else {
    if (ctx.request.url == '/api/users/checkLogin' || ctx.request.url == '/api/users/login' || ctx.request.url == '/api/users/logout' || ctx.request.url.indexOf('/goods/list')>-1){
      await next();
    }else{
      ctx.body = utils.handleFail('当前未登录',10008);
    }
  }
});

// 应用中间件
router.use('/users', users.routes(), users.allowedMethods());
router.use('/goods', goods.routes(), goods.allowedMethods());
app.use(router.routes());

// 服务端口监听
app.listen(3000);

// 拦截
// app.use((ctx)=>{
//   ctx.body = 'Hello, Koa2!';
// });


app.on('error',(err,ctx)=>{
  console.error('server error',err);
});

console.log('app started on port 3000...');
