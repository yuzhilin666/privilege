var title = '欢迎学习webpack4.42';

console.log(title);

require('./css/index.css');

import axios from 'axios'

axios.get('/api/goods/list').then((res)=>{
    console.log('list:'+res);
})