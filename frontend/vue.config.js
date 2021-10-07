module.exports = {
  pluginOptions: {
    webpackBundleAnalyzer: {
      openAnalyzer: false
    }
  },
  chainWebpack: (config) => {
    config.plugins.delete('prefetch')
  }
}