const proPlugins = []
if (process.env.NODE_ENV === 'production') {
  proPlugins.push('transform-remove-console')
}
module.exports = {
  presets: [
    '@vue/cli-plugin-babel/preset'
  ],
  plugins: [
    ...proPlugins,
    // 配置路由懒加载插件
    '@babel/plugin-syntax-dynamic-import'
  ]

}
