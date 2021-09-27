import { createStore } from "vuex";
// const categoryData = require('./modelData.json')
import categoryData from './modelData.json'
import axios from 'axios'

interface query {
  pid: string,
  page: number,
}
const BASE_URL = 'http://j5d205.p.ssafy.io:8080'

export default createStore({
  state: {
    categoryData: categoryData,
  },
  mutations: {},
  actions: {
    requestProductList: function ({ commit }, query: query) {
      const url = `${BASE_URL}/api/productselllist`
      const header = {
        params: query
      }
      return axios.get(url, header)
    }
  },
  getters: {
    getCategoryData: function (state) {
      return state.categoryData
    }
  },
  modules: {},
});
