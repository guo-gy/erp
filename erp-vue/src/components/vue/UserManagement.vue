<template>
    <div v-if="this.permission < 0">
        <h1>无权限访问</h1>
        <p>您没有权限访问此页面。</p>
    </div>
    <div v-if="this.permission === 0">
        <el-card>
            <div slot="header" class="clearfix">
                <span>用户信息管理</span>
            </div>
            <p>您仅拥有读权限，无权修改信息</p>
            <el-form :model="user" @submit.prevent="saveUserInfo">
                <el-form-item label="用户名">
                    <el-input v-model="user.name" disabled></el-input>
                </el-form-item>
                <el-form-item label="隶属公司">
                    <el-input v-model="user.companyName" disabled></el-input>
                </el-form-item>
                <el-form-item label="个人简介">
                    <el-input typr="textarea" v-model="user.description" disabled></el-input>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
    <div v-if="this.permission > 0" class="user-management">
        <el-card>
            <div slot="header" class="clearfix">
                <span>用户信息管理</span>
            </div>
            <el-form :model="user" @submit.prevent="saveUserInfo">
                <el-form-item label="用户名">
                    <el-input v-model="user.name"></el-input>
                </el-form-item>
                <el-form-item label="隶属公司">
                    <el-input v-model="user.companyName" disabled></el-input>
                </el-form-item>
                <el-form-item label="个人简介">
                    <el-input typr="textarea" v-model="user.description"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="saveUserInfo">保存</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script>
import axios from 'axios';
import { ElMessage } from 'element-plus';
export default {
    data() {
        return {
            permission: -1,
            user: {

                userId: localStorage.getItem('userId'),
                companyId: localStorage.getItem('companyId'),
                name: '',
                companyName: '',
                description: ''
            }
        };
    },
    created() {
        this.userId = localStorage.getItem('userId');
        this.companyId = localStorage.getItem('companyId');
        this.fetchUserInfo();
        this.getPermission();
    },
    methods: {
        async getPermission() {
            const moduleId = 7;
            const userId = localStorage.getItem('userId');
            const response = await axios.get(`http://localhost:8080/api/permission/${userId}/${moduleId}`);
            this.permission = response.data.data;
        },
        async fetchUserInfo() {
            const response = await axios.get(`http://localhost:8080/api/user/${this.userId}/name`);
            if (response.data.success == true) {
                console.log(this.userId);
                console.log(response.data.data);
                this.user.name = response.data.data;
            }
            else {
                this.user.name = '获取信息失败';
            }
            const response2 = await axios.get(`http://localhost:8080/api/company/${this.companyId}/name`);
            if (response2.data.success == true) {
                this.user.companyName = response2.data.data;
            }
            else {
                this.user.companyName = '获取信息失败';
            }
            const response3 = await axios.get(`http://localhost:8080/api/user/${this.userId}/description`);
            if (response3.data.success == true) {
                this.user.description = response3.data.data;
            }
            else {
                this.user.description = '获取信息失败';
            }
        },
        async saveUserInfo() {
            const request = {
                userId: this.user.userId,
                name: this.user.name,
                description: this.user.description
            }
            console.log(request);
            const response = await axios.post('http://localhost:8080/api/user/upduser', request);
            if (response.data.success == true) {
                ElMessage.success(response.data.message);
                this.fetchUserInfo();
            }
            else {
                ElMessage.error(response.data.message);
            }
        }
    }
};
</script>
