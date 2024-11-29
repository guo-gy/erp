<template>
    <div v-if="this.permission < 0">
        <h1>无权限访问</h1>
        <p>您没有权限访问此页面。</p>
    </div>
    <div v-if="this.permission === 0">
        <h1>任务中心</h1>
        <p>您仅拥有读权限，无权指派任务</p>
        <el-table :data="works" style="width: 100%">
            <el-table-column prop="id" label="任务ID" />
            <el-table-column prop="name" label="任务名称" />
            <el-table-column prop="originName" label="指派人" />
            <el-table-column label="操作">
                <template v-slot="scope">
                    <el-button v-if="scope.row.status === false" @click="updWork(scope.row.id)"
                        type="success">完成</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
    <div v-if="this.permission > 0">
        <h1>任务中心</h1>
        <el-row :gutter="20" style="margin-bottom: 20px;">
            <el-col :span="8">
                <el-input v-model="targetName" placeholder="输入指派员工名称"></el-input>
            </el-col>
            <el-col :span="8">
                <el-input v-model="workName" placeholder="输入任务信息"></el-input>
            </el-col>
            <el-col :span="8">
                <el-button @click="addWork">任务指派</el-button>
            </el-col>
        </el-row>
        <el-table :data="works" style="width: 100%">
            <el-table-column prop="id" label="任务ID" />
            <el-table-column prop="name" label="任务名称" />
            <el-table-column prop="originName" label="指派人" />
            <el-table-column label="操作">
                <template v-slot="scope">
                    <el-button v-if="scope.row.status === false" @click="updWork(scope.row.id)"
                        type="success">完成</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import axios from 'axios';
import { ElMessage } from 'element-plus';
export default {
    name: 'WorkManagement',
    data() {
        return {
            permission: -1,
            userId: localStorage.getItem('userId'),
            works: [],
            targetName: '',
            workName: '',
        };
    },
    mounted() {
        this.getPermission();
        this.fetchWorks();
    },
    methods: {
        async getPermission() {
            const moduleId = 1;
            const userId = localStorage.getItem('userId');
            const response = await axios.get(`http://localhost:8080/api/permission/${userId}/${moduleId}`);
            this.permission = response.data.data;
        },
        async fetchWorks() {
            const response = await axios.get(`http://localhost:8080/api/work/${this.userId}/target`);
            this.works = response.data.data;
            for (const work of this.works) {
                try {
                    const response = await axios.get(`http://localhost:8080/api/user/${work.originId}/name`);
                    work.originName = response.data.data;
                } catch (error) {
                    work.originName = '获取派出人员名称失败';
                }
            }
        },
        async addWork() {
            const request = {
                targetName: this.targetName,
                originId: this.userId,
                workName: this.workName,
                status: false
            };
            const response = await axios.post('http://localhost:8080/api/work/addwork', request);
            console.log(response);
            if (response.data.success) {
                this.fetchWorks();
                ElMessage.success(response.data.message);
            } else {
                ElMessage.error(response.data.message);
            }
        },
        async updWork(id) {
            const request = {
                id: id,
                status: true
            };
            const response = await axios.post('http://localhost:8080/api/work/updwork', request);
            this.fetchWorks();
            ElMessage.success("完成任务成功");

        }
    },
}
</script>

<style scoped>
/* 商品管理样式 */
</style>