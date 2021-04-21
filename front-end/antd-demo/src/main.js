import Vue from 'vue';
import Button from 'ant-design-vue/lib/button';
import 'ant-design-vue/dist/antd.css';
import App from './App';
import axios from 'axios'


Vue.component(Button.name, Button);
Vue.config.productionTip = false;
Vue.prototype.axios = axios

axios.defaults.baseURL = '/api'

axios.interceptors.request.use(
  config => {
    return config
  },
  function (error) {
    return Promise.reject(error)
  }
)

new Vue({
  render: h => h(App),
}).$mount('#app');