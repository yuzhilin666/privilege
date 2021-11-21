module.exports = {
  // 前端Server和接口代理
  devServer:{
    host:'localhost',
    port:9090,
    proxy:{
      '/api/*':{
        target:'http://lemall.futurefe.com/'
      }
    }
  },
  // 项目的基本路径
  // publicPath:'/',
  // 项目的生成目录
  // outputDir:'',
  // 修改index.html的路径
  // indexPath:'demo.html',
  // 文件名是否需要hash
  // filenameHashing:true,
  // eslint语法检查，保存时使用
  // lintOnSave:true,
  // 是否启用sourceMap
  // productionSourceMap:true
}