import Vue from 'vue';
import App from './App';
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import axios from 'axios'

import router from './router/index.js';

Vue.use(Antd);

Vue.prototype.router = router;

Vue.config.productionTip = false;
Vue.prototype.axios = axios;

axios.defaults.baseURL = '/api'

new Vue({
  el: "#app",
  router,
  render: h => h(App),
});