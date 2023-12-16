export default ({
    install(Vue) {
    //全局过滤器 
    Vue.filter("mySlice", function(value) {
        return value.slice(0, 4);
    });

    //混合的全局配置
    Vue.mixin({
        data() {
            return {
                x: 100,
                y: 200
            }
        }
    })

    //在Vue的原型上添加一个方法（vm和vc都能使用）
    Vue.prototype.hello = () => {alert("Hello")}
    }
});