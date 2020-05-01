module.exports = {
    publicPath: '/tools/',
    devServer: {
        proxy: {
            '/api': {
                target: 'http://175.24.31.49/api', //对应自己的接口
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    }
}
