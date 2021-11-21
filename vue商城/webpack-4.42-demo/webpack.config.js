/**
 * webpack4.42版本，内容讲解
 */
const HtmlWebpackPlugin = require('html-webpack-plugin');
const VueLoaderPlugin = require('vue-loader/lib/plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');
module.exports = {
    mode:"development",
    entry:'./src/main.js',
    output:{
        filename:'bundle.js',
        path:__dirname+'/dist',
        publicPath:'/',
        library:'demo',
        libraryTarget:'umd'
    },
    module:{
        rules:[
            {
                test:/\.css$/,
                use:['style-loader','css-loader','postcss-loader'],
                exclude:/node_modules/,
                include:/src/
            },{
                test:/\.vue$/,
                loader:'vue-loader',
                exclude:/node_modules/,
                include:/src/
            },{
                test:/\.(js|.jsx)$/,
                loader:'babel-loader',
                exclude:/node_modules/,
                include:/src/
            },{
                test:/\.(png|jpg|gif|svg)$/,
                use:{
                    loader:'url-loader',
                    options:{
                        limit:100
                    }
                },
                exclude:/node_modules/
            },{
                test:/\.(scss|sass)$/,
                use:['style-loader','css-loader','sass-loader'],
                exclude:/node_modules/,
                include:/src/
            }
        ]
    },
    plugins:[
        new CleanWebpackPlugin(),
        new VueLoaderPlugin(),
        new CopyWebpackPlugin([
            {
                from : __dirname + '/public',
                to : __dirname + '/dist',
                ignore: ['.*']
            }
        ]),
        new HtmlWebpackPlugin({
            template:'public/index.html'
        }),
    ],
    devServer:{
        contentBase:__dirname+"/dist",//服务根目录
        host:'localhost',//服务主机
        port:8080,//服务端口
        hot:true,//代码热更新
        open:true,//默认打开浏览器
        openPage:'index.html',//默认打开的页面
        proxy:{
            "/api":{
                target:"http://lemall.futurefe.com",
                changeOrigin:true
            }
        }
    }
}