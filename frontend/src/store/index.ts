import { createStore } from "vuex";
// const categoryData = require('./modelData.json')
import categoryData from './modelData.json'
import axios from 'axios'

interface query {
  pid: string,
  page: number,
}
interface detailquery {
  id: string,
  market: string,
}

const BASE_URL = 'https://j5d205.p.ssafy.io'

export default createStore({
  state: {
    categoryData: categoryData,
  },
  mutations: {},
  actions: {
    requestProductInfo: function ({ commit }, pid: string) {
      const url = `${BASE_URL}/api/product`
      const header = { 
        params: { 
          pid: pid 
        } 
      }
      return axios.get(url, header)
    },
    requestPriceInfo: function ({ commit }, pid: string) {
      const url = `${BASE_URL}/api/dateprice`
      const header = { 
        params: { 
          pid: pid 
        } 
      }
      return axios.get(url, header)
    },

    requestProductList: function ({ commit }, query: query) {
      const url = `${BASE_URL}/api/productselllist`
      const header = { 
        params: query 
      }
      return axios.get(url, header)
    },

    requestProductSalesInfo: function ({ commit }, pid: string) {
      const url = `${BASE_URL}/api/dateprice`
      const header = {
        params: { pid: pid }
      }
      return axios.get(url, header)
    },

    requestProductDetail: function ({ commit }, query: detailquery) {
      const url = `${BASE_URL}/api/productselldetail`
      const header = {
        params: query
      }
      return axios.get(url, header);
    }
  },
  getters: {
    getCategoryData: function (state) {
      return state.categoryData
    }
  },
  modules: {},
});
