'use strict'

const path = require('path')
module.exports = {
    dev: {
        assertsSubDirectory: 'static',
        assertsPublicPath: '/',
        proxyTable: {
            "/baidu_music_api": {
                target: "http://tingapi.ting.baidu.com",
                changeOrigin: true,
                pathRewrite: {
                    '^/baidu_music_api': ''
                }
            }
        },
        host: 'localhost',
        port: 8080,
        autoOpenBrowser: false,
        errorOverlay: true,
        notifyOnErrors: true,
        poll: false,
    }
}