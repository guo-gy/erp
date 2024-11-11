import { createRouter, createWebHistory } from 'vue-router';
import Login from '../components/Login.vue';
import ProductList from '../components/ProductList.vue';

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/products',
    name: 'ProductList',
    component: ProductList
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router; 