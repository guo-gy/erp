<template>
    <div>
        <h1>人员管理</h1>
        <el-row :gutter="20" style="margin-bottom: 20px;">
            <el-col :span="4">
                <el-input v-model="name" placeholder="人员名称"></el-input>
            </el-col>
            <el-col :span="4">
                <el-input v-model="model" placeholder="功能名称"></el-input>
            </el-col>
            <el-col :span="4">
                <el-input v-model.number="permission" placeholder="权限等级" type="number"></el-input>
            </el-col>
            <el-col :span="4">
                <el-button @click="updInventory">调整权限</el-button>
            </el-col>
            <el-col :span="8">
                <el-button type="success" @click="registerDialogVisible = true"
                    class="register-button">注册新员工</el-button>
            </el-col>
            <el-dialog title="注册" v-model="registerDialogVisible" @close="resetRegisterForm">
                <el-form class="register-form">
                    <el-form-item label="用户名" class="form-item">
                        <el-input v-model="registeruserName" required placeholder="请输入用户名" />
                    </el-form-item>
                    <el-form-item label="密码" class="form-item">
                        <el-input type="password" v-model="registerPassword" required placeholder="请输入密码" />
                    </el-form-item>
                    <el-form-item label="确认密码" class="form-item">
                        <el-input type="password" v-model="registerrePassword" required placeholder="请输入密码" />
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="register">注册</el-button>
                        <el-button @click="registerDialogVisible = false">取消</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
        </el-row>
        <el-table :data="inventories" style="width: 100%">
            <el-table-column prop="usereName" label="人员名称" />
            <el-table-column prop="modelName" label="功能模块" />
            <el-table-column prop="modelLevel" label="权限等级" />
            <el-table-column label="操作">
                <template v-slot="scope">
                    <el-button @click="delInventory(scope.row.id)" type="danger">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import axios from 'axios';
import { ElMessage } from 'element-plus';
export default {
    name: 'WorkerManagement',
    data() {
        return {
            registerDialogVisible: false,
            companyId: localStorage.getItem('companyId'),
            users: [],
            userName: '',
            modelName: '',
            modelLevel: 0,
            registeruserName: '',
            registerPassword: '',
            registerrePassword: '',
        };

    },
    mounted() {
        this.fetchUsers();
    },
    methods: {
        async fetchUsers() {
            const response = await axios.get(`http://localhost:8080/api/${this.companyId}/user`);
            this.users = response.data.data;
            for (const user of this.users) {
                try {
                    const response = await axios.get(`http://localhost:8080/api/${user.id}/permission`);
                    inventory.productName = response.data.data;
                } catch (error) {
                    inventory.productName = '获取商品名称失败';
                }
            }
        },
        async updInventory() {
            const request = {
                companyId: this.companyId,
                productName: this.name,
                quantity: this.quantity,
            };
            const response = await axios.post('http://localhost:8080/api/inventory/updinventory', request);
            console.log(response);
            if (response.data.success) {
                this.fetchInventories();
                ElMessage.success(response.data.message);
            } else {
                ElMessage.error(response.data.message);
            }
        },
        async delInventory(productId) {
            const request = {
                companyId: this.companyId,
                productId: productId
            };
            const response = await axios.post('http://localhost:8080/api/inventory/delinventory', request)
            if (response.data.success) {
                this.fetchInventories();
                ElMessage.success(response.data.message);
            } else {
                ElMessage.error(response.data.message);
            }
        },
        async register() {
            try {
                const response = await axios.post('http://localhost:8080/api/user/registerworker', {
                    companyName: localStorage.getItem('companyName'),
                    userName: this.registeruserName,
                    password: this.registerPassword,
                    repassword: this.registerrePassword
                });
                if (response.data.success == true) {
                    ElMessage.success(response.data.message);
                    this.registerDialogVisible = false;
                } else {
                    ElMessage.error(response.data.message);
                }
            } catch (error) {
                console.error(error);
                ElMessage.error('系统异常');
            }
        }

    },

}

</script>

<style scoped>
/* 商品管理样式 */
</style>
<!-- End of Selection -->