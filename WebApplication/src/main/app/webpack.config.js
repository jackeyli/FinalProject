const path = require('path');
const webpack = require('webpack');
module.exports = {
    entry: './app.js',
    output: {
        path: path.join(__dirname, './public'),
        filename: 'bundle.js'
    },
    resolve: {
        alias: {
            '../../theme.config$': path.join(__dirname, 'my-semantic-theme/theme.config')
        }
    },
    devServer:{
      port:3000,
      proxy: {
          '/request': 'http://localhost:8080/WebApplication'
      }
    },
    module: {
        rules:[{
            loader:'babel-loader',
            test:/\.jsx?/
        },{
            test:/\.s?css/,
            use:[
                "style-loader",
                "css-loader",
                "sass-loader"
            ]
        },
        {
            test:/\.jpe?g$|\.gif$|\.ico$|\.png$|\.svg$/i,
            use:[
                "file-loader"
            ]
        }
        ]
    }
}