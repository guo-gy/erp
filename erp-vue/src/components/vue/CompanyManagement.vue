<template>
    <div class="company-management">
        <el-card>
            <div slot="header" class="clearfix">
                <span>公司信息</span>
            </div>
            <div>
                <el-form :model="company">
                    <el-form-item label="公司名">
                        <el-input v-model="company.name"></el-input>
                    </el-form-item>
                    <el-form-item label="资金量">
                        <el-input v-model="company.money" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="公司简介">
                        <el-input type="textarea" v-model="company.description"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="saveCompanyInfo">保存</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-card>
    </div>
</template>

<script>
import axios from 'axios';
import { ElMessage } from 'element-plus';

export default {
    data() {
        return {
            company: {
                id: localStorage.getItem('companyId'),
                name: '',
                money: '',
                description: ''
            }
        };
    },
    created() {
        this.id = localStorage.getItem('companyId');
        this.fetchCompanyInfo();
    },
    methods: {
        async fetchCompanyInfo() {
            const response = await axios.get(`http://localhost:8080/api/company/${this.id}/name`);
            if (response.data.success == true) {
                this.company.name = response.data.data;
            }
            else {
                this.company.name = '获取信息失败';
            }
            const response2 = await axios.get(`http://localhost:8080/api/company/${this.id}/money`);
            if (response2.data.success == true) {
                this.company.money = response2.data.data;
            }
            else {
                this.company.money = '获取信息失败';
            }
            const response3 = await axios.get(`http://localhost:8080/api/company/${this.id}/description`);
            if (response3.data.success == true) {
                this.company.description = response3.data.data;
            }
            else {
                this.company.description = '获取信息失败';
            }
        },
        async saveCompanyInfo() {
            const request = {
                id: this.company.id,
                name: this.company.name,
                description: this.company.description
            }
            const response = await axios.post('http://localhost:8080/api/company/updcompany', request)
            if (response.data.success == true) {
                ElMessage.success(response.data.message);
                this.fetchCompanyInfo();
            }
            else {
                ElMessage.error(response.data.message);
            }
        }
    }
};
</script>
