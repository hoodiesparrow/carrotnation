  import { createStore } from "vuex";
  // const categoryData = require('./modelData.json')
  import categoryData from './modelData.json'
  import axios from 'axios'

  interface Query {
    pid: string,
    page: number,
  }
  interface InfoQuery {
    pid: string,
    market: number,
  }
  interface DetailQuery {
    id: string,
    market: string,
  }
  interface byPricequery {
    pid: string,
    cycle : string
  }
  interface nearProductQuery {
    pid: string,
    lon: string,
    lat: string,
    market: number,
  }

  const BASE_URL = 'https://j5d205.p.ssafy.io'

  export default createStore({
    state: {
      categoryData: categoryData,
      sort: 1,
      market: 0,
      openCoordinate: true,
    },
    mutations: {
      CHANGE_SORT: function (state, idx: number) {
        state.sort = idx
      },
      CHANGE_MARKET: function (state, idx: number) {
        state.market = idx
      },
      CHANGE_OPENCOOR: function (state, flag: boolean) {
        state.openCoordinate = flag;
      }
    },
    actions: {
      requestProductInfo: function ({ commit }, query: InfoQuery) {
        const url = `${BASE_URL}/api/product`
        let header = {}
        if (query.market !== 0) {
          header = {
            params: {
              pid: query.pid,
              market: query.market,
            }
          }
        } else { 
          header = {
            params: {
              pid: query.pid
            }
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

      requestProductList: function ({ commit }, query: Query) {
        const url = `${BASE_URL}/api/productselllist`
        const header = { 
          params: query 
        }
        return axios.get(url, header)
      },

      requestNearProduct: function ({ commit }, query: nearProductQuery) {
        const url = `${BASE_URL}/api/nearProduct`
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

      requestProductDetail: function ({ commit }, id: number) {
        const url = `${BASE_URL}/api/productselldetail`
        const header = {
          params: { 
            id: id 
          } 
        }
        return axios.get(url, header);
      },

      requestDatePrice: function ({ commit }, pid: string) {
        const url = `${BASE_URL}/api/dateprice`
        const header = {
          params: { pid: pid }
        }
        return axios.get(url, header)
      },

      requestByPrice: function ({ commit }, query: byPricequery) {
        const url = `${BASE_URL}/api/byprice`
        const header = {
          params: query
        }
        return axios.get(url, header)
      }

  },
  getters: {
    getCategoryData: function (state) {
      return state.categoryData
    },
    getSort: function (state) {
      return state.sort
    },
    getMarket: function (state) {
      return state.market
    },
    getOpenCoor: function (state) {
      return state.openCoordinate
    }
  },
  modules: {},
});
