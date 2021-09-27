import { createStore } from "vuex";
// const categoryData = require('./modelData.json')
import categoryData from './modelData.json'

export default createStore({
  state: {
    categoryData: categoryData,
  },
  mutations: {},
  actions: {},
  getters: {
    getCategoryData: function (state) {
      return state.categoryData
    }
  },
  modules: {},
});
