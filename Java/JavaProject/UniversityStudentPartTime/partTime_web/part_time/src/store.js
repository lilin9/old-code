import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    logined: false,
    loginId: ''
  },
  mutations: {
    changeLogined (state) {
      state.logined = true
    },
    saveLoginId (state, id) {
      state.loginId = id
    }
  },
  actions: {

  }
})
