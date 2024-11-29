<template>
    <el-container class="home-container">
        <el-aside width="200px">
            <el-menu :default-active="activeMenu" @select="handleSelect">
                <el-menu-item style="font-weight: bold; color: #409EFF;">欢迎来到ERP系统</el-menu-item>
                <el-menu-item index="work-center">任务中心</el-menu-item>
                <el-menu-item index="inventory-management">库存管理</el-menu-item>
                <el-menu-item index="sales-management">销售管理</el-menu-item>
                <el-menu-item index="purchase-management">采购管理</el-menu-item>
                <el-menu-item index="worker-management">人员管理</el-menu-item>
                <el-menu-item index="company-management">公司管理</el-menu-item>
                <el-menu-item index="user-management">个人管理</el-menu-item>
            </el-menu>
        </el-aside>
        <el-divider direction="vertical"></el-divider>
        <el-container>
            <el-header>
                <div style="display: flex; justify-content: space-between; align-items: center;">
                    <h1 class="home-title">员工：{{ this.userName }} (隶属于：{{ this.companyName }})</h1>
                    <el-button type="danger" @click="logout">退出登录</el-button>
                </div>
            </el-header>
            <el-divider></el-divider>
            <el-main>
                <router-view />
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
import axios from 'axios';
import { ElMessage } from 'element-plus';

export default {
    data() {
        return {
            userId: null,
            companyId: null,
            activeMenu: 'work-center',
            userName: '获取信息失败',
            companyName: '获取信息失败',
        };
    },
    created() {
        this.userId = localStorage.getItem('userId');
        this.companyId = localStorage.getItem('companyId');
        this.getUserName();
        this.getCompanyName();
        this.$router.push(`/home/work-center`);
    },
    methods: {
        async getUserName() {
            const response = await axios.get(`http://localhost:8080/api/user/${this.userId}/name`);
            if (response.data.success) {
                this.userName = response.data.data;
            } else {
                this.logout();
            }
        },
        async getCompanyName() {
            const response = await axios.get(`http://localhost:8080/api/company/${this.companyId}/name`);
            if (response.data.success) {
                this.companyName = response.data.data;
                localStorage.setItem('companyName', response.data.data);
            } else {
                ElMessage.error(response.data.message);
            }
        },
        handleSelect(key) {
            this.$router.push(`/home/${key}`);
        },
        async logout() {
            localStorage.removeItem('companyId');
            localStorage.removeItem('companyName');
            localStorage.removeItem('userId');
            this.$router.push('/');
        },
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
