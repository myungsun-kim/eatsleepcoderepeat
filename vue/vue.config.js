module.exports = {
  lintOnSave: false,
  devServer: {
    // disableHostCheck: true,
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        logLevel: 'debug',
        // pathRewrite: { 'http://localhost:8080/api': '' },
      },
    },
  },
};
