<template>
    <div v-if="this.permission < 0">
        <h1>无权限访问</h1>
        <p>您没有权限访问此页面。</p>
    </div>
    <div v-if="this.permission === 0">
        <h1>人员管理</h1>
        <p>您仅拥有读权限，无权调整权限和注册账号</p>
        <p>（目前开放三个权限等级，无权限<0，只读权限=0，读写权限>0</0>。）</p>
        <el-table :data="users" style="width: 100%">
            <el-table-column prop="userName" label="人员名称" width="150" />
            <el-table-column label="模块权限列表" width="500">
                <template v-slot="scope">
                    <el-table :data="scope.row.permissions" style="width: 100%">
                        <el-table-column prop="moduleName" label="模块名称" width="200" />
                        <el-table-column prop="permissionLevel" label="权限等级" width="200" />
                    </el-table>
                </template>
            </el-table-column>
        </el-table>
    </div>
    <div v-if="this.permission > 0">
        <h1>人员管理</h1>
        <p>（目前开放三个权限等级，无权限<0，只读权限=0，读写权限>0</0>。）</p>

        <el-row :gutter="20" style="margin-bottom: 20px;">
            <el-col :span="4">
                <el-input v-model="userName" placeholder="人员名称"></el-input>
            </el-col>
            <el-col :span="4">
                <el-input v-model="moduleName" placeholder="功能名称"></el-input>
            </el-col>
            <el-col :span="4">
                <el-input v-model.number="permissionLevel" placeholder="权限等级" type="number"></el-input>
            </el-col>
            <el-col :span="4">
                <el-button @click="updPermission">调整权限</el-button>
            </el-col>
            <el-col :span="8">
                <el-button type="success" @click="registerDialogVisible = true"
                    class="register-button">注册新员工</el-button>
            </el-col>
            <el-dialog title="注册" v-model="registerDialogVisible">
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
        <el-table :data="users" style="width: 100%">
            <el-table-column prop="userName" label="人员名称" width="150" />
            <el-table-column label="模块权限列表" width="500">
                <template v-slot="scope">
                    <el-table :data="scope.row.permissions" style="width: 100%">
                        <el-table-column prop="moduleName" label="模块名称" width="200" />
                        <el-table-column prop="permissionLevel" label="权限等级" width="200" />
                    </el-table>
                </template>

            </el-table-column>

            <el-table-column label="操作">
                <template v-slot="scope">
                    <el-button type="danger" @click="delUser(scope.row.id)">删除</el-button>
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
            permission: -1,
            registerDialogVisible: false,
            companyId: localStorage.getItem('companyId'),
            users: [],
            userName: '',
            moduleName: '',
            permissionLevel: 0,
            registeruserName: '',
            registerPassword: '',
            registerrePassword: '',
        };

    },
    mounted() {
        this.fetchUsers();
        this.getPermission();
    },
    methods: {
        async getPermission() {
            const moduleId = 5;
            const userId = localStorage.getItem('userId');
            const response = await axios.get(`http://localhost:8080/api/permission/${userId}/${moduleId}`);
            this.permission = response.data.data;
        },
        async fetchUsers() {
            const response = await axios.get(`http://localhost:8080/api/user/${this.companyId}/user`);
            this.users = response.data.data;
            console.log(response);
            for (const user of this.users) {
                try {
                    const response = await axios.get(`http://localhost:8080/api/permission/${user.id}/permission`);
                    const response2 = await axios.get(`http://localhost:8080/api/user/${user.id}/name`);

                    user.permissions = response.data.data;
                    user.userName = response2.data.data;
                    for (const permission of user.permissions) {
                        try {
                            const response = await axios.get(`http://localhost:8080/api/module/${permission.moduleId}/name`);
                            permission.moduleName = response.data.data;
                        } catch (error) {
                            permission.moduleName = '获取模块名称失败';
                        }
                    }
                } catch (error) {
                    user.permissions = '获取权限失败';
                }
            }
        },
        async updPermission() {
            const request = {
                userName: this.userName,
                moduleName: this.moduleName,
                permissionLevel: this.permissionLevel,
            };
            console.log(request);
            const response = await axios.post('http://localhost:8080/api/permission/updpermission', request);
            console.log(response);
            if (response.data.success) {
                this.fetchUsers();
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
        },
        async delUser(id) {
            const request = {
                id: id
            };
            console.log(request);
            const response = await axios.post('http://localhost:8080/api/user/deluser', request);
            console.log(response);
            if (response.data.success) {
                this.fetchUsers();
                ElMessage.success(response.data.message);
            } else {
                ElMessage.error(response.data.message);
            }
        },

    },

}

</script>

<style scoped>
/* 商品管理样式 */
</style>
<!-- End of Selection -->