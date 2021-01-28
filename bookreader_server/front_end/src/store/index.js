import Vue from 'vue'
import Vuex from 'vuex'
import book from './modules/book'
import getters from './getters'
import audio from './modules/audio'
Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
     book,
    audio,
  },
  state: {
  },
  mutations: {
  },
  actions: {
  },
  getters
})
