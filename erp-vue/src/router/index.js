import { createRouter, createWebHistory } from 'vue-router';
import Login from '../components/vue/Login.vue';
import Home from '../components/vue/Home.vue';
import Dashboard from '../components/vue/Dashboard.vue';
import ProductManagement from '../components/vue/ProductManagement.vue';
import InventoryManagement from '../components/vue/InventoryManagement.vue';
import SalesManagement from '../components/vue/SalesManagement.vue';

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    children: [
      {
        path: 'dashboard',
        name: 'dashboard',
        component: Dashboard
      },
      {
        path: 'product-management',
        name: 'product-management',
        component: ProductManagement
      },
      {
        path: 'inventory-management',
        name: 'inventory-management',
        component: InventoryManagement
      },
      {
        path: 'sales-management',
        name: 'sales-management',
        component: SalesManagement
      },
      {
        path: '',
        redirect: 'home/dashboard'
      },
    ]
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router; 