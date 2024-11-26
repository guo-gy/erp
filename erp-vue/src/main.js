// src/main.js
import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store'; // 确保引入了 Vuex store
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css'; // 引入 Element Plus 样式

const app = createApp(App);
app.use(router);
app.use(store); // 确保使用了 Vuex store
app.use(ElementPlus);
app.mount('#app');
