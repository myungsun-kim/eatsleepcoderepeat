module.exports = {
  lintOnSave: false,
  devServer: {
    // disableHostCheck: true,
    proxy: {
      '/api': {
        // target: 'http://localhost:8080',
        target: 'http://j5d105.p.ssafy.io',
        changeOrigin: true,
        logLevel: 'debug',
        // pathRewrite: { 'http://localhost:8080/api': '' },
      },
    },
  },
};
