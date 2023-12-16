//该文件用于创建Vuex中最为核心的store
//引入vue
import Vue from 'vue'
//引入vuex
import Vuex from "vuex"

//应用插件
Vue.use(Vuex)
//准备actions，用于响应组件中的动作
const actions = {
/*     add(context, value) {
        context.commit("ADD", value)
    },

    reduce(context, value) {
        context.commit("REDUCE", value)
    }, */

    odd(context, value) {
        if (context.state.sum % 2) {
            context.commit("ADD", value)
        }
    },

    wait(context, value) {
        setTimeout(() => {
            context.commit("ADD", value)
        }, 500);
    }
}
//准备motations，用于操作数据（state）
const mutations = {
    ADD(state, value) {
        state.sum += value
    },
    REDUCE(state, value) {
        state.sum -= value
    }
}
//准备getters，用于将state中数据进行加工
const getters = {
    bigSum(state) {
        return state.sum * 10;
    }
}

//创建并暴露store接口
export default new Vuex.Store({
    actions,mutations,state,getters
});