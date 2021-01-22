import Vue from 'vue'
import VueLogger from 'vuejs-logger'
import {BootstrapVue, IconsPlugin, NavbarPlugin, TablePlugin, PaginationPlugin} from 'bootstrap-vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'

import './assets/app.scss'

Vue.config.productionTip = false

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)
Vue.use(NavbarPlugin)
Vue.use(TablePlugin)
Vue.use(PaginationPlugin)

Vue.use(VueLogger, {
  isEnabled: true,
  logLevel : 'debug',
  stringifyArguments : false,
  showLogLevel : true,
  showMethodName : false,
  separator: '|',
  showConsoleColors: true
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
