const ExtractTextPlugin = require('extract-text-webpack-plugin');

module.exports = {
    module:{
        rules:[{
            test: /\.css$/,
            /* use: ['style-loader', {
                loader: 'css-loader',
                options: {
                    modules: true
                }
            }] */
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
    plugins:[
        new ExtractTextPlugin('07-webpack-main.css')
    ]
}
