// vue.config.js
module.exports = {
    runtimeCompiler: true,
    // https://cli.vuejs.org/config/#devserver-proxy
    devServer: {
        port: 8082,
        proxy: {
            '/api': {
                target: 'http://localhost:8081',
                ws: true,
                changeOrigin: true
            }
        }
    }
}