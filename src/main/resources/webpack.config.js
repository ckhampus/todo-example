var path = require('path');
var webpack = require('webpack');

module.exports = {
    entry: {
        client: './assets/components/client.js',
        server: './assets/components/server.js'
    },
    output: {
        path: path.join(__dirname, 'static'),
        filename: 'bundle.[name].js'
    },
    module: {
        noParse: ['react'],
        loaders: [
            {test: /\.js$/, exclude: /node_modules/, loader: 'babel-loader'}
        ]
    },
    plugins: [
        new webpack.DefinePlugin({
            'process.env.NODE_ENV': '"production"'
        }),
        new webpack.optimize.CommonsChunkPlugin({
            name: 'common'
        })
    ]
};