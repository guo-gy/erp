<template>
    <el-container class="home-container">
        <el-aside width="200px">
            <el-menu :default-active="activeMenu" @select="handleSelect">
                <el-menu-item index="dashboard">仪表盘</el-menu-item>
                <el-menu-item index="product-management">产品管理</el-menu-item>
                <el-menu-item index="inventory-management">库存管理</el-menu-item>
                <el-menu-item index="sales-management">销售管理</el-menu-item>
            </el-menu>
        </el-aside>
        <el-container>
            <el-header>
                <div style="display: flex; justify-content: space-between; align-items: center;">
                    <h1 class="home-title">欢迎来到ERP系统</h1>
                    <el-button type="danger" @click="logout">退出登录</el-button>
                
                </div>
            </el-header>
            <el-main>
                <router-view />
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
import { mapState, mapActions } from 'vuex';

export default {
  data() {
    return {
      activeMenu: 'dashboard', // 默认选中的菜单项
    };
  },
  computed: {
    ...mapState(['user'])
  },
  methods: {
    ...mapActions(['updateUser', 'logout']),
    handleSelect(index) {
      this.activeMenu = index; // 更新选中的菜单项
      this.$router.push({ name: index }); // 路由跳转
    },
    logout() {
      // 清除用户登录信息
      localStorage.removeItem('userToken');
      // 跳转到登录页面
      this.$router.push({ name: 'Login' });
    }
  },
};
</script>

<style scoped>
.home-container {
    height: 100vh;
}

.home-title {
    text-align: center;
    color: #333;
    margin: 20px 0;
}
</style>
