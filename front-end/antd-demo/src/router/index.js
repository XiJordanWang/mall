import Vue from 'vue';
import Router from 'vue-router';
import Main from '../views/Main.vue'
import content from '../views/content.vue'

Vue.use(Router);

const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

const routers = [
    { path: '/main', components: Main },
    { path: '/toMain', redirect: '/views/Main' },
    { path: '/content', components: content },
]

export default new Router({
    routers
})