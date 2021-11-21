import './../css/index.css'
//这是首页
/*!
* 这是首页 注释
* **/
require(['./common.js','./a.js','./b.js'], (common,a,b) => {
    common.initIndex();
    $(function () {
        console.log("this is jQuery");
    })
})
