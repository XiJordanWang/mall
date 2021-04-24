module.exports = {
    devServer: {
        host: "localhost",
        port: 6060, // 端口号
        https: false,
        open: true,
        openPage: '/different/page',
        proxy: {
            '/api': {
                target: 'http://127.0.0.1:8083',
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    }
}