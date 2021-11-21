const ExtractTextPlugin = require('extract-text-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    module:{
        rules:[{
            test: /\.css$/,
            // use: ['style-loader', 'css-loader']
            use: ExtractTextPlugin.extract({
                fallback: 'style-loader',
                use: {
                    loader: 'css-loader',
                    options: {
                        modules: true
                    }
                }
            })
        }]
    },
    plugins: [
        new ExtractTextPlugin('07-webpack-all-[chunkhash].css'),
        new HtmlWebpackPlugin({
            filename: '../07-webpack-all-build.html',
            template: '07-webpack-all.html'
        })
    ]  
}
