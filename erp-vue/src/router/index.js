import { createRouter, createWebHistory } from 'vue-router';
import Login from '../components/vue/Login.vue';
import Home from '../components/vue/Home.vue';
import WorkCenter from '../components/vue/WorkCenter.vue';
import InventoryManagement from '../components/vue/InventoryManagement.vue';
import SalesManagement from '../components/vue/SalesManagement.vue';
import PurchaseManagement from '../components/vue/PurchaseManagement.vue';
import CompanyManagement from '../components/vue/CompanyManagement.vue';
import UserManagement from '../components/vue/UserManagement.vue';
import WorkerManagement from '../components/vue/WorkerManagement.vue';
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
        path: 'work-center',
        name: 'work-center',
        component: WorkCenter
      },
      {
        path: 'inventory-management',
        name: 'inventory-management',
        component: InventoryManagement
      },
      {
        path: 'Sales-management',
        name: 'Sales-management',
        component: SalesManagement
      },
      {
        path: 'Purchase-management',
        name: 'Purchase-management',
        component: PurchaseManagement
      },
      {
        path: 'Company-management',
        name: 'Company-management',
        component: CompanyManagement
      },
      {
        path: 'User-management',
        name: 'User-management',
        component: UserManagement
      },
      {
        path: 'User-management',
        name: 'User-management',
        component: UserManagement
      },
      {
        path: 'worker-management',
        name: 'worker-management',
        component: WorkerManagement
      },

    ]
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;